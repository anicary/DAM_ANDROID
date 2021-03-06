package mx.edu.ittepic.dadm_u4_ejercicio6;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText dado1,dado2,total1,dadoJ1,dadoJ2,total2;
    Button tirar;
    TextView turno;
    int turnojugada=0;
    Random pc,jugador;
    int dadoj1num=0,dadoj2num=0;
    int sumj=0,sumcpu=0;
    boolean jugando=false,p=false,cpu=false;
    int pos=0,pos2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pc= new Random();
        jugador= new Random();
        dado1 =(EditText)findViewById(R.id.dado1);
        dado2 =(EditText)findViewById(R.id.dado2);
        total1 =(EditText)findViewById(R.id.total1);
        dadoJ1 =(EditText)findViewById(R.id.dadoJ1);
        dadoJ2 =(EditText)findViewById(R.id.dadoJ2);
        total2 =(EditText)findViewById(R.id.total2);
        tirar =(Button)findViewById(R.id.tirar);
        turno=(TextView)findViewById(R.id.turno);
        turno.setText("TURNO JUGADOR");
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                if(jugando)
                                {
                                    if(turnojugada<3)
                                    {
                                        if(p)
                                        {
                                            tirar.setEnabled(false);
                                            if(pos==0)
                                            {
                                                dadoj1num=jugador.nextInt(6)+1;
                                                dadoJ1.setText(""+dadoj1num);
                                            }
                                            if(pos==1)
                                            {
                                                dadoj2num=jugador.nextInt(6)+1;
                                                sumj+=dadoj1num+dadoj2num;
                                                dadoJ2.setText(""+dadoj2num);

                                            }
                                            if(pos==2)
                                            {
                                                total2.setText(""+sumj);
                                                turno.setText("TURNO CPU");
                                                p=false;
                                                pos=0;
                                                pos2=0;
                                                cpu=true;

                                                return;
                                            }
                                            pos++;
                                        }
                                        if(cpu)
                                        {

                                            if(pos2==0)
                                            {
                                                dadoj1num=jugador.nextInt(6)+1;
                                                dado1.setText(""+dadoj1num);
                                            }
                                            if(pos2==1)
                                            {
                                                dadoj2num=jugador.nextInt(6)+1;
                                                sumcpu+=dadoj1num+dadoj2num;
                                                dado2.setText(""+dadoj2num);

                                            }
                                            if(pos2==2)
                                            {
                                                total1.setText(""+sumcpu);
                                                turno.setText("TURNO JUGADOR");
                                                pos=0;
                                                pos2=0;
                                                cpu=false;
                                                turnojugada++;
                                                tirar.setEnabled(true);
                                                return;
                                            }
                                            pos2++;
                                        }

                                    }else
                                    {
                                        if(sumj>sumcpu)
                                        {
                                            Toast.makeText(MainActivity.this,"EL JUGADOR GANA",Toast.LENGTH_SHORT).show();
                                        }else
                                        {
                                            Toast.makeText(MainActivity.this,"LA CPU GANA",Toast.LENGTH_SHORT).show();
                                        }
                                        jugando=false;
                                        p=false;
                                        cpu=false;
                                        tirar.setEnabled(true);
                                        sumj=0;
                                        sumcpu=0;
                                        turnojugada=0;
                                    }
                                }
                            }
                        });
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        tirar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jugando=true;
                p=true;
                if(turnojugada==0)
                {
                    dadoJ1.setText("");
                    dadoJ2.setText("");
                    dado1.setText("");
                    dado2.setText("");
                    total2.setText("");
                    total1.setText("");
                }
            }
        });
    }
}
