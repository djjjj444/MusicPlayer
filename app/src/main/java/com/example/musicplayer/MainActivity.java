package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private MediaPlayer mediaPlayer=new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);
        final List<Song> list=MusicList.getMusicdate(this);
        ListAdepter listAdepter=new ListAdepter(this,list);
        ListView lv=(ListView)findViewById(R.id.lv);
        lv.setAdapter(listAdepter);
        Button play=(Button)findViewById(R.id.play);
        Button pause=(Button)findViewById(R.id.pause);
        Button stop=(Button)findViewById(R.id.stop);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song=list.get(position);
                try {
                    mediaPlayer.setDataSource(song.path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
