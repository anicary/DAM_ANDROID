package mx.edu.ittepic.dadm_u3_adivinanza_ana_carolina_mondragon_rangel;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver receptor;
    IntentFilter filtro;
    EditText numero,contenido;
    Button PERMISOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       if(verificarPermisos())
       {

       }
       /* PERMISOS=(Button)findViewById(R.id.button);
        PERMISOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        /* PERMISOS */
              /*  ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECEIVE_SMS}, 10);
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS}, 11);
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 225);
                int permisoEstado = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE);
                if (permisoEstado != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
                }*/
          /*      SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage("15555215556", null, "HOLA",null,null);
            }
        }); */

        try {
            filtro = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
            receptor = new ReceptorSMS();
            registerReceiver(receptor, filtro);//LO REGISTRA A NIVEL DE APLIACION ESPERANDO EL SMS ENTRANTE

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No se logro iniciar el receptor de sms entrante", Toast.LENGTH_SHORT).show();
        }
 /*
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num,cont;
                num = numero.getText().toString();
                cont = contenido.getText().toString();

                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(num, null, cont,null,null);

                Toast.makeText(MainActivity.this,"SE ENVIO MENSAJE", Toast.LENGTH_LONG).show();
            }
        }); */
    }
    /* PERMISOS */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
     /*   if (requestCode == 2){
            if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            }
        }
        if (requestCode == 1) {
            if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            }
        }
        if (requestCode == 3) {
            if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            }
        }
        if (requestCode == 4) {
            if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            }
        }*/
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
