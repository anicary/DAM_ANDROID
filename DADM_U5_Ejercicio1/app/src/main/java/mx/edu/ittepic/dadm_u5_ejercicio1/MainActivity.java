package mx.edu.ittepic.dadm_u5_ejercicio1;

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
    EditText modelo,fabricante,descripcion,precio,ram;
    Button insertar,consultar;
    TextView etiqueta;
    ConexionWeb conexionWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modelo = (EditText)findViewById(R.id.modelo);
        fabricante = (EditText)findViewById(R.id.fabricante);
        descripcion = (EditText)findViewById(R.id.descripcion);
        precio = (EditText)findViewById(R.id.precio);
        ram = (EditText)findViewById(R.id.ram);

        insertar = (Button) findViewById(R.id.insertar);
        consultar = (Button)findViewById(R.id.consultar);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    conexionWeb = new ConexionWeb(MainActivity.this);
                    conexionWeb.agregarVariables("modelo",modelo.getText().toString());
                    conexionWeb.agregarVariables("fabricante",fabricante.getText().toString());
                    conexionWeb.agregarVariables("descripcion",descripcion.getText().toString());
                    conexionWeb.agregarVariables("precio",precio.getText().toString());
                    conexionWeb.agregarVariables("ram",ram.getText().toString());
                    URL direccion = new URL("https://anicary.000webhostapp.com/movilesinsertar.php");
                    conexionWeb.execute(direccion);

                }catch (MalformedURLException e){
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
