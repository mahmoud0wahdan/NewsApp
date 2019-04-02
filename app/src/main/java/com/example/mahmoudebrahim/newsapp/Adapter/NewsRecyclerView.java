package com.example.mahmoudebrahim.newsapp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mahmoudebrahim.Api.Model.NewsSourceItemResponse.ArticlesItem;
import com.example.mahmoudebrahim.newsapp.R;

import java.util.List;

public class NewsRecyclerView extends RecyclerView.Adapter<NewsRecyclerView.Viewholder> {

    List<ArticlesItem> articlesItems;

    public NewsRecyclerView(List<ArticlesItem> articlesItems) {
        this.articlesItems = articlesItems;
    }

    public void changeData(List<ArticlesItem> articlesItems){
        this.articlesItems = articlesItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view=LayoutInflater.from(viewGroup.getContext())
               .inflate(R.layout.news_tem,viewGroup,false);

        return new Viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {

        ArticlesItem articlesItem=articlesItems.get(i);
        viewholder.sourceName.setText(articlesItem.getSource().getName());
        viewholder.title.setText(articlesItem.getTitle());
        viewholder.date.setText(articlesItem.getPublishedAt());
        Glide.with(viewholder.itemView)
                .load(articlesItem.getUrlToImage()).into(viewholder.newsImage);
    }

    @Override
    public int getItemCount() {
        if(articlesItems==null)
            return 0;
        return articlesItems.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        TextView title,date,sourceName;
        ImageView newsImage;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            sourceName=itemView.findViewById(R.id.source_name);
            date=itemView.findViewById(R.id.date);
            newsImage=itemView.findViewById(R.id.news_image);
        }
    }
}
