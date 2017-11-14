package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_bdremota;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        nombre.setText(getIntent().getStringExtra("nombre"));
        domicilio.setText(getIntent().getStringExtra("domicilio"));
        telefono.setText(getIntent().getStringExtra("telefono"));
        correo.setText(getIntent().getStringExtra("correo"));

        btnagregar=(Button)findViewById(R.id.actualizar);
        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
