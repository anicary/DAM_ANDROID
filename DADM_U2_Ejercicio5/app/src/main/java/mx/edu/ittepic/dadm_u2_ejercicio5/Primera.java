package mx.edu.ittepic.dadm_u2_ejercicio5;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Primera extends AppCompatActivity {
    EditText edit1;
    ListView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera);

        edit1 = (EditText) findViewById(R.id.edit1);
        menu = (ListView) findViewById(R.id.listamenu);

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                switch (posicion) {
                    case 0:
                        abrirOperaciones();
                        break;
                    case 1:
                        abrirCobro();
                        break;
                    case 2:
                        acercade();
                        break;
                    case 3:
                        salir();
                        break;
                }
            }
        });
    }

    public void abrirOperaciones(){
        Intent abrirOperaciones = new Intent(Primera.this,operaciones.class);
        abrirOperaciones.putExtra("Cantidad",edit1.getText().toString());  // creamos un putExtra y lo enviamos al activity
        startActivity(abrirOperaciones);

    }
    public void abrirCobro(){
        Intent abrirCobro = new Intent(Primera.this,cobro.class);
        startActivity(abrirCobro);
        }
    public void acercade(){
        Toast.makeText(Primera.this,"PRUEBA DE EXAMEN",Toast.LENGTH_LONG).show();
         }
    public void salir(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("ATENCION")
                .setMessage("DESEAS CERRAR LA APLICACION")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .show();
         }

    }

