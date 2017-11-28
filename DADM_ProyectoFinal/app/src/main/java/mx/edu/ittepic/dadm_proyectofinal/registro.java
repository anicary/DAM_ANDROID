package mx.edu.ittepic.dadm_proyectofinal;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.MalformedURLException;
import java.net.URL;

public class registro extends AppCompatActivity  implements AsyncResponse{
    EditText rnombre,rapellido,rcorreo,rcontrasena,rrcontrasena;
    String name,apell,mail,passw,passw2,datos="";
    Spinner sexo,estado,municipio;
    Button registrarse;
    ConexionWeb conexionWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        rnombre = (EditText)findViewById(R.id.nombreregistro);
        rapellido = (EditText)findViewById(R.id.apellidoregistro);
        rcorreo = (EditText)findViewById(R.id.correoregistro);
        rcontrasena = (EditText)findViewById(R.id.contraregistro);
        rrcontrasena = (EditText)findViewById(R.id.rcontraregistro);

        registrarse = (Button)findViewById(R.id.registro);

        sexo = (Spinner)findViewById(R.id.sexoregistro);
        estado = (Spinner)findViewById(R.id.estadoregistro);
        municipio = (Spinner)findViewById(R.id.municipioregistro);

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=rnombre.getText().toString();
                apell=rapellido.getText().toString();
                mail=rcorreo.getText().toString();
                passw=rcontrasena.getText().toString();
                passw2=rrcontrasena.getText().toString();
                if (!name.equals("") || !apell.equals("") || !mail.equals("") || !passw.equals("") || !passw2.equals("")){
                    //TODO PENDIENTE
                    if (passw.equals(passw2)){
                        try{
                            conexionWeb = new ConexionWeb(registro.this);
                            conexionWeb.agregarVariables("nombre",name);
                            conexionWeb.agregarVariables("apellidos",apell);
                            conexionWeb.agregarVariables("correo",mail);
                            conexionWeb.agregarVariables("contrasena",passw);
                            URL direccion = new URL("http://carolina.x10host.com/index.php/Sistema/registro_usuario");
                            conexionWeb.execute(direccion);
                        }catch (MalformedURLException e){
                            Toast.makeText(registro.this,e.getMessage(),Toast.LENGTH_LONG).show();

                        }
                    }else{
                        datos+="LAS CONTRASEÑAS NO COINCIDEN";
                        AlertDialog.Builder alerta = new AlertDialog.Builder(registro.this);
                        alerta.setTitle("ATENCION")
                                .setMessage("LAS CONTRASEÑAS NO COINCIDEN")
                                .setIcon(R.drawable.ic_error_black_24dp)
                                .setPositiveButton("ENTENDIDO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        dialog.dismiss();
                                    }
                                }).show();
                    }

                }else{
                    if (name.equals("") || name.equals(" ")){
                        datos+="\n EL NOMBRE ESTA VACIO";
                    }
                    if (apell.equals("") || apell.equals(" ")){
                        datos+="\n El APELLIDO ESTA VACIO";
                    }
                    if (mail.equals("") || mail.equals(" ")){
                        datos+="\n El CORREO ESTA VACIO";
                    }
                    if (passw.equals("")|| passw.equals(" ")){
                        datos+="\n LA CONTRASEÑA ESTA VACIA";
                    }
                    if (passw2.equals("") || passw2.equals(" ")){
                        datos+="\n ES NECESARIO REPETIR LA CONTRASEÑA";
                    }
                   AlertDialog.Builder alerta2 = new AlertDialog.Builder(registro.this);
                    alerta2.setTitle("ATENCION")
                            .setMessage("LOS SIGUIENTES CAMPOS ESTAN VACIOS  "+datos)
                            .setIcon(R.drawable.ic_error_black_24dp)
                            .setPositiveButton("ENTENDIDO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            }
        });
    }
    @Override
    public void procesarRespuesta(String r) {
        if(r.equals("duplicado")){
            AlertDialog.Builder alerta = new AlertDialog.Builder(registro.this);
            alerta.setTitle("ATENCION")
                    .setMessage("EL CORREO YA ESTA REGISTRADO POR OTRO USUARIO.")
                    .setIcon(R.drawable.ic_error_black_24dp)
                    .setPositiveButton("ENTENDIDO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    }).show();
        }else
        {
            try{
                JSONArray arrayjson = new JSONArray(r);
                for(int i = 0; i < arrayjson.length(); i++){
                    SharedPreferences.Editor editor = getSharedPreferences("INFO_USUARIO", MODE_PRIVATE).edit();
                    editor.putString("nombre",arrayjson.getJSONObject(i).getString("nombre"));
                    editor.putString("apellidos",arrayjson.getJSONObject(i).getString("apellidos"));
                    editor.putInt("idusuarios",Integer.parseInt(arrayjson.getJSONObject(i).getString("idusuarios")));
                    editor.apply();
                    Intent Ventanaregistro = new Intent(registro.this,Inicio.class);
                    startActivity(Ventanaregistro);
                }
            }catch (JSONException e){
                Toast.makeText(registro.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

    }
}
