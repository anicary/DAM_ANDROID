package mx.edu.ittepic.dadm_minigameu3_anacarolina_zulmaisabel;

import android.content.Context;
import android.media.MediaPlayer;
public class Musica {
    public MediaPlayer  mainPlayer;
    boolean activo;
    public  Musica(Context contexto, int Cancion, boolean repetir)
    {
        mainPlayer = new MediaPlayer();
        mainPlayer = MediaPlayer.create(contexto, Cancion);
        mainPlayer.setLooping(repetir);
    }
    public void reproducir()
    {
        activo=true;
        mainPlayer.start();
    }
    public void detener(){
        mainPlayer.stop();
        activo=false;
    }
    public void pausar(){
        mainPlayer.pause();
        activo=false;
    }
    public void repetir(boolean bucle)
    {
        mainPlayer.setLooping(bucle);
    }
    public void setVolumen(float volumen)
    {
        mainPlayer.setVolume(volumen,volumen);
    }
    public boolean getActivo(){return activo;}
}
