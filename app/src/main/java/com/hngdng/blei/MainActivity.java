package com.hngdng.blei;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.hngdng.blei.Model.Faves;
import com.hngdng.blei.Model.Photo;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private RecyclerView mLv1;
    private List<Photo> photoList;
    private Adapter adapter;
    private int page;
    private SwipeRefreshLayout swipeLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photoList = new ArrayList<>();
        mLv1= (RecyclerView) findViewById(R.id.lv1);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,1);
        adapter = new Adapter(this,photoList);
        mLv1.setAdapter(adapter);
        mLv1.setLayoutManager(staggeredGridLayoutManager);
        loadPhoto(page);
        swipeLayout = findViewById(R.id.swipeLayout);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MainActivity.this.page = 1;
//                photoList.clear();
                loadPhoto(MainActivity.this.page);
            }
        });

        mLv1.addOnScrollListener(new EndlessRecyclerViewScrollListener(staggeredGridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                MainActivity.this.page++;
                loadPhoto(MainActivity.this.page++);

            }
        });



    }
    public void loadPhoto(int page){
        AndroidNetworking.post("https://www.flickr.com/services/rest/")
                .addBodyParameter("method", "flickr.favorites.getList")
                .addBodyParameter("api_key", "9c99fae4da3b6a8c1dfc47faa558b8de")
                .addBodyParameter("user_id", "136160119@N04")
                .addBodyParameter("format", "json")
                .addBodyParameter("nojsoncallback", "1")
                .addBodyParameter("extras", "views,media,path_alias,url_sq,url_t,url_s,url_q,url_m,url_n,url_z,url_c,url_l,url_o")
                .addBodyParameter("per_page", "10")
                .addBodyParameter("page", String.valueOf(page))
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(Faves.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        swipeLayout.setRefreshing(false);
                        Faves faves = (Faves) response;
                        List<Photo> photoList = faves.getPhotos().getPhoto();
                        MainActivity.this.photoList.addAll(photoList);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

}
