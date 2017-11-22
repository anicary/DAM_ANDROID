package mx.edu.ittepic.dadm_u4_ejercicio2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista =(ListView)findViewById(R.id.lista);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int operacion, long l) {

                switch (operacion){

                    case 0:
                        Intent opcion = new Intent(MainActivity.this,Insertar.class);
                        startActivity(opcion);
                        break;
                    case 1:
                        Intent opcion1 = new Intent(MainActivity.this,Consultar.class);
                       startActivity(opcion1);
                        break;
                    case 2:
                       Intent opcion2 = new Intent(MainActivity.this,Actualizar.class);
                       startActivity(opcion2);
                        break;
                    case 3:
                        Intent opcion3 = new Intent(MainActivity.this,Eliminar.class);
                        startActivity(opcion3);
                        break;
                    case 4:
                        finish();
                        break;
                }

            }
        });
    }
}
