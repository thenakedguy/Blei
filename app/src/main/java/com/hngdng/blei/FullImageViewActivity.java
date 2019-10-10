package com.hngdng.blei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hngdng.blei.Model.Photo;

public class FullImageViewActivity extends AppCompatActivity {
    private Photo photo;
    private ImageView mImageView2;

    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_view);
        int pos;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
            } else {
//                String s = extras.get("URL").toString();
//                Toast.makeText(this,""+ s,Toast.LENGTH_LONG).show();
                mImageView2 = (ImageView) findViewById(R.id.imageView2);
            }
        } else {
        }
    }
}
