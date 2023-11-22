package com.example.customview;
import android.view.animation.Interpolator;
public class ElasticityInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float input) {
        if (input <= 0.5) {
            return input * 2;
        } else if (input <= 0.6f) {
            return 1 + (input - 0.5f) * 2;
        } else if (input <= 0.7) {
            return 1.2f - ((input - 0.6f) * 3);
        } else if (input <= 0.8) {
            return 0.9f + ((input - 0.7f) * 2);
        } else if (input <= 0.9) {
            return 1.1f - ((input - 0.8f) * 2);
        } else if (input <= 1) {
            return input;
        }
        return 0;

    }
}