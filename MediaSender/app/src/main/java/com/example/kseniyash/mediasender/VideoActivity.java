package com.example.kseniyash.mediasender;

import java.io.File;

import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.VideoView;
import android.os.Bundle;
import android.os.Environment;

public class VideoActivity extends AppCompatActivity {

    ToggleButton enableMediaController;
    VideoView myVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        enableMediaController = (ToggleButton)findViewById(R.id.enableMediaController);
        myVideoView = (VideoView)findViewById(R.id.videoView);
        myVideoView.setVideoPath(getViewSrc());
        myVideoView.requestFocus();
        myVideoView.start();

        setMediaController();

        enableMediaController.setOnCheckedChangeListener(new OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                setMediaController();
            }});
    }
    private void setMediaController(){
        if(enableMediaController.isChecked()){
            myVideoView.setMediaController(new MediaController(this));
        }else{
            myVideoView.setMediaController(null);
        }
    }

    private String getViewSrc(){
        File extStorageDirectory = Environment.getExternalStorageDirectory();
        String s = extStorageDirectory.getAbsolutePath() + "/test.mp4";
        Toast.makeText(VideoActivity.this, s, Toast.LENGTH_SHORT).show();
        return s;
    }
}
