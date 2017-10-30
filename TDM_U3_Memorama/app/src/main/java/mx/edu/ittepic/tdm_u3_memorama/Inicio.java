package mx.edu.ittepic.tdm_u3_memorama;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Inicio extends View{
    Icono im1, im2,im3;
    public Inicio(Context context) {
        super(context);

        im1 = new Icono(BitmapFactory.decodeResource(getResources(), R.drawable.fondo1),20,670,true,"",false);
        im2 = new Icono(BitmapFactory.decodeResource(getResources(), R.drawable.boton1),130,350,true,"",false);
        im3 = new Icono(BitmapFactory.decodeResource(getResources(), R.drawable.logo),50,-180,true,"",false);
    }
    public void onDraw (Canvas c) {
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        c.drawRect(0, 0, 1000, 1500, p);
        c.drawBitmap(im1.imagen, im1.x, im1.y, p);
        c.drawBitmap(im2.imagen, im2.x, im2.y, p);
        c.drawBitmap(im3.imagen, im3.x, im3.y, p);
    }
    public boolean onTouchEvent(MotionEvent me){
        if (me.getAction() == MotionEvent.ACTION_DOWN){
            //Si presiono
            if (im2.estaenArea(me.getX(), me.getY())){
                Intent este=new Intent(getContext(), PrincipalTablero.class);
                getContext().startActivity(este);
            }
        }

        invalidate();
        return true;
    }
}
