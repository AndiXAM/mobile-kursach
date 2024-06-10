package com.example.kursach;

import androidx.appcompat.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.DialogFragment;
import androidx.annotation.NonNull;

public class CustomDialogFragment extends DialogFragment {

    private static final String TAG = "DialogLifecycle";

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Dialog created");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("О программе")
                .setMessage("Данное приложение явлется информационно справочной система на тему киберспорта")
                .setIcon(R.drawable.infromationicon)
                .setView(R.layout.dialog)

                .setNegativeButton("Ок", null)
                .setPositiveButton("Инструкция", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        InstructionDialogFragment instructionDialogFragment = new InstructionDialogFragment();
                        instructionDialogFragment.show(getFragmentManager(), "instruction_dialog");
                    }
                })
                .create();
    }

}