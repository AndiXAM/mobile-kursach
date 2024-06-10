package com.example.kursach;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class CommentActivity extends AppCompatActivity {

    private static final String COMMENTS_PREF = "comments_pref";
    private static final String COMMENTS_KEY = "comments_key";

    private ListView commentsList;
    private Button addCommentButton;
    private EditText commentInput;
    private List<Comment> comments;
    private CommentsAdapter adapter;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        commentsList = findViewById(R.id.comments_list);
        addCommentButton = findViewById(R.id.add_comment);
        commentInput = findViewById(R.id.comment_input);

        sharedPreferences = getSharedPreferences(COMMENTS_PREF, MODE_PRIVATE);

        Intent intent = getIntent();
        String Username = intent.getStringExtra("username");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Лента комментариев");
            actionBar.setSubtitle("");
        }

        loadComments();

        adapter = new CommentsAdapter(this, comments);
        commentsList.setAdapter(adapter);

        addCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentText = commentInput.getText().toString();
                if (!commentText.isEmpty()) {
                    Comment newComment = new Comment(Username, commentText);
                    comments.add(newComment);
                    adapter.notifyDataSetChanged();
                    commentInput.setText("");
                    saveComments();
                } else {
                    Toast.makeText(CommentActivity.this, "Введите комменатрий", Toast.LENGTH_SHORT).show();
                }
            }
        });

        commentsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (Username.equals("admin")) {
                    comments.remove(position);
                    adapter.notifyDataSetChanged();
                    saveComments();
                } else {
                }
            }
        });
    }

    private void loadComments() {
        String commentsJson = sharedPreferences.getString(COMMENTS_KEY, null);
        if (commentsJson != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Comment>>() {}.getType();
            comments = gson.fromJson(commentsJson, type);
        } else {
            comments = new ArrayList<>();
        }
    }

    private void saveComments() {
        Gson gson = new Gson();
        String commentsJson = gson.toJson(comments);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(COMMENTS_KEY, commentsJson);
        editor.apply();
    }
}