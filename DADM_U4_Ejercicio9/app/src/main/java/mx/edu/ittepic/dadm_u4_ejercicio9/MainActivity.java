package mx.edu.ittepic.dadm_u4_ejercicio9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView etiqueta;
    Button ejecutar;
    Asincrono asin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ejecutar=(Button)findViewById(R.id.ejecutar);
        etiqueta=(TextView)findViewById(R.id.etiqueta);

        asin = new Asincrono(this); //CUANDO PASA A THIS PASA LA DIRECCION DE MEMORIA DE LA CLASE


    }
}
