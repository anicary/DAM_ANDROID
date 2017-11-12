package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_pbd;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarCliente extends AppCompatActivity {
    BD db;
    EditText nombre, dom, col;
    String name, domicilio, colonia;
    Button agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);

        db = new BD(AgregarCliente.this, "prueba1", null, 1);

        nombre = (EditText) findViewById(R.id.nombre);
        dom = (EditText) findViewById(R.id.domicilio);
        col = (EditText) findViewById(R.id.colonia);

        agregar = (Button) findViewById(R.id.agregar);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = nombre.getText().toString();
                domicilio = dom.getText().toString();
                colonia = col.getText().toString();

                try {
                    SQLiteDatabase base = db.getWritableDatabase();
                    String query1 = "INSERT INTO cliente VALUES (null,'NOMBRE','DOMICILIO','COLONIA')";


                    query1 = query1.replace("NOMBRE", name);
                    query1 = query1.replace("DOMICILIO",domicilio);
                    query1 = query1.replace("COLONIA", colonia);

                    base.execSQL(query1); //no retorna nada
                    finish();
                }catch (SQLException e){
                    Toast.makeText(AgregarCliente.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}