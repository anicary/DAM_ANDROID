package mx.edu.ittepic.dadm_proyectofinal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class editarMascota extends AppCompatActivity  implements AsyncResponse{
    String nombrea="",edada="",sexoa="";
    String idusuarios="";
    EditText enombre,eedad;
    Spinner etipo, eraza, esexo;
    Button editarpet;
    ConexionWeb conexionWeb;
    int id=0;
    String idtipos[],idraza[];
    String idrazaencontrada="",idtipoencontrado;
    boolean tc=true,rc=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Editar Mascota");
        setContentView(R.layout.activity_editar_mascota);
        SharedPreferences prefs =
                getSharedPreferences("INFO_USUARIO", Context.MODE_PRIVATE);
        idusuarios = prefs.getString("idusuarios", "0");


        enombre = (EditText) findViewById(R.id.enombremascota);
        eedad = (EditText) findViewById(R.id.eedadmascota);
        editarpet = (Button)findViewById(R.id.editarmascota);
        esexo = (Spinner) findViewById(R.id.esexomascota);
        etipo = (Spinner) findViewById(R.id.etipomascota);
        eraza = (Spinner) findViewById(R.id.erazamascota);
        Intent intento = getIntent();

        id=getIntent().getExtras().getInt("idmascota");
        enombre.setText(getIntent().getExtras().getString("nombre"));
        eedad.setText(getIntent().getExtras().getString("edad"));
        idrazaencontrada=getIntent().getExtras().getString("raza");
        idtipoencontrado=getIntent().getExtras().getString("tipo");
        cargarTipos();
        cargarRazas(idrazaencontrada);

        if(getIntent().getExtras().getString("edad").equals("HEMBRA")){
            esexo.setSelection(0);
        }else{
            esexo.setSelection(1);
        }
        new editarMascota.DescargarImagenes((ImageView) findViewById(R.id.efotomascota1))
                .execute(""+getIntent().getExtras().getString("foto"));
        editarpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombrea = enombre.getText().toString();
                edada = eedad.getText().toString();
                sexoa = esexo.getSelectedItem().toString();
                if (!nombrea.equals("") || !edada.equals("") || !sexoa.equals("")) {
/*                    try {
                        conexionWeb = new ConexionWeb(editarMascota.this);
                        conexionWeb.agregarVariables("idmascota", getIntent().getExtras().getString("idmascota"));
                        conexionWeb.agregarVariables("nombre", nombrea);
                        conexionWeb.agregarVariables("edad",edada);
                        conexionWeb.agregarVariables("sexo", sexoa);
                        conexionWeb.agregarVariables("tipo_mascota_idtipo_mascota", idtipos[etipo.getSelectedItemPosition()]);
                        conexionWeb.agregarVariables("razamascota_idrazamascota",  idraza[eraza.getSelectedItemPosition()]);
                        conexionWeb.agregarVariables("idusuarios", idusuarios);
                        URL direccion = new URL("http://carolina.x10host.com/index.php/Sistema/editar_mascota");
                        conexionWeb.execute(direccion);
                    } catch (MalformedURLException e) {
                        Toast.makeText(editarMascota.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }*/
                }
            }
        });
        etipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                cargarRazas(idtipos[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

    }
    public void procesarRespuesta(String r) {
        if(r.equals("actualizado")){
            Toast.makeText(editarMascota.this,"CAMBIOS GUARDADOS",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(editarMascota.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }else
        {
            if (r.equals("eliminado")){
                Intent intent = new Intent(editarMascota.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }else
            {
                try{
                    JSONArray arrayjson = new JSONArray(r);
                    if (arrayjson.getJSONObject(0).has("idtipo_mascota")) {
                        List<String> spinnerArray =  new ArrayList<String>();
                        idtipos = new String[arrayjson.length()];
                        for (int i = 0; i < arrayjson.length(); i++) {
                            spinnerArray.add(arrayjson.getJSONObject(i).getString("nombre"));
                            idtipos[i]=arrayjson.getJSONObject(i).getString("idtipo_mascota");
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                this, android.R.layout.simple_spinner_item, spinnerArray);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        etipo.setAdapter(adapter);
                        if(tc){
                            for(int i=0;i<idtipos.length;i++){
                                if(idtipos[i].equals(idtipoencontrado)){
                                    etipo.setSelection(i);
                                }
                            }
                        }else
                        {
                            tc=false;
                        }
                     //   cargarRazas(idtipos[0]);
                    }else
                    {
                        JSONArray arrayjson2 = new JSONArray(r);
                        if (arrayjson2.getJSONObject(0).has("idrazamascota")) {
                            List<String> spinnerArray =  new ArrayList<String>();
                            idraza= new String[arrayjson2.length()];
                            for (int i = 0; i < arrayjson2.length(); i++) {
                                spinnerArray.add(arrayjson2.getJSONObject(i).getString("nombre_raza"));
                                idraza[i]=arrayjson.getJSONObject(i).getString("idrazamascota");
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                    this, android.R.layout.simple_spinner_item, spinnerArray);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            eraza.setAdapter(adapter);
                            if(rc){
                                for(int i=0;i<idraza.length;i++){
                                    if(idraza[i].equals(idrazaencontrada)){
                                        eraza.setSelection(i);
                                    }
                                }
                            }else
                            {
                                rc=false;
                            }

                        }
                    }
                }catch (JSONException e){
                }
            }
        }

    }
    public void cargarMascotaId(){
        try {
            conexionWeb = new ConexionWeb(editarMascota.this);
            conexionWeb.agregarVariables("idmascota", idusuarios);
            URL direcciopn = new URL("http://carolina.x10host.com/index.php/Sistema/obtenerMascotaId");
            conexionWeb.execute(direcciopn);
        } catch (MalformedURLException e) {
            Toast.makeText(editarMascota.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.eliminar,m);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem mi){
        switch (mi.getItemId()){
            case R.id.eliminarpet:
                AlertDialog.Builder alerta = new AlertDialog.Builder(this);
                alerta.setTitle("ATENCION")
                        .setMessage("¿ESTAS SEGURO DE ELIMINAR ESTA MASCOTA?")
                        .setIcon(R.drawable.ic_error_black_24dp)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    conexionWeb = new ConexionWeb(editarMascota.this);
                                    conexionWeb.agregarVariables("idmascota", ""+id);
                                    conexionWeb.agregarVariables("idusuarios", idusuarios);
                                    URL direccion = new URL("http://carolina.x10host.com/index.php/Sistema/eliminar_mascota");
                                    conexionWeb.execute(direccion);
                                } catch (MalformedURLException e) {
                                    Toast.makeText(editarMascota.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();

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
    public void cargarTipos(){
        try {
            conexionWeb = new ConexionWeb(editarMascota.this);
            conexionWeb.agregarVariables("idusuarios", idusuarios);
            URL direcciopn = new URL("http://carolina.x10host.com/index.php/Sistema/tipo_masctoa");
            conexionWeb.execute(direcciopn);
        } catch (MalformedURLException e) {
            Toast.makeText(editarMascota.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void cargarRazas(String id){
        try {
            conexionWeb = new ConexionWeb(editarMascota.this);
            conexionWeb.agregarVariables("idraza", id);
            URL direcciopn = new URL("http://carolina.x10host.com/index.php/Sistema/razas_datos_android_id_tipo");
            conexionWeb.execute(direcciopn);
        } catch (MalformedURLException e) {
            Toast.makeText(editarMascota.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}

