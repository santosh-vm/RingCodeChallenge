package android.santosh.com.ringcodechallenge.listeners;

import android.santosh.com.ringcodechallenge.model.RedditPost;

import java.util.List;

/**
 * Created by Santosh on 8/24/17.
 */

public interface MainControllerListener {
    void onRedditPostListFetchSuccess(List<RedditPost> redditPostList);

    void onRedditPostListFetchFailure();
}
