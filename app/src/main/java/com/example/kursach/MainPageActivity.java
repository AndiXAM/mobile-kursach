package com.example.kursach;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainPageActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        String[] data = {"Киберспорт", "Новость дня","Галерея", "Комментарии", "Прочее..."};

        ListView listView = findViewById(R.id.test);

        String username = getIntent().getStringExtra("username");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(username);
            actionBar.setSubtitle("");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_list_item, data) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view;
                if (position == 0) {
                    view = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_item_special, parent, false);
                } else {
                    view = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_item, parent, false);
                }
                TextView textView = view.findViewById(R.id.text_view);
                textView.setText(getItem(position));
                return view;
            }
        };

        listView.setAdapter(adapter);        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = data[position];
            handleItemClick(selectedItem);
        });




    }

    private void handleItemClick(String selectedItem) {
        System.out.println("Нажат элемент: " + selectedItem);



        if (selectedItem.equals("Новость дня")) {
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            String loadedText = sharedPreferences.getString("savedText", "https://esports.mirea.ru/");
            System.out.println("Загруженный текст: " + loadedText);
            String url = loadedText;
            if (url.isEmpty())
                url = "https://esports.mirea.ru";

            try {
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent2);
            } catch (Exception e) {
                Toast.makeText(this, "Ошибка при переходе по ссылке: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else if (selectedItem.equals("Галерея")) {
            Intent intent3 = new Intent(this, GalleryActivity.class);
            startActivity(intent3);
        } else if (selectedItem.equals("Комментарии")) {
            Intent intent4 = new Intent(this, CommentActivity.class);
            String username = getIntent().getStringExtra("username");
            intent4.putExtra("username",username);
            startActivity(intent4);
        } else {
            Intent intent = new Intent(this, InformationActivity.class);
            intent.putExtra("selected_item", selectedItem);
            startActivity(intent);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.aboutAuthor) {
            DeveloperDialogFragment dialog = new DeveloperDialogFragment();
            dialog.show(getSupportFragmentManager(), "dev");        }
        else if (item.getItemId() == R.id.aboutProgram) {
            CustomDialogFragment dialog2 = new CustomDialogFragment();
            dialog2.show(getSupportFragmentManager(), "custom");
        }
        return super.onOptionsItemSelected(item);
    }



}