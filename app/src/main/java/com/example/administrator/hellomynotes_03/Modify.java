package com.example.administrator.hellomynotes_03;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/8/17.
 */
public class Modify extends Activity implements View.OnClickListener{
    private Button saveBtn,delBtn,cancelBtn;
    private EditText editText;
    private Intent intent ;
    private NotesDB notesDB;
    private SQLiteDatabase sqLiteDatabase ;
    private int _id ;
    private String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify);
        initModify();
    }
    private void initModify(){
        saveBtn = (Button)findViewById(R.id.modify_saveBtn);
        delBtn = (Button)findViewById(R.id.modify_delBtn);
        cancelBtn = (Button)findViewById(R.id.modify_cancelBtn);
        editText = (EditText)findViewById(R.id.modify_edit);
        saveBtn.setOnClickListener(this);
        delBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        ID = getIntent().getStringExtra(NotesDB.ID);
        _id = Integer.parseInt(ID);
        String content = getIntent().getStringExtra(NotesDB.CONTENT);
        Log.d("id",_id+"");
        System.out.println(content);
//        Log.d("contnetn",content);
        editText.setText(content);
        notesDB = new NotesDB(this);
        sqLiteDatabase = notesDB.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.modify_saveBtn:
                    updataDate(_id,editText.getText().toString(),Addcontent.getTime());
                    finish();
                break;
            case R.id.modify_delBtn:
                    delData(_id);
                Toast.makeText(getApplicationContext(),"成功删除",Toast.LENGTH_SHORT).show();
                    finish();
                break;
            case R.id.modify_cancelBtn:
                finish();
                break;
            default:
                break;
        }
    }
    private void delData(int id){
        sqLiteDatabase.delete(NotesDB.DB_NAME, NotesDB.ID+"=?",  new String[]{ID});
    }
    private void updataDate(int id,String content,String time){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NotesDB.CONTENT,content);
        contentValues.put(NotesDB.TIME,time);
        sqLiteDatabase.update(NotesDB.DB_NAME,contentValues,NotesDB.ID+="?",new String[]{ID} );
    }
}
