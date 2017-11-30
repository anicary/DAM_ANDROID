package mx.edu.ittepic.dadm_proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class Perfil extends AppCompatActivity {
    String nombrea="",apellidosa="",correoa="",imagen="";
    TextView nombre,apellido,correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        nombre=(TextView)findViewById(R.id.vnombreperfil);
        apellido=(TextView)findViewById(R.id.vapellidoperfil);
        correo=(TextView)findViewById(R.id.vcorreoperfil);

        SharedPreferences prefs =
                getSharedPreferences("INFO_USUARIO", Context.MODE_PRIVATE);

        nombrea = prefs.getString("nombre", "Nombre");
        apellidosa = prefs.getString("apellidos", "apellidos");
        correoa = prefs.getString("correo", "correo@email.com");
        imagen = prefs.getString("imagen", "http://carolina.x10host.com/archivos/fotos/perfil.jpg");
        nombre.setText(nombrea);
        apellido.setText(apellidosa);
        correo.setText(correoa);
        new Perfil.DescargarImagenes((ImageView) findViewById(R.id.imgPer))
                .execute(""+imagen);
    }
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.menu,m);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem mi){
        switch (mi.getItemId()){
            case R.id.editar:

                break;
            case R.id.cerrarse:

                break;
        }
        return true;
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

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
