package com.example.project;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.annotations.SerializedName;

public class in_deapth_view extends AppCompatActivity   {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.in_deapth_view);

            Bundle extras = getIntent().getExtras();
            String title = null;
            String image = null;
            String emoji = null;

            String location = null;
            String ingredients = null;
            String desc = null;

            int eta = 0;
            int price = 0;

            if (extras != null) {
                title = extras.getString("title");
                image = extras.getString("image");
                emoji = extras.getString("emoji");

                location = extras.getString("location");
                ingredients = extras.getString("ingredients");
                desc = extras.getString("desc");

                eta = extras.getInt("eta");
                price = extras.getInt("price");
            }

            TextView titleView = findViewById(R.id.title);
            ImageView imageView = findViewById(R.id.image);
            TextView emojiView = findViewById(R.id.emoji);

            TextView descView = findViewById(R.id.desc);

            TextView etaLocView = findViewById(R.id.eta_locl);

            TextView priceView = findViewById(R.id.price);

            Log.d("asdadasd","sadasd");
            titleView.setText(title);
            //textView1.setText("" + price);*/



        }

}
