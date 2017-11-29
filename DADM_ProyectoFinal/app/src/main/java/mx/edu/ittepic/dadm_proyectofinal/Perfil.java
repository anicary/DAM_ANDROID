package mx.edu.ittepic.dadm_proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Perfil extends AppCompatActivity {
    TextView nombre,apellido,correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        nombre=(TextView)findViewById(R.id.nombreperfil);
        apellido=(TextView)findViewById(R.id.apellidoperfil);
        correo=(TextView)findViewById(R.id.correoperfil);
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
