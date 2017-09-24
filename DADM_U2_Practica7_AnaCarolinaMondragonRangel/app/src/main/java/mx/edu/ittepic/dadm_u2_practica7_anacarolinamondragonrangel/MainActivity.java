package mx.edu.ittepic.dadm_u2_practica7_anacarolinamondragonrangel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String []arreglo;
    Button mostrar;
    EditText numeroscoma;
    TextView pos;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String entrante =getIntent().getExtras().getString("arreglo");
        arreglo=entrante.split(",");/* separa un string,  dependiendo del carácter que pongas adentro*/

        mostrar=(Button) findViewById(R.id.btnmostrar);
        numeroscoma=(EditText)findViewById(R.id.edittxt1);
        pos=(TextView)findViewById(R.id.posicion);

        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer valorsacado = Integer.parseInt (numeroscoma.getText().toString());
                if(valorsacado<arreglo.length){
                    pos.setText("Posicion:"+valorsacado+":"+arreglo[valorsacado]);
                }else{
                    Toast.makeText(MainActivity.this,"POSICION INVALIDA",Toast.LENGTH_SHORT);
                }




            }
        });

    }


}
