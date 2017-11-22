package mx.edu.ittepic.dadm_u4_ejercicio7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText contador;
    Button iniciar, pausar, despausar, terminar;
    Thread thread;
    int cont = 1;
    boolean ejecutar = false, pausa = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contador = (EditText) findViewById(R.id.contador);
        iniciar = (Button) findViewById(R.id.iniciar);
        pausar = (Button) findViewById(R.id.pausar);
        despausar = (Button) findViewById(R.id.despausar);
        terminar = (Button) findViewById(R.id.terminar);
        thread = new Thread() {
            public void run() {
                while (true) {
                    if (ejecutar) {
                        if (pausa) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    contador.setText("" + cont);
                                }
                            });
                            try {
                                sleep(300);
                            } catch (InterruptedException e) {

                            }
                            cont++;
                        }
                    }
                }
            }
        };
        try {
            thread.start();

        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "ERROR YA QUEMASTE TU CARTUCHO", Toast.LENGTH_SHORT).show();
        }
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ejecutar) {
                    ejecutar = true;
                }
            }
        });
        pausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pausa = false;
            }
        });
        despausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pausa = true;

            }
        });
        terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutar = false;
                pausa=true;
                cont = 0;
                contador.setText("");
            }
        });
    }
}
