package mx.edu.ittepic.dadm_u3_ana_carolina_mondragon_rangel_coposnieve;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by UsuarioPrueba on 14/10/2017.
 */

public class Copos {
    int x,y,tamx,tamy;

    public Copos(int x, int y,int tamx, int tamy){
        this.x=x;
        this.y=y;
        this.tamx=tamx;
        this.tamy=tamy;
    }
    public Canvas Dibujar(Canvas c){
        Paint p = new Paint();
        //SOMBRA
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        c.drawCircle(x+5,y+5,tamx,p);

        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.FILL);
        c.drawCircle(x,y,tamx,p);

        return c;
    }
}
