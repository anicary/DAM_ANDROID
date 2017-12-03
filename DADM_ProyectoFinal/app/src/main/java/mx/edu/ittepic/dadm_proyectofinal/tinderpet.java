package mx.edu.ittepic.dadm_proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class tinderpet extends AppCompatActivity implements  AsyncResponse {
    tinderAdaptador adater;
    ArrayList<mascota> elemento;
    ArrayList<mascota> elementos;
    ListView Menu_lista;
    ConexionWeb conexionWeb;
    String idusuarios="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinderpet);
        SharedPreferences prefs =  getSharedPreferences("INFO_USUARIO", Context.MODE_PRIVATE);
        idusuarios = prefs.getString("idusuarios", "0");
        Menu_lista = (ListView) findViewById(R.id.tinderlista);
        cargarMascotas();
    }
    private ArrayList<mascota> getElemento() {
        elementos = new ArrayList<mascota>();
        return elementos;
    }
    public void cargarMascotas() {
        try {
            conexionWeb = new ConexionWeb(tinderpet.this);
            conexionWeb.agregarVariables("idusuarios", idusuarios);
            URL direcciopn = new URL("http://carolina.x10host.com/index.php/Sistema/tinder_pet");
            conexionWeb.execute(direcciopn);
        } catch (MalformedURLException e) {
            Toast.makeText(tinderpet.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void procesarRespuesta(String r) {

        elemento = getElemento();
        if(r.equals("no_mascotas")){
            Toast.makeText(tinderpet.this,"Aun no hay mascotas para hacer matchs", Toast.LENGTH_LONG).show();
        }else
        {
            try{
                JSONArray  arrayjson = new JSONArray(r);
                for(int i = 0; i < arrayjson.length(); i++){
                    elementos.add(new mascota(Integer.parseInt(arrayjson.getJSONObject(i).getString("idmascota")),arrayjson.getJSONObject(i).getString("nombre"),arrayjson.getJSONObject(i).getString("edad"),arrayjson.getJSONObject(i).getString("sexo"),arrayjson.getJSONObject(i).getString("razamascota_idrazamascota"),arrayjson.getJSONObject(i).getString("tipo_mascota_idtipo_mascota"),arrayjson.getJSONObject(i).getString("foto_mas")));
                }
                adater= new tinderAdaptador(this, elementos, new tinderAdaptador.botonClick() {
                    @Override
                    public void onBtnClick(int position) {

                    }
                }, new tinderAdaptador.botonClick() {
                    @Override
                    public void onBtnClick(int position) {

                    }
                });
                Menu_lista.setAdapter(adater);
            }catch (JSONException e){

            }
        }
    }
}
