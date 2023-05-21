package com.example.javaandroidproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.javaandroidproject.application.HomeApplication;
import com.example.javaandroidproject.catalog.CatalogActivity;
import com.example.javaandroidproject.contants.Urls;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgLogo = (ImageView)findViewById(R.id.imgLogo);
        String url = Urls.BASE+"/uploads/1.jpg";
        Glide.with(HomeApplication.getAppContext())
                .load(url)
                .apply(new RequestOptions().override(600))
                .into(imgLogo);

    }

    public void onClickBtn(View view) {
        Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
        startActivity(intent);
    }


}