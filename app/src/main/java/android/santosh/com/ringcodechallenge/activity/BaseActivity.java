package android.santosh.com.ringcodechallenge.activity;

import android.os.Bundle;
import android.santosh.com.ringcodechallenge.AppAPI;
import android.santosh.com.ringcodechallenge.AppApplication;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Santosh on 8/23/17.
 */

public class BaseActivity extends AppCompatActivity {
    protected AppAPI appAPI;
    protected static String THUMBNAIL_URL_INTENT_KEY = "THUMBNAIL_URL_INTENT_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        appAPI = ((AppApplication)getApplication()).getAppAPI();
    }
}
