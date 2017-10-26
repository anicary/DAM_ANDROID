package mx.edu.ittepic.dadm_u2_ejercicio8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.View;

/**
 * Created by UsuarioPrueba on 04/10/2017.
 */
//UNIDAD 3
public class Lienzo extends View {
    CountDownTimer timer;
    int y;
    boolean movimiento;

    public Lienzo(Context context) {
        super(context);
        y=100;
        movimiento = true;
        timer = new CountDownTimer(5000,1) {
            @Override
            public void onTick(long l) {

                if(movimiento ==true){
                    y+=5;
                }else{
                    y-=5;
                }
                if(y>=getHeight()){
                    movimiento = false;
                }
                if(y<0){
                    movimiento=true;
                }
                invalidate(); //Vuelve a ejecutar el onDraw
            }

            @Override
            public void onFinish() {
                timer.start();
            }
        };
        timer.start();
    }
    public void onDraw(Canvas c){
        Paint p = new Paint();

        //INVESTIGAR TYPEFACE Y DESCARGAR LAS FONTS DE ANDROID TTF
        p.setTextSize(90);
        p.setColor(Color.RED);
        c.drawText("ANA CAROLINA",100,y,p);
        p.setTextSize(40);
        c.drawText("ancho: "+getWidth()+"alto: "+getHeight(),20,500,p);
    }
}
