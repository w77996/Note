package com.example.administrator.hellomynotes_03;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/16.
 */
public class Addcontent extends Activity implements View.OnClickListener{
    private NotesDB notesDB;
    private Button saveBtn,cancelBtn;
    private EditText editText;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        initContent();
    }
    private void initContent(){
        notesDB = new NotesDB(this);
        sqLiteDatabase = notesDB.getWritableDatabase();
        saveBtn = (Button)findViewById(R.id.saveBtn);
        cancelBtn = (Button)findViewById(R.id.canclBtn);
        editText =(EditText)findViewById(R.id.edittext);
        saveBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }
    public static String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
    public long inserData(String text,String time){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NotesDB.CONTENT,text);
        contentValues.put(NotesDB.TIME,time);
        Log.d("test","test");
        long row = sqLiteDatabase.insert(NotesDB.DB_NAME,null,contentValues);
        Log.d("row",row+"");
        return row;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveBtn:
                    inserData(editText.getText().toString(),getTime());
                    finish();
                break;
            case R.id.canclBtn:
                    finish();
                break;
            default:
                break;
        }
    }
}