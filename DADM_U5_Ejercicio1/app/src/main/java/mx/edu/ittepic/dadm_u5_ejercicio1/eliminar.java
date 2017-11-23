package mx.edu.ittepic.dadm_u5_ejercicio1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class eliminar extends AppCompatActivity implements AsyncResponse {
    ConexionWeb conexionWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        conexionWeb = new ConexionWeb(this);
    }

    @Override
    public void procesarRespuesta(String r) {

    }
}
