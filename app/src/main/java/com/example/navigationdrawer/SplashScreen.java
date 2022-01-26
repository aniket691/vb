package com.example.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //        try {
        //            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash);
        //            VideoView simpleVideoView = (VideoView) findViewById(R.id.vh); // initiate a video view
        //            simpleVideoView.setVideoURI(uriS);
        //
        //            simpleVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
        //                public void onCompletion(MediaPlayer mp) {
        //                    jump();
        //                }
        //            });
        //            simpleVideoView.start();
        //        } catch (Exception ex) {
        //            jump();
        //        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), SignIn.class));
                finish();
            }


        }, 2000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        jump();
        return true;
    }

    private void jump() {
        if (isFinishing())
            return;
        startActivity(new Intent(this, SignIn.class));
        finish();
    }
}