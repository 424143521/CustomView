package com.example.customview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class ArcView extends View {

    private Paint arcPaint;
    private Paint textPaint;
    private String text;
    private RectF rectF;
    private float progress =0;

    public ArcView(Context context) {
        this(context, null);
    }

    public ArcView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        arcPaint = new Paint();
        arcPaint.setAntiAlias(true);
        arcPaint.setStrokeWidth(20);
        arcPaint.setColor(0xFF0000FF); // 设置画笔颜色为蓝色
        arcPaint.setStyle(Paint.Style.STROKE); // 设置画笔样式为填充

        textPaint = new Paint();
        textPaint.setTextSize(50); // 设置文字大小
        textPaint.setColor(0xFFFFFF00);
        textPaint.setStrokeWidth(10);
        textPaint.setTextAlign(Paint.Align.CENTER); // 设置文字居中
        rectF = new RectF(100, 100, 300, 300); // 设置弧形的外接矩形区域
    }
    //自定义属性要做动画需要给自定义View添加setter方法
    public float getProgress(){
        invalidate();//通知重绘
        return progress;
    }
    public void setProgress(float progress) {
        this.progress = progress;
        invalidate(); // 调用invalidate方法使得View重新绘制
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
        text = String.valueOf((int)(progress/360*100))+"%"; // 将progress转换为字符串
        canvas.drawText(text, rectF.centerX(), rectF.centerY(), textPaint); // 在圆弧的中心位置绘制文字
        canvas.drawArc(rectF, 20, progress, false, arcPaint); // 绘制弧形
    }
}
