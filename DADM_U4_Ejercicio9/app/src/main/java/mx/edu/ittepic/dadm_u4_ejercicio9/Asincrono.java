package mx.edu.ittepic.dadm_u4_ejercicio9;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by UsuarioPrueba on 09/11/2017.
 */


//A ESTA CLASE SE LE ENVIA UN VALOR ENTERO QUE REPRESENTA EL TAMAÑO DE UN VECTOR
//EL HILO INFORMA ATRAVEZ DE MENSAJES(CADENA)
//COMO RESULTADO SE OBTIENE EL VECTOR CON N NUMEROS ENTEROS ALEATROIOS
//EJEMPLO ENVIO 20,GENERA UN VECTOR DE 20 Y EN CADA CELDILLA SE GUARDA UN ENTERO ALEATORIO
//EL RESULTADO ES EL VECTOR YA CON LOS NUMEROS DENTRO
//SE LE PUEDE PONER VOID AL ASYNTASK Y NO RECIBE PARAMETROS

public class Asincrono extends AsyncTask <Integer,String, int[]> {
    //y aqui tambien seria pollito p
    MainActivity puntero;
    int[]vector;
    ProgressDialog dialogo;
    public Asincrono(MainActivity p){
        //va dirijido a la clase si la clase se llama pollito entonces seria pollito puntero
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
    //doibackgroun es polimorfico porque depende de lo que mandas como resultado para que defeniAS EL TIPO DE PARAMETRO
    //SE EJECUTA UNA VEZ
    protected int[] doInBackground(Integer... integers) {
        //LO QUE SE DEFINE EN LA OTRA CLASE LO PUEDO USAR AQUI A TRAVEZ DE UN PUNTERO
        vector = new int[integers[0]];
        for (int i=0;i<vector.length;i++){
            vector[i]=(int) (Math.random()*99+1);
            publishProgress("GENERANDO NUMERO: "+vector[i]);//INVOCA AL PROGRESSUPDATE DE FORMA AUTOMATICA
            dormir(200);
        }
        return vector;
    }
    protected void onProgressUpdate(String... v){
        dialogo.setMessage(v[0]);
    }
    public void onPostExecute(int[] v){
        //DEBE DE SER DEL TIPO DEL RESULTADO
        String resultado="";

        dialogo.setMessage("SE FINALIZO LA GENERACION DE NUMEROS");
        dormir(1000);
        dialogo.dismiss();
        for (int i=0; i< v.length;i++){
            resultado += v[i]+"   ";

        }
        puntero.etiqueta.setText(resultado);
    }

}
