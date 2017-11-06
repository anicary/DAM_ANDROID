package mx.edu.ittepic.dadm_minigameu3_anacarolina_zulmaisabel;

import android.graphics.Bitmap;
public class Sprite {
    Bitmap imagen;
    float x, y;
    int id;
    public Sprite(Bitmap imagen, float x, float y,float tam) {
        imagen=escalado(imagen, tam, true);
        this.imagen = imagen;
        this.x = x;
        this.y = y;
    }
    public void mover(float x, float y) {
        this.x = x - (imagen.getWidth() / 2);
        this.y = y - (imagen.getHeight() / 2);
    }
    public void animacionX(float velocidad)
    {
        this.x+=velocidad;
    }
    public boolean hitArea(float xp, float yp) {
        if (xp >= x && xp <= (x + imagen.getWidth())) {
            if (yp >= y && yp <= (y + imagen.getHeight())) {
                return true;
            }
        }
        return false;
    }
    public float getX() {   return this.x = x;    }
    public float getY() {   return this.y = y;
    }
    public void setX(float x){
        this.x=x;
    }
    public static Bitmap escalado(Bitmap imgentrada, float tamanio,  boolean filtro) {
        float ratio = Math.min((float) tamanio / imgentrada.getWidth(), (float) tamanio / imgentrada.getHeight());
        int width = Math.round((float) ratio * imgentrada.getWidth());
        int height = Math.round((float) ratio * imgentrada.getHeight());
        Bitmap nuevaImagen = Bitmap.createScaledBitmap(imgentrada, width, height, filtro);
        return nuevaImagen;
    }
    public void moverX(float x)
    {
        this.x+=x;
    }
    public float getTamano()
    {
        return  imagen.getWidth();
    }
}
