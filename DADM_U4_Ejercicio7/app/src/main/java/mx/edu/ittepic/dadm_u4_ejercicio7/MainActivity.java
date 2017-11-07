package mx.edu.ittepic.dadm_u4_ejercicio7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText contador;
    Button iniciar,pausar,despausar,terminar;
    Thread thread,hilo;
    int cont=1,pau;
    boolean ejecutar=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contador =(EditText)findViewById(R.id.contador);

        iniciar =(Button)findViewById(R.id.iniciar);
        pausar =(Button)findViewById(R.id.pausar);
        despausar =(Button)findViewById(R.id.despausar);
        terminar =(Button)findViewById(R.id.terminar);

        thread = new Thread(){

            public void run() {
                //el metodo run se ejecuta en segundo plano y se ejecuta 1 sola vez
                while (ejecutar) {

                    runOnUiThread(new Runnable() {
                        //EJECUTAR HILO EN INTERFAZ DE USUARIO
                        @Override
                        public void run() {
                            contador.setText(""+cont);
                        }
                    });
                    try {
                        sleep(300);
                    }catch (InterruptedException e){

                    }
                    cont++;
                }
            }
        };
        hilo = new Thread(){

            public void run() {
                //el metodo run se ejecuta en segundo plano y se ejecuta 1 sola vez
                while (ejecutar) {

                    runOnUiThread(new Runnable() {
                        //EJECUTAR HILO EN INTERFAZ DE USUARIO
                        @Override
                        public void run() {
                            contador.setText(""+cont);
                        }
                    });
                    try {
                        sleep(300);
                    }catch (InterruptedException e){

                    }
                    cont++;
                }
            }
        };
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    thread.start();

                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"ERROR YA QUEMASTE TU CARTUCHO",Toast.LENGTH_SHORT).show();
                }
            }
        });
        pausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutar =false;
                if (ejecutar=false){
                    pau+=cont;
                }
            }
        });

        despausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutar=true;
                try{
                    hilo.start();

                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"ERROR YA QUEMASTE TU CARTUCHO",Toast.LENGTH_SHORT).show();
                }

            }
        });

        terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
