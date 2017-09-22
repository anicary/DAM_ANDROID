package mx.edu.ittepic.dadm_u2_ejercicio2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView)findViewById(R.id.Listamenu);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {
                switch (posicion){
                    case 0:
                        abrirOtraVentana();
                        break;
                    case 1:
                        acercade();
                        break;
                    case 2:
                        cerrarAplicacion();
                        break;
                }
            }
        });

    }

    public void abrirOtraVentana(){
        Intent otroActivity = new Intent(this,Main2Activity.class);
        startActivity(otroActivity);

    }
    public void acercade(){
        Toast.makeText(this,"(c) ana carolina \n mondragon",Toast.LENGTH_LONG).show();

    }
    public void cerrarAplicacion(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("ATENCION")
                .setMessage("DESEAS CERRAR LA APLICACION")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .show();
    }
}
