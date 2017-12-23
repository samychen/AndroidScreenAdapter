package com.samychen.gracefulwrapper.androidscreenadapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.samychen.gracefulwrapper.androidscreenadapter.percentlayout.Main2Activity;

public class MainActivity extends AppCompatActivity {

    private TextView txt1;
    private TextView txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        //这里是第二次绘制，第一次是xml布局绘制，可以考虑自定义组件在onDraw里面实现绘制，比如PercentRelativeLayout
        ViewCalculateUtil.setViewLinearLayoutParam(txt1,1040,80,10,0,20,20);
        ViewCalculateUtil.setViewLinearLayoutParam(txt2,400,400,50,0,0,0);
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });
    }
}
