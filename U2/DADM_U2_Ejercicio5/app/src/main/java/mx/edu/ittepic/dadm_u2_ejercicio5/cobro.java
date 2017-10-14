package mx.edu.ittepic.dadm_u2_ejercicio5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class cobro extends AppCompatActivity {
    int cantidad;
    TextView[]etiquetas;
    EditText[]campos;
    LinearLayout layin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cobro);

        layin = (LinearLayout) findViewById(R.id.layoutcobro);
        cantidad = getIntent().getExtras().getInt("Cantidad");

        /* PRIMERO SE CREAN LAS CELDILLAS DEL VECTOR*/
        etiquetas = new TextView[cantidad];
        campos = new EditText[cantidad];

        for (int i = 0; i < cantidad; i++) {
            etiquetas[i] = new TextView(this);
            campos[i] = new EditText(this);

            etiquetas[i].setText("Cantidad" + (i + 1) + ": ");
            campos[i].setInputType(InputType.TYPE_CLASS_NUMBER);

            layin.addView(etiquetas[i]);
            layin.addView(campos[i]);
        }
    }

    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menucobro, m);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem mcobro) {
        switch (mcobro.getItemId()) {
            case R.id.sumar:
                int total =0;
                for (int i = 0; i < cantidad; i++){
                    total += Integer.parseInt(campos[i].getText().toString());
                }
                Toast.makeText(getApplicationContext(), "Suma:" + total, Toast.LENGTH_LONG).show();
                break;

            case R.id.regresar:
                finish();

                break;
            case R.id.borrar:
                for(int i =0; i<cantidad;i++){
                    layin.removeView(etiquetas[i]);
                    layin.removeView(campos[i]);
                }
                break;
        }
        return true;
    }
}
