package mx.edu.ittepic.dadm_u3_ejercicio3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by UsuarioPrueba on 20/10/2017.
 */

public class ReceptorSMS extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"SI ESTA TRABAJANDO EL RECIVER", Toast.LENGTH_SHORT).show();

    }
}
