package mx.edu.ittepic.dadm_u5_ejercicio1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ConexionWeb extends AsyncTask<URL,String,String> {
    List<String[]> variables; //vector dinamico
    MainActivity puntero;
    ProgressDialog dialogo;


    public ConexionWeb(MainActivity p){
        puntero = p;
        variables = new ArrayList<>();
    }

    public void onPreExecute(){
        dialogo = ProgressDialog.show(puntero,"PROCESANDO...","CONECTANDO CON SERVIDOR");
    }

    public void agregarVariables(String nombreVariable, String contenidoVariable){
        String[] temporal ={nombreVariable,contenidoVariable};
        variables.add(temporal);
    }
    private String generarCadenaPost(){
        String post="";
        try {
            for (int i = 0; i < variables.size(); i++) {
                String[] temporal = variables.get(i);
                post += temporal[0] + "=" + URLEncoder.encode(temporal[1], "UTF-8") + " ";
                //nombre                                    //juanaperez
            }
        }catch (Exception e){
            Toast.makeText(puntero,"NO SE PUDO CONVERTIR A URL", Toast.LENGTH_LONG).show();
        }
        post=post.trim(); //qita los esácion es blancos antes y despues de la cadena
        post = post.replaceAll(" ","&");
        return post;
    }
    protected void onProgressUpdate(String... s){
        //SIRVE PARA ESTAR ACTUALIZANDO EL PROGRESO
        dialogo.setMessage(s[0]);
    }

    /*
    1.- CREAR EL CODIGO DE ENVIO DE VARIABLES(NOMBRE,DOM,SEXO)
    2.-ABRIR LA CONEXION CON EL SERVIDOR REMOTO/HOSTING
    3.- SE ENVIAN LAS VARIABLES Y VALORES CONVERTIDAS EN FORMATO POST
    4.-SE REVISA SI FUERON ENVIADAS CON EXITO EN CUYO CASO SE DEBERA ESPERAR POR LA RESPUESTA DEL SERVIDOR
    5.- SE CIERRAN CONEXIONES Y SE MUESTRA LA RESPUESTA DEL SERVER
     */

    @Override
    protected String doInBackground(URL... urls)
    {
        String POST = generarCadenaPost(); //la cadena se ocupa aqui
        String respuesta=""; //aqui se recuperan los datos
        HttpURLConnection conexion = null;
        try{
            conexion = (HttpURLConnection) urls[0].openConnection();//con esta linea ya se esta conectando a la direccion
           // conexion.setDoInput(true); //ACTIVA EL MODO POST
            conexion.setDoOutput(true);

            conexion.setFixedLengthStreamingMode(POST.length()); //indicas que tamaño o cuantas letras vas a mandar
            publishProgress("Enviando datos..."); //SI NO LO PONEMOS NO IMPORTA
            conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //la creacion de flujo de salida que sirve para enviar la cadena POST
            //fujosalida tiene un write a nivel de red          //para amarrar la conexion
            OutputStream flujoSalida = new BufferedOutputStream(conexion.getOutputStream());
            flujoSalida.write(POST.getBytes());
            flujoSalida.flush();//aqui se forza a que se envie
            flujoSalida.close();
                //si el servidor contesta un 200
            if (conexion.getResponseCode()==200){
                publishProgress("recibiendo respuesta del servidor"); //PARA MANDAR UN MENSAJE
                //tener un mecanismo de recuperacion de lo que me mandara                   //la codificacion como lo recibira
                InputStreamReader entrada = new InputStreamReader(conexion.getInputStream(),"UTF-8");
                BufferedReader flujoEntrada = new BufferedReader(entrada);
                String temp = "";
                do{
                    temp = flujoEntrada.readLine(); //el servidor aqui te puede responder
                    if (temp !=null){
                        respuesta+=temp;
                    }
                }while (temp!=null);
                flujoEntrada.close();

            }else{
                return "ERROR_400"; //en caso de que ocurra un error aqui cacha los errores en caso que sea diferente de 200
            }
        }catch (UnknownHostException e){
            dialogo.dismiss();
            //la esepcion se disparara cuando escribiste mal la direccion web o cuando el server se caiga
            respuesta = "ERROR_404";
        }catch (IOException e){
            dialogo.dismiss();
            //INPUTOUTPUT tiene que ver con el envio, se dispara cuando no se puede enviar o recibir datos
            respuesta = "ERROR_405";
        }finally {
            //cuando el try se ejecuta completo y no sucede una exepcion se invoca el finally
            //si conexion es diferente de null significa que se si se pudo conectar entonces
            if (conexion !=null){
                conexion.disconnect();
            }
        }
        return respuesta; //envia la respuesta del servidor leida anteriormente
    }
    public void onPostExecute(String r){
        dialogo.dismiss();//PARA QUITARLO
        puntero.etiqueta.setText(r); // es la que nos va a mostrar la contestacion del servidor
        //ETIQUETA ESTA EN EL MAINACTIVITY DECLARADA
    }
}