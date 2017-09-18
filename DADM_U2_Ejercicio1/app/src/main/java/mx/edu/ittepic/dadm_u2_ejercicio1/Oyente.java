package mx.edu.ittepic.dadm_u2_ejercicio1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Oyente extends AppCompatActivity {

    Button btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyente);

        btn1=(Button) findViewById(R.id.saludar);
        btn2 =(Button) findViewById(R.id.fecha);
        btn3 = (Button) findViewById(R.id.guardar);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Oyente.this,"PRESIONE BTN1",Toast.LENGTH_LONG).show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Oyente.this,"PRESIONE BTN2",Toast.LENGTH_LONG).show();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Oyente.this,"PRESIONE BTN3",Toast.LENGTH_LONG).show();
            }
        });
    }
}
