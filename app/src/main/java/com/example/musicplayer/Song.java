package com.example.musicplayer;

public class Song {
    public Song(){}
    public Song(String singer1,String song1,String path1,int duration1,long size1){
        this.duration=duration1;
        this.path=path1;
        this.singer=singer1;
        this.size=size1;
        this.song=song1;
    }
    public String singer;
    public String song;
    public String path;
    public int duration;
    public long size;
    public String getSinger(){
        return singer;
    }
    public String getSong(){
        return song;
    }
    public String getPath(){
        return path;
    }
    public int getDuration(){
        return duration;
    }
    public long getSize(){
        return size;
    }
    public void setSinger(String singer){
        this.singer=singer;
    }
    public void setSong(String song){
        this.song=song;
    }
    public void setPath(String path){
        this.path=path;
    }
    public void setDuration(int duration){
        this.duration=duration;
    }
    public void setSize(long size){
        this.size=size;
    }
}
