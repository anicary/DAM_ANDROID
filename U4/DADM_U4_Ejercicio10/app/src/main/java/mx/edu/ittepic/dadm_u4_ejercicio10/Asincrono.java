package mx.edu.ittepic.dadm_u4_ejercicio10;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by UsuarioPrueba on 10/11/2017.
 */

public class Asincrono extends AsyncTask <Integer,String, int[]> {
    MainActivity puntero;
    int[]vector;
    ProgressDialog dialogo;

    public Asincrono(MainActivity p){
        puntero = p;
    }
    public  void onPreExecute(){
        //SE INVOCA DESPUES DEL CONSTRUCTOR EN ESTE CASO DE PUBLIC ASINCRONO
        super.onPreExecute();
        dialogo = new ProgressDialog(puntero);
        dialogo.setTitle("Procesando...");
        dialogo.setMessage("Iniciando generacion de numeros");
        dialogo.show();
        dormir(150);
    }
    private void dormir(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch (InterruptedException e){
            Toast.makeText(puntero,"ERROR EN SLEEP",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected int[] doInBackground(Integer... integers) {
        vector = new int[integers[0]];
        for (int i=0;i<vector.length;i++){
            vector[i]=(int) (Math.random()*49+1);
            publishProgress("GENERANDO NUMERO: "+vector[i]);//INVOCA AL PROGRESSUPDATE DE FORMA AUTOMATICA
            dormir(200);
        }
        return vector;
    }
}
