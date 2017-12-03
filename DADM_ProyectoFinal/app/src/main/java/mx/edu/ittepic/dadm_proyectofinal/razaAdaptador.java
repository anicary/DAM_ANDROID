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
 * Created by UsuarioPrueba on 02/12/2017.
 */

public class razaAdaptador extends BaseAdapter {
    private Activity actividad;
    private ArrayList<raza> elementos;
    raza elemento;
    View vista;
    private razaAdaptador.botonClick btnMasinfo = null;
    public razaAdaptador(Activity actividad, ArrayList<raza> elementos,razaAdaptador.botonClick btnMasinfo) {
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
        return elementos.get(position).getidrazamascota();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        vista = convertView;
        if (vista == null) {
            LayoutInflater inflater = (LayoutInflater) actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista = inflater.inflate(R.layout.wikikardex, null);
        }
        elemento = elementos.get(position);
        ImageView pata = (ImageView) vista.findViewById(R.id.vermas);
        pata.setTag(position);
        pata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnMasinfo != null){
                    btnMasinfo.onBtnClick((Integer) view.getTag());
                }

            }
        });
        TextView a = (TextView) vista.findViewById(R.id.wikikardexraza);
        a.setText(elemento.getnombrer());
        /*TextView c = (TextView) vista.findViewById(R.id.vwikinkardex);
        c.setText(elemento.getcaracter());*/
        ImageView raza = (ImageView) vista.findViewById(R.id.vwikikardesfoto);
        Picasso.with(actividad).load(elemento.getfotor()).into(raza);
     /*   new razaAdaptador.DescargarImagenes((ImageView) vista.findViewById(R.id.vwikikardesfoto))
                .execute(""+elemento.getfotor());*/
        return vista;
    }
    private class DescargarImagenes extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DescargarImagenes(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

