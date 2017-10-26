package mx.edu.ittepic.dadm_u2_ejercicio7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by UsuarioPrueba on 03/10/2017.
 */

public class Lienzo extends View {
    Bitmap img;

    public Lienzo(Context context) {
        super(context);
        
        img = BitmapFactory.decodeResource(getResources(),R.drawable.imagen);
    }
    public void onDraw(Canvas c){
        Paint p = new Paint();
        p.setColor(Color.BLUE);

        c.drawCircle(100,100,80,p);

        p.setColor(Color.RED);
        p.setTextSize(80);
        c.drawText("Ana Carolina",200,800,p);

        p.setStyle(Paint.Style.STROKE); //STROKE DIBUJA EL CONTORNO
        p.setColor(Color.BLACK);
        p.setStrokeWidth(10); // HACE EL CONTORNO MAS GRUESO
        c.drawRect(200,200,400,350,p);

        p.setStyle(Paint.Style.FILL); //DIBUJA EL RELLENO
        p.setColor(Color.GREEN);
        c.drawRect(200,550,600,750,p);
        p.setStyle(Paint.Style.STROKE); //DIBUJA EL CONTRONO
        p.setColor(Color.MAGENTA);
        p.setStrokeWidth(20);
        c.drawRect(200,550,600,750,p);

        c.drawBitmap(img,400,1000,p);
    }
}
