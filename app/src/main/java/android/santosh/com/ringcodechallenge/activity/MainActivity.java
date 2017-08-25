package android.santosh.com.ringcodechallenge.activity;

import android.santosh.com.ringcodechallenge.R;
import android.os.Bundle;
import android.santosh.com.ringcodechallenge.adapter.RecyclerViewAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends BaseActivity {
    private static String TAG = MainActivity.class.getSimpleName();

    private ProgressBar progressBar;
    private TextView errorMessageTextView;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUIElements();
        appAPI.getMainController().fetchPost();
    }

    private void bindUIElements() {
        //Progress Bar
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        //TextView
        errorMessageTextView = (TextView) findViewById(R.id.error_message_text_view);
        errorMessageTextView.setVisibility(View.GONE);

        //RecyclerView
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager recyclerViewLinearLayoutManger = new LinearLayoutManager(this);
        recyclerViewLinearLayoutManger.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recyclerViewLinearLayoutManger);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setVisibility(View.GONE);
    }
}
