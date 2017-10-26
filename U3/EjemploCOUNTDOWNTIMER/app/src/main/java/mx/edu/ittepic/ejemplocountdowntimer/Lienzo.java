package mx.edu.ittepic.ejemplocountdowntimer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.View;
/**
 *
 */
public class Lienzo  extends View {
    int x=0,y=0;
    CountDownTimer timer;
    public Lienzo(Context context) {
        super(context);
        x=50;
        y=60;
        timer = new CountDownTimer(5000,1) {
            @Override
            public void onTick(long millisUntilFinished) {
                y+=5;
                invalidate();
            }

            @Override
            public void onFinish() {
                y=60;
                timer.start();
            }
        };
        timer.start();
    }
    public void onDraw(Canvas pincel) {
        Paint pintura =new Paint();
        Paint pintura2 =new Paint();
        pincel.drawColor(Color.rgb(221,160,221));
        pintura.setColor(Color.WHITE);
        pintura.setTextSize(55);
        pintura2.setTextSize(55);
        pintura2.setColor(Color.BLACK);
        pincel.drawText("ANA CAROLINA MONDRAGON RANGEL",x+3,y+3,pintura2);
        pincel.drawText("ANA CAROLINA MONDRAGON RANGEL",x,y,pintura);
    }
}
