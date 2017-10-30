package mx.edu.ittepic.dadm_minigameu3_anacarolina_zulmaisabel;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.Random;

/**
 * Created by mario on 30/10/2017.
 */


public class Tablero extends View {
    CountDownTimer quitar;
    Icono imgA[], imgB[], imagentocada;
    int numeros = 10;
    int carta2 = 0, carta1;
    // boolean turno = false;
    //  boolean tacados
    int turno = 0;
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
        float yesA[] = {40, 250,  500,  800, 40, 250, 500, 800, 40, 250, 500, 800}; //coordenadas de y pokebolas
        float equisA[] = {20, 20, 20, 20, 270, 270, 270, 270, 510, 510, 510, 510}; //coordenadas de x pokebolas
        float pos[][]={
                {50,60},
                {47,275},
                {47,520},
                {47,820},
                {295,50},
                {295,260},
                {305,520},
                {305,820},
                {540,60},
                {540,275},
                {540,520},
                {540,820}
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
            imgA[i] = new Icono(BitmapFactory.decodeResource(getResources(), drawablesA[i]), equisA[i], yesA[i], true, "",true);
        }
        int p = 0;
        for (int i = 0; i < imgB.length; i++) {
            imgB[i] = new Icono(BitmapFactory.decodeResource(getResources(), drawablesB[i]),pos[i][0],pos[i][1], false, pares[pa[i]],false);
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
    }
    public void onDraw(Canvas c) {
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        c.drawRect(0, 0, 1000, 1500, p);
        for (int i = 0; i < imgA.length; i++) {
            if (imgA[i].visible == true) {
                c.drawBitmap(imgA[i].imagen, imgA[i].x, imgA[i].y, p);
            }
            if (imgB[i].visible == true) {
                c.drawBitmap(imgB[i].imagen, imgB[i].x, imgB[i].y, p);
            }
        }
    }
    public boolean onTouchEvent(MotionEvent me) {
        if (me.getAction() == MotionEvent.ACTION_DOWN) {
            for (int i = 0; i < imgB.length; i++) {
                if (imgB[i].estaenArea(me.getX(), me.getY())) {
                    if(!imgB[i].getActivo()){

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
        if (me.getAction() == MotionEvent.ACTION_UP) {
            if (turno >=2) {
                System.out.println("C"+imgB[carta1].getPar() +" "+imgB[carta2].getPar() );
                if (imgB[carta1].getPar() == imgB[carta2].getPar()) {
                    System.out.println("CORRECTO");
                    imgB[carta1].setActivo(true);
                    imgB[carta2].setActivo(true);
                    turno=0;
                }
                else
                {

                    turno=0;
                    imgB[carta2].setVisible(false);
                    imgB[carta1].setVisible(false);
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
    public static float[] Randomize(float[] arr) {
        float[] randomizedArray = new float[arr.length];
        System.arraycopy(arr, 0, randomizedArray, 0, arr.length);
        Random rgen = new Random();
        for (int i = 0; i < randomizedArray.length; i++) {
            int randPos = rgen.nextInt(randomizedArray.length);
            float tmp = randomizedArray[i];
            randomizedArray[i] = randomizedArray[randPos];
            randomizedArray[randPos] = tmp;
        }
        return randomizedArray;
    }

}
