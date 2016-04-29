package com.example.lydia.lydiawolfs_pset3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.lydia.lydiawolfs_pset3.R;

/**
 * Created by Lydia on 29-4-2016.
 */

public class MyAdapter extends ArrayAdapter<String>{


    public MyAdapter(Context context, String [] values) {
        super(context, R.layout.row_layout_2, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater toDoInflater = LayoutInflater.from(getContext());

        View theView = toDoInflater.inflate(R.layout.row_layout_2, parent, false);

        String toDoItem = getItem(position);

        TextView txtToDoItem = (TextView) theView.findViewById(R.id.txtNewToDo);

        txtToDoItem.setText(toDoItem);

        CheckBox theCB = (CheckBox) txtToDoItem.findViewById(R.id.cb);

        return theView;
    }
}
