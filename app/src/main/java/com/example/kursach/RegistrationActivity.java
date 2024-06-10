package com.example.kursach;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.kursach.databinding.ActivityMainBinding;
import com.example.kursach.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends AppCompatActivity {

    private ActivityRegistrationBinding binding;
    private DatabaseHelper dbHelper;
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String TABLE_NAME = "users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        dbHelper = new DatabaseHelper(this); // Инициализация DatabaseHelper

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
            actionBar.setSubtitle("");
        }

        try { // это вывод датасторы
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(
                    TABLE_NAME,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    int usernameIndex = cursor.getColumnIndexOrThrow(COLUMN_USERNAME);
                    int passwordIndex = cursor.getColumnIndexOrThrow(COLUMN_PASSWORD);
                    String username = cursor.getString(usernameIndex);
                    String password = cursor.getString(passwordIndex);
                    // Делайте что-нибудь с данными пользователя
                    Log.d("User", "Username: " + username + ", Password: " + password);
                } while (cursor.moveToNext());
            }

            cursor.close();
        } catch (SQLiteException e) {
            Log.e("Error", "Error accessing database: " + e.getMessage());
        }
    }

    public void closeCurrentActivity(View view) {
        String login = binding.login.getText().toString();
        String password = binding.login.getText().toString();
        saveCredentials(login,password);
        finish();
    }

    public void saveCredentials(String username, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}