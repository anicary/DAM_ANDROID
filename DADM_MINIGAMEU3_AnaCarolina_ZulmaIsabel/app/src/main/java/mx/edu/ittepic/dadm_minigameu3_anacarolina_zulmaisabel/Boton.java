package mx.edu.ittepic.dadm_minigameu3_anacarolina_zulmaisabel;

import android.graphics.Bitmap;

/**
 * Created by UsuarioPrueba on 22/10/2017.
 */
public class Boton {
    Bitmap imagen;
    float x, y,tam;
    int id;
    public  Boton(Bitmap imagen,float x,float y, int id, float tam){
        imagen=escalado(imagen,tam,true);
        this.imagen=imagen;
        this.x=x;
        this.y=y;
        this.id=id;
    }
    public Bitmap getImagen()
    {
        return imagen;
    }
    public boolean hitArea(float xp, float yp) {
        if (xp >= x && xp <= (x + imagen.getWidth())) {
            if (yp >= y && yp <= (y + imagen.getHeight())) {
                return true;
            }
        }
        return false;
    }
    public static Bitmap escalado(Bitmap imgentrada, float tamanio,  boolean filtro) {
        float ratio = Math.min((float) tamanio / imgentrada.getWidth(), (float) tamanio / imgentrada.getHeight());
        int width = Math.round((float) ratio * imgentrada.getWidth());
        int height = Math.round((float) ratio * imgentrada.getHeight());
        Bitmap nuevaImagen = Bitmap.createScaledBitmap(imgentrada, width, height, filtro);
        return nuevaImagen;
    }
}
