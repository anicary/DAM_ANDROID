package mx.edu.ittepic.dadm_u3_ejercicio2;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;

import java.util.Random;

/**
 * Created by UsuarioPrueba on 20/10/2017.
 */

public class Figura {
    float x,y;
    CountDownTimer t;
    int color; //COLOR ES UN ENTERO
    float ancho, alto;

    public Figura(Lienzo v){ //CONSTRUCTOR SE LLAMA IGUAL QUE LA CLASE
        //GENERA UN NUMERO RANDOM QUE VA DE 0.0 A 0.9999
        x = (float) (Math.random()*500); //GENERA DEL 0 AL 499
        y = (float) (Math.random()*800); //GENERA DEL 0 AL 799

        final Lienzo li = v; //ES UNA VARIABLE GLOBAL QUE TRAE LA DIRECCION DE LIENZO Y LA PONE EN li

        t = new CountDownTimer(1000,50) {
            @Override
            public void onTick(long l) {
                y += 5;
                li.invalidate();
            }

            @Override
            public void onFinish() {
                t.start();
            }
        };
       t.start();
        color = (int) (Math.random()*10); //SE MOSTRARAS LOS COLORES ALEATORIAMENTE
        switch (color){
            case 0: color = Color.MAGENTA;
                break;
            case 1: color = Color.BLUE;
                break;
            case 2: color = Color.BLACK;
                break;
            case 3: color = Color.YELLOW;
                break;
            case 4: color = Color.RED;
                break;
            case 5: color = Color.LTGRAY;
                break;
            case 6: color = Color.GREEN;
                break;
            case 7: color = Color.GRAY;
                break;
            case 8: color = Color.argb(200,30,11,100);
                break;
            case 9: color = Color.rgb(230,166,20);
                break;
            default: color = Color.CYAN;
        }
        ancho = (int) (Math.random()*300+80);
        alto = (int) (Math.random()*250+40);
    } //AQUI TERMINA EL CONSTRUCTOR

    public boolean estaEnArea(float xP, float yP){
        if(xP > x && xP < x+ancho){
            if (yP > y && yP <y+alto){
                return true;
            }
        }
        return false;
    }
}
