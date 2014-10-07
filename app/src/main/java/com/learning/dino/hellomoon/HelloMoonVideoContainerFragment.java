package com.learning.dino.hellomoon;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class HelloMoonVideoContainerFragment extends Fragment {


    private MediaPlayer mPlayer;
    private SurfaceHolder mSurfaceHolder;
    private SurfaceView mSurfaceView;

    public HelloMoonVideoContainerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //retain fragment so it is not destroyed on device rotation or other configuration change
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hello_moon_video, container, false);
        mSurfaceView = (SurfaceView)v.findViewById(R.id.videoSurface);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                //mPlayer = MediaPlayer.create(getActivity(), R.raw.apollo_17_stroll);
                if (mPlayer == null){
                    mPlayer = MediaPlayer.create(getActivity(), R.raw.apollo_17_stroll);
                    //Log.i("mPlayer ", "is null");
                }else{
                    mPlayer.setDisplay(mSurfaceHolder);
                }
                //mPlayer.setDisplay(mSurfaceHolder);
                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mPlayer){
                        getActivity().finish();
                    }
                });
                mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mPlayer) {
                        mPlayer.start();
                    }
                });
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                mPlayer.setDisplay(null);
            }
        });

        return v;
    }


}
