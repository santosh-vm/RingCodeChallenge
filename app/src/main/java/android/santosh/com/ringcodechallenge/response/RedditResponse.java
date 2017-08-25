package android.santosh.com.ringcodechallenge.response;

import android.santosh.com.ringcodechallenge.model.RedditPost;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Santosh on 8/24/17.
 */

public class RedditResponse {
    @SerializedName("data")
    private MainData mainData;

    public MainData getMainData() {
        return mainData;
    }

    public static class MainData {
        @SerializedName("children")
        RedditPost[] redditPosts;

        @SerializedName("after")
        String after;

        @SerializedName("before")
        String before;

        public RedditPost[] getRedditPosts() {
            return redditPosts;
        }

        public String getAfter() {
            return after;
        }

        public String getBefore() {
            return before;
        }
    }
}
