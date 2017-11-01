package mx.edu.ittepic.dadm_u4_ejercicio3;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText equipo1,anotacion1,equipo2,anotacion2,fecha;
    Button btninsertar,btneliminar;
    String idtrabajadorUltimo="";
    BD db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        equipo1 = (EditText) findViewById(R.id.equipo);
        anotacion1 = (EditText) findViewById(R.id.anotacion1);
        equipo2 = (EditText) findViewById(R.id.equipo2);
        anotacion2 = (EditText) findViewById(R.id.anotacion2);
        fecha = (EditText) findViewById(R.id.fecha);

        btninsertar =(Button)findViewById(R.id.insertar);
        btneliminar = (Button) findViewById(R.id.eliminar);

        db = new BD(this,"prueba1",null,1);

        btninsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarDatos();
            }
        });

    }
    private void insertarDatos(){
        try{

            SQLiteDatabase base =db.getWritableDatabase();
            String query1 = "INSERT INTO RESULTADOS VALUES (1,'EQUIPO1','ANOTACIONEQUIPO1','EQUIPO2','ANOTACIONEQUIPO2','FECHA')";

            String query2 = "SELECT MAX(IDRESULTADO) FROM RESULTADOS";

            query1 = query1.replace("EQUIPO1",equipo1.getText().toString());
            query1 = query1.replace("ANOTACIONEQUIPO1",anotacion1.getText().toString());
            query1 = query1.replace("EQUIPO2",equipo2.getText().toString());
            query1 = query1.replace("ANOTACIONEQUIPO2",anotacion2.getText().toString());
            query1 = query1.replace("FECHA",fecha.getText().toString());

            base.execSQL(query1);
            Cursor resultado = base.rawQuery(query2,null); //retorna un cursor
            if (resultado.moveToFirst()) {
                do {
                    idtrabajadorUltimo=resultado.getString(0);
                } while (resultado.moveToNext());
            }

            Toast.makeText(this,"SE INSERTO CON EXITO",Toast.LENGTH_LONG).show();

            equipo1.setText("");
            anotacion1.setText("");
            equipo2.setText("");
            anotacion2.setText("");
            fecha.setText("");

            base.close();

        }catch (SQLException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
