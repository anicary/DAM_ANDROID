package mx.edu.ittepic.dadm_minigameu3_anacarolina_zulmaisabel;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.provider.Settings;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by UsuarioPrueba on 22/10/2017.
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
    public String getEtiqueta()
    {
        return Etiqueta;
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

    public boolean onColission(SpriteAnim otro) {
        int xmenos= (dibujo.imagen.getWidth()/2);
        int ymenos= (dibujo.imagen.getHeight()/2);
       if (otro.hitArea((dibujo.x+xmenos), (dibujo.y+ymenos))) {
            System.out.println("1"+dibujo.x+" "+dibujo.y);
            return true;
        }
       /* if (otro.hitArea((dibujo.x) + dibujo.imagen.getWidth(), dibujo.y))

        {
            System.out.println("ARRIBA "+dibujo.x+" "+dibujo.y);
            return true;
        }
        if (otro.hitArea(dibujo.x + dibujo.imagen.getWidth(), dibujo.y + dibujo.imagen.getHeight())) {
            System.out.println(""+dibujo.x+" "+dibujo.y);
            return true;
        }*/
        /*if (otro.hitArea(dibujo.x,  + dibujo.imagen.getHeight()))
        {
            System.out.println(""+dibujo.x+" "+dibujo.y);
            return true;
        }*/
        return false;
    }
    public boolean onColission(Sprite otro) {
        if (otro.hitArea(dibujo.x, dibujo.y)) {
            System.out.println("1"+dibujo.x+" "+dibujo.y);

            return true;
        }
        if (otro.hitArea(dibujo.x + dibujo.imagen.getWidth(), dibujo.y))
        {
            return true;
        }
        if (otro.hitArea(dibujo.x + dibujo.imagen.getWidth(), dibujo.y + dibujo.imagen.getHeight())) {
            return true;
        }
        if (otro.hitArea(dibujo.x,  + dibujo.imagen.getHeight()))
        {
            return true;
        }
        return false;
    }
    public boolean onColission(Pikachu otro) {
        if (otro.hitArea(dibujo.x+(dibujo.x/3), dibujo.y)) {
           // System.out.println("1"+dibujo.x+" "+dibujo.y);
            System.out.println("TOCOOOOOOOOO 1");
            return true;
        }
      /*  if (otro.hitArea(dibujo.x + dibujo.imagen.getWidth(), dibujo.y))
        {
            System.out.println("TOCOOOOOOOOO 2");
            return true;
        }
        if (otro.hitArea(dibujo.x + dibujo.imagen.getWidth(), dibujo.y + dibujo.imagen.getHeight())) {
            return true;
        }
        if (otro.hitArea(dibujo.x,  + dibujo.imagen.getHeight()))
        {
            return true;
        }*/
        return false;
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
