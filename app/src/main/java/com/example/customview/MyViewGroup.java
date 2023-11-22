package com.example.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Package com.hc.studyview
 * Created by HuaChao on 2016/6/3.
 */
public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    /***
     * 获取子View中宽度最大的值
     */
    private int getMaxChildWidth() {
        int childCount = getChildCount();
        int maxWidth = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getMeasuredWidth() > maxWidth)
                maxWidth = childView.getMeasuredWidth();

        }

        return maxWidth;
    }

    /***
     * 将所有子View的高度相加
     **/
    private int getTotleHeight() {
        int childCount = getChildCount();
        int height = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            height += childView.getMeasuredHeight();

        }

        return height;
    }
//首先重写onMeasure，实现测量子View大小以及设定ViewGroup的大小：
    //int widthMeasureSpec, int heightMeasureSpec为父view对子view的宽度限制和高度限制
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //将所有的子View进行测量，这会触发每个子View的onMeasure函数
        //注意要与measureChild区分，measureChild是对单个view进行测量
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int childCount = getChildCount();

        if (childCount == 0) {//如果没有子View,当前ViewGroup没有存在的意义，不用占用空间
            setMeasuredDimension(0, 0);
        } else {

            //计算下宽度和高度
            //我们将高度设置为所有子View的高度相加，宽度设为子View中最大的宽度
            int height = getTotleHeight();
            int width = getMaxChildWidth();
            //把计算下宽度和高度以及父view的限制一起传进去，返回的结果为符合限制的修正的尺寸
            int resolvedWidth = resolveSize(width, widthMeasureSpec);
            int resolvedHeight = resolveSize(height, heightMeasureSpec);
           //把修正之后的尺寸存储起来
            setMeasuredDimension(resolvedWidth, resolvedHeight);

        }
    }


    //父view的限制是开发者写layout：match—parent这种属性时对子view对要求
    //子view如何遵守父view对限制，measureSpec为父view对限制模式和宽高度
    public static int resolveSize(int size, int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = size;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return result;

     }

    //摆放子view
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        //记录当前的高度位置
        int curHeight = 0;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int height = child.getMeasuredHeight();
            int width = child.getMeasuredWidth();

            child.layout(0, curHeight, l + width, curHeight + height);
            curHeight += height;

        }
    }


}
