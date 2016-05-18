package com.example.lydia.lydiawolfs_pset3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/**
 * Created by Lydia on 29-4-2016.
 * DB helper that manages the todoList database.
 */
public class DBhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "todo.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TODO_LIST = "todolist";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ITEM = "item";

    public DBhelper(Context context){ // DIT IS DE CONSTRUCTOR
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TODO_LIST + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY," + COLUMN_ITEM + " TEXT"+ ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TODO_LIST + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY," + COLUMN_ITEM + " TEXT,"+ ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);

    }

    /*
    CRUD method
    Add one item.
    */
    public void addItem(String listItem) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM, listItem);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TODO_LIST, null, values);
        db.close();
    }

    /*
    CRUD method
    Delete one item from the list.
     */
    // TODO delete items by id not String item value
    public void deleteItem(String item) {
        String query = "DELETE FROM " + TODO_LIST + " WHERE " + COLUMN_ITEM + " =  \"" + item + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    /*
    CRUD method
    Loading complete database
     */
    public ArrayList<String>readList() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT _id, item FROM " + TODO_LIST;
        Cursor cursor = db.rawQuery( query, null );
        ArrayList<String> ToDoItems = new ArrayList<String>();

        // adding a String listItem to the ArrayList of ToDoItems.
        String listItem = "";
        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                listItem = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM));
                ToDoItems.add(listItem);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return ToDoItems;
        }

        /*
        Function to clean up whole database.
         */
        public void DeleteAll(){
            SQLiteDatabase db = getWritableDatabase();
            String query = "DELETE FROM " + TODO_LIST;
            db.execSQL(query);
        }
    }
