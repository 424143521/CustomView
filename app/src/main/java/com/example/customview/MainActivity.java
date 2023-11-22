package com.example.customview;



import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private StarView startView;
    private ObjectAnimator anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyViewGroup myView = findViewById(R.id.myView);
        View arcView = findViewById(R.id.arcView);
        View squareView = findViewById(R.id.squareView);
        View text = findViewById(R.id.text);
        View button = findViewById(R.id.button);
        startView = findViewById(R.id.startView);

        //插值动画方式1
        // 创建一个从0到1的动画
        ValueAnimator interpolator = ValueAnimator.ofFloat(0, 1);
        interpolator.setDuration(4000); // 设置动画持续时间为1000毫秒

        // 使用自定义的插值器
        interpolator.setInterpolator(new ElasticityInterpolator());
        //这段代码是在动画过程中，通过添加一个`AnimatorUpdateListener`来监听动画的每一帧变化，并在每一帧更新时，获取当前的动画值，并将这个值设置为`text`的透明度。
        interpolator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 在动画过程中，获取当前的值
                float currentValue = (float) animation.getAnimatedValue();
                button.setAlpha(currentValue);
                button.setTranslationX(currentValue*700);
            }
        });
        interpolator.start(); // 开始播放动画
        //插值动画方式2



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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        anim = ObjectAnimator.ofFloat(startView, "y",startView.getY(), 200);
        anim.setDuration(3000);
        anim.setInterpolator(new CustomInterpolator());

        anim.start();
    }

}
