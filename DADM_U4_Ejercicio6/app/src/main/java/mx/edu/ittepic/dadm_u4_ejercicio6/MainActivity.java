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
    Thread thread;
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
                                           // jugando=false;
                                            pos=0;
                                            pos2=0;
                                            cpu=true;
                                            return;
                                        }
                                        pos++;
                                        //Toast.makeText(MainActivity.this,"EL JUGADOR GANA",Toast.LENGTH_SHORT).show();
                                    }
                                    if(cpu)
                                    {

                                        if(pos2==0)
                                        {
                                            //Toast.makeText(MainActivity.this,"CPU",Toast.LENGTH_SHORT).show();
                                            dadoj1num=jugador.nextInt(6)+1;
                                            dado1.setText(""+dadoj1num);
                                        }
                                        if(pos2==1)
                                        {
                                           // Toast.makeText(MainActivity.this,"CPU",Toast.LENGTH_SHORT).show();
                                            dadoj2num=jugador.nextInt(6)+1;
                                            sumcpu+=dadoj1num+dadoj2num;
                                            dado2.setText(""+dadoj2num);

                                        }
                                        if(pos2==2)
                                        {
                                            total1.setText(""+sumcpu);
                                            turno.setText("TURNO JUGADOR");
                                           // p=true;
                                          //  jugando=false;
                                            pos=0;
                                            pos2=0;
                                            cpu=false;
                                            tirar.setEnabled(true);
                                            return;
                                        }
                                        pos2++;
                                        //Toast.makeText(MainActivity.this,"EL JUGADOR GANA",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                        });
                        Thread.sleep(1000);
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
                if(turnojugada<3)
                {
                    /*
                    tirar.setEnabled(false);
                    dadoj1num=jugador.nextInt(6)+1;
                    dadoj2num=jugador.nextInt(6)+1;
                    sumj+=dadoj1num+dadoj2num;
                    dadoJ1.setText(""+dadoj1num);
                    dadoJ2.setText(""+dadoj2num);
                    total2.setText(""+sumj);
                    turno.setText("TURNO CPU");
                    cpu();
                    turnojugada++;*/
                }
            }
        });

    }
    public void cpu()
    {
      /*
        if(turnojugada>=2)
        {
            if(sumj>sumcpu)
            {
                Toast.makeText(MainActivity.this,"EL JUGADOR GANA",Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(MainActivity.this,"LA CPU GANA",Toast.LENGTH_SHORT).show();
            }
            sumj=0;
            dadoJ1.setText("");
            dadoJ2.setText("");
            total2.setText("");
            sumcpu=0;
            dado1.setText("");
            dado2.setText("");
            total1.setText("");


            turnojugada=0;
        }
        System.out.println(""+turnojugada);*/
    }

}
