package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_pbd;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaClientes extends AppCompatActivity {
    ADcliente adater;
    ArrayList<Cliente> elemento;
    ListView Menu_lista;
    Button agregar;
    BD conexion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);
        Menu_lista=(ListView)findViewById(R.id.listaClientes);
        conexion= new BD(ListaClientes.this, "prueba1", null, 1);
        elemento = getElemento();
        agregar=(Button) findViewById(R.id.btnAgregar);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento= new Intent(ListaClientes.this,AgregarCliente.class);
                startActivity(intento);
            }
        });
        adater = new ADcliente(this, elemento);
        Menu_lista.setAdapter(adater);
    }
    private ArrayList<Cliente> getElemento() {
        SQLiteDatabase base = conexion.getReadableDatabase();
        Cursor c = base.rawQuery("SELECT * FROM cliente", null);
        ArrayList<Cliente> elementos = new ArrayList<Cliente>();
        if (c.moveToFirst() == true) {
            elementos.add(new Cliente(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2),c.getString(3)));
        }else
        {
            elementos.add(new Cliente(1,"NO EXISTEN CLIENTES","  "," "));
        }
        return elementos;
    }
}
