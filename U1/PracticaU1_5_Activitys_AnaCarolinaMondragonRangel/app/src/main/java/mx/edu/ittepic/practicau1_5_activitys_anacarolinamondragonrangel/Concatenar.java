package mx.edu.ittepic.practicau1_5_activitys_anacarolinamondragonrangel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Concatenar extends AppCompatActivity {
    Button btn;
    EditText edit1,edit2;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concatenar);

        edit1=(EditText)findViewById(R.id.palabra1);
        edit2=(EditText)findViewById(R.id.palabra2);
        btn=(Button)findViewById(R.id.concatenar);
        result=(TextView)findViewById(R.id.resultado);
    }
    public void concatenar (View v){

        String palabra1,palabra2;

            palabra1 = (edit1.getText().toString());
            palabra2 = (edit2.getText().toString());

            result.setText("resultado: " +palabra1+palabra2);
    }
    public void cerrar (View v){
        finish();
    }
}
