package com.example.plant_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    public DB( Context context) {
        super(context, "info.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(id integer primary key,PP BLOB)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");

    }
    public boolean save(int id,byte[] pp)
    {

            ContentValues c=new ContentValues();
        SQLiteDatabase db=this.getWritableDatabase();
            //c.put("id",id);

            c.put("pp",pp);
//            c.put("name",name);
//            c.put("email",email);
//            c.put("phone",phone);
           //c.put("add",add);
         long a= db.insert("user",null,c);
        if(a==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
