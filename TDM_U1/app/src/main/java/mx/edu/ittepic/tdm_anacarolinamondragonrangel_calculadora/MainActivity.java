package mx.edu.ittepic.tdm_anacarolinamondragonrangel_calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView result;
    Button borrar,uno,dos,tres,cuatro,cinco,seis,siete,ocho,nueve,cero,sumar,restar,dividir,mult,pto,igual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.textView2);
        borrar = findViewById(R.id.Borrar);
        uno = findViewById(R.id.num1);
        dos = findViewById(R.id.num2);
        tres = findViewById(R.id.num3);
        cuatro = findViewById(R.id.num4);
        cinco = findViewById(R.id.num5);
        seis = findViewById(R.id.num6);
        siete = findViewById(R.id.num7);
        ocho = findViewById(R.id.num8);
        nueve = findViewById(R.id.num9);
        cero = findViewById(R.id.numcero);
        sumar =findViewById(R.id.suma);
        restar = findViewById(R.id.resta);
        dividir = findViewById(R.id.dividir);
        mult = findViewById(R.id.mult);
        pto = findViewById(R.id.punto);
        igual = findViewById(R.id.igual);
    }
}
