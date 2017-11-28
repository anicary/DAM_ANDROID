package mx.edu.ittepic.dadm_proyectofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class registro extends AppCompatActivity {
    EditText rnombre,rapellido,rcorreo,rcontrasena,rrcontrasena;
    Spinner sexo,estado,municipio;
    Button registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        rnombre = (EditText)findViewById(R.id.nombreregistro);
        rapellido = (EditText)findViewById(R.id.apellidoregistro);
        rcorreo = (EditText)findViewById(R.id.correoregistro);
        rcontrasena = (EditText)findViewById(R.id.contraregistro);
        rrcontrasena = (EditText)findViewById(R.id.rcontraregistro);

        registrarse = (Button)findViewById(R.id.registro);

        sexo = (Spinner)findViewById(R.id.sexoregistro);
        estado = (Spinner)findViewById(R.id.estadoregistro);
        municipio = (Spinner)findViewById(R.id.municipioregistro);

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rnombre !=null){

                }
            }
        });
    }
}
