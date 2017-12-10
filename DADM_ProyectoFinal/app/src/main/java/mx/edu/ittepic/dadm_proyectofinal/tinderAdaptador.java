package mx.edu.ittepic.dadm_proyectofinal;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by UsuarioPrueba on 03/12/2017.
 */

public class tinderAdaptador extends BaseAdapter {
    private Activity actividad;
    private ArrayList<mascotatinder> elementos;
    mascotatinder elemento;
    View vista;
    ImageView botonCorzon,botonDis;
    Boolean corazon=true,likee=true;
    TextView cora,like;
    LinearLayout perfil_ver;
    private tinderAdaptador.botonClick botonMatch = null,botonMatchNo=null,botonPerfilUsuario=null;
    public tinderAdaptador(Activity actividad, ArrayList<mascotatinder> elementos,tinderAdaptador.botonClick botonMatch,tinderAdaptador.botonClick botonMatchNo, tinderAdaptador.botonClick botonPerfilUsuario) {
        this.actividad = actividad;
        this.elementos = elementos;
        this.botonMatch=botonMatch;
        this.botonMatchNo=botonMatchNo;
        this.botonPerfilUsuario=botonPerfilUsuario;
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
        elemento = elementos.get(position);
        cora =(TextView) vista.findViewById(R.id.vcerocora);
        if (elemento.getCoraz().equals("null")){
            cora.setText(""+0);
        }else {
            cora.setText(elemento.getCoraz());
        }
        like =(TextView) vista.findViewById(R.id.vcerolike);
        if (elemento.getLike().equals("null")){
            like.setText(""+0);
        }else {
            like.setText(elemento.getLike());
        }

        botonCorzon =(ImageView) vista.findViewById(R.id.corazon);
        botonCorzon.setImageDrawable(elemento.obtenerfotoCora());
        botonCorzon.setTag(position);
        botonCorzon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(botonMatch != null){
                    botonMatch.onBtnClick((Integer) v.getTag());
                }
            }
        });
        botonDis =(ImageView) vista.findViewById(R.id.like);
        botonDis.setImageDrawable(elemento.obtenerfotoLike());
        botonDis.setTag(position);
        botonDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(botonMatchNo != null){
                    botonMatchNo.onBtnClick((Integer) v.getTag());
                }
            }
        });
        TextView a = (TextView) vista.findViewById(R.id.vnombretinder);
        a.setText(elemento.getnombre());
        TextView b = (TextView) vista.findViewById(R.id.vedadtinder);
        b.setText(elemento.getedad());
        ImageView tinderfoto = (ImageView) vista.findViewById(R.id.fotot);
        Picasso.with(actividad).load(elemento.getfoto()).into(tinderfoto);
        TextView nombreu = (TextView) vista.findViewById(R.id.nomusuariotinder);
        nombreu.setText(elemento.getNombreu());
        ImageView foto_usuario =(ImageView)vista.findViewById(R.id.fotou);
        Picasso.with(actividad).load(elemento.getFotop()).into(foto_usuario);
        perfil_ver = (LinearLayout) vista.findViewById(R.id.perfil_usuari);
        perfil_ver.setTag(position);
        perfil_ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(botonPerfilUsuario != null){
                    botonPerfilUsuario.onBtnClick((Integer) view.getTag());
                }
            }
        });
        TextView ubicacion = (TextView) vista.findViewById(R.id.txtUbicacion);
        ubicacion.setText(elemento.getUsuario_ubicacion());
        return vista;

    }
    public boolean getCorazon(){
        return  corazon;
    }
    public boolean getlikee(){
        return  likee;
    }
    public void  ponerConrazon(String corazon){
        cora.setText(corazon);
    }
    public void ponerLike(String s){
        like.setText(s);
    }
    public int retornarCorazon(){
        return Integer.parseInt(elemento.getCoraz());
    }

}

