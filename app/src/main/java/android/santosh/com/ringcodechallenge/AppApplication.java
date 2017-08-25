package android.santosh.com.ringcodechallenge;

import android.app.Application;
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
        //Log.d(TAG,"onTrimMemory");
        //appAPI.getMainController().resetData();
    }

    public AppAPI getAppAPI() {
        return appAPI;
    }
}
