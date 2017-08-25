package android.santosh.com.ringcodechallenge.model;

import android.santosh.com.ringcodechallenge.RedditPostData;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Santosh on 8/24/17.
 */

public class RedditPost {
    @SerializedName("data")
    RedditPostData redditPostData;

    public RedditPostData getRedditPostData() {
        return redditPostData;
    }
}
