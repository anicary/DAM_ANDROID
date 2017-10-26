package mx.edu.ittepic.dadm_u3_ana_carolina_mondragon_rangel_coposnieve;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.provider.Settings;

import java.util.Random;

/**
 * Created by UsuarioPrueba on 14/10/2017.
 */

 public class Copos {
    public float x,y,tamx,tamy;
    public boolean estado=false;
    CountDownTimer anim;
    int limite=0;
    Random xrandom;
    public Copos(float x, float y,float tamx, float tamy){
        this.x=x;
        this.y=y;
        this.tamx=tamx;
        this.tamy=tamy;
        xrandom = new Random();
    }
    public Canvas Dibujar(Canvas c){
        Paint p = new Paint();
        //SOMBRA
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        c.drawCircle(x+5,y+5,tamx,p);
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.FILL);
        c.drawCircle(x,y,tamx,p);

        return c;
    }
    public void setEstado(boolean estado){
        this.estado=estado;
    }
    float velocidad=0;
    float posxini=0;
    float posyini=0;
    boolean estado1=true;
    public void animacion(){
        if (tamx>=15 && tamx<=30)
        {
            velocidad= (float) 1.5;
        }
        if (tamx>=10 && tamx<15)
        {
            velocidad=1;
        }
        if (tamx>=0 && tamx<10)
        {
            velocidad= (float) 0.5;
        }
        posxini=x; posyini=y;
        anim=new CountDownTimer(1000,1) {//TIEMPO Y VECES
            @Override
            public void onTick(long l) {
                if (estado) { //CODIGOZIGZAG
                    if(x<=(posxini+(tamx/2)) && estado1)
                    {
                        x = (x + velocidad);
                    }
                    else
                    {
                        estado1=false;

                    }
                    if(x>=(posxini) && !estado1)
                    {
                        x = (x - velocidad);
                    }
                    else
                    {
                        estado1=true;
                    }

                    y=+(y+velocidad);
                    if (y>=limite)
                    {
                        y=0;
                        x=xrandom.nextInt(1000); //CREA COPITOS RANDOM DE 0 A 1000
                        posxini=x;
                        posyini=y;
                    }

                } else
                {
                   posxini=x;
                    y=-tamx;
                    posyini=y;
                }
            }
            @Override
            public void onFinish() {
                anim.start();
            }
        };
        anim.start();
    }
    public  void settamanoy(int limite) //ENVIA EL TAMAÑO DE LA PANTALLA
    {
        this.limite=limite;

    }
}
