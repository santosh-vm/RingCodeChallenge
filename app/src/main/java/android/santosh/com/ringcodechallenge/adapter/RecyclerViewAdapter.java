package android.santosh.com.ringcodechallenge.adapter;

import android.content.Context;
import android.santosh.com.ringcodechallenge.R;
import android.santosh.com.ringcodechallenge.listeners.RecyclerViewClickInterface;
import android.santosh.com.ringcodechallenge.model.RedditPostData;
import android.santosh.com.ringcodechallenge.model.RedditPost;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Santosh on 8/24/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static String TAG = RecyclerViewAdapter.class.getSimpleName();
    private Context context;
    private RecyclerViewClickInterface recyclerViewClickInterface;
    private List<RedditPost> redditPosts = new ArrayList<>();

    public RecyclerViewAdapter(Context context, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.context = context;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_list_item, parent, false);
        return new RecyclerViewItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof RecyclerViewItemViewHolder) {
            RecyclerViewItemViewHolder recyclerViewItemViewHolder = (RecyclerViewItemViewHolder) viewHolder;
            final RedditPostData redditPostData = redditPosts.get(position).getRedditPostData();
            recyclerViewItemViewHolder.titleTextView.setText(redditPostData.getTitle());
            recyclerViewItemViewHolder.numberOfcommentsTextView.setText(String.format(Locale.US, "%s", redditPostData.getNumberofComments()));
            recyclerViewItemViewHolder.timeStampTextView.setText(String.format(Locale.US, "%s", redditPostData.getUtcTimestamp()));

            if (TextUtils.isEmpty(redditPostData.getThumbnailUrl())) {
                recyclerViewItemViewHolder.thumbNailRootView.setVisibility(View.GONE);
                recyclerViewItemViewHolder.thumbNailImageView.setVisibility(View.GONE);
            } else {
                recyclerViewItemViewHolder.thumbNailRootView.setVisibility(View.VISIBLE);
                recyclerViewItemViewHolder.thumbNailImageView.setVisibility(View.VISIBLE);
                Picasso.with(context).load(redditPostData.getThumbnailUrl()).into(recyclerViewItemViewHolder.thumbNailImageView);
                recyclerViewItemViewHolder.thumbNailImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recyclerViewClickInterface.onThumbNailClicked(redditPostData.getThumbnailUrl());
                    }
                });
            }

        }
    }

    @Override
    public int getItemCount() {
        return redditPosts.size();
    }

    public class RecyclerViewItemViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView numberOfcommentsTextView;
        TextView timeStampTextView;
        View thumbNailRootView;
        ImageView thumbNailImageView;

        public RecyclerViewItemViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            numberOfcommentsTextView = itemView.findViewById(R.id.num_comments_text_view);
            thumbNailRootView = itemView.findViewById(R.id.thumbnail_view_holder);
            thumbNailImageView = itemView.findViewById(R.id.thumbnail_imageview);
            timeStampTextView = itemView.findViewById(R.id.timestamp_text_view);
        }
    }

    public void swapReddiPosts(List<RedditPost> redditPostList) {
        this.redditPosts.clear();
        this.redditPosts.addAll(redditPostList);
        notifyDataSetChanged();
    }


    public void add(RedditPost redditPost) {
        if (!redditPosts.contains(redditPost)) {
            redditPosts.add(redditPost);
            notifyItemInserted(redditPosts.size() - 1);
        }
    }

    public void addAll(List<RedditPost> redditPostList) {
        //Log.d(TAG, "addAll Before, redditPosts.size(): " + redditPostList.size() + ", this.redditPosts.size(): " + this.redditPosts.size());
        for (RedditPost redditPost : redditPostList) {
            add(redditPost);
        }
        //Log.d(TAG, "addAll After, this.redditPosts.size(): " + this.redditPosts.size());
    }
}
