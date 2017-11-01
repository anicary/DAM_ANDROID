package mx.edu.ittepic.dadm_u4_ejercicio3;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText equipo1,anotacion1,equipo2,anotacion2,fecha;
    Button btninsertar,btneliminar;
    String idtrabajadorUltimo="";
    BD db;
    BroadcastReceiver receptor;
    IntentFilter filtro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(verificarPermisos())
        {

        }
        try {
            filtro = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
            receptor = new ReceptorSMS();
            registerReceiver(receptor, filtro);//LO REGISTRA A NIVEL DE APLIACION ESPERANDO EL SMS ENTRANTE

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No se logro iniciar el receptor de sms entrante", Toast.LENGTH_SHORT).show();
        }

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
            /* METODO LARGO SIMPLE*/
           /* String cam1,cam2,cam3,cam4,cam5;
            cam1=equipo1.getText().toString();
            cam2=anotacion1.getText().toString();
            cam3=equipo2.getText().toString();
            cam4=anotacion2.getText().toString();
            cam5=fecha.getText().toString();
            String query1 = "INSERT INTO RESULTADOS VALUES (1,'"+cam1+"',"+cam2+",'"+cam3+"',"+cam4+",'"+cam5+"')";
            */
            /* METODO LARGO SIMPLE FIN*/
            /* METODO CONFUSO SIMPLE */
                   /* String query1 = "INSERT INTO RESULTADOS VALUES (1,'"+equipo1.getText().toString()+"',"+anotacion1.getText().toString()+"," +
                    "'"+equipo2.getText().toString()+"',"+anotacion2.getText().toString()+",'"+fecha.getText().toString()+"')"; */
               /* METODO CONFUSO SIMPLE FIN */

            /* METODO LARGO */
            String query1 = "INSERT INTO RESULTADOS VALUES (null,'EQUIPO1',AN1,'EQUIPO2',AN2,'FECHA')";
            query1 = query1.replace("EQUIPO1",equipo1.getText().toString());
            query1 = query1.replace("AN1",anotacion1.getText().toString());
            query1 = query1.replace("EQUIPO2",equipo2.getText().toString());
            query1 = query1.replace("AN2",anotacion2.getText().toString());
            query1 = query1.replace("FECHA",fecha.getText().toString());
            /* METODO LARGO  FIN*/
            base.execSQL(query1);
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
    /* PERMISOS */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private  boolean verificarPermisos() {
        int permisoEnviarMensajes = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        int estadoTelefono = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        int permisoRecivirMensajes = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        int permisoLeerMensajes = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
        List<String> listaPermisos = new ArrayList<>();
        if (estadoTelefono != PackageManager.PERMISSION_GRANTED) {
            listaPermisos.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (permisoEnviarMensajes != PackageManager.PERMISSION_GRANTED) {
            listaPermisos.add(Manifest.permission.SEND_SMS);
        }
        if (permisoRecivirMensajes != PackageManager.PERMISSION_GRANTED) {
            listaPermisos.add(Manifest.permission.RECEIVE_SMS);
        }
        if (permisoLeerMensajes != PackageManager.PERMISSION_GRANTED) {
            listaPermisos.add(Manifest.permission.READ_SMS);
        }
        if (!listaPermisos.isEmpty()) {
            ActivityCompat.requestPermissions(this, listaPermisos.toArray(new String[listaPermisos.size()]),1);
            return false;
        }
        return true;
    }
}
