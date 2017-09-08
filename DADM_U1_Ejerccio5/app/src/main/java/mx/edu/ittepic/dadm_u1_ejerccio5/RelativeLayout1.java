package mx.edu.ittepic.dadm_u1_ejerccio5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RelativeLayout1 extends AppCompatActivity {
    int Pdolar, Peuro, Pprecio;
    EditText dolar, euro, precio;
    Button convertir, asignar;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layout1);

        convertir = (Button) findViewById(R.id.btn1);
        asignar = (Button) findViewById(R.id.btn2);

        dolar = (EditText) findViewById(R.id.canditaddolares);
        euro = (EditText) findViewById(R.id.cantidadeuros);
        precio = (EditText) findViewById(R.id.cantidadprecio);

        resultado = (TextView) findViewById(R.id.resultado);

    }

    public void asignar(View v) {
        try {
            Pprecio = Integer.parseInt(precio.getText().toString());

        } catch (NumberFormatException e) {
        }
    }

    public void convertir(View v) {

        if (Pprecio != 0) {

            if (dolar.getText().toString().trim().length() == 0) {

                try {

                    Peuro = Integer.parseInt(euro.getText().toString());
                    resultado.setText("Resultado:" + ((Pprecio * Peuro) * 1.20));

                } catch (NumberFormatException e) {
                    Toast.makeText(this, "error1", Toast.LENGTH_SHORT).show();
                }

            } else {
                try {
                    Pdolar = Integer.parseInt(dolar.getText().toString());
                    resultado.setText("Resultado:" + (Pprecio * Pdolar));
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "error2", Toast.LENGTH_SHORT).show();
                }
            }
            } else{
                Toast.makeText(this, "error no se asigno ningun valor al peso", Toast.LENGTH_SHORT).show();
            }
        }

    }


