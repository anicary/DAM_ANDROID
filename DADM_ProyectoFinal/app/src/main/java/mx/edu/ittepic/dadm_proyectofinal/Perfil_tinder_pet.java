package mx.edu.ittepic.dadm_proyectofinal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class Perfil_tinder_pet extends AppCompatActivity implements AsyncResponse {
    ImageView fotoperfil;
    ConexionWeb conexionWeb;
    TextView nombre,correo,ciudad;
    String titulo="";
    String ncidudad,nestado;
    ListView Menu_lista;
    ArrayList<mascota> elemento;
    ArrayList<mascota> elementos;
    MascotaAdaptador2 adater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_tinder_pet);
        fotoperfil=(ImageView) findViewById(R.id.imgPTU);
        nombre=(TextView) findViewById(R.id.vnombreperfiltinderu);
        correo=(TextView) findViewById(R.id.vcoreoperfiltinderu);
        ciudad=(TextView) findViewById(R.id.vestadoperfiltinderu);

        Menu_lista = (ListView) findViewById(R.id.listadogexterna);
        cargarUsuario(""+getIntent().getExtras().getInt("idusuarios"));
        cargarMascotas(""+getIntent().getExtras().getInt("idusuarios"));
        setTitle(titulo);
        ciudad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="";
                try
                {
                    query = URLEncoder.encode(""+ncidudad+","+nestado, "utf-8");
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,   Uri.parse("https://www.google.com/maps/search/?api=1&query="+query));
                    startActivity(intent);
                }catch (UnsupportedEncodingException e){

                }

            }
        });
    }
    @Override
    public void procesarRespuesta(String r){
            try{
                JSONArray arrayjson = new JSONArray(r);
                if (arrayjson.getJSONObject(0).has("apellidos")) {
                    titulo=""+arrayjson.getJSONObject(0).getString("nombre")+" "+arrayjson.getJSONObject(0).getString("apellidos");
                    setTitle(titulo);
                    nombre.setText(arrayjson.getJSONObject(0).getString("nombre")+" "+arrayjson.getJSONObject(0).getString("apellidos"));
                    correo.setText(arrayjson.getJSONObject(0).getString("correo"));
                    ciudad.setText(arrayjson.getJSONObject(0).getString("municipio")+","+arrayjson.getJSONObject(0).getString("estado"));
                    ncidudad=""+arrayjson.getJSONObject(0).getString("municipio");
                    nestado=""+arrayjson.getJSONObject(0).getString("estado");
                    Picasso.with(Perfil_tinder_pet.this).load(arrayjson.getJSONObject(0).getString("perfil_foto")).into(fotoperfil);
                }
                else
                {
                    elemento = getElemento();
                    Menu_lista.setVisibility(View.VISIBLE);
                    for(int i = 0; i < arrayjson.length(); i++){
                        elementos.add(new mascota(Integer.parseInt(arrayjson.getJSONObject(i).getString("idmascota")),arrayjson.getJSONObject(i).getString("nombre"),arrayjson.getJSONObject(i).getString("edad"),arrayjson.getJSONObject(i).getString("sexo"),arrayjson.getJSONObject(i).getString("razamascota_idrazamascota"),arrayjson.getJSONObject(i).getString("tipo_mascota_idtipo_mascota"),arrayjson.getJSONObject(i).getString("foto_mas")));
                    }
                    adater= new MascotaAdaptador2(this, elementos, new MascotaAdaptador2.botonClick() {
                        @Override
                        public void onBtnClick(int position) {
                            Intent acde = new Intent(Perfil_tinder_pet.this, editarMascota.class);
                            acde.putExtra("idmascota", elementos.get(position).getidmascota());
                            acde.putExtra("nombre", elementos.get(position).getnombre());
                            acde.putExtra("foto", elementos.get(position).getfoto());
                            acde.putExtra("edad", elementos.get(position).getedad());
                            acde.putExtra("tipo", elementos.get(position).gettipo());
                            acde.putExtra("raza", elementos.get(position).getraza());
                            startActivity(acde);
                        }
                    }, new MascotaAdaptador2.botonClick() {
                        @Override
                        public void onBtnClick(int position) {
                            Intent acde = new Intent(Perfil_tinder_pet.this, Pet_pedia_info.class);
                            acde.putExtra("cargar", true);
                            acde.putExtra("razaid", elementos.get(position).getraza());
                            startActivity(acde);
                        }
                    });
                    Menu_lista.setAdapter(adater);
                }
            }catch (JSONException e){
            }
    }
    public void cargarUsuario(String idusuarios){
        try {
            conexionWeb = new ConexionWeb(Perfil_tinder_pet.this);
            conexionWeb.agregarVariables("idusuarios", idusuarios);
            URL direcciopn = new URL("http://caropetworld.xyz/index.php/Sistema/perfil_usuario_tinder");
            conexionWeb.execute(direcciopn);
        } catch (MalformedURLException e) {
            Toast.makeText(Perfil_tinder_pet.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void cargarMascotas(String idusuarios) {
        try {
            conexionWeb = new ConexionWeb(Perfil_tinder_pet.this);
            conexionWeb.agregarVariables("idusuarios", idusuarios);
            URL direcciopn = new URL("http://caropetworld.xyz/index.php/Sistema/cargarMascotas");
            conexionWeb.execute(direcciopn);
        } catch (MalformedURLException e) {
            Toast.makeText(Perfil_tinder_pet.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private ArrayList<mascota> getElemento() {
        elementos = new ArrayList<mascota>();
        return elementos;
    }
}
