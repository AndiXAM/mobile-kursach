package com.example.kursach;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.kursach.databinding.ActivityInfromationBinding;

public class InformationActivity extends AppCompatActivity {

    private ActivityInfromationBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfromationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String selectedItem = getIntent().getStringExtra("selected_item");
        binding.textView.setText(selectedItem);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
            actionBar.setSubtitle("");
        }

        if (selectedItem.equals("Киберспорт")){
            binding.textView.setText("Киберспорт – это соревнования по компьютерным или мобильным играм, признанные официальным видом спорта в России с 2016 года. В России киберспорт можно разделить на шутеры, стратегии, карточно-коллекционные игры, спортивные симуляторы \nКиберспорт является командным или индивидуальным соревнованием на основе компьютерных видеоигр. Главные матчи крупных турниров смотрят миллионы зрителей, и они часто проходят на стадионах, куда собираются тысячи любителей киберспорта, а также транслируются в интернете в онлайн-режиме. Призовые фонды на мировых чемпионатах могут достигать десятков миллионов долларов");
        }
        if (selectedItem.equals("Прочее...")){
            binding.textView.setText("Турниры и соревнования \nГлавные матчи крупных киберспортивных турниров собирают миллионы зрителей. Они часто проходят на стадионах, куда приходят тысячи фанатов, а также транслируются онлайн. Призовые фонды на мировых чемпионатах могут достигать десятков миллионов долларов \n\nСоставы команд \nВ киберспорте количество игроков в команде варьируется от 1 до 15 в зависимости от дисциплины. Идеальный состав команды включает:\n" +
                    "Профессиональных игроков\n" +
                    "Капитана\n" +
                    "Тренера" );
        }
    }
}