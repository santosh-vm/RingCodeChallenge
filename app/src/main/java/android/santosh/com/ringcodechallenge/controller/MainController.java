package android.santosh.com.ringcodechallenge.controller;

import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;

/**
 * Created by Santosh on 8/24/17.
 */

public class MainController {
    private static String TAG = MainController.class.getSimpleName();

    private Handler uiHandler;
    private ExecutorService executorService;
    private OkHttpClient client;
    private Gson gson;

    public MainController(Handler uiHandler){
        this.executorService = Executors.newSingleThreadExecutor();
        this.uiHandler = uiHandler;
        this.client = new OkHttpClient();
        this.gson = new GsonBuilder().create();
    }
}
