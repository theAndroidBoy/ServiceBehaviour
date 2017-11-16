package easyapps.com.servicesbehaviour;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

public class IntentServiceTest extends IntentService {

    int Counter=0;
    private int mRandomNumber;
    private boolean mIsRandomGeneratorOn;
    private final int MAX = 10;

    public IntentServiceTest() {
        super("flow :workerThread");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) { //this method will run on separate worker thread

        Log.i("flow", "onHandleIntent: this method works on separate thread");
        logSleepThread(700); //time in milliSeconds
        Log.i("flow", "onHandleIntent: Thread id : "+Thread.currentThread().getId());
        logSleepThread(700); //time in milliSeconds
        Log.i("flow", "onHandleIntent: so the Thread Id proves that this method is on separate thread");
        mIsRandomGeneratorOn = true;
        startRandomNumberGenerator();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) { //this method will run on UI thread
        super.onStartCommand(intent,flags,startId);

        Log.i("flow", "onStartCommand: service started ");
        logSleepThread(700); //time in milliSeconds
        Log.i("flow", "onStartCommand: Thread Id of this service : " + Thread.currentThread().getId());
        logSleepThread(700); //time in milliSeconds
        Log.i("flow", "onStartCommand: so onStartCommand is still on UI thread ");


        logSleepThread(700); //time in milliSeconds
        Log.i("flow", "onStartCommand: returning Start_Sticky,but we have 2 other options also");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRandomNumberGenerator();
        Log.i("flow", "Service Destroyed: service destroyed");
        logSleepThread(700); //time in milliSeconds
        Log.i("flow", "Service Destroyed: normal service needs to be destroyed even when task finished");
        logSleepThread(700); //time in milliSeconds
        Log.i("flow", "onDestroy: but this is an Intent Service so it destroyed itself,after task was finished");
        logSleepThread(700); //time in milliSeconds
        Log.i("flow", "onDestroy: and if app is close before service finish its job,the process is not killed" +
                ",....Why?");
    }


    private void startRandomNumberGenerator() {
        Log.i("flow", "startRandomNumberGenerator: ");
        logSleepThread(700); //time in milliSeconds
        Log.i("flow", "startRandomNumberGenerator: worker thread ID: "+Thread.currentThread().getId());
        logSleepThread(700); //time in milliSeconds
        Log.i("flow", "startRandomNumberGenerator: only 10 random no will be generated");
        logSleepThread(700); //time in milliSeconds
        Log.i("flow", "startRandomNumberGenerator: after that loop will terminate..");
        logSleepThread(700); //time in milliSeconds
        Log.i("flow", "startRandomNumberGenerator: and task of service will finish so as its IntentService" +
                " it should stop itself ,so on Destroy should be called");
        logSleepThread(700); //time in milliSeconds
        while (mIsRandomGeneratorOn) {
            try {
                Thread.sleep(700);
                if (mIsRandomGeneratorOn) {
                    mRandomNumber = new Random().nextInt(MAX);
                    Log.i("flow", "Thread id: " + Thread.currentThread().getId() + ", Random Number: " + mRandomNumber);

                    Counter++;      //only 10 random numbers will be generated.
                    if(Counter==10)
                        return;

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

   private void logSleepThread(long value)
   {
       try {
           Thread.sleep(value);    //current thread will sleep for "value" time .
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }

}
