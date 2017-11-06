package mx.edu.ittepic.dadm_u4_ejercicio5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by UsuarioPrueba on 06/11/2017.
 */

public class Lienzo extends View {
    Bitmap img1,img2,img3,img4;
    public Lienzo(Context context) {
        super(context);

        img1 = BitmapFactory.decodeResource(getResources(),R.drawable.amiguitas);
        img2 =BitmapFactory.decodeResource(getResources(),R.drawable.sad);
        img3 =BitmapFactory.decodeResource(getResources(),R.drawable.face);
        img4 =BitmapFactory.decodeResource(getResources(),R.drawable.o);
    }

    public void onDraw(Canvas c){

        Paint p = new Paint();
        c.drawBitmap(img1,200,400,p);
        c.drawBitmap(img2,600,400,p);
        c.drawBitmap(img3,200,800,p);
        c.drawBitmap(img4,600,800,p);

    }

}
