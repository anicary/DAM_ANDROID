package mx.edu.ittepic.dadm_proyectofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class agregar_mascota extends AppCompatActivity {
    EditText nombre,edad;
    Spinner tipo,raza,sexo;
    Button agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_mascota);
        nombre =(EditText)findViewById(R.id.nombremascota);
        edad = (EditText)findViewById(R.id.edadmascota);
        sexo=(Spinner)findViewById(R.id.sexomascota);
        tipo=(Spinner)findViewById(R.id.tipomascota);
        raza=(Spinner)findViewById(R.id.razamascota);
        agregar=(Button)findViewById(R.id.agregar);
    }
}
