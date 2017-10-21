package mx.edu.ittepic.dadm_u3_ejercicio3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver receptor;
    IntentFilter filtro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            filtro = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
            receptor = new ReceptorSMS();
            registerReceiver(receptor,filtro);//LO REGISTRA A NIVEL DE APLIACION ESPERANDO EL SMS ENTRANTE

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"No se logro iniciar el receptor de sms entrante", Toast.LENGTH_SHORT).show();
        }
    }
}
