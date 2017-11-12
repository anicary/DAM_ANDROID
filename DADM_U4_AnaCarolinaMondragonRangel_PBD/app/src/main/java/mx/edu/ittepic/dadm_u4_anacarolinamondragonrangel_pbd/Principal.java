package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_pbd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class Principal extends AppCompatActivity {
    Button Clientes,Reparacion;
    Intent intento, intento2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_principal);

        Clientes=(Button) findViewById(R.id.btn_clientes);
        Clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intento =new Intent(Principal.this,ListaClientes.class);
                startActivity(intento);
            }
        });

        Reparacion =(Button)findViewById(R.id.btn_aparato);
        Reparacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intento =new Intent(Principal.this,ListaOrdenes.class);
                startActivity(intento);
            }
        });
    }
}
