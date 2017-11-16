package mx.edu.ittepic.dadm_u4_ejercicio2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by UsuarioPrueba on 31/10/2017.
 */

public class BD extends SQLiteOpenHelper {
    public BD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TRABAJADOR(IDTRABAJADOR INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE VARCHAR(400),DOMICILIO VARCHAR(400),PUESTO VARCHAR(200),SUELDO FLOAT,FECHAINGRESO DATE))");//EJECUTA SQL
        db.execSQL("CREATE TABLE CONYUGUE(IDCONYUGUE INTEGER PRIMARY KEY AUTOINCREMENT,NOMBRE VARCHAR(400),IDTRABAJADOR  INTEGER, FOREGEIN KEY (IDTRABAJADOR) REFERENCES TRABAJADOR (IDTRABAJADOR))");//EJECUTA SQL

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
