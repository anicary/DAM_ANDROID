package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_pbd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AgregarEquipo extends AppCompatActivity {
    EditText descripcion,tipo;
    Button btnagregarEquipo;
    BD db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_equipo);

        descripcion=(EditText)findViewById(R.id.descripcion);
        tipo=(EditText)findViewById(R.id.tipo);
        btnagregarEquipo=(Button) findViewById(R.id.btnagregarEquipo);

    }
}
