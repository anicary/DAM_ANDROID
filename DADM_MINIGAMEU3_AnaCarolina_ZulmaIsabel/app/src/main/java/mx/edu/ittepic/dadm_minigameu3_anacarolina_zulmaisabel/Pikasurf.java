package mx.edu.ittepic.dadm_minigameu3_anacarolina_zulmaisabel;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Pikasurf extends AppCompatActivity  implements SensorEventListener {
    public SensorManager sensores; //SENSOR MANAGER
    public long ultimaact; //ES UNA VARIABLE PARA DETECTAR EL SHAKE
    float x=0,y=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getSupportActionBar().hide();
        setContentView(new Pika(this));
        sensores = (SensorManager) getSystemService(SENSOR_SERVICE); //OBTIENE EL SENSOR DE TELEFONO
        ultimaact = System.currentTimeMillis(); //OBTIENE LOS MILISEGUNDOS ACTUALES
    }
    public void onSensorChanged(SensorEvent sensorEvent) { //CUANDO EL SENSOR DETECTA UN CAMBIO

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) { //TIPO DE SENSOR
            float[] valores = sensorEvent.values;
            x = valores[0];
            y = valores[1];
            float z = valores[2];
            System.out.println("x"+(x*.981));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    protected void onResume() {
        super.onResume();
        sensores.registerListener(this,
                sensores.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensores.unregisterListener(this);
    }

    public class Pika extends View{
        public Pika (Context context) {
            super(context);

        }
    }
}
