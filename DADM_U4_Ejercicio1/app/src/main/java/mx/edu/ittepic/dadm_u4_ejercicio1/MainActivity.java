package mx.edu.ittepic.dadm_u4_ejercicio1;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText identificador,nombre,domicilio;
    Button insertar,consultar,actualizar,eliminar;
    BaseDatos db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        identificador = (EditText)findViewById(R.id.identificador);
        nombre = (EditText)findViewById(R.id.nombre);
        domicilio =(EditText)findViewById(R.id.domicilio);

        insertar = (Button) findViewById(R.id.insertar);
        consultar = (Button)findViewById(R.id.consultar);
        actualizar =(Button) findViewById(R.id.actualizar);
        eliminar = (Button)findViewById(R.id.eliminar);

        db = new BaseDatos(this,"prueba1",null,1);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarDatos();
            }
        });
    }
    private void insertarDatos(){
        try{
                                //INSERT DELETE UPDATE
            SQLiteDatabase base =db.getWritableDatabase();
            String sentenciaSQL = "INSERT INTO PERSONA VALUES (id,'nombre','domicilio')";

            sentenciaSQL.replace("id",identificador.getText().toString());
            sentenciaSQL.replace("nombre",nombre.getText().toString());
            sentenciaSQL.replace("domicilio",domicilio.getText().toString());

            base.execSQL(sentenciaSQL);//AQUI SE ESTA REALIZANDO EL INSERT
            //LA LINEA DE ARRIBA HACE TODO MENOS CONSULTAS

            Toast.makeText(this,"SE INSERTO CON EXITO",Toast.LENGTH_LONG).show();

            identificador.setText("");
            nombre.setText("");
            domicilio.setText("");


        }catch (SQLException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
