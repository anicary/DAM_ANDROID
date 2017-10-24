package mx.edu.ittepic.dadm_minigameu3_anacarolina_zulmaisabel;

import android.content.Context;
import android.media.MediaPlayer;
public class Musica {
    public MediaPlayer  mainPlayer;
    public  Musica(Context contexto, int Cancion, boolean repetir)
    {
        mainPlayer = new MediaPlayer();
        mainPlayer = MediaPlayer.create(contexto, Cancion);
        mainPlayer.setLooping(repetir);
    }
    public void reproducir()
    {

        mainPlayer.start();
    }
    public void detener(){
        mainPlayer.stop();
    }
    public void repetir(boolean bucle)
    {
        mainPlayer.setLooping(bucle);
    }
}
