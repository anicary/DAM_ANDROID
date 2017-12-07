package mx.edu.ittepic.dadm_proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.InputStream;

public class Perfil extends AppCompatActivity {
    String nombrea="",apellidosa="",correoa="",imagen="",idusuarios="";
    TextView nombre,apellido,correo;
    BDInterna dbinterna;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Tu Perfil");
        setContentView(R.layout.activity_perfil);

        nombre=(TextView)findViewById(R.id.vnombreperfil);
        apellido=(TextView)findViewById(R.id.vapellidoperfil);
        correo=(TextView)findViewById(R.id.vcorreoperfil);

        SharedPreferences prefs =
                getSharedPreferences("INFO_USUARIO", Context.MODE_PRIVATE);

        nombrea = prefs.getString("nombre", "Nombre");
        apellidosa = prefs.getString("apellidos", "apellidos");
        correoa = prefs.getString("correo", "correo@email.com");
        imagen = prefs.getString("imagen", "http://caropetworld.xyz/archivos/fotos/perfil.jpg");
        idusuarios = prefs.getString("idusuarios", "0");
        dbinterna = new BDInterna(Perfil.this, "baseinterna", null, 1);
        nombre.setText(nombrea);
        apellido.setText(apellidosa);
        correo.setText(correoa);
        ImageView imageView=(ImageView)findViewById(R.id.imgPer) ;
        Picasso.with(Perfil.this).load(imagen).into(imageView);
    }
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.menu,m);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem mi){
        switch (mi.getItemId()){
            case R.id.editar:
                Intent editarperfil = new Intent(Perfil.this,editarPerfilusuario.class);
                startActivity(editarperfil);

                break;
            case R.id.cerrarse:
                try {
                    SQLiteDatabase base = dbinterna.getWritableDatabase();
                    String query1 = "DELETE FROM usuario  where idusuarios=" + idusuarios + ";";
                    base.execSQL(query1);
                    Intent intent = new Intent(Perfil.this, Inicio.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } catch (SQLException e) {
                    Toast.makeText(Perfil.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

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
