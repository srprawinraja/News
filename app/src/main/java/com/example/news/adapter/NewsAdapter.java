package com.example.news.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.news.ArticleActivity;
import com.example.news.R;
import com.example.news.data.Article;

import java.util.List;
import java.time.ZonedDateTime;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    String image;
    private List<Article> items;
    private Context context;
    public NewsAdapter(List<Article> items, Context context) {
        this.items = items;
        this.context = context;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView date, category, title, description;

        public NewsViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.cardImage);
            date = itemView.findViewById(R.id.cardDate);
            category = itemView.findViewById(R.id.cardCategory);
            title = itemView.findViewById(R.id.cardTitle);
            description = itemView.findViewById(R.id.cardDescription);
        }
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);

        return new NewsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article item = items.get(position);
        String imageUrl = item.getUrlToImage();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholdercard)
                    .error(R.drawable.img)
                    .into(holder.image);
        } else {
            holder.image.setImageResource(R.drawable.img);
        }
        String title = item.getTitle();
        String content = item.getContent();
        String category = item.getSource().getName();
        String dateTime = item.getPublishedAt();
        String imgUrl = item.getUrlToImage();
        String description = item.getDescription();
        ZonedDateTime zdt = ZonedDateTime.parse(dateTime);
        String modifiedDate  = zdt.toLocalDate().toString();

        holder.date.setText(modifiedDate);
        holder.category.setText(category);
        holder.title.setText(title);
        holder.description.setText(description);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ArticleActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("content", content);
            intent.putExtra("img", imgUrl);
            intent.putExtra("category", category);
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return items.size();
    }
    public void updateData(List<Article> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


}
