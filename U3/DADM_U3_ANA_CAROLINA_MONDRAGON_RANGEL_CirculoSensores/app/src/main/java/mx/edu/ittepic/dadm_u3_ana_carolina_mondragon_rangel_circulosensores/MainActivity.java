package mx.edu.ittepic.dadm_u3_ana_carolina_mondragon_rangel_circulosensores;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    public SensorManager sensores; //SENSOR MANAGER
    public long ultimaact; //ES UNA VARIABLE PARA DETECTAR EL SHAKE
    Display display; //OBTENER RESOLUCION PANTALLA
    int ancho = 0;
    int alto = 0;
    Circulo[] circulos;
    CountDownTimer global;
    int contadorshake=0;
    float x=0,y=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Dibujo(this));

        sensores = (SensorManager) getSystemService(SENSOR_SERVICE); //OBTIENE EL SENSOR DE TELEFONO
        ultimaact = System.currentTimeMillis(); //OBTIENE LOS MILISEGUNDOS ACTUALES
        display = getWindowManager().getDefaultDisplay(); //ASIGNA EL TAMAÑO DE LA PANTALLA
        Point size = new Point(); //OBTENER X y Y
        display.getSize(size); //GUARDA LOS VALORES DE LA PANTALLA QUE LE RETORNARA AL POINT
        ancho = size.x;
        alto = size.y;
        Random xrandom = new Random(); // GENERAR CIRCULOS RANDOM
        circulos= new Circulo[200]; //MAX 200
        for (int i=0;i<circulos.length;i++){
            circulos[i]= new Circulo((ancho/2),(alto/2),xrandom.nextInt(200),false);
            circulos[i].setlimites(ancho,alto);
        }
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) { //CUANDO EL SENSOR DETECTA UN CAMBIO

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) { //TIPO DE SENSOR
            float[] valores = sensorEvent.values;
            x = valores[0];
            y = valores[1];
            float z = valores[2];
            //MULTIPLICAS LAS POSICIONES Y SE DIVIDE  POR EL SENSOR DE LA TIERRA Y GRAVEDAD
            float aceleracionraiz = ((x * x + y * y + z * z)/(SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH));
            long tiempoactual = sensorEvent.timestamp; //ASIGNA EL TIEMPO EN EL QUE OCURRIO EL TOQUE
            if (aceleracionraiz >= 5) //OCURRE UN SHAKE
            {
                if (tiempoactual - ultimaact < 200) {
                    return;
                }
                ultimaact = tiempoactual;
                if (contadorshake<circulos.length)
                {
                    circulos[contadorshake].agregar();

                }
                contadorshake++;

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
    public class Dibujo extends View {

        public Dibujo(Context context) {
            super(context);
            global = new CountDownTimer(1000, 1) {
                @Override
                public void onTick(long l) {
                    invalidate();
                }

                @Override
                public void onFinish() {
                    global.start();
                }
            };
            global.start();
        }

        public void onDraw(Canvas c) {
            Paint p = new Paint();
            p.setColor(Color.rgb(206, 101, 0));
            p.setStyle(Paint.Style.FILL);
            for (int i = 0; i < circulos.length; i++) {
                circulos[i].pintar(c, p);
                circulos[i].mover(x, y);
            }
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {

            if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {
                for (int i = 0; i < circulos.length; i++) {
                    if(circulos[i].tocando(motionEvent.getX(), motionEvent.getY())) {
                        circulos[i].setEstado(false);
                    }
                }

            }
            return true;
        }
    }

}


