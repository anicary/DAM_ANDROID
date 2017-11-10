package mx.edu.ittepic.dadm_u4_ejercicio10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView etiqueta;
    EditText numero;
    Button validar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etiqueta=(TextView)findViewById(R.id.etiqueta);
        numero=(EditText)findViewById(R.id.numero);
        validar=(Button)findViewById(R.id.validar);
    }
}
