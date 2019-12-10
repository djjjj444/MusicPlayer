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
import android.widget.SeekBar;
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
        final SeekBar seekBar=(SeekBar)findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = list.get(position);
                if (!mediaPlayer.isPlaying()) {
                    try {
                        mediaPlayer.setDataSource(song.path);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        seekBar.setMax(song.duration);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    mediaPlayer.reset();
                    try {
                        mediaPlayer.setDataSource(song.path);
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.start();
                }
            }
        });
        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
                else{
                    mediaPlayer.pause();
                }
            }
        });

    }


}
