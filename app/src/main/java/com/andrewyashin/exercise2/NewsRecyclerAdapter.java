package com.andrewyashin.exercise2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {

    private final List<NewsItem> news;
    private final Context context;
    private final LayoutInflater inflater;
    private final RequestManager imageLoader;
    private final OnItemClickListener clickListener;

    public NewsRecyclerAdapter(@NonNull Context context, @NonNull List<NewsItem> news, OnItemClickListener clickListener) {
        this.context = context;
        this.news = news;
        inflater = LayoutInflater.from(context);
        this.imageLoader = Glide.with(context);
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(inflater.inflate(R.layout.newsitem, viewGroup, false), clickListener);
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

        public ViewHolder(@NonNull View newsView, @NonNull final OnItemClickListener listener) {
            super(newsView);
            newsView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION) {
                        listener.OnItemClick(position);
                    }
                }
            });
            categoryView = newsView.findViewById(R.id.category);
            titleView = newsView.findViewById(R.id.title);
            previewtextView = newsView.findViewById(R.id.preview);
            dateView = newsView.findViewById(R.id.date);
            imageView = newsView.findViewById(R.id.headingimage);
        }

        void bind(NewsItem newsItem){
            titleView.setText(newsItem.getTitle());
            categoryView.setText(newsItem.getCategory().getName());
            previewtextView.setText(newsItem.getPreviewText());
            dateView.setText(new SimpleDateFormat("MMM d, h:mm a", Locale.ENGLISH).format(newsItem.getPublishDate()));
            imageLoader.load(newsItem.getImageUrl()).into(imageView);
        }
    }


}
