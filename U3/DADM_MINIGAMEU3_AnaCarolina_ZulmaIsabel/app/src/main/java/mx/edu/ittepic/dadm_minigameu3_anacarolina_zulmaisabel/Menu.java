package mx.edu.ittepic.dadm_minigameu3_anacarolina_zulmaisabel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class Menu extends AppCompatActivity {
    ImageView pikasurf,pikarun,memo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_menu);
        pikasurf= (ImageView)findViewById(R.id.pikasurf);
        pikasurf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opcion = new Intent(Menu.this,Pikasurf.class);
                startActivity(opcion);
            }
        });
        pikarun= (ImageView)findViewById(R.id.acomodalos);
        pikarun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opcion = new Intent(Menu.this,Pikarun_act.class);
                startActivity(opcion);
            }
        });
        memo= (ImageView)findViewById(R.id.memorama);
        memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opcion = new Intent(Menu.this,PrincipalTablero.class);
                startActivity(opcion);
            }
        });
    }
    public void Acerca (View v){

        Intent opcion = new Intent(Menu.this,AcercaDe.class);
        startActivity(opcion);
    }
}
