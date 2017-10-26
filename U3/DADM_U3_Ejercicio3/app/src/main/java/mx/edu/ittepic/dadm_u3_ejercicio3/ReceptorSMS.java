package mx.edu.ittepic.dadm_u3_ejercicio3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by UsuarioPrueba on 20/10/2017.
 */

public class ReceptorSMS extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context,"SI ESTA TRABAJANDO EL RECIVER", Toast.LENGTH_SHORT).show();
        Bundle b = intent.getExtras();
        try {
            if (b != null) {
                final Object[] entrantes =(Object[]) b.get("pdus");
                for (int i=0; i<entrantes.length; i++){
                    SmsMessage mensaje = SmsMessage.createFromPdu((byte[])entrantes[i]);
                    String numero, contenido;

                    numero = mensaje.getDisplayOriginatingAddress();
                    contenido = mensaje.getDisplayMessageBody();

                    if(contenido.startsWith("!saludo")){
                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage(numero,null,"se a recibido un saludo, gracias.",null,null);
                    }

                    Toast.makeText(context,"Recibido dese: "+numero+"Contenido: " +contenido,Toast.LENGTH_SHORT).show();

                }
            }
        }catch (Exception e){
            Toast.makeText(context,"NO SE PUDO LEER EL SMS ENTRANTE", Toast.LENGTH_SHORT).show();
        }
    }
}
