package mx.edu.ittepic.miniu1_anacarolinamondragonrangel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Alimento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimento);
    }
    public void Acerca(View v){

        Intent opcion = new Intent(Alimento.this,Acercade.class);
        startActivity(opcion);
    }
}
