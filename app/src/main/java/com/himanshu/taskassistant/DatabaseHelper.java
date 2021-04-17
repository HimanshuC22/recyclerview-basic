package com.himanshu.taskassistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TaskDB";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "TASKS";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        query = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY, TITLE TEXT, DESCRIPTION TEXT, DATETIME TEXT, ALARM BOOLEAN)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void addNotes(String title, String des, String datetime, boolean alarm) {
        SQLiteDatabase sqLiteDatabase = this .getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TITLE", title);
        values.put("DESCRIPTION", des);
        values.put("DATETIME", datetime);
        values.put("ALARM", alarm);
        //inserting new row
        sqLiteDatabase.insert(TABLE_NAME, null , values);

        //close database connection
        sqLiteDatabase.close();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<Data> getNotes() {
        ArrayList<Data> arrayList = new ArrayList<>();

        String select_query= "SELECT *FROM " + TABLE_NAME;
        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            do {
                Data data = new Data(cursor.getString(0), cursor.getString(1), cursor.getString(2), false);

                arrayList.add(data);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }
    public void delete(String ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //deleting row
        sqLiteDatabase.delete(TABLE_NAME, "ID=" + ID, null);
        sqLiteDatabase.close();
    }
}
