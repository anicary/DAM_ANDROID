package mx.edu.ittepic.miniu1_anacarolinamondragonrangel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Seleccionmascota extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionmascota);
    }
    public void Calendario (View v){

        Intent opcion = new Intent(Seleccionmascota.this,Calendario.class);
        startActivity(opcion);
    }
    public void Alimento(View v){

        Intent opcion = new Intent(Seleccionmascota.this,Alimento.class);
        startActivity(opcion);
    }
}
