package android.santosh.com.ringcodechallenge.adapter;

import android.content.Context;
import android.santosh.com.ringcodechallenge.R;
import android.santosh.com.ringcodechallenge.model.RedditPostData;
import android.santosh.com.ringcodechallenge.model.RedditPost;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Santosh on 8/24/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static String TAG = RecyclerViewAdapter.class.getSimpleName();
    private Context context;
    private List<RedditPost> redditPostList = new ArrayList<>();

    public RecyclerViewAdapter(Context context) {
        this.context = context;
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
            RedditPostData redditPostData = redditPostList.get(position).getRedditPostData();
            recyclerViewItemViewHolder.titleTextView.setText(redditPostData.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return redditPostList.size();
    }

    public class RecyclerViewItemViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;

        public RecyclerViewItemViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);

        }
    }


    public void add(RedditPost redditPost) {
        redditPostList.add(redditPost);
        notifyItemInserted(redditPostList.size() - 1);
    }

    public void addAll(List<RedditPost> redditPostList) {
        for (RedditPost redditPost : redditPostList) {
            add(redditPost);
        }
    }
}
