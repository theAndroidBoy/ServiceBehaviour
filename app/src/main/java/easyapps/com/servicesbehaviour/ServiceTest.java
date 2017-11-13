package easyapps.com.servicesbehaviour;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

public class ServiceTest extends Service {

    private int mRandomNumber;
    private boolean mIsRandomGeneratorOn;
    private final int MAX = 10;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("flow", "onBind: ");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("flow", "onStartCommand: service started ");
        Log.i("flow", "onStartCommand: Thread Id of this service : " + Thread.currentThread().getId());
        AppClass.incrementStatus();
            Log.i("flow", "onStartCommand: service started for the "+(AppClass.getStatus())+" time");


        mIsRandomGeneratorOn = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("flow", "run: new Thread created ");
                startRandomNumberGenerator();
            }
        }).start();
        Log.i("flow", "onStartCommand: returning Start_Sticky,but we have 2 other options also");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRandomNumberGenerator();
        Log.i("flow", "Service Destroyed");
    }


    private void startRandomNumberGenerator() {
        Log.i("flow", "startRandomNumberGenerator: ");
        while (mIsRandomGeneratorOn) {
            try {
                Thread.sleep(2000);
                if (mIsRandomGeneratorOn) {
                    mRandomNumber = new Random().nextInt(MAX);
                    Log.i("flow", "Thread id: " + Thread.currentThread().getId() + ", Random Number: " + mRandomNumber);
                }
            } catch (InterruptedException e) {
                Log.i("flow", "Thread Interrupted");
            }

        }
    }

    private void stopRandomNumberGenerator() {
        Log.i("flow", "stopRandomNumberGenerator: ");
        mIsRandomGeneratorOn = false;
    }



}
