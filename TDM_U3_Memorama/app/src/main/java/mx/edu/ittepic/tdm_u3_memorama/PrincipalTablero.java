package mx.edu.ittepic.tdm_u3_memorama;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PrincipalTablero extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Tablero(this));

    }
}
