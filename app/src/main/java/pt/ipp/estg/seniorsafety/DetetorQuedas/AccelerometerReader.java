package pt.ipp.estg.seniorsafety.DetetorQuedas;

import android.hardware.Sensor;
import android.hardware.SensorManager;

public class AccelerometerReader {

    private boolean mAccelerometerAvailable = false;
    private boolean isEnabled = false;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    public AccelerometerReader() throws UnsupportedOperationException {
        //Verifique se existe acelerômetro
        for (Sensor aSensor : mSensorManager.getSensorList(Sensor.TYPE_ALL))
            if (aSensor.equals(Sensor.TYPE_ACCELEROMETER))
                mAccelerometerAvailable = true;
        if (!mAccelerometerAvailable)
            throw new UnsupportedOperationException("\n" +"O acelerômetro não está disponível.");
    }

    public void setEnableAccelerometer(boolean doEnable) throws UnsupportedOperationException {
        if (!mAccelerometerAvailable)
            throw new UnsupportedOperationException("O acelerômetro não está disponível.");

        /* Se deve estar ativado e ainda não está */
        if (doEnable && !this.isEnabled) {
            isEnabled = true;
        }

        /* Se deve ser desativado e ainda não está */
        else if (!doEnable && this.isEnabled) {
            isEnabled = false;
        }
    }
}
