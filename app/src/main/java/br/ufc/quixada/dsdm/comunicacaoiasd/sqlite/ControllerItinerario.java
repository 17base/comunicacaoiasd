package br.ufc.quixada.dsdm.comunicacaoiasd.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.ufc.quixada.dsdm.comunicacaoiasd.model.Itinerario;

/**
 * Created by jonas_000 on 12/01/2016.
 */

public class ControllerItinerario {

    private SQLiteDatabase db;
    private CreateBD createBD;
    private Context context;

    public static final String TABLE_NAME_ITINERARIO = " itinerario ";
    public static final String _ID_ITINERARIO = " id_itinerario ";
    public static final String _MES_ITINERARIO = " mes ";
    public static final String _DIA_ITINERARIO = " dia ";
    public static final String _ENDERECO_ITINERARIO = " endereco ";
    public static final String _BAIRRO_ITINERARIO = " bairro ";

    public ControllerItinerario(Context context) {
        this.createBD = new CreateBD(context);
        this.context = context;
    }

    public boolean saveItinerario(Itinerario itinerario) {
        boolean isSalvo = false;
        ContentValues values;
        long resultadoInsercao;

        db = createBD.getWritableDatabase();
        values = new ContentValues();

        values.put(CreateBD._MES_ITINERARIO, itinerario.getMes());
        values.put(CreateBD._DIA_ITINERARIO, itinerario.getDia());
        values.put(CreateBD._ENDERECO_ITINERARIO, itinerario.getEndereco());
        values.put(CreateBD._BAIRRO_ITINERARIO, itinerario.getBairro());

        resultadoInsercao = db.insert(CreateBD.TABLE_NAME_ITINERARIO, null, values);
        db.close();

        if (resultadoInsercao == -1) {
            isSalvo = false;
        } else {
            isSalvo = true;
        }
        return isSalvo;
    }

    public Cursor listItinerario() {
        Cursor cursor;

        String[] values = {createBD._ID_ITINERARIO, createBD._MES_ITINERARIO, createBD._DIA_ITINERARIO, createBD._ENDERECO_ITINERARIO, createBD._BAIRRO_ITINERARIO};
        db = createBD.getReadableDatabase();
        cursor = db.query(createBD.TABLE_NAME_ITINERARIO, values, null, null,null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor findById(Long id) {
        Cursor cursor;

        String[] values = {createBD._ID_ITINERARIO, createBD._MES_ITINERARIO, createBD._DIA_ITINERARIO, createBD._ENDERECO_ITINERARIO, createBD._BAIRRO_ITINERARIO};
        String WHERE = CreateBD._ID_ITINERARIO + " = " + id;
        db = createBD.getReadableDatabase();
        cursor = db.query(createBD.TABLE_NAME_ITINERARIO, values, WHERE, null,null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void updateItinerario(Itinerario itinerario) {

        //Toast.makeText(context, nota.toString(), Toast.LENGTH_LONG).show();

        ContentValues values;
        String WHERE;
        db = createBD.getWritableDatabase();

        WHERE = "id = " + itinerario.getId();

        values = new ContentValues();
        values.put(CreateBD._MES_ITINERARIO, itinerario.getMes());
        values.put(CreateBD._DIA_ITINERARIO, itinerario.getDia());
        values.put(CreateBD._ENDERECO_ITINERARIO, itinerario.getEndereco());
        values.put(CreateBD._BAIRRO_ITINERARIO, itinerario.getBairro());

        db.update(CreateBD.TABLE_NAME_ITINERARIO, values, WHERE, null);
        db.close();
    }

    public void removeItinerario(Long id) {
        String WHERE = CreateBD._ID_ITINERARIO + " = " + id;
        db = createBD.getReadableDatabase();
        db.delete(CreateBD.TABLE_NAME_ITINERARIO, WHERE, null);
        db.close();
    }

    public Itinerario cursorToItinerario(Cursor cursor){
        Itinerario itinerario = new Itinerario();
        itinerario.setId(cursor.getLong(cursor.getColumnIndex(_ID_ITINERARIO)));
        itinerario.setMes(cursor.getString(cursor.getColumnIndex(_MES_ITINERARIO)));
        itinerario.setDia(cursor.getString(cursor.getColumnIndex(_DIA_ITINERARIO)));
        itinerario.setEndereco(cursor.getString(cursor.getColumnIndex(_ENDERECO_ITINERARIO)));
        itinerario.setBairro(cursor.getString(cursor.getColumnIndex(_BAIRRO_ITINERARIO)));
        return itinerario;
    }
}