package mx.edu.ittepic.dadm_u3_ejercicio4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by UsuarioPrueba on 23/10/2017.
 */

public class Lienzo extends View {
  float triangulopos[] ={300,200,300};
    public Lienzo (Context context){
        super(context);


    }
    public void onDraw(Canvas c){
        Paint p = new Paint();
        circulo(c, Color.MAGENTA,400,200,300,300);
        triangulo(c, Color.BLACK,triangulopos[0],triangulopos[1],triangulopos[2]);
    }
    public boolean onTouchEvent(MotionEvent me) { //CODIGO BASE DE UN VIEW CON EVENTO
        float x, y;

        x = me.getX();
        y = me.getY();

        switch (me.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if (triangulopos[0] > x && triangulopos[0] < x + triangulopos[2]) {
                    if (triangulopos[1] > y && triangulopos[1] < y + triangulopos[3]) {
                        triangulopos[0] = x;
                        triangulopos[1] = y;

                        invalidate();
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE:
                if (triangulopos[0] > 0) {
                    if (triangulopos[0] <= 1080) {
                        triangulopos[0] = x;
                        triangulopos[1] = y;

                    }
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return true;
    }


    public boolean estaEnAreaRect(float xP, float yP){
        if(xP > 300 && xP < 200){
            if (yP > 200 && yP <200){
                invalidate();
                return true;
            }
        }
        return false;
    }
    public void circulo(Canvas c, int color, float x,float y, float tamx, float tamy){
        Paint p = new Paint();
        p.setColor(color);
        p.setStyle(Paint.Style.FILL);
        float a= x+tamx;
        float b= y+tamy;
        RectF circulo = new RectF(x,y,a,b);
        c.drawOval(circulo,p);

    }

    public void triangulo(Canvas c, int color, float x,float y, float tamano){
        float tamanomitad = tamano/2;
        Path camino = new Path();
        camino.moveTo(x,y-tamanomitad);
        camino.lineTo(x-tamanomitad,y+tamanomitad);
        camino.lineTo(x+tamanomitad,y+tamanomitad);
        camino.lineTo(x,y-tamanomitad);
        camino.close();
        Paint p = new Paint();
        p.setColor(color);
        p.setStyle(Paint.Style.FILL);
        c.drawPath(camino,p);
    }

}
