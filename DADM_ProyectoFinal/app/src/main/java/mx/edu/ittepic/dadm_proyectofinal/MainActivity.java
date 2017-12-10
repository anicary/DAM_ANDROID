package mx.edu.ittepic.dadm_proyectofinal;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

import static android.R.attr.typeface;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener,AsyncResponse {
    String nombre="",apellidos="",correo="",imagen="",idusuarios="";
    MascotaAdaptador adater;
    BDInterna dbinterna;
    ArrayList<mascota> elemento;
    ArrayList<mascota> elementos;
    ListView Menu_lista;
    ConexionWeb conexionWeb;
    JSONArray arrayjson;
    ConstraintLayout conperroyout;
    private Animator mCurrentAnimator;

    private int mShortAnimationDuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conperroyout =(ConstraintLayout)findViewById(R.id.constrainperro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences prefs =
                getSharedPreferences("INFO_USUARIO", Context.MODE_PRIVATE);

         nombre = prefs.getString("nombre", "Nombre");
         apellidos = prefs.getString("apellidos", "apellidos");
        correo = prefs.getString("correo", "correo@email.com");
        imagen = prefs.getString("imagen", "http://carolina.x10host.com/archivos/fotos/perfil.jpg");
        idusuarios = prefs.getString("idusuarios", "0");
        dbinterna = new BDInterna(MainActivity.this, "baseinterna", null, 1);
        setTitle("Bienvenida "+nombre);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Ventanaregistro = new Intent(MainActivity.this, agregar_mascota.class);
                startActivity(Ventanaregistro);
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View v = navigationView.getHeaderView(0);
        TextView nombrenav = (TextView) v.findViewById(R.id.nombrenav);
        nombrenav.setText(""+nombre+" "+apellidos);
       /* new DescargarImagenes((ImageView) v.findViewById(R.id.navimagenperfil))
                .execute(""+imagen);*/
        ImageView imagenperfil=(ImageView) v.findViewById(R.id.navimagenperfil) ;
        Picasso.with(MainActivity.this).load(imagen).into(imagenperfil);


        View v2 = navigationView.getHeaderView(0);
        TextView navcorreo = (TextView) v2.findViewById(R.id.navcorreo);
        navcorreo.setText(""+correo);
        cargarMascotas();
        Menu_lista = (ListView) findViewById(R.id.lista_mascotas);
        ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE , Manifest.permission.READ_EXTERNAL_STORAGE }, 1);
    }
    private ArrayList<mascota> getElemento() {
        elementos = new ArrayList<mascota>();
        return elementos;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.curiosidades) {
            Intent curiosidad = new Intent(MainActivity.this,curiosidades.class);
            startActivity(curiosidad);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_perfil) {
            Intent perfil = new Intent(MainActivity.this, Perfil.class);
            startActivity(perfil);
        } else if (id == R.id.nav_de) {
            Intent acde = new Intent(MainActivity.this, acerca.class);
            startActivity(acde);

        }
        else if (id == R.id.nav_tinder) {
            Intent acde = new Intent(MainActivity.this, tinderpet.class);
            startActivity(acde);

        }
        else if (id == R.id.nav_petpedia) {
            Intent acde = new Intent(MainActivity.this, pet_pedia.class);
            startActivity(acde);
        }
        else if (id == R.id.nav_sesion) {
            try {
                SQLiteDatabase base = dbinterna.getWritableDatabase();
                String query1 = "DELETE FROM usuario  where idusuarios=" + idusuarios + ";";
                base.execSQL(query1);
                Intent intent = new Intent(MainActivity.this, Inicio.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } catch (SQLException e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else if(id ==R.id.nav_shake){
            Intent shake = new Intent(MainActivity.this, desestres.class);
            startActivity(shake);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
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
    public void cargarMascotas() {
        try {
            conexionWeb = new ConexionWeb(MainActivity.this);
            conexionWeb.agregarVariables("idusuarios", idusuarios);
            URL direcciopn = new URL("http://caropetworld.xyz/index.php/Sistema/cargarMascotas");
            conexionWeb.execute(direcciopn);
        } catch (MalformedURLException e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    String idtemp="";
    @Override
    public void procesarRespuesta(String r) {

        elemento = getElemento();
        if(r.equals("no-mascotas")){
            Toasty.Config.getInstance().apply();
            Drawable icono = getResources().getDrawable(R.drawable.ic_pets_error_blanco);
            Toasty.normal(MainActivity.this, "Aun no tienes mascotas",icono).show();
            // Toast.makeText(MainActivity.this,"Aun no tienes mascotas", Toast.LENGTH_LONG).show();
        }else
        {
            try{
                 arrayjson = new JSONArray(r);
                conperroyout.setVisibility(View.GONE);
                Menu_lista.setVisibility(View.VISIBLE);
                for(int i = 0; i < arrayjson.length(); i++){
                    idtemp=arrayjson.getJSONObject(i).getString("idmascota");
                    elementos.add(new mascota(Integer.parseInt(arrayjson.getJSONObject(i).getString("idmascota")),arrayjson.getJSONObject(i).getString("nombre"),arrayjson.getJSONObject(i).getString("edad"),arrayjson.getJSONObject(i).getString("sexo"),arrayjson.getJSONObject(i).getString("razamascota_idrazamascota"),arrayjson.getJSONObject(i).getString("tipo_mascota_idtipo_mascota"),arrayjson.getJSONObject(i).getString("foto_mas")));
                }
                adater= new MascotaAdaptador(this, elementos, new MascotaAdaptador.botonClick() {
                    @Override
                    public void onBtnClick(int position) {
                        Intent acde = new Intent(MainActivity.this, editarMascota.class);
                        acde.putExtra("idmascota", elementos.get(position).getidmascota());
                        acde.putExtra("nombre", elementos.get(position).getnombre());
                        acde.putExtra("foto", elementos.get(position).getfoto());
                        acde.putExtra("edad", elementos.get(position).getedad());
                        acde.putExtra("tipo", elementos.get(position).gettipo());
                        acde.putExtra("raza", elementos.get(position).getraza());
                        startActivity(acde);
                    }
                }, new MascotaAdaptador.botonClick() {
                    @Override
                    public void onBtnClick(int position) {
                        Intent acde = new Intent(MainActivity.this, Pet_pedia_info.class);
                        acde.putExtra("cargar", true);
                        acde.putExtra("razaid", elementos.get(position).getraza());
                        startActivity(acde);
                    }
                });
                Menu_lista.setAdapter(adater);
            }catch (JSONException e){

            }
        }
    }

}
