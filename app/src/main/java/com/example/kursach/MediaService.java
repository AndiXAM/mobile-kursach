package com.example.kursach;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MediaService extends Service {

    MediaPlayer ambientMediaPlayer;


    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate(){
        ambientMediaPlayer=MediaPlayer.create(this, R.raw.ding);
        ambientMediaPlayer.setLooping(false);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        ambientMediaPlayer.start();
        return  START_STICKY;
    }
    @Override
    public void onDestroy(){
        ambientMediaPlayer.stop();
    }
}