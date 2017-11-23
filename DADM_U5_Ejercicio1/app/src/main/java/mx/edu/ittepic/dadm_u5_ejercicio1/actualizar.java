package mx.edu.ittepic.dadm_u5_ejercicio1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class actualizar extends AppCompatActivity implements AsyncResponse{
    EditText modelo,fabricante,descripcion,precio,ram;
    ConexionWeb conexionWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        conexionWeb = new ConexionWeb(this);
    }

    @Override
    public void procesarRespuesta(String r) {

    }
}
