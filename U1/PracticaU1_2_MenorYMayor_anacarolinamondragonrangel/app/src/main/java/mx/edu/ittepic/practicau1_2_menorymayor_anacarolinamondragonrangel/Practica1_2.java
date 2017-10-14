package mx.edu.ittepic.practicau1_2_menorymayor_anacarolinamondragonrangel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Practica1_2 extends AppCompatActivity {

    EditText campo1, campo2, campo3;
    Button menor, mayor, limpiar;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practica1_2);

        campo1 = (EditText) findViewById(R.id.num1);
        campo2 = (EditText) findViewById(R.id.num2);
        campo3 = (EditText) findViewById(R.id.num3);

        menor = (Button) findViewById(R.id.btn1);
        mayor = (Button) findViewById(R.id.btn2);
        limpiar = (Button) findViewById(R.id.btn3);

        resultado = (TextView) findViewById(R.id.resultado);

    }

    public void menor (View v){

        int num1, num2, num3;

        try {
            num1 = Integer.parseInt(campo1.getText().toString());
            num2 = Integer.parseInt(campo2.getText().toString());
            num3 = Integer.parseInt(campo3.getText().toString());

            if (num1 < num2 && num1 < num3) {

                resultado.setText("resultado: " + num1);
            } else {
                if (num2 < num3) {

                    resultado.setText("resultado:" + num2);
                } else {

                    resultado.setText("resultado:" + num3);
                }
            }
        }catch (NumberFormatException e){
            Toast.makeText(this,"error",Toast.LENGTH_LONG).show();
        }
    }
    public void mayor (View v){

        int num1, num2, num3;

        try {

            num1 = Integer.parseInt(campo1.getText().toString());
            num2 = Integer.parseInt(campo2.getText().toString());
            num3 = Integer.parseInt(campo3.getText().toString());

            if (num1 > num2 && num1 > num3) {

                resultado.setText("resultado: " + num1);
            } else {
                if (num2 > num3) {

                    resultado.setText("resultado:" + num2);
                } else {

                    resultado.setText("resultado:" + num3);
                }
            }
        }catch (NumberFormatException e) {
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
        }
    }

    public void borrar (View v){

        campo1.setText(" ");
        campo2.setText(" ");
        campo3.setText(" ");

        resultado.setText("resultado: ");
    }



}
