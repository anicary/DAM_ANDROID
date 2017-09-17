package mx.edu.ittepic.practicau1_5_activitys_anacarolinamondragonrangel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Recuperar extends AppCompatActivity {
    TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);

        txt1=(TextView) findViewById(R.id.recuperar);
    }
    public  void leer (View v){
        String nombreArchivo = "Palabras.txt";

        try {
            InputStreamReader manejadorLectura= new InputStreamReader(openFileInput(nombreArchivo));
            BufferedReader buffreader = new BufferedReader( manejadorLectura ) ;
            String mensajeobtenido = buffreader.readLine () ;
            txt1.setText(mensajeobtenido);

        }catch (Exception e){}
    }
    public void cerrar (View v){
        finish();
    }
}
