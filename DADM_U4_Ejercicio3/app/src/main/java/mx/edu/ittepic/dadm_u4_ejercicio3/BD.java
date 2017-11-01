package mx.edu.ittepic.dadm_u4_ejercicio3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by UsuarioPrueba on 01/11/2017.
 */

public class BD extends SQLiteOpenHelper {
    public BD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE RESULTADOS(IDRESULTADO INTEGER PRIMARY KEY AUTOINCREMENT, EQUIPO1 VARCHAR(200),ANOTACIONEQUIPO1 INT,EQUIPO2 VARCHAR(200),ANOTACIONEQUIPO2 INT,FECHA DATE))");//EJECUTA SQL

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,  int oldVersion, int newVersion) {

    }
}
