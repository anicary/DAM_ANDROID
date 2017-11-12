package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_pbd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListaOrdenes extends AppCompatActivity {
Button botonAgregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ordenes);
        setTitle("ORDENES DE REPARACION");
        botonAgregar=(Button)findViewById(R.id.btnCrear);
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListaOrdenes.this,AgregarReparacion.class);
                startActivity(intent);
            }
        });
    }
}
