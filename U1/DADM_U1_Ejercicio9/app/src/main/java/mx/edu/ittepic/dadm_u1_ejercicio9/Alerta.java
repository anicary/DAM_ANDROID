package mx.edu.ittepic.dadm_u1_ejercicio9;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Alerta extends AppCompatActivity {
    EditText campo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerta);
        //edit text de puro codigo
        campo = new EditText(this);
        campo.setHint("Escribe tu nombre");
    }
    public void mostrarAlerta(View v){

        final AlertDialog.Builder cuadroDialogo = new AlertDialog.Builder(this);

        cuadroDialogo.setTitle("ATENCION!!");
        cuadroDialogo.setMessage("ERES EL GANADOR 9999999999999");
        //AGREGAR CAMPO DE TEXTO QUE SE CREO DENTRO DEL CUADRO DE DIALOGO
        cuadroDialogo.setView(campo);

        cuadroDialogo.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i){
                //PARA QUE MUESTRE LO QUE INGRESES EN EL EDIT TEXT
                Toast.makeText(Alerta.this,"FELICIDADES "+campo.getText().toString(),Toast.LENGTH_LONG).show();
                campo.setText("");
                dialog.dismiss();
            }
        });

        cuadroDialogo.setNegativeButton("RECHAZAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();
            }
        });

        cuadroDialogo.show();
    }
}
