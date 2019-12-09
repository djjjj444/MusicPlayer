package com.example.musicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

import java.util.List;

public class MyAdepter extends ArrayAdapter {
    private List<Song> list;
    private int resourceId;

    public MyAdepter(@NonNull Context context, int resource,List<Song> list) {
        super(context, resource);
        this.list=list;
        resourceId=resource;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        Song song= (Song) getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView num=(TextView) view.findViewById(R.id.num);
        TextView songs=(TextView) view.findViewById(R.id.songs);
        TextView singer=(TextView)view.findViewById(R.id.singer);
       num.setText(position);
        songs.setText(list.get(position).song.toString());
        singer.setText(list.get(position).singer.toString());
        return view;
    }
}
