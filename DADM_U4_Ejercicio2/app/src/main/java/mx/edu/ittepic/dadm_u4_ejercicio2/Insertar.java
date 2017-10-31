package mx.edu.ittepic.dadm_u4_ejercicio2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Insertar extends AppCompatActivity {
    BD db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
    }
    public boolean onCreateOptionsMenu(Menu m){
                                        /*nombre archivo XML*//*Nombre de la clase public*/
        getMenuInflater().inflate(R.menu.menuinsertar,m);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem elegido){
        switch (elegido.getItemId()){
            case R.id.opcinsertar:

                break;
            case R.id.salir:
                finish();
                break;
        }
        return true;
    }
}
