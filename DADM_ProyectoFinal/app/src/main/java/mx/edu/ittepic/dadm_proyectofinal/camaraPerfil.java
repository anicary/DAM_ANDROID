package mx.edu.ittepic.dadm_proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;

public class camaraPerfil extends AppCompatActivity {
ImageView imagen;
    Button tomarf;
    Bitmap enviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara_perfil);
        SharedPreferences prefs =
                getSharedPreferences("INFO_USUARIO", Context.MODE_PRIVATE);
        tomarf=(Button)findViewById(R.id.tomarfoto);

        imagen=(ImageView) findViewById(R.id.imgPernew);
        new camaraPerfil.DescargarImagenes((ImageView) findViewById(R.id.imgPernew))
                .execute(""+ prefs.getString("imagen", "http://carolina.x10host.com/archivos/fotos/perfil.jpg"));
        tomarf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 7);

            }
        });
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            try {
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                Bitmap bmp = null;
                bmp = getBitmapFromUri(selectedImage);
                enviar=bmp;
                imagen.setImageBitmap(bmp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 7 && resultCode == RESULT_OK && data != null ) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            enviar=thumbnail;
            imagen.setImageBitmap(thumbnail);
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }
}
