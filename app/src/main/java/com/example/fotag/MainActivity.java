package com.example.fotag;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {
    Model model;
    ImageButton loadButton;
    ImageButton clearButton;

    ArrayList<String> ImgUrl= new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager Manager;
    Adapter adapter;
    MainActivity mainAct = this;
    RatingBar mainRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = Model.getInstance();
        model.addObserver(this);

        this.recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        Manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(Manager);
        adapter = new Adapter(ImgUrl, this, 0, model);
        recyclerView.setAdapter(adapter);

        loadButton = (ImageButton) findViewById(R.id.loadButton);
        loadButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ImgUrl.clear();
                ImgUrl.add("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/fox.jpg");
                ImgUrl.add("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/doggo.jpg");
                ImgUrl.add("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/bunny.jpg");
                ImgUrl.add("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/chinchilla.jpg");
                ImgUrl.add("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/hamster.jpg");
                ImgUrl.add("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/husky.jpg");
                ImgUrl.add("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/kitten.png");
                ImgUrl.add("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/puppy.jpg");
                ImgUrl.add("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/sleepy.png");
                ImgUrl.add("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/loris.jpg");
                model.clearRatings();
                model.setRatingZero(ImgUrl);
                adapter = new Adapter(ImgUrl, mainAct, 0, model);
                recyclerView.setAdapter(adapter);
                mainRating.setRating(0);
            }
        });

        clearButton = (ImageButton) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ImgUrl.clear();
                adapter = new Adapter(ImgUrl, mainAct, 0, model);
                recyclerView.setAdapter(adapter);
                model.clearRatings();
                mainRating.setRating(0);
            }
        });

        mainRating = (RatingBar) findViewById(R.id.mainRating);
        mainRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                adapter = new Adapter(ImgUrl, mainAct, (int)v, model);
                recyclerView.setAdapter(adapter);
            }
        });

        model.initObservers();
    }

    @Override
    public void update(Observable o, Object arg)
    {
        // Update button with click count from model
//        mIncrementButton.setText(String.valueOf(mModel.getCounter()));
    }
}
