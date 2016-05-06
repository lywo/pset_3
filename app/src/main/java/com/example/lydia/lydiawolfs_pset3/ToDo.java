package com.example.lydia.lydiawolfs_pset3;

/**
 * Created by Lydia on 4-5-2016.
 */
public class ToDo {

    private int _id;
    private String _item;

    public ToDo(){

    }

    public ToDo(int id, String item) {
        this._id = id;
        this._item = item;
    }

    public void setID(int id) {
        this._id = id;
    }

    public int getID() {
        return this._id;
    }

    public void setItem(String item) {
        this._item= item;
    }

    public String getItem() {
        return this._item;
    }
}
