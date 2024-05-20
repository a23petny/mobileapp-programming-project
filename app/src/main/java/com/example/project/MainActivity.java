package com.example.project;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=a23petny";

    private ArrayList<Snack> snacks;
    private RecyclerViewAdapter adapter;
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        List<Snack> snacks = new ArrayList<>();

        snacks.add(new Snack("Apple Slices", "🍎", "Healthy", 3, 5, "Cafeteria", "Fresh apple slices, a perfect healthy snack."));
        snacks.add(new Snack("Chocolate Chip Cookie", "🍪", "Indulgent", 2, 3, "Bakery", "A classic treat, crisp on the outside, chewy on the inside."));
        snacks.add(new Snack("Granola Bar", "🍫", "Healthy", 4, 2, "Vending Machine", "A crunchy granola bar with nuts and honey."));
        snacks.add(new Snack("Yogurt Parfait", "🍨", "Healthy", 5, 6, "Cafeteria", "Layers of creamy yogurt, granola, and fresh berries."));
        snacks.add(new Snack("Potato Chips", "🥔", "Indulgent", 2, 1, "Vending Machine", "Crispy and salty potato chips, a perfect snack for a break."));
        snacks.add(new Snack("Smoothie", "🥤", "Healthy", 6, 7, "Juice Bar", "A refreshing blend of fruits and yogurt, perfect for a hot day."));
        snacks.add(new Snack("Candy Bar", "🍫", "Indulgent", 1, 2, "Vending Machine", "A sweet and satisfying candy bar to keep you going."));
        snacks.add(new Snack("Trail Mix", "🥜", "Healthy", 4, 3, "Grocery Store", "A mix of nuts, dried fruit, and chocolate chips."));
        snacks.add(new Snack("Cheese Sticks", "🧀", "Moderate", 3, 4, "Deli", "String cheese sticks, a good source of protein and calcium."));
        snacks.add(new Snack("Pretzels", "🥨", "Moderate", 2, 2, "Vending Machine", "Crunchy and salty pretzels, great for snacking anytime."));


        adapter = new RecyclerViewAdapter(this, snacks, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(Snack snack) {
                Log.d("ASDA",""+snack.getTitle());
                Intent intent = new Intent(MainActivity.this, in_deapth_view.class);
                intent.putExtra("title", snack.getTitle());
                intent.putExtra("emoji", snack.getEmoji());
                intent.putExtra("location", snack.getLocation());
                intent.putExtra("desc", snack.getDesc());
                intent.putExtra("eta", snack.getEta());
                intent.putExtra("price", snack.getPrice());
                startActivity(intent);
            }
        });

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);





        new JsonTask(this).execute(JSON_URL);

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.about_screen) { //target_audience
            Log.d("==>","about_screen");

            Intent intent = new Intent(MainActivity.this, target_audience.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.healthy_snacks) {
            Log.d("==>","healthy_snacks");

            adapter.notifyDataSetChanged();
            return true;

        }

        if (id == R.id.all_snacks) {
            Log.d("==>","all_snacks");

            adapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", "" + json);
        Gson gson = new Gson();
        // Unmarshall JSON -> list of objects
        Type type = new TypeToken<List<Snack>>() {
        }.getType();
        String tempString = ""+gson.fromJson(json, type);
        Log.d("==>",tempString);

        //here filter gson.fromJson(json, type); bettween healthy and unhealthy

        snacks = gson.fromJson(json, type);
    }


}