package mx.edu.ittepic.dadm_u3_ana_carolina_mondragon_rangel_coposnieve;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.view.View;

import java.util.Random;

/**
 * Created by UsuarioPrueba on 14/10/2017.
 */

public class Lienzo extends View {
    Copos[] GrupoCopos;
    int[]arbol =
            {
                    0,0,0,0,0,3,0,0,0,0,0,
                    0,0,0,0,3,0,3,0,0,0,0,
                    0,0,0,0,0,3,0,0,0,0,0,
                    0,0,0,0,0,1,0,0,0,0,0,
                    0,0,0,0,1,2,1,0,0,0,0,
                    0,0,0,1,4,2,2,1,0,0,0,
                    0,0,1,2,2,2,4,2,1,0,0,
                    0,0,0,1,2,6,2,1,0,0,0,
                    0,0,1,2,6,2,6,2,1,0,0,
                    0,1,2,4,2,2,2,4,2,1,0,
                    0,0,1,2,6,2,6,2,1,0,0,
                    0,1,2,6,2,6,2,6,2,1,0,
                    1,2,2,2,2,2,2,2,2,2,1,
                    0,1,2,2,4,2,2,4,2,1,0,
                    0,0,1,1,1,5,1,1,1,0,0,
                    0,0,0,0,1,1,1,0,0,0,0

            };
    CountDownTimer global;
    int posactios=0;
    public Lienzo(Context context) {
        super(context);
        GrupoCopos = new Copos[100];
        Random xrandom = new Random();
        for (int i=0;i<GrupoCopos.length;i++){
            GrupoCopos[i] =new Copos(xrandom.nextInt(1000),0,xrandom.nextInt(25),20);
            GrupoCopos[i].settamanoy(1700);
            GrupoCopos[i].animacion();
        }

        global= new CountDownTimer(1500,1) {
            @Override
            public void onTick(long l) {
                invalidate();
            }

            @Override
            public void onFinish() {
                if(posactios<GrupoCopos.length){
                    GrupoCopos[posactios].setEstado(true);
                    posactios++;
                }
                global.start();
            }
        };
        global.start();
    }
    public void arbol (Canvas c, float x, float y,float tamx, float tamy){
        int posFila =0;
        int columna =0;
        int fila=0;
        for (int i =0; i < arbol.length;i++){

            if (arbol[i]==1){
                cuadrado(c, Color.BLACK,x+columna,y+fila,tamx,tamy);
            }
            if (arbol[i]==2){
                cuadrado(c,Color.rgb(65, 164, 0),x+columna,y+fila,tamx,tamy);//verde claro
            }
            if (arbol[i]==3){
                cuadrado(c,Color.YELLOW,x+columna,y+fila,tamx,tamy);
            }
            if (arbol[i]==4){
                cuadrado(c,Color.RED,x+columna,y+fila,tamx,tamy);
            }
            if (arbol[i]==5){
                cuadrado(c,Color.rgb(185, 102, 0),x+columna,y+fila,tamx,tamy);//CAFE
            }
            if (arbol[i]==6){
                cuadrado(c,Color.rgb(0, 131, 0),x+columna,y+fila,tamx,tamy);//VERDE MAS FUERTE
            }
            columna+=tamx;
            posFila++;
            if (posFila == 11){
                columna=0;
                posFila=0;
                fila+=tamy;

            }

        }
    }
    public void onDraw (Canvas c)
    {
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.rgb(59, 66, 167));
        c.drawPaint(p); //PARA DIBUJAR EL PAINT
        cuadrado(c,Color.WHITE,0,getHeight()-300,getWidth(),300);
        arbol(c,200,1000,30,30);//DONDE SE LLAMA EL ARBOL
        arbol(c,100,1150,10,10);//Arbol lejos
        arbol(c,50,1150,10,10);//Arbol lejos
        arbol(c,900,1150,10,10);//Arbol lejos
        arbol(c,700,1100,18,18);//DONDE SE LLAMA EL ARBOL
        for (int i=0;i<GrupoCopos.length;i++){
            GrupoCopos[i].Dibujar(c);
        }
    }

    public  void cuadrado (Canvas c,int color,float  x,float y,float tamx, float tamy)
    {
        Paint p =new Paint();
        p.setColor(color);
        p.setStyle(Paint.Style.STROKE); //CONTORNO
        p.setStyle(Paint.Style.FILL); //MAS GRUESO
        float r = x + tamx;
        float b = y + tamy;
        RectF rec = new RectF(x, y, r, b);
        c.drawRect(rec, p);
    }
}
