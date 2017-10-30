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
import android.view.Window;
import android.view.WindowManager;

import java.util.Random;

public class Pikarun_act extends AppCompatActivity {
    CountDownTimer principal,puntuacionCont;
    int puntuacionGlobal;
    int resulusionx,resulusiony;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  requestWindowFeature(Window.FEATURE_NO_TITLE);
     //   this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
   //     this.getSupportActionBar().hide();
        setContentView(new PikaRUN(this));
    }
    public class PikaRUN extends View {
        boolean JUEGO=true;
        SpriteAnim pikarunsp;
        Sprite [] capas;
        Sprite  puntos;
        int img[]={R.drawable.pikarun1,R.drawable.pikarun2,R.drawable.pikarun3,R.drawable.pikarun4,R.drawable.pikarun5,R.drawable.pikarun6};
        int  layers[]={R.drawable.layerr1,R.drawable.layerr0};
        Bitmap [] imge;
        int  pixelArt[]={R.drawable.roca};
        Objetos [] assets;
        CountDownTimer ObjetosEntregar;
        int posrocas=0,velocidad=-15;
        public PikaRUN (Context context) {
            super(context);
            Resolucion();
            imge= new Bitmap[img.length];
            capas= new Sprite[2];
            for(int i=0; i<layers.length ;i++)
            {
                capas[i] = new Sprite(BitmapFactory.decodeResource(getResources(),layers[i]),0,0,(float)(resulusiony*1.7));
            }
            for(int i=0;i<img.length;i++)
            {
                imge[i]=BitmapFactory.decodeResource(getResources(),img[i]);
            }
            pikarunsp= new SpriteAnim(imge,resulusionx/9,(float) (resulusiony/1.8),(float)(resulusiony/4.32));
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
                assets[i]=new Objetos(getApplication(),pixelArt[0],resulusionx+300,(float) (resulusiony/1.5),resulusiony/8,"PIEDRA");
            }
            ObjetosEntregar=new CountDownTimer(10000,2000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if(JUEGO){
                        if(posrocas<10)
                        {
                            assets[posrocas].setEstado(true);
                        }
                    }else
                    {
                        for(int i=0;i<assets.length;i++)
                        {
                            assets[i].setEstado(false);
                        }
                    }
                }
                @Override
                public void onFinish() {
                    ObjetosEntregar.start();
                }
            };
            ObjetosEntregar.start();
            puntuacionCont=new CountDownTimer(5000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if(JUEGO) {
                        puntuacionGlobal++;
                        if(puntuacionGlobal==20)
                        {
                            velocidad=-20;
                        }
                        if(puntuacionGlobal==40)
                        {
                            velocidad=-30;
                        }
                        if(puntuacionGlobal==60)
                        {
                            velocidad=-40;
                        }
                        if(puntuacionGlobal>100)
                        {
                            velocidad=-60;
                        }
                    }
                }
                @Override
                public void onFinish() {
                    puntuacionCont.start();
                }
            };
            puntuacionCont.start();
            puntos= new Sprite(BitmapFactory.decodeResource(getResources(),R.drawable.puntos),0,-(resulusiony/30),(float)(resulusiony/2.5));
            pikarunsp.setSalto(resulusiony/72);
        }
        public void onDraw (Canvas c)
        {
            Paint p = new Paint();
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.rgb(59, 66, 167));
            c.drawPaint(p); //PARA DIBUJAR EL PAINT
            for(int i=0;i<capas.length;i++)
            {
                c.drawBitmap(capas[i].imagen, capas[i].x, capas[i].y, p);
            }
            for(int i=0;i<assets.length;i++)
            {
                assets[i].dibujarObjeto(c);
                assets[i].moverX(velocidad);
            }
            for (int i = 0; i < assets.length; i++) {
                if (assets[i].onColission(pikarunsp)) {
                    JUEGO=false;
                    assets[i].moverX(0);
                    System.out.println("CHOKO");
                    pikarunsp.animSTOP();
                }
            }
            pikarunsp.dibujar(c);
           // pikarunsp.animINI();
            c.drawBitmap(puntos.imagen, puntos.x,puntos.y, p);
            p.setColor(Color.BLACK);
            p.setTextSize(resulusionx/40);
            p.setStyle(Paint.Style.FILL);
            c.drawText("DISTANCIA: "+puntuacionGlobal,resulusionx/38,resulusiony/22,p);
        }
        public boolean onTouchEvent(MotionEvent motionEvent) {

            if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {


            }
            if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                if(!JUEGO)
                {
                    JUEGO=true;
                    puntuacionGlobal=0;
                    pikarunsp.animINI();
                }else
                {
                    pikarunsp.saltar();
                }
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
