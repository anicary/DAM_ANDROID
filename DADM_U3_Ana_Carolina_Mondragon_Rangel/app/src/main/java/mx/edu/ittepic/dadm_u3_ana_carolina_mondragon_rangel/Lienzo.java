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
    int y=0,x=0,ybruja=0,brujaturno;
    float yedi=0,xedi=0,xedi2=0,xedi3=0;
    /* VALORES VAMPIROS */
    float xv=0,yv=0,pv=0,pv2=0;
    CountDownTimer relogvapmpi1,relogalas,edificiocapa1,edificiocapa2,edificiocapa3,bruja;
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
        edificiocapa1= new CountDownTimer(30000,5) {
            @Override
            public void onTick(long l) {
                if (xedi==1800){
                    xedi=0;
                }
                xedi++;
                //  System.out.println("Texto"+xedi);
            }

            @Override
            public void onFinish() {
                edificiocapa1.start();
            }
        };
        edificiocapa1.start();
        edificiocapa2= new CountDownTimer(30000,50) {
            @Override
            public void onTick(long l) {
                if (xedi2==1800){
                    xedi2=0;
                }
                xedi2++;
            }

            @Override
            public void onFinish() {
                edificiocapa2.start();
            }
        };
        edificiocapa2.start();
        edificiocapa3= new CountDownTimer(30000,120) {
            @Override
            public void onTick(long l) {
                if (xedi3==1800){
                    xedi3=0;
                }
                xedi3++;
            }

            @Override
            public void onFinish() {
                edificiocapa3.start();
            }
        };
        edificiocapa3.start();
        bruja= new CountDownTimer(3250,1) {
            @Override
            public void onTick(long l) {
                if(brujaturno==0){
                    ybruja++;
                }else
                {
                    if(ybruja!=0){
                        ybruja--;
                    }
                }

            }
            @Override
            public void onFinish() {

                if(brujaturno==0){
                    brujaturno=1;
                }else {
                    brujaturno=0;
                    ybruja=0;
                }
                bruja.start();
            }
        };
        bruja.start();
    }
    public void onDraw (Canvas c){
        Paint p = new Paint();
        p.setColor(Color.rgb(206, 101, 0));
        p.setStyle(Paint.Style.FILL);
        dibujarCuadrado(c,Color.rgb(252, 172, 0),0,0,1920,1080);

        lunacafe(c,y,x);
        lunamasnaranja(c,y,x);
        lunanaranja(c,y,x);
        lunaamarilla(c,y,x);
        /*Edificios */
        Edificios1(c,xedi2-1750-500,yedi+700,Color.rgb(46, 0, 0));
        Edificios1(c,xedi2+500,yedi+700,Color.rgb(46, 0, 0));
        Edificios1(c,xedi+50,yedi+625,Color.BLACK);
        Edificios1(c,xedi-1750,yedi+625,Color.BLACK);
        dibujarCuadrado(c,Color.BLACK,0,850,1900,50);
        /*Edificios*/
        vampiro(c,xv+250,yv+50);
        vampiro(c,xv+1500,yv+50);
        vampiro(c,xv+400,yv+300);

        Bruja(c,ybruja,x);
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
        dibujarCuadrado(c,Color.rgb(206, 136, 208),x+830,y+550,100,70);

        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(242, 212, 171));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(242, 212, 171),x+828,y+590,40,40);

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

        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(216, 178, 85));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(216, 178, 85),x+800,y+630,72,30);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(216, 178, 85));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(216, 178, 85),x+1020,y+630,42,30);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(216, 178, 85));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(216, 178, 85),x+1120,y+630,100,30);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(216, 178, 85));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(216, 178, 85),x+1180,y+575,40,130);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(216, 178, 85));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(216, 178, 85),x+1220,y+555,200,180);

        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(166, 86, 44));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(166, 86, 44),x+1260,y+580,160,40);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(166, 86, 44));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(166, 86, 44),x+1260,y+670,160,40);


    }
    public void lunacafe(Canvas c,float y, float x){
        Paint p = new Paint();

        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(206, 101, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(206, 101, 0),x+600,y+1,800,100);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(206, 101, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(206, 101, 0),x+400,y+20,1200,60);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(206, 101, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(206, 101, 0),x+350,y+80,1300,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(206, 101, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(206, 101, 0),x+300,y+130,1400,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(206, 101, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(206, 101, 0),x+250,y+180,1500,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(206, 101, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(206, 101, 0),x+200,y+220,1600,370);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(206, 101, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(206, 101, 0),x+250,y+590,1500,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(206, 101, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(206, 101, 0),x+300,y+640,1400,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(206, 101, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(206, 101, 0),x+350,y+690,1300,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(206, 101, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(206, 101, 0),x+400,y+730,1200,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(206, 101, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(206, 101, 0),x+450,y+780,1100,70);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(206, 101, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(206, 101, 0),x+500,y+830,1000,70);



    }
    public void lunamasnaranja(Canvas c,float y, float x) {
        Paint p = new Paint();

        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(241, 135, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(241, 135, 0),x+700,y+10,600,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(241, 135, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(241, 135, 0),x+600,y+40,800,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(241, 135, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(241, 135, 0),x+550,y+80,900,70);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(241, 135, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(241, 135, 0),x+500,y+130,1000,70);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(241, 135, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(241, 135, 0),x+450,y+180,1100,70);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(241, 135, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(241, 135, 0),x+400,y+230,1200,70);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(241, 135, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(241, 135, 0),x+400,y+290,1200,300);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(241, 135, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(241, 135, 0),x+450,y+590,1100,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(241, 135, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(241, 135, 0),x+500,y+640,1000,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(241, 135, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(241, 135, 0),x+550,y+690,900,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(241, 135, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(241, 135, 0),x+600,y+740,800,40);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(241, 135, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(241, 135, 0),x+650,y+780,700,70);


    }
    public void lunanaranja(Canvas c,float y, float x){
        Paint p = new Paint();

        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(252, 172, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(252, 172, 0),x+800,y+100,400,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(252, 194, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(252, 172, 0),x+700,y+150,600,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(252, 172, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(252, 172, 0),x+650,y+200,700,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(252, 172, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(252, 172, 0),x+600,y+250,800,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(252, 172, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(252, 172, 0),x+550,y+300,900,290);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(252, 172, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(252, 172, 0),x+600,y+590,800,50);
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(252, 172, 0),x+650,y+640,700,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(252, 172, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(252, 172, 0),x+700,y+690,600,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(252, 172, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(252, 172, 0));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(252, 172, 0),x+800,y+730,400,50);

    }
    public void lunaamarilla (Canvas c, float y, float x){
        Paint p = new Paint();

        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(249, 187, 3));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(249, 187, 3),x+900,y+240,200,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(249, 187, 3));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(249, 187, 3),x+800,y+290,400,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(249, 187, 3));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(249, 187, 3),x+750,y+340,500,200);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(249, 187, 3));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(249, 187, 3),x+800,y+540,400,50);
        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.rgb(249, 187, 3));
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        dibujarCuadrado(c,Color.rgb(249, 187, 3),x+900,y+580,200,50);

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
    public void Edificios1(Canvas pincel, float x, float y, int color){
      /* Edificio 1*/
        dibujarCuadrado(pincel,color,x-50,y+225,1900,50);
        dibujarCuadrado(pincel,color,x+150,y-100,25,25);
        dibujarCuadrado(pincel,color,x+125,y-75,75,25);
        dibujarCuadrado(pincel,color,x+100,y-50,125,25);
        dibujarCuadrado(pincel,color,x+75,y-25,175,300);
        dibujarCuadrado(pincel,color,x+250,y+150,50,75);
        dibujarCuadrado(pincel,color,x+275,y+175,50,75);
        dibujarCuadrado(pincel,color,x+300,y+200,50,75);
        dibujarCuadrado(pincel,color,x+0,y+200,75,25);

        vetanas(pincel,x+150,y-75,1);
        vetanas(pincel,x+125,y-25,2);
        vetanas(pincel,x+125+25+25,y-25,2);
        vetanas(pincel,x+125+25,y+50,3);
        /* Edificio 1 fin */
         /* Edificio 2 */
        dibujarCuadrado(pincel,color,x+375,y+25,75,200);
        dibujarCuadrado(pincel,color,x+400,y+0,25,25);
        vetanas(pincel,x+400,y+50,2);
        vetanas(pincel,x+400,y+125,2);
         /* Edificio 2 fin */
        /* Edificio 3*/
        dibujarCuadrado(pincel,color,x+150+1350,y-100,25,25);
        dibujarCuadrado(pincel,color,x+125+1350,y-75,75,25);
        dibujarCuadrado(pincel,color,x+100+1350,y-50,125,25);
        dibujarCuadrado(pincel,color,x+75+1350,y-25,175,300);
        dibujarCuadrado(pincel,color,x+250+1350,y+150,50,75);
        dibujarCuadrado(pincel,color,x+275+1350,y+175,50,75);
        dibujarCuadrado(pincel,color,x+300+1350,y+200,50,75);
        vetanas(pincel,x+150+1350,y-75,1);
        vetanas(pincel,x+125+1350,y-25,2);
        vetanas(pincel,x+125+25+25+1350,y-25,2);
        vetanas(pincel,x+125+25+1350,y+50,3);
        /* Edificio 3 fin */
            /* Edificio 4*/
        dibujarCuadrado(pincel,color,x+150+500,y-100-15,25,25);
        dibujarCuadrado(pincel,color,x+125+500,y-75-15,75,25);
        dibujarCuadrado(pincel,color,x+100+500,y-50-15,125,25);
        dibujarCuadrado(pincel,color,x+75+500,y-25-15,175,300);
        dibujarCuadrado(pincel,color,x+250+500,y+150-15,50,75);
        dibujarCuadrado(pincel,color,x+275+500,y+175-15,50,75);
        dibujarCuadrado(pincel,color,x+300+500,y+200-15,50,75);
        vetanas(pincel,x+125+500,y-25-15,2);
        vetanas(pincel,x+125+25+25+500,y-25-15,2);
        vetanas(pincel,x+125+25+25+500,y+50,2);
        vetanas(pincel,x+125+25+25+450,y+50,2);
        vetanas(pincel,x+125+25+25+500,y+125,2);
        vetanas(pincel,x+125+25+25+450,y+125,2);
        /* Edificio 4 fin */
        /* Edificio 5*/
        dibujarCuadrado(pincel,color,x+150+800,y-100-15,25,25);
        dibujarCuadrado(pincel,color,x+125+800,y-75-15,75,25);
        dibujarCuadrado(pincel,color,x+100+800,y-50-15,125,25);
        dibujarCuadrado(pincel,color,x+75+800,y-25-15,175,300);
        dibujarCuadrado(pincel,color,x+250+800,y+150-15,50,75);
        dibujarCuadrado(pincel,color,x+275+800,y+175-15,50,75);
        dibujarCuadrado(pincel,color,x+300+800,y+200-15,50,75);
        vetanas(pincel,x+125+800,y-25-15,2);
        vetanas(pincel,x+125+25+25+800,y-25-15,2);
        vetanas(pincel,x+125+25+25+800,y+50,2);
        /* Edificio 5 fin */
                 /* Edificio 2 */
        dibujarCuadrado(pincel,color,x+1000,y+25,75,200);
        dibujarCuadrado(pincel,color,x+1000,y+0,25,25);
        vetanas(pincel,x+1000,y+50,2);
        vetanas(pincel,x+1000,y+125,2);
         /* Edificio 2 fin */
                  /* Edificio 2 */
        dibujarCuadrado(pincel,color,x+1300,y+25,75,200);
        dibujarCuadrado(pincel,color,x+1300,y+0,25,25);
         /* Edificio 2 fin */
    }
    public void vetanas(Canvas pincel,float x,float y, int ventas)
    {
        if (ventas==1){
            dibujarCuadrado(pincel,Color.rgb(255,140,0),x+0,y-0,25,25);
        }else{
            if (ventas==3){
                dibujarCuadrado(pincel,Color.rgb(255,140,0),x-25,y+25,75,25);
                dibujarCuadrado(pincel,Color.rgb(255,140,0),x+0,y-0,25,100);
            }
            else
            {
                dibujarCuadrado(pincel,Color.rgb(255,140,0),x+0,y-0,25,50);
            }

        }

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
