package mx.edu.ittepic.dadm_minigameu3_anacarolina_zulmaisabel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Intro(this));
    }
}
