package mx.edu.ittepic.dadm_u5_practica6_anacarolinamondragonrangel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class Multiplicar extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{
    Button calcular;
    TextView tabla ;
    Integer limite=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplicar);
        final EditText num;
        ((SeekBar)findViewById(R.id.barra)).setOnSeekBarChangeListener(this);
        num=(EditText) findViewById(R.id.ed1);
        calcular=(Button)findViewById(R.id.calcular);
        tabla=(TextView)findViewById(R.id.txt3);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resultado="";
                int valor=0;
                //   inicio | condicion| incremento   (++ o --)
                for (int i=1; i<= limite; i++){

                   valor=Integer.parseInt(num.getText().toString());

                    resultado += valor + " x "+ (i) +"="+(valor*(i))+" \n";
                }
               tabla.setText(resultado);
            }
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        ((TextView)findViewById(R.id.txt2)).setText("CALCULAR HASTA: "+ i );
        limite =i;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}

