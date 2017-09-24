package mx.edu.ittepic.dadm_u5_practica6_anacarolinamondragonrangel;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class Multiplicar extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplicar);

        final EditText num;
        Button calcular;
        TextView resultado;

        ((SeekBar)findViewById(R.id.barra)).setOnSeekBarChangeListener(this);

        num=(EditText) findViewById(R.id.ed1);
        calcular=(Button)findViewById(R.id.calcular);
        resultado=(TextView)findViewById(R.id.txt3);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resultado="";
                int campo1,valor=0;
                campo1 = Integer.parseInt(num.getText().toString());
                for (int i=0; i<= campo1; i++){
                    valor=campo1*i;
                    resultado = campo1 + " x "+ i +"="+valor;
                }


            }
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        ((TextView)findViewById(R.id.txt2)).setText("CALCULAR HASTA: "+ i);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}

