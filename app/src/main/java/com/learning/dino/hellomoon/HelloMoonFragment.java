package com.learning.dino.hellomoon;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class HelloMoonFragment extends Fragment {

    private AudioPlayer mPlayer = new AudioPlayer();

    private Button mPlayButton;
    private Button mPauseButton;
    private Button mStopButton;
    private Button mPlayVideo;


    public HelloMoonFragment() {
        // Required empty public constructor
    }

    void updateButtons(){
        boolean isPlaying = mPlayer.isPlaying();
        boolean isPaused = mPlayer.isPaused();

        if (isPaused){
            mPauseButton.setText(R.string.hellomoon_resume);
        }else{
            mPauseButton.setText(R.string.hellomoon_pause);
        }

        if (isPlaying){
            //player is not null
            mPlayButton.setEnabled(true);  //just start over
            mPauseButton.setEnabled(true);
            mStopButton.setEnabled(true);
        } else if (isPaused){
            //player is not null
            mPlayButton.setEnabled(true);  //just start over
            mPauseButton.setEnabled(true);
            mStopButton.setEnabled(true);
        }else{
            //Stopped, player is null
            mPlayButton.setEnabled(true);  //just start over
            mPauseButton.setEnabled(false); //no point to pause/resume if player is null
            mStopButton.setEnabled(true); //does nothing since player is null
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //set retain so fragment does not get destroyed on device rotation
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hello_moon, container, false);
        mPlayButton = (Button)v.findViewById(R.id.hellomoon_playButton);
        mPlayButton.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                mPlayer.play(getActivity());
                updateButtons();
            }
        });
        mPauseButton = (Button)v.findViewById(R.id.hellomoon_pauseButton);
        mPauseButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mPlayer.pauseOrResume();
                updateButtons();
                //mStopButton.setEnabled(false);
            }
        });
        mStopButton = (Button)v.findViewById(R.id.hellomoon_stopButton);
        mStopButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mPlayer.stop();
                updateButtons();
                mPauseButton.setEnabled(false);
            }
        });
        mPlayVideo = (Button)v.findViewById(R.id.play_videoButton);
        mPlayVideo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mPlayer.stop();
                Intent i = new Intent(getActivity(), HelloMoonVideoContainerActivity.class);
                startActivity(i);
            }
        });

        return v;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mPlayer.stop(); //stop player once app is destroyed since it executes on separate thread
    }
}
