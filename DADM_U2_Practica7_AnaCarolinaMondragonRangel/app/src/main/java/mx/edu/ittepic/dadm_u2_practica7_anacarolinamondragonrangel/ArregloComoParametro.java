package mx.edu.ittepic.dadm_u2_practica7_anacarolinamondragonrangel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ArregloComoParametro extends AppCompatActivity {
    EditText numeros;
    Button btncambiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arreglo_como_parametro);

        numeros =(EditText) findViewById(R.id.edittxt);
        btncambiar=(Button)findViewById(R.id.btnabrir);

        btncambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (numeros.getText().toString().equals("")){
                    Toast.makeText(ArregloComoParametro.this,"INSERTE NUMEROS",Toast.LENGTH_LONG).show();
                }else{
                    Intent opcion = new Intent(ArregloComoParametro.this,MainActivity.class);
                    opcion.putExtra("arreglo",numeros.getText().toString());/*ENVIAR DATOS A LOS ACTIVITYS */
                    startActivity(opcion);
                }

            }

        });

    }
}
