package com.example.musicplayer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdepter extends BaseAdapter {
    private Context context;
    private List<Song> list;
    public ListAdepter(MainActivity mainActivity, List<Song> list){
        this.context=mainActivity;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.listview, null);
            holder.song=(TextView) convertView.findViewById(R.id.songs);
            holder.singer=(TextView) convertView.findViewById(R.id.singer);
            holder.position=(TextView) convertView.findViewById(R.id.num);
            holder.duration=(TextView) convertView.findViewById(R.id.duration);
            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder) convertView.getTag();
        }
        holder.song.setText(list.get(position).song.toString());
        holder.singer.setText(list.get(position).singer.toString());
        holder.position.setText(position+1+"");
        int durtion=list.get(position).duration;
        String time=MusicList.formatTime(durtion);
        holder.duration.setText(time);
        return convertView;
    }
    class ViewHolder{
        TextView song;//歌曲名
        TextView singer;//歌手
        TextView duration;//时长
        TextView position;//序号

    }

}
