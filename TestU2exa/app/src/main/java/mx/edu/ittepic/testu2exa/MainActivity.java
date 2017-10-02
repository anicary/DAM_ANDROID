package mx.edu.ittepic.testu2exa;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp1=(Spinner) findViewById(R.id.spinner);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (sp1.getSelectedItemPosition()){
                    case 0:
                        Toast.makeText(MainActivity.this,""+sp1.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Intent enviar = new Intent(MainActivity.this,Main2Activity.class);
                        enviar.putExtra("SUEÑO",sp1.getSelectedItem().toString());
                        startActivity(enviar);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
public boolean onCreateOptionsMenu(Menu m){
    getMenuInflater().inflate(R.menu.puntitos,m);
    return true;
}
public boolean onOptionsItemSelected(MenuItem mi){

    switch (mi.getItemId()){
        case R.id.perico:
            Toast.makeText(MainActivity.this,"HOLA K ACE",Toast.LENGTH_SHORT).show();
            break;
        case R.id.tachas:
            final AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
            alerta.setTitle("AYUDA")
                    .setMessage("PLOX")
                    .setPositiveButton("SALVAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            AlertDialog.Builder salvar =new AlertDialog.Builder(MainActivity.this);
                            salvar.setTitle("RESCATADO")
                                    .setMessage("FELICIDADES LO SALVASTE")
                                    .setPositiveButton("CHIDO", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                        }
                                    }).show();


                        }
                    }).show();


             break;
    }
    return true;
}
}
