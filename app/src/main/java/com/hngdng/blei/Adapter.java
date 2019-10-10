package com.hngdng.blei;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.interfaces.ParsedRequestListener;
import com.bumptech.glide.Glide;
import com.hngdng.blei.Model.Faves;
import com.hngdng.blei.Model.Photo;
import com.hngdng.blei.PhotoHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Adapter extends RecyclerView.Adapter<PhotoHolder>{
    public Context context;
    public List<Photo> photoList;

    public Adapter(Context context,List<Photo> photoList){
        this.context = context;
        this.photoList = photoList;
    }
    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,
                parent, false);
        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        Photo photo = photoList.get(position);
        holder.imgPhoto.getLayoutParams().height = getRandomIntInRange(300,200);
        Glide.with(context).load(photo.getUrlS()).into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }
    protected int getRandomIntInRange(int max, int min){
        Random mRandom = new Random();
        return mRandom.nextInt((max-min)+min)+min;
    }
}
//    interface AdapterListener{
//        public void itemOnClick(int pos)
//    }
class PhotoHolder extends RecyclerView.ViewHolder {
    public ImageView imgPhoto;
    private Photo photo;
    private List<Photo> photoList;
    public PhotoHolder(@NonNull final View itemView) {

        super(itemView);
        imgPhoto = itemView.findViewById(R.id.imageView);
        itemView.setClickable(true);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                photo = photoList.get(position);
                String URL = photo.getUrlO();
                Intent intent = new Intent(view.getContext(),FullImageViewActivity.class);
                intent.putExtra("URL", URL);
                view.getContext().startActivity(intent);
            }
        });

    }
}




