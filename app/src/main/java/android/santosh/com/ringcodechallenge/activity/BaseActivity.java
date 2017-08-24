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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appAPI = ((AppApplication)getApplication()).getAppAPI();
    }
}
