package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_pbd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class BD  extends SQLiteOpenHelper {
    public BD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE cliente(idcliente INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre VARCHAR(200), domicilio VARCHAR(400),colonia VARCHAR(100));");
        db.execSQL("CREATE TABLE orden_reparacion(idordenreparacion INTEGER PRIMARY KEY NOT NULL, fechaingreso date, costo float,observaciones VARCHAR(400));");
        db.execSQL("CREATE TABLE aparato(idaparato INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, descripcion  VARCHAR(200), tipo  VARCHAR(400),idordenreparacion INTEGER, FOREIGN KEY(idordenreparacion) REFERENCES orden_reparacion(idordenreparacion));");
    /*DATOS INICIALES*/
        db.execSQL(" INSERT INTO cliente VALUES (null,'ANA CAROLINA MONDRAGON RANGEL','PASEO REALEZA ','VALLE REAL');");
        db.execSQL(" INSERT INTO cliente VALUES (null,'ADRIANA MONDRAGON RANGEL','PASEO REALEZA ','VALLE REAL');");
        db.execSQL(" INSERT INTO cliente VALUES (null,'GUILLERMO MONDRAGON RANGEL','PASEO REALEZA ','VALLE REAL');");
        db.execSQL(" INSERT INTO cliente VALUES (null,'ZULMA',' bulevar xalisco ','bulevar');");
        db.execSQL(" INSERT INTO cliente VALUES (null,'CARITO',' PASEO xalisco ','PASEO');");
        db.execSQL(" INSERT INTO cliente VALUES (null,'ANA',' PASEO xalisco ','PASEO');");
        db.execSQL(" INSERT INTO cliente VALUES (null,'MONDRAGON',' PASEO xalisco ','PASEO');");
        db.execSQL(" INSERT INTO cliente VALUES (null,'RANGEL',' PASEO xalisco ','PASEO');");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}