package com.andrewyashin.exercise2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class NewsDetailsActivity extends Activity {

    public static void start(Activity NewListActivity, int  position) {
        Intent intent = new Intent(NewListActivity, NewsDetailsActivity.class);
        intent.putExtra("position", position);
        NewListActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        final ImageView newsImage = findViewById(R.id.imageNewsDetails);
        final TextView titleText = findViewById(R.id.titleNewsDetails);
        final TextView date = findViewById(R.id.dateNewsDetails);
        final TextView fullText = findViewById(R.id.fulltextNewsDetails);

        int position = getIntent().getIntExtra("position",0 );

        NewsItem newsItem =  DataUtils.generateNews().get(position);

        setTitle(newsItem.getCategory().getName());
        Glide.with(this).load(newsItem.getImageUrl()).into(newsImage);
        titleText.setText(newsItem.getTitle());
        date.setText(new SimpleDateFormat("MMM d, h:mm a", Locale.ENGLISH).format(newsItem.getPublishDate()));
        fullText.setText(newsItem.getFullText());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
