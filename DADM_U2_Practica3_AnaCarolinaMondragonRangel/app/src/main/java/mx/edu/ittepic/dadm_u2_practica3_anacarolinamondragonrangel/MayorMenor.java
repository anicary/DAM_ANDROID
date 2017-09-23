package mx.edu.ittepic.dadm_u2_practica3_anacarolinamondragonrangel;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MayorMenor extends AppCompatActivity {

    EditText num1,num2,num3;
    Button btnmenor,btnmayor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mayor_menor);

        num1=(EditText) findViewById(R.id.valor1);
        num2=(EditText) findViewById(R.id.valor2);
        num3=(EditText) findViewById(R.id.valor3);

        btnmenor=(Button) findViewById(R.id.menor);
        btnmayor=(Button) findViewById(R.id.mayor);

        btnmenor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int valor1 = 0, valor2 = 0, valor3 = 0;

                try {

                    valor1 = Integer.parseInt(num1.getText().toString());
                    valor2 = Integer.parseInt(num2.getText().toString());
                    valor3 = Integer.parseInt(num3.getText().toString());
                }catch (NumberFormatException e){
                }

                    if (valor1 > 0 && valor2 > 0 && valor3 > 0) {

                        if (valor1 < valor2 || valor1 < valor3) {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(MayorMenor.this);
                            alerta.setTitle("RESULTADO:")
                                    .setMessage("EL NUMERO MENOR ES: " + valor1)
                                    .setNegativeButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    }).show();

                        } else {
                            if (valor2 < valor3) {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(MayorMenor.this);
                                alerta.setTitle("RESULTADO:")
                                        .setMessage("EL NUMERO MENOR ES: " + valor2)
                                        .setNegativeButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        }).show();
                            } else {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(MayorMenor.this);
                                alerta.setTitle("RESULTADO:")
                                        .setMessage("EL NUMERO MENOR ES: " + valor3)
                                        .setNegativeButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        }).show();
                            }

                        }
                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MayorMenor.this);
                        alerta.setTitle("ATENCION:")
                                .setMessage("Ingrese numeros enteros positivos")
                                .setNegativeButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                }).show();
                    }
            }
        });

        btnmayor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int valor1=0,valor2=0,valor3=0;

                try {
                    valor1 = Integer.parseInt(num1.getText().toString());
                    valor2 = Integer.parseInt(num2.getText().toString());
                    valor3 = Integer.parseInt(num3.getText().toString());

                }catch (NumberFormatException e){
                }

                    if (valor1 > 0 && valor2 > 0 && valor3 > 0) {

                        if (valor1 > valor2 || valor1 > valor3) {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(MayorMenor.this);
                            alerta.setTitle("RESULTADO:")
                                    .setMessage("EL NUMERO MAYOR ES: " + valor1)
                                    .setNegativeButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    }).show();

                        } else {
                            if (valor2 > valor3) {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(MayorMenor.this);
                                alerta.setTitle("RESULTADO:")
                                        .setMessage("EL NUMERO MAYOR ES: " + valor2)
                                        .setNegativeButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        }).show();
                            } else {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(MayorMenor.this);
                                alerta.setTitle("RESULTADO:")
                                        .setMessage("EL NUMERO MAYOR ES: " + valor3)
                                        .setNegativeButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        }).show();
                            }

                        }
                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MayorMenor.this);
                        alerta.setTitle("ATENCION:")
                                .setMessage("Ingrese numeros enteros positivos")
                                .setNegativeButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                }).show();
                    }
            }
        });
    }
}
