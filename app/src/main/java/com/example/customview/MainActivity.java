package com.example.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyViewGroup myView = findViewById(R.id.myView);
        View arcView = findViewById(R.id.arcView);
        View squareView = findViewById(R.id.squareView);
        View text = findViewById(R.id.text);


        //ViewPropertyAnimator实现
        squareView.animate().rotation(360).translationX(200).scaleX(2).scaleY(2).alpha(0.5f).setDuration(4000);
        text.animate().translationX(800).setDuration(4000);

        //ObjectAnimator实
        ObjectAnimator animator = ObjectAnimator.ofFloat(myView, "rotationY", 0.0f,180.0f).setDuration(2000);;//创建动画对象
        animator.start();//开始动画
        // 创建一个动画集合，将两个动画一起播放
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(arcView, "translationY", 200).setDuration(3000);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(arcView, "progress", 300).setDuration(4000);
        animatorSet.playTogether(animator2, animator3);
        animatorSet.start(); // 开始播放动画


    }
}