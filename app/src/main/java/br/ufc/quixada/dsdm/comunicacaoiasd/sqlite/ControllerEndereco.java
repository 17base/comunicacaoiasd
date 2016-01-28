package br.ufc.quixada.dsdm.comunicacaoiasd.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.ufc.quixada.dsdm.comunicacaoiasd.model.Endereco;

/**
 * Created by jonas_000 on 12/01/2016.
 */

public class ControllerEndereco {

    private SQLiteDatabase db;
    private CreateBD createBD;
    private Context context;

    public static final String TABLE_NAME_ENDERECO = " endereco ";
    public static final String _ID_ENDERECO = " id_endereco ";
    public static final String _ENDERECO = " endereco ";
    public static final String _BAIRRO = " bairro ";

    public ControllerEndereco(Context context) {
        this.createBD = new CreateBD(context);
        this.context = context;
    }

    public boolean saveEndereco(Endereco endereco) {
        boolean isSalvo = false;
        ContentValues values;
        long resultadoInsercao;

        db = createBD.getWritableDatabase();
        values = new ContentValues();

        values.put(CreateBD._ENDERECO, endereco.getEndereco());
        values.put(CreateBD._BAIRRO, endereco.getBairro());

        resultadoInsercao = db.insert(CreateBD.TABLE_NAME_ENDERECO, null, values);
        db.close();

        if (resultadoInsercao == -1) {
            isSalvo = false;
        } else {
            isSalvo = true;
        }
        return isSalvo;
    }

    public Cursor listContatos() {
        Cursor cursor;

        String[] values = {createBD._ID_ENDERECO, createBD._ENDERECO, createBD._BAIRRO};
        db = createBD.getReadableDatabase();
        cursor = db.query(createBD.TABLE_NAME_ENDERECO, values, null, null,null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor findById(Long id) {
        Cursor cursor;

        String[] values = {createBD._ID_ENDERECO, createBD._ENDERECO, createBD._BAIRRO};
        String WHERE = CreateBD._ID_ENDERECO + " = " + id;
        db = createBD.getReadableDatabase();
        cursor = db.query(createBD.TABLE_NAME_ENDERECO, values, WHERE, null,null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void updateEndereco(Endereco endereco) {

        //Toast.makeText(context, nota.toString(), Toast.LENGTH_LONG).show();

        ContentValues values;
        String WHERE;
        db = createBD.getWritableDatabase();

        WHERE = "id = " + endereco.getId();

        values = new ContentValues();
        values.put(CreateBD._ENDERECO, endereco.getEndereco());
        values.put(CreateBD._BAIRRO, endereco.getBairro());

        db.update(CreateBD.TABLE_NAME_ENDERECO, values, WHERE, null);
        db.close();
    }

    public void removeEndereco(Long id) {
        String WHERE = CreateBD._ID_ENDERECO + " = " + id;
        db = createBD.getReadableDatabase();
        db.delete(CreateBD.TABLE_NAME_ENDERECO, WHERE, null);
        db.close();
    }

    public Endereco cursorToEndereco(Cursor cursor){
        Endereco endereco = new Endereco();
        endereco.setId(cursor.getLong(cursor.getColumnIndex(_ID_ENDERECO)));
        endereco.setEndereco(cursor.getString(cursor.getColumnIndex(_ENDERECO)));
        endereco.setBairro(cursor.getString(cursor.getColumnIndex(_BAIRRO)));
        return endereco;
    }
}