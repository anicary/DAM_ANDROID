package mx.edu.ittepic.dadm_u3_ana_carolina_mondragon_rangel_provedorcontenido;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Mensajes extends AppCompatActivity {
    private ListView mensajes;
    private static final int PERMISO_DE_LECTURA = 100; //INT PERMISO DE LECTURA CON CATEGORIA DE 100
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes);
        this.mensajes = (ListView) findViewById(R.id.mensajes);
        mostrarMensajes();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISO_DE_LECTURA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mostrarMensajes();
            } else {
                Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void mostrarMensajes() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_SMS}, PERMISO_DE_LECTURA);

        } else {

            List<String> contactosRetornados = obtenerMensajes(); //VA A CARGAR EN UNA LISTA LOS CONTACTOS OBTENIDOS
            //LOS METE EN UN LISTVIEW MENSAJE
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactosRetornados);
            mensajes.setAdapter(adaptador);
        }
    }
    private List<String> obtenerMensajes() {
        List<String> mesajesLista = new ArrayList<>();
        ContentResolver cr = getContentResolver();//ES PARA RESOLVER EL CONTENTPROVIDER
        //QUERY DE CONTERRESOLVER  //CONTENTPROVIDER
        Cursor cursor = cr.query(Telephony.Sms.Inbox.CONTENT_URI,
                //RETORNA UN STRING QUE SACA DE TELEFONIA EL CUERPO DE LOS SMS QUE RECIBIMOS
                new String[] { Telephony.Sms.Inbox.BODY },
                null,
                null,
                Telephony.Sms.Inbox.DEFAULT_SORT_ORDER);
        if (cursor.moveToFirst()) { //COMPARA SI LA BUSQUEDA TUVO UN RESULTADO
            do {
                String Cuerpo = cursor.getString(cursor.getColumnIndex(Telephony.Sms.Inbox.BODY));
                mesajesLista.add(Cuerpo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return mesajesLista;
    }
}
