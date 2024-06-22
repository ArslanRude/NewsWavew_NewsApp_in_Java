package com.example.newsapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.kwabenaberko.newsapilib.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsRecyclerAdaptor extends RecyclerView.Adapter<NewsRecyclerAdaptor.NewsViewHolder>{
    List<Article> articalList;
    NewsRecyclerAdaptor(List<Article> artistsList){
        this.articalList = artistsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_row,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = articalList.get(position);
        holder.titleTextView.setText(article.getTitle());
        holder.sourceTextView.setText(article.getSource().getName());
        Picasso.get().load(article.getUrlToImage())
                .error(R.drawable.no_image_icon)
                .into(holder.imageView);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(),NewsFullActivity.class);
            intent.putExtra("url",article.getUrl());
            v.getContext().startActivity(intent);
        });
    }

    void updataData(List<Article> data){
        articalList.clear();
        articalList.addAll(data);
    }

    @Override
    public int getItemCount() {
        return articalList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView,sourceTextView;
        ImageView imageView;


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.article_title);
            sourceTextView = itemView.findViewById(R.id.article_source);
            imageView = itemView.findViewById(R.id.article_image_view);

        }
    }
}
