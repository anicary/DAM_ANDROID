package mx.edu.ittepic.dadm_proyectofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class camaraPerfil extends AppCompatActivity {
ImageView imagen;
    Button tomarf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara_perfil);
        imagen = (ImageView)findViewById(R.id.imgPernew);
        tomarf=(Button)findViewById(R.id.tomarfoto);
    }
}
