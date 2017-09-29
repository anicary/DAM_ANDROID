package mx.edu.ittepic.dadm_u2_ejercicio5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class operaciones extends AppCompatActivity {
    EditText [] cantidad; // Se declara un array
    TextView[] cantidadtext;
    LinearLayout layin,layin2,layin3;
    ScrollView scroll;
    Button  btnsumar,btnrestar,btndivmul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operaciones);
        layin = (LinearLayout) findViewById(R.id.linearoperaciones);
        layin2= new LinearLayout(this);
        layin2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layin2.setOrientation(LinearLayout.VERTICAL);
        layin3= new LinearLayout(this);
        layin3.setGravity(Gravity.CENTER);
       /* layin3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));*/
        layin3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layin3.setOrientation(LinearLayout.HORIZONTAL);
        scroll= new ScrollView(this);
        scroll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1000));
        LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        parametros.setMargins(80, 0, 80, 0);
        cantidad= new EditText[Integer.parseInt(getIntent().getStringExtra("Cantidad"))]; //llenamos el arreglo con la cantidad
        cantidadtext= new TextView[Integer.parseInt(getIntent().getStringExtra("Cantidad"))];
        for(int i=0; i<cantidad.length; i++){
            cantidad[i] = new EditText(this);
            cantidad[i].setId(i);
            cantidad[i].setHint("Escribe una cantidad");
            cantidadtext[i] = new TextView(this);
            cantidadtext[i].setText("Cantidad "+(i+1));
            cantidadtext[i].setLayoutParams(parametros);
            cantidad[i].setLayoutParams(parametros);
            layin2.addView(cantidadtext[i]);
            layin2.addView(cantidad[i]);
        }
        scroll.addView(layin2);
        layin.addView(scroll);
        btnsumar=new Button(this);
        btnrestar=new Button(this);
        btndivmul=new Button(this);
        btnsumar.setText("SUMAR");
        btnrestar.setText("RESTAR");
        btndivmul.setText("MULTIPLICAR/DIVIDIR");
        btnsumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int suma=0;
                for (int i=0;i<cantidad.length;i++)
                {
                    suma+=Integer.parseInt(cantidad[i].getText().toString());
                }
                Toast.makeText(getApplicationContext(),"Suma:"+suma,Toast.LENGTH_LONG).show();
            }
        });
        btnrestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int resta=0;
                for (int i=0;i<cantidad.length;i++)
                {
                    resta-=Integer.parseInt(cantidad[i].getText().toString());
                }
                Toast.makeText(getApplicationContext(),"resta:"+resta,Toast.LENGTH_LONG).show();
            }
        });
        btndivmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int multi=1;
                for (int i=0;i<cantidad.length;i++)
                {
                    multi=multi*Integer.parseInt(cantidad[i].getText().toString());
                }
                Toast.makeText(getApplicationContext(),"multi:"+multi,Toast.LENGTH_LONG).show();
            }
        });
        btndivmul.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                float divi=1;
                for (int i=0;i<cantidad.length;i++)
                {
                    divi=divi/Float.parseFloat(cantidad[i].getText().toString());
                }
                Toast.makeText(getApplicationContext(),"multi:"+divi,Toast.LENGTH_LONG).show();
                return true;
            }
        });
        layin3.addView(btnsumar);
        layin3.addView(btnrestar);
        layin.addView(layin3);
        layin.addView(btndivmul);
    }
}
