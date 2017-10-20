package mx.edu.ittepic.dadm_u3_ana_carolina_mondragon_rangel_provedorcontenido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button Contacto,Mensajes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Contacto= (Button) findViewById(R.id.button);
        Contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Contacto.class);
                startActivity(intent);
            }
        });
        Mensajes= (Button) findViewById(R.id.button2);
        Mensajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Mensajes.class);
                startActivity(intent);
            }
        });
    }
}