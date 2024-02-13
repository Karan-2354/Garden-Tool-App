package com.example.plant_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(name text,email Text primary key ,password Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");

    }
    public boolean insert_data(String name, String email, String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("name",name);
        c.put("email",email);
        c.put("password",password);

        long r=db.insert("users",null,c);
        if(r==-1)
        {
            return false;
        }
        else
            return true;
    }public boolean updatepassword( String email, String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("password",password);

        long r=db.update("users",c,"email=?",new String[]{email});
        if(r==-1)
        {
            return false;
        }
        else
            return true;
    }

    public boolean checkemail(String email)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("select * from users where email=?",new String[]{email});

        if(c.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    public Boolean checkemailpassword(String email,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("select * from users where email=? and password=?",new String[]{email,password});
        if(c.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

//    public ArrayList<Info> fecthinfo()
//    {
//        SQLiteDatabase db=this.getReadableDatabase();
//        Cursor cursor=db.rawQuery("select name,email from users",null);
//        ArrayList<Info> info=new ArrayList<>();
//
//        while (cursor.moveToNext())
//        {
//            Info i=new Info();
//            i.name=cursor.getString(0);
//            i.email=cursor.getString(1);
//
//            info.add(i);
//        }
//        return info;
//
//    }

    private   Info getProfileFromDatabase() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                "name",
                "email"
        };

        Cursor cursor = db.rawQuery("select name,email from users",null);

        Info profile = null;

        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
           // int age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));

            profile = new Info(name, email);
        }

        cursor.close();
        return profile;
    }
}
