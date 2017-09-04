package mx.edu.ittepic.dadm_u1_ejercicio4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LinearLayout1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout1);
    }
    public void administracion (View v){
        Toast.makeText(this,"Usted eligio administracion",Toast.LENGTH_SHORT).show();
    }
    public void arquitectura (View v){
        Toast.makeText(this, "Usted eligio arquitectura", Toast.LENGTH_SHORT).show();
    }
    public void civil (View v){
        Toast.makeText(this, "Usted eligio civil", Toast.LENGTH_SHORT).show();
    }
    public void electrica (View v){
        Toast.makeText(this,"Usted eligio electrica",Toast.LENGTH_SHORT).show();
    }
    public void industrial (View v){
        Toast.makeText(this, "Usted eligio industrial", Toast.LENGTH_SHORT).show();
    }
    public void sistemas (View v){
        Toast.makeText(this,"Usted eligio sistemas", Toast.LENGTH_SHORT).show();
    }
    public void tic (View v){
        Toast.makeText(this,"Usted eligio tics", Toast.LENGTH_SHORT).show();
    }
    public void quimica (View v){
        Toast.makeText(this,"Usted eligio quimica", Toast.LENGTH_SHORT).show();
    }
    public void cerrar (View v){
        finish();
    }

}
