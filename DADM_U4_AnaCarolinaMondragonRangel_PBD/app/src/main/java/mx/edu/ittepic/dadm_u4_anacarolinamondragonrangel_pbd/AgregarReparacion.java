package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_pbd;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class AgregarReparacion extends AppCompatActivity {
    EditText ed1,ed2;
    Spinner SpinnerCliente;
    Button btnagregarCliente;
    BD db;

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_reparacion);

        db = new BD(AgregarReparacion.this, "reparacionCelular", null, 1);

        ed1=(EditText)findViewById(R.id.fecha);
        ed2=(EditText)findViewById(R.id.costo);

        btnagregarCliente=(Button) findViewById(R.id.btnagregar);

        SpinnerCliente=(Spinner)findViewById(R.id.spinnerCliente);
        myCalendar = Calendar.getInstance();

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                ed1.setText(""+myCalendar.get(Calendar.YEAR)+"-"+myCalendar.get(Calendar.MONTH)+"-"+myCalendar.get(Calendar.DAY_OF_MONTH));
            }

        };

        ed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AgregarReparacion.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });


    }
}
