package mx.edu.ittepic.practicau1_1_retosemana_anacarolinamondragonrangel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MenuBonitoSinImagenes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bonito_sin_imagenes);

        ArrayList<View> viewsToFadeIn = new ArrayList<View>();
        viewsToFadeIn.add(findViewById(R.id.vacio1));
        viewsToFadeIn.add(findViewById(R.id.vacio2));
        viewsToFadeIn.add(findViewById(R.id.internet));
        viewsToFadeIn.add(findViewById(R.id.movil));
        viewsToFadeIn.add(findViewById(R.id.itic));
        viewsToFadeIn.add(findViewById(R.id.app));
        viewsToFadeIn.add(findViewById(R.id.android));
        for (View v : viewsToFadeIn)
        {
            v.setAlpha(0);
        }

        for (View v : viewsToFadeIn)
        {
            v.animate().alpha(1.0f).setDuration(2000).start();
        }
    }
}
