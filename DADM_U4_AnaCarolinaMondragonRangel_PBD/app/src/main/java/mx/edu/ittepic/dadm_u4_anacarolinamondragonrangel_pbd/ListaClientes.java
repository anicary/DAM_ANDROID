package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_pbd;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaClientes extends AppCompatActivity {
    ADcliente adater;
    ArrayList<Cliente> elemento;
    ListView Menu_lista;
    Button agregar;
    BD conexion;
    ArrayList<Cliente> elementos;
    AlertDialog.Builder alertaBorrar;
    int idClienteborrar,poslista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);
        setTitle("Lista clientes");
        Menu_lista = (ListView) findViewById(R.id.listaClientes);
        conexion = new BD(ListaClientes.this, "reparacionCelular", null, 1);
        elemento = getElemento();
        agregar = (Button) findViewById(R.id.btnAgregar);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(ListaClientes.this, AgregarCliente.class);
                startActivity(intento);
            }
        });
        adater = new ADcliente(this, elemento, new ADcliente.botonClick() {
            @Override
            public void onBtnClick(int position) {
                Intent intento = new Intent(ListaClientes.this,EditarCliente.class);
                intento.putExtra("idcliente",elementos.get(position).getIdcliente() );
                intento.putExtra("nombre",elementos.get(position).getNombre() );
                intento.putExtra("domicilio",elementos.get(position).getDomicilio() );
                intento.putExtra("colonia",elementos.get(position).getColonia() );
                startActivity(intento);
            }
        }, new ADcliente.botonClick() {
            @Override
            public void onBtnClick(int position) {
                idClienteborrar= elementos.get(position).getIdcliente();
                poslista=position;
                alertaBorrar = new AlertDialog.Builder(ListaClientes.this);
                alertaBorrar.setIcon(R.drawable.ic_delete_forever_black_24dp)
                        .setTitle("Eliminar a este cliente")
                        .setMessage("Nombre: "+  elementos.get(position).getNombre())
                        .setPositiveButton("ELIMINAR", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    System.out.println(""+idClienteborrar);
                                    elementos.remove(poslista);
                                    Menu_lista.setAdapter(adater);
                                    SQLiteDatabase base = conexion.getWritableDatabase();
                                    String query1 = "DELETE FROM cliente  where idcliente=" + idClienteborrar + ";";
                                    base.execSQL(query1);
                                } catch (SQLException e) {
                                    Toast.makeText(ListaClientes.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                alertaBorrar.show();
            }
        });
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menulista, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.agregar_clientemenu:
                Intent intento = new Intent(ListaClientes.this, AgregarCliente.class);
                startActivity(intento);
                return true;
            case R.id.volveralmenu:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
