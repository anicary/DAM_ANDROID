package mx.edu.ittepic.dadm_u5_ejercicio1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class actualizar extends AppCompatActivity implements AsyncResponse{
    EditText modelo,fabricante,descripcion,precio,ram;
    ConexionWeb conexionWeb;
    Button buscar,aplicar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        modelo =(EditText)findViewById(R.id.modelo);
        fabricante =(EditText)findViewById(R.id.fabricante);
        descripcion =(EditText)findViewById(R.id.descripcion);
        precio =(EditText)findViewById(R.id.precio);
        ram =(EditText)findViewById(R.id.ram);
        buscar =(Button)findViewById(R.id.buscar);
        aplicar =(Button)findViewById(R.id.aplicar);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        aplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void procesarRespuesta(String r) {

    }
}
