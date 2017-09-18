package mx.edu.ittepic.miniu1_anacarolinamondragonrangel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class REGISTRO extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }
    public void Registro (View v){

        Intent opcion = new Intent(REGISTRO.this,SiguienteRegistro.class);
        startActivity(opcion);
    }
}
