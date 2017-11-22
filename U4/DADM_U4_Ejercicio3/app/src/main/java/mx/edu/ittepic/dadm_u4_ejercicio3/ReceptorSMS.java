package mx.edu.ittepic.dadm_u4_ejercicio3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;
public class ReceptorSMS  extends BroadcastReceiver {
    BD db;
    @Override
    public void onReceive(Context context, Intent intent) {
        db = new BD(context,"prueba1",null,1);
        Bundle b = intent.getExtras();
        try {
            if (b != null) {
                final Object[] entrantes =(Object[]) b.get("pdus");
                for (int i=0; i<entrantes.length; i++){
                    SmsMessage mensaje = SmsMessage.createFromPdu((byte[])entrantes[i]);
                    String numero, contenido;
                    numero = mensaje.getDisplayOriginatingAddress();
                    contenido = mensaje.getDisplayMessageBody();
                    SmsManager sms = SmsManager.getDefault();
                    String comparador=contenido.substring(0,10);
                    if(comparador.startsWith("!RESULTADO")){
                        String fecha=contenido.substring(11,contenido.length());
                        SQLiteDatabase base =db.getWritableDatabase();
                        String query1 = "SELECT * FROM RESULTADOS WHERE FECHA='"+fecha+"';";
                        System.out.println("fecha"+fecha);
                        Cursor resultado = base.rawQuery(query1,null); //retorna un cursor
                        if (resultado.moveToFirst()) {
                            sms.sendTextMessage(numero, null, "LOS RESULTADOS PARA LA FECHA "+fecha+" SON:", null, null);
                            String mensajes[] = new String[resultado.getCount()];
                            int pos=0;
                            do {
                                Toast.makeText(context,"MENSAJE "+fecha+"", Toast.LENGTH_SHORT).show();
                                mensajes[pos]=""+resultado.getString(1)+""+":"+resultado.getString(2)+"--"+resultado.getString(3)+""+":"+resultado.getString(4);
                                pos++;
                            } while (resultado.moveToNext());

                            for(int e=0;e<mensajes.length;e++)
                            {
                                sms.sendTextMessage(numero, null,""+ xonvertidorNonAscii(mensajes[e]), null, null);
                            }

                        }else
                        {
                            sms.sendTextMessage(numero, null,"NO HAY PARTIDOS EN ESA FECHA  ", null, null);
                        }

                    }else{
                        Toast.makeText(context,"error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }catch (Exception e){
            Toast.makeText(context,"NO SE PUDO ENVIAR EL MENSAJE", Toast.LENGTH_SHORT).show();
        }
    }
    private static final String PLAIN_ASCII = "AaEeIiOoUu"
            + "AaEeIiOoUuYy"
            + "AaEeIiOoUuYy"
            + "AaOoNn"
            + "AaEeIiOoUuYy"
            + "Aa"
            + "Cc"
            + "OoUu"
            ;
    private static final String UNICODE = "\u00C0\u00E0\u00C8\u00E8\u00CC\u00EC\u00D2\u00F2\u00D9\u00F9"
            + "\u00C1\u00E1\u00C9\u00E9\u00CD\u00ED\u00D3\u00F3\u00DA\u00FA\u00DD\u00FD"
            + "\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177"
            + "\u00C3\u00E3\u00D5\u00F5\u00D1\u00F1"
            + "\u00C4\u00E4\u00CB\u00EB\u00CF\u00EF\u00D6\u00F6\u00DC\u00FC\u0178\u00FF"
            + "\u00C5\u00E5" + "\u00C7\u00E7" + "\u0150\u0151\u0170\u0171";

    public static String xonvertidorNonAscii(String s) {
        if (s == null)
            return null;
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int pos = UNICODE.indexOf(c);
            if (pos > -1) {
                sb.append(PLAIN_ASCII.charAt(pos));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}