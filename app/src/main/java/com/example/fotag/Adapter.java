package com.example.fotag;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

// Adapter from tutorial at https://ledron.github.io/RecyclerView/
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    ArrayList<String> urls;
    Context context;
    int numStars;
    boolean fullScreen = false;
    Model model;
    //constructor
    public Adapter(ArrayList<String> ImgUrl, Context context_, int stars, Model model)
    {
//        this.urls = ImgUrl;
        this.urls = new ArrayList<String>();
        urls.addAll(ImgUrl);
//        System.out.println(urls.size() + " size");
        this.context = context_;
        this.numStars = stars;
        this.model = model;
        for(int i = urls.size()-1; i >= 0; i--) {
            if (model.getRating(urls.get(i)) != numStars) {
                urls.remove(i);
            }
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;
        RatingBar ratingBar;
        ImageModel imgModel;

        public ViewHolder(View v)
        {
            super(v);
            image =(ImageView)v.findViewById(R.id.img);
            ratingBar = (RatingBar)v.findViewById(R.id.ratingBar);
        }

        public ImageView getImage(){ return this.image;}
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.img_item, parent, false);
        v.setLayoutParams(new RecyclerView.LayoutParams(1080,800));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        int currRating = model.getRating(urls.get(position));
        if (currRating == numStars) {
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FullScreenImage.class);
                    intent.putExtra("image_url", urls.get(position));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
            holder.ratingBar.setRating((int)currRating);
            holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    model.setRating(urls.get(position), (int)v);
                    System.out.println(v);
                }
            });
            Picasso.get()
                    .load(urls.get(position))
                    .resize(180, 130)
                    .centerCrop()
                    .into(holder.image);
        }
    }

    @Override
    public int getItemCount()
    {
        return urls.size();
    }

}
