package mx.edu.ittepic.practicau1_5_activitys_anacarolinamondragonrangel;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    ListaAdaptador adater;
    ArrayList<Lista> elemento;
    ListView Menu_lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Menu_lista=(ListView)findViewById(R.id.lista);
        Menu_lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int operacion, long l) {

                switch (operacion){

                    case 0:
                        Intent opcion = new Intent(MenuActivity.this,Ecuacion.class);
                        startActivity(opcion);
                        break;
                    case 1:
                        Intent opcion1 = new Intent(MenuActivity.this,Concatenar.class);
                        startActivity(opcion1);
                        break;
                    case 2:
                        Intent opcion2 = new Intent(MenuActivity.this,Guardar.class);
                        startActivity(opcion2);
                        break;
                    case 3:
                        Intent opcion3 = new Intent(MenuActivity.this,Recuperar.class);
                        startActivity(opcion3);
                        break;
                }

            }
        });
        elemento = getElemento();
        adater = new ListaAdaptador(this, elemento);
        Menu_lista.setAdapter(adater);
    }
    private ArrayList<Lista> getElemento() {
        ArrayList<Lista> elementos = new ArrayList<Lista>();
        elementos.add(new Lista(1, "Ecuaciones", "","@drawable/ecuaciones"));
        elementos.add(new Lista(2, "Concatenar", "","@drawable/concatenar"));
        elementos.add(new Lista(3, "Guardar en Archivo", "","@drawable/guardar"));
        elementos.add(new Lista(4, "Recuperar de Archivo", "","@drawable/recuperar"));
        return elementos;
    }

}
