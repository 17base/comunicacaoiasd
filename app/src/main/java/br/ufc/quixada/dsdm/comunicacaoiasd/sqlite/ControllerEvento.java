package br.ufc.quixada.dsdm.comunicacaoiasd.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.ufc.quixada.dsdm.comunicacaoiasd.model.Evento;

/**
 * Created by jonas_000 on 12/01/2016.
 */

public class ControllerEvento {

    private SQLiteDatabase db;
    private CreateBD createBD;
    private Context context;

    public static final String TABLE_NAME_EVENTO = " evento ";
    public static final String _ID_EVENTO = " id_evento ";
    public static final String _MES_EVENTO = " mes ";
    public static final String _DATA_EVENTO = " data ";
    public static final String _TITULO = " titulo ";
    public static final String _DESCRICAO = " descricao ";
    public static final String _ORGANIZACAO = " organizacao ";

    public ControllerEvento(Context context) {
        this.createBD = new CreateBD(context);
        this.context = context;
    }

    public boolean saveEvento(Evento evento) {
        boolean isSalvo = false;
        ContentValues values;
        long resultadoInsercao;

        db = createBD.getWritableDatabase();
        values = new ContentValues();

        values.put(CreateBD._MES_EVENTO, evento.getMes());
        values.put(CreateBD._DATA_EVENTO, evento.getData());
        values.put(CreateBD._TITULO, evento.getTitulo());
        values.put(CreateBD._DESCRICAO, evento.getDescricao());
        values.put(CreateBD._ORGANIZACAO, evento.getOrganizacao());

        resultadoInsercao = db.insert(CreateBD.TABLE_NAME_EVENTO, null, values);
        db.close();

        if (resultadoInsercao == -1) {
            isSalvo = false;
        } else {
            isSalvo = true;
        }
        return isSalvo;
    }

    public Cursor listEventos() {
        Cursor cursor;

        String[] values = {createBD._ID_EVENTO, createBD._MES_EVENTO, createBD._DATA_EVENTO, createBD._TITULO, createBD._DESCRICAO, createBD._ORGANIZACAO};
        db = createBD.getReadableDatabase();
        cursor = db.query(createBD.TABLE_NAME_EVENTO, values, null, null,null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor findById(Long id) {
        Cursor cursor;

        String[] values = {createBD._ID_EVENTO, createBD._MES_EVENTO, createBD._DATA_EVENTO, createBD._TITULO, createBD._DESCRICAO, createBD._ORGANIZACAO};
        String WHERE = CreateBD._ID_EVENTO + " = " + id;
        db = createBD.getReadableDatabase();
        cursor = db.query(createBD.TABLE_NAME_EVENTO, values, WHERE, null,null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void updateEvento(Evento evento) {

        //Toast.makeText(context, nota.toString(), Toast.LENGTH_LONG).show();

        ContentValues values;
        String WHERE;
        db = createBD.getWritableDatabase();

        WHERE = "id = " + evento.getId();

        values = new ContentValues();
        values.put(CreateBD._MES_EVENTO, evento.getMes());
        values.put(CreateBD._DATA_EVENTO, evento.getData());
        values.put(CreateBD._TITULO, evento.getTitulo());
        values.put(CreateBD._DESCRICAO, evento.getDescricao());
        values.put(CreateBD._ORGANIZACAO, evento.getOrganizacao());

        db.update(CreateBD.TABLE_NAME_EVENTO, values, WHERE, null);
        db.close();
    }

    public void removeEvento(Long id) {
        String WHERE = CreateBD._ID_EVENTO + " = " + id;
        db = createBD.getReadableDatabase();
        db.delete(CreateBD.TABLE_NAME_EVENTO, WHERE, null);
        db.close();
    }

    public Evento cursorToEndereco(Cursor cursor){
        Evento evento = new Evento();
        evento.setId(cursor.getLong(cursor.getColumnIndex(_ID_EVENTO)));
        evento.setMes(cursor.getString(cursor.getColumnIndex(_MES_EVENTO)));
        evento.setData(cursor.getString(cursor.getColumnIndex(_DATA_EVENTO)));
        evento.setTitulo(cursor.getString(cursor.getColumnIndex(_TITULO)));
        evento.setDescricao(cursor.getString(cursor.getColumnIndex(_DESCRICAO)));
        evento.setOrganizacao(cursor.getString(cursor.getColumnIndex(_ORGANIZACAO)));
        return evento;
    }
}