package mx.edu.ittepic.practicau1_5_activitys_anacarolinamondragonrangel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Ecuacion extends AppCompatActivity {

    Button btn;
    EditText edit1,edit2;
    TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecuacion);

        edit1=(EditText)findViewById(R.id.num1);
        edit2=(EditText)findViewById(R.id.num2);
        btn=(Button)findViewById(R.id.sumar);
        result=(TextView)findViewById(R.id.resultado);
    }
    public void sumar (View v){

        float num1,num2;


        try {
            num1 = Integer.parseInt(edit1.getText().toString());
            num2 = Integer.parseInt(edit2.getText().toString());

            result.setText("resultado: " +(num1+num2));

        }catch (NumberFormatException e){}
    }
public void cerrar (View v){
    finish();
}
}
