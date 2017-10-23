package mx.edu.ittepic.dadm_u3_adivinanza_ana_carolina_mondragon_rangel;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
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

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver receptor;
    IntentFilter filtro;
    EditText numero,contenido;
    Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 10);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 123);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        }

        try {
            filtro = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
            receptor = new ReceptorSMS();
            registerReceiver(receptor, filtro);//LO REGISTRA A NIVEL DE APLIACION ESPERANDO EL SMS ENTRANTE

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No se logro iniciar el receptor de sms entrante", Toast.LENGTH_SHORT).show();
        }

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
        });
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 10) {
        }
        if (requestCode == 123){

        }
        if (requestCode == 1) {
            if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

            }
        }
    }

}
