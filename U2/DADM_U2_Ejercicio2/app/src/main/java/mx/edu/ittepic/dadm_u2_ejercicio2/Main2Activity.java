package mx.edu.ittepic.dadm_u2_ejercicio2;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText nombre;
    Button saludar;
    CountDownTimer decrometo;
    String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nombre=(EditText) findViewById(R.id.nombre);
        saludar=(Button) findViewById(R.id.saludar);
                                /*TIEMPO TOTAL| TIEMPO LAPSO*/
        decrometo = new CountDownTimer(50000,10000) {
            @Override
            public void onTick(long l) {
                /*SE EJECUTA CADA LAPSO*/
                Toast.makeText(Main2Activity.this,"HOLA COMO ESTAS "+nom,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                /*SE EJECUTA AL FINALIZAR*/
                Toast.makeText(Main2Activity.this,"POR ULTIMA VEZ "+nom,Toast.LENGTH_SHORT).show();
                /*PARA HACERLO INFINITO
                    decrometro.start();*/
            }
        };
        saludar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nom = nombre.getText().toString();
                /*PARA INDICAR QUUE SE VA A INICIAR LA EJECUCION EN SEGUNDO PLANO*/
                decrometo.start();
            }
        });
    }
    public boolean OnCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.menusegundaventana,m);
        return true;
    }
    /*TAREA 1 MEMORIZAR LOS METODOS DE OPTIONMENU
    * TAREA 2 COMO FUNCIONA Y PARA QUE SIRVE LA CLASE COUNTDOWNTIMER DE ANDROID
    * TRAER UN EJEMPLO*/
}
