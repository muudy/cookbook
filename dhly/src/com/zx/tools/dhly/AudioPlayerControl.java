package com.zx.tools.dhly;

import android.media.MediaPlayer;
import android.widget.MediaController;
import android.net.Uri;
import android.util.Log;
import android.widget.LinearLayout;



class AudioPlayerControl//ÉùÒô²¥·Å¿ØÖÆÆ÷
    implements MediaController.MediaPlayerControl
{
    private static final String TAG = "CallRecorder";

    private MediaPlayer player = null;
    private String path = null;

    public AudioPlayerControl(String path, bangfang listenerActivity) 
        throws java.io.IOException
    {
        this.path = path;

        player = new MediaPlayer();
        player.setDataSource(path);

        player.setOnPreparedListener(listenerActivity);
        player.setOnInfoListener(listenerActivity);
        player.setOnErrorListener(listenerActivity);
        player.setOnCompletionListener(listenerActivity);
        
        player.prepareAsync();
    }

    
    public boolean canPause() { return true; }
    public boolean canSeekBackward() { return true; }
    public boolean canSeekForward() { return true; }

    public int getBufferPercentage() {
        return 100;
    }

    public int getCurrentPosition() { 
        int pos = player.getCurrentPosition();
        return pos;
    }

    public int getDuration() {
        int duration = player.getDuration();
        return duration;
    }

    public boolean isPlaying() {
        boolean isp = player.isPlaying();
        return isp;
    }

    public void pause() {
        player.pause();
    }

    public void seekTo(int pos) {
        player.seekTo(pos);
    }

    public void start() {
        player.start();
    }

    public void destroy() {
        if (player != null) {
            player.reset();
            player.release();
            player = null;
        }
    }
}
