package br.ufc.quixada.dsdm.comunicacaoiasd.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.ufc.quixada.dsdm.comunicacaoiasd.model.Contato;
import br.ufc.quixada.dsdm.comunicacaoiasd.sqlite.CreateBD;

/**
 * Created by jonas_000 on 12/01/2016.
 */

public class ControllerContato {

    private SQLiteDatabase db;
    private CreateBD createBD;
    private Context context;

    public static final String _ID_CONTATO = " id_contato ";
    public static final String _DEPARTAMENTO = " departamento ";
    public static final String _RESPONSAVEL = " resposavel ";
    public static final String _ASSOCIADO = " associado ";
    public static final String _EMAIL_RESP = " email_resp ";
    public static final String _TELEFONE_RESP = " telefone_resp ";
    public static final String _EMAIL_ASS = " email_ass ";
    public static final String _TELEFONE_ASS = " telefone_ass ";
    public static final String _IGREJA = " igreja ";

    public ControllerContato(Context context) {
        this.createBD = new CreateBD(context);
        this.context = context;
    }

    public boolean saveContato(Contato contato) {
        boolean isSalvo = false;
        ContentValues values;
        long resultadoInsercao;

        db = createBD.getWritableDatabase();
        values = new ContentValues();

        values.put(CreateBD._DEPARTAMENTO, contato.getDep());
        values.put(CreateBD._RESPONSAVEL, contato.getResp());
        values.put(CreateBD._ASSOCIADO, contato.getAss());
        values.put(CreateBD._EMAIL_RESP, contato.getEmail_resp());
        values.put(CreateBD._EMAIL_ASS, contato.getEmail_ass());
        values.put(CreateBD._TELEFONE_RESP, contato.getTelefone_resp());
        values.put(CreateBD._TELEFONE_ASS, contato.getTelefone_ass());
        values.put(CreateBD._IGREJA, contato.getIgreja());

        resultadoInsercao = db.insert(CreateBD.TABLE_NAME_CONTATO, null, values);
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

        String[] values = {createBD._ID_CONTATO, createBD._DEPARTAMENTO, createBD._RESPONSAVEL, createBD._ASSOCIADO, createBD._EMAIL_RESP, createBD._EMAIL_ASS, createBD._TELEFONE_ASS, createBD._TELEFONE_RESP, createBD._IGREJA};
        db = createBD.getReadableDatabase();
        cursor = db.query(createBD.TABLE_NAME_CONTATO, values, null, null,null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor findById(Long id) {
        Cursor cursor;

        String[] values = {createBD._ID_CONTATO, createBD._DEPARTAMENTO, createBD._RESPONSAVEL, createBD._ASSOCIADO, createBD._EMAIL_RESP, createBD._EMAIL_ASS, createBD._TELEFONE_ASS, createBD._TELEFONE_RESP, createBD._IGREJA};
        String WHERE = CreateBD._ID_CONTATO + " = " + id;
        db = createBD.getReadableDatabase();
        cursor = db.query(createBD.TABLE_NAME_CONTATO, values, WHERE, null,null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void updateContato(Contato contato) {

        //Toast.makeText(context, nota.toString(), Toast.LENGTH_LONG).show();

        ContentValues values;
        String WHERE;
        db = createBD.getWritableDatabase();

        WHERE = "id = " + contato.getId();

        values = new ContentValues();
        values.put(CreateBD._DEPARTAMENTO, contato.getDep());
        values.put(CreateBD._RESPONSAVEL, contato.getResp());
        values.put(CreateBD._ASSOCIADO, contato.getAss());
        values.put(CreateBD._EMAIL_RESP, contato.getEmail_resp());
        values.put(CreateBD._EMAIL_ASS, contato.getEmail_ass());
        values.put(CreateBD._TELEFONE_RESP, contato.getTelefone_resp());
        values.put(CreateBD._TELEFONE_ASS, contato.getTelefone_ass());
        values.put(CreateBD._IGREJA, contato.getIgreja());

        db.update(CreateBD.TABLE_NAME_CONTATO, values, WHERE, null);
        db.close();
    }

    public void removeContato(Long id) {
        String WHERE = CreateBD._ID_CONTATO + " = " + id;
        db = createBD.getReadableDatabase();
        db.delete(CreateBD.TABLE_NAME_CONTATO, WHERE, null);
        db.close();
    }

    public Contato cursorToContato(Cursor cursor){
        Contato contato = new Contato();
        contato.setId(cursor.getLong(cursor.getColumnIndex(_ID_CONTATO)));
        contato.setDep(cursor.getString(cursor.getColumnIndex(_DEPARTAMENTO)));
        contato.setResp(cursor.getString(cursor.getColumnIndex(_RESPONSAVEL)));
        contato.setAss(cursor.getString(cursor.getColumnIndex(_ASSOCIADO)));
        contato.setEmail_resp(cursor.getString(cursor.getColumnIndex(_EMAIL_RESP)));
        contato.setEmail_ass(cursor.getString(cursor.getColumnIndex(_EMAIL_ASS)));
        contato.setTelefone_resp(cursor.getString(cursor.getColumnIndex(_TELEFONE_RESP)));
        contato.setTelefone_ass(cursor.getString(cursor.getColumnIndex(_TELEFONE_ASS)));
        contato.setIgreja(cursor.getString(cursor.getColumnIndex(_IGREJA)));
        return contato;
    }
}