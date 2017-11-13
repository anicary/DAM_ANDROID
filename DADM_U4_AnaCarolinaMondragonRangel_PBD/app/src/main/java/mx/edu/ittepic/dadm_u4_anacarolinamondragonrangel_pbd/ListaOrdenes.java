package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_pbd;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaOrdenes extends AppCompatActivity {
Button botonAgregar;
    ADorden adater;
    ArrayList<Cliente> elemento;
    BD conexion;
    ArrayList<Cliente> elementos;
    ListView Menu_lista;
    AlertDialog.Builder alertaBorrar;
    int idClienteborrar,poslista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ordenes);
        setTitle("ORDENES DE REPARACION");
        conexion = new BD(ListaOrdenes.this, "reparacionCelular", null, 1);
        botonAgregar=(Button)findViewById(R.id.btnCrear);
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListaOrdenes.this,AgregarReparacion.class);
                startActivity(intent);
            }
        });
        Menu_lista = (ListView) findViewById(R.id.idlistareparacion);
        elemento = getElemento();
        adater = new ADorden(this, elemento, new ADorden.botonClick() {
            @Override
            public void onBtnClick(int position) {

            }
        }, new ADorden.botonClick() {
            @Override
            public void onBtnClick(int position) {

            }
        }, new ADorden.botonClick() {
            @Override
            public void onBtnClick(int position) {

            }
        });
        Menu_lista.setAdapter(adater);
    }
    private ArrayList<Cliente> getElemento() {
        SQLiteDatabase base = conexion.getReadableDatabase();
        Cursor c = base.rawQuery("SELECT * FROM orden_reparacion", null);
        elementos = new ArrayList<Cliente>();
        while (c.moveToNext()) {
            elementos.add(new Cliente(Integer.parseInt(c.getString(0)), c.getString(1), c.getString(2), c.getString(3)));
        }
        return elementos;
    }
}
