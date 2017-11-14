package mx.edu.ittepic.dadm_u4_ejercicio11;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by UsuarioPrueba on 14/11/2017.
 */
//URL ES LA DIRECCION WEB QUE CORRESPONDE CON EL PHP, MENSAJES,LO QUE RESPONDE EL SERVIDOR
public class ConexionWeb extends AsyncTask<URL,String,String> {
    List<String[]> variables;
    MainActivity puntero;
    ProgressDialog dialogo;

    public ConexionWeb(MainActivity p){
        puntero = p;
        variables = new ArrayList<String[]>();
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
            Toast.makeText(puntero,"NO SE PUDO CONVERTIR A URL",Toast.LENGTH_LONG).show();
        }
        post=post.trim(); //qita los esácion es blancos antes y despues de la cadena
        post = post.replaceAll(" ","&");
        return post;
    }


    /*
    1.- CREAR EL CODIGO DE ENVIO DE VARIABLES(NOMBRE,DOM,SEXO)
    2.-ABRIR LA CONEXION CON EL SERVIDOR REMOTO/HOSTING
    3.- SE ENVIAN LAS VARIABLES Y VALORES CONVERTIDAS EN FORMATO POST
    4.-SE REVISA SI FUERON ENVIADAS CON EXITO EN CUYO CASO SE DEBERA ESPERAR POR LA RESPUESTA DEL SERVIDOR
    5.- SE CIERRAN CONEXIONES Y SE MUESTRA LA RESPUESTA DEL SERVER
     */

    @Override
    protected String doInBackground(URL... urls) {
        return null;
    }
}
