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

public class ListaOrdenes extends AppCompatActivity {
Button botonAgregar;
    ADcliente adater;
    ArrayList<Cliente> elemento;
    BD conexion;
    ArrayList<Cliente> elementos;
    ListView Menu_lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ordenes);
        setTitle("ORDENES DE REPARACION");
        botonAgregar=(Button)findViewById(R.id.btnCrear);
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListaOrdenes.this,AgregarReparacion.class);
                startActivity(intent);
            }
        });
        Menu_lista = (ListView) findViewById(R.id.idlistareparacion);
        conexion = new BD(ListaOrdenes.this, "reparacionCelular", null, 1);
        elemento = getElemento();
        Menu_lista.setAdapter(adater);
    }
    private ArrayList<Cliente> getElemento() {
        SQLiteDatabase base = conexion.getReadableDatabase();
        Cursor c = base.rawQuery("SELECT * FROM cliente", null);
        elementos = new ArrayList<Cliente>();
        while (c.moveToNext()) {
            elementos.add(new Cliente(Integer.parseInt(c.getString(0)), c.getString(1), c.getString(2), c.getString(3)));
        }
        return elementos;
    }
}
