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

        Log.i("flow", "onCreate: Thread Id of this Activity :"+Thread.currentThread().getId());
        intent=new Intent(this,ServiceTest.class);
    }

    public void startServiceButton(View view) {
        Log.i("flow", "startServiceButton: ");
        startService(intent);
    }

    public void stopServiceButton(View view) {
        Log.i("flow", "stopServiceButton: ");
        stopService(intent);
    }

}
