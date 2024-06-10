package com.example.kursach;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.kursach.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {



    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
            actionBar.setSubtitle("");
        }





    }

    public void openRegistrationActivity(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void openMainPageActivity(View view) {
        Intent intent;
        if (binding.login.getText().toString().equals("admin") && binding.password.getText().toString().equals("admin")){
            intent = new Intent(this, AdminActivity.class);
            startActivity(intent);
        }
        else {

            intent = new Intent(this, MainPageActivity.class);
            intent.putExtra("username", binding.login.getText().toString());
            if( checkCredentials(binding.login.getText().toString(), binding.password.getText().toString())){
                startActivity(intent);
            }
            else {
                Toast.makeText(MainActivity.this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
            }
        }

    }


    DatabaseHelper dbHelper = new DatabaseHelper(this);
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String TABLE_NAME = "users";
    public boolean checkCredentials(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }
}