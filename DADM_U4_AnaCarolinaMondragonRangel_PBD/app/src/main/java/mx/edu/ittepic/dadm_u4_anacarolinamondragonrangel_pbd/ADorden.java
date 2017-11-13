package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_pbd;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ADorden   extends BaseAdapter {
    private Activity actividad;
    private ArrayList<Cliente> elementos;
    Cliente elemento;
    TextView id;
    View vista;
    private ADcliente.botonClick btnEditar = null,btnEliminar=null;

    public ADorden(Activity actividad, ArrayList<Cliente> elementos, ADcliente.botonClick btnEditarOido, ADcliente.botonClick btnEliminarOido) {
        this.actividad = actividad;
        this.elementos = elementos;
        btnEditar = btnEditarOido;
        btnEliminar=btnEliminarOido;
    }
    public interface botonClick {
        public abstract void onBtnClick(int position);
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
        vista = convertView;
        if (vista == null) {
            LayoutInflater inflater = (LayoutInflater) actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista = inflater.inflate(R.layout.cliente_list, null);
        }
        elemento = elementos.get(position);
        id = (TextView) vista.findViewById(R.id.idCliente);
        id.setText(""+elemento.getIdcliente());
        TextView nombre = (TextView) vista.findViewById(R.id.txtNombre);
        nombre.setText(elemento.getNombre());
        TextView domicilio = (TextView) vista.findViewById(R.id.txtDomicilio);
        domicilio.setText(elemento.getDomicilio());
        TextView colonia = (TextView) vista.findViewById(R.id.txtColonia);
        colonia.setText(elemento.getColonia());
        Button editar =(Button) vista.findViewById(R.id.btnEditar);
        editar.setTag(position);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnEditar != null)
                    btnEditar.onBtnClick((Integer) v.getTag());
            }
        });
        Button eliminar =(Button) vista.findViewById(R.id.btnELIMINAR);
        eliminar.setTag(position);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnEliminar != null)
                    btnEliminar.onBtnClick((Integer) v.getTag());
            }
        });
        return vista;
    }

}