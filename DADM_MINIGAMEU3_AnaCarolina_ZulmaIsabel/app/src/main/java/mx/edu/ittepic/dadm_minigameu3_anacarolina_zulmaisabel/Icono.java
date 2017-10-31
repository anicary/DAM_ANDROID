package mx.edu.ittepic.dadm_minigameu3_anacarolina_zulmaisabel;

import android.graphics.Bitmap;

public class Icono {
    Bitmap imagen, imagen2;
    float x,y;
    public boolean visible,activo;
    String par;
    public Icono(Bitmap i, float equis, float ye, boolean visible,String par,boolean activo,float tam){
        imagen = i;
        imagen=escalado(imagen, tam, true);
        x = equis;
        y = ye;
        this.visible=visible;
        this.par=par;
        this.activo=activo;
    }

    public boolean estaenArea(float posX, float posY){
        if(posX >= x && posX <= (x + imagen.getWidth())){
            if(posY >= y && posY <= (y + imagen.getHeight())){
                return true;
            }
        }
        return false;
    }
    public void setVisible(boolean visible){
        this.visible=visible;
    }
    public boolean getVisible()
    {
        return  visible;
    }
    public String getPar()
    {
        return par;
    }
    public void setActivo(boolean activo)
    {
        this.activo=activo;
    }
    public boolean getActivo()
    {
        return activo;
    }

    public static Bitmap escalado(Bitmap imgentrada, float tamanio,  boolean filtro) {
        float ratio = Math.min((float) tamanio / imgentrada.getWidth(), (float) tamanio / imgentrada.getHeight());
        int width = Math.round((float) ratio * imgentrada.getWidth());
        int height = Math.round((float) ratio * imgentrada.getHeight());
        Bitmap nuevaImagen = Bitmap.createScaledBitmap(imgentrada, width, height, filtro);
        return nuevaImagen;
    }


}
