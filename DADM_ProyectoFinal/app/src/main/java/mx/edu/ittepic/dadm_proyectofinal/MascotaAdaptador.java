package mx.edu.ittepic.dadm_proyectofinal;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by UsuarioPrueba on 30/11/2017.
 */

public class MascotaAdaptador {
    private Activity actividad;
    private ArrayList<mascota>elementos;
    public MascotaAdaptador(Activity actividad, ArrayList<mascota> elementos) {
        this.actividad = actividad;
        this.elementos = elementos;

    }

}
