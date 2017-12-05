package mx.edu.ittepic.dadm_proyectofinal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static android.R.attr.data;

public class registro extends AppCompatActivity implements AsyncResponse {
    EditText rnombre, rapellido, rcorreo, rcontrasena, rrcontrasena;
    String name, apell, mail, passw, passw2, datos = "";
    Spinner sexo, estado, municipio;
    Button registrarse;
    ConexionWeb conexionWeb;
    List<String[]> listtasMunicipios = new ArrayList<String[]>();
    BDInterna dbinterna;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        cargarEstados();
        rnombre = (EditText) findViewById(R.id.nombreregistro);
        rapellido = (EditText) findViewById(R.id.apellidoregistro);
        rcorreo = (EditText) findViewById(R.id.correoregistro);
        rcontrasena = (EditText) findViewById(R.id.contraregistro);
        rrcontrasena = (EditText) findViewById(R.id.rcontraregistro);
        dbinterna = new BDInterna(registro.this, "baseinterna", null, 1);
        registrarse = (Button) findViewById(R.id.registro);

 setTitle("REGISTRO DE USUARIO");



        sexo = (Spinner) findViewById(R.id.sexoregistro);
        estado = (Spinner) findViewById(R.id.estadoregistro);
        municipio = (Spinner) findViewById(R.id.municipioregistro);
        estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                List<String> municipiosNuevaLista = new ArrayList<String>();
                Collections.addAll(municipiosNuevaLista, listtasMunicipios.get(i));
                ArrayAdapter adapter = new ArrayAdapter<String>(registro.this,R.layout.support_simple_spinner_dropdown_item,municipiosNuevaLista);
                municipio.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = rnombre.getText().toString();
                apell = rapellido.getText().toString();
                mail = rcorreo.getText().toString();
                passw = rcontrasena.getText().toString();
                passw2 = rrcontrasena.getText().toString();
                if (!name.equals("") || !apell.equals("") || !mail.equals("") || !passw.equals("") || !passw2.equals("")) {
                    //TODO PENDIENTE
                    if (passw.equals(passw2)) {
                        try {
                            conexionWeb = new ConexionWeb(registro.this);
                            conexionWeb.agregarVariables("nombre", name);
                            conexionWeb.agregarVariables("apellidos", apell);
                            conexionWeb.agregarVariables("correo", mail);
                            conexionWeb.agregarVariables("estado",  estado.getSelectedItem().toString());
                            conexionWeb.agregarVariables("municipio", municipio.getSelectedItem().toString());
                            conexionWeb.agregarVariables("contrasena", passw);
                            URL direccion = new URL("http://carolina.x10host.com/index.php/Sistema/registro_usuario");
                            conexionWeb.execute(direccion);
                        } catch (MalformedURLException e) {
                            Toast.makeText(registro.this, e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    } else {
                        datos += "LAS CONTRASEÑAS NO COINCIDEN";
                        AlertDialog.Builder alerta = new AlertDialog.Builder(registro.this);
                        alerta.setTitle("ATENCION")
                                .setMessage("LAS CONTRASEÑAS NO COINCIDEN")
                                .setIcon(R.drawable.ic_error_black_24dp)
                                .setPositiveButton("ENTENDIDO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        dialog.dismiss();
                                    }
                                }).show();
                    }
                } else {
                    if (name.equals("") || name.equals(" ")) {
                        datos += "\n EL NOMBRE ESTA VACIO";
                    }
                    if (apell.equals("") || apell.equals(" ")) {
                        datos += "\n El APELLIDO ESTA VACIO";
                    }
                    if (mail.equals("") || mail.equals(" ")) {
                        datos += "\n El CORREO ESTA VACIO";
                    }
                    if (passw.equals("") || passw.equals(" ")) {
                        datos += "\n LA CONTRASEÑA ESTA VACIA";
                    }
                    if (passw2.equals("") || passw2.equals(" ")) {
                        datos += "\n ES NECESARIO REPETIR LA CONTRASEÑA";
                    }
                    AlertDialog.Builder alerta2 = new AlertDialog.Builder(registro.this);
                    alerta2.setTitle("ATENCION")
                            .setMessage("LOS SIGUIENTES CAMPOS ESTAN VACIOS  " + datos)
                            .setIcon(R.drawable.ic_error_black_24dp)
                            .setPositiveButton("ENTENDIDO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            }
        });
    }

    public void cargarEstados() {
        try {
            conexionWeb = new ConexionWeb(this);
            URL direcciopn = new URL("http://carolina.x10host.com/archivos/estados_municipios.json");
            conexionWeb.execute(direcciopn);
        } catch (MalformedURLException e) {
            Toast.makeText(registro.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void procesarRespuesta(String r) {
        if (r.equals("duplicado")) {
            AlertDialog.Builder alerta = new AlertDialog.Builder(registro.this);
            alerta.setTitle("ATENCION")
                    .setMessage("EL CORREO YA ESTA REGISTRADO POR OTRO USUARIO.")
                    .setIcon(R.drawable.ic_error_black_24dp)
                    .setPositiveButton("ENTENDIDO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    }).show();
        } else {
            try {
                Object json = new JSONTokener(r).nextValue();
                if (json instanceof JSONObject) {
                    JSONObject jObject = new JSONObject(r);

                    Iterator iterator = jObject.keys();
                    List<String> listaEstados = new ArrayList<String>();
                    while(iterator.hasNext()){
                        String key = (String)iterator.next();
                       /* JSONObject issue = jObject.getJSONObject(key);
                        String _pubKey = issue.optString("id");*/
                        System.out.println("ESTADOS "+key);
                        listaEstados.add(key);
                        JSONArray json2 = jObject.getJSONArray(key);
                        String[] minicipios  = new String[json2.length()];
                        for (int i = 0; i < json2.length(); i++) {
                            minicipios[i]=(String) json2.get(i);
                        }
                        listtasMunicipios.add(minicipios);
                    }
                     ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,listaEstados);
                     estado.setAdapter(adapter);
                    /*
                    JSONArray json2 = jObject.getJSONArray("Aguascalientes");
                    System.out.println("TEXTOOO  "+json2.get(2));*/
                } else if (json instanceof JSONArray) {
                    JSONArray arrayjson = new JSONArray(r);
                    if (arrayjson.getJSONObject(0).has("nombre")) {
                        for (int i = 0; i < arrayjson.length(); i++) {
                            SharedPreferences.Editor editor = getSharedPreferences("INFO_USUARIO", MODE_PRIVATE).edit();
                            editor.putString("nombre",arrayjson.getJSONObject(i).getString("nombre"));
                            editor.putString("apellidos",arrayjson.getJSONObject(i).getString("apellidos"));
                            editor.putString("correo",arrayjson.getJSONObject(i).getString("correo"));
                            editor.putString("idusuarios",(arrayjson.getJSONObject(i).getString("idusuarios")));
                            editor.putString("imagen",arrayjson.getJSONObject(i).getString("perfil_foto"));
                            editor.apply();
                            try {
                                SQLiteDatabase base = dbinterna.getWritableDatabase();
                                String query1 = "INSERT INTO usuario VALUES (idusuarios,'nombre','apellidos','correo','imagen')";
                                query1 = query1.replace("idusuarios",arrayjson.getJSONObject(i).getString("idusuarios"));
                                query1 = query1.replace("nombre", arrayjson.getJSONObject(i).getString("nombre"));
                                query1 = query1.replace("apellidos",arrayjson.getJSONObject(i).getString("apellidos"));
                                query1 = query1.replace("correo",arrayjson.getJSONObject(i).getString("correo"));
                                query1 = query1.replace("imagen",arrayjson.getJSONObject(i).getString("perfil_foto"));
                                base.execSQL(query1);
                            }catch (SQLException e){
                                Toast.makeText(registro.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                            Intent Ventanaregistro = new Intent(registro.this, MainActivity.class);
                            startActivity(Ventanaregistro);
                            finish();
                        }
                    }
                }
            } catch (JSONException e) {
                Toast.makeText(registro.this, e.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("" + e.getMessage());
            }
        }

    }
}
