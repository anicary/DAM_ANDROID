package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_bdremota;

import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by UsuarioPrueba on 13/11/2017.
 */

public class ConexionRemota extends AsyncTask<URL,Void,String> {
    public List<String[]> variables;
    public MainActivity puntero;
    public AgregarContacto agregar;
    public EditarContacto editareliminar;
    public  ConexionRemota(MainActivity p)
    {
        variables= new ArrayList<>();
        puntero=p;
    }
    public  ConexionRemota(AgregarContacto p)
    {
        variables= new ArrayList<>();
        agregar=p;
    }
    public  ConexionRemota(EditarContacto p)
    {
        variables= new ArrayList<>();
        editareliminar=p;
    }
    public void agregarValores(String indentificador,String Datos)
    {
        String[] temp={indentificador,Datos};
        variables.add(temp);
    }
    public void limpiarVariables(){

        variables= new ArrayList<String[]>();
    }
    @Override
    protected String doInBackground(URL... params) {
        String POST="",Respuesta="";
        for(int i=0;i<variables.size();i++)
        {
            String[] temp=variables.get(i);
            try {
                POST+=temp[0]+"="+ URLEncoder.encode(temp[1], "UTF-8")+" ";
            }
            catch (UnsupportedEncodingException E)
            {
                E.printStackTrace();
            }
        }
        POST=POST.trim();
        POST=POST.replaceAll(" ", "&");
        HttpURLConnection conexion = null;
        try {
            conexion = (HttpURLConnection)params[0].openConnection();
            conexion.setDoOutput(true);//Activa el post
            conexion.setFixedLengthStreamingMode(POST.length());//Cantidad letras enviar
            conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//Conexion como formulario.
            OutputStream flujosSalida= new BufferedOutputStream(conexion.getOutputStream());
            flujosSalida.write(POST.getBytes());
            flujosSalida.flush();
            flujosSalida.close();
            if(conexion.getResponseCode() == 200)
            {
                InputStreamReader inputStreamReader = new InputStreamReader(conexion.getInputStream(),"UTF-8");
                BufferedReader flujoEntrada= new BufferedReader(inputStreamReader);
                String linea="";
                do
                {
                    linea+=flujoEntrada.readLine();
                    if(linea!=null)
                    {
                        Respuesta+=linea;
                    }

                }while(linea==null);
            }
            else
            {
                Respuesta="ERROR 404_1";
            }
        }catch (UnknownHostException e)
        {
            return  "ERROR 404_2";// HOST NO ACTIVO
        }
        catch (IOException e)
        {
            return  "ERROR 404_2"; //FLUJO DE ENTRADA Y SALIDA
        }
        finally {
            if(conexion!=null)
            {
                conexion.disconnect();
            }
            else
            {
                System.out.println("Error de conexion");
            }
        }
        return Respuesta;
    }
    protected void onPostExecute(String respuesta){
        if(puntero!=null)
        {
            puntero.mensaje(respuesta);
            limpiarVariables();
        }
        if(agregar!=null)
        {
            limpiarVariables();
        }
        if(editareliminar!=null)
        {
            limpiarVariables();
        }


    }
}
