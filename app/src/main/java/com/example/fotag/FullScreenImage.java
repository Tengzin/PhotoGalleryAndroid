package com.example.fotag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;


public class FullScreenImage extends AppCompatActivity {

    ImageView myImage;
    String url = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreen);
        final Intent intent = new Intent(this, MainActivity.class);

        url = getIntent().getStringExtra("image_url");
//        url = "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/fox.jpg";
        myImage = findViewById(R.id.myImage);
        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        Picasso.get()
                .load(url)
                .resize(180, 130)
                .centerCrop()
                .into(myImage);
        }
}