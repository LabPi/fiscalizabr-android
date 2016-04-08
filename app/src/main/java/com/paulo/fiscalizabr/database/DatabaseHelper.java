package com.paulo.fiscalizabr.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Paulo on 08/04/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FiscalizaBR.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    private static final String SQL_CREATE_CONVENIOS =
            "CREATE TABLE " + Contract.ConvenioEntry.TABLE_NAME + " (" +
                    Contract.ConvenioEntry._ID + " INTEGER PRIMARY KEY," +
                    Contract.ConvenioEntry.COLUMN_NAME_NUMERO_CONVENIO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioEntry.COLUMN_NAME_OBJETO_CONVENIO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioEntry.COLUMN_NAME_IS_FAVORITO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioEntry.COLUMN_NAME_MUNICIPIO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioEntry.COLUMN_NAME_UF + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioEntry.COLUMN_NAME_NOME_PROPONENTE + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioEntry.COLUMN_NAME_VIGENCIA + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioEntry.COLUMN_NAME_VALOR_CONVENIO + TEXT_TYPE +
                    " )";

    private static final String SQL_CREATE_CONVENIO_COMPLETO =
            "CREATE TABLE " + Contract.ConvenioCompletoEntry.TABLE_NAME + " (" +
                    Contract.ConvenioCompletoEntry._ID + " INTEGER PRIMARY KEY," +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_ANO_ASSINATURA + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_ANO_CONVENIO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_ANO_PUBLICACAO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_DATA_ASSINATURA + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_DATA_PUBLICACAO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_ESTA_ASSINADO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_ESTA_PUBLICADO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_ESTA_EMPENHADO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_FIM_VIGENCIA + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_INICIO_VIGENCIA + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_JUSTIFICATIVA + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_MODALIDADE + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_PROGRAMA + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_OBJETO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_NUMERO_CONVENIO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_CARGO_RESPONSAVEL_CONCEDENTE + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_PERMITE_AJUSTE_CRONOGRAMA_FISICO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_POSSUI_ADITIVO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_POSSUI_PROGRAMA_DE_OFICIO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_MUNICIPIO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_PROPONENTE + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_RESPONSAVEL_PROPONENTE + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_QUALIFICACAO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_REGIAO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_UF + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_ANO_PROPOSTA + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_DATA_INICIO_PROPOSTA + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_SITUACAO_CONVENIO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_SITUACAO_PUBLICACAO_CONVENIO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_ULTIMO_EMPENHO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_ULTIMO_PAGAMENTO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_CONTRAPARTIDA_FINANCEIRA + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_CONTRAPARTIDA_FINANCEIRA_BENS_E_SERVICOS + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_DESEMBOLSADO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_GLOBAL + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_REPASSE_UNIAO + TEXT_TYPE + COMMA_SEP +
                    Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_TOTAL_CONTRAPARTIDA + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_CONVENIOS =
            "DROP TABLE IF EXISTS " + Contract.ConvenioEntry.TABLE_NAME;

    private static final String SQL_DELETE_CONVENIO_COMPLETO =
            "DROP TABLE IF EXISTS " + Contract.ConvenioCompletoEntry.TABLE_NAME;

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_CONVENIOS);
        db.execSQL(SQL_CREATE_CONVENIOS);
        db.execSQL(SQL_DELETE_CONVENIO_COMPLETO);
        db.execSQL(SQL_CREATE_CONVENIO_COMPLETO);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
