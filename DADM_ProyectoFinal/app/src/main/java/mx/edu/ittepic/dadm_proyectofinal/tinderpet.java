package mx.edu.ittepic.dadm_proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.hlab.animatedPullToRefresh.AnimatedPullToRefreshLayout;
import com.rahuljanagouda.statusstories.StatusStoriesActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class tinderpet extends AppCompatActivity implements AsyncResponse, AnimatedPullToRefreshLayout.OnRefreshListener {
    tinderAdaptador adater;
    ArrayList<mascotatinder> elemento;
    ArrayList<mascotatinder> elementos;
    ListView Menu_lista;
    ConexionWeb conexionWeb;
    String idusuarios = "";
    String imagenesMostrar[];
    boolean corazon[], dislike[];
    AnimatedPullToRefreshLayout mPullToRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinderpet);
        setTitle("Tinder Pet");
        SharedPreferences prefs = getSharedPreferences("INFO_USUARIO", Context.MODE_PRIVATE);
        idusuarios = prefs.getString("idusuarios", "0");
        Menu_lista = (ListView) findViewById(R.id.tinderlista);

        cargarMascotas();
        cargarCorazone();

        mPullToRefreshLayout = (AnimatedPullToRefreshLayout) findViewById(R.id.pullToRefreshLayout);
        mPullToRefreshLayout.setColorAnimationArray(new int[]{Color.CYAN, Color.RED, Color.YELLOW, Color.MAGENTA});
        mPullToRefreshLayout.setOnRefreshListener(this);
    }

    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.tindermenu, m);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem mi) {
        switch (mi.getItemId()) {
            case R.id.pet:
                Intent a = new Intent(tinderpet.this, StatusStoriesActivity.class);
                a.putExtra(StatusStoriesActivity.STATUS_RESOURCES_KEY, imagenesMostrar);
                a.putExtra(StatusStoriesActivity.STATUS_DURATION_KEY, 3000L);
                a.putExtra(StatusStoriesActivity.IS_IMMERSIVE_KEY, true);
                a.putExtra(StatusStoriesActivity.IS_CACHING_ENABLED_KEY, true);
                a.putExtra(StatusStoriesActivity.IS_TEXT_PROGRESS_ENABLED_KEY, true);
                startActivity(a);
                break;
        }
        return true;
    }

    private ArrayList<mascotatinder> getElemento() {
        elementos = new ArrayList<mascotatinder>();
        return elementos;
    }

    public void cargarCorazone() {
        try {
            conexionWeb = new ConexionWeb(tinderpet.this);
            conexionWeb.agregarVariables("idusuarios", idusuarios);
            URL direcciopn = new URL("http://caropetworld.xyz/index.php/Sistema/obtenerCorazonesGlobal");
            conexionWeb.execute(direcciopn);
        } catch (MalformedURLException e) {
            Toast.makeText(tinderpet.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void cargarMascotas() {
        try {
            conexionWeb = new ConexionWeb(tinderpet.this);
            conexionWeb.agregarVariables("idusuarios", idusuarios);
            URL direcciopn = new URL("http://caropetworld.xyz/index.php/Sistema/tinder_pet");
            conexionWeb.execute(direcciopn);
        } catch (MalformedURLException e) {
            Toast.makeText(tinderpet.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void procesarRespuesta(String r) {
        if (r.equals("no_mascotas")) {
            Toast.makeText(tinderpet.this, "Aun no hay mascotas para hacer matchs", Toast.LENGTH_LONG).show();
        } else {
            if (r.equals("CORA") || r.equals("Like")) {
                System.out.println("PETICION!!!");
            } else {

                try {
                    if (elemento == null) {
                        elemento = getElemento();
                    }
                    JSONArray test = new JSONArray(r);
                    if (test.getJSONObject(0).has("idmatch")) {
                        for (int i = 0; i < test.length(); i++) {
                            for (int e = 0; e < corazon.length(); e++) {

                            }
                        }
                    } else {
                        if (test.getJSONObject(0).has("iddislike")) {

                        } else {
                            JSONArray arrayjson = new JSONArray(r);
                            imagenesMostrar = new String[arrayjson.length()];
                            corazon = new boolean[arrayjson.length()];
                            dislike = new boolean[arrayjson.length()];
                            for (int i = 0; i < arrayjson.length(); i++) {
                                imagenesMostrar[i] = arrayjson.getJSONObject(i).getString("foto_mas");
                                corazon[i] = true;
                                dislike[i] = true;
                                elementos.add(new mascotatinder(Integer.parseInt(arrayjson.getJSONObject(i).getString("idmascota")),
                                        arrayjson.getJSONObject(i).getString("nombre"), arrayjson.getJSONObject(i).getString("edad"),
                                        arrayjson.getJSONObject(i).getString("sexo"), arrayjson.getJSONObject(i).getString("razamascota_idrazamascota"),
                                        arrayjson.getJSONObject(i).getString("tipo_mascota_idtipo_mascota"),
                                        arrayjson.getJSONObject(i).getString("foto_mas"),
                                        arrayjson.getJSONObject(i).getString("megusta"), arrayjson.getJSONObject(i).getString("nomegusta"),
                                        getResources().getDrawable(R.drawable.ic_heart_off), getResources().getDrawable(R.drawable.ic_thumb_black),
                                        Integer.parseInt(arrayjson.getJSONObject(i).getString("idusuarios")),
                                        (arrayjson.getJSONObject(i).getString("nombreu")), (arrayjson.getJSONObject(i).getString("perfil_foto")),
                                        arrayjson.getJSONObject(i).getString("municipio") + "," + arrayjson.getJSONObject(i).getString("estado")));
                            }
                            adater = new tinderAdaptador(this, elemento, new tinderAdaptador.botonClick() {
                                @Override
                                public void onBtnClick(int position) {
                                    if (corazon[position]) {
                                        int valor = Integer.parseInt(elemento.get(position).getCoraz());
                                        elemento.get(position).setCoraz("" + (valor + 1));
                                        elemento.get(position).ponerImagenCora(getResources().getDrawable(R.drawable.ic_heart_on));
                                        corazon[position] = false;
                                        try {
                                            conexionWeb = new ConexionWeb(tinderpet.this);
                                            conexionWeb.agregarVariables("idmascota", "" + elemento.get(position).getidmascota());
                                            conexionWeb.agregarVariables("idusuarios", "" + idusuarios);
                                            URL direcciopn = new URL("http://caropetworld.xyz/index.php/Sistema/CorazonUp");
                                            conexionWeb.execute(direcciopn);
                                        } catch (MalformedURLException e) {
                                            Toast.makeText(tinderpet.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                        try {
                                            adater.notifyDataSetChanged();
                                        } catch (Exception e) {
                                        }
                                        System.out.println("MATCH" + position);
                                    } else {
                                        System.out.println("Des MATCH" + position);
                                        int valor = Integer.parseInt(elemento.get(position).getCoraz());
                                        if (valor > 0) {
                                            //  adater.ponerConrazon("" + (valor - 1));
                                            elemento.get(position).setCoraz("" + (valor - 1));
                                            elemento.get(position).ponerImagenCora(getResources().getDrawable(R.drawable.ic_heart_off));
                                            corazon[position] = true;
                                            try {
                                                conexionWeb = new ConexionWeb(tinderpet.this);
                                                conexionWeb.agregarVariables("idusuarios", "" + idusuarios);
                                                conexionWeb.agregarVariables("idmascota", "" + elemento.get(position).getidmascota());
                                                URL direcciopn = new URL("http://caropetworld.xyz/index.php/Sistema/CorazonDown");
                                                conexionWeb.execute(direcciopn);
                                            } catch (MalformedURLException e) {
                                                Toast.makeText(tinderpet.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                            try {
                                                adater.notifyDataSetChanged();
                                            } catch (Exception e) {
                                            }
                                            System.out.println("MATCH" + position);
                                        }
                                    }

                                }
                            }, new tinderAdaptador.botonClick() {
                                @Override
                                public void onBtnClick(int position) {
                                    if (dislike[position]) {
                                        try {
                                            int valor = Integer.parseInt(elemento.get(position).getLike());
                                            elemento.get(position).ponerImagenLike(getResources().getDrawable(R.drawable.ic_thumb));
                                            elemento.get(position).setLike("" + (valor + 1));
                                            try {
                                                adater.notifyDataSetChanged();
                                            } catch (Exception e) {

                                            }
                                            System.out.println("MATCH" + position);
                                            dislike[position] = false;
                                            conexionWeb = new ConexionWeb(tinderpet.this);
                                            conexionWeb.agregarVariables("idmascota", "" + elemento.get(position).getidmascota());
                                            conexionWeb.agregarVariables("idusuarios", "" + idusuarios);
                                            URL direcciopn = new URL("http://caropetworld.xyz/index.php/Sistema/likeUp");
                                            conexionWeb.execute(direcciopn);
                                        } catch (MalformedURLException e) {
                                            Toast.makeText(tinderpet.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        try {
                                            int valor = Integer.parseInt(elemento.get(position).getLike());
                                            if (valor > 0) {
                                                elemento.get(position).setLike("" + (valor - 1));
                                                elemento.get(position).ponerImagenLike(getResources().getDrawable(R.drawable.ic_thumb_black));
                                                try {
                                                    adater.notifyDataSetChanged();
                                                } catch (Exception e) {

                                                }
                                                System.out.println("MATCH" + position);
                                                dislike[position] = true;
                                                conexionWeb = new ConexionWeb(tinderpet.this);
                                                conexionWeb.agregarVariables("idmascota", "" + elemento.get(position).getidmascota());
                                                conexionWeb.agregarVariables("idusuarios", "" + idusuarios);
                                                URL direcciopn = new URL("http://caropetworld.xyz/index.php/Sistema/likeUp");
                                                conexionWeb.execute(direcciopn);
                                            }
                                        } catch (MalformedURLException e) {
                                            Toast.makeText(tinderpet.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            }, new tinderAdaptador.botonClick() {
                                @Override
                                public void onBtnClick(int position) {
                                    Intent intento = new Intent(tinderpet.this, Perfil_tinder_pet.class);
                                    intento.putExtra("idusuarios", elemento.get(position).getIdusuario());
                                    startActivity(intento);
                                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                }
                            });
                            Menu_lista.setAdapter(adater);
                        }
                    }
                } catch (JSONException e) {
                }
            }
        }
    }

    @Override
    public void onRefresh() {
        try {
            elementos.clear();
            cargarMascotas();
            mPullToRefreshLayout.refreshComplete();
        } catch (Exception e) {

        }
    }
}
