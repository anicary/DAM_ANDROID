package mx.edu.ittepic.dadm_u3_ana_carolina_mondragon_rangel_provedorcontenido;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Contacto extends AppCompatActivity {
    private ListView nombres;
    private static final int PERMISO_DE_LECTURA = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        this.nombres = (ListView) findViewById(R.id.lnombres);
        mostrarContactos();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISO_DE_LECTURA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mostrarContactos();
            } else {
                Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void mostrarContactos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISO_DE_LECTURA);

        } else {

            List<String> contactosRetornados = obtenerContactos();
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactosRetornados);
            nombres.setAdapter(adaptador);
        }
    }
    private List<String> obtenerContactos() {
        List<String> contactosLista = new ArrayList<>();
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                contactosLista.add(name);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactosLista;
    }
}
