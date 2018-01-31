package mx.edu.ittepic.tdm_anacarolinamondragonrangel_calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView result;
    Button borrar,uno,dos,tres,cuatro,cinco,seis,siete,ocho,nueve,cero,sumar,restar,dividir,mult,pto,igual;
    double num1,num2;
    double res;
    String n1="";
    int cont=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.textView2);
        borrar = findViewById(R.id.Borrar);
        uno = findViewById(R.id.num1);
        dos = findViewById(R.id.num2);
        tres = findViewById(R.id.num3);
        cuatro = findViewById(R.id.num4);
        cinco = findViewById(R.id.num5);
        seis = findViewById(R.id.num6);
        siete = findViewById(R.id.num7);
        ocho = findViewById(R.id.num8);
        nueve = findViewById(R.id.num9);
        cero = findViewById(R.id.numcero);
        sumar = findViewById(R.id.suma);
        restar = findViewById(R.id.resta);
        dividir = findViewById(R.id.dividir);
        mult = findViewById(R.id.mult);
        pto = findViewById(R.id.punto);
        igual = findViewById(R.id.igual);

        cero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n1 = n1 + "0";
                result.setText(n1);
            }
        });
        uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n1 = n1 + "1";
                result.setText(n1);
            }
        });
        dos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n1 = n1 + "2";
                result.setText(n1);
            }
        });
        tres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n1 = n1 + "3";
                result.setText(n1);
            }
        });
        cuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n1 = n1 + "4";
                result.setText(n1);
            }
        });
        cinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n1 = n1 + "5";
                result.setText(n1);
            }
        });
        seis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n1 = n1 + "6";
                result.setText(n1);
            }
        });
        siete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n1 = n1 + "7";
                result.setText(n1);
            }
        });
        ocho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n1 = n1 + "8";
                result.setText(n1);
            }
        });
        nueve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n1 = n1 + "9";
                result.setText(n1);
            }
        });
        sumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    String temporal = result.getText().toString();
                    num1= Double.parseDouble(temporal);


                }catch (NumberFormatException e){

                    Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                cont=1;
                result.setText("");
                n1="";
            }
        });
        restar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    String temporal = result.getText().toString();
                    num1= Double.parseDouble(temporal);

                }catch (NumberFormatException e){

                    Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                cont=2;
                result.setText("");
                n1="";
            }
        });

        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    String temporal = result.getText().toString();
                    num1= Double.parseDouble(temporal);

                }catch (NumberFormatException e){

                    Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                cont=3;
                result.setText("");
                n1="";
            }
        });
        dividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    String temporal = result.getText().toString();
                    num1= Double.parseDouble(temporal);

                }catch (NumberFormatException e){

                    Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                cont=4;
               result.setText("");
                n1="";
            }
        });
       igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temporal = result.getText().toString();
                num2=Double.parseDouble(temporal);

                switch (cont){
                    case 1:
                        res=num1+num2;
                        break;
                    case 2:
                        res=num1-num2;
                        break;
                    case 3:
                        res=num1*num2;
                        break;
                    case 4:
                        res=num1/num2;
                        break;
                }

               result.setText(""+res);


            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("");
                num1=0;
                num2=0;
                n1="";
                res=0;
            }
        });

    }

}

