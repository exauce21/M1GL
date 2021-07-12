package com.example.m1gl2021;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BdSchool extends SQLiteOpenHelper {

    public BdSchool(@Nullable Context context) {
        super(context, "school.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT, login VARCHAR(50), password VARCHAR(100));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //s'éxecute lorsque la version de la base change
        db.execSQL("DROP TABLE if exists user;");
        onCreate(db);
    }

    public boolean create(String login, String password){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("login", login);
            cv.put("password", password);
            db.insert("user", null, cv);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getUsers(){
        List<String> list = new ArrayList<>();

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.query(null, null, null, null, null, null, null);

            if(c!=null && c.getCount()>0){
                c.moveToFirst();

                do{
                    String login = c.getString(c.getColumnIndex("login"));
                    list.add(login);
                    c.moveToNext();
                }while(!c.isAfterLast());
            }

            db.close();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return list;
    }

    public boolean update(String login, String password){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            //cv.put("login", login);
            cv.put("password", password);
            db.update("user", cv,"login='"+login+"'", null);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String login){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("user", "login='"+login+"'", null);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
