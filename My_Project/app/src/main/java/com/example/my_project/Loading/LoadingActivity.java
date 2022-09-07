package com.example.my_project.Loading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.my_project.HW_MainActivity;
import com.example.my_project.R;



public class LoadingActivity extends AppCompatActivity {
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ImageView loading_gif = (ImageView) findViewById(R.id.loading_gif);
        Glide.with(this).load(R.drawable.bubble).centerCrop().into(loading_gif);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    finish();
                }
            }, 3000);

    }
}