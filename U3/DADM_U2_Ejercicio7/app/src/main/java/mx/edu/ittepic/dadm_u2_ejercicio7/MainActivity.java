package mx.edu.ittepic.dadm_u2_ejercicio7;
//UNIDAD 3 DESCRIPCION DEL VIEW Y COMO HACER EL MOVIMIENTO DE PERSONAJES

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Lienzo(this));
    }
}
