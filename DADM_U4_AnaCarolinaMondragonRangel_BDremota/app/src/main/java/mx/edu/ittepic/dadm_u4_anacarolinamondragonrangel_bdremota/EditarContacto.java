package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_bdremota;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditarContacto extends AppCompatActivity {
    EditText nombre,domicilio,telefono,correo;
    Button btnagregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contacto);

        nombre =(EditText)findViewById(R.id.nombre);
        domicilio =(EditText)findViewById(R.id.dom);
        telefono =(EditText)findViewById(R.id.tel);
        correo =(EditText)findViewById(R.id.correo);

        btnagregar=(Button)findViewById(R.id.btnagregar);
    }
}
