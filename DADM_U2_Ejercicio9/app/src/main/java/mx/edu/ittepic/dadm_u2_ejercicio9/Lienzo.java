package mx.edu.ittepic.dadm_u2_ejercicio9;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by UsuarioPrueba on 05/10/2017.
 */

public class Lienzo extends View {
    CountDownTimer timer;
    Bitmap img;
    int x;
    boolean movimiento;

    public Lienzo(Context context) {
        super(context);

        img = BitmapFactory.decodeResource(getResources(),R.drawable.logo);

        x=200;
        movimiento = true;

        timer = new CountDownTimer(5000,1) {
            @Override
            public void onTick(long l) {

                if(movimiento ==true){
                    x+=5;
                }else{
                    x-=5;
                }
                if(x>=(getWidth()-img.getWidth())){
                    movimiento = false;
                }
                if(x<0){
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
        c.drawBitmap(img,x,300,p);

    }
}
