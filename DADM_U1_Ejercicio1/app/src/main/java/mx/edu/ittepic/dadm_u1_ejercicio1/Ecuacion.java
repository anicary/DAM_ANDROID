package mx.edu.ittepic.dadm_u1_ejercicio1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Ecuacion extends AppCompatActivity {
    EditText n1,n2;
    Button ejecutarOperacion;
    TextView et1,et2,et3;
    RadioButton sumar,dividir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecuacion);

        //HACIENDO REFERENCIA O LIGA DEL OBJETO DE DISEÑO
        //AL OBJETO DE PROGRAMACION

        n1 = (EditText) findViewById(R.id.valor1);
        n2 = (EditText) findViewById(R.id.valor2);

        et1 = (TextView) findViewById(R.id.etiqueta1);
        et2 = (TextView)findViewById(R.id.etiqueta2);
        et3 = (TextView) findViewById(R.id.etiqueta3);

        sumar = (RadioButton) findViewById(R.id.operacion1);
        dividir = (RadioButton) findViewById(R.id.operacion2);

        ejecutarOperacion = (Button) findViewById(R.id.realizar);

    }

    public void operacion(View v){
        //ESTE ES UN METODO LIGA CON EL BOTON DE LA VISTA
    }

}

