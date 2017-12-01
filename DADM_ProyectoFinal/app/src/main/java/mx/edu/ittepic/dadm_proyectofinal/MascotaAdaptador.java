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
    public MascotaAdaptador(Activity actividad, ArrayList<mascota> elementos) {
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
        TextView nombre = (TextView) vista.findViewById(R.id.vnombrekardex);
        nombre.setText(elemento.getnombre());
        TextView edad = (TextView) vista.findViewById(R.id.vedadkardex);
        edad.setText(elemento.getedad());
        TextView raza = (TextView) vista.findViewById(R.id.vtipokardex);
        raza.setText(elemento.getraza());
       // ImageView raza = (ImageView) vista.findViewById(R.id.vkardesfoto);
        new MascotaAdaptador.DescargarImagenes((ImageView) vista.findViewById(R.id.vkardesfoto))
                .execute(""+elemento.getfoto());
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
