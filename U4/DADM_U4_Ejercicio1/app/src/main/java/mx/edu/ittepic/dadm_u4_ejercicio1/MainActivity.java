package mx.edu.ittepic.dadm_u4_ejercicio1;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText identificador,nombre,domicilio;
    String id,nom,dom;
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
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarDatos();
            }
        });
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarDatos();
            }
        });
    }
    private void insertarDatos(){//Es privado porque solo se va a utilizar dentro de la clase
        nom = nombre.getText().toString();
        id= identificador.getText().toString();
        dom = domicilio.getText().toString();
        try{
                                //INSERT DELETE UPDATE
            SQLiteDatabase base =db.getWritableDatabase(); //insertar o delete
            String sentenciaSQL = "INSERT INTO PERSONA VALUES (ID,'NOMBRE','DOMICILIO')";

            sentenciaSQL = sentenciaSQL.replace("ID",id);
            sentenciaSQL=sentenciaSQL.replace("NOMBRE",nom);
            sentenciaSQL=sentenciaSQL.replace("DOMICILIO",dom);

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
        id = identificador.getText().toString();
        try{
            SQLiteDatabase base =db.getReadableDatabase();//LECTURA PORQUE ES CONSULTA, no es subsetible a daños
            String sentenciaSQL = "SELECT * FROM PERSONA WHERE ID="+id;
            //ejecuta una sensentencia especificamente en un query un query es un select y te regrese un objeto
            //tipo cursor
           Cursor resultadoConsulta = base.rawQuery(sentenciaSQL,null);
            //movetofirst se va al primer resultado que tiene y si no no muestra nada
            if (resultadoConsulta.moveToFirst()==false){
                Toast.makeText(this,"NO HAY DATOS",Toast.LENGTH_LONG).show();
            }else{
                AlertDialog.Builder alerta = new AlertDialog.Builder(this);
                        alerta.setTitle("RESULTADO")
                                .setMessage("Nombre : "+resultadoConsulta.getString(1)+
                                        "Domicilio : "+resultadoConsulta.getString(2))
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
    private void eliminarDatos(){
       final EditText idBorrar = new EditText(this);
        idBorrar.setInputType(InputType.TYPE_CLASS_NUMBER);
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("ATENCION")
                .setMessage("ID A BORRAR: ")
                .setView(idBorrar)
                .setPositiveButton("BORRAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        eliminarDatos2(idBorrar.getText().toString());
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                }).show();
    }
    private void eliminarDatos2(String idBorrar){
    try{
        SQLiteDatabase base =db.getWritableDatabase();
        String sentenciaSQL = "DELETE FROM PERSONA WHERE ID="+idBorrar;
        base.execSQL(sentenciaSQL);

        Toast.makeText(this,"SE BORRO EL ID "+idBorrar,Toast.LENGTH_LONG).show();
        base.close();
    }catch (SQLException e){
        Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
    }
    }
    private void actualizarDatos(){
        final EditText idActualizar = new EditText(this);
        idActualizar.setInputType(InputType.TYPE_CLASS_NUMBER);
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("ATENCION")
                .setMessage("ID A ACTUALIZAR: ")
                .setView(idActualizar)
                .setPositiveButton("BUSCAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        actualizarDatos2(idActualizar.getText().toString());
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                }).show();
    }
    private void actualizarDatos2(final String idActualizar){
        try{
            SQLiteDatabase base =db.getReadableDatabase();
            String sentenciaSQL = "SELECT * FROM PERSONA WHERE ID="+idActualizar;

            Cursor respuesta = base.rawQuery(sentenciaSQL,null);
            if (respuesta.moveToFirst()==false){
                Toast.makeText(this,"NO EXISTE EL ID ",Toast.LENGTH_LONG).show();
            }else{
                final EditText nombreActualizar,domicilioActualizar;
                LinearLayout layout;

                nombreActualizar = new EditText(this);
                domicilioActualizar = new EditText(this);
                layout = new LinearLayout(this);

                nombreActualizar.setText(respuesta.getString(1));
                domicilioActualizar.setText(respuesta.getString(2));
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(nombreActualizar);
                layout.addView(domicilioActualizar);

                AlertDialog.Builder alerta = new AlertDialog.Builder(this);
                alerta.setTitle("ATENCION")
                        .setMessage("MODIFIQUE DATOS: ")
                        .setView(layout)
                        .setPositiveButton("ACTUALIZAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                actualizarDatos3(idActualizar,nombreActualizar.getText().toString(),domicilioActualizar.getText().toString());
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                        }).show();
            }

            base.close();
        }catch (SQLException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    private void actualizarDatos3(String id, String nombre, String domicilio){
        try{
            SQLiteDatabase base =db.getWritableDatabase();
            String SQL = "UPDATE PERSONA SET NOMBRE ='XX1' , DOMICILIO='XX2' WHERE ID="+id;
            SQL = SQL.replace("XX1",nombre);
            SQL = SQL.replace("XX2",domicilio);

            base.execSQL(SQL);

            Toast.makeText(this,"SE ACTUALIZO REGISTRO CON ID"+id,Toast.LENGTH_LONG).show();
            base.close();

        }catch (SQLException e){

        }
    }

}
