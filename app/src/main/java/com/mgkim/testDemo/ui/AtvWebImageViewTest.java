package com.mgkim.testDemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.mgkim.testDemo.R;
import com.mgkim.testDemo.net.widget.CacheWebImageView;
import com.mgkim.testDemo.net.widget.WebImageView;

public class AtvWebImageViewTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atv_web_image_view_test);

//        WebImageView iv = findViewById(R.id.wiv_test01);
//        iv.setUrl("https://cdn.pixabay.com/photo/2019/09/28/20/21/trees-4511721_960_720.jpg");
        CacheWebImageView iv2 = findViewById(R.id.wiv_test02);
        iv2.setUrl("https://cdn.pixabay.com/photo/2019/09/28/20/21/trees-4511721_960_720.jpg");
    }
}
