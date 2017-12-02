package mx.edu.ittepic.dadm_proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class editarMascota extends AppCompatActivity  implements AsyncResponse{
    String nombrea="",edada="",sexoa="";
    String idusuarios="";
    EditText enombre,eedad;
    Spinner etipo, eraza, esexo;
    Button editarpet;
    ConexionWeb conexionWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Editar Mascota");
        setContentView(R.layout.activity_editar_mascota);

        enombre = (EditText) findViewById(R.id.enombremascota);
        eedad = (EditText) findViewById(R.id.eedadmascota);
        editarpet = (Button)findViewById(R.id.editarmascota);
        esexo = (Spinner) findViewById(R.id.esexomascota);
        etipo = (Spinner) findViewById(R.id.etipomascota);
        eraza = (Spinner) findViewById(R.id.erazamascota);

        editarpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombrea = enombre.getText().toString();
                edada = eedad.getText().toString();
                sexoa = esexo.getSelectedItem().toString();
                if (!nombrea.equals("") || !edada.equals("") || !sexoa.equals("")) {
                    try {
                        conexionWeb = new ConexionWeb(editarMascota.this);
                        conexionWeb.agregarVariables("nombre", nombrea);
                        conexionWeb.agregarVariables("edad",edada);
                        conexionWeb.agregarVariables("sexo", sexoa);
                        conexionWeb.agregarVariables("idusuarios", idusuarios);
                        URL direccion = new URL("http://carolina.x10host.com/index.php/Sistema/editar_mascota");
                        conexionWeb.execute(direccion);
                    } catch (MalformedURLException e) {
                        Toast.makeText(editarMascota.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
        SharedPreferences prefs =
                getSharedPreferences("INFO_USUARIO", Context.MODE_PRIVATE);

        idusuarios = prefs.getString("idusuarios", "0");

    }
    public void procesarRespuesta(String r) {
        if(r.equals("actualizado")){
            Toast.makeText(editarMascota.this,"CAMBIOS GUARDADOS",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(editarMascota.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }else
        {

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

                break;
        }
        return true;
    }
}

