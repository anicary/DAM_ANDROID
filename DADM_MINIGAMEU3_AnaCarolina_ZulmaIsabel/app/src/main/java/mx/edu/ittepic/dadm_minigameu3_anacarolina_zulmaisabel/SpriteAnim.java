package mx.edu.ittepic.dadm_minigameu3_anacarolina_zulmaisabel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;

public class SpriteAnim {
    Bitmap [] imagen;
    float x, y, tam,saltovelo,posinicialy;
    int id;
    int pos=5;
    int posarr=0;
    boolean salto=true;
    boolean anim=true;
    CountDownTimer relog,bajar,saltar,global;
    public SpriteAnim(Bitmap [] imagen, float x, float y,float tam) {
        this.imagen= new Bitmap[imagen.length];
        posarr=imagen.length;
        for(int i=0;i<imagen.length;i++)
        {
            imagen[i]=escalado(imagen[i], tam, true);
            this.imagen[i] = imagen[i];
        }
        this.posinicialy=y;
        this.x = x;
        this.y = y;
        this.tam=tam;
        relog= new CountDownTimer(1000,50) {
            @Override
            public void onTick(long millisUntilFinished) {
             if(anim)
             {
                if(pos<1){
                    pos=5;
                }else
                {
                    pos--;
                }
             }
            }

            @Override
            public void onFinish() {

                relog.start();
            }
        };
        relog.start();


    }
    public void animacionX(float velocidad)
    {
        this.x+=velocidad;
    }
    public boolean hitArea(float xp, float yp) {
        for(int i=0;i<imagen.length;i++)
        {
            if (xp >= x && xp <= (x + imagen[0].getWidth())) {
                if (yp >= y && yp <= (y + imagen[0].getHeight())) {
                    return true;
                }
            }
        }
        return false;
    }
    public float getX() {   return this.x = x;    }
    public float getY() {   return this.y = y;
    }
    public static Bitmap escalado(Bitmap imgentrada, float tamanio,  boolean filtro) {
        float ratio = Math.min((float) tamanio / imgentrada.getWidth(), (float) tamanio / imgentrada.getHeight());
        int width = Math.round((float) ratio * imgentrada.getWidth());
        int height = Math.round((float) ratio * imgentrada.getHeight());
        Bitmap nuevaImagen = Bitmap.createScaledBitmap(imgentrada, width, height, filtro);
        return nuevaImagen;
    }
    public void dibujar(Canvas c)
    {
        Paint p= new Paint();
        c.drawBitmap(imagen[pos], x,y, p);
    }
    public  void animINI()
    {
        anim=true;
        relog.start();
    }
    public  void animSTOP()
    {
        anim=false;
        relog.cancel();
        System.out.println("detener animacion");
    }
    boolean estado=false;
    public void saltar(boolean dato){
       salto=false;
        estado=true;
        /*if(anim)
        {
            if(salto)
            {
                bajar= new CountDownTimer(800,1) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if(posinicialy>=y)
                        {
                            y+=saltovelo;
                        }
                    }
                    @Override
                    public void onFinish() {
                        salto=true;
                        bajar.cancel();
                    }
                };
                saltar= new CountDownTimer(800,1) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if(!salto)
                        {
                            if((posinicialy-(imagen[0].getHeight())) <=y)
                            {
                                y-=saltovelo;
                            }
                        }
                    }
                    @Override
                    public void onFinish() {
                        bajar.start();
                        saltar.cancel();
                    }
                };
                salto=false;
                saltar.start();
            }
        }*/
    }
    public boolean getSalto() {
        return salto;
    }
    public float getPosinicialy()
    {
        return posinicialy;
    }
    public boolean getEstado()
    {
        return  estado;
    }



}
