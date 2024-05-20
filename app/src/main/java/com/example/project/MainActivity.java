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

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";
    private final String JSON_FILE = "mountains.json";

    private ArrayList<Snack> snacks;
    private RecyclerViewAdapter adapter;
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Snack 1
        Snack snack1 = new Snack(
                "image_url_1.jpg",
                "Popcorn",
                "\uD83C\uDF7F",
                "Moderate",
                2,
                5,
                "Movie Theater",
                "Popcorn kernels, butter, salt",
                "Classic movie snack, perfect for a night out at the cinema."
        );

// Snack 2
        Snack snack2 = new Snack(
                "image_url_2.jpg",
                "Nachos",
                "\uD83E\uDDC0",
                "Indulgent",
                6,
                10,
                "Sports Bar",
                "Tortilla chips, cheese, jalapeños, salsa, sour cream, guacamole",
                "Loaded nachos with all your favorite toppings, great for sharing."
        );

// Snack 3
        Snack snack3 = new Snack(
                "image_url_3.jpg",
                "Sushi Rolls",
                "\uD83C\uDF63",
                "Healthy",
                8,
                20,
                "Sushi Restaurant",
                "Rice, seaweed, fish, vegetables",
                "Fresh sushi rolls made to order, a taste of Japan."
        );

// Snack 4
        Snack snack4 = new Snack(
                "image_url_4.jpg",
                "Cupcakes",
                "\uD83E\uDDC1",
                "Sweet",
                4,
                8,
                "Bakery",
                "Flour, sugar, butter, eggs, frosting",
                "Delicious cupcakes in a variety of flavors, perfect for dessert."
        );

// Snack 5
        Snack snack5 = new Snack(
                "image_url_5.jpg",
                "Pretzels",
                "\uD83E\uDD68",
                "Moderate",
                3,
                7,
                "Street Vendor",
                "Dough, salt",
                "Soft and salty pretzels, a classic snack for any occasion."
        );

// Snack 6
        Snack snack6 = new Snack(
                "image_url_6.jpg",
                "Fruit Smoothie",
                "\uD83C\uDF53",
                "Healthy",
                5,
                10,
                "Smoothie Bar",
                "Assorted fruits, yogurt, honey",
                "Refreshing fruit smoothie, perfect for a nutritious boost."
        );

// Snack 7
        Snack snack7 = new Snack(
                "image_url_7.jpg",
                "Mozzarella Sticks",
                "\uD83E\uDDC0",
                "Indulgent",
                7,
                12,
                "Italian Restaurant",
                "Mozzarella cheese, breadcrumbs, marinara sauce",
                "Crispy on the outside, gooey on the inside, a tasty appetizer."
        );

// Snack 8
        Snack snack8 = new Snack(
                "image_url_8.jpg",
                "Fries",
                "\uD83C\uDF5F",
                "Moderate",
                4,
                9,
                "Fast Food Joint",
                "Potatoes, oil, salt",
                "Golden and crispy fries, the ultimate comfort food."
        );

// Snack 9
        Snack snack9 = new Snack(
                "image_url_9.jpg",
                "Brownie",
                "\uD83C\uDF6B",
                "Sweet",
                3,
                6,
                "Café",
                "Chocolate, flour, sugar, eggs",
                "Rich and fudgy brownie, a chocolate lover's dream."
        );

// Snack 10
        Snack snack10 = new Snack(
                "image_url_10.jpg",
                "Cheese and Crackers",
                "\uD83E\uDDC0",
                "Moderate",
                6,
                10,
                "Gourmet Deli",
                "Assorted cheeses, crackers",
                "A classic combination of cheese and crackers, perfect for snacking."
        );

        snacks = new ArrayList<>(Arrays.asList(
            snack1,
            snack2,
            snack3,
            snack4,
            snack5,
            snack6,
            snack7,
            snack8,
            snack9,
            snack10,
            snack1,
            snack2,
            snack3,
            snack4,
            snack5,
            snack6,
            snack7,
            snack8,
            snack9,
            snack10

        ));


        adapter = new RecyclerViewAdapter(this, snacks, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(Snack snack) {
                Log.d("ASDA",""+snack.getTitle());
                Intent intent = new Intent(MainActivity.this, in_deapth_view.class);
                intent.putExtra("title", snack.getTitle());
                intent.putExtra("image", snack.getImage());
                intent.putExtra("emoji", snack.getEmoji());
                intent.putExtra("location", snack.getLocation());
                //intent.putExtra("ingredients", snack.getIngredients());
                intent.putExtra("desc", snack.getDesc());
                intent.putExtra("eta", snack.getEta());
                intent.putExtra("price", snack.getPrice());
                startActivity(intent);
            }
        });

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);



        new JsonFile(this, this).execute(JSON_FILE);

        //new JsonTask(this).execute(JSON_URL);

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
            return true;
        }

        if (id == R.id.healthy_snacks) {
            Log.d("==>","healthy_snacks");
            return true;
        }

        if (id == R.id.all_snacks) {
            Log.d("==>","all_snacks");
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

        snacks = gson.fromJson(json, type);
    }


}