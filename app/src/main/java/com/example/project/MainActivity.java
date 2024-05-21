package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.lang3.StringEscapeUtils;

import java.lang.reflect.Type;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=a23petny";

    private ArrayList<Snack> snacks = new ArrayList<>();
    private ArrayList<String> acceptableHealthlvl = new ArrayList<>();
    private RecyclerViewAdapter adapter;
    private SharedPreferences myPreferenceRef;
    private SharedPreferences.Editor myPreferenceEditor;

    // why  dont i have a toolbar in my about view and how can i make the emojis into emojis
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myPreferenceRef =  getSharedPreferences("MyPreferenceName", MODE_PRIVATE);
        myPreferenceEditor = myPreferenceRef.edit();

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

            acceptableHealthlvl.clear();
            acceptableHealthlvl.add("High");
            acceptableHealthlvl.add("Very High");
            myPreferenceEditor.putStringSet("acceptableHealthlvl", new HashSet<>(acceptableHealthlvl));
            myPreferenceEditor.apply();

            new JsonTask(this).execute(JSON_URL);
            return true;

        }

        if (id == R.id.all_snacks) {
            acceptableHealthlvl.clear();
            myPreferenceEditor.putStringSet("acceptableHealthlvl", new HashSet<>(acceptableHealthlvl));
            myPreferenceEditor.apply();
            new JsonTask(this).execute(JSON_URL);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onResume(){
        super.onResume();

        acceptableHealthlvl.addAll(myPreferenceRef.getStringSet("acceptableHealthlvl", new HashSet<>(Arrays.asList(""))));
    }



    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", "" + json);
        Gson gson = new Gson();
        // Unmarshall JSON -> list of objects
        Type type = new TypeToken<List<Snack>>() {
        }.getType();
        List<Snack> allSnacks = gson.fromJson(json, type);

        snacks.clear();
        for (Snack snack : allSnacks) {
            if (acceptableHealthlvl.isEmpty() || acceptableHealthlvl.contains(snack.getHealthLvl())) {//"\uD83C\uDF5F"
                //Log.d("asda",snack.getEmoji());
                String baseEmoji = snack.getEmoji();
                String emoji = StringEscapeUtils.unescapeJava(baseEmoji);

                Log.d("asda",""+emoji);
                snack.setEmoji(emoji);
                snacks.add(snack);
            }
        }

        adapter.notifyDataSetChanged();
    }

}