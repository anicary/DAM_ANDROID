package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_pbd;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarCliente extends AppCompatActivity {
    int idcliente = 0;
    BD db;
    Button botonActualizar;
    EditText nombre, dom, col;
    String name, domicilio, colonia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cliente);
        idcliente = (getIntent().getExtras().getInt("idcliente"));
        db = new BD(EditarCliente.this, "reparacionCelular", null, 1);
        nombre = (EditText) findViewById(R.id.nombreeditar);
        dom = (EditText) findViewById(R.id.domicilioeditar);
        col = (EditText) findViewById(R.id.coloniaeditar);
        nombre.setText(getIntent().getExtras().getString("nombre"));
        setTitle("EDITAR CLIENTE- " + getIntent().getExtras().getString("nombre"));
        dom.setText(getIntent().getExtras().getString("domicilio"));
        col.setText(getIntent().getExtras().getString("colonia"));
        botonActualizar = (Button) findViewById(R.id.botonEditarCliente);
        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nombre.getText().toString();
                domicilio = dom.getText().toString();
                colonia = col.getText().toString();
                try {
                    SQLiteDatabase base = db.getWritableDatabase();
                    String query1 = "UPDATE cliente SET nombre='NOMBRENUEVO',domicilio='DOMICILIONUEVO', colonia='COLONIANUEVA' where idcliente=" + idcliente + ";";
                    query1 = query1.replace("NOMBRENUEVO", name);
                    query1 = query1.replace("DOMICILIONUEVO", domicilio);
                    query1 = query1.replace("COLONIANUEVA", colonia);
                    base.execSQL(query1);
                    Intent intent = new Intent(EditarCliente.this, ListaClientes.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } catch (SQLException e) {
                    Toast.makeText(EditarCliente.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menueditarcliente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.editVolver:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
