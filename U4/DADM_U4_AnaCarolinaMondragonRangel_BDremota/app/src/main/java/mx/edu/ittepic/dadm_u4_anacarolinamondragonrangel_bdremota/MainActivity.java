package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_bdremota;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    Button agregarcontacto;
    Intent intento;
    ConexionRemota con;
    int idpersona[];
    String datosPersona[][];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Contactos en linea!");
        agregarcontacto =(Button)findViewById(R.id.agregarcon);

        lista =(ListView)findViewById(R.id.lista);

        agregarcontacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intento =new Intent(MainActivity.this,AgregarContacto.class);
                startActivity(intento);
            }
        });
        con= new ConexionRemota(MainActivity.this);
        cargar();
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intento =new Intent(MainActivity.this,EditarContacto.class);
                intento.putExtra("idPersona",idpersona[position]);
                intento.putExtra("nombre", datosPersona[position][0]);
                intento.putExtra("domicilio", datosPersona[position][1]);
                intento.putExtra("telefono", datosPersona[position][2]);
                intento.putExtra("correo", datosPersona[position][3]);
                startActivity(intento);
            }
        });

    }
    public void cargar()
    {
        try {
            con.execute(new URL("http://carolina.x10host.com/seleccionarcontacto.php"));
        }catch (MalformedURLException e)
        {
            AlertDialog.Builder error = new AlertDialog.Builder(MainActivity.this);
            error.setMessage("Error").setIcon(R.drawable.ic_error_red_24dp).setTitle("Error").show();
        }
    }
    public void mensaje(String r)
    {

        if(r.equals("ERROR 404_2") || r.equals("ERROR 404_1"))
        {
            AlertDialog.Builder error=new AlertDialog.Builder(MainActivity.this);
            error.setMessage("Problemas en el servidor").setIcon(R.drawable.ic_error_red_24dp).setTitle("Error").show();
        }
        else
        {
            List<String> listaString = new ArrayList<String>();
            try{
                JSONArray arrayjson = new JSONArray(r);
                idpersona=new int[arrayjson.length()];
                datosPersona=new String[arrayjson.length()][4];
                for(int i = 0; i < arrayjson.length(); i++){
                    listaString.add(arrayjson.getJSONObject(i).getString("nombre"));
                    idpersona[i]=Integer.parseInt(arrayjson.getJSONObject(i).getString("idPersona"));
                    datosPersona[i][0]=arrayjson.getJSONObject(i).getString("nombre");
                    datosPersona[i][1]=arrayjson.getJSONObject(i).getString("domicilio");
                    datosPersona[i][2]=arrayjson.getJSONObject(i).getString("telefono");
                    datosPersona[i][3]=arrayjson.getJSONObject(i).getString("correo");
                }
                ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,listaString);
                ListView listView = (ListView) findViewById(R.id.lista);
                listView.setAdapter(adapter);
            }catch (JSONException e){

            }
        }




    }
}
