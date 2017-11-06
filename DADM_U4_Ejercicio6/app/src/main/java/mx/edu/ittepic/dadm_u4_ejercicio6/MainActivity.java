package mx.edu.ittepic.dadm_u4_ejercicio6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText dado1,dado2,total1,dadoJ1,dadoJ2,total2;
    Button tirar;
    TextView turno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dado1 =(EditText)findViewById(R.id.dado1);
        dado2 =(EditText)findViewById(R.id.dado2);
        total1 =(EditText)findViewById(R.id.total1);

        dadoJ1 =(EditText)findViewById(R.id.dadoJ1);
        dadoJ2 =(EditText)findViewById(R.id.dadoJ2);
        total2 =(EditText)findViewById(R.id.total2);

        tirar =(Button)findViewById(R.id.tirar);

        turno=(TextView)findViewById(R.id.turno);

    }

}
