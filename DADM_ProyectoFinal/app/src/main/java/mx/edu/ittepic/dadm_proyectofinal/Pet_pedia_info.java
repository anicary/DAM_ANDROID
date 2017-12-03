package mx.edu.ittepic.dadm_proyectofinal;

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

import com.squareup.picasso.Picasso;

import java.io.InputStream;

public class Pet_pedia_info extends AppCompatActivity {
    TextView cara, sal, caracte, util;
    ImageView fo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Pet Pedia");
        setContentView(R.layout.activity_pet_pedia_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String caracter = getIntent().getExtras().getString("caracter");
        String salud = getIntent().getExtras().getString("salud");
        String caracteristicas = getIntent().getExtras().getString("caracteristicas");
        String utlidad = getIntent().getExtras().getString("utilidad");
        String foto = getIntent().getExtras().getString("foto");

        cara = (TextView) findViewById(R.id.caracter);
        sal = (TextView) findViewById(R.id.salud);
        caracte = (TextView) findViewById(R.id.caracteristicas);
        util = (TextView) findViewById(R.id.utilidad);
        fo = (ImageView) findViewById(R.id.foto_info);
        cara.setText("TEMPERAMENTO\n"+caracter);
        sal.setText("SALUD/CUIDADOS\n"+salud);
        caracte.setText("CARACTERISTICAS\n"+caracteristicas);
        util.setText("UTILIDAD\n"+utlidad);
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
}
