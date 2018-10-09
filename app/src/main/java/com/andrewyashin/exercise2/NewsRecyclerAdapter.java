package com.andrewyashin.exercise2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {

    private final List<NewsItem> news;
    private final Context context;
    private final LayoutInflater inflater;
    private final RequestManager imageLoader;

    public NewsRecyclerAdapter(@NonNull Context context,@NonNull List<NewsItem> news) {
        this.context = context;
        this.news = news;
        inflater = LayoutInflater.from(context);
        this.imageLoader = Glide.with(context);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(inflater.inflate(R.layout.activity_new_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(news.get(position));

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView categoryView;
        public final TextView titleView;
        public final TextView previewtextView;
        public final TextView dateView;
        public final ImageView imageView;

        public ViewHolder(@NonNull View newsView) {
            super(newsView);
            categoryView = (TextView) newsView.findViewById(R.id.category);
            titleView = newsView.findViewById(R.id.title);
            previewtextView = newsView.findViewById(R.id.preview);
            dateView = newsView.findViewById(R.id.date);
            imageView = newsView.findViewById(R.id.headingimage);
        }

        void bind(NewsItem newsItem){
            titleView.setText(newsItem.getTitle());
            categoryView.setText(newsItem.getCategory().getName());
            previewtextView.setText(newsItem.getPreviewText());
            dateView.setText(newsItem.getPublishDate().toString());
            imageLoader.load(newsItem.getImageUrl()).into(imageView);
        }
    }


}
