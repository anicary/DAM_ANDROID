package mx.edu.ittepic.dadm_proyectofinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class Inicio extends AppCompatActivity {
    Button registro, inicio;
    EditText usuario,contraseña;
    String user,password;

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
                    Intent Ventanaregistro = new Intent(Inicio.this,MainActivity.class);
                    startActivity(Ventanaregistro);

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
}
