package mx.edu.ittepic.practicau1_5_activitys_anacarolinamondragonrangel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStreamWriter;

public class Guardar extends AppCompatActivity {

    EditText edit1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar);

        edit1=(EditText)findViewById(R.id.nombre);
    }
    public void guardar (View v) {
        String nombreArchivo = "Palabras.txt";
        try {
            OutputStreamWriter Manejador = new OutputStreamWriter(openFileOutput(nombreArchivo, MODE_PRIVATE));
            Manejador.write(edit1.getText().toString());
            Manejador.flush();
            Manejador.close();
            Toast.makeText(this, "ARCHIVO GUARDADO", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
                Toast.makeText(this, "ERROR AL GUARDAR", Toast.LENGTH_LONG).show();

        }
    }
    public void cerrar (View v){
        finish();
    }
}
