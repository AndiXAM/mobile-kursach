package com.example.kursach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kursach.databinding.ActivityAdminBinding;
import com.example.kursach.databinding.ActivityInfromationBinding;

public class AdminActivity extends AppCompatActivity {

    private ActivityAdminBinding binding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Панель администратора");
            actionBar.setSubtitle("");
        }
    }

    public void SendURL(View view) {
        String textToSave = binding.editTextText3.getText().toString();
        String textNotifiacation = binding.editTextText4.getText().toString();
        editor.putString("savedText", textToSave);
        editor.apply();

        ImageView checkmarkImage = findViewById(R.id.checkmark_image);
        checkmarkImage.setVisibility(View.VISIBLE);

        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation);
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out_animation);

        checkmarkImage.startAnimation(fadeInAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkmarkImage.startAnimation(fadeOutAnimation);
                checkmarkImage.setVisibility(View.INVISIBLE);
            }
        }, 1000); // Задержка в 1 секунды

        Intent i=new Intent(this, MediaService.class);
        startService(i);
        showNotification("Новость дня!", textNotifiacation);// тут вызывается увеодмление
    }


    private void showNotification(String title, String message) { // оно работает НЕ ТРОГАЙ
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel_id", "Channel Name", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.infromationicon)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(0, builder.build());
    }


    public void goComment(View view) {
        Intent intent = new Intent(this, CommentActivity.class);
        intent.putExtra("username","admin");
        startActivity(intent);
    }
}