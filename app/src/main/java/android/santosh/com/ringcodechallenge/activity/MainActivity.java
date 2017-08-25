package android.santosh.com.ringcodechallenge.activity;

import android.santosh.com.ringcodechallenge.R;
import android.os.Bundle;
import android.santosh.com.ringcodechallenge.adapter.RecyclerViewAdapter;
import android.santosh.com.ringcodechallenge.listeners.MainControllerListener;
import android.santosh.com.ringcodechallenge.model.RedditPost;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends BaseActivity implements MainControllerListener {
    private static String TAG = MainActivity.class.getSimpleName();

    private ProgressBar progressBar;
    private TextView errorMessageTextView;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appAPI.getMainController().addMainControllerListener(this);
        bindUIElements();
    }

    private void bindUIElements() {
        //Progress Bar
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        //TextView
        errorMessageTextView = (TextView) findViewById(R.id.error_message_text_view);
        errorMessageTextView.setVisibility(View.GONE);

        //RecyclerView
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager recyclerViewLinearLayoutManger = new LinearLayoutManager(this);
        recyclerViewLinearLayoutManger.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recyclerViewLinearLayoutManger);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (recyclerViewAdapter.getItemCount() <= 0) {
            Log.d(TAG,"recyclerViewAdapter.getItemCount() is less than zero.");
            appAPI.getMainController().fetchPost();
        } else {
            Log.d(TAG,"recyclerViewAdapter from memory.");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appAPI.getMainController().removeMainControllerListener(this);
    }

    @Override
    public void onRedditPostListFetchSuccess(List<RedditPost> redditPostList) {
        Log.d(TAG, "onRedditPostListFetchSuccess redditPostList.size(): " + redditPostList.size());
        progressBar.setVisibility(View.GONE);
        errorMessageTextView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerViewAdapter.addAll(redditPostList);
    }

    @Override
    public void onRedditPostListFetchFailure() {
        Log.d(TAG, "onRedditPostListFetchFailure()");
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        if (recyclerViewAdapter.getItemCount() <= 0) {
            errorMessageTextView.setVisibility(View.VISIBLE);
        } else {
            errorMessageTextView.setVisibility(View.GONE);
        }
    }
}
