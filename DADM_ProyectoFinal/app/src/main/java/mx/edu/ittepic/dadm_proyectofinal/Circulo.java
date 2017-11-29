package mx.edu.ittepic.dadm_proyectofinal;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Circulo {
    float x,y,radio,velocidad=0;
    boolean estado=false;
    float xlim=0,ylim=0;
    public Circulo(float x,float y, float radio, boolean estado)
    {
        this.x=x;
        this.y=y-radio;
        this.radio=radio;
        this.estado=estado;
        if (radio>150 && radio<=200)
        {
            velocidad=1;
        }
        if (radio>100 && radio<=150)
        {
            velocidad= (float) 2.5;
        }
        if (radio>0 && radio<=100)
        {
            velocidad=(float) 2.5;
        }

    }
    public void pintar(Canvas c, Paint p){
        if(estado)
        {
            Paint p2 = new Paint();
            p2.setColor(Color.BLACK);
            p2.setStyle(Paint.Style.FILL);
            c.drawCircle(x+2, y+2, radio+5, p2);
            c.drawCircle(x, y, radio, p);
        }
    }
    public void setlimites(float xlim,float ylim){
        this.xlim=xlim;
        this.ylim=ylim;
    }
    public boolean tocando(float xp, float yp) {
        if (xp >= (x-radio) && xp <= ((x) +  radio)) {
            if (yp >= (y-radio) && yp <= ((y) + radio )) {
                return true;
            }
            else
            {
                return false;
            }
        }
        else {
            return false;
        }
    }
    public void agregar()
    {
        estado=true;
    }
    public float getVelocidad()
    {
        return velocidad;
    }
    public void setEstado(boolean estado)
    {
        this.estado=estado;
    }
    public boolean getEstado()
    {
        return estado;
    }
    public void  mover(float x,float y){

        if(estado)
        {
            if (this.x>0)
            {
                if (this.y>0)
                {
                    if (this.x<(xlim-(radio)))
                    {
                        if (this.y<(ylim-(radio)))
                        {
                            this.x+=-(x*velocidad);
                            this.y+=(y*velocidad);
                        }
                        else
                        {
                            this.y+=-(radio*2);
                        }
                    }
                    else
                    {
                        this.x+=-(radio*2);
                    }

                }
                else {
                    this.y+=radio*2;
                }
            }
            else
            {
                this.x+=radio*2;
            }
        }
    }
}

