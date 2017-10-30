package mx.edu.ittepic.tdm_u3_memorama;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PrincipalInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Inicio(this));

    }
}
