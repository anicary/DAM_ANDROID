package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_pbd;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AgregarEquipo extends AppCompatActivity {
    EditText descripcion,tipo;
    String description, type;
    int idordenreparacion;
    Button btnagregarEquipo;
    Intent intent;
    BD db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_equipo);

        descripcion=(EditText)findViewById(R.id.descripcion);
        tipo=(EditText)findViewById(R.id.tipo);
        btnagregarEquipo=(Button) findViewById(R.id.btnagregarEquipo);

        db = new BD(AgregarEquipo.this, "reparacionCelular", null, 1);
        btnagregarEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idordenreparacion = (getIntent().getExtras().getInt("idordenreparacion"));
                description =descripcion.getText().toString();
                type = tipo.getText().toString();
                try {
                    SQLiteDatabase base = db.getWritableDatabase();
                    String query1 = "INSERT INTO aparato VALUES (null,'DESCRIPCION','TIPO',"+idordenreparacion+")";
                    query1 = query1.replace("DESCRIPCION", description);
                    query1 = query1.replace("TIPO",type);
                    base.execSQL(query1);
                    intent = new Intent(AgregarEquipo.this, ListaOrdenes.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }catch (SQLException e){
                    Toast.makeText(AgregarEquipo.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
