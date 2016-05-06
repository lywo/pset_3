package com.example.lydia.lydiawolfs_pset3;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lydia on 29-4-2016.
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
                " INTEGER PRIMARY KEY," + COLUMN_ITEM + " TEXT,"+ ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // oude database verwijderen

        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TODO_LIST + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY," + COLUMN_ITEM + " TEXT,"+ ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);

    }

    // CRUD method adding new info
    public void addItem(String listItem) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM, listItem);

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TODO_LIST, null, values);
        db.close();
    }


    // CRUD method read info
    public ToDo readItem(String item) {
        String query = "Select * FROM " + TODO_LIST + " WHERE " + COLUMN_ITEM + " =  \"" + item + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        ToDo listItem = new ToDo();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            listItem.setID(Integer.parseInt(cursor.getString(0)));
            listItem.setItem(cursor.getString(1));
            cursor.close();
        } else {
            listItem = null;
        }
        db.close();
        return listItem;
    }

    // CRUD method edit
    public ToDo editItem(ToDo listItem) {
        String query = "Select * FROM " + TODO_LIST + " WHERE " + COLUMN_ID + " = " + listItem.getID() + "\"";

        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM, listItem.getItem());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TODO_LIST, null, values);
        db.close();

        return listItem;
    }

    // CRUD method delete
    public boolean deleteItem(String item) {

        boolean result = false;

        String query = "Select * FROM " + TODO_LIST + " WHERE " + COLUMN_ITEM + " =  \"" + item + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        ToDo listItem = new ToDo();

        if (cursor.moveToFirst()) {
            listItem.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TODO_LIST, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(listItem.getID()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    /*
    Loading complete database
     */
    public String [] readList() {
//            throws SQLException, JSONException, ClassNotFoundException, java.sql.SQLException

        SQLiteDatabase db = getReadableDatabase();
        String query = "Select _id, item FROM " + TODO_LIST+ "\"";
        Cursor cursor = db.rawQuery( query, null );

        List<String> listItems = new ArrayList<String>();

//        PreparedStatement ps = null;
//        ResultSet rs = ps.executeQuery();
//
//        while(rs.next()) {
//            int id = rs.getString(1);
//            String pass = rs.getString(2);
//            listItems.add(id, pass);
//        }

//        Connection con = ...;
//        try {
//            ps = con.prepareStatement(query);
//        } catch (java.sql.SQLException e) {
//            e.printStackTrace();
//        }

        String listItem = "";
        if (cursor .moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                listItem = cursor.getString(cursor.getColumnIndex(listItem));

                listItems.add(listItem);
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();

        String [] ToDoItems = listItems.toArray(new String[listItems.size()]);

        return ToDoItems;

        }
    }
