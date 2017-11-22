package mx.edu.ittepic.dadm_u4_ejercicio11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText nombre,domicilio,sexo;
    Button enviar;
    TextView etiqueta;
    ConexionWeb conexionWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText)findViewById(R.id.nombre);
        domicilio = (EditText)findViewById(R.id.domicilio);
        sexo=(EditText)findViewById(R.id.sexo);
        enviar = (Button)findViewById(R.id.enviar);
        etiqueta=(TextView)findViewById(R.id.etiqueta);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                conexionWeb = new ConexionWeb(MainActivity.this);
                //antes de conectar se agregan las variables los que esta esperando el POST
                conexionWeb.agregarVariables("nombre",nombre.getText().toString());
                conexionWeb.agregarVariables("domicilio",domicilio.getText().toString());
                conexionWeb.agregarVariables("sexo",sexo.getText().toString());

                try {
                    URL direccion = new URL("https://anicary.000webhostapp.com/recibirvariables.php");
                    conexionWeb.execute(direccion);
                }catch (MalformedURLException e){
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
