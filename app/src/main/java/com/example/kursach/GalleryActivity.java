package com.example.kursach;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.kursach.databinding.ActivityGalleryBinding;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    ActivityGalleryBinding binding;
    ArrayList<Product> products = new ArrayList<>();
    BoxAdapter boxAdapter;

    ListView lvMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGalleryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
            actionBar.setSubtitle("");
        }

        fillData();
        boxAdapter = new BoxAdapter(this, products);

        lvMain = binding.lvMain;
        lvMain.setAdapter(boxAdapter);

    }



    void fillData() {
        String[] calcNames = {"League of Legends World Championship 2023", "M5 World Championship", "M4 World Championship",
                "MLBB Southeast Asia Cup 2023","Mid-Season Invitational 2023"};
        for (int i = 1; i <= 5; i++) {
            int imageId = getResources().getIdentifier("image" + ((i - 1) % 9 + 1),
                    "drawable", "com.example.kursach");
            products.add(new Product( calcNames[i-1],
                    imageId, false));
        }
    }


}