package mx.edu.ittepic.dadm_ejercicio3;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FrameLayout2 extends AppCompatActivity {
    ImageView contenedorImagen;
    TextView titulo,mensaje;
    int posicion;
    Button saltar,siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout2);

        contenedorImagen = (ImageView) findViewById(R.id.contenedor);
        titulo = (TextView) findViewById(R.id.titulo);
        mensaje = (TextView) findViewById(R.id.mensaje);
        posicion = 1;
        saltar = (Button) findViewById(R.id.btn1);
        siguiente = (Button) findViewById(R.id.btn2);
    }

    public void siguienteImagen (View v){

        posicion ++;
        switch (posicion){
            case 2: //Cambiar imagen x explora titulo a Explora y el mensaje
                contenedorImagen.setImageResource(R.drawable.explorar);
                titulo.setText("EXPLORA");
                mensaje.setText("Tenemos las mejores opciones\n para ti amigo!!!");
                break;
            case 3: //Cambiar imagen x unete, titulo unete, mensaje
                contenedorImagen.setImageResource(R.drawable.unete);
                titulo.setText("UNETE");
                mensaje.setText("Agreganos a tus redes sociales!!\n somos los mejores");
                siguiente. setText("Finalizar");
                saltar.setText("");
                break;
            case 4: finish();
                break;
        }
    }
    public void cerrar (View v){
        finish();
    }
}
