package android.santosh.com.ringcodechallenge;

import android.santosh.com.ringcodechallenge.controller.MainController;

/**
 * Created by Santosh on 8/24/17.
 */

public class AppAPI {
    private MainController mainController;

    public AppAPI(MainController mainController) {
        this.mainController = mainController;
    }

    public MainController getMainController() {
        return mainController;
    }
}
