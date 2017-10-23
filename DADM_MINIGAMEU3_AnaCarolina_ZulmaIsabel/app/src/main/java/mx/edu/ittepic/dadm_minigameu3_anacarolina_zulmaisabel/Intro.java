package mx.edu.ittepic.dadm_minigameu3_anacarolina_zulmaisabel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.view.View;

/**
 * Created by UsuarioPrueba on 22/10/2017.
 */

public class Intro extends View {
    Bitmap corgi,texto;
    CountDownTimer timer,siguiente;
    int y=0, y1=1300;
    boolean movimiento;
    public Intro(Context context) {
        super(context);

        corgi = BitmapFactory.decodeResource(getResources(),R.drawable.corgi);
        texto = BitmapFactory.decodeResource(getResources(),R.drawable.generatedtext);
        movimiento = true;


        timer = new CountDownTimer(5000,1) {
            @Override
            public void onTick(long l) {

                if(movimiento ==true) {
                    y += 8;
                    y1 -=8;
                }
                if (y >= (1300- corgi.getHeight()) / 2) {
                    movimiento = false;
                }

                    invalidate(); //Vuelve a ejecutar el onDraw
            }
            @Override
            public void onFinish()
            {
                timer.start();
            }
        };
        siguiente.start();
        siguiente = new CountDownTimer(1000,1) {
            @Override
            public void onTick(long l) {
            }
            @Override
            public void onFinish()
            {
                if(movimiento == false) {
                    Intent opcion = new Intent(getContext(), Menu.class);
                    getContext().startActivity(opcion);
                }
            }
        };
    }
    public void onDraw(Canvas c){
        Paint p = new Paint();
        c.drawBitmap(corgi,200,y,p);
        c.drawBitmap(texto,50,y1,p);
    }
}
