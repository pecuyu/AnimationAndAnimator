package com.yu.animationandanimator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class AnimationActivity extends AppCompatActivity {
    private ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        mIv = (ImageView) findViewById(R.id.id_iv);
    }

    public void alpha(View view) {
        AlphaAnimation animation = new AlphaAnimation(1, 0.5f);
        animation.setDuration(2000);
        mIv.startAnimation(animation);

    }

    public void scale(View view) {
        ScaleAnimation animation = new ScaleAnimation(1, 0.5f, 1, 0.8f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(2000);
        mIv.startAnimation(animation);
    }

    public void transition(View view) {
        TranslateAnimation animation = new TranslateAnimation(0, 300, 0, 500);
        animation.setDuration(2000);
        mIv.startAnimation(animation);
    }

    public void rotation(View view) {
        RotateAnimation animation = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(2000);
        mIv.startAnimation(animation);

    }

    public void set(View view) {
        AnimationSet set = new AnimationSet(true);
        AlphaAnimation aa = new AlphaAnimation(1, 0.5f);
        ScaleAnimation sa = new ScaleAnimation(1, 0.5f, 1, 0.8f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        TranslateAnimation ta = new TranslateAnimation(100, 300, 200, 500);
        RotateAnimation ra = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        set.addAnimation(aa);
        set.addAnimation(sa);
        set.addAnimation(ta);
        set.addAnimation(ra);
        set.setDuration(2000);
        mIv.startAnimation(set);

    }
}
