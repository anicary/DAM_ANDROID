package mx.edu.ittepic.practicau1_5_activitys_anacarolinamondragonrangel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        elemento = getElemento();
        adater = new ListaAdaptador(this, elemento);
        Menu_lista.setAdapter(adater);
    }
    private ArrayList<Lista> getElemento() {
        ArrayList<Lista> elementos = new ArrayList<Lista>();
        elementos.add(new Lista(1, "Ecuaciones", "","@drawable/ic_android_black_24dp"));
        elementos.add(new Lista(2, "Concatenar", "","@drawable/ic_android_black_24dp"));
        elementos.add(new Lista(3, "Guardar en Archivo", "","@drawable/ic_android_black_24dp"));
        elementos.add(new Lista(4, "Recuperar de Archivo", "","@drawable/ic_android_black_24dp"));
        return elementos;
    }

}
