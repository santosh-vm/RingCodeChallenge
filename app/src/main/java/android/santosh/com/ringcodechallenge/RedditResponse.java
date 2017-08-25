package android.santosh.com.ringcodechallenge;

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
        Children[] childrens;

        @SerializedName("after")
        String after;

        @SerializedName("before")
        String before;

        public Children[] getChildrens() {
            return childrens;
        }

        public String getAfter() {
            return after;
        }

        public String getBefore() {
            return before;
        }
    }


    public static class Children {
        @SerializedName("data")
        RedditPostData redditPostData;

        public RedditPostData getRedditPostData() {
            return redditPostData;
        }
    }
}
