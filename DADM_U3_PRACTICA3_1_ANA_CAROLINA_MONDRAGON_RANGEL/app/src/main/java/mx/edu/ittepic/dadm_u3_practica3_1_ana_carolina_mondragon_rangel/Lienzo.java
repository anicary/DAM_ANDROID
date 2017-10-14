package mx.edu.ittepic.dadm_u3_practica3_1_ana_carolina_mondragon_rangel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
/**
 * Created by UsuarioPrueba on 12/10/2017.
 */
public class Lienzo extends View {
    int[] zorro = {
           /*1,2,3*/
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 2, 2, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 1, 2, 3, 2, 2, 1, 2, 2, 2, 2, 2, 2, 3, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 1, 2, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 1, 2, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 1, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0,
            0, 1, 2, 2, 2, 3, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2, 1, 2, 2, 2, 2, 2, 1, 0, 0, 0,
            0, 1, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 3, 1, 2, 1, 2, 2, 2, 2, 2, 2, 1, 0, 0,
            0, 1, 2, 2, 2, 1, 1, 3, 1, 2, 2, 2, 2, 2, 1, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0,
            0, 1, 1, 3, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 1, 2, 1, 1, 1, 1, 1, 2, 2, 1, 0,
            1, 1, 1, 1, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 3, 1, 2, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1,
            1, 3, 3, 3, 1, 1, 3, 3, 3, 3, 2, 2, 2, 2, 3, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1,
            1, 3, 3, 3, 3, 3, 1, 1, 1, 3, 3, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0,
            0, 1, 3, 3, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0,
            0, 1, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 0, 0,
            0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0,
            0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0,
    };

    public Lienzo(Context context) {
        super(context);
    }

    public void onDraw(Canvas c) {
        Paint p = new Paint();
        zorro(c,500,0,25);

    }

    public void zorro(Canvas c, int x, int y,int tamano) {
        Paint p = new Paint();
        int fila =0;
        int columna =0;
        int pos=0;
        for (int i = 0; i < zorro.length; i++) {
                if (zorro[i] == 0) { //NADA
                }
                if (zorro[i] == 1) { //NEGRO
                    p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
                    p.setColor(Color.rgb(242,212,171));
                    p.setStyle(Paint.Style.FILL);
                  /* p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO*/
                    dibujarCuadrado(c,Color.BLACK,x+columna,y+fila,tamano,tamano);
                }
            if (zorro[i] == 2) { //ROJO
                p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
                p.setColor(Color.rgb(255, 51, 0));
                p.setStyle(Paint.Style.FILL);
                  /* p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO*/
                dibujarCuadrado(c,(Color.rgb(255, 51, 0)),x+columna,y+fila,tamano,tamano);
            }
            if (zorro[i] == 3) { //CREMA
                p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
                p.setColor(Color.rgb(255, 255, 230));
                p.setStyle(Paint.Style.FILL);
                  /* p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO*/
                dibujarCuadrado(c,(Color.rgb(255, 255, 230)),x+columna,y+fila,tamano,tamano);
            }
                columna+=tamano; //CADA VEZ QUE ENTRA SE LE SUMA 10 A LA COLUMNA Y 1 A LA POSICION
            pos++;
            if(pos==28){
                columna=0;
                fila+=tamano;
                pos=0;
            }

            System.out.println(""+columna);
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