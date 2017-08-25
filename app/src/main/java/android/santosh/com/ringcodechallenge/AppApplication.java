package android.santosh.com.ringcodechallenge;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.os.Handler;
import android.santosh.com.ringcodechallenge.controller.MainController;
import android.util.Log;

/**
 * Created by Santosh on 8/24/17.
 */

public class AppApplication extends Application {
    private static String TAG = AppApplication.class.getSimpleName();
    private AppAPI appAPI;

    @Override
    public void onCreate() {
        super.onCreate();
        MainController mainController = new MainController(new Handler());
        appAPI = new AppAPI(mainController);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        //Log.d(TAG, "onTrimMemory, level: " + level);
        if(level == ComponentCallbacks2.TRIM_MEMORY_COMPLETE
                || level == ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL){
            appAPI.getMainController().resetData();
        }

    }

    public AppAPI getAppAPI() {
        return appAPI;
    }
}
