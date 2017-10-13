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
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0


    };

    public Lienzo(Context context) {
        super(context);
    }

    public void onDraw(Canvas c) {
        Paint p = new Paint();
        zorro(c,0,0,5);

    }

    public void zorro(Canvas c, int x, int y,int tamano) {
        Paint p = new Paint();
        int fila =0;
        int columna =0;
        int pos=0;
        for (int i = 0; i < zorro.length; i++) {
            for (int e=0;e<28;e++){
                if (zorro[pos] == 0) {
                }
                if (zorro[pos] == 1) {
                    p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
                    p.setColor(Color.rgb(242,212,171));
                    p.setStyle(Paint.Style.FILL);
                  /* p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO*/
                    dibujarCuadrado(c,Color.BLACK,x+fila,y+columna,tamano,tamano);
                }
                columna+=tamano;
            }
            columna=0;
            fila+=tamano;
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