package mx.edu.ittepic.dadm_u3_adivinanza_ana_carolina_mondragon_rangel;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by UsuarioPrueba on 22/10/2017.
 */


public class ReceptorSMS extends BroadcastReceiver {
    int intento=0,adivina=0;
    String [][] adivinanza ={
            {"QUE TIENE EL REY EN LA PANSA??","OMBLIGO","EL OMBLIGO"},
            {"LANA SUBE LANA BAJA, QUE ES?","LA NAVAJA","NAVAJA"},
    };
    Random  ran = new Random();
    @Override
    public void onReceive(Context context, Intent intent) {
                /* PERMISOS */
        //Toast.makeText(context,"SI ESTA TRABAJANDO EL RECIVER", Toast.LENGTH_SHORT).show();
        Bundle b = intent.getExtras();
    //    try {
            if (b != null) {
                final Object[] entrantes =(Object[]) b.get("pdus");
                for (int i=0; i<entrantes.length; i++){
                    SmsMessage mensaje = SmsMessage.createFromPdu((byte[])entrantes[i]);
                    String numero, contenido;
                    numero = mensaje.getDisplayOriginatingAddress();
                    contenido = mensaje.getDisplayMessageBody();
                    SmsManager sms = SmsManager.getDefault();
                    if(contenido.startsWith("!ADIVINANZA")){

                        if(intento==0)
                        {
                            adivina=ran.nextInt(entrantes.length+1);
                        }

                    }
                    if(intento<4)
                    {
                        if(intento==0)
                        {
                            sms.sendTextMessage(numero, null, ""+adivinanza[adivina][0] +" ", null, null);
                            intento++;
                        }else
                        {
                            if(contenido.startsWith(""+adivinanza[adivina][1]) || contenido.startsWith(""+adivinanza[adivina][2]) )
                            {
                                sms.sendTextMessage(numero, null, "CORRECTO!!", null, null);
                                intento=0;
                            }else
                            {
                                sms.sendTextMessage(numero, null, "Incorrecto Intentos: "+(intento), null, null);
                                intento++;
                            }
                        }


                    }
                    if(intento>=4)
                    {
                        intento=0;
                        sms.sendTextMessage(numero, null, "Termino las respuestas correctas eran: "+adivinanza[adivina][1]+","+adivinanza[adivina][2], null, null);
                    }
                   //Toast.makeText(context,"Recibido dese: "+numero+"Contenido: " +contenido,Toast.LENGTH_SHORT).show();
                }
            }
     /*   }catch (Exception e){
            Toast.makeText(context,"NO SE PUDO ENVIAR EL MENSAJE", Toast.LENGTH_SHORT).show();
        } */
    }
}

