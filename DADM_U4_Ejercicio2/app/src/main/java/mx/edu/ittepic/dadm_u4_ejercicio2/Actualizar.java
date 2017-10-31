package mx.edu.ittepic.dadm_u4_ejercicio2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Actualizar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
    }
    public boolean onCreateOptionsMenu(Menu m){
                                        /*nombre archivo XML*//*Nombre de la clase public*/
        getMenuInflater().inflate(R.menu.menuactualizar,m);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem elegido){
        switch (elegido.getItemId()){
            case R.id.opcactualizar:
                //TODO CODIGO ACTUALIZAR
                break;
            case R.id.salir:
                finish();
                break;
        }
        return true;
    }
}

