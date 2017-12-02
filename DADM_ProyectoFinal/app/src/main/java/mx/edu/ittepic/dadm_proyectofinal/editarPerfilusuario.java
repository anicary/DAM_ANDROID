package mx.edu.ittepic.dadm_proyectofinal;

import android.content.Context;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class editarPerfilusuario extends AppCompatActivity implements AsyncResponse {
    String nombrea="",apellidosa="",correoa="",imagen="";
    String idusuarios="";
    EditText nombre,apellido,correo;
    Button actualizar;
    ConexionWeb conexionWeb;
    BDInterna dbinterna;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Editar Perfil");
        setContentView(R.layout.activity_editar_perfilusuario);

        nombre = (EditText) findViewById(R.id.veunombreperfil);
        apellido = (EditText) findViewById(R.id.veuapellidoperfil);
        correo = (EditText) findViewById(R.id.veucorreoperfil);
        actualizar = (Button)findViewById(R.id.editarperfil);
        dbinterna = new BDInterna(editarPerfilusuario.this, "baseinterna", null, 1);

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombrea = nombre.getText().toString();
                apellidosa = apellido.getText().toString();
                correoa = correo.getText().toString();
                if (!nombrea.equals("") || !apellidosa.equals("") || !correoa.equals("")) {
                    try {
                        conexionWeb = new ConexionWeb(editarPerfilusuario.this);
                        conexionWeb.agregarVariables("nombre", nombrea);
                        conexionWeb.agregarVariables("apellidos", apellidosa);
                        conexionWeb.agregarVariables("correo", correoa);
                        conexionWeb.agregarVariables("idusuarios", idusuarios);
                        URL direccion = new URL("http://carolina.x10host.com/index.php/Sistema/editar_usuario");
                        conexionWeb.execute(direccion);
                    } catch (MalformedURLException e) {
                        Toast.makeText(editarPerfilusuario.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
            }
        });

        SharedPreferences prefs =
                getSharedPreferences("INFO_USUARIO", Context.MODE_PRIVATE);

        idusuarios = prefs.getString("idusuarios", "0");

        nombrea = prefs.getString("nombre", "Nombre");
        apellidosa = prefs.getString("apellidos", "apellidos");
        correoa = prefs.getString("correo", "correo@email.com");
        imagen = prefs.getString("imagen", "http://carolina.x10host.com/archivos/fotos/perfil.jpg");
        nombre.setText(nombrea);
        apellido.setText(apellidosa);
        correo.setText(correoa);
    }
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.camara,m);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem mi){
        switch (mi.getItemId()){
            case R.id.tomar:
                
                break;
        }
        return true;
    }

    public void procesarRespuesta(String r) {
        Toast.makeText(editarPerfilusuario.this,"CAMBIOS GUARDADOS",Toast.LENGTH_LONG).show();
        SharedPreferences.Editor editor = getSharedPreferences("INFO_USUARIO", MODE_PRIVATE).edit();
        editor.putString("nombre",nombrea);
        editor.putString("apellidos",apellidosa);
        editor.putString("correo",correoa);
        editor.apply();
        try {
            SQLiteDatabase base = dbinterna.getWritableDatabase();
            String query1 = "UPDATE usuario SET nombre='"+nombrea+"', apellidos='"+apellidosa+"', correo='"+correoa+"' ;";
            base.execSQL(query1);
        }catch (SQLException e){
            Toast.makeText(editarPerfilusuario.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(editarPerfilusuario.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
