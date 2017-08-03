package com.qj.dimensadaptive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * @author mao
 * @version v1.0
 * @date 2017/6/8 13:22
 * @des dimens屏幕适配演示
 * @link http://blog.csdn.net/github_2011/article/details/72636851
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        mHeight = (TextView) findViewById(R.id.height);
    }

    private void initData() {
        int screenHeight = DimesUtil.getScreenHeight(this);
        int totalHeight = DimesUtil.getTotalHeight(this);
        int i = totalHeight - screenHeight;
        //int statusHeight = DimesUtil.getStatusHeight(this);
        StringBuilder sb = new StringBuilder();
        sb.append("总高度 : ");
        sb.append(totalHeight);
        sb.append("内容高度 : ");
        sb.append(screenHeight);
        sb.append("虚拟按键 : ");
        sb.append(i);
        //sb.append("状态栏高度 : ");
        //sb.append(statusHeight);
        Log.e(TAG, "initData: " + sb.toString());
        mHeight.setText(sb.toString());
    }

    private void initListener() {

    }
}
