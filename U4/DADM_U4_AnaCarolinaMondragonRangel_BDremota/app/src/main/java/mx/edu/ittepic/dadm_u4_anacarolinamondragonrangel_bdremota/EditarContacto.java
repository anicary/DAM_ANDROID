package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_bdremota;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.MalformedURLException;
import java.net.URL;

public class EditarContacto extends AppCompatActivity {
    EditText nombre,domicilio,telefono,correo;
    Button btnagregar,borrar;
    int idpersonba=0;
    ConexionRemota con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contacto);

        nombre =(EditText)findViewById(R.id.nombre);
        domicilio =(EditText)findViewById(R.id.dom);
        telefono =(EditText)findViewById(R.id.tel);
        correo =(EditText)findViewById(R.id.correo);
        idpersonba=getIntent().getExtras().getInt("idPersona");
        nombre.setText(getIntent().getStringExtra("nombre"));
        domicilio.setText(getIntent().getStringExtra("domicilio"));
        telefono.setText(getIntent().getStringExtra("telefono"));
        correo.setText(getIntent().getStringExtra("correo"));

        con= new ConexionRemota(EditarContacto.this);

        btnagregar=(Button)findViewById(R.id.actualizar);
        borrar=(Button)findViewById(R.id.btnborrar);
        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    con.agregarValores("idPersona",""+idpersonba);
                    con.agregarValores("nombre",nombre.getText().toString());
                    con.agregarValores("domicilio",domicilio.getText().toString());
                    con.agregarValores("telefono",telefono.getText().toString());
                    con.agregarValores("correo",correo.getText().toString());
                    con.execute(new URL("http://carolina.x10host.com/editarcontactos.php"));
                    Intent intent = new Intent(EditarContacto.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }catch (MalformedURLException e){
                    AlertDialog.Builder error=new AlertDialog.Builder(EditarContacto.this);
                    error.setMessage(""+e.getMessage()).setIcon(R.drawable.ic_error_red_24dp).setTitle("Error").show();
                }
            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    con.agregarValores("idPersona",""+idpersonba);
                    con.execute(new URL("http://carolina.x10host.com/eliminarcontacto.php"));
                    Intent intent = new Intent(EditarContacto.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }catch (MalformedURLException e){
                    AlertDialog.Builder error=new AlertDialog.Builder(EditarContacto.this);
                    error.setMessage(""+e.getMessage()).setIcon(R.drawable.ic_error_red_24dp).setTitle("Error").show();
                }
            }
        });

    }
}
