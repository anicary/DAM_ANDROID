package mx.edu.ittepic.dadm_proyectofinal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Pet_pedia_info extends AppCompatActivity implements AsyncResponse{
    TextView cara, sal, caracte, util;
    ImageView fo;
    String caracter="";
    String salud="";
    String caracteristicas="";
    String utlidad ="";
    String foto="" ;
    ConexionWeb conexionWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Pet Pedia");
        setContentView(R.layout.activity_pet_pedia_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getIntent().getExtras().getBoolean("cargar")){
            cargarDatos(getIntent().getExtras().getInt("razaid"));
        }else
        {
            setTitle(""+getIntent().getExtras().getString("nombre_raza"));
            caracter = getIntent().getExtras().getString("caracter");
            salud = getIntent().getExtras().getString("salud");
            caracteristicas = getIntent().getExtras().getString("caracteristicas");
            utlidad = getIntent().getExtras().getString("utilidad");
            foto = getIntent().getExtras().getString("foto");
        }

        cara = (TextView) findViewById(R.id.caracter);
        sal = (TextView) findViewById(R.id.salud);
        caracte = (TextView) findViewById(R.id.caracteristicas);
        util = (TextView) findViewById(R.id.utilidad);
        fo = (ImageView) findViewById(R.id.foto_info);
        cara.setText(caracter);
        sal.setText(salud);
        caracte.setText(caracteristicas);
        util.setText(utlidad);
        Picasso.with(Pet_pedia_info.this).load(foto).into(fo);
    /*    new Pet_pedia_info.DescargarImagenes((ImageView) findViewById(R.id.foto))
                .execute("" + foto); */


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Interesante ¿no lo crees?", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
    public void cargarDatos(int ia){
        try {
            conexionWeb = new ConexionWeb(Pet_pedia_info.this);
            conexionWeb.agregarVariables("idraza",""+ ia);
            URL direcciopn = new URL("http://carolina.x10host.com/index.php/Sistema/razas_datos_android_id");
            conexionWeb.execute(direcciopn);
        } catch (MalformedURLException e) {
            Toast.makeText(Pet_pedia_info.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void procesarRespuesta(String r) {
        if(r.equals("")){
            Toast.makeText(Pet_pedia_info.this,"Ocurrio un error", Toast.LENGTH_LONG).show();
        }else
        {
            try{
                JSONArray arrayjson = new JSONArray(r);
                for(int i = 0; i < arrayjson.length(); i++){

                    caracter = arrayjson.getJSONObject(i).getString("caracter");
                    salud = arrayjson.getJSONObject(i).getString("salud");
                    caracteristicas = arrayjson.getJSONObject(i).getString("caracteristicas");
                    utlidad = arrayjson.getJSONObject(i).getString("utilidad");
                    foto =  arrayjson.getJSONObject(i).getString("foto");
                    setTitle(""+ arrayjson.getJSONObject(i).getString("nombre_raza"));

                }
            }catch (JSONException e){

            }
        }
    }
}
