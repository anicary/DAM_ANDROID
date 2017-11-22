package mx.edu.ittepic.dadm_u4_ejercicio1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by UsuarioPrueba on 25/10/2017.
 */
//openHelper es como un asistente
    //sqlite es un middleware que lo utiliza android pero no esta echo por android
public class BaseDatos extends SQLiteOpenHelper  {
    //activida que lo va a usar, base de datos, cursosfactory se deja en null y la version siempre va a ser 1
    //el nombre de la BD se pone en el mainactivity
    public BaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //estructura de tablas que se desea crear
        //tiene un parametro llamado db
        //CUANDO UN ENTERO ES LLAVE PRIMARIA ES INTEGER DE LO CONTRARIO SOLO ES INT
        db.execSQL("CREATE TABLE PERSONA(ID INTEGER PRIMARY KEY, NOMBRE VARCHAR(200),DOMICILIO VARCHAR(300))");//EJECUTA SQL
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //MODIFICAR LA ESTRUCTURA DE UNA BD ANTERIOR EN EL MISMO PROYECTO(CAMIAR UNA O MAS TABLAS)
        //EJEMPLO AGREGARLA A UNA PERSONA 3 COLUMNAS:TRABAJO,SUELDO Y CREDITO
    }
}
