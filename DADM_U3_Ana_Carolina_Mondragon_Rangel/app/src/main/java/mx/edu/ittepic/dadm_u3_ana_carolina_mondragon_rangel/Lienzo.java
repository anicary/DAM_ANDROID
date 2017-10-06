package mx.edu.ittepic.dadm_u3_ana_carolina_mondragon_rangel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by UsuarioPrueba on 06/10/2017.
 */

public class Lienzo extends View{
    int y=0,x=0;
    public Lienzo(Context context) {
        super(context);
    }
    public void onDraw (Canvas c){
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
        dibujarCuadrado(c,Color.BLACK,x+960,y+200,50,140);

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
