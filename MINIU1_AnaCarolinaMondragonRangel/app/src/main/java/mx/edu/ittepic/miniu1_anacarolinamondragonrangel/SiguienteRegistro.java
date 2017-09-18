package mx.edu.ittepic.miniu1_anacarolinamondragonrangel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SiguienteRegistro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siguiente_registro);
    }
    public void Regresar (View v){

        Intent opcion = new Intent(SiguienteRegistro.this,REGISTRO.class);
        startActivity(opcion);
    }
    public void Finalizar (View v){

        Intent opcion = new Intent(SiguienteRegistro.this,Seleccionmascota.class);
        startActivity(opcion);
    }
}
