package mx.edu.ittepic.dadm_minigameu3_anacarolina_zulmaisabel;

import android.content.Context;
import android.graphics.BitmapFactory;
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

import java.util.Random;

public class Pikasurf extends AppCompatActivity  implements SensorEventListener {
    public SensorManager sensores;
    public long ultimaact;
    int resulusionx,resulusiony;
    float x=0,y=0;
    Pikachu jugador;
    Sprite boton1;
    Sprite [] capas;
    float tamanoplayer=0;
    boolean juego=true;
    Objetos [] assets;
    int  layers[]={R.drawable.layer0,R.drawable.layer1,R.drawable.layer2,R.drawable.layer3,R.drawable.layer4};
    int  pixelArt[]={R.drawable.capsup,R.drawable.roca};
    int posrocas[]={
            500,
            700,
            900
    };
    int puntuacionGlobal;
    Musica reproductor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getSupportActionBar().hide();
        setContentView(new Pika(this));
        /* INICIAR COMPONENTES DEL JUEGO*/
        Resolucion();
        sensores = (SensorManager) getSystemService(SENSOR_SERVICE); //OBTIENE EL SENSOR DE TELEFONO
        ultimaact = System.currentTimeMillis(); //OBTIENE LOS MILISEGUNDOS ACTUALES
        jugador= new Pikachu(BitmapFactory.decodeResource(getResources(), R.drawable.pika),(resulusionx/3),(resulusiony/2)-50,"PIKA",(float)(resulusiony/3.5));
        tamanoplayer=jugador.getTamanoY();
        capas= new Sprite[layers.length];
        for(int i=0; i<layers.length ;i++)
        {
            capas[i] = new Sprite(BitmapFactory.decodeResource(getResources(),layers[i]),0,0,resulusiony*2);
        }
        assets = new Objetos[10];
        int posrock=0;
        for(int i=0;i< assets.length;i++)
        {
            if(posrock<2)
            {
                posrock++;
            }else
            {
                posrock=0;
            }
            if(i<3)
            {
                assets[i]=new Objetos(getApplication(),pixelArt[0],1920,posrocas[posrock],200,"CAPSUP");
            }else
            {

                assets[i]=new Objetos(getApplication(),pixelArt[1],resulusionx+300,posrocas[posrock],300,"PIEDRA");
            }

        }
        reproductor= new Musica(getApplicationContext(),R.raw.surfmusica,true);
        reproductor.reproducir();
        boton1= new Sprite(BitmapFactory.decodeResource(getResources(),R.drawable.botonsur),(resulusionx/2)-(resulusionx/9),(resulusiony/2)-50,resulusiony/2);
    }
    public void onSensorChanged(SensorEvent sensorEvent) { //CUANDO EL SENSOR DETECTA UN CAMBIO

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) { //TIPO DE SENSOR
            float[] valores = sensorEvent.values;
            x = valores[0];
            y = valores[1];
            float z = valores[2];
            if(juego)
            {
                if(x>=0 && x<=6)
                {
                    if(jugador.getY()>(resulusiony/2)-210)
                    {
                        jugador.sety(-15);
                    }

                }else
                {
                    if(x>7 && x<=10)
                    {
                        if(jugador.getY()<(resulusiony-jugador.getTamanoY()))
                        {
                            jugador.sety(15);
                        }

                    }
                }
            }
            //x5.51404
            // 8.49571
            System.out.println("x"+(x)+"  "+x+"   y:"+y);
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
        CountDownTimer ObjetosEntregar,global,puntuRelog;
        int pos=0, posgame=0;

        Random numero= new Random();
        public Pika (Context context) {
            super(context);
            puntuRelog= new CountDownTimer(1000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if(juego) {
                        puntuacionGlobal++;
                    }

                }

                @Override
                public void onFinish() {
                    puntuRelog.start();
                }
            };
            puntuRelog.start();
            global= new CountDownTimer(1000,1) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if(juego){

                    }
                    invalidate();
                }

                @Override
                public void onFinish() {
                    global.start();
                }
            };
            global.start();

            ObjetosEntregar=new CountDownTimer(10000,2000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if(juego){
                        posgame= numero.nextInt(assets.length-1);
                        assets[posgame].setEstado(true);
                    }
                }
                @Override
                public void onFinish() {
                    ObjetosEntregar.start();
                }
            };
            ObjetosEntregar.start();
           /* timpo= new CountDownTimer(1000,1) {
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
            timpo.start(); */
        }
        public void onDraw (Canvas c)
        {
            Paint p = new Paint();
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.rgb(59, 66, 167));
            c.drawPaint(p); //PARA DIBUJAR EL PAINT
            // cuadrado(c,Color.WHITE,xc,yc,50,50);
            for(int i=0;i<capas.length;i++)
            {
                c.drawBitmap(capas[i].imagen, capas[i].x, capas[i].y, p);
            }
            if(juego) {
                capas[3].animacionX(-3);
                capas[1].animacionX((float) -0.8);
                capas[4].animacionX((float) -3);
            }
            for(int i=0;i<assets.length;i++)
            {
                assets[i].dibujarObjeto(c);
                assets[i].moverX(-5);
            }
            c.drawBitmap(jugador.imagen, jugador.x, jugador.y, p);
            c.drawBitmap(boton1.imagen, boton1.x, boton1.y, p);
            p.setColor(Color.BLACK);
            p.setTextSize(resulusionx/40);
            p.setStyle(Paint.Style.FILL);
            c.drawText("PUNTUACION: "+puntuacionGlobal,resulusionx/38,resulusiony/22,p);
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
    }
    public void Resolucion() {
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        resulusionx = size.x;
        resulusiony = size.y;
        System.out.println("RESOLUCION "+resulusionx+","+resulusiony);

    }
}
