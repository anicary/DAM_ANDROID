package mx.edu.ittepic.dadm_u2_ejercicio2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public boolean OnCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.menusegundaventana,m);
        return true;
    }
    /*TAREA 1 MEMORIZAR LOS METODOS DE OPTIONMENU
    * TAREA 2 COMO FUNCIONA Y PARA QUE SIRVE LA CLASE COUNTDOWNTIMER DE ANDROID
    * TRAER UN EJEMPLO*/
}
