package android.santosh.com.ringcodechallenge.activity;

import android.os.Bundle;
import android.santosh.com.ringcodechallenge.R;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Santosh on 8/25/17.
 */

public class ThumbNailDetailsActivity extends BaseActivity {
    private static String TAG = ThumbNailDetailsActivity.class.getSimpleName();
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thumbnail_details);
        imageView = (ImageView) findViewById(R.id.thumbnail_imageview);
        String url = getIntent().getStringExtra(THUMBNAIL_URL_INTENT_KEY);
        Picasso.with(this)
                .load(url)
                .resize(500,500)
                .into(imageView);
    }
}
