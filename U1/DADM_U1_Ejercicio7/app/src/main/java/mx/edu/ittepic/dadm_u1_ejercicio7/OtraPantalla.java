package mx.edu.ittepic.dadm_u1_ejercicio7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OtraPantalla extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otra_pantalla);
    }

    public void regresarAlaPrimertaActivity(View v){

        finish();
    }
}
