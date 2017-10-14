package mx.edu.ittepic.practicau1_5_activitys_anacarolinamondragonrangel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListaAdaptador extends BaseAdapter {
    private Activity actividad;
    private ArrayList<Lista> elementos;
    public ListaAdaptador(Activity actividad, ArrayList<Lista> elementos) {
        this.actividad = actividad;
        this.elementos = elementos;
    }
    @Override
    public int getCount() {
        return elementos.size();
    }
    @Override
    public Object getItem(int position) {
        return elementos.get(position);
    }
    @Override
    public long getItemId(int position) {
        return elementos.get(position).getId();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;
        if (vista == null) {
            LayoutInflater inflater = (LayoutInflater) actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista = inflater.inflate(R.layout.lista_menu, null);
        }
        Lista elemento = elementos.get(position);
        TextView nombre = (TextView) vista.findViewById(R.id.textlista);
        nombre.setText(elemento.getNombre());
        ImageView image = (ImageView) vista.findViewById(R.id.imglista);
        int imageResource = actividad.getResources().getIdentifier(elemento.getImagen(), null, actividad.getPackageName());
        image.setImageDrawable(actividad.getResources().getDrawable(imageResource));
        return vista;
    }
}
