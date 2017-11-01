package mx.edu.ittepic.dadm_minigameu3_anacarolina_zulmaisabel;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Random;

public class PrincipalTablero extends AppCompatActivity {
    Vibrator sistemavibrador;
    Musica reproductor;
    boolean juego=true;
    CountDownTimer global;
    Musica pikaerror,pikacorrecto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getSupportActionBar().hide();
        setContentView(new Tablero(this));
        sistemavibrador = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        reproductor= new Musica(getApplicationContext(),R.raw.memu,true);
        reproductor.setVolumen((float)0.3);
        reproductor.reproducir();
        pikaerror=new Musica(getApplicationContext(),R.raw.pikadeath,false);
        pikacorrecto=new Musica(getApplicationContext(),R.raw.pipika,false);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            reproductor.detener();
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onResume() {
        super.onResume();
        reproductor.reproducir();
    }
    @Override
    protected void onPause() {
        super.onPause();
        reproductor.detener();
    }
    public class Tablero extends View {
        CountDownTimer quitar;
        Icono imgA[], imgB[], imagentocada;
        int numeros = 10;
        int carta2 = 0, carta1;
        // boolean turno = false;
        //  boolean tacados
        int turno = 0;
        Sprite fondo;
        float xv,yv;
        CountDownTimer tiempo;
        int drawablesA[] = {R.drawable.pokebola, R.drawable.pokebola, R.drawable.pokebola,
                R.drawable.pokebola, R.drawable.pokebola, R.drawable.pokebola,
                R.drawable.pokebola, R.drawable.pokebola, R.drawable.pokebola,
                R.drawable.pokebola, R.drawable.pokebola, R.drawable.pokebola};
        int drawablesB[] = {
            /*   col1  fila 1  */
                R.drawable.charmander,
                R.drawable.pikamemo ,
            /*   col1  fila 2  */
                R.drawable.lol,
                R.drawable.jiggly,
            /*   col2  fila 1  */
                R.drawable.sai,
                R.drawable.sai,
            /*   col2  fila 2  */
                R.drawable.charmander,
                R.drawable.pip,
            /*   col3  fila 1 */
                R.drawable.jiggly,
                R.drawable.pikamemo,
            /*   col3  fila 2  */
                R.drawable.lol,
                R.drawable.pip
        };
        public Tablero(Context context) {
            super(context);
            Resolucion();
            imgA = new Icono[12];
            imgB = new Icono[12];
            float yesA[] = {0, (float)(yv/5.48571428571),  (float)(yv/2.74285714286),  (float)(yv/1.82857142857), 0, (float)(yv/5.48571428571), (float)(yv/2.74285714286),  (float)(yv/1.82857142857), 0, (float)(yv/5.48571428571), (float)(yv/2.74285714286),  (float)(yv/1.82857142857)}; //coordenadas de y pokebolas
            fondo = new Sprite(BitmapFactory.decodeResource(getResources(),R.drawable.fondo3),0,0,yv);
            float equisA[] = {(float)(xv/10.8), (float)(xv/10.8), (float)(xv/10.8),(float)(xv/10.8), (float)(xv/2.7), (float)(xv/2.7), (float)(xv/2.7), (float)(xv/2.7), (float)(xv/1.54285714286), (float)(xv/1.54285714286), (float)(xv/1.54285714286), (float)(xv/1.54285714286)}; //coordenadas de x pokebolas
            float pos[][]={
                    {(float)(xv/9),(float)(yv/96)},
                    {(float)(xv/9),(float)(yv/5.48571428571)},
                    {(float)(xv/9),(float)(yv/2.74285714286)},
                    {(float)(xv/9),(float)(yv/1.82857142857)},
                    {(float)(xv/2.57142857143),(float)(yv/96)},
                    {(float)(xv/2.57142857143),(float)(yv/5.48571428571)},
                    {(float)(xv/2.57142857143),(float)(yv/2.74285714286)},
                    {(float)(xv/2.57142857143),(float)(yv/1.82857142857)},
                    {(float)(xv/1.54285714286),(float)(yv/96)},
                    {(float)(xv/1.54285714286),(float)(yv/5.48571428571)},
                    {(float)(xv/1.54285714286),(float)(yv/2.74285714286)},
                    {(float)(xv/1.54285714286),(float)(yv/1.82857142857)}
            };
            String pares[] = {"A", "B", "C", "D", "E", "F"};
            int pa[] = { // 0=CHARMANDER 1=PIKACHU
                /*   col1  fila 1  */
                    0, 1,                   //AQUI DEPENDIENDO DEL ORDEN DE LA FOTO MOVERIAS AQUI LOS PARES
                /*   col1  fila 2  */
                    4, 3,
                /*   col2  fila 1  */
                    5, 5,
                /*   col2  fila 2  */
                    0, 2,
                /*   col3  fila 1 */
                    3, 1,
                /*   col3  fila 2  */
                    4, 2};

            for (int i = 0; i < imgA.length; i++) { //DE SQUI PARA ABAJO NO CAMBIES NADA
                imgA[i] = new Icono(BitmapFactory.decodeResource(getResources(), drawablesA[i]), equisA[i], yesA[i], true, "",true,(float)(yv/6.4));
            }
            int p = 0;
            for (int i = 0; i < imgB.length; i++) {
                imgB[i] = new Icono(BitmapFactory.decodeResource(getResources(), drawablesB[i]),pos[i][0],pos[i][1], false, pares[pa[i]],false,(float)(yv/6.4));
            }
            quitar = new CountDownTimer(5000, 1) {
                @Override
                public void onTick(long millisUntilFinished) {
                    invalidate();
                }

                @Override
                public void onFinish() {
                    cancel();
                }
            };

            tiempo= new CountDownTimer(1000,1) {
                @Override
                public void onTick(long millisUntilFinished) {

                }
                @Override
                public void onFinish() {

                }
            };
            global= new CountDownTimer(1000,1) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    juego=true;
                    invalidate();
                }
            };
        }
        public void onDraw(Canvas c) {
            Paint p = new Paint();
            p.setColor(Color.BLACK);
            c.drawRect(0, 0, xv, yv, p);
            c.drawBitmap(fondo.imagen, fondo.x, fondo.y, p);
            for (int i = 0; i < imgA.length; i++) {
                if (imgA[i].visible) {
                    c.drawBitmap(imgA[i].imagen, imgA[i].x, imgA[i].y, p);
                }
                if (imgB[i].visible) {
                    c.drawBitmap(imgB[i].imagen, imgB[i].x, imgB[i].y, p);
                }
            }
        }
        public boolean onTouchEvent(MotionEvent me) {
            if (me.getAction() == MotionEvent.ACTION_DOWN) {
                if(juego)
                {
                    for (int i = 0; i < imgB.length; i++) {
                        if (imgB[i].estaenArea(me.getX(), me.getY())) {
                            if(!imgB[i].getActivo() &&  !imgB[i].getVisible()){
                                if (turno <= 2) {
                                    carta2 = i;
                                    numeros = i;
                                    if (turno == 0) {
                                        carta1 = carta2;
                                    }
                                    imgB[carta2].setVisible(true);
                                    invalidate();
                                    turno++;
                                }
                            }
                        }

                    }
                }
            }
            if (me.getAction() == MotionEvent.ACTION_UP) {
                if(juego)
                {
                    if (turno >=2) {
                        System.out.println("C"+imgB[carta1].getPar() +" "+imgB[carta2].getPar() );
                        if (imgB[carta1].getPar() == imgB[carta2].getPar()) {
                            System.out.println("CORRECTO");
                            imgA[carta1].setVisible(false);
                            imgA[carta2].setVisible(false);
                            imgB[carta1].setActivo(true);
                            imgB[carta2].setActivo(true);
                            turno=0;
                            pikacorrecto.reproducir();
                            invalidate();
                        }
                        else
                        {
                            pikaerror.reproducir();
                            global.start();
                            System.out.println("ERROR");
                            sistemavibrador.vibrate(500);
                            turno=0;
                            imgB[carta2].setVisible(false);
                            imgB[carta1].setVisible(false);
                            juego=false;
                        }
                    }
                }
            }
            System.out.println("C1:" + carta1 + "  C2: " + carta2 + " TURNO:" + turno);
            return true;
        }
        public void Resolucion() {
            WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            xv = size.x;
            yv = size.y;
        }
    }
}
