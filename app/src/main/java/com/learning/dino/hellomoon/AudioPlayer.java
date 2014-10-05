package com.learning.dino.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by dbulj on 05/10/2014.
 */
public class AudioPlayer {

    private MediaPlayer mPlayer;
    private int mPosition;
    private boolean mPaused;

    public void stop(){
        if (mPlayer != null){
            mPaused = false;
            mPosition = 0;
            //MediaPlayer holds on to the audio decoder hardware and other system resources until
            //you call release().  These resources are shared accross all apps.
            //So, keep exactly one MediaPlayer around and keep it around only as long as it is
            //playing something.  To enforce that rule, we call stop() as a first call from play()
            //and we set an OnCompletionListener to call stop when the audio file has finished playing.
            mPlayer.release(); //destroy the instance of MediaPlayer
            mPlayer = null;
        }
    }

    public void pauseOrResume(){
        if (mPlayer != null){
            if (mPlayer.isPlaying()){
                mPosition = mPlayer.getCurrentPosition();
                mPaused = true;
                mPlayer.pause();
            }else{
                mPlayer.seekTo(mPosition);
                mPaused = false;
                mPlayer.start();
            }
        }
    }

    public void play(Context c){
        stop();

        mPlayer = MediaPlayer.create(c, R.raw.one_small_step);

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                //Once audio file completed playing, destroy MediaPlayer.  We use agressive stop()
                //rather than provided MediaPleyer.stop() method to enforce rule to have only one
                //MediaPlayer around only when media is playing.
                stop();
            }
        });

        mPlayer.start();
    }

    public boolean isPlaying(){
        return mPlayer != null;
    }

    public boolean isPaused() {
        return mPaused;
    }
}
