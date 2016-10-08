package com.example.administrator.hellomynotes_03;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/8/16.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private Cursor cursor;
    private LinearLayout layout;
    public MyAdapter(Context context, Cursor cursor){
        this.context = context;
        this.cursor = cursor;
    }
    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return cursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =  LayoutInflater.from(context);
        layout = (LinearLayout) inflater.inflate(R.layout.list_cell,null);

        TextView contentTv = (TextView)layout.findViewById(R.id.cell_content);
        TextView timeTv = (TextView)layout.findViewById(R.id.cell_time);
        cursor.moveToPosition(position);
        String content = cursor.getString(cursor.getColumnIndex("content"));
        String time = cursor.getString(cursor.getColumnIndex("time"));


        contentTv.setText(content);
        timeTv.setText(time);

        return layout;
    }
}
