package com.example.administrator.hellomynotes_03;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button button;
    private  NotesDB notesDB;
    private ListView listView;
    private Cursor cursor;
    private SQLiteDatabase sqLiteDatabase;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  Log.d("kankan","kankan");
        //System.out.println("kankanaknakna");
        initMain();
        //Toast.makeText(this,"fsafas",Toast.LENGTH_SHORT).show();
    }
    public void  initMain(){
        button = (Button)findViewById(R.id.addBtn);
        listView = (ListView)findViewById(R.id.listview);
        notesDB = new NotesDB(this);
        sqLiteDatabase = notesDB.getWritableDatabase();



        selectDB();
      //  Log.d("tess","rfdsfsdfsadfasfsdfsadfsafsdfsdfsdfsdest");
        //System.out.println("zheshishenmememememmememem");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("positon",position+"");
                cursor.moveToPosition(position);
                Intent intent = new Intent(MainActivity.this,Modify.class);
                intent.putExtra(NotesDB.ID,cursor.getString(cursor.getColumnIndex(NotesDB.ID)));
                intent.putExtra(NotesDB.CONTENT,cursor.getString(cursor.getColumnIndex(NotesDB.CONTENT)));
                System.out.println(cursor.getString(cursor.getColumnIndex(NotesDB.CONTENT)));
                startActivity(intent);
               // System.out.println(position);
               // Log.d("position",position+"");

            }
        });
        button.setOnClickListener(this);
    }
    public void selectDB() {
        cursor = sqLiteDatabase.query(NotesDB.DB_NAME, null, null, null, null,
                null, null);
        adapter = new MyAdapter(this, cursor);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("resume","resume");
        selectDB();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addBtn:
                System.out.println("fasdfa");
                Intent intent  = new Intent(this,Addcontent.class);
                startActivity(intent);
                break;
            default:

                break;
        }
    }

}
