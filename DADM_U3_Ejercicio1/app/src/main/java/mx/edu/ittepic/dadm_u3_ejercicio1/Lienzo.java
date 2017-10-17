package mx.edu.ittepic.dadm_u3_ejercicio1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by UsuarioPrueba on 16/10/2017.
 */

public class Lienzo extends View {
    Bitmap img;
    int ancho,alto;

    public Lienzo(Context context) {
        super(context);
        ancho = 400;
        alto =200;
                                        //accede a la carpeta //indicas que recurso quieres obtener
        img = BitmapFactory.decodeResource(getResources(),R.drawable.dona);

    }

    public void onDraw(Canvas c){ //ESTRUCTURA BASICA DEL LIENZO
        Paint p = new Paint();   //ESTRUCTURA BASICA DEL LIENZO
        p.setColor(Color.BLUE);
        c.drawRect(300,200,300+ancho,200+alto,p);
        c.drawBitmap(img,150,500,p);
    }
    public boolean onTouchEvent(MotionEvent me){ //CODIGO BASE DE UN VIEW CON EVENTO
        float xPantalla, yPantalla;

        xPantalla=me.getX();
        yPantalla=me.getY();

        switch (me.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(estaEnAreaRect(xPantalla,yPantalla)){
                    Toast.makeText(getContext(),"TOCASTE EL RECTANGULO",Toast.LENGTH_SHORT).show();
                }
                if (estaEnAreaImg(xPantalla,yPantalla)){
                    Toast.makeText(getContext(),"TOCASTE LA IMAGEN",Toast.LENGTH_SHORT).show();
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
    public boolean estaEnAreaImg(float xP, float yP){
        if (xP >150 && xP <150+img.getWidth()){
            if (yP>500 && yP <500+img.getHeight()){
                invalidate();
                return true;
            }
        }
        return false;
    }

}
