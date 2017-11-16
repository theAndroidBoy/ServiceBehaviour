package easyapps.com.servicesbehaviour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("flow", "onCreate: UI thread id :"+Thread.currentThread().getId());
        intent=new Intent(this,IntentServiceTest.class);
    }

    public void startServiceButton(View view) {
        Log.i("flow", "startServiceButton: start service button clicked");
        startService(intent);
    }

    public void stopServiceButton(View view) {
        Log.i("flow", "stopServiceButton: stop service button clicked");
        stopService(intent);
    }

}
