package easyapps.com.servicesbehaviour;

import android.app.Application;

/**
 * Created by Asim on 11/13/2017.
 */

public class AppClass extends Application {

    private static int status=0;

    public static int getStatus() {
        return status;
    }

    public static void incrementStatus()
    {
        status++;
    }
}
