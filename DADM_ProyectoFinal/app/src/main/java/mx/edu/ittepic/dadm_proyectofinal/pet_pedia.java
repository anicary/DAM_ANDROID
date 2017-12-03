package mx.edu.ittepic.dadm_proyectofinal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class pet_pedia extends AppCompatActivity implements AsyncResponse {
    razaAdaptador adater;
    ArrayList<raza> elemento;
    ArrayList<raza> elementos;
    ConexionWeb conexionWeb;
    JSONArray arrayjson;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Pet Pedia");
        setContentView(R.layout.activity_pet_pedia);
        lista = (ListView) findViewById(R.id.lista);
        cargarRazas();

    }
    private class DescargarImagenes extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DescargarImagenes(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }
    }

    public void cargarRazas() {
        try {
            conexionWeb = new ConexionWeb(pet_pedia.this);
            URL direcciopn = new URL("http://carolina.x10host.com/index.php/Sistema/razas_datos_android");
            conexionWeb.execute(direcciopn);
        } catch (MalformedURLException e) {
            Toast.makeText(pet_pedia.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private ArrayList<raza> getElemento() {
        elementos = new ArrayList<raza>();
        return elementos;
    }
    @Override
    public void procesarRespuesta(String r) {
        elemento = getElemento();
        try {
            arrayjson = new JSONArray(r);
            for (int i = 0; i < arrayjson.length(); i++) {

                elementos.add(new raza(Integer.parseInt(arrayjson.getJSONObject(i).getString("idrazamascota")), arrayjson.getJSONObject(i).getString("nombre_raza"), arrayjson.getJSONObject(i).getString("caracter"), arrayjson.getJSONObject(i).getString("salud"), arrayjson.getJSONObject(i).getString("caracteristicas"), arrayjson.getJSONObject(i).getString("utilidad"),arrayjson.getJSONObject(i).getString("foto_raza")));
            }
            adater = new razaAdaptador(this, elementos, new razaAdaptador.botonClick() {
                @Override
                public void onBtnClick(int position) {
                    Intent acde = new Intent(pet_pedia.this,Pet_pedia_info.class);
                    acde.putExtra("idraza", elementos.get(position).getidrazamascota());
                    acde.putExtra("caracter", elementos.get(position).getcaracter());
                    acde.putExtra("salud", elementos.get(position).getsalud());
                    acde.putExtra("caracteristicas", elementos.get(position).getcaracteristicas());
                    acde.putExtra("utilidad", elementos.get(position).getutilidad());
                    acde.putExtra("foto", elementos.get(position).getfotor());
                    startActivity(acde);
                }
            });
            lista.setAdapter(adater);
        } catch (JSONException e) {

        }
    }
}


