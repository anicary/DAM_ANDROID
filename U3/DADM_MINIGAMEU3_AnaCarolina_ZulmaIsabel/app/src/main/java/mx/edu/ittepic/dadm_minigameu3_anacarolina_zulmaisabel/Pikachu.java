package mx.edu.ittepic.dadm_minigameu3_anacarolina_zulmaisabel;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class Pikachu {

    Bitmap imagen;
    float x, y;
    int id;
    String TAG;
    int resulusionx,resulusiony;
    public Pikachu(Bitmap imagen, float x, float y, String TAG, float tam) {
        imagen = escalado(imagen, tam, true);
        this.imagen = imagen;
        this.x = x;
        this.y = y;
        this.TAG = TAG;
    }
    public void mover(float x, float y) {
        this.x = x - (imagen.getWidth() / 2);
        this.y = y - (imagen.getHeight() / 2);
    }
    public void sety(float y){

        this.y+=y;
    }
    public boolean hitArea(float xp, float yp) {
        if (xp >= x && xp <= (x + imagen.getWidth())) {
            if (yp >= y && yp <= (y + imagen.getHeight())) {
                return true;
            }
        }
        return false;
    }
    public float getTamanoY()
    {
        return imagen.getHeight();
    }
    public String getTAG() {
        return TAG;
    }
    public void setTAG(String TAG) {
        this.TAG = TAG;
    }
    public void movera(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public float getX() {
        return this.x = x;
    }
    public float getY() {
        return this.y = y;
    }

    public boolean onColission(Pikachu otro) {
        if (otro.hitArea(x, y)) {
            return true;
        }
        if (otro.hitArea(x + imagen.getWidth(), y))
        {
            return true;
        }
        if (otro.hitArea(x + imagen.getWidth(), y + imagen.getHeight())) {
            return true;
        }
        if (otro.hitArea(x, y + imagen.getHeight()))
        {
            return true;
        }
        return false;
    }

    public static Bitmap escalado(Bitmap imgentrada, float tamanio, boolean filtro) {
        float ratio = Math.min((float) tamanio / imgentrada.getWidth(), (float) tamanio / imgentrada.getHeight());
        int width = Math.round((float) ratio * imgentrada.getWidth());
        int height = Math.round((float) ratio * imgentrada.getHeight());
        Bitmap nuevaImagen = Bitmap.createScaledBitmap(imgentrada, width, height, filtro);
        return nuevaImagen;
    }
}
