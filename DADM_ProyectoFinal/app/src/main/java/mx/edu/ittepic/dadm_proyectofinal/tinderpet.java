package mx.edu.ittepic.dadm_proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.rahuljanagouda.statusstories.StatusStoriesActivity;

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
    String imagenesMostrar[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinderpet);
      //  setTitle("Tinder Pet");
        SharedPreferences prefs =  getSharedPreferences("INFO_USUARIO", Context.MODE_PRIVATE);
        idusuarios = prefs.getString("idusuarios", "0");
        Menu_lista = (ListView) findViewById(R.id.tinderlista);
        cargarMascotas();
    }
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.tindermenu,m);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem mi){
        switch (mi.getItemId()){
            case R.id.pet:
                final String[] resources = new String[]{
                        "https://firebasestorage.googleapis.com/v0/b/firebase-satya.appspot.com/o/images%2Fi00001.jpg?alt=media&token=460667e4-e084-4dc5-b873-eefa028cec32",
                        "https://firebasestorage.googleapis.com/v0/b/firebase-satya.appspot.com/o/images%2Fi00002.jpg?alt=media&token=e8e86192-eb5d-4e99-b1a8-f00debcdc016",
                        "https://firebasestorage.googleapis.com/v0/b/firebase-satya.appspot.com/o/images%2Fi00004.jpg?alt=media&token=af71cbf5-4be3-4f8a-8a2b-2994bce38377",
                        "https://firebasestorage.googleapis.com/v0/b/firebase-satya.appspot.com/o/images%2Fi00005.jpg?alt=media&token=7d179938-c419-44f4-b965-1993858d6e71",
                        "https://firebasestorage.googleapis.com/v0/b/firebase-satya.appspot.com/o/images%2Fi00006.jpg?alt=media&token=cdd14cf5-6ed0-4fb7-95f5-74618528a48b",
                        "https://firebasestorage.googleapis.com/v0/b/firebase-satya.appspot.com/o/images%2Fi00007.jpg?alt=media&token=98524820-6d7c-4fb4-89b1-65301e1d6053",
                        "https://firebasestorage.googleapis.com/v0/b/firebase-satya.appspot.com/o/images%2Fi00008.jpg?alt=media&token=7ef9ed49-3221-4d49-8fb4-2c79e5dab333",
                        "https://firebasestorage.googleapis.com/v0/b/firebase-satya.appspot.com/o/images%2Fi00009.jpg?alt=media&token=00d56a11-7a92-4998-a05a-e1dd77b02fe4",
                        "https://firebasestorage.googleapis.com/v0/b/firebase-satya.appspot.com/o/images%2Fi00010.jpg?alt=media&token=24f8f091-acb9-432a-ae0f-7e6227d18803",
                };

                Intent a = new Intent(tinderpet.this, StatusStoriesActivity.class);
                a.putExtra(StatusStoriesActivity.STATUS_RESOURCES_KEY, resources);
                a.putExtra(StatusStoriesActivity.STATUS_DURATION_KEY, 3000L);
                a.putExtra(StatusStoriesActivity.IS_IMMERSIVE_KEY, true);
                a.putExtra(StatusStoriesActivity.IS_CACHING_ENABLED_KEY, true);
                a.putExtra(StatusStoriesActivity.IS_TEXT_PROGRESS_ENABLED_KEY, true);
                startActivity(a);
                break;
        }
        return true;
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
