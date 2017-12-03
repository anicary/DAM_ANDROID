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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by UsuarioPrueba on 03/12/2017.
 */

public class tinderAdaptador extends BaseAdapter {
    private Activity actividad;
    private ArrayList<mascota> elementos;
    mascota elemento;
    View vista;
    private tinderAdaptador.botonClick botonMatch = null,botonMatchNo=null;
    public tinderAdaptador(Activity actividad, ArrayList<mascota> elementos,tinderAdaptador.botonClick botonMatch,tinderAdaptador.botonClick botonMatchNo) {
        this.actividad = actividad;
        this.elementos = elementos;
        this.botonMatch=botonMatch;
        this.botonMatchNo=botonMatchNo;
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
            vista = inflater.inflate(R.layout.tinder, null);
        }
      //  elemento = elementos.get(position);
        ImageView pata = (ImageView) vista.findViewById(R.id.vermas);
        pata.setTag(position);
        pata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(botonMatch != null){
                    botonMatch.onBtnClick((Integer) view.getTag());
                }
            }
        });
        TextView a = (TextView) vista.findViewById(R.id.vnombretinder);
        a.setText(elemento.getnombre());
        TextView b = (TextView) vista.findViewById(R.id.vedadtinder);
        a.setText(elemento.getedad());
        /*TextView c = (TextView) vista.findViewById(R.id.vwikinkardex);
        c.setText(elemento.getcaracter());*/
        ImageView tinderfoto = (ImageView) vista.findViewById(R.id.fotot);
       Picasso.with(actividad).load(elemento.getfoto()).into(tinderfoto);
     /*   new razaAdaptador.DescargarImagenes((ImageView) vista.findViewById(R.id.vwikikardesfoto))
                .execute(""+elemento.getfotor());*/
        return vista;
    }

}

