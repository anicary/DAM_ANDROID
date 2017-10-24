package mx.edu.ittepic.dadm_minigameu3_anacarolina_zulmaisabel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Pikasurf extends AppCompatActivity  implements SensorEventListener {
    public SensorManager sensores; //SENSOR MANAGER
    public long ultimaact; //ES UNA VARIABLE PARA DETECTAR EL SHAKE
    int resulusionx,resulusiony;
    float x=0,y=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getSupportActionBar().hide();
        setContentView(new Pika(this));
        sensores = (SensorManager) getSystemService(SENSOR_SERVICE); //OBTIENE EL SENSOR DE TELEFONO
        ultimaact = System.currentTimeMillis(); //OBTIENE LOS MILISEGUNDOS ACTUALES
    }
    public void onSensorChanged(SensorEvent sensorEvent) { //CUANDO EL SENSOR DETECTA UN CAMBIO

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) { //TIPO DE SENSOR
            float[] valores = sensorEvent.values;
            x = valores[0];
            y = valores[1];
            float z = valores[2];
            System.out.println("x"+(x*.981));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    protected void onResume() {
        super.onResume();
        sensores.registerListener(this,
                sensores.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensores.unregisterListener(this);
    }

    public class Pika extends View{
        int xc=0,yc=0;
        CountDownTimer timpo;
        int pos=0;
        public Pika (Context context) {
            super(context);
            Resolucion();
            timpo= new CountDownTimer(1000,1) {
                @Override
                public void onTick(long millisUntilFinished) {
                    switch (pos){
                        case 0:
                            xc+=5;
                            if(xc<=(resulusionx-50))
                            {
                                pos=0;
                            }else
                            {
                                pos=1;
                            }
                            break;
                        case 1:
                            yc+=5;
                            if(yc<=(resulusiony-50))
                            {
                                pos=1;
                            }else
                            {
                                pos=2;
                            }
                            break;
                        case 2:
                            xc-=5;
                            if(xc>=0 )
                            {
                                pos=2;
                            }else
                            {
                                pos=3;
                            }
                            break;
                        case 3:
                            yc-=5;
                            if(yc>0)
                            {
                                pos=3;
                            }else
                            {
                                pos=0;
                            }
                            break;
                    }


                    System.out.println("POS "+pos+" xc"+xc);
                    invalidate();
                }

                @Override
                public void onFinish() {
                    timpo.start();
                }
            };
            timpo.start();
        }
        public void onDraw (Canvas c)
        {
            Paint p = new Paint();
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.rgb(59, 66, 167));
            c.drawPaint(p); //PARA DIBUJAR EL PAINT
            cuadrado(c,Color.WHITE,xc,yc,50,50);
        }
        public  void cuadrado (Canvas c, int color, float  x, float y, float tamx, float tamy)
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
        public void Resolucion() {
            WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            resulusionx = size.x;
            resulusiony = size.y;
            System.out.println("RESOLUCION "+resulusionx+","+resulusiony);

        }
    }
}
