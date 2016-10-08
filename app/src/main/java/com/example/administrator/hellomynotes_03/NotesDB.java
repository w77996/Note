package com.example.administrator.hellomynotes_03;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/8/16.
 */
public class NotesDB extends SQLiteOpenHelper {
    public static final String DB_NAME="notes";
    public static  String ID = "_id";
    public static final String CONTENT = "content";
    public static final String TIME = "time";
//    private SQLiteDatabase sqLiteDatabase = getWritableDatabase();
    private NotesDB notesDB;

    public NotesDB(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+DB_NAME+"("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +CONTENT+" TEXT NO NULL,"
                +TIME+" TEXT NO NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
