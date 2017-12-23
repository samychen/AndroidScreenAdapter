package com.samychen.gracefulwrapper.androidscreenadapter.percentlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.samychen.gracefulwrapper.androidscreenadapter.R;

/**
 * Created by samychen on 2017/12/23 0023.
 * 我的github地址 https://github.com/samychen
 */

public class PercentRelativeLayout extends RelativeLayout {

    public PercentRelativeLayout(Context context) {
        super(context);
    }

    public PercentRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PercentRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int height = View.MeasureSpec.getSize(heightMeasureSpec);
        //测量子控件的宽搞进行改变
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
            //把已经得到的布局参数进行更改
            float widthPercent = 0;
            float heightPercent = 0;
            if (layoutParams instanceof PercentRelativeLayout.LayoutParams){
                //获取到布局上的内容
                widthPercent = ((LayoutParams)layoutParams).getWidthPercent();
                heightPercent = ((LayoutParams)layoutParams).getHeightPercent();
            }
            if(widthPercent>0){
                layoutParams.width = (int)(width*widthPercent);
            }
            if (heightPercent>0){
                layoutParams.height = (int)(height*heightPercent);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
    //需要报自定义的属性封装进去

    @Override
    public RelativeLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        //返回自己设置好的布局参数
        return new LayoutParams(getContext(),attrs);
    }
    public static class LayoutParams extends RelativeLayout.LayoutParams{

        public float getWidthPercent() {
            return widthPercent;
        }

        public void setWidthPercent(float widthPercent) {
            this.widthPercent = widthPercent;
        }

        public float getHeightPercent() {
            return heightPercent;
        }

        public void setHeightPercent(float heightPercent) {
            this.heightPercent = heightPercent;
        }

        private float widthPercent;
        private float heightPercent;

        /**
         * 把自定义属性加入
         * @param c
         * @param attrs
         */
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray array = c.obtainStyledAttributes(attrs, R.styleable.PercentRelativeLayout);
            widthPercent = array.getFloat(R.styleable.PercentRelativeLayout_layout_widthPrecent,this.getWidthPercent());
            heightPercent = array.getFloat(R.styleable.PercentRelativeLayout_layout_heightPrecent,this.getHeightPercent());
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(RelativeLayout.LayoutParams source) {
            super(source);
        }
    }
}
