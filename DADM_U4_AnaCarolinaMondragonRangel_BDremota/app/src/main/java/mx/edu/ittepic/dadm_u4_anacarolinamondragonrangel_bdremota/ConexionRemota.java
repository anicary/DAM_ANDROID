package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_bdremota;

import android.os.AsyncTask;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by UsuarioPrueba on 13/11/2017.
 */

public class ConexionRemota extends AsyncTask<URL,Void,String> {
    List<String[]> variables;
    MainActivity puntero;

    public void ConexionWeb(MainActivity p)
    {
        variables= new ArrayList<>();
        puntero=p;
    }
    public void agregarValores(String indentificador,String Datos)
    {
        String[] temp={indentificador,Datos};
        variables.add(temp);
    }
    public void limpiarVariables(){
        variables= new ArrayList<String[]>();
    }

    @Override
    protected String doInBackground(URL... urls) {
        return null;
    }
}
