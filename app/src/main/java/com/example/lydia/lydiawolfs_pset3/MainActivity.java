package com.example.lydia.lydiawolfs_pset3;

/*
Lydia Wolfs
pset 3 : To Do List
10338217
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

/*
activity where toDoItems string array is filled with SQlite query
next To Do list is displayed
Rows can be added when add button is clicked via add query
 */

public class MainActivity extends AppCompatActivity {
    ArrayList<String> toDoItems = new ArrayList<String>();
    MyAdapter toDoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DBhelper myDB = new DBhelper(this);
        final ListView theToDoList = (ListView) findViewById(R.id.myList);
        final Button addToDoBT = (Button) findViewById(R.id.newToDoBT);
        addToDoBT.setEnabled(false);
        EditText myEditText = (EditText) findViewById(R.id.etxtNewToDo);
        myEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length()==0){
                    addToDoBT.setEnabled(false);
                } else {
                    addToDoBT.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // update toDoItems String [] with current to do items / load db
        toDoItems = myDB.readList();
        toDoAdapter = new MyAdapter(this, toDoItems);

        // set Adapter
        theToDoList.setAdapter(toDoAdapter);
        toDoAdapter.notifyDataSetChanged();

        assert theToDoList != null;
        theToDoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Delete row of item at current position in sql
                String toDoItem = theToDoList.getItemAtPosition(position).toString();

                // Toast to let user know which item was deleted
                Toast.makeText(getApplicationContext(), "You deleted: " + toDoItem, Toast.LENGTH_SHORT).show();

                myDB.deleteItem(toDoItem);

                // Update ListView
                toDoItems.clear();
                toDoItems.addAll(myDB.readList());
                toDoAdapter.notifyDataSetChanged();

                return true;
            }
        });
    }

    /*
    Adding a new to do item (string) to SQLitedatabse toDo_LIST in To Do object
    Adding also new string in listView via myAdapter
     */
    protected void addNewItem(View view){
        EditText txtToDoItem = (EditText) findViewById(R.id.etxtNewToDo);
        String newToDoItem = txtToDoItem.getText().toString();

        // new newToDoItem in sql input via query
        DBhelper myDB = new DBhelper(this);
        myDB.addItem(newToDoItem);
        txtToDoItem.setText("");

        // update ListView
        toDoItems.clear();
        toDoItems.addAll(myDB.readList());
        toDoAdapter.notifyDataSetChanged();
    }

    // Use of a Bundle to save the list’s data during rotation.
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
