package mx.edu.ittepic.dadm_u2_ejercicio6;

import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LinearLayout layin,layoutBtn;
    TextView textoImag;
    ImageView img;
    Switch sw1;
    Button anterior,siguiente;
    CountDownTimer reloj;
    int [] imagenes ={R.drawable.dona, R.drawable.burger,R.drawable.taco,R.drawable.dogo,
            R.drawable.camaron, R.drawable.pizza,R.drawable.papas,R.drawable.chela,
            R.drawable.huevito,R.drawable.pan};
    String[] texto ={"ROSQUILLA","HAMBURGUESA","TAQUITOS","HOT DOG","CAMARON","PIZZA","PAPAS FRITAS","CERVEZA","HUEVO","CUERNOS"};
    int posicion=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layin =(LinearLayout)findViewById(R.id.layout1);

        layoutBtn = new LinearLayout(this);
        layoutBtn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layoutBtn.setOrientation(LinearLayout.HORIZONTAL);
        layoutBtn.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        parametros.setMargins(80,20,80,0);

        textoImag = new TextView(this);
        textoImag.setPadding(0,30,0,0);
        textoImag.setText("ROSQUILLAS");
        textoImag.setTextSize(30);
        textoImag.setGravity(Gravity.CENTER);

        img = new ImageView(this);
        img.setImageDrawable(getDrawable(imagenes[0]));

        sw1= new Switch(this);
        sw1.setGravity(Gravity.CENTER_HORIZONTAL);
        sw1.setShowText(true);
        sw1.setTextOn("ON");
        sw1.setTextOff("OFF");

        anterior = new Button(this);
        anterior.setText("ANTERIOR");
        anterior.setLayoutParams(parametros);

        siguiente = new Button(this);
        siguiente.setText("SIGUIENTE");
        siguiente.setLayoutParams(parametros);

        layoutBtn.addView(anterior);
        layoutBtn.addView(siguiente);



        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posicion == 0) {
                    posicion = imagenes.length;

                } else {
                    posicion--;
                }
                    img.setImageDrawable(getDrawable(imagenes[posicion]));
                    textoImag.setText(texto[posicion]);
                }
        });
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posicion < imagenes.length-1){
                    posicion ++;

                }else{
                posicion =0;
                }
                img.setImageDrawable(getDrawable(imagenes[posicion]));
                textoImag.setText(texto[posicion]);
            }
        });
        reloj = new CountDownTimer(50000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

            }
        };
        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (sw1.isChecked()){
                    layoutBtn.setVisibility(View.GONE);
                    reloj.start();
                }else{
                    reloj.cancel();
                    layoutBtn.setVisibility(View.VISIBLE);

                }
            }
        });
        layin.addView(textoImag);
        layin.addView(img);
        layin.addView(sw1);
        layin.addView(layoutBtn);




    }

}
