package com.yu.animationandanimator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

public class AnimatorActivity extends AppCompatActivity {
    private ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        mIv = (ImageView) findViewById(R.id.id_iv);
    }

    public void onViewPropertyAnimator(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mIv.animate().setDuration(2000)
                    .alpha(0.5f)
                    .rotation(0.5f)
                    .scaleX(1.5f)
                    .translationX(50f)
                    .setInterpolator(new OvershootInterpolator())
                    .setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {  // api>19
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {

                }
            });
        }
    }

    public void onObjectAnimator(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mIv, "scaleX", 1, 0.5f).setDuration(2000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {  // 可选的监听
            @Override
            public void onAnimationPause(Animator animation) {
                super.onAnimationPause(animation);
            }
        });
        animator.start();
    }

    public void onValueAnimator(View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1, 0.5f).setDuration(2000);
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                mIv.setScaleY(fraction*2);
                mIv.setAlpha(fraction);
                mIv.setTranslationX(fraction*200);
            }
        });
        valueAnimator.start();
    }

    public void onPropertyValuesHolder(View view) {
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1, 0.5f);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 1, 0.3f);
        PropertyValuesHolder rotate = PropertyValuesHolder.ofFloat("rotationY", 0, 180);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mIv,scaleX, alpha, rotate);
        animator.setDuration(2000);
        animator.start();

    }

    public void onAnimatorSet(View view) {
        AnimatorSet set = new AnimatorSet();
        final ObjectAnimator scaleX = ObjectAnimator.ofFloat(mIv, "scaleX", 1, 0.5f, 1);
        final ObjectAnimator scaleY = ObjectAnimator.ofFloat(mIv, "scaleY", 1, 0.5f, 1);
        final ObjectAnimator translationX = ObjectAnimator.ofFloat(mIv, "translationX", 0, 100, 0);
        final ObjectAnimator translationY = ObjectAnimator.ofFloat(mIv, "translationY", 0, 100, 0);
        final ObjectAnimator rotationX = ObjectAnimator.ofFloat(mIv, "rotationX", 0, 360);
        final ObjectAnimator rotationY = ObjectAnimator.ofFloat(mIv, "rotationY", 0, 360);
        final ObjectAnimator alpha = ObjectAnimator.ofFloat(mIv, "alpha", 1, 0.5f, 1);
//        set.playTogether(scaleX, scaleY, translationX, translationY, rotationX, rotationY, alpha);  //  一起
//        set.playSequentially(scaleX, scaleY, translationX, translationY, rotationX, rotationY, alpha); // 顺序
        set.play(scaleX).with(scaleY).with(translationX).with(translationY).before(rotationX);
        set.play(rotationX).with(rotationY).after(alpha);  // 按指定顺序

        set.setDuration(2000).start();
    }
}
