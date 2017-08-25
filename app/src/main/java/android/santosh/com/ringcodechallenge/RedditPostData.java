package android.santosh.com.ringcodechallenge;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Santosh on 8/24/17.
 */

public class RedditPostData {
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
