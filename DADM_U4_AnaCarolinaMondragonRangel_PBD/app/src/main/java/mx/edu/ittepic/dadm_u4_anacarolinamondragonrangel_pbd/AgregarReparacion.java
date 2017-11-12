package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_pbd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

public class AgregarReparacion extends AppCompatActivity {
    EditText ed1,ed2;
    Spinner SpinnerCliente;
    BD db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_reparacion);

        db = new BD(AgregarReparacion.this, "reparacionCelular", null, 1);

        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);

        SpinnerCliente=(Spinner)findViewById(R.id.spinnerCliente);


    }
}
