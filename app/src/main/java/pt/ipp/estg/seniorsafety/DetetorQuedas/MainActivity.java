package pt.ipp.estg.seniorsafety.DetetorQuedas;

import pt.ipp.estg.seniorsafety.R;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity  extends Activity implements SensorEventListener {

    private Location currentLocation;

    private Timer checkImmobile = new Timer();
    private TimerTask ok;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private final int MAX_RECORDS = 200;
    private final int NUM_FALL_THRESHOLD = 16;
    private final double FALL_MAG_THRESHOLD = 35;
    private final int REST_THRESHOLD = 20;

    private int currRecordInd;
    private int accel_count; //a queda ocorre se accel_count >= NUM_ACCEL_THRESHOLD
    private int idle_count;
    private boolean cycle;

    private float[] accel_data;

    private boolean isAYOActive;

    private Button buttonSettings;
    private TextView titleSettings;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleSettings = (TextView) findViewById(R.id.textFallDetectionStatus);

        buttonSettings = (Button) findViewById(R.id.buttonContactos);

        isAYOActive = false;
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


    }

    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
        isAYOActive = false;

        TextView tv = (TextView) findViewById(R.id.accelerometer_values);
        tv.setText("");

        currRecordInd = 0;
        accel_count = 0;
        cycle = false;
        idle_count = 0;

        accel_data = new float[MAX_RECORDS];
    }

    protected void onPause() {
        super.onPause();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //deixe esse método vazio, não exclua (necessário para implementar a interface)
    }

    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
            return;

        float mSensorX = event.values[0];
        float mSensorY = event.values[1];
        float mSensorZ = event.values[2];

        // para debugging
        TextView tv = (TextView) findViewById(R.id.accelerometer_values);

        // grande loop para verificar o limiar começa aqui

        // 1)obtenha nova leitura do acelerômetro
        float accelValue = mSensorX*mSensorX + mSensorY*mSensorY + mSensorZ*mSensorZ;

        // 2)registre a diferença do acelerômetro e aumente currRecordInd
        if(currRecordInd != 0) { //senão o primeiro registro

            // 3) atualiza accel_count
            //verifique se está em ciclo e, se estiver, se o registro existente está acima ou abaixo de DIFF_THRESHOLD também
            boolean newRecordTap = accelValue < FALL_MAG_THRESHOLD;
            boolean oldRecordTap = accel_data[currRecordInd] < FALL_MAG_THRESHOLD;
            if (newRecordTap) {
                //oldRecordTap booleano = accel_diff [(currRecordInd + MAX_RECORDS - 1)% MAX_RECORDS] <FALL_THRESHOLD;
                if(!oldRecordTap || !cycle) {
                    accel_count++;
                }
                idle_count = 0;
            }
            else {
                //boolean oldRecordTap = accel_diff[(currRecordInd + MAX_RECORDS - 1) % MAX_RECORDS] < FALL_THRESHOLD;
                if(oldRecordTap && cycle) {
                    accel_count--;
                }
                idle_count++;
                if(idle_count >= REST_THRESHOLD)
                    accel_count = Math.max(0, accel_count - 2);
            }
        }
        accel_data[currRecordInd] = accelValue;
        currRecordInd = (currRecordInd + 1) % MAX_RECORDS;

        if(currRecordInd == 0)
            tv.setText("");


        // 4) verifique se o limite accel_count foi atingido; caso contrário, alterne a atividade
        if(accel_count >= NUM_FALL_THRESHOLD) {
            //Precisa verificar se o "você está bem já está chamado"
            if (!isAYOActive){
                isAYOActive = true;
                Intent verification = new Intent(this, Verification.class);
                startActivity(verification);
                currRecordInd++; //Remove this line IF text of Accelerometer is different.
            }
        }
    }

    public void onSettingsButtonClick(View v) {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) { //Whenever ANYTHING is pressed!

        if(ok != null)
            ok.cancel();

        ok = new TimerTask()
        {
            public void run()
            {
                int from = 100;
                int to = 601;
                Calendar c = Calendar.getInstance();
                int t = c.get(Calendar.HOUR_OF_DAY) * 100 + c.get(Calendar.MINUTE);
                if(t < from && t > to) {
                    Intent notif = new Intent(MainActivity.this, Verification.class);
                    startActivity(notif);
                }
                else dispatchTouchEvent(null); //Resets timer if sleeping
            }
        };
        if(event == null) { //If sleeping, sets timer to 10:00am
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, 10);
            c.set(Calendar.MINUTE,0);
            c.set(Calendar.SECOND,0);
            checkImmobile.schedule(ok,c.getTime());
        }
        else checkImmobile.schedule(ok,14400000); //4 Hours == 14400000


        return super.dispatchTouchEvent(event);  //Allows event to continue propagating
    }

}
