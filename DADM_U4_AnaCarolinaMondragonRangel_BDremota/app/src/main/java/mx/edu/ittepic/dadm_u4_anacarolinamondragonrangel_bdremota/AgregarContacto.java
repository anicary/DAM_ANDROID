package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_bdremota;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.MalformedURLException;
import java.net.URL;

public class AgregarContacto extends AppCompatActivity {
    EditText nombre,domicilio,telefono,correo;
    Button agregar;
    ConexionRemota con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto);
        agregar =(Button)findViewById(R.id.btnagregar);
        nombre =(EditText)findViewById(R.id.nombre);
        domicilio =(EditText)findViewById(R.id.dom);
        telefono =(EditText)findViewById(R.id.tel);
        correo =(EditText)findViewById(R.id.correo);
        con= new ConexionRemota(AgregarContacto.this);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    con.agregarValores("nombre",nombre.getText().toString());
                    con.agregarValores("domicilio",domicilio.getText().toString());
                    con.agregarValores("telefono",telefono.getText().toString());
                    con.agregarValores("correo",correo.getText().toString());
                    con.execute(new URL(""));
                }catch (MalformedURLException e){
                    AlertDialog.Builder error=new AlertDialog.Builder(AgregarContacto.this);
                    error.setMessage(""+e.getMessage()) .setTitle("Error").show();
                }
            }
        });
    }
}
