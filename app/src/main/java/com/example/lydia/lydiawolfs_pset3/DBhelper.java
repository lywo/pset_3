package com.example.lydia.lydiawolfs_pset3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.w3c.dom.Text;

/**
 * Created by Lydia on 29-4-2016.
 */
public class DBhelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "first.db";
    private static final int DATABASE_VERSION = 1;

    public DBhelper(Context context){ // DIT IS DE CONSTRUCTOR
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = getWritableDatabase();
        String query = "CREATE TABLE toDoItems(_id INTEGER PRIMARY KEY AUTOINCREMENT", item Text)
        db.execSQL(query); // executing!
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db = getWritableDatabase();
        String query = "CREATE TABLE toDoItems(_id INTEGER PRIMARY KEY AUTOINCREMENT", item Text)
        db.execSQL(query);
    }

    public void onUpdate(SQLiteDatabase db){

    }

    // CRUD method adding new info
//    SQLiteDatabase db = getWritableDatabase();
//
//    ContentValues values = new ContentValues();
    values.put();

    db.insert(c);
    db.close();


    // CRUD method read
//    SQLiteDatabase db = getWritableDatabase();
    query = "SELECT _id, item FROM toDoItems";
    Cursor cursor = db.rawQuery(query, null);
    //DO whatever you want with the information
    cursor.close();
    db.close();

    // CRUD method edit
    int Id= 3
    SQLiteDatabase db = getWritableDatabase();

    ContentValues values = newContentValues();
    values.put(item, );

    db.update(toDoItems, values, _id=?,  new String[] {String.valueOf(id)});
    db.close();

    // CRUD method delete
    int id =3;
    SQLiteDatabase db = getWritableDatabase();

    db.delete(, _id = ?, new String[] {String.ValueOf(id)});

    db.close();
}
