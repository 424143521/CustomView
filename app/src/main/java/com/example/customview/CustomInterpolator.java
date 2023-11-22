package com.example.customview;

import android.view.animation.Interpolator;
    public class CustomInterpolator implements Interpolator {
        @Override
        public float getInterpolation(float input) {
            if (input < 0.5) {
                // 在动画的前半段，我们让动画加速进行。这里我们简单地将进度平方，这样动画的速度就会随着进度的增加而增加。
                return input * input;
            } else {
                // 在动画的后半段，我们让动画减速进行。这里我们让进度先减半，然后再平方，这样动画的速度就会随着进度的增加而减小。
                input = input - 0.5f;
                return input * input + 0.5f;
            }
        }
    }

