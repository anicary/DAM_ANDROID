package mx.edu.ittepic.dadm_u2_practica4_anacarolinamondragonrangel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ImpuestosSAT extends AppCompatActivity {

    EditText salario,horas,subtotal,iva,total;
    Spinner opcion;
    Button realizarpago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impuestos_sat);

        salario= (EditText) findViewById(R.id.salario);
        horas=(EditText) findViewById(R.id.hrs);
        subtotal=(EditText) findViewById(R.id.subtotal);
        iva=(EditText) findViewById(R.id.iva);
        total=(EditText) findViewById(R.id.total);

        opcion=(Spinner) findViewById(R.id.tipopersona);

        realizarpago=(Button) findViewById(R.id.realizarpago);

        realizarpago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              double subresultado = 0,campo1,campo2,resultado=0,resultadototal = 0, campoiva=.15;

                try {
                    campo1 = Float.parseFloat(salario.getText().toString());
                    campo2 = Float.parseFloat(horas.getText().toString());

                if (opcion.getSelectedItemPosition() == 0) {
                    subresultado = campo1 * campo2;
                    resultado = subresultado*campoiva;
                    resultadototal =resultado+subresultado;

                }
                subtotal.setText("SUBTOTAL "+subresultado);
                    iva.setText("IVA "+campoiva);
                    total.setText("TOTAL "+resultadototal);
                }catch (NumberFormatException e) {

                }


            }
        });
    }
}
