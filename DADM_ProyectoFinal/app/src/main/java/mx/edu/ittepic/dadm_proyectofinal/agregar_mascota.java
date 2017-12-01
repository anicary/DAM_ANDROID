package mx.edu.ittepic.dadm_proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class agregar_mascota extends AppCompatActivity implements AsyncResponse {
    EditText nombre, edad;
    String name, sex, edad1,idusuarios="";;
    Spinner tipo, raza, sexo;
    Button agregar;
    ConexionWeb conexionWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Registra a tu mascota");
        setContentView(R.layout.activity_agregar_mascota);
        nombre = (EditText) findViewById(R.id.nombremascota);
        edad = (EditText) findViewById(R.id.edadmascota);
        sexo = (Spinner) findViewById(R.id.sexomascota);
        tipo = (Spinner) findViewById(R.id.tipomascota);
        raza = (Spinner) findViewById(R.id.razamascota);
        agregar = (Button) findViewById(R.id.agregar);

        SharedPreferences prefs =
                getSharedPreferences("INFO_USUARIO", Context.MODE_PRIVATE);

        idusuarios = prefs.getString("idusuarios", "0");

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nombre.getText().toString();
                sex = sexo.getSelectedItem().toString();
                edad1 = edad.getText().toString();

                if (!name.equals("") || !sex.equals("") || !edad1.equals("")) {
                    //TODO PENDIENTE
                    try {
                        conexionWeb = new ConexionWeb(agregar_mascota.this);
                        conexionWeb.agregarVariables("idusuarios",idusuarios);
                        conexionWeb.agregarVariables("nombre", name);
                        conexionWeb.agregarVariables("sexo", sex);
                        conexionWeb.agregarVariables("edad", edad1);
                        conexionWeb.agregarVariables("tipo_mascota_idtipo_mascota", "1");
                        conexionWeb.agregarVariables("razamascota_idrazamascota", "1");
                        URL direccion = new URL("http://carolina.x10host.com/index.php/Sistema/registro_mascota");
                        Intent inicio = new Intent(agregar_mascota.this,MainActivity.class);
                        startActivity(inicio);
                        conexionWeb.execute(direccion);
                    } catch (MalformedURLException e) {
                        Toast.makeText(agregar_mascota.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
    }
    @Override
    public void procesarRespuesta(String r){}
}
