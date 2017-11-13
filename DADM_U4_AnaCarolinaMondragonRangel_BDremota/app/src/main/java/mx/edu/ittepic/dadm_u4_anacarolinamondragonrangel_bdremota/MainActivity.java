package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_bdremota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    Button agregarcontacto;
    Intent intento;
    ConexionRemota con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        List<String> listaString = new ArrayList<String>();
        try{
            JSONArray arr = new JSONArray("[{\"0\":\"3\",\"idPersona\":\"3\",\"1\":\"Carolina\",\"nombre\":\"Carolina\",\"2\":\"Valle real\",\"domicilio\":\"Valle real\",\"3\":\"311\",\"telefono\":\"311\",\"4\":\"carobox\",\"correo\":\"carobox\"},{\"0\":\"2\",\"idPersona\":\"2\",\"1\":\"1\",\"nombre\":\"1\",\"2\":\"1\",\"domicilio\":\"1\",\"3\":\"1\",\"telefono\":\"1\",\"4\":\"1\",\"correo\":\"1\"}]");
            for(int i = 0; i < arr.length(); i++){
                String info = arr.getJSONObject(i).getString("area") + arr.getJSONObject(i).getString("city");
                listaString.add(info);
            }
            ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,listaString);
            ListView listView = (ListView) findViewById(R.id.lista);
            listView.setAdapter(adapter);
        }catch (JSONException e){

        }

    }
}
