package com.example.ingame;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerometorSensor {
    public interface Listener {
        void onTranslation(float tx, float ty, float tz);
    }

    private Listener listener;

    public void setListener(Listener l) {
        listener = l;
    }

    private final SensorManager sensorManager;
    private final Sensor sensor;
    private final SensorEventListener sensorEventListener;

    private final float[] gravity = new float[3];
    private final float[] linear_acceleration = new float[3];

    AccelerometorSensor(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                handleMovement(sensorEvent);
                if (listener != null) {
                    //listener.onTranslation(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
                    listener.onTranslation(linear_acceleration[0], linear_acceleration[1], linear_acceleration[2]);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }

    private void handleMovement(SensorEvent event) {
        // alpha is calculated as t / (t + dT)
        // with t, the low-pass filter's time-constant
        // and dT, the event delivery rate

        final float alpha = 0.9f;

        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];
    }

    public void register() {
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unRegister() {
        sensorManager.unregisterListener(sensorEventListener);
    }
}
