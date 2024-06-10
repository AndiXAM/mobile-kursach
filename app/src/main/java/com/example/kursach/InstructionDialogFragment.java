package com.example.kursach;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InstructionDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Инструкция")
                .setMessage("Если вы читаете иструкцию то находитесь в главном меню. Если вы хотите сменить аккаунт то должны выйти и закрыть приложение. Нажатие на элементы списка будет отправлять вас на тематические экраны. Вернуться обратно можно при нажатии кнопки назад на палени навигации телефона")
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Обработка нажатия на кнопку "Ок"
                    }
                })
                .create();
    }
}