package mx.edu.ittepic.dadm_u2_ejercicio1;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Oyente extends AppCompatActivity {

    Button btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyente);

        btn1=(Button) findViewById(R.id.saludar);
        btn2 =(Button) findViewById(R.id.fecha);
        btn3 = (Button) findViewById(R.id.guardar);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Oyente.this,"PRESIONE BTN1",Toast.LENGTH_LONG).show();
            }
        });
        btn1.setOnLongClickListener(new View.OnLongClickListener()/*metodo que asigna el oyente*/ {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder mensaje = new AlertDialog.Builder(Oyente.this);

                mensaje.setTitle("ATENCION");
                mensaje.setMessage("Diste click sostenido en btn1");
                mensaje.setPositiveButton("aceptar", new DialogInterface.OnClickListener()/*texto que mostrara el mensaje y el evento*/
                {
                    @Override                           /*dialog es el nombre*/
                    public void onClick(DialogInterface dialog, int i)
                    /*para hacer que desaparezca el cuadro de dialogo*/{
                        dialog.dismiss();
                    }
                });/*esta clase anonima esta anidada dentro de otra clase*/
                mensaje.show();
                return false;
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Oyente.this,"PRESIONE BTN2",Toast.LENGTH_LONG).show();
            }
        });
        btn2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder mensaje =new AlertDialog.Builder(Oyente.this);

                mensaje.setTitle("ATENCION2");
                mensaje.setMessage("DISTE CLIC SOSTENIDO EN BTN2");
                mensaje.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){ /*Para que se autocomplete se pone OnClick*/
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();

                    }
                });
                mensaje.show();
                return false;
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Oyente.this,"PRESIONE BTN3",Toast.LENGTH_LONG).show();
            }
        });

        btn3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder mensaje = new AlertDialog.Builder(Oyente.this);
                mensaje.setTitle("ATENCION");
                mensaje.setMessage("Diste un clic sostenid en btn3");
                mensaje.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                mensaje.show();
                return false;
            }
        });
    }
}
