package com.example.kursach;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CommentsAdapter extends ArrayAdapter<Comment> {

    public CommentsAdapter(Context context, List<Comment> comments) {
        super(context, 0, comments);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comment_item, parent, false);
        }

        TextView usernameTextView = convertView.findViewById(R.id.username_text);
        TextView commentTextView = convertView.findViewById(R.id.comment_text);

        Comment comment = getItem(position);
        usernameTextView.setText(comment.getUsername());
        commentTextView.setText(comment.getCommentText());

        return convertView;
    }
}