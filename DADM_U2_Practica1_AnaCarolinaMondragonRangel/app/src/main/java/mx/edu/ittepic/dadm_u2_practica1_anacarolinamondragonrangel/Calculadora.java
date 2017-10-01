package mx.edu.ittepic.dadm_u2_practica1_anacarolinamondragonrangel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Calculadora extends AppCompatActivity {

    EditText num1,num2;
    Button btnsumar;
    TextView result;
    Spinner opciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        num1=(EditText) findViewById(R.id.valor1);
        num2=(EditText) findViewById(R.id.valor2);

        opciones=(Spinner) findViewById(R.id.opcion);

        btnsumar=(Button) findViewById(R.id.sumar);

        result=(TextView) findViewById(R.id.resultado);

        btnsumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num1.getText().toString().equals("") || num2.getText().toString().equals("")){
                    Toast.makeText(Calculadora.this,"ERROR CAMPO VACIO",Toast.LENGTH_SHORT).show();

                }else {

                   float valoresultado = 0,campo1,campo2;

                    try {
                        campo1 = Float.parseFloat(num1.getText().toString());
                        campo2 = Float.parseFloat(num2.getText().toString());

                        if (campo1 < 0 || campo2 < 0) {
                            Toast.makeText(Calculadora.this, "ESCRIBA UN NUMERO MAYOR A 0", Toast.LENGTH_SHORT).show();
                        }else {

                            if (opciones.getSelectedItemPosition() == 0) {
                                valoresultado = campo1 + campo2;

                            }
                            if (opciones.getSelectedItemPosition() == 1) {
                                valoresultado = campo1 - campo2;

                            }
                            if (opciones.getSelectedItemPosition() == 2) {
                                valoresultado = campo1 * campo2;

                            }
                            if (opciones.getSelectedItemPosition() == 3) {
                                valoresultado = campo1 / campo2;

                            }
                        }
                        result.setText("RESULTADO:"+valoresultado);

                    }catch (NumberFormatException e){
                        Toast.makeText(Calculadora.this,"ERROR",Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
    }
}
