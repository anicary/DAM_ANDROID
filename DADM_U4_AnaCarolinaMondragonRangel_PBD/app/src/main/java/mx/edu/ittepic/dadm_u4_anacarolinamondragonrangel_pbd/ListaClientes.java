package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_pbd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaClientes extends AppCompatActivity {
    ADcliente adater;
    ArrayList<Cliente> elemento;
    ListView Menu_lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);
        Menu_lista=(ListView)findViewById(R.id.listaClientes);
        elemento = getElemento();
        adater = new ADcliente(this, elemento);

        Menu_lista.setAdapter(adater);
    }
    private ArrayList<Cliente> getElemento() {
        ArrayList<Cliente> elementos = new ArrayList<Cliente>();
        elementos.add(new Cliente(1,"TEST","TEST ","test"));
       /* for(int i=0;i<ProductoNombre.length;i++)
        {
            elementos.add(new Producto(i, ProductoNombre[i], ProductoPeso[i],ProductoCosto[i], idProducto[i],"@drawable/imagendulce"));
        }*/
        return elementos;
    }
}
