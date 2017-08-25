package android.santosh.com.ringcodechallenge.controller;

import android.os.Handler;
import android.santosh.com.ringcodechallenge.model.RedditPostData;
import android.santosh.com.ringcodechallenge.response.RedditResponse;
import android.santosh.com.ringcodechallenge.listeners.MainControllerListener;
import android.santosh.com.ringcodechallenge.model.RedditPost;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Santosh on 8/24/17.
 */

public class MainController {
    private static String TAG = MainController.class.getSimpleName();
    private static String REDDIT_TOP_POST_LIST_URL = "https://www.reddit.com/top/.json?count=10&limit=10&after=%1s";

    private Handler uiHandler;
    private ExecutorService executorService;
    private OkHttpClient client;
    private Gson gson;
    private List<MainControllerListener> mainControllerListeners = Collections.synchronizedList(new ArrayList<MainControllerListener>());

    private String currentBefore = null;
    private String currentAfter = null;

    public MainController(Handler uiHandler) {
        this.executorService = Executors.newSingleThreadExecutor();
        this.uiHandler = uiHandler;
        this.client = new OkHttpClient();
        this.gson = new GsonBuilder().create();
    }

    public void fetchPost() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Request request = new Request.Builder()
                                .url(String.format(Locale.US, REDDIT_TOP_POST_LIST_URL, currentAfter))
                                .build();
                        Response response = client.newCall(request).execute();
                        String stringyfiedJson = new String(response.body().bytes(), "UTF-8");
                        switch (response.code()) {
                            case 200:
                                RedditResponse redditResponse = gson.fromJson(stringyfiedJson, RedditResponse.class);
                                Log.d(TAG, "redditResponse redditResponse.getMainData().getRedditPosts().length: " + redditResponse.getMainData().getRedditPosts().length);
                                List<RedditPost> redditPostDataList = Arrays.asList(redditResponse.getMainData().getRedditPosts());
                                break;
                            default:
                                notifyRedditPostListFetchFailure();
                                break;
                        }
                    } catch (IOException iex) {
                        notifyRedditPostListFetchFailure();
                    }
                }
            });
        } else {
            notifyRedditPostListFetchFailure();
        }
    }

    public void resetData() {
        currentBefore = null;
        currentAfter = null;
    }

    public void addMainControllerListener(MainControllerListener mainControllerListener) {
        if (mainControllerListeners != null && !mainControllerListeners.contains(mainControllerListener)) {
            mainControllerListeners.add(mainControllerListener);
        }
    }

    public void removeMainControllerListener(MainControllerListener mainControllerListener) {
        if (mainControllerListeners != null && mainControllerListeners.contains(mainControllerListener)) {
            mainControllerListeners.add(mainControllerListener);
        }
    }

    public void notifyRedditPostListFetchSuccess(final List<RedditPostData> redditPostDataList) {
        if (mainControllerListeners != null && mainControllerListeners.size() > 0) {
            for (final MainControllerListener mainControllerListener : mainControllerListeners) {
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mainControllerListener.onRedditPostListFetchSuccess(redditPostDataList);
                    }
                });
            }
        }
    }

    public void notifyRedditPostListFetchFailure() {
        if (mainControllerListeners != null && mainControllerListeners.size() > 0) {
            for (final MainControllerListener mainControllerListener : mainControllerListeners) {
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mainControllerListener.onRedditPostListFetchFailure();
                    }
                });
            }
        }
    }
}
