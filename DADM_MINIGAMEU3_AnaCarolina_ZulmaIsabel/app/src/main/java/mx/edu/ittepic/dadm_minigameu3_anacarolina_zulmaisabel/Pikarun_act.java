package mx.edu.ittepic.dadm_minigameu3_anacarolina_zulmaisabel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.Random;

public class Pikarun_act extends AppCompatActivity {
    CountDownTimer principal;
    int resulusionx,resulusiony;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PikaRUN(this));
    }

    public class PikaRUN extends View {
        SpriteAnim pikarunsp;
        int img[]={R.drawable.pikarun1,R.drawable.pikarun2,R.drawable.pikarun3,R.drawable.pikarun4,R.drawable.pikarun5,R.drawable.pikarun6};
        Bitmap [] imge;
        int  pixelArt[]={R.drawable.roca};
        Objetos [] assets;
        CountDownTimer ObjetosEntregar;
        int posrocas=0;
        public PikaRUN (Context context) {
            super(context);
            Resolucion();
            imge= new Bitmap[img.length];
            for(int i=0;i<img.length;i++)
            {
                imge[i]=BitmapFactory.decodeResource(getResources(),img[i]);
            }
            pikarunsp= new SpriteAnim(imge,resulusionx/9,resulusiony/2,250);
            principal= new CountDownTimer(1000,1) {
                @Override
                public void onTick(long millisUntilFinished) {
                        invalidate();
                }

                @Override
                public void onFinish() {
                    principal.start();

                }
            };principal.start();
            assets = new Objetos[10];
            int posrock=0;
            for(int i=0;i< assets.length;i++)
            {
                //assets[i]=new Objetos(getApplication(),pixelArt[0],resulusionx+300,500,300,"PIEDRA");
                assets[i]=new Objetos(getApplication(),pixelArt[0],resulusionx+300,(float) (resulusiony/1.7),resulusiony/5,"PIEDRA");
               // assets[i].setEstado(true);
            }
            ObjetosEntregar=new CountDownTimer(10000,2000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if(true){
                        if(posrocas<10)
                        {
                            assets[posrocas].setEstado(true);
                        }
                    }
                }
                @Override
                public void onFinish() {
                    ObjetosEntregar.start();
                }
            };
            ObjetosEntregar.start();
        }
        public void onDraw (Canvas c)
        {
            Paint p = new Paint();
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.rgb(59, 66, 167));
            c.drawPaint(p); //PARA DIBUJAR EL PAINT
            for(int i=0;i<assets.length;i++)
            {
                assets[i].dibujarObjeto(c);
                assets[i].moverX(-5);
            }
            pikarunsp.dibujar(c);
            pikarunsp.animINI();
        }
        public boolean onTouchEvent(MotionEvent motionEvent) {

            if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {

            }
            if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                pikarunsp.saltar();
            }
            return true;
        }
    }
    public void Resolucion() {
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        resulusionx = size.x;
        resulusiony = size.y;
        System.out.println("RESOLUCION "+resulusionx+","+resulusiony);

    }
}
