package mx.edu.ittepic.dadm_u4_ejercicio4;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView etiqueta;
    Button ciclo,timer,hilo;
    Thread thread;
    int contador=1;
    boolean ejecutar=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etiqueta =(TextView)findViewById(R.id.etiqueta);

        ciclo=(Button)findViewById(R.id.ciclo);
        timer=(Button)findViewById(R.id.timer);
        hilo=(Button)findViewById(R.id.hilo);

        ciclo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i=1;i<=100000;i++){
                    etiqueta.setText(""+i);
                }
            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutar=false;
            }
        });

        thread = new Thread(){

            public void run() {
                //el metodo run se ejecuta en segundo plano y se ejecuta 1 sola vez
                while (ejecutar) {

                    runOnUiThread(new Runnable() {
                        //EJECUTAR HILO EN INTERFAZ DE USUARIO
                        @Override
                        public void run() {
                            etiqueta.setText("" + contador);
                        }
                    });
                    try {
                        sleep(500);
                    }catch (InterruptedException e){

                    }
                    contador++;
                }
            }
        };
        hilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                thread.start();
            }catch (Exception e){
                    Toast.makeText(MainActivity.this,"ERROR YA QUEMASTE TU CARTUCHO",Toast.LENGTH_SHORT).show();
            }
            }
        });

    }
}
