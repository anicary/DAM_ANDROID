package mx.edu.ittepic.dadm_1_ejercicio2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FrameLayoutEjemplo extends AppCompatActivity {
    ImageView contenedorImagenes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout_ejemplo);

        contenedorImagenes = (ImageView) findViewById(R.id.contenedorimagenes);
    }

    public void cambiarImagen1 (View v){

        contenedorImagenes.setImageResource(R.drawable.imagen1);
    }
    public void cambiarImagen2 (View v){

        contenedorImagenes.setImageResource(R.drawable.imagen2);
    }
    public void cambiarImagen3 (View v){

        contenedorImagenes.setImageResource(R.drawable.imagen3);
    }
    public void cambiarImagen4 (View v){

        contenedorImagenes.setImageResource(R.drawable.imagen4);
    }
}
