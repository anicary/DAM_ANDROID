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
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkEventListener;

import org.w3c.dom.Text;

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
    SparkButton botonCorzon,botonDis;
    Boolean corazon=true,likee=true;
    TextView cora,like;
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
        elemento = elementos.get(position);
    /*  ImageView pata = (ImageView) vista.findViewById(R.id.vermas);
        pata.setTag(position);
        pata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(botonMatch != null){
                    botonMatch.onBtnClick((Integer) view.getTag());
                }
            }
        });**/
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
        botonCorzon =  (SparkButton) vista.findViewById(R.id.spark_button);

        botonCorzon.setTag(position);
        botonCorzon.setEventListener(new SparkEventListener() {
            @Override
            public void onEvent(ImageView button, boolean buttonState) {
                if (buttonState) {
                    if(botonMatch != null){
                       botonMatch.onBtnClick((Integer)  botonCorzon.getTag());

                    }
                } else {
                    if(botonMatch != null){
                        botonMatch.onBtnClick((Integer)  botonCorzon.getTag());

                    }
                }
            }

            @Override
            public void onEventAnimationEnd(ImageView button, boolean buttonState) {
                corazon=false;
            }

            @Override
            public void onEventAnimationStart(ImageView button, boolean buttonState) {
                corazon=true;
            }
        });
        botonDis =  (SparkButton) vista.findViewById(R.id.like_button);

        botonDis.setTag(position);
        botonDis.setEventListener(new SparkEventListener() {
            @Override
            public void onEvent(ImageView button, boolean buttonState) {
                if (buttonState) {
                    if(botonMatchNo != null){
                        botonMatchNo.onBtnClick((Integer)  botonDis.getTag());
                    }
                } else {
                    if(botonMatchNo != null){
                        botonMatchNo.onBtnClick((Integer)  botonDis.getTag());
                    }
                }
            }

            @Override
            public void onEventAnimationEnd(ImageView button, boolean buttonState) {
                likee=false;
            }

            @Override
            public void onEventAnimationStart(ImageView button, boolean buttonState) {
                likee=true;
            }
        });
        TextView a = (TextView) vista.findViewById(R.id.vnombretinder);
        a.setText(elemento.getnombre());
        TextView b = (TextView) vista.findViewById(R.id.vedadtinder);
        b.setText(elemento.getedad());
        ImageView tinderfoto = (ImageView) vista.findViewById(R.id.fotot);
        Picasso.with(actividad).load(elemento.getfoto()).into(tinderfoto);
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

