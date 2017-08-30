package mx.edu.ittepic.practicau1_1_layoutsymaslayout_anacarlinamondragonrangel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class pantalla2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla2);
    }
    public void ventana(View vista){
        Intent intent = new Intent(this, pantalla3.class);
        startActivity(intent);
    }
}
