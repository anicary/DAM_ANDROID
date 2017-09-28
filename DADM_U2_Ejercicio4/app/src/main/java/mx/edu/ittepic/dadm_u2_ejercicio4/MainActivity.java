package mx.edu.ittepic.dadm_u2_ejercicio4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText valorcadena,valorentero,valorbooleano;
    Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valorcadena = (EditText)findViewById(R.id.valorcadena);
        valorentero = (EditText)findViewById(R.id.valorentero);
        valorbooleano = (EditText)findViewById(R.id.valorbooleano);

        enviar = (Button) findViewById(R.id.enviar);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*CREAR EL INTENT PARA INVOCAR SEUNDA VENTANA*/
                 Intent frida = new Intent(MainActivity.this,Main2Activity.class);
                /*PASARLE LOS VALORES AL INTENT*/
                frida.putExtra("cadena",valorcadena.getText().toString());

                int entero =Integer.parseInt(valorentero.getText().toString());
                frida.putExtra("paquirris",entero);

                boolean boleano = Boolean.parseBoolean(valorbooleano.getText().toString());
                frida.putExtra("despensa",boleano);
                /*INICIAR LA INVOCACION DE 2NDA VENTANA*/
                startActivity(frida);


            }
        });
    }
}
