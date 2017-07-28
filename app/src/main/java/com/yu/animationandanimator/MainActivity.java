package com.yu.animationandanimator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goAnimation(View view) {
        startActivity(new Intent(this, AnimationActivity.class));
    }

    public void goAnimator(View view) {
        startActivity(new Intent(this, AnimatorActivity.class));
    }
}
