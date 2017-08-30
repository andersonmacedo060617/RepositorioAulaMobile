package com.example.aluno.appveiculo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aluno on 30/08/2017.
 */

public class DataBase extends SQLiteOpenHelper {

    public static final String dbName = "banco.db";
    public static final int dbVersion = 3;
    public static Context context;

    public DataBase(Context context) {
        super(context, dbName, null, dbVersion);
        this.context = context;
    }

    public static final String TABLE_USUARIO = "USUARIO";
    public static final String USUARIO_ID = "id";
    public static final String USUARIO_NOME = "nome";
    public static final String USUARIO_LOGIN = "login";
    public static final String USUARIO_SENHA = "senha";
    public static final String USUARIO_TIPO = "tipo";
    public static final String USUARIO_FOTO = "foto";
    public static final String USUARIO_PAGO = "pago";
    public static final String USUARIO_VENCIMENTO = "vencimento";
    private static  final String CREATE_TABLE_USUARIO = "CREATE TABLE " + TABLE_USUARIO + "(" +
                USUARIO_ID + " integer primary key autoincrement, " +
                USUARIO_NOME + " text not null, " +
                USUARIO_LOGIN + " text not null, " +
                USUARIO_SENHA + " text not null, " +
                USUARIO_TIPO + " integer not null, " + //0 - Adm, 1 - Cliente
                USUARIO_FOTO + " text not null, " +
                USUARIO_PAGO + " integer, " + //0 - não pago, 1 - Pago
                USUARIO_VENCIMENTO + " texto" +
            ")";



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table " + TABLE_USUARIO);
        onCreate(sqLiteDatabase);
    }
}
