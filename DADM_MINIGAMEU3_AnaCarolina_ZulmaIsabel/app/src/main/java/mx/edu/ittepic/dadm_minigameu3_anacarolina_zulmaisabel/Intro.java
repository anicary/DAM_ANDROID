package mx.edu.ittepic.dadm_minigameu3_anacarolina_zulmaisabel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by UsuarioPrueba on 22/10/2017.
 */

public class Intro extends View {
    Bitmap corgi,texto;
    CountDownTimer timer,siguiente;
    int resulusionx,resulusiony;
    int y=0, y1=1300;
    boolean movimiento;
    public Intro(Context context) {
        super(context);
        Resolucion();
        corgi = BitmapFactory.decodeResource(getResources(),R.drawable.corgi);
        corgi=escalado(corgi,(resulusiony/3), true);
        texto = BitmapFactory.decodeResource(getResources(),R.drawable.generatedtext);
        texto=escalado(texto,(resulusiony/2), true);
        movimiento = true;


        timer = new CountDownTimer(5000,1) {
            @Override
            public void onTick(long l) {

                if(movimiento ==true) {
                    y += 8;
                    y1 -=8;
                }
                else
                {
                    siguiente.start();
                    timer.cancel();
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
        timer.start();
        siguiente = new CountDownTimer(2000,1) {
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
    public void Resolucion() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        resulusionx = size.x;
        resulusiony = size.y;
        System.out.println("RESOLUCION "+resulusionx+","+resulusiony);

    }
    public static Bitmap escalado(Bitmap imgentrada, float tamanio,  boolean filtro) {
        float ratio = Math.min((float) tamanio / imgentrada.getWidth(), (float) tamanio / imgentrada.getHeight());
        int width = Math.round((float) ratio * imgentrada.getWidth());
        int height = Math.round((float) ratio * imgentrada.getHeight());
        Bitmap nuevaImagen = Bitmap.createScaledBitmap(imgentrada, width, height, filtro);
        return nuevaImagen;
    }

}
