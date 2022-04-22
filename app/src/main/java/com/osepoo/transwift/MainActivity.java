package com.osepoo.transwift;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MediaPlayer intromusic;
    Animation topAnim,bottomAnim;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        imageView = (ImageView) findViewById(R.id.mainimage);
        textView = (TextView) findViewById(R.id.maintext);

        imageView.setAnimation(topAnim);
        textView.setAnimation(bottomAnim);

        LinearLayout linearLayout = findViewById(R.id.homepagelayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();

        intromusic = MediaPlayer.create(MainActivity.this,R.raw.intromsc1);
        intromusic.start();

        Thread firstPageTimer = new Thread(){
            public void run(){

                try{
                    sleep(6000);
                    Intent slimshady = new Intent(MainActivity.this,HomePage.class);
                    startActivity(slimshady);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    finish();
                }
            }
        };
        firstPageTimer.start();
    }
    protected void onPause(){
        super.onPause();
        intromusic.release();
    }
}
