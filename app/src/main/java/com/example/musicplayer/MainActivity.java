package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.wifi.aware.DiscoverySession;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity{

    private MediaPlayer mediaPlayer=new MediaPlayer();
    private SeekBar seekBar;
    private Timer timer;
    private int currentposition;
    private int nextposition;
    private int backposition;
    private boolean aBoolean=false;
    private List<Song> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);
        list=MusicList.getMusicdate(this);
        final ListAdepter listAdepter=new ListAdepter(this,list);
        ListView lv=(ListView)findViewById(R.id.lv);
        lv.setAdapter(listAdepter);
        Button play=(Button)findViewById(R.id.play);
        final Button back=(Button)findViewById(R.id.back);
        final Button next=(Button)findViewById(R.id.next);
        final TextView tv1=(TextView)findViewById(R.id.tv1);
        final TextView tv2=(TextView)findViewById(R.id.tv2);
        final Button moshi=(Button) findViewById(R.id.moshi);
        moshi.setText("顺序播放");
        seekBar=(SeekBar)findViewById(R.id.seekbar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = list.get(position);
                currentposition=position;
                getposition();
                seekBar.setMax(song.duration);
                seekBar.setProgress(0);
                tv1.setText(song.song);
                tv2.setText(song.singer);
                if (!mediaPlayer.isPlaying()) {
                    try {
                        mediaPlayer.setDataSource(song.path);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    seekBar.setProgress(0);
                    try {
                        mediaPlayer.setDataSource(song.path);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    seekBar.setProgress(0);
                }

            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                list.remove(position);
                ListView lv=(ListView)findViewById(R.id.lv);
                ListAdepter listAdepter1=new ListAdepter(MainActivity.this,list);
                lv.setAdapter(listAdepter1);
                return true;
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
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(aBoolean==false) {
                    Song song = list.get(backposition);
                    currentposition = backposition;
                    seekBar.setProgress(0);
                    seekBar.setMax(song.duration);
                    tv1.setText(song.song);
                    tv2.setText(song.singer);
                    try {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(song.path);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Random random=new Random();
                    currentposition=random.nextInt(list.size());
                    Song song=list.get(currentposition);
                    seekBar.setProgress(0);
                    seekBar.setMax(song.duration);
                    tv1.setText(song.song);
                    tv2.setText(song.singer);
                    try {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(song.path);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                getposition();
            }
        });
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(aBoolean==false) {
                    Song song = list.get(nextposition);
                    currentposition = nextposition;
                    seekBar.setProgress(0);
                    seekBar.setMax(song.duration);
                    tv1.setText(song.song);
                    tv2.setText(song.singer);
                    try {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(song.path);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Random random=new Random();
                    currentposition=random.nextInt(list.size());
                    Song song=list.get(currentposition);
                    seekBar.setProgress(0);
                    seekBar.setMax(song.duration);
                    tv1.setText(song.song);
                    tv2.setText(song.singer);
                    try {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(song.path);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                getposition();
            }
        });
        moshi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(aBoolean){
                    Button button = findViewById(R.id.moshi);
                    button.setText("顺序播放");
                    aBoolean=false;
                    return;
                }
                if(aBoolean==false){
                    Button button = findViewById(R.id.moshi);
                    button.setText("随机播放");
                    aBoolean=true;
                    return;
                }
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(aBoolean==false){
                    Song song=list.get(nextposition);
                    currentposition=nextposition;
                    tv1.setText(song.song);
                    tv2.setText(song.singer);
                    seekBar.setProgress(0);
                    seekBar.setMax(song.duration);
                    try {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(song.path);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    getposition();
                }
                else{
                    Random random=new Random();
                    currentposition=random.nextInt(list.size());
                    Song song=list.get(currentposition);
                    tv1.setText(song.song);
                    tv2.setText(song.singer);
                    seekBar.setMax(song.duration);
                    seekBar.setProgress(0);
                    try {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(song.path);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                getposition();
            }
        });
        seekProcess();

    }


    public void seekProcess(){
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int i=mediaPlayer.getCurrentPosition();
                seekBar.setProgress(i);
            }
        },0,200);
    }

    public void getposition(){
        if(currentposition==0){
            nextposition=currentposition+1;
            backposition=list.size()-1;
        }
        else if(currentposition==list.size()-1){
            nextposition=0;
            backposition=currentposition-1;
        }
        else{
            nextposition=currentposition+1;
            backposition=currentposition-1;
        }
    }
}
