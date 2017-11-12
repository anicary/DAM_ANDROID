package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_pbd;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ADcliente  extends BaseAdapter {
    private Activity actividad;
    private ArrayList<Cliente> elementos;

    public ADcliente(Activity actividad, ArrayList<Cliente> elementos) {
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
        return elementos.get(position).getIdcliente();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;
        if (vista == null) {
            LayoutInflater inflater = (LayoutInflater) actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista = inflater.inflate(R.layout.cliente_list, null);
        }
        Cliente elemento = elementos.get(position);
        TextView id = (TextView) vista.findViewById(R.id.idCliente);
        id.setText(""+elemento.getIdcliente());
        TextView nombre = (TextView) vista.findViewById(R.id.txtNombre);
        nombre.setText(elemento.getNombre());
        TextView domicilio = (TextView) vista.findViewById(R.id.txtDomicilio);
        domicilio.setText(elemento.getDomicilio());
        TextView colonia = (TextView) vista.findViewById(R.id.txtColonia);
        colonia.setText(elemento.getColonia());
        return vista;
    }

}