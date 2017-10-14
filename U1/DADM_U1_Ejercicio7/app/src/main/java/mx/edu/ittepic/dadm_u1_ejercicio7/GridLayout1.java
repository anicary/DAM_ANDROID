package mx.edu.ittepic.dadm_u1_ejercicio7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GridLayout1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout1);
        setTitle("PAPUS");
    }

    public void siguienteVentana (View v){

        Intent otroActivity = new Intent(GridLayout1.this, OtraPantalla.class);
        startActivity(otroActivity);
    }
}
