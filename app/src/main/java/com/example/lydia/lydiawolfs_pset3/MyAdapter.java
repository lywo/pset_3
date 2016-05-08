package com.example.lydia.lydiawolfs_pset3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.lydia.lydiawolfs_pset3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lydia on 29-4-2016.
 */

public class MyAdapter extends ArrayAdapter<String>{


    public MyAdapter(Context context, ArrayList<String> values) {
        super(context, 0, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater toDoInflater = LayoutInflater.from(getContext());

        View theView = toDoInflater.inflate(R.layout.row_layout_2, parent, false);
        String toDoItem = getItem(position);
        TextView txtToDoItem = (TextView) theView.findViewById(R.id.txtToDo);
        txtToDoItem.setText(toDoItem);
        return theView;
    }
}
