package mx.edu.ittepic.practicau1_3_textatributos_anacarolinamondragonrangel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Practica1_3 extends AppCompatActivity {
    TextView txt1;
    Button btn;
    EditText edt;
    Spinner spn;
    int turno=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practica1_3);
        txt1=(TextView)findViewById(R.id.textView);
        btn=(Button)findViewById(R.id.button);
        edt=(EditText)findViewById(R.id.editText);
        spn=(Spinner)findViewById(R.id.spinner);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"ERROR CAMPO DE TEXTO VACIO",Toast.LENGTH_SHORT).show();
                }else
                {
                    if(turno==0)
                    {
                        txt1.setText(edt.getText().toString());
                        if (spn.getSelectedItem().toString().equals("AZUL")){
                            txt1.setTextColor(getResources().getColor(R.color.azul));
                        }
                        if (spn.getSelectedItem().toString().equals("BLANCO")){
                            txt1.setTextColor(getResources().getColor(R.color.blanco));
                        }
                        if (spn.getSelectedItem().toString().equals("AMARILLO")){
                            txt1.setTextColor(getResources().getColor(R.color.amarillo));
                        }
                        if (spn.getSelectedItem().toString().equals("VERDE")){
                            txt1.setTextColor(getResources().getColor(R.color.verde));
                        }
                        btn.setText("BORRAR CAMPO DE TEXTO");
                        turno=1;
                    }else
                    {
                        turno=0;
                        btn.setText("CAMBIAR COLOR Y TEXTO");
                        edt.setText("");
                    }
                }
            }
        });
    }
}
