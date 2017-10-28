package mx.edu.ittepic.dadm_minigameu3_anacarolina_zulmaisabel;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by mario on 27/10/2017.
 */

public class Objetos {
    Sprite dibujo;
    String Etiqueta="";
    Context contexto;
    int resulusionx,resulusiony;
    boolean estado=false;
    public Objetos(Context contexto,int imagen, float x, float y,float tam, String Etiqueta)
    {
        this.contexto=contexto;
        dibujo= new Sprite(BitmapFactory.decodeResource(contexto.getResources(), imagen),x,y,tam);
        this.Etiqueta=Etiqueta;
        Resolucion();
    }
    public void Resolucion() {
        WindowManager wm = (WindowManager) contexto.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        resulusionx = size.x;
        resulusiony = size.y;
        System.out.println("RESOLUCION "+resulusionx+","+resulusiony);

    }
    public void dibujarObjeto(Canvas c)
    {
        Paint p = new Paint();
        if(estado)
        {
            c.drawBitmap(dibujo.imagen, dibujo.x, dibujo.y, p);
        }
    }
    public void moverX(float velocidad)
    {
        if(estado)
        {
            if( dibujo.x<=(0-200))
            {
                this.estado=false;
                dibujo.x=resulusionx+200;
            }else{
                dibujo.x+=velocidad;
            }
        }else
        {
            this.estado=false;
            dibujo.x=resulusionx+200;
        }
    }
    public void setEstado(boolean estado)
    {
        this.estado=estado;
    }
    public boolean getEstado()
    {
        return estado;
    }
}
