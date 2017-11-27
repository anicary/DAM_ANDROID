package mx.edu.ittepic.dadm_u5_ejercicio1;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements AsyncResponse{
    EditText modelo,fabricante,descripcion,precio,ram;
    Button insertar,consultar;
    TextView etiqueta;
    ConexionWeb conexionWeb;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modelo = (EditText)findViewById(R.id.modelo);
        fabricante = (EditText)findViewById(R.id.fabricante);
        descripcion = (EditText)findViewById(R.id.descripcion);
        precio = (EditText)findViewById(R.id.precio);
        ram = (EditText)findViewById(R.id.ram);

        insertar = (Button) findViewById(R.id.insertar);
        consultar = (Button)findViewById(R.id.consultar);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    conexionWeb = new ConexionWeb(MainActivity.this);
                    conexionWeb.agregarVariables("modelo",modelo.getText().toString());
                    conexionWeb.agregarVariables("fabricante",fabricante.getText().toString());
                    conexionWeb.agregarVariables("descripcion",descripcion.getText().toString());
                    conexionWeb.agregarVariables("precio",precio.getText().toString());
                    conexionWeb.agregarVariables("ram",ram.getText().toString());
                    dialog = ProgressDialog.show(MainActivity.this,"ATENCION","CONECTANDO...");
                    URL direccion = new URL("https://anicary.000webhostapp.com/movilesinsertar.php");
                    conexionWeb.execute(direccion);
                }catch (MalformedURLException e){
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();

                }
            }
        });
        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pedirPalabraClave();
            }
        });
    }
    public void pedirPalabraClave(){
        final EditText clave = new EditText(this);
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        clave.setHint("MODELO A CONSULTAR");
        alerta.setTitle("consulta")
                .setMessage("escriba solo el modelo")
                .setView(clave)
                .setPositiveButton("BUSCAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        realizarConsulta(clave.getText().toString());
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                }).show();
    }

    @Override
    public void procesarRespuesta(String r) {
        dialog.dismiss();
        etiqueta.setText(r);
    }
   private void realizarConsulta(String clave) {
       try {
           conexionWeb = new ConexionWeb(this);
           conexionWeb.agregarVariables("modelo", clave);
           dialog = ProgressDialog.show(this, "ATENCION", "CONSULTANDO...");
           URL direcciopn = new URL("https://anicary.000webhostapp.com/movilesconsultar.php");
           conexionWeb.execute(direcciopn);

       } catch (MalformedURLException e) {
           Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
       }
   }
       public boolean onCreateOptionsMenu(Menu m){
           getMenuInflater().inflate(R.menu.menuopci,m);
           return true;
       }


       public boolean onOptionsItemSelected(MenuItem mi){
           switch (mi.getItemId()){
               case R.id.insertar:
                   Intent insert = new Intent(this,actualizar.class);
                   startActivity(insert);
                   break;
               case R.id.consultar:
                   Intent consult = new Intent(this,eliminar.class);
                   startActivity(consult);
                   break;
           }
           return true;
       }

   }

