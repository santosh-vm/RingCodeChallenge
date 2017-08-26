package android.santosh.com.ringcodechallenge.activity;

import android.content.Intent;
import android.os.Parcelable;
import android.santosh.com.ringcodechallenge.R;
import android.os.Bundle;
import android.santosh.com.ringcodechallenge.adapter.RecyclerViewAdapter;
import android.santosh.com.ringcodechallenge.listeners.MainControllerListener;
import android.santosh.com.ringcodechallenge.listeners.RecyclerViewClickInterface;
import android.santosh.com.ringcodechallenge.model.RedditPost;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends BaseActivity implements MainControllerListener, RecyclerViewClickInterface {
    private static String TAG = MainActivity.class.getSimpleName();
    private static String LIST_STATE_KEY = "LIST_STATE_KEY";

    private ProgressBar progressBar;
    private TextView errorMessageTextView;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private LinearLayoutManager recyclerViewLinearLayoutManger;
    private Parcelable recyclerViewListSate;

    private boolean isLoading = false;
    int previousVisibleItems, visibleItemCount, totalItemCount;
    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (dy > 0) {
                visibleItemCount = recyclerViewLinearLayoutManger.getChildCount();
                totalItemCount = recyclerViewLinearLayoutManger.getItemCount();
                previousVisibleItems = recyclerViewLinearLayoutManger.findFirstVisibleItemPosition();
                //Log.d(TAG, "onScrolled totalItemCount: " + totalItemCount);
                if (!isLoading && ((visibleItemCount + previousVisibleItems) >= totalItemCount) && totalItemCount < 50) {
                    //Log.d(TAG, "Let's Fetch more posts");
                    isLoading = true;
                    appAPI.getMainController().fetchPost();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Main Activity");
        }
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
        recyclerViewAdapter = new RecyclerViewAdapter(this, this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerViewLinearLayoutManger = new LinearLayoutManager(this);
        recyclerViewLinearLayoutManger.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recyclerViewLinearLayoutManger);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        List<RedditPost> redditPosts = appAPI.getMainController().getRedditPosts();
        //Log.d(TAG,"onCreate redditPosts.size: "+redditPosts.size());
        if (redditPosts.size() > 0) {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerViewAdapter.swapReddiPosts(redditPosts);
        } else {
            recyclerView.setVisibility(View.GONE);
            appAPI.getMainController().fetchPost();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appAPI.getMainController().removeMainControllerListener(this);
    }

    @Override
    public void onRedditPostListFetchSuccess(List<RedditPost> redditPostList) {
        //Log.d(TAG, "onRedditPostListFetchSuccess redditPostList.size(): " + redditPostList.size());
        progressBar.setVisibility(View.GONE);
        errorMessageTextView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerViewAdapter.addAll(redditPostList);
        isLoading = false;
    }

    @Override
    public void onRedditPostListFetchFailure() {
        //Log.d(TAG, "onRedditPostListFetchFailure()");
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        if (recyclerViewAdapter.getItemCount() <= 0) {
            errorMessageTextView.setVisibility(View.VISIBLE);
        } else {
            errorMessageTextView.setVisibility(View.GONE);
        }
        isLoading = false;
    }

    @Override
    public void onThumbNailClicked(String thumbNailUrl) {
        Intent intent = new Intent(this,ThumbNailDetailsActivity.class);
        intent.putExtra(THUMBNAIL_URL_INTENT_KEY, thumbNailUrl);
        startActivity(intent);
    }
}
