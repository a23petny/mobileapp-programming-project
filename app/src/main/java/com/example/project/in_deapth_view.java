package com.example.project;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class in_deapth_view extends AppCompatActivity   {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.in_deapth_view);

            Bundle extras = getIntent().getExtras();
            String texty = null;
            int number = 0;
            if (extras != null) {
                texty = extras.getString("text");
                number = extras.getInt("number");
                // Do something with the name and number
            }

            TextView textView = findViewById(R.id.title);
            TextView textView1 = findViewById(R.id.price);
            textView.setText(texty);
            textView1.setText("" + number);
        }

}
