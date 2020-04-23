package com.example.passwordmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db = this.getReadableDatabase();
    public DatabaseHelper(Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE table user(email text primary key, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    //inserting in database
    public boolean insert(String email, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.insert("user", null, contentValues);
        if (ins == -1) {
            return false;
        }
        else {
            return true;
        }
    }


    //check if email exists
    public boolean checkMail(String email) {
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});
        if (cursor.getCount() > 0) {
            return false;
        }
        else {
            return true;
        }
    }

     //Checking the email and password, if it matches
    public boolean emailPassword(String email, String password) {
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?", new String[]{email,password});
        if (cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
