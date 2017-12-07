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
import android.widget.AdapterView;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

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
    String idtipos[],idraza[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Registra a tu mascota");
        ActivityCompat.requestPermissions(this,
                new String[]{ Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE , Manifest.permission.READ_EXTERNAL_STORAGE },
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
        tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                cargarRazas(idtipos[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        SharedPreferences prefs = getSharedPreferences("INFO_USUARIO", Context.MODE_PRIVATE);
        idusuarios = prefs.getString("idusuarios", "0");
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nombre.getText().toString();
                sex = sexo.getSelectedItem().toString();
                edad1 = edad.getText().toString();

                if (!name.equals("") && !sex.equals("") && !edad1.equals("")) {
                    //TODO PENDIENTE
                    try {
                        String imagebase64string="";
                        try {
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            imagenenviar.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte[] byteArrayImage = baos.toByteArray();
                             imagebase64string = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
                        } catch (Exception e) {
                            imagebase64string="/9j/4AAQSkZJRgABAgEAYABgAAD/4QqrRXhpZgAATU0AKgAAAAgABwESAAMAAAABAAEAAAEaAAUA\n" +
                                    "AAABAAAAYgEbAAUAAAABAAAAagEoAAMAAAABAAIAAAExAAIAAABjAAAAcgEyAAIAAAAUAAAA1Ydp\n" +
                                    "AAQAAAABAAAA7AAAARgADqYAAAAnEAAOpgAAACcQQWRvYmUgUGhvdG9zaG9wIENTNCAoMTEuMHgy\n" +
                                    "MDA4MDgyNiBbMjAwODA4MjYubS40NTYgMjAwOC8wOC8yNjowMjowMDowMCBjdXRvZmY7IG0gYnJh\n" +
                                    "bmNoXSkgIFdpbmRvd3MAMjAxNzoxMTozMCAxMToxNjoyOQAAAAAAA6ABAAMAAAABAAEAAKACAAQA\n" +
                                    "AAABAAABLKADAAQAAAABAAABLAAAAAAAAAAGAQMAAwAAAAEABgAAARoABQAAAAEAAAFmARsABQAA\n" +
                                    "AAEAAAFuASgAAwAAAAEAAgAAAgEABAAAAAEAAAF2AgIABAAAAAEAAAktAAAAAAAAAEgAAAABAAAA\n" +
                                    "SAAAAAH/2P/gABBKRklGAAECAABIAEgAAP/tAAxBZG9iZV9DTQAB/+4ADkFkb2JlAGSAAAAAAf/b\n" +
                                    "AIQADAgICAkIDAkJDBELCgsRFQ8MDA8VGBMTFRMTGBEMDAwMDAwRDAwMDAwMDAwMDAwMDAwMDAwM\n" +
                                    "DAwMDAwMDAwMDAENCwsNDg0QDg4QFA4ODhQUDg4ODhQRDAwMDAwREQwMDAwMDBEMDAwMDAwMDAwM\n" +
                                    "DAwMDAwMDAwMDAwMDAwMDAwM/8AAEQgAoACgAwEiAAIRAQMRAf/dAAQACv/EAT8AAAEFAQEBAQEB\n" +
                                    "AAAAAAAAAAMAAQIEBQYHCAkKCwEAAQUBAQEBAQEAAAAAAAAAAQACAwQFBgcICQoLEAABBAEDAgQC\n" +
                                    "BQcGCAUDDDMBAAIRAwQhEjEFQVFhEyJxgTIGFJGhsUIjJBVSwWIzNHKC0UMHJZJT8OHxY3M1FqKy\n" +
                                    "gyZEk1RkRcKjdDYX0lXiZfKzhMPTdePzRieUpIW0lcTU5PSltcXV5fVWZnaGlqa2xtbm9jdHV2d3\n" +
                                    "h5ent8fX5/cRAAICAQIEBAMEBQYHBwYFNQEAAhEDITESBEFRYXEiEwUygZEUobFCI8FS0fAzJGLh\n" +
                                    "coKSQ1MVY3M08SUGFqKygwcmNcLSRJNUoxdkRVU2dGXi8rOEw9N14/NGlKSFtJXE1OT0pbXF1eX1\n" +
                                    "VmZ2hpamtsbW5vYnN0dXZ3eHl6e3x//aAAwDAQACEQMRAD8A7NJJJTsakkkklKSSSSUpJJJJSkkk\n" +
                                    "klKSSSSUpJJJJSkkkklKSSSSUpJJJJT/AP/Q7NJJJTsakkkklKSSSSUpJJJJSkgCZgExzGvJ2/8A\n" +
                                    "VJ2Mc8kN5ALifADly2MTEqoBcwlxeGkl3kP3fzfpJspUkC3Pq6dkW17/AGsDgCyTzPjH0fald07J\n" +
                                    "rJ2t9RoE7m8+Y2fSWykmcZXcIedSTu1e48e46fPyTKVYpJJJJSkkkklKSSSSU//R7NJJJTsakkk3\n" +
                                    "HKSl0WjEvv8A5tsN/edoPl+8j4nTn2nfcDWwH6PDj/5Bq1WtaxoY0Q1oAA8AEyU62XAOZ+ybYP6R\n" +
                                    "s9hBg6f+SVS2m2kgWtLCeO8x5hb6p9Vc0Yh3DUubtPgZn/qUIzN0ogI+lUbWm93L9Gf1R/5ktBCx\n" +
                                    "mOZj1Md9JrQD8Y8kVNkbJXBSHfb6VL7IJ2AmAiKj1W17KWsbIFh9zh4AfQ/tJAWQFFyte+p7nzRK\n" +
                                    "ce68kVN3bYmdAJ45V7AwKzWLrmh+8AtY4aAa8g/vLQa1rRDQAPAaeSeZ1stEXMZ0m0g73tadIIkx\n" +
                                    "+97faq+Zj/ZrQySWuEtce5/OGi3ELJ2/Z7HOaHBrXGD5AoCZtNBwkkw4TqRYpJJJJT//0uzSSSU7\n" +
                                    "GpHwqH3XjbAFZDnEidP6pQFodIibhGvt1/ztEJGgUjd0kkklCvUqfUS5rKXADS1kkiY51/kq4szq\n" +
                                    "1svZSJ4l4n2kE+32/wBhOjug7Omks3C6g1rRVe4kyYscRAHbe4q+Lay1r9w2vEtMxOm7/qUCCFAs\n" +
                                    "1Ry6235tNPG0eo8gngH2t9p2t3fvol/UMemWzvePzW/xd9FD6Zvs9XIsMusdHHEfun9z3IgEC1eD\n" +
                                    "eSSSTUqQsp7WY73PaHtA1aRIP3oqzOq3y5uO3ge5/wAfzG/9+RiLKDs0Ne/PeEkklMsUkkkkp//T\n" +
                                    "7NJJJTsalf6Q9odYwmHOgtHiByqCQJBBBII1BGhCBFikg09Eksmjql7JFo9UdjoCovy8vJd+hDmi\n" +
                                    "Icxhkafnb9rdqj4CusOpZfTU3dY8Nb4k/wCvgsW+12Re6yDLyA1vJA4a1WmdIJrYS/Y8/TbAIH9W\n" +
                                    "FcxsKrHAMb7B+eQJ/s/uogxj4o1LlDEyjMVO00PA/KrbekvLAH3FsgbmASJ528/mrSSQMynhDkX9\n" +
                                    "Mtqj0Ztae2gI/Ij9Ms9MvxrfZZu3Na7QmRrt/wA1aCo9WDfRY6PdvgOGhAh0oiV6FVVq3klijqOU\n" +
                                    "1m0OEABoJEnT86XE+5Wqb+pXVe1rRpItdpPhtZ+8gYEdlWnzMxmOyAZtd9FvMaaOcJb7Fjuc+ywu\n" +
                                    "d7nvPbuT4BadPTmFm7KJttdqZcdJ12aO/eVmvFx6iDXW1rhw6Ndf5SIIjtqoglwQQeCnXQWV12sL\n" +
                                    "LGhzTyCsB+z1H7NWbjtPlPtToytaRSySSSch/9Ts0kklOxqSSSBLSHCJBBE8aHukp0sTp1ZpBvYd\n" +
                                    "7j7mmDp+aP5H8pXmVsZOxoExMeQ2hSSUJJLJSkkkkFKSSSSUpZ3V5irwBM/MaaLRWd1evSuwN1Et\n" +
                                    "Lx2H7rv7X0U6G4QdmHTcUPd61gMN+hPB/lfyv+oWkxjK2BjBDRwPBCwWhuJVAiWg8zz7u6OhI2VA\n" +
                                    "KSSSQSsdB4+SwbrDbc+xw2lx1aTMR7ds/JX+pZbQDjsALvzyQfb+c3b9H3/nNd+Ys1SQHVbIqSSS\n" +
                                    "T1r/AP/V7NJJJTsakgYIPgZ+74JJJKd3Gu9ehtsQXDUDWCNCirIwMz0HCp8CpxJ3fuk+P8la3Khk\n" +
                                    "KK8G10kkkEqSSSSUpVuoNe7EeGN3HQkd4BDtFZSSBo2po9Je00OYDq1xJHk7VpV5ZFwdg5osY0+m\n" +
                                    "dQJ+kD/OD/O9y0a8vHtaXNeIaQDJjU/RTpDqOqB2TIGXlNx6yQQbIljCYnt/FRvzqamOIMvEta2D\n" +
                                    "9Idjp7Vk332X2epZG6AABwAEoxvdRLFznOMvcXO7uOp0TJJKVYpJJJJT/9bs0kklOxqSSSSUoEgy\n" +
                                    "DBGoI7FaGF1ANDmZDoaI2EyTr9Lc7+Ss9JAgFINPQV2MsYHsO5rtQQpLExcuzGf3dUfpM/782fzl\n" +
                                    "qUZmPeBscA4zDHaO0/kqOUSFwNp0kkk1KkklXvzselphwe8aBjSCZ/lfupAWpp9Wc03VsHLWkn+0\n" +
                                    "Rt/6hUg5zSC0lpGoIMaxEp7LH22OseZc7mNB8FFTAUKWE6rAAcJ0kkUKSSSSUpJJJJT/AP/X7NJJ\n" +
                                    "JTsakkkklKSSSSUpJJJJSRmRkVzstcJ51n/q9ykM7MAI9U694bP/AFKCkhQ7JtKcvKcINzo8oH4t\n" +
                                    "AQeOE6SKFJJJJKUkkkkpSSSSSlJJJJKf/9Ds0kklOxqSSSSUpJJJJSkkkklKSSSSUpJJJJSkkkkl\n" +
                                    "KSSSSUpJJJJSkkkklP8A/9n/7Q8qUGhvdG9zaG9wIDMuMAA4QklNBCUAAAAAABAAAAAAAAAAAAAA\n" +
                                    "AAAAAAAAOEJJTQPtAAAAAAAQAGAAAAABAAIAYAAAAAEAAjhCSU0EJgAAAAAADgAAAAAAAAAAAAA/\n" +
                                    "gAAAOEJJTQQNAAAAAAAEAAAAHjhCSU0EGQAAAAAABAAAAB44QklNA/MAAAAAAAkAAAAAAAAAAAEA\n" +
                                    "OEJJTScQAAAAAAAKAAEAAAAAAAAAAjhCSU0D9QAAAAAASAAvZmYAAQBsZmYABgAAAAAAAQAvZmYA\n" +
                                    "AQChmZoABgAAAAAAAQAyAAAAAQBaAAAABgAAAAAAAQA1AAAAAQAtAAAABgAAAAAAAThCSU0D+AAA\n" +
                                    "AAAAcAAA/////////////////////////////wPoAAAAAP////////////////////////////8D\n" +
                                    "6AAAAAD/////////////////////////////A+gAAAAA/////////////////////////////wPo\n" +
                                    "AAA4QklNBAAAAAAAAAIAAThCSU0EAgAAAAAABAAAAAA4QklNBDAAAAAAAAIBAThCSU0ELQAAAAAA\n" +
                                    "BgABAAAABDhCSU0ECAAAAAAAEAAAAAEAAAJAAAACQAAAAAA4QklNBB4AAAAAAAQAAAAAOEJJTQQa\n" +
                                    "AAAAAANBAAAABgAAAAAAAAAAAAABLAAAASwAAAAGAHAAZQByAGYAaQBsAAAAAQAAAAAAAAAAAAAA\n" +
                                    "AAAAAAAAAAABAAAAAAAAAAAAAAEsAAABLAAAAAAAAAAAAAAAAAAAAAABAAAAAAAAAAAAAAAAAAAA\n" +
                                    "AAAAABAAAAABAAAAAAAAbnVsbAAAAAIAAAAGYm91bmRzT2JqYwAAAAEAAAAAAABSY3QxAAAABAAA\n" +
                                    "AABUb3AgbG9uZwAAAAAAAAAATGVmdGxvbmcAAAAAAAAAAEJ0b21sb25nAAABLAAAAABSZ2h0bG9u\n" +
                                    "ZwAAASwAAAAGc2xpY2VzVmxMcwAAAAFPYmpjAAAAAQAAAAAABXNsaWNlAAAAEgAAAAdzbGljZUlE\n" +
                                    "bG9uZwAAAAAAAAAHZ3JvdXBJRGxvbmcAAAAAAAAABm9yaWdpbmVudW0AAAAMRVNsaWNlT3JpZ2lu\n" +
                                    "AAAADWF1dG9HZW5lcmF0ZWQAAAAAVHlwZWVudW0AAAAKRVNsaWNlVHlwZQAAAABJbWcgAAAABmJv\n" +
                                    "dW5kc09iamMAAAABAAAAAAAAUmN0MQAAAAQAAAAAVG9wIGxvbmcAAAAAAAAAAExlZnRsb25nAAAA\n" +
                                    "AAAAAABCdG9tbG9uZwAAASwAAAAAUmdodGxvbmcAAAEsAAAAA3VybFRFWFQAAAABAAAAAAAAbnVs\n" +
                                    "bFRFWFQAAAABAAAAAAAATXNnZVRFWFQAAAABAAAAAAAGYWx0VGFnVEVYVAAAAAEAAAAAAA5jZWxs\n" +
                                    "VGV4dElzSFRNTGJvb2wBAAAACGNlbGxUZXh0VEVYVAAAAAEAAAAAAAlob3J6QWxpZ25lbnVtAAAA\n" +
                                    "D0VTbGljZUhvcnpBbGlnbgAAAAdkZWZhdWx0AAAACXZlcnRBbGlnbmVudW0AAAAPRVNsaWNlVmVy\n" +
                                    "dEFsaWduAAAAB2RlZmF1bHQAAAALYmdDb2xvclR5cGVlbnVtAAAAEUVTbGljZUJHQ29sb3JUeXBl\n" +
                                    "AAAAAE5vbmUAAAAJdG9wT3V0c2V0bG9uZwAAAAAAAAAKbGVmdE91dHNldGxvbmcAAAAAAAAADGJv\n" +
                                    "dHRvbU91dHNldGxvbmcAAAAAAAAAC3JpZ2h0T3V0c2V0bG9uZwAAAAAAOEJJTQQoAAAAAAAMAAAA\n" +
                                    "Aj/wAAAAAAAAOEJJTQQUAAAAAAAEAAAABDhCSU0EDAAAAAAJSQAAAAEAAACgAAAAoAAAAeAAASwA\n" +
                                    "AAAJLQAYAAH/2P/gABBKRklGAAECAABIAEgAAP/tAAxBZG9iZV9DTQAB/+4ADkFkb2JlAGSAAAAA\n" +
                                    "Af/bAIQADAgICAkIDAkJDBELCgsRFQ8MDA8VGBMTFRMTGBEMDAwMDAwRDAwMDAwMDAwMDAwMDAwM\n" +
                                    "DAwMDAwMDAwMDAwMDAENCwsNDg0QDg4QFA4ODhQUDg4ODhQRDAwMDAwREQwMDAwMDBEMDAwMDAwM\n" +
                                    "DAwMDAwMDAwMDAwMDAwMDAwMDAwM/8AAEQgAoACgAwEiAAIRAQMRAf/dAAQACv/EAT8AAAEFAQEB\n" +
                                    "AQEBAAAAAAAAAAMAAQIEBQYHCAkKCwEAAQUBAQEBAQEAAAAAAAAAAQACAwQFBgcICQoLEAABBAED\n" +
                                    "AgQCBQcGCAUDDDMBAAIRAwQhEjEFQVFhEyJxgTIGFJGhsUIjJBVSwWIzNHKC0UMHJZJT8OHxY3M1\n" +
                                    "FqKygyZEk1RkRcKjdDYX0lXiZfKzhMPTdePzRieUpIW0lcTU5PSltcXV5fVWZnaGlqa2xtbm9jdH\n" +
                                    "V2d3h5ent8fX5/cRAAICAQIEBAMEBQYHBwYFNQEAAhEDITESBEFRYXEiEwUygZEUobFCI8FS0fAz\n" +
                                    "JGLhcoKSQ1MVY3M08SUGFqKygwcmNcLSRJNUoxdkRVU2dGXi8rOEw9N14/NGlKSFtJXE1OT0pbXF\n" +
                                    "1eX1VmZ2hpamtsbW5vYnN0dXZ3eHl6e3x//aAAwDAQACEQMRAD8A7NJJJTsakkkklKSSSSUpJJJJ\n" +
                                    "SkkkklKSSSSUpJJJJSkkkklKSSSSUpJJJJT/AP/Q7NJJJTsakkkklKSSSSUpJJJJSkgCZgExzGvJ\n" +
                                    "2/8AVJ2Mc8kN5ALifADly2MTEqoBcwlxeGkl3kP3fzfpJspUkC3Pq6dkW17/AGsDgCyTzPjH0fal\n" +
                                    "d07JrJ2t9RoE7m8+Y2fSWykmcZXcIedSTu1e48e46fPyTKVYpJJJJSkkkklKSSSSU//R7NJJJTsa\n" +
                                    "kkk3HKSl0WjEvv8A5tsN/edoPl+8j4nTn2nfcDWwH6PDj/5Bq1WtaxoY0Q1oAA8AEyU62XAOZ+yb\n" +
                                    "YP6Rs9hBg6f+SVS2m2kgWtLCeO8x5hb6p9Vc0Yh3DUubtPgZn/qUIzN0ogI+lUbWm93L9Gf1R/5k\n" +
                                    "tBCxmOZj1Md9JrQD8Y8kVNkbJXBSHfb6VL7IJ2AmAiKj1W17KWsbIFh9zh4AfQ/tJAWQFFyte+p7\n" +
                                    "nzRKce68kVN3bYmdAJ45V7AwKzWLrmh+8AtY4aAa8g/vLQa1rRDQAPAaeSeZ1stEXMZ0m0g73tad\n" +
                                    "IIkx+97faq+Zj/ZrQySWuEtce5/OGi3ELJ2/Z7HOaHBrXGD5AoCZtNBwkkw4TqRYpJJJJT//0uzS\n" +
                                    "SSU7GpHwqH3XjbAFZDnEidP6pQFodIibhGvt1/ztEJGgUjd0kkklCvUqfUS5rKXADS1kkiY51/kq\n" +
                                    "4szq1svZSJ4l4n2kE+32/wBhOjug7Omks3C6g1rRVe4kyYscRAHbe4q+Lay1r9w2vEtMxOm7/qUC\n" +
                                    "CFAs1Ry6235tNPG0eo8gngH2t9p2t3fvol/UMemWzvePzW/xd9FD6Zvs9XIsMusdHHEfun9z3IgE\n" +
                                    "C1eDeSSSTUqQsp7WY73PaHtA1aRIP3oqzOq3y5uO3ge5/wAfzG/9+RiLKDs0Ne/PeEkklMsUkkkk\n" +
                                    "p//T7NJJJTsalf6Q9odYwmHOgtHiByqCQJBBBII1BGhCBFikg09Eksmjql7JFo9UdjoCovy8vJd+\n" +
                                    "hDmiIcxhkafnb9rdqj4CusOpZfTU3dY8Nb4k/wCvgsW+12Re6yDLyA1vJA4a1WmdIJrYS/Y8/TbA\n" +
                                    "IH9WFcxsKrHAMb7B+eQJ/s/uogxj4o1LlDEyjMVO00PA/KrbekvLAH3FsgbmASJ528/mrSSQMynh\n" +
                                    "DkX9Mtqj0Ztae2gI/Ij9Ms9MvxrfZZu3Na7QmRrt/wA1aCo9WDfRY6PdvgOGhAh0oiV6FVVq3kli\n" +
                                    "jqOU1m0OEABoJEnT86XE+5Wqb+pXVe1rRpItdpPhtZ+8gYEdlWnzMxmOyAZtd9FvMaaOcJb7Fjuc\n" +
                                    "+ywud7nvPbuT4BadPTmFm7KJttdqZcdJ12aO/eVmvFx6iDXW1rhw6Ndf5SIIjtqoglwQQeCnXQWV\n" +
                                    "12sLLGhzTyCsB+z1H7NWbjtPlPtToytaRSySSSch/9Ts0kklOxqSSSBLSHCJBBE8aHukp0sTp1Zp\n" +
                                    "BvYd7j7mmDp+aP5H8pXmVsZOxoExMeQ2hSSUJJLJSkkkkFKSSSSUpZ3V5irwBM/MaaLRWd1evSuw\n" +
                                    "N1EtLx2H7rv7X0U6G4QdmHTcUPd61gMN+hPB/lfyv+oWkxjK2BjBDRwPBCwWhuJVAiWg8zz7u6Oh\n" +
                                    "I2VAKSSSQSsdB4+SwbrDbc+xw2lx1aTMR7ds/JX+pZbQDjsALvzyQfb+c3b9H3/nNd+Ys1SQHVbI\n" +
                                    "qSSST1r/AP/V7NJJJTsakgYIPgZ+74JJJKd3Gu9ehtsQXDUDWCNCirIwMz0HCp8CpxJ3fuk+P8la\n" +
                                    "3KhkKK8G10kkkEqSSSSUpVuoNe7EeGN3HQkd4BDtFZSSBo2po9Je00OYDq1xJHk7VpV5ZFwdg5os\n" +
                                    "Y0+mdQJ+kD/OD/O9y0a8vHtaXNeIaQDJjU/RTpDqOqB2TIGXlNx6yQQbIljCYnt/FRvzqamOIMvE\n" +
                                    "ta2D9Idjp7Vk332X2epZG6AABwAEoxvdRLFznOMvcXO7uOp0TJJKVYpJJJJT/9bs0kklOxqSSSSU\n" +
                                    "oEgyDBGoI7FaGF1ANDmZDoaI2EyTr9Lc7+Ss9JAgFINPQV2MsYHsO5rtQQpLExcuzGf3dUfpM/78\n" +
                                    "2fzlqUZmPeBscA4zDHaO0/kqOUSFwNp0kkk1KkklXvzselphwe8aBjSCZ/lfupAWpp9Wc03VsHLW\n" +
                                    "kn+0Rt/6hUg5zSC0lpGoIMaxEp7LH22OseZc7mNB8FFTAUKWE6rAAcJ0kkUKSSSSUpJJJJT/AP/X\n" +
                                    "7NJJJTsakkkklKSSSSUpJJJJSRmRkVzstcJ51n/q9ykM7MAI9U694bP/AFKCkhQ7JtKcvKcINzo8\n" +
                                    "oH4tAQeOE6SKFJJJJKUkkkkpSSSSSlJJJJKf/9Ds0kklOxqSSSSUpJJJJSkkkklKSSSSUpJJJJSk\n" +
                                    "kkklKSSSSUpJJJJSkkkklP8A/9kAOEJJTQQhAAAAAABVAAAAAQEAAAAPAEEAZABvAGIAZQAgAFAA\n" +
                                    "aABvAHQAbwBzAGgAbwBwAAAAEwBBAGQAbwBiAGUAIABQAGgAbwB0AG8AcwBoAG8AcAAgAEMAUwA0\n" +
                                    "AAAAAQA4QklNBAYAAAAAAAf//wAAAAEBAP/hEfNodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAv\n" +
                                    "ADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4\n" +
                                    "OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3Jl\n" +
                                    "IDQuMi4yLWMwNjMgNTMuMzUyNjI0LCAyMDA4LzA3LzMwLTE4OjEyOjE4ICAgICAgICAiPiA8cmRm\n" +
                                    "OlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1u\n" +
                                    "cyMiPiA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5h\n" +
                                    "ZG9iZS5jb20veGFwLzEuMC8iIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMv\n" +
                                    "MS4xLyIgeG1sbnM6cGhvdG9zaG9wPSJodHRwOi8vbnMuYWRvYmUuY29tL3Bob3Rvc2hvcC8xLjAv\n" +
                                    "IiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RF\n" +
                                    "dnQ9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZUV2ZW50IyIgeG1s\n" +
                                    "bnM6dGlmZj0iaHR0cDovL25zLmFkb2JlLmNvbS90aWZmLzEuMC8iIHhtbG5zOmV4aWY9Imh0dHA6\n" +
                                    "Ly9ucy5hZG9iZS5jb20vZXhpZi8xLjAvIiB4bXA6Q3JlYXRvclRvb2w9IkFkb2JlIFBob3Rvc2hv\n" +
                                    "cCBDUzQgKDExLjB4MjAwODA4MjYgWzIwMDgwODI2Lm0uNDU2IDIwMDgvMDgvMjY6MDI6MDA6MDAg\n" +
                                    "Y3V0b2ZmOyBtIGJyYW5jaF0pICBXaW5kb3dzIiB4bXA6Q3JlYXRlRGF0ZT0iMjAxNy0xMS0zMFQx\n" +
                                    "MTowNjoxMS0wNzowMCIgeG1wOk1vZGlmeURhdGU9IjIwMTctMTEtMzBUMTE6MTY6MjktMDc6MDAi\n" +
                                    "IHhtcDpNZXRhZGF0YURhdGU9IjIwMTctMTEtMzBUMTE6MTY6MjktMDc6MDAiIGRjOmZvcm1hdD0i\n" +
                                    "aW1hZ2UvanBlZyIgcGhvdG9zaG9wOkNvbG9yTW9kZT0iMyIgcGhvdG9zaG9wOklDQ1Byb2ZpbGU9\n" +
                                    "ImMyIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjhDMzVGMDk1RkFENUU3MTFBMTI2RUYzNkU4\n" +
                                    "MDVFNzhFIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjhCMzVGMDk1RkFENUU3MTFBMTI2RUYz\n" +
                                    "NkU4MDVFNzhFIiB4bXBNTTpPcmlnaW5hbERvY3VtZW50SUQ9InhtcC5kaWQ6OEIzNUYwOTVGQUQ1\n" +
                                    "RTcxMUExMjZFRjM2RTgwNUU3OEUiIHRpZmY6T3JpZW50YXRpb249IjEiIHRpZmY6WFJlc29sdXRp\n" +
                                    "b249Ijk2MDAwMC8xMDAwMCIgdGlmZjpZUmVzb2x1dGlvbj0iOTYwMDAwLzEwMDAwIiB0aWZmOlJl\n" +
                                    "c29sdXRpb25Vbml0PSIyIiB0aWZmOk5hdGl2ZURpZ2VzdD0iMjU2LDI1NywyNTgsMjU5LDI2Miwy\n" +
                                    "NzQsMjc3LDI4NCw1MzAsNTMxLDI4MiwyODMsMjk2LDMwMSwzMTgsMzE5LDUyOSw1MzIsMzA2LDI3\n" +
                                    "MCwyNzEsMjcyLDMwNSwzMTUsMzM0MzI7Qjg4MzMzNTlBRjdBMEI4MzEyNzQ4MzZGQkMxQzQ5RDEi\n" +
                                    "IGV4aWY6UGl4ZWxYRGltZW5zaW9uPSIzMDAiIGV4aWY6UGl4ZWxZRGltZW5zaW9uPSIzMDAiIGV4\n" +
                                    "aWY6Q29sb3JTcGFjZT0iMSIgZXhpZjpOYXRpdmVEaWdlc3Q9IjM2ODY0LDQwOTYwLDQwOTYxLDM3\n" +
                                    "MTIxLDM3MTIyLDQwOTYyLDQwOTYzLDM3NTEwLDQwOTY0LDM2ODY3LDM2ODY4LDMzNDM0LDMzNDM3\n" +
                                    "LDM0ODUwLDM0ODUyLDM0ODU1LDM0ODU2LDM3Mzc3LDM3Mzc4LDM3Mzc5LDM3MzgwLDM3MzgxLDM3\n" +
                                    "MzgyLDM3MzgzLDM3Mzg0LDM3Mzg1LDM3Mzg2LDM3Mzk2LDQxNDgzLDQxNDg0LDQxNDg2LDQxNDg3\n" +
                                    "LDQxNDg4LDQxNDkyLDQxNDkzLDQxNDk1LDQxNzI4LDQxNzI5LDQxNzMwLDQxOTg1LDQxOTg2LDQx\n" +
                                    "OTg3LDQxOTg4LDQxOTg5LDQxOTkwLDQxOTkxLDQxOTkyLDQxOTkzLDQxOTk0LDQxOTk1LDQxOTk2\n" +
                                    "LDQyMDE2LDAsMiw0LDUsNiw3LDgsOSwxMCwxMSwxMiwxMywxNCwxNSwxNiwxNywxOCwyMCwyMiwy\n" +
                                    "MywyNCwyNSwyNiwyNywyOCwzMDsyRjQzMjMzQTg4MkE1QkQ1MDY4NzIyNjlBMTQ0OTFBNSI+IDx4\n" +
                                    "bXBNTTpIaXN0b3J5PiA8cmRmOlNlcT4gPHJkZjpsaSBzdEV2dDphY3Rpb249ImNyZWF0ZWQiIHN0\n" +
                                    "RXZ0Omluc3RhbmNlSUQ9InhtcC5paWQ6OEIzNUYwOTVGQUQ1RTcxMUExMjZFRjM2RTgwNUU3OEUi\n" +
                                    "IHN0RXZ0OndoZW49IjIwMTctMTEtMzBUMTE6MTY6MjktMDc6MDAiIHN0RXZ0OnNvZnR3YXJlQWdl\n" +
                                    "bnQ9IkFkb2JlIFBob3Rvc2hvcCBDUzQgKDExLjB4MjAwODA4MjYgWzIwMDgwODI2Lm0uNDU2IDIw\n" +
                                    "MDgvMDgvMjY6MDI6MDA6MDAgY3V0b2ZmOyBtIGJyYW5jaF0pICBXaW5kb3dzIi8+IDxyZGY6bGkg\n" +
                                    "c3RFdnQ6YWN0aW9uPSJzYXZlZCIgc3RFdnQ6aW5zdGFuY2VJRD0ieG1wLmlpZDo4QzM1RjA5NUZB\n" +
                                    "RDVFNzExQTEyNkVGMzZFODA1RTc4RSIgc3RFdnQ6d2hlbj0iMjAxNy0xMS0zMFQxMToxNjoyOS0w\n" +
                                    "NzowMCIgc3RFdnQ6c29mdHdhcmVBZ2VudD0iQWRvYmUgUGhvdG9zaG9wIENTNCAoMTEuMHgyMDA4\n" +
                                    "MDgyNiBbMjAwODA4MjYubS40NTYgMjAwOC8wOC8yNjowMjowMDowMCBjdXRvZmY7IG0gYnJhbmNo\n" +
                                    "XSkgIFdpbmRvd3MiIHN0RXZ0OmNoYW5nZWQ9Ii8iLz4gPC9yZGY6U2VxPiA8L3htcE1NOkhpc3Rv\n" +
                                    "cnk+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                                    "ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDw/eHBhY2tldCBlbmQ9\n" +
                                    "InciPz7/4gIcSUNDX1BST0ZJTEUAAQEAAAIMbGNtcwIQAABtbnRyUkdCIFhZWiAH3AABABkAAwAp\n" +
                                    "ADlhY3NwQVBQTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA9tYAAQAAAADTLWxjbXMAAAAAAAAA\n" +
                                    "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAApkZXNjAAAA/AAAAF5jcHJ0\n" +
                                    "AAABXAAAAAt3dHB0AAABaAAAABRia3B0AAABfAAAABRyWFlaAAABkAAAABRnWFlaAAABpAAAABRi\n" +
                                    "WFlaAAABuAAAABRyVFJDAAABzAAAAEBnVFJDAAABzAAAAEBiVFJDAAABzAAAAEBkZXNjAAAAAAAA\n" +
                                    "AANjMgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                                    "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB0ZXh0AAAAAEZCAABYWVogAAAAAAAA9tYAAQAA\n" +
                                    "AADTLVhZWiAAAAAAAAADFgAAAzMAAAKkWFlaIAAAAAAAAG+iAAA49QAAA5BYWVogAAAAAAAAYpkA\n" +
                                    "ALeFAAAY2lhZWiAAAAAAAAAkoAAAD4QAALbPY3VydgAAAAAAAAAaAAAAywHJA2MFkghrC/YQPxVR\n" +
                                    "GzQh8SmQMhg7kkYFUXdd7WtwegWJsZp8rGm/fdPD6TD////uAA5BZG9iZQBkgAAAAAH/2wCEABIO\n" +
                                    "Dg4QDhUQEBUeExETHiMaFRUaIyIXFxcXFyIRDAwMDAwMEQwMDAwMDAwMDAwMDAwMDAwMDAwMDAwM\n" +
                                    "DAwMDAwBFBMTFhkWGxcXGxQODg4UFA4ODg4UEQwMDAwMEREMDAwMDAwRDAwMDAwMDAwMDAwMDAwM\n" +
                                    "DAwMDAwMDAwMDAwMDP/AABEIASwBLAMBIgACEQEDEQH/3QAEABP/xAE/AAABBQEBAQEBAQAAAAAA\n" +
                                    "AAADAAECBAUGBwgJCgsBAAEFAQEBAQEBAAAAAAAAAAEAAgMEBQYHCAkKCxAAAQQBAwIEAgUHBggF\n" +
                                    "AwwzAQACEQMEIRIxBUFRYRMicYEyBhSRobFCIyQVUsFiMzRygtFDByWSU/Dh8WNzNRaisoMmRJNU\n" +
                                    "ZEXCo3Q2F9JV4mXys4TD03Xj80YnlKSFtJXE1OT0pbXF1eX1VmZ2hpamtsbW5vY3R1dnd4eXp7fH\n" +
                                    "1+f3EQACAgECBAQDBAUGBwcGBTUBAAIRAyExEgRBUWFxIhMFMoGRFKGxQiPBUtHwMyRi4XKCkkNT\n" +
                                    "FWNzNPElBhaisoMHJjXC0kSTVKMXZEVVNnRl4vKzhMPTdePzRpSkhbSVxNTk9KW1xdXl9VZmdoaW\n" +
                                    "prbG1ub2JzdHV2d3h5ent8f/2gAMAwEAAhEDEQA/AOiSSSUi1SSSSSlJJJJKUkkkkpSSSSSlJJJJ\n" +
                                    "KUkkkkpSSSSSlJJJJKUkkkkpSSSSSlJJJJKUkkkkpSSSSSlJJJJKUkkkkpSSSSSlJJJJKUkkkkp/\n" +
                                    "/9DokkklItUkkkkpSSSSSlJJJJKUkkkkpSSSSSlJJJJKUkkkkpSSSSSlJJJJKUkkkkpSSSSSlJJJ\n" +
                                    "JKUkkkkpSSSSSlJJJJKUkkkkpSSSSSlJJJJKf//R6JJJJSLVJJJJKUkkkkpSSSSSlJJJJKUkkkkp\n" +
                                    "SSSSSlJJJJKUkkna0ucGjkmPvSUsna0ucGjkmPvVn7GSTB2tB0nUn953tVtrWtENAA8tEDJVOc2m\n" +
                                    "1ztoaeYntp/KUHNLSWuEEchaqia6yZLQT4kBDiTTlpKxlUhhD26NdyPAqunAoUkkkkpSSSSSlJJJ\n" +
                                    "JKUkkkkpSSSSSlJJJJKUkkkkp//S6JJJJSLVJJJJKUkkkkpSSSSSlJJJJKUkkkkpSSSSSlJJJJKZ\n" +
                                    "MYXuDW8lX241TXBwGo8fH99Rrp/RMBJaRr7dDJRwAAAOBoE0lICkkkk1KkkkklNbMI9No7kz9wVJ\n" +
                                    "Xc0+xonkzHwVJPjstKkkkkVKSSSSUpJJJJSkkkklKSSSSUpJJJJSkkkklP8A/9PokkklItUkkkkp\n" +
                                    "SSSSSlJJJJKUkkrGNTvducPYOPMpFSJlVj/otnz7Iww7CJJAPYK41rWNDWiAOAnTeJNNQ4Wgh0GN\n" +
                                    "R2lVnVvaYc0g6/gtRIgHkT8UhJVOSi0Veo8SJaPpJ8ikVuEfRdwPBWMMD0iRyTqiToitWwBAhJJJ\n" +
                                    "MXKSSSSUpJJIkAEngalJTQyrA+zaOG6fP85AUrHBz3OaIBMwkyt7/oiY5Ug2WsU7WOd9EExzAlWm\n" +
                                    "YZ13kcQInn9781Wa6xWwNHzPiUDJVNEYtpaTEHSAe6N9kJaGkgbZ9wEl0/vfRVpJN4immpdjMZVL\n" +
                                    "QSR3/wDJqotYgEQdQVTtxAA57XaDXaf/ACSIPdRDVSSSTkKSSSSUpJJJJSkkkklP/9TokkklItUk\n" +
                                    "kkkpSSSSSlJJK/itYGbmmSY3eRH/AJ0kTSkdGKID7OeQ3/yatpJJhNrlJJJIKUkkkkpp5rvc1vgJ\n" +
                                    "n4/+cKxQ1zKmtcIImR80C1rHZID3SDGg7fyP7atgACBoAidgEKSSSQSpJJJJSkDLeW1QPzjBKOq+\n" +
                                    "Xv2aAFnc9wZRG6i0mN3ODeJIE/FaVVTKxDeTEnxhAxKxG8t1/NcrSMigKSSSTUqSSSSUpDyHFtLi\n" +
                                    "PCPv9qIoXM3sLd20ck+QSCnMSSMAkAyOxSUi1SSSSSlJJJJKUkkkkp//1eiSSSUi1SSSSSlJJJJK\n" +
                                    "Z1Vm1+0adyVosYGNDR25PEn95VcJo3Od3AA+/wD85VxNkdUhSSSSalSSSSSlJJJJKaVoP2seZbH/\n" +
                                    "AEVdWdfa97tr4lhI0VnHvNhLXD3ATITiNAgNhJJJNSpJJJJSlUyYfcxgBPjB8VZssFbC4/IeJVXG\n" +
                                    "Isuc9/0+R4fuojugttrQ0BrRAHATpJIJUkkkkpSSSSSlIOU8tq0/OMH4EORlQyrS5+0H2t0+aIGq\n" +
                                    "CgSSST0KSSSSUpJJJJSkkkklP//W6JJJJSLVJJJJKUkkkkps4RO9w/NjX4/mq6qmF+f8v+/K2mS3\n" +
                                    "SFJJJIJUkkkkpShc4sqc4GCOCVNVsx5DQwfnanx0+iiN1NIkkydSVJjyx25vI4+aiknrW3VlDafV\n" +
                                    "JLuQY/6PtVprg5ocOCJ+9ZSmPVhpG6OGkTyf3EDFNuiLay0O3AA8E6cf1kGzLa2Q0bj4/mqqa7nH\n" +
                                    "Vpk94UC1wmRwYJ7SlwhVsrLX2H3HTwHCuYjYqmB7jz/5JUFfxHTVH7p/A+5KWygnSSSTEqSSSSUp\n" +
                                    "JJM5wa0uPA10SUwvsNdZcPpcBZqNkXC0iBDW8Tzqgp4FLSpJJJFSkkkklKSSSSUpJJJJT//X6JJJ\n" +
                                    "JSLVJJJJKUkkkkpNj3Ct+v0Xc/8AkloAgiRqCslErufX9E6dweECLUC6SSBVlNfo/wBru54b/wBU\n" +
                                    "puyKWmC4fLX/AKlNorkig62tpLXOAIEkKk7IukgPkcTAChXW+18Dnkko8PdFtyzKrb9H3EiRHCou\n" +
                                    "cXuLjyVN1FrXBu2SeIRasV8y8QPv0IR0CNUFdbrHbW8o/wBjfuGo26Sf+q2K41jW/RAE8wIToGSa\n" +
                                    "av2IT9PTwjWFaAAAA4GgSSQtKlCyptg1GoBAPhKmkgpqHCMaP1+H/mShU92O8te3Q8+KvIeQ1pqd\n" +
                                    "u7CR8U6+6KZtcHAEdwDHeCnWZW/03h3MciYlWBm6nc3T82PH+UkYqttpKm3MdPuaCPLlEdmVAaAk\n" +
                                    "x/q1Ciq2wTAkCfIf+ZLPuvL3e0lrYiJ5TE23vGk9tPotlWRh1xqTPyR0G6t2ip11usdDfmewV4Y9\n" +
                                    "IMhv3yf+qRGsa36IAnmBCXEqnN9K39x33FQIIMEQfArWUH1V2EF4khLiVTmJJ3NLXFp5Bj7kychS\n" +
                                    "SSSSlJJJJKf/0OiSSSUi1SSSSSlJJJJKUkkkkpSk1jnAlokNElXWUMDCGOndo53On57WIzWMaSWg\n" +
                                    "CeY8kOJNNKnFLwHOMNPhyrVVLKhpq7u5EAAEDQBJNJKaUkkkgpSSSSSlJJJJKUkkkkpSDlEei7zi\n" +
                                    "PvRlXzP5of1v4ORG6i0UklYx6A87nQWwdAdQf5aeVrCqoutDHgtnXwP/AEldqoZWBAl37xUy1riC\n" +
                                    "RJbqE6YTaaWaxrSS0QXGSnSSQSpJJJJSkjMGOe08Skmc4NBc4wByUlObaHh53iHHUgeagp2ua+wu\n" +
                                    "aIBUFItUkkkkpSSSSSn/0eiSSSUi1SSSSSlJJJJKUrWLSxwL3awYAPCqq7hE7XCNAdD5oHZQbKSS\n" +
                                    "SYuUkkkkpSSSSSlJJJJKUkkkkpSSSSSlKrmP0DC3nUO81aVfL/m4I7iCPH+UiN1FoiCQCYHcrTqr\n" +
                                    "bW3aPv7lUKI9VoI3AmIWkjJAUkkkmpUkkkkpSSSSSlIVtlTfZZw4cxoikgAk8DUrOtvfYSJ9kyAi\n" +
                                    "BaCiSSST0KSSSSUpJJJJT//S6JJJJSLVJJJJKUkkkkpSuYX0Xa9xoqaPj3Pa8N5a4xHhP7qB2UG+\n" +
                                    "kkkmLlJJJJKUkkkkpSSSSSlJJJJKUkkkkpSi9jXt2u4UkjMaanz0SU5lbzXYHRq3kH/NWk1wc0OH\n" +
                                    "BE/espadJBqYR4Afd7U6SAzSSSTUqSSSSUpJJBybXVsG3knnwSU18qxj9pY6QJ0/zVXSSUgWqSSS\n" +
                                    "SUpJJJJSkkkklP8A/9PokkklItUkkkkpSSSSSlJJJJKdSt26trpkkCT5qSzarXVuGvt7haQIIBHB\n" +
                                    "1CYRSQpJJJBKkkkklKSSSSUpJJJJSkkkklKSSSSU1Mqokh41cTEAeSHjWlj9h1a8gfAq+st7HMcW\n" +
                                    "uEEJw1FILqJKtVlAsJs0Lfvd/YVlNISpJM57WkNJgu4Qrb2MDmz7wNBHcpUpnc/06y4c8D4lZpc4\n" +
                                    "iCSQTPz/AHknPc76RJjiTKZPApaSpJJJFSkkkklKSSSSUpJJJJT/AP/U6JJJJSLVJJJJKUkkkkpS\n" +
                                    "SSSSlKzj3NZptgfnGdNPz9qrJSYjskQp1gQRI1BSVKjIbXXtdJM6DyKtMursJDTMaphC62aSSSCl\n" +
                                    "JJJJKUkkkkpSSSSSlJJJJKUqOZ/Oj+r/ABcryz8p4daY/N9v3Ix3QUKm21waGfmzLgNN39ZQST0J\n" +
                                    "H3PdAHta2NoHaPo+5DJJMkyfEpJJKUkkkkpSSSSSlJJJJKUkkkkpSSSSSn//1eiSSSUi1SSSSSlJ\n" +
                                    "JJJKUkkkkpSSSSSlJ2ucxwc0wRwUySSnQpvFukQ4CT4IyymuLSHNMEcFWast0gWRt/e7/wDRTTHs\n" +
                                    "kFuJKDLa7CQwyQppqVJJJJKUkkkkpSSZzg0FzjAHJQXZdQEg7j4RH/VJUpJba2psnnsPFZhJJk6k\n" +
                                    "qT3ue7c7lRTwKWkqSSSRUpJJJJSkkkklKSSSSUpJJJJSkkkklKSSSSU//9bokkklItUkkkkpSSSS\n" +
                                    "SlJJJJKUkkkkpSSSSSlJJJJKUCQZGhCmLrQZDz8zP/VKCSSmx9st8G/j/wCSTtzXz7mgjy0/8kqy\n" +
                                    "SVBVtl2a+fa0Aeev/kU32y3wb+P/AJJV0kqCrZ2XWWfSOnIHZQSSSUpJJJJSkkkklKSSSSUpJJJJ\n" +
                                    "SkkkklKSSSSUpJJJJSkkkklP/9fokkklItUkkkkpSSSSSlJJJJKUkkkkpSSSSSlJJJJKUkkkkpSS\n" +
                                    "SSSlJJJJKUkkkkpSSSSSlJJJJKUkkkkpSSSSSlJJJJKUkkkkpSSSSSlJJJJKf//Q6JJJJSLVJJJJ\n" +
                                    "KUkkkkpSSSSSlJJJJKUkkkkpSSSSSlJJJJKUkkkkpSSSSSlJJJJKUkkkkpSSSSSlJJJJKUkkkkpS\n" +
                                    "SSSSlJJJJKUkkkkpSSSSSn//0eiSSSUi1SSSSSlJJJJKUkkkkpSSSSSlJJJJKUkkkkpSSSSSlJJJ\n" +
                                    "JKUkkkkpSSSSSlJJJJKUkkkkpSSSSSlJJJJKUkkkkpSSSSSlJJJJKUkkkkp//9k=\n";
                            e.printStackTrace();
                        }
                        conexionWeb = new ConexionWeb(agregar_mascota.this);
                        conexionWeb.agregarVariables("idusuarios",idusuarios);
                        conexionWeb.agregarVariables("nombre", name);
                        conexionWeb.agregarVariables("sexo", sex);
                        conexionWeb.agregarVariables("edad", edad1);
                        conexionWeb.agregarVariables("tipo_mascota_idtipo_mascota", idtipos[tipo.getSelectedItemPosition()]);
                        conexionWeb.agregarVariables("razamascota_idrazamascota",  idraza[raza.getSelectedItemPosition()]);
                        conexionWeb.agregarVariables("foto_mas",imagebase64string);
                        URL direccion = new URL("http://carolina.x10host.com/index.php/Sistema/registro_mascota");
                        conexionWeb.execute(direccion);
                        Toasty.Config.getInstance()
                                .apply(); // required
                        Toasty.success(agregar_mascota.this, "Mascota Agregada!", Toast.LENGTH_SHORT, true).show();
                        Intent inicio = new Intent(agregar_mascota.this,MainActivity.class);
                       // inicio.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(inicio);
                    } catch (MalformedURLException e) {
                        Toast.makeText(agregar_mascota.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }else{
                    Toasty.Config.getInstance()
                            .apply(); // required
                    Toasty.error(agregar_mascota.this, "ERROR!,Campos Vacios", Toast.LENGTH_SHORT, true).show();
                }
            }
        });
        galerria = (Button) findViewById(R.id.btnGaleria);
        galerria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE); */
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);//
                startActivityForResult(Intent.createChooser(intent, "Selecionar foto grafia del perrito"), 1);
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
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            if (data != null) {
                try {
                    Bitmap bmp = null;
                    bmp = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                    imagenenviar=bmp;
                    imageView.setImageBitmap(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
           /* try {
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
            }*/
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
            Toast.makeText(agregar_mascota.this, r, Toast.LENGTH_LONG).show();
        try{
            JSONArray arrayjson = new JSONArray(r);
            if (arrayjson.getJSONObject(0).has("idtipo_mascota")) {
                List<String> spinnerArray =  new ArrayList<String>();
                idtipos = new String[arrayjson.length()];
                for (int i = 0; i < arrayjson.length(); i++) {
                    spinnerArray.add(arrayjson.getJSONObject(i).getString("nombre"));
                    idtipos[i]=arrayjson.getJSONObject(i).getString("idtipo_mascota");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        this, android.R.layout.simple_spinner_item, spinnerArray);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                tipo.setAdapter(adapter);
                cargarRazas(idtipos[0]);
            }else
            {
                JSONArray arrayjson2 = new JSONArray(r);
                if (arrayjson2.getJSONObject(0).has("idrazamascota")) {
                    List<String> spinnerArray =  new ArrayList<String>();
                    idraza= new String[arrayjson2.length()];
                    for (int i = 0; i < arrayjson2.length(); i++) {
                        spinnerArray.add(arrayjson2.getJSONObject(i).getString("nombre_raza"));
                       idraza[i]=arrayjson.getJSONObject(i).getString("idrazamascota");
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            this, android.R.layout.simple_spinner_item, spinnerArray);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    raza.setAdapter(adapter);
                }
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
    public void cargarRazas(String id){
        try {
            conexionWeb = new ConexionWeb(agregar_mascota.this);
            conexionWeb.agregarVariables("idraza", id);
            URL direcciopn = new URL("http://carolina.x10host.com/index.php/Sistema/razas_datos_android_id_tipo");
            conexionWeb.execute(direcciopn);
        } catch (MalformedURLException e) {
            Toast.makeText(agregar_mascota.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
