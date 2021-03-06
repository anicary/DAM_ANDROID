package mx.edu.ittepic.dadm_u4_ejercicio2;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Insertar extends AppCompatActivity {
    BD db;
    EditText nombre, domicilio, sueldo, puesto, fecha, conyugue;
    String idtrabajadorUltimo="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        nombre = (EditText) findViewById(R.id.nombre);
        domicilio = (EditText) findViewById(R.id.domicilio);
        sueldo = (EditText) findViewById(R.id.sueldo);
        puesto = (EditText) findViewById(R.id.puesto);
        fecha = (EditText) findViewById(R.id.fecha);
        conyugue = (EditText) findViewById(R.id.conyugue);

        db = new BD(this,"prueba1",null,1);
    }

    public boolean onCreateOptionsMenu(Menu m) {

        getMenuInflater().inflate(R.menu.menuinsertar, m);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem elegido) {
        switch (elegido.getItemId()) {
            case R.id.opcinsertar:
                insertarDatos();
                break;
            case R.id.salir:
                finish();
                break;
        }
        return true;
    }

    private void insertarDatos() {
        try {
            SQLiteDatabase base = db.getWritableDatabase();
            String query1 = "INSERT INTO TRABAJADOR VALUES (null,'NOMBRE','DOMICILIO','PUESTO','SUELDO','FECHAINGRESO')";
            String query2 = "SELECT MAX(IDTRABAJADOR) FROM TRABAJADOR";

            query1 = query1.replace("NOMBRE",nombre.getText().toString());
            query1 = query1.replace("DOMICILIO", domicilio.getText().toString());
            query1 = query1.replace("PUESTO", puesto.getText().toString());
            query1 = query1.replace("SUELDO",sueldo.getText().toString());
            query1 = query1.replace("FECHAINGRESO",fecha.getText().toString());

            base.execSQL(query1); //no retorna nada

            Cursor resultado = base.rawQuery(query2,null); //retorna un cursor
            if (resultado.moveToFirst()) {
                do {
                    idtrabajadorUltimo=resultado.getString(0);
                } while (resultado.moveToNext());
            }
            String query3 = "INSERT INTO CONYUGE VALUES (null,'NOMBRE',"+idtrabajadorUltimo+")";

            query3 = query3.replace("NOMBRE",nombre.getText().toString());
            base.execSQL(query3); //no retorna nada



            Toast.makeText(this, "SE INSERTO CON EXITO", Toast.LENGTH_LONG).show();

            nombre.setText("");
            domicilio.setText("");
            puesto.setText("");
            sueldo.setText("");
            fecha.setText("");
            conyugue.setText("");

            base.close();

        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}


