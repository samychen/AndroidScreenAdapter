package com.samychen.gracefulwrapper.androidscreenadapter;

import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by samychen on 2017/12/23 0023.
 * 我的github地址 https://github.com/samychen
 */

public class ViewCalculateUtil {
    //获取调用层传入的值进行设置
    public static void setViewLinearLayoutParam(View view,int width,int height,int topMargin,int bottomMargin,int leftMargin,int rightMargin){
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (width!= LinearLayout.LayoutParams.MATCH_PARENT&&width!= LinearLayout.LayoutParams.WRAP_CONTENT){
            layoutParams.width = UIUtils.getInstance(SamyApplication.getInstance()).getWidth(width);
        } else {
            layoutParams.width = width;
        }
        if (height!= LinearLayout.LayoutParams.MATCH_PARENT&&height!= LinearLayout.LayoutParams.WRAP_CONTENT){
            layoutParams.height = UIUtils.getInstance(SamyApplication.getInstance()).getHeight(height);
        } else {
            layoutParams.height = height;
        }
        layoutParams.topMargin = UIUtils.getInstance(SamyApplication.getInstance()).getHeight(topMargin);
        layoutParams.bottomMargin = UIUtils.getInstance(SamyApplication.getInstance()).getHeight(bottomMargin);
        layoutParams.leftMargin = UIUtils.getInstance(SamyApplication.getInstance()).getWidth(leftMargin);
        layoutParams.rightMargin = UIUtils.getInstance(SamyApplication.getInstance()).getWidth(rightMargin);
        view.setLayoutParams(layoutParams);
    }
}
