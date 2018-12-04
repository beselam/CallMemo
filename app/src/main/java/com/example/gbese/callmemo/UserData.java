package com.example.gbese.callmemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserData extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="NebiBese.db";
    public static final int    DATABASE_VERSION  =1;



    public UserData(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE MMEMO ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "TITLE TEXT, "
                + "CONTENT TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addData(String itemtitle,String itemcontent ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("TITLE",itemtitle);
        contentValues.put("CONTENT", itemcontent);
        db.insert("MMEMO", null, contentValues);


        return true;

    }

    public Cursor getListContent(int ListId){
        SQLiteDatabase db = this.getWritableDatabase();
      Cursor cuursor = db.query("MMEMO",
                new String[]{"_id","TITLE", "CONTENT"}, "_id = ?", new String[]{Integer.toString(ListId)}
                , null, null, null);
        return cuursor;

    }
    public boolean UpdateData(String itemtitle,String itemcontent ,int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("TITLE",itemtitle);
        contentValues.put("CONTENT", itemcontent);
        db.update("MMEMO",contentValues,
                "_id = ?",
                new String[]{Integer.toString(id)});


        return true;

    }
    public boolean DelateData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("MMEMO",
                "_id = ?",
                new String[]{Integer.toString(id)});


        return true;

    }

}
