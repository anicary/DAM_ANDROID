package mx.edu.ittepic.practicau1_1_retosemana_anacarolinamondragonrangel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
    public void internet (View v){
        Toast.makeText(this,"internet",Toast.LENGTH_SHORT).show();
    }
    public void movil (View v){
        Toast.makeText(this, "MOVIL", Toast.LENGTH_SHORT).show();
    }
    public void itic (View v){
        Toast.makeText(this, "ITIC", Toast.LENGTH_SHORT).show();
    }
    public void android (View v){
        Toast.makeText(this,"ANDROID",Toast.LENGTH_SHORT).show();
    }
    public void apps (View v){
        Toast.makeText(this, "APPS", Toast.LENGTH_SHORT).show();
    }
}
