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
    CountDownTimer timer;
    boolean movimiento=true;
    int x=300;
    int ancho,alto;

    public Lienzo (Context context){
        super(context);
        ancho =400;
        alto=200;

        timer = new CountDownTimer(5000,1) {
            @Override
            public void onTick(long l) {

                if(movimiento ==true){
                    x+=5;
                }else{
                    x-=5;
                }
                if(x>=1000){
                    movimiento = false;
                }
                if(x<0){
                    movimiento=true;
                }
                invalidate(); //Vuelve a ejecutar el onDraw
            }

            @Override
            public void onFinish()
            {
                timer.start();
            }
        };
        timer.start();
    }
    public void onDraw(Canvas c){
        Paint p = new Paint();
        circulo(c, Color.MAGENTA,400,200,300,300);
        triangulo(c, Color.BLACK,300+ancho,200,300);
    }
    public boolean onTouchEvent(MotionEvent me){ //CODIGO BASE DE UN VIEW CON EVENTO
        float xPantalla, yPantalla;

        xPantalla=me.getX();
        yPantalla=me.getY();

        switch (me.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(estaEnAreaRect(xPantalla,yPantalla)){
                    Toast.makeText(getContext(),"COLISIONO",Toast.LENGTH_SHORT).show();
                }
        }

        return true;
    }

    public boolean estaEnAreaRect(float xP, float yP){
        if(xP > 300 && xP < 300+ancho){
            if (yP > 200 && yP <200+alto){
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

    public void triangulo(Canvas c, int color, int x,int y, int tamano){
        int tamanomitad = tamano/2;
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
