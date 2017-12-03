package mx.edu.ittepic.dadm_proyectofinal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by UsuarioPrueba on 30/11/2017.
 */

public class MascotaAdaptador extends BaseAdapter {
    private Activity actividad;
    private ArrayList<mascota>elementos;
    mascota elemento;
    View vista;
    private botonClick btnMasinfo = null;
    public MascotaAdaptador(Activity actividad, ArrayList<mascota> elementos,botonClick btnMasinfo) {
        this.actividad = actividad;
        this.elementos = elementos;
        this.btnMasinfo=btnMasinfo;
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
        return elementos.get(position).getidmascota();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        vista = convertView;
        if (vista == null) {
            LayoutInflater inflater = (LayoutInflater) actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista = inflater.inflate(R.layout.kardex, null);
        }
        elemento = elementos.get(position);
        ImageView pata = (ImageView) vista.findViewById(R.id.idpatita);
        pata.setTag(position);
        pata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(btnMasinfo != null){
                        btnMasinfo.onBtnClick((Integer) view.getTag());
                    }

            }
        });
        TextView nombre = (TextView) vista.findViewById(R.id.vnombrekardex);
        nombre.setText(elemento.getnombre());
        TextView edad = (TextView) vista.findViewById(R.id.vedadkardex);
        edad.setText(elemento.getedad());
        TextView raza = (TextView) vista.findViewById(R.id.vtipokardex);
        raza.setText(elemento.getraza());
        ImageView razaa = (ImageView) vista.findViewById(R.id.vkardesfoto);
        Picasso.with(actividad).load(elemento.getfoto()).into(razaa);
        return vista;
    }
}
