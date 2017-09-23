package mx.edu.ittepic.dadm_u2_practica2_anacarolinamondragonrangel;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class CapturaDatos extends AppCompatActivity {
    EditText name,direccion,edad_p;
    Button btnvalidar;
    String mensajeError ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura_datos);

        name=(EditText) findViewById(R.id.nombre);
        direccion=(EditText) findViewById(R.id.domicilio);
        edad_p=(EditText) findViewById(R.id.edad);
        btnvalidar=(Button) findViewById(R.id.validar);

        btnvalidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.getText().toString().equals("")) {
                    mensajeError += "El campo nombre esta vacio \n";
                }else {
                }

                if (direccion.getText().toString().equals("")) {
                    mensajeError += "El campo direccion esta vacio \n";
                }else {
                }

                int valor1=0;
                try {
                    valor1 = Integer.parseInt(edad_p.getText().toString());
                    if (edad_p.getText().toString().equals("")) {
                        mensajeError += "El campo edad esta vacio \n";
                    }else{
                        if (valor1 < 0) {
                            mensajeError += "El campo edad es menor de 0 \n";
                        }
                    }

                } catch (NumberFormatException e) {
                    mensajeError += "El campo edad no se ingreso un numero \n";
                }
                AlertDialog.Builder alerta = new AlertDialog.Builder(CapturaDatos.this);

                if (mensajeError.length()>0){

                    alerta.setTitle("ATENCION")
                            .setMessage("FALTA LLENAR LOS SIGUIENTES CAMPOS \n"+mensajeError)
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            }).show();
                }else{
                    alerta.setTitle("ATENCION")
                            .setMessage("FELICIDADES HAS APROBADO EL CURSO")
                            .setPositiveButton("CERRAR", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                    dialogInterface.dismiss();
                                }
                            })
                            .setNegativeButton("GRACIAS", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            }).show();
                }
                mensajeError="";
            }
        });
    }
}
