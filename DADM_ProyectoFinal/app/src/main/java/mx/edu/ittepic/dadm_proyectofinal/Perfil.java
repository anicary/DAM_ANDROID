package mx.edu.ittepic.dadm_proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Perfil extends AppCompatActivity {
    String nombrea="",apellidosa="",correoa="";
    TextView nombre,apellido,correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        nombre=(TextView)findViewById(R.id.vnombreperfil);
        apellido=(TextView)findViewById(R.id.vapellidoperfil);
        correo=(TextView)findViewById(R.id.vcorreoperfil);

        SharedPreferences prefs =
                getSharedPreferences("INFO_USUARIO", Context.MODE_PRIVATE);

        nombrea = prefs.getString("nombre", "Nombre");
        apellidosa = prefs.getString("apellidos", "apellidos");
        correoa = prefs.getString("correo", "correo@email.com");
        nombre.setText(nombrea);
        apellido.setText(apellidosa);
        correo.setText(correoa);
    }
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.menu,m);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem mi){
        switch (mi.getItemId()){
            case R.id.editar:

                break;
            case R.id.cerrarse:

                break;
        }
        return true;
    }
}
