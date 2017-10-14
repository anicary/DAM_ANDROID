package mx.edu.ittepic.dadm_u2_ejercicio4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView t1,t2,t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        t1=(TextView)findViewById(R.id.valorcadena);
        t2=(TextView)findViewById(R.id.valorentero);
        t3=(TextView)findViewById(R.id.valorbooleano);

        /*PARA RECUPERAR EL VALOR Y MOSTRARLO*/
        t1.setText(getIntent().getExtras().getString("cadena"));

        int x = getIntent().getExtras().getInt("paquirris");
        x=x*3;

        t2.setText("EL VALOR * 3 ES: "+x);

        boolean y =getIntent().getExtras().getBoolean("despensa");
        if (y ==true){
            t3.setText("PASASTE UN VALOR VERDADERO");
        }else{
            t3.setText("PASASTE UN VALOR FALSO");
        }


    }
}
