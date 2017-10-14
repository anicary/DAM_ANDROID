package mx.edu.ittepic.practicau1_4_grados_anacarolinamondragonrangel;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Practica1_4 extends AppCompatActivity {
    EditText edt1;
    Button bt1;
    RadioButton a,b;
    TextView resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practica1_4);
        bt1=(Button)findViewById(R.id.convertir);
        edt1=(EditText)findViewById(R.id.numero);
        a=(RadioButton)findViewById(R.id.rd1);
        b=(RadioButton)findViewById(R.id.rd2);
        resultado=(TextView)findViewById(R.id.resultado);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float numconvertir=Float.parseFloat(edt1.getText().toString());
                if (numconvertir>0){
                    if (a.isChecked()){
                        resultado.setText("°"+((1.8*numconvertir)+32));
                    }else
                    {
                        resultado.setText("°"+((numconvertir-32)/1.8));
                    }
                }else
                {
                    Toast.makeText(getApplicationContext(),"ERROR LA CANTIDAD A CONVERTIR ES MENOR A 0",Toast.LENGTH_SHORT).show();
                }
            }
        });
        a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                bt1.setText("Convertir Fahrenheit a centigrados");
            }
        });
        b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                bt1.setText("Convertir centigrados a Fahrenheit");
            }
        });
    }
}
