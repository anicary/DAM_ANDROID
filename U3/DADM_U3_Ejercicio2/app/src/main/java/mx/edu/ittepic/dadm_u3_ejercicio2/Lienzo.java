package mx.edu.ittepic.dadm_u3_ejercicio2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by UsuarioPrueba on 20/10/2017.
 */

public class Lienzo extends View {
    Figura c1,c2,c3,c4;
    public Lienzo(Context context){
        super(context);

        c1 = new Figura(this); //AQUI SE MANDA LLAMAR AL CONSTRUCTOR
        c2 = new Figura(this);
        c3 = new Figura(this);
        c4 = new Figura(this);
    }

    public void onDraw(Canvas c){
        Paint p = new Paint();
        //*PRIMER COLOR*//
        p.setColor(c1.color); //LO VA A PINTAR DEL COLOR QUE TENGA ADENTRO
        c.drawRect(c1.x,c1.y,c1.x+c1.ancho,c1.y+c1.alto,p); //LA COORDENADA ORIGINAL + ALTO Y ANCHO PARA PONER X y Y
        // P ES PARA LLEVARSE EL COLOR
        //*SEGUNDO COLOR*//
        p.setColor(c2.color);
        c.drawRect(c2.x,c2.y,c2.x+c2.ancho,c2.y+c2.alto,p);
        //*TERCER COLOR*//
        p.setColor(c3.color);
        c.drawRect(c3.x,c3.y,c3.x+c3.ancho,c3.y+c3.alto,p);
        //*CUARTO COLOR*//
        p.setColor(c4.color);
        c.drawRect(c4.x,c4.y,c4.x+c4.ancho,c4.y+c4.alto,p);

    }

    public boolean onTouchEvent (MotionEvent me){
        float x = me.getX();
        float y = me.getY();

        switch (me.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(c1.estaEnArea(x,y) == true){ //EL X y Y SE REFIERE A LAS CORDENADAS DE LA PANTALLA
                    Toast.makeText(getContext(),"ESTA EN C1", Toast.LENGTH_LONG).show();
                }
                if(c2.estaEnArea(x,y) == true) {
                    Toast.makeText(getContext(), "ESTA EN C2", Toast.LENGTH_LONG).show();
                }
                if(c3.estaEnArea(x,y) == true) {
                    Toast.makeText(getContext(), "ESTA EN C3", Toast.LENGTH_LONG).show();
                }
                if(c4.estaEnArea(x,y) == true) {
                    Toast.makeText(getContext(), "ESTA EN C4", Toast.LENGTH_LONG).show();
                }

                break;
        }
        return true;
    }
}
