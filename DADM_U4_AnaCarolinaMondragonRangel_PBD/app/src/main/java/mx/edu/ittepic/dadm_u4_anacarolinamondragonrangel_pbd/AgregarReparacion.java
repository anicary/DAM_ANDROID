package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_pbd;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AgregarReparacion extends AppCompatActivity {
    EditText ed1,ed2,ed3;
    Spinner SpinnerCliente;
    Button btnagregarCliente;
    BD db;

    Calendar miCalendario;
    DatePickerDialog.OnDateSetListener fecha;
    List<String> listaSpinner =  new ArrayList<String>();
    int [] idclientes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_reparacion);
        db = new BD(AgregarReparacion.this, "reparacionCelular", null, 1);
        ed1=(EditText)findViewById(R.id.fecha);
        ed2=(EditText)findViewById(R.id.costo);
        ed2=(EditText)findViewById(R.id.rdescrpcion);
        btnagregarCliente=(Button) findViewById(R.id.btnagregar);
        SpinnerCliente=(Spinner)findViewById(R.id.spinnerCliente);
        miCalendario = Calendar.getInstance();
        SQLiteDatabase base = db.getReadableDatabase();
        Cursor c = base.rawQuery("SELECT * FROM cliente", null);
        idclientes= new int[c.getCount()];
        int pos=0;
        while (c.moveToNext()) {
            listaSpinner.add(""+ c.getString(1));
            idclientes[pos]=Integer.parseInt(c.getString(0));
                pos++;
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, listaSpinner);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerCliente.setAdapter(adaptador);
        fecha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                miCalendario.set(Calendar.YEAR, year);
                miCalendario.set(Calendar.MONTH, monthOfYear);
                miCalendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                ed1.setText(""+miCalendario.get(Calendar.YEAR)+"-"+miCalendario.get(Calendar.MONTH)+"-"+miCalendario.get(Calendar.DAY_OF_MONTH));
            }

        };
        ed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AgregarReparacion.this, fecha, miCalendario
                        .get(Calendar.YEAR), miCalendario.get(Calendar.MONTH),
                        miCalendario.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        btnagregarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int posid= idclientes[ SpinnerCliente.getSelectedItemPosition()];
                    SQLiteDatabase base = db.getWritableDatabase();
                    String query1 = "INSERT INTO orden_reparacion VALUES ("+posid+",'FECHA','COSTO','DESCRIP')";
                    query1 = query1.replace("COSTO", ed2.getText().toString());
                    query1 = query1.replace("FECHA", ed1.getText().toString());
                    query1 = query1.replace("DESCRIP", ed2.getText().toString());

                    base.execSQL(query1);
                    Intent intent = new Intent(AgregarReparacion.this, ListaOrdenes.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }catch (SQLException e){
                    Toast.makeText(AgregarReparacion.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
