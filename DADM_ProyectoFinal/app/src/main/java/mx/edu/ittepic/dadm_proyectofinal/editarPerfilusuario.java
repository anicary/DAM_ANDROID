package mx.edu.ittepic.dadm_proyectofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class editarPerfilusuario extends AppCompatActivity {
    String nombrea="",apellidosa="",correoa="",imagen="";
    TextView nombre,apellido,correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfilusuario);
    }
}
