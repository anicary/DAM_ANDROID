package mx.edu.ittepic.dadm_u4_ejercicio5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import static java.lang.Thread.sleep;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
    public SensorManager sensores; //SENSOR MANAGER
    public long ultimaact; //ES UNA VARIABLE PARA DETECTAR EL SHAKE
    Thread thread;
    float posicionesOriginales[][]={
            {200,400},
            {600,400},
            {200,800},
            {600,800}
    };
    float posiciones[][]={
            {200,400},
            {600,400},
            {200,800},
            {600,800}
    };
    boolean [] mover={false,false,false,false};
    Bitmap imgenes[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Lienzo(this));
        sensores = (SensorManager) getSystemService(SENSOR_SERVICE); //OBTIENE EL SENSOR DE TELEFONO
        ultimaact = System.currentTimeMillis(); //OBTIENE LOS MILISEGUNDOS ACTUALES



    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) { //CUANDO EL SENSOR DETECTA UN CAMBIO

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) { //TIPO DE SENSOR
            float[] valores = sensorEvent.values;
            float x = valores[0];
            float y = valores[1];
            float z = valores[2];
            //MULTIPLICAS LAS POSICIONES Y SE DIVIDE  POR EL SENSOR DE LA TIERRA Y GRAVEDAD
            float aceleracionraiz = ((x * x + y * y + z * z)/(SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH));
            long tiempoactual = sensorEvent.timestamp; //ASIGNA EL TIEMPO EN EL QUE OCURRIO EL TOQUE
            if (aceleracionraiz >= 5) //OCURRE UN SHAKE
            {
                if (tiempoactual - ultimaact < 200) {

                    return;
                }
                for (int i=0;i<imgenes.length;i++){
                    posiciones[i][0]=posicionesOriginales[i][0];
                    posiciones[i][1]=posicionesOriginales[i][1];
                    mover[i]=false;
                }
                System.out.println("SHAKEEE");
            }
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
    public class Lienzo extends View {
        public Lienzo(Context context) {
            super(context);
            imgenes= new Bitmap[4];
            imgenes[0] = BitmapFactory.decodeResource(getResources(),R.drawable.amiguitas);
            imgenes[1]  =BitmapFactory.decodeResource(getResources(),R.drawable.sad);
            imgenes[2] =BitmapFactory.decodeResource(getResources(),R.drawable.face);
            imgenes[3] =BitmapFactory.decodeResource(getResources(),R.drawable.o);
            thread = new Thread() {
                @Override
                public void run() {
                    try {
                        while(true) {
                            sleep(50);
                            System.out.println("ME EJECUTO");
                            if(mover[0])
                            {
                               posiciones[0][0]-=5;
                                posiciones[0][1]-=5;
                            }
                            if(mover[1])
                            {
                                posiciones[1][0]+=5;
                                posiciones[1][1]-=5;
                            }
                            if(mover[2])
                            {
                                posiciones[2][0]-=5;
                                posiciones[2][1]+=5;
                            }
                            if(mover[3])
                            {
                                posiciones[3][0]+=5;
                                posiciones[3][1]+=5;
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    invalidate();
                                }
                            });

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            thread.start();

        }

        public boolean Area(float xp, float yp,float x,float y, Bitmap imagen) {
            if (xp >= x && xp <= (x + imagen.getWidth())) {
                if (yp >= y && yp <= (y + imagen.getHeight())) {
                    return true;
                }
            }
            return false;
        }
        public void onDraw(Canvas c){

            Paint p = new Paint();
            for(int i=0;i<imgenes.length;i++)
            {
                c.drawBitmap(imgenes[i],posiciones[i][0],posiciones[i][1],p);
            }
//            c.drawBitmap(img1,200,400,p);
//            c.drawBitmap(img2,600,400,p);
//            c.drawBitmap(img3,200,800,p);
//            c.drawBitmap(img4,600,800,p);
        }
        public boolean onTouchEvent(MotionEvent motionEvent) {
            float x=motionEvent.getX();
            float y=motionEvent.getY();
            if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {
                for (int i=0;i<imgenes.length;i++)
                {
                    if(Area(x,y,posiciones[i][0],posiciones[i][1],imgenes[i]))
                    {
                        mover[i]=true;
                        System.out.println("TOCADO "+i);
                    }
                }
            }

            return true;
        }
    }

}
