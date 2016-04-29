package com.example.lydia.lydiawolfs_pset3;

/*
Lydia Wolfs
pset 3 : To Do List
10338217
 */


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/*
activity where toDoItems string array is filled with SQlite query
next To Do list is displayed
Rows can be added when add button is clicked via add query
 */

public class MainActivity extends AppCompatActivity {

    String [] toDoItems = {}; // query db and load

    ListAdapter toDoAdapter = new MyAdapter(this, toDoItems);

// roep de notifyDatasetChnaged method aan over de adapter
// toDoAdapter.notifyDatasetChanged();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView theToDoList = (ListView) findViewById(R.id.listView);
        theToDoList.setAdapter(toDoAdapter);

        theToDoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // delete row at position
                return false;
            }
        });

    }

    protected void addNewItem(){
        setContentView(R.layout.activity_main);

        EditText txtToDoItem = (EditText) findViewById(R.id.newToDo);
        String newToDoItem = txtToDoItem.getText().toString();

        // new newToDoItem in sql input via query

    }


    // Use a Bundle to save the list’s data during rotation.
}
