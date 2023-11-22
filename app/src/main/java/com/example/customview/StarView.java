package com.example.customview;



import android.content.Context;

import android.graphics.Canvas;

import android.graphics.Color;

import android.graphics.Paint;

import android.graphics.Path;

import android.util.AttributeSet;

import android.view.View;

public class StarView extends View {
    private Paint mPaint;
    private Path mPath;

    public StarView(Context context) {
        this(context, null);
    }

    public StarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.YELLOW);
        mPaint.setStyle(Paint.Style.FILL);
        mPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();
        float radius = Math.min(width, height) / 2.0f;
        float centerX = getPaddingLeft() + width / 2.0f;
        float centerY = getPaddingTop() + height / 2.0f;
        mPath.reset();
        for (int i = 0; i < 5; i++) {
            float x = (float) (centerX + radius * Math.sin(i * 2 * Math.PI / 5));
            float y = (float) (centerY - radius * Math.cos(i * 2 * Math.PI / 5));
            if (i == 0) {
                mPath.moveTo(x, y);
            } else {
                mPath.lineTo(x, y);
            }
        }
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }
}