package com.samychen.gracefulwrapper.androidscreenadapter.view;


import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.samychen.gracefulwrapper.androidscreenadapter.SamyApplication;
import com.samychen.gracefulwrapper.androidscreenadapter.UIUtils;

/**
 * Created by samychen on 2017/12/26 0026.
 * 我的github地址 https://github.com/samychen
 */

public class SamyTextView extends android.support.v7.widget.AppCompatTextView {
    private static String TAG = "SamyTextView";

    public SamyTextView(Context context) {
        super(context);
    }

    public SamyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SamyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "onMeasure: ");
        int width = measureDimension(getSuggestedMinimumWidth(), widthMeasureSpec,true);
        int height = measureDimension(getSuggestedMinimumHeight(), heightMeasureSpec,false);
        if (width ==0 || height == 0){
            super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        } else {
            setMeasuredDimension(width, height);
        }
    }
    public int measureDimension(int defaultSize, int measureSpec, boolean isWidth){
        int defSize = defaultSize;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);
        Log.d(TAG, "specSize计算前: "+isWidth+specSize);
        if (isWidth){
            specSize = UIUtils.getInstance(SamyApplication.getInstance()).getWidth(specSize);
            defSize = UIUtils.getInstance(SamyApplication.getInstance()).getWidth(defSize);
        } else {
            specSize = UIUtils.getInstance(SamyApplication.getInstance()).getHeight(specSize);
            defSize = UIUtils.getInstance(SamyApplication.getInstance()).getHeight(defSize);
        }
        Log.d(TAG, "specSize: 计算后"+isWidth+specSize);
        int result = defSize;
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED: //未指定
                Log.d(TAG, "UNSPECIFIED: ");
                result = defSize;
                break;
            case MeasureSpec.EXACTLY: //精确
                Log.d(TAG, "EXACTLY: ");//match_parent
            case MeasureSpec.AT_MOST: //至多
                Log.d(TAG, "AT_MOST: ");//wrap_content
                result = 0;
                break;
        }
        Log.d(TAG, "result=: "+result);
        return result;
    }

}
