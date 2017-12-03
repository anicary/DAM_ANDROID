package mx.edu.ittepic.dadm_proyectofinal;

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
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener,AsyncResponse {
    String nombre="",apellidos="",correo="",imagen="",idusuarios="";
    MascotaAdaptador adater;
    BDInterna dbinterna;
    ArrayList<mascota> elemento;
    ArrayList<mascota> elementos;
    ListView Menu_lista;
    ConexionWeb conexionWeb;
    JSONArray arrayjson;
    private Animator mCurrentAnimator;

    private int mShortAnimationDuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      //  SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

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
            URL direcciopn = new URL("http://carolina.x10host.com/index.php/Sistema/cargarMascotas");
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
            Toast.makeText(MainActivity.this,"Aun no tienes mascotas", Toast.LENGTH_LONG).show();
        }else
        {
            try{
                 arrayjson = new JSONArray(r);
                for(int i = 0; i < arrayjson.length(); i++){
                    idtemp=arrayjson.getJSONObject(i).getString("idmascota");
                    elementos.add(new mascota(Integer.parseInt(arrayjson.getJSONObject(i).getString("idmascota")),arrayjson.getJSONObject(i).getString("nombre"),arrayjson.getJSONObject(i).getString("edad"),arrayjson.getJSONObject(i).getString("sexo"),arrayjson.getJSONObject(i).getString("razamascota_idrazamascota"),arrayjson.getJSONObject(i).getString("foto_mas")));
                }
                adater= new MascotaAdaptador(this, elementos, new MascotaAdaptador.botonClick() {
                    @Override
                    public void onBtnClick(int position) {
                        Intent acde = new Intent(MainActivity.this, editarMascota.class);
                        acde.putExtra("idmascota", elementos.get(position).getidmascota());
                        acde.putExtra("nombre", elementos.get(position).getnombre());
                        acde.putExtra("foto", elementos.get(position).getfoto());
                        acde.putExtra("edad", elementos.get(position).getedad());
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
    private void zoomImageFromThumb(final View thumbView, Drawable imageResId) {

        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }
        final ImageView expandedImageView = (ImageView) findViewById(
                R.id.expanded_image);
      //  expandedImageView.setImageResource(imageResId);
        expandedImageView.setImageDrawable(imageResId);
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.contenedor)
                .getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f)).with(ObjectAnimator.ofFloat(expandedImageView,
                View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y,startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }
}
