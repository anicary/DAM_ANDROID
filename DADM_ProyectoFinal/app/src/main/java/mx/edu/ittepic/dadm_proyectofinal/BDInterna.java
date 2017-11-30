package mx.edu.ittepic.dadm_proyectofinal;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDInterna  extends SQLiteOpenHelper {
    public BDInterna(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario(idusuarios INTEGER, nombre VARCHAR(400), apellidos VARCHAR(400),correo VARCHAR(400),imagen VARCHAR(400));");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}