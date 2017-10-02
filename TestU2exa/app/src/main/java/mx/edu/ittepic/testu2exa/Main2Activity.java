package mx.edu.ittepic.testu2exa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
 LinearLayout Layon;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String cama = getIntent().getExtras().getString("SUEÑO");
        Toast.makeText(Main2Activity.this,""+cama,Toast.LENGTH_SHORT).show();

        Layon =(LinearLayout) findViewById(R.id.layout1);

        btn1 = new Button(this);
        btn1.setText("TOCAME");
        btn1.setGravity(Gravity.CENTER);

        btn1.setOnClickListener();
    }
}
