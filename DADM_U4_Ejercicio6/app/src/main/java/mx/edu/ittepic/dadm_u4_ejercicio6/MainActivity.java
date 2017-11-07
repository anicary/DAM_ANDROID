package mx.edu.ittepic.dadm_u4_ejercicio6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        tirar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(turnojugada<3)
                {
                    tirar.setEnabled(false);
                    dadoj1num=jugador.nextInt(6)+1;
                    dadoj2num=jugador.nextInt(6)+1;
                    sumj+=dadoj1num+dadoj2num;
                    dadoJ1.setText(""+dadoj1num);
                    dadoJ2.setText(""+dadoj2num);
                    total2.setText(""+sumj);
                    turno.setText("TURNO CPU");
                    cpu();
                    turnojugada++;
                }
            }
        });

    }
    public void cpu()
    {
        dadoj1num=jugador.nextInt(6)+1;
        dadoj2num=jugador.nextInt(6)+1;
        sumcpu+=dadoj1num+dadoj2num;
        dado1.setText(""+dadoj1num);
        dado2.setText(""+dadoj2num);
        total1.setText(""+sumcpu);
        tirar.setEnabled(true);
        turno.setText("TURNO JUGADOR");
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
        System.out.println(""+turnojugada);
    }

}
