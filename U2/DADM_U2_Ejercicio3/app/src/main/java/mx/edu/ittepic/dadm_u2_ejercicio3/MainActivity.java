package mx.edu.ittepic.dadm_u2_ejercicio3;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.LinearLayout;
        import android.widget.RadioButton;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout layin;
    TextView etiqueta;
    EditText pedirNombre;
    Button agregar,quitar;
    RadioButton rbtn;
    CheckBox chkbox;
    /*ARREGLO*/
    CheckBox [] vectorCasillas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layin = (LinearLayout) findViewById(R.id.layout1);

        /*SE CREAN 10 CASILLAS DEL VECTOR TIPO CHECKBOX*/
        vectorCasillas = new CheckBox[10];

        for(int i=0; i<10; i++){
            vectorCasillas[i] = new CheckBox(this);
            vectorCasillas[i].setText("CASILLA NUMERO: "+i);
        }

        etiqueta = new TextView(this);
        pedirNombre = new EditText(this);
        agregar= new Button(this);
        rbtn = new RadioButton(this);
        chkbox = new CheckBox(this);
        quitar = new Button(this);

        etiqueta.setText("ESCRIBA SU NOMBRE: ");
        agregar.setText("AGREGAR OBJETOS");
        quitar.setText("ELIMINAR OBJETOS");

        chkbox.setText("CASADO");
        chkbox.setChecked(true);
        rbtn.setText("ACEPTA");

        /*AGREGANDO LOS OBJETOS AL LAYOUT
        PARA    QUE SE MUESTREN EN PANTALLA */

        layin.addView(agregar);
        layin.addView(quitar);

       /*EVENTO PARA QUE CUANDO DE CLIC EN EL BTN AGREGAR AGREGE LAS COSAS*/
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    layin.addView(etiqueta);
                    layin.addView(pedirNombre);
                    layin.addView(rbtn);
                    layin.addView(chkbox);

                /*SE AGREGARAN LAS CASILLAS DEL 0 AL 9*/
                    for (int i = 0; i < 10; i++) {
                        layin.addView(vectorCasillas[i]);
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"NO AGREGAR 2 VECES",Toast.LENGTH_SHORT).show();
                }
            }
        });
        /*EVENTO PARA QUE CUANDO DE CLIC EN EL BTN QUITAR ELIMINE LAS COSAS*/
        quitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*EL REMOVEVIEW ES PARA OCULTAR LAS COSAS*/
                layin.removeView(etiqueta);
                layin.removeView(pedirNombre);
                layin.removeView(rbtn);
                layin.removeView(chkbox);

                /*SE QUITARAN LAS CASILLAS DEL 0 AL 9*/
                for (int i =0; i<10; i++)
                    layin.removeView(vectorCasillas[i]);

            }
        });
    }
}



