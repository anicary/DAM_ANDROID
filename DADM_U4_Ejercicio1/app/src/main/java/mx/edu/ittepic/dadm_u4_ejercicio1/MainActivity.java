package mx.edu.ittepic.dadm_u4_ejercicio1;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
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
        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultarDatos();
            }
        });
    }
    private void insertarDatos(){
        try{
                                //INSERT DELETE UPDATE
            SQLiteDatabase base =db.getWritableDatabase(); //insertar o delete
            String sentenciaSQL = "INSERT INTO PERSONA VALUES (ID,'NOMBRE','DOMICILIO')";

            sentenciaSQL = sentenciaSQL.replace("ID",identificador.getText().toString());
            sentenciaSQL=sentenciaSQL.replace("NOMBRE",nombre.getText().toString());
            sentenciaSQL=sentenciaSQL.replace("DOMICILIO",domicilio.getText().toString());

            base.execSQL(sentenciaSQL);//AQUI SE ESTA REALIZANDO EL INSERT
            //LA LINEA DE ARRIBA HACE TODO MENOS CONSULTAS

            Toast.makeText(this,"SE INSERTO CON EXITO",Toast.LENGTH_LONG).show();

            identificador.setText("");
            nombre.setText("");
            domicilio.setText("");

            base.close();

        }catch (SQLException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    private void consultarDatos(){
        try{
            SQLiteDatabase base =db.getReadableDatabase();//LECTURA PORQUE ES CONSULTA, no es subsetible a daños
            String sentenciaSQL = "SELECT * FROM PRESONA WHERE ID="+identificador.getText().toString();
            //ejecuta una sensentencia especificamente en un query un query es un select y te regrese un objeto
            //tipo cursor
           Cursor resultadoConsulta = base.rawQuery(sentenciaSQL,null);
            //movetofirst se va al primer resultado que tiene y si no no muestra nada
            if (resultadoConsulta.moveToFirst()==false){
                Toast.makeText(this,"NO HAY DATOS",Toast.LENGTH_LONG).show();
            }else{
                AlertDialog.Builder alerta = new AlertDialog.Builder(this);
                        alerta.setTitle("RESULTADO")
                                .setMessage("Nombre: "+resultadoConsulta.getString(1)+
                                        "Domicilio: "+resultadoConsulta.getString(2))
                        .setPositiveButton("OK",new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        }).show();
            }
            base.close();
        }catch (SQLException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}
