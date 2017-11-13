package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_bdremota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    Button agregarcontacto;
    Intent intento;
    ConexionRemota con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agregarcontacto =(Button)findViewById(R.id.agregarcon);

        lista =(ListView)findViewById(R.id.lista);

        agregarcontacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intento =new Intent(MainActivity.this,AgregarContacto.class);
                startActivity(intento);
            }
        });
        con= new ConexionRemota(MainActivity.this);
    }
}
