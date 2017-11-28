package mx.edu.ittepic.dadm_proyectofinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.MalformedURLException;
import java.net.URL;

public class Inicio extends AppCompatActivity implements AsyncResponse {
    Button registro, inicio;
    EditText usuario,contraseña;
    String user,password;
    ConexionWeb conexionWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_inicio);

        registro = (Button) findViewById(R.id.registro);
        inicio = (Button) findViewById(R.id.inicio);

        usuario = (EditText) findViewById(R.id.usuario);
        contraseña = (EditText) findViewById(R.id.contrasena);

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user=usuario.getText().toString();
                password=contraseña.getText().toString();
                if (!user.equals("") && !password.equals("")){

                    try{
                        conexionWeb = new ConexionWeb(Inicio.this);
                        conexionWeb.agregarVariables("correo",user);
                        conexionWeb.agregarVariables("contrasena",password);
                        URL direccion = new URL("http://carolina.x10host.com/index.php/Sistema/inicio_sesion_android");
                        conexionWeb.execute(direccion);
                    }catch (MalformedURLException e){
                        Toast.makeText(Inicio.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(Inicio.this);
                    alerta.setTitle("ATENCION")
                            .setMessage("HAY CAMPOS VACIOS")
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
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Ventanaregistro = new Intent(Inicio.this,registro.class);
                startActivity(Ventanaregistro);

            }
        });
    }
    @Override
    public void procesarRespuesta(String r) {
        if(r.equals("error-inicio")){
            AlertDialog.Builder alerta = new AlertDialog.Builder(Inicio.this);
            alerta.setTitle("ATENCION")
                    .setMessage("ERROR DE INICIO DE SESION")
                    .setIcon(R.drawable.ic_error_black_24dp)
                    .setPositiveButton("ENTENDIDO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    }).show();
        }else
        {
            if(r.equals("usuario-desactivado")){
                AlertDialog.Builder alerta = new AlertDialog.Builder(Inicio.this);
                alerta.setTitle("ATENCION")
                        .setMessage("SU USUARIO ESTA ACTUALMENTE DESACTIVADO")
                        .setIcon(R.drawable.ic_error_black_24dp)
                        .setPositiveButton("ENTENDIDO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        }).show();
            }else {
                try{
                    JSONArray arrayjson = new JSONArray(r);
                    for(int i = 0; i < arrayjson.length(); i++){
                        SharedPreferences.Editor editor = getSharedPreferences("INFO_USUARIO", MODE_PRIVATE).edit();
                        editor.putString("nombre",arrayjson.getJSONObject(i).getString("nombre"));
                        editor.putString("apellidos",arrayjson.getJSONObject(i).getString("apellidos"));
                        editor.putInt("idusuarios",Integer.parseInt(arrayjson.getJSONObject(i).getString("idusuarios")));
                        editor.apply();
                        Intent Ventanaregistro = new Intent(Inicio.this,MainActivity.class);
                        startActivity(Ventanaregistro);
                    }
                }catch (JSONException e){
                    Toast.makeText(Inicio.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
