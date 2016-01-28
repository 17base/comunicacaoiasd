package br.ufc.quixada.dsdm.comunicacaoiasd.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jonas_000 on 12/01/2016.
 */


public class CreateBD extends SQLiteOpenHelper {

    public static final String TABLE_NAME_ENDERECO = " endereco ";
    public static final String _ID_ENDERECO = " id_endereco ";
    public static final String _ENDERECO = " endereco ";
    public static final String _BAIRRO = " bairro ";

    public static final String TABLE_NAME_CONTATO = " contato ";
    public static final String _ID_CONTATO = " id_contato ";
    public static final String _DEPARTAMENTO = " departamento ";
    public static final String _RESPONSAVEL = " resposavel ";
    public static final String _ASSOCIADO = " associado ";
    public static final String _EMAIL_RESP = " email_resp ";
    public static final String _TELEFONE_RESP = " telefone_resp ";
    public static final String _EMAIL_ASS = " email_ass ";
    public static final String _TELEFONE_ASS = " email_ass ";
    public static final String _IGREJA = " igreja ";

    public static final String TABLE_NAME_EVENTO = " evento ";
    public static final String _ID_EVENTO = " id_evento ";
    public static final String _MES_EVENTO = " mes ";
    public static final String _DIA_EVENTO = " dia ";
    public static final String _TITULO = " titulo ";
    public static final String _DESCRICAO = " descricao ";
    public static final String _ORGANIZACAO = " organizacao ";

    public static final String TABLE_NAME_ITINERARIO = " itinerario ";
    public static final String _ID_ITINERARIO = " id_itinerario ";
    public static final String _MES_ITINERARIO = " mes ";
    public static final String _DIA_ITINERARIO = " dia ";
    public static final String _ENDERECO_ITINERARIO = " endereco ";
    public static final String _BAIRRO_ITINERARIO = " bairro ";

    private static final String DATABASE_NAME = "comunicacao_iasd.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_CONTATO = " CREATE TABLE "+ TABLE_NAME_CONTATO +
            "(" +
            _ID_CONTATO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            _DEPARTAMENTO + " TEXT NOT NULL, " +
            _RESPONSAVEL + " TEXT NOT NULL, " +
            _ASSOCIADO + " TEXT NOT NULL, " +
            _EMAIL_RESP + " TEXT NOT NULL, " +
            _TELEFONE_RESP + " TEXT NOT NULL, " +
            _EMAIL_ASS + " TEXT NOT NULL, " +
            _TELEFONE_ASS + " TEXT NOT NULL, " +
            _IGREJA + " TEXT NOT NULL, " +
            ")";

    private static final String SQL_CREATE_TABLE_EVENTO = " CREATE TABLE "+ TABLE_NAME_EVENTO +
            "(" +
            _ID_EVENTO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            _MES_EVENTO + " TEXT NOT NULL, " +
            _DIA_EVENTO + " TEXT NOT NULL, " +
            _TITULO + " TEXT NOT NULL, " +
            _DESCRICAO + " TEXT NOT NULL, " +
            _ORGANIZACAO + " TEXT NOT NULL, " +
            ")";

    private static final String SQL_CREATE_TABLE_ITINERARIO = " CREATE TABLE "+ TABLE_NAME_ITINERARIO +
            "(" +
            _ID_ITINERARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            _MES_ITINERARIO + " TEXT NOT NULL, " +
            _DIA_ITINERARIO + " TEXT NOT NULL, " +
            _ENDERECO_ITINERARIO + " TEXT NOT NULL, " +
            _BAIRRO_ITINERARIO + " TEXT NOT NULL, " +
            ")";

    private static final String SQL_CREATE_TABLE_ENDERECO = " CREATE TABLE "+ TABLE_NAME_ENDERECO +
            "(" +
            _ID_ENDERECO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            _ENDERECO + " TEXT NOT NULL, " +
            _BAIRRO + " TEXT NOT NULL, " +
            ")";

    public CreateBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public CreateBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_CONTATO);
        db.execSQL(SQL_CREATE_TABLE_ENDERECO);
        db.execSQL(SQL_CREATE_TABLE_EVENTO);
        db.execSQL(SQL_CREATE_TABLE_ITINERARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(CreateBD.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CONTATO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ENDERECO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_EVENTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ITINERARIO);
        onCreate(db);
    }
}
