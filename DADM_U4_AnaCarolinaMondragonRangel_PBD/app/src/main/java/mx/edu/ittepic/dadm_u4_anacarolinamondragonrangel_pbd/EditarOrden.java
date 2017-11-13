package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_pbd;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarOrden extends AppCompatActivity {
    int idordenreparacion = 0;
    EditText fecha,costo,desc;
    String daytime,cos,descrip;
    BD db;
    Button btnagregar;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_orden);

        fecha=(EditText)findViewById(R.id.fechar);
        costo=(EditText)findViewById(R.id.costor);
        desc=(EditText)findViewById(R.id.rdescrpcionr);
        btnagregar=(Button)findViewById(R.id.btnagregarr);

        fecha.setText(getIntent().getExtras().getString("fechaingreso"));
        costo.setText(getIntent().getExtras().getString("costo"));
        desc.setText(getIntent().getExtras().getString("observaciones"));

        db = new BD(EditarOrden.this, "reparacionCelular", null, 1);

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daytime = fecha.getText().toString();
                cos = costo.getText().toString();
                descrip= desc.getText().toString();
                try {
                    SQLiteDatabase base = db.getWritableDatabase();
                    String query1 = "UPDATE orden_reparacion SET fechaingreso='FECHA',costo='COSTO', observaciones='DESCRIPCION' where idordenreparacion=" + idordenreparacion + ";";
                    query1 = query1.replace("FECHA", daytime);
                    query1 = query1.replace("COSTO",cos);
                    query1 = query1.replace("DESCRIPCION", descrip);
                    base.execSQL(query1);
                    intent = new Intent(EditarOrden.this, ListaOrdenes.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }catch (SQLException e){
                    Toast.makeText(EditarOrden.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
