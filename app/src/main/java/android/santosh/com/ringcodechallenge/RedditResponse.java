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
        ChildData childData;

        public ChildData getChildData() {
            return childData;
        }
    }

    public static class ChildData {
        @SerializedName("title")
        String title;
        @SerializedName("author")
        String author;
        @SerializedName("created_utc")
        long utcTimestamp;
        @SerializedName("thumbnail")
        String thumbnailUrl;
        @SerializedName("num_comments")
        int numberofComments;

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public long getUtcTimestamp() {
            return utcTimestamp;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public int getNumberofComments() {
            return numberofComments;
        }
    }
}
