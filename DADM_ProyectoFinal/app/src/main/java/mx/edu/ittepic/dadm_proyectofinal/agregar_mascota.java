package mx.edu.ittepic.dadm_proyectofinal;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.bitmap;

public class agregar_mascota extends AppCompatActivity implements AsyncResponse {
    EditText nombre, edad;
    String name, sex, edad1,idusuarios="";;
    Spinner tipo, raza, sexo;
    Button agregar,galerria,camara;
    ConexionWeb conexionWeb;
    private static int RESULT_LOAD_IMAGE = 1;
    ImageView imageView;
    Bitmap imagenenviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Registra a tu mascota");
        ActivityCompat.requestPermissions(this,
                new String[]{ Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE },
                1);
        cargarTipos();
        setContentView(R.layout.activity_agregar_mascota);
        nombre = (EditText) findViewById(R.id.nombremascota);
        edad = (EditText) findViewById(R.id.edadmascota);
        sexo = (Spinner) findViewById(R.id.sexomascota);
        tipo = (Spinner) findViewById(R.id.tipomascota);
        raza = (Spinner) findViewById(R.id.razamascota);
        agregar = (Button) findViewById(R.id.agregar);
         imageView = (ImageView) findViewById(R.id.fotomascota1);
        SharedPreferences prefs = getSharedPreferences("INFO_USUARIO", Context.MODE_PRIVATE);
        idusuarios = prefs.getString("idusuarios", "0");
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nombre.getText().toString();
                sex = sexo.getSelectedItem().toString();
                edad1 = edad.getText().toString();

                if (!name.equals("") || !sex.equals("") || !edad1.equals("")) {
                    //TODO PENDIENTE
                    try {
                        String imagebase64string="";
                        try {
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            imagenenviar.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte[] byteArrayImage = baos.toByteArray();
                             imagebase64string = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        conexionWeb = new ConexionWeb(agregar_mascota.this);
                        conexionWeb.agregarVariables("idusuarios",idusuarios);
                        conexionWeb.agregarVariables("nombre", name);
                        conexionWeb.agregarVariables("sexo", sex);
                        conexionWeb.agregarVariables("edad", edad1);
                        conexionWeb.agregarVariables("tipo_mascota_idtipo_mascota", "1");
                        conexionWeb.agregarVariables("razamascota_idrazamascota", "1");
                        conexionWeb.agregarVariables("foto_mas",imagebase64string);
                        URL direccion = new URL("http://carolina.x10host.com/index.php/Sistema/registro_mascota");
                        Intent inicio = new Intent(agregar_mascota.this,MainActivity.class);
                        startActivity(inicio);
                        conexionWeb.execute(direccion);
                    } catch (MalformedURLException e) {
                        Toast.makeText(agregar_mascota.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
        galerria = (Button) findViewById(R.id.btnGaleria);
        galerria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        camara = (Button) findViewById(R.id.btnFotodog);
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 7);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
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
                imagenenviar=bmp;
                imageView.setImageBitmap(bmp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 7 && resultCode == RESULT_OK && data != null ) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imagenenviar=thumbnail;
                imageView.setImageBitmap(thumbnail);
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
    @Override
    public void procesarRespuesta(String r){
        try{
            JSONArray arrayjson = new JSONArray(r);
            if (arrayjson.getJSONObject(0).has("idtipo_mascota")) {
                List<String> spinnerArray =  new ArrayList<String>();

                for (int i = 0; i < arrayjson.length(); i++) {
                    spinnerArray.add(arrayjson.getJSONObject(i).getString("nombre"));
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        this, android.R.layout.simple_spinner_item, spinnerArray);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                tipo.setAdapter(adapter);
            }else
            {

            }
        }catch (JSONException e){
        }
    }
    public void cargarTipos(){
        try {
            conexionWeb = new ConexionWeb(agregar_mascota.this);
            conexionWeb.agregarVariables("idusuarios", idusuarios);
            URL direcciopn = new URL("http://carolina.x10host.com/index.php/Sistema/tipo_masctoa");
            conexionWeb.execute(direcciopn);
        } catch (MalformedURLException e) {
            Toast.makeText(agregar_mascota.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
