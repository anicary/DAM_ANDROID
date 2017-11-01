package mx.edu.ittepic.dadm_u4_ejercicio2;

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
            String SQL = "INSERT INTO TRABAJADOR VALUES (IDTRABAJADOR,'NOMBRE','DOMICILIO','PUESTO','SUELDO','FECHAINGRESO')";

            SQL = SQL.replace("NOMBRE",nombre.getText().toString());
            SQL = SQL.replace("DOMICILIO", domicilio.getText().toString());
            SQL = SQL.replace("PUESTO", puesto.getText().toString());
            SQL = SQL.replace("SUELDO",sueldo.getText().toString());
            SQL = SQL.replace("FECHAINGRESO",fecha.getText().toString());


            base.execSQL(SQL);//AQUI SE ESTA REALIZANDO EL INSERT
            //LA LINEA DE ARRIBA HACE TODO MENOS CONSULTAS

            Toast.makeText(this, "SE INSERTO CON EXITO", Toast.LENGTH_LONG).show();

            nombre.setText("");
            domicilio.setText("");

            base.close();

        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}


