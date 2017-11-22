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
    private ADorden.botonClick btnEditar = null,btnEliminar=null,btnAgregar;

    public ADorden(Activity actividad, ArrayList<Cliente> elementos, ADorden.botonClick btnAgregar, ADorden.botonClick btnEditarOido, ADorden.botonClick btnEliminarOido) {
        this.actividad = actividad;
        this.elementos = elementos;
        btnEditar = btnEditarOido;
        btnEliminar=btnEliminarOido;
        this.btnAgregar=btnAgregar;
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
            vista = inflater.inflate(R.layout.orden_list, null);
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
        Button editar =(Button) vista.findViewById(R.id.btnEditarOrden);
        editar.setTag(position);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnEditar != null)
                    btnEditar.onBtnClick((Integer) v.getTag());
            }
        });
        Button eliminar =(Button) vista.findViewById(R.id.btnEliminarOrden);
        eliminar.setTag(position);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnEliminar != null)
                    btnEliminar.onBtnClick((Integer) v.getTag());
            }
        });
        Button agregar =(Button) vista.findViewById(R.id.btnAgregarEquipoa);
        agregar.setTag(position);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnAgregar != null)
                    btnAgregar.onBtnClick((Integer) v.getTag());
            }
        });
        return vista;
    }

}