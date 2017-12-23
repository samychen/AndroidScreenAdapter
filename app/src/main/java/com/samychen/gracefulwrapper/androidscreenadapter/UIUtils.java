package com.samychen.gracefulwrapper.androidscreenadapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by samychen on 2017/12/23 0023.
 * 我的github地址 https://github.com/samychen
 * 用来按UI基准值生成真实设备上需要的宽高
 */

public class UIUtils {
    private static UIUtils ourInstance;
    public static UIUtils getInstance(){
        return ourInstance;
    }
    public static UIUtils getInstance(Context context){
        if (ourInstance==null){
            ourInstance = new UIUtils(context);
        }
        return ourInstance;
    }
    public static final int STANDARD_WIDTH = 1080;
    //真实高度有48个像素高度状态栏
    public static final int STANDARD_HEIGHT = 1872;//1920 - 48;
    private static final String DIMEN_CLASS = "com.android.internal.R$dimen";
    //实际设备分辨率 480x800
    public float displayMetricsWidth;
    public float displayMetricsHeight;

    private UIUtils(Context context){
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (displayMetricsWidth==0.0F||displayMetricsHeight==0.0F){
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int systemBarHeight = getSystemBarHeight(context);
            //处理真正宽高
            if (displayMetrics.widthPixels>displayMetrics.heightPixels){
                //横屏
                this.displayMetricsWidth = (float) displayMetrics.heightPixels;
                this.displayMetricsHeight = (float)displayMetrics.widthPixels - systemBarHeight;
            } else {
                this.displayMetricsWidth = (float)displayMetrics.widthPixels;
                this.displayMetricsHeight = (float)displayMetrics.heightPixels;
            }

        }
    }

    private int getSystemBarHeight(Context context) {
        return getValue(context,DIMEN_CLASS,"system_bar_height",48);
    }

    /**
     *
     * @param context
     * @param dimenClass 安卓源码中找到的存放信息的类
     * @param system_bar_height
     * @param defValue 默认值
     * @return
     */
    private int getValue(Context context, String dimenClass, String system_bar_height, int defValue) {
        try {
            Class clazz = Class.forName(dimenClass);
            Object o = clazz.newInstance();
            Field field = clazz.getField(system_bar_height);
            int x = Integer.parseInt(field.get(o).toString());
            return context.getResources().getDimensionPixelOffset(x);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }
    //开始获取缩放以后的结果
    public float getWidth(float width){
        return width*this.displayMetricsWidth/1080.0F;
    }
    public float getHeight(float height){
        return height*this.displayMetricsHeight/1872.0F;
    }
    public int getWidth(int width){
        return (int) (width*this.displayMetricsWidth/1080.0F);
    }
    public int getHeight(int height){
        return (int) (height*this.displayMetricsHeight/1872.0F);
    }

}
