package com.example.kursach;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DeveloperDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_developer_dialog, null);
        builder.setView(view);


        ImageView avatar = view.findViewById(R.id.avatar);
        TextView name = view.findViewById(R.id.name);
        TextView group = view.findViewById(R.id.group);
        TextView year = view.findViewById(R.id.year);
        TextView email = view.findViewById(R.id.email);


        avatar.setImageResource(R.drawable.newauthor);
        name.setText("Автор - Хариханов Анди Майрбекович");
        group.setText("ИКБО-03-22");
        year.setText("2024");
        email.setText("andy280704@gmail.com");

        return builder.create();
    }
}