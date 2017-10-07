package mx.edu.ittepic.dadm_u3_ana_carolina_mondragon_rangel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.view.View;

/**
 * Created by UsuarioPrueba on 06/10/2017.
 */

public class Lienzo extends View{
    int y=0,x=0;
    /* VALORES VAMPIROS */
    float xv=0,yv=0,pv=0,pv2=0;
    CountDownTimer relogvapmpi1,relogalas;
    public Lienzo(Context context) {
        super(context);
        relogvapmpi1 = new CountDownTimer(2000,1) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(pv==0){
                    yv+=1;
                }else
                {
                    if(yv!=0){
                        yv-=1;
                    }
                }
                invalidate();
            }
            @Override
            public void onFinish() {
                if(pv==0){
                    pv=1;
                }else
                {
                    yv=0;
                    pv=0;
                }
                relogvapmpi1.start();
            }
        };
        relogvapmpi1.start();
        relogalas = new CountDownTimer(2000,200) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(pv2==0){
                    pv2=1;
                }else
                {
                    pv2=0;
                }
                invalidate();
            }
            @Override
            public void onFinish() {
                relogalas.start();
            }
        };
        relogalas.start();
        /* VALORES VAMPIROS */
    }
    public void onDraw (Canvas c){
        vampiro(c,xv+250,yv+50);
        vampiro(c,xv+1500,yv+50);
        vampiro(c,xv+400,yv+300);
        Bruja(c,y,x);
    }
    public void Bruja (Canvas c, float y, float x){
        Paint p = new Paint();

        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.BLACK,x+1000,y+200,80,250);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.BLACK,x+960,y+200,50,210);

        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(242,212,171));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(242,212,171),x+860,y+200,100,140);

        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(167, 24, 211));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(167, 24, 211),x+800,y+180,400,30);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(167, 24, 211));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(167, 24, 211),x+830,y+150,330,30);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(167, 24, 211));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(167, 24, 211),x+860,y+120,260,30);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(167, 24, 211));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(167, 24, 211),x+890,y+90,200,30);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(167, 24, 211));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(167, 24, 211),x+930,y+60,130,30);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(167, 24, 211));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(167, 24, 211),x+970,y+30,200,30);

        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(99,180,200));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(99, 180, 200),x+890,y+230,30,40);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.BLACK,x+920,y+230,20,10);

        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.BLACK,x+860,y+300,25,10);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.BLACK,x+860,y+305,22,10);

        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(167, 24, 211));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(167, 24, 211),x+920,y+340,40,30);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(167, 24, 211));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(167, 24, 211),x+900,y+360,60,60);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(167, 24, 211));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(167, 24, 211),x+870,y+410,140,90);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(167, 24, 211));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(167, 24, 211),x+920,y+410,80,30);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(167, 24, 211));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(167, 24, 211),x+950,y+450,100,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(167, 24, 211));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(167, 24, 211),x+830,y+490,250,70);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(206, 136, 208));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(206, 136, 208),x+830,y+490,80,70);

        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(242, 212, 171));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(242, 212, 171),x+828,y+560,40,40);

        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(167, 24, 211));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(167, 24, 211),x+870,y+560,250,100);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(167, 24, 211));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(167, 24, 211),x+870,y+580,150,100);

        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.BLACK,x+970,y+680,50,40);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.BLACK,x+1015,y+720,50,40);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.BLACK,x+1070,y+660,50,40);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.BLACK,x+1120,y+700,50,40);


    }
    public void vampiro(Canvas pincel,float x,float y){
        float tam1=75, tam2=25;
        dibujarCuadrado(pincel,Color.BLACK,x+75,y-25,tam2,tam2);
        dibujarCuadrado(pincel,Color.BLACK,x+125,y-25,tam2,tam2);
        if(pv2==1){
            dibujarCuadrado(pincel,Color.BLACK,x,y,tam1,tam2);
            dibujarCuadrado(pincel,Color.BLACK,x+100,y,tam2,tam2);
            dibujarCuadrado(pincel,Color.BLACK,x+150,y,tam1,tam2);
            dibujarCuadrado(pincel,Color.BLACK,x+25,y+25,175,tam2);
        }else
        {
            dibujarCuadrado(pincel,Color.BLACK,x+150,y,25,tam2);
            dibujarCuadrado(pincel,Color.BLACK,x+50,y,25,tam2);
            dibujarCuadrado(pincel,Color.BLACK,x+100,y,tam2,tam2);
            dibujarCuadrado(pincel,Color.BLACK,x+50,y+25,125,tam2);
        }

        dibujarCuadrado(pincel,Color.BLACK,x+75,y+50,75,tam2);
        dibujarCuadrado(pincel,Color.BLACK,x+100,y+75,25,tam2);
    }
    public  void dibujarCuadrado(Canvas pincel,int color,float  derecha,float arriba,float tam1, float tam2)
    {
        Paint pintura =new Paint();
        pintura.setColor(color);
        float right = derecha + tam1;
        float bottom = arriba + tam2;
        RectF Rectangulo = new RectF(derecha, arriba, right, bottom);
        pincel.drawRect(Rectangulo, pintura);
    }
}
