package mx.edu.ittepic.dadm_u2_ejercicio9;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by UsuarioPrueba on 05/10/2017.
 */

public class Lienzo extends View {
    CountDownTimer timer;
    Bitmap img;
    int x;
    boolean movimiento;

    public Lienzo(Context context) {
        super(context);

        img = BitmapFactory.decodeResource(getResources(),R.drawable.logo);

        x=200;
        movimiento = true;

        timer = new CountDownTimer(5000,1) {
            @Override
            public void onTick(long l) {

                if(movimiento ==true){
                    x+=5;
                }else{
                    x-=5;
                }
                if(x>=(getWidth()-img.getWidth())){
                    movimiento = false;
                }
                if(x<0){
                    movimiento=true;
                }
                invalidate(); //Vuelve a ejecutar el onDraw
            }

            @Override
            public void onFinish()
            {
                timer.start();
            }
        };
        timer.start();
    }
    public void onDraw(Canvas c){
        Paint p = new Paint();
        c.drawBitmap(img,x,300,p);

    }
    public boolean onTouchEvent(MotionEvent e){// para poder mover la figura
        //este metodo se invoca cuando tocas la pantalla siempre y cuando la pantalla sea un VIEW
        float x=e.getX(); //obtienes la coordenada del toque en X
        float y=e.getY();//coordenada de toque en y

        //el toque tiene 3 situaciones, el toque de la pantalla nos genera 3 eventos
        //1.-precionar
        switch (e.getAction()) { //REGRESA EL EVENTO
            case MotionEvent.ACTION_DOWN: //ESTO ES PRECIONAR
                //EL GETCONTEXT EN VEZ DE THIS
                Toast.makeText(getContext(),"PRESIONASTE",Toast.LENGTH_SHORT).show();
                break;

            //2.-mover o arrastrar, este puede pasar o no
            case MotionEvent.ACTION_MOVE:
                break;

            //3.-soltar
            case MotionEvent.ACTION_UP:
                Toast.makeText(getContext(),"SOLTASTE X: "+x+"Y:"+y,Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }
}
