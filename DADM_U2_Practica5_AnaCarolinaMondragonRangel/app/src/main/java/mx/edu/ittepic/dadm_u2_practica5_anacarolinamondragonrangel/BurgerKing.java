package mx.edu.ittepic.dadm_u2_practica5_anacarolinamondragonrangel;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class BurgerKing extends AppCompatActivity {
    EditText italiana,vegana,light,refresco;
    CheckBox h1,h2,h3,r;
    Button ordenar;
    String mensajeError ="";
    int resultado=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger_king);

        italiana = (EditText) findViewById(R.id.edit1);
        vegana = (EditText) findViewById(R.id.edit2);
        light = (EditText) findViewById(R.id.edit3);
        refresco = (EditText) findViewById(R.id.edit4);

        h1 = (CheckBox) findViewById(R.id.chk1);
        h2 = (CheckBox) findViewById(R.id.chk2);
        h3 = (CheckBox) findViewById(R.id.chk3);
        r = (CheckBox) findViewById(R.id.chk4);

        ordenar = (Button) findViewById(R.id.btnordenar);

        h1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (h1.isChecked()){

                    italiana.setEnabled(true);

                }else{

                    italiana.setEnabled(false);

                }
            }
        });
        h2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (h2.isChecked()){

                    vegana.setEnabled(true);

                }else{

                    vegana.setEnabled(false);

                }
            }
        });
        h3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (h3.isChecked()){

                   light.setEnabled(true);

                }else{

                   light.setEnabled(false);

                }
            }
        });
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (r.isChecked()){

                    refresco.setEnabled(true);

                }else{

                    refresco.setEnabled(false);

                }
            }
        });
        ordenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (h1.isChecked() && italiana.getText().toString().equals("")) {
                    mensajeError += "El campo de italiana esta vacio \n";
                } else {
                    if (h1.isChecked()) {
                        int valor1 = 0;
                        valor1 = Integer.parseInt(italiana.getText().toString());
                        resultado += valor1 * 55;
                    }
                }
                if (h2.isChecked() && vegana.getText().toString().equals("")) {
                    mensajeError += "El campo de vegana esta vacio \n";
                } else {
                    if (h2.isChecked()) {
                        int valor1 = 0;
                        valor1 = Integer.parseInt(vegana.getText().toString());
                        resultado += valor1 * 150;
                    }
                }
                if (h3.isChecked() && light.getText().toString().equals("")) {
                    mensajeError += "El campo de light esta vacio \n";
                } else {
                    if (h3.isChecked()) {
                        int valor1 = 0;
                        valor1 = Integer.parseInt(italiana.getText().toString());
                        resultado += valor1 * 80;
                    }
                }
                if (r.isChecked() && refresco.getText().toString().equals("")) {
                    mensajeError += "El campo de italiana esta vacio \n";
                } else {
                    if (r.isChecked()) {
                        int valor1 = 0;
                        valor1 = Integer.parseInt(italiana.getText().toString());
                        resultado += valor1 * 24;
                    }
                }
                AlertDialog.Builder alerta = new AlertDialog.Builder(BurgerKing.this);
                if (mensajeError.length() > 0) {

                    alerta.setTitle("ATENCION")
                            .setMessage("FALTA LLENAR LOS SIGUIENTES CAMPOS \n" + mensajeError)
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            }).show();
                } else {
                    alerta.setTitle("AVISO")
                            .setMessage("PASE A RECOGER SU ORDEN A VENTANILLA")
                            .setMessage("EL TOTAL DE SU ORDEN ES: "+ resultado)
                            .setNegativeButton("REGRESAR", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                    dialogInterface.cancel();
                                }
                            })
                            .setPositiveButton("CERRAR", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();
                }
                mensajeError = "";
            }

        });

    }
}
