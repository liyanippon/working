package com.example.admin.erp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class ZuiHaooActivity extends AppCompatActivity {
    private BarChart mCombinedChart;
    private CombinedBarChartUtil mCombinedChartUtil;
    //第一个值：节点 第二个值：起始值 第三个值：结束值
    private final int mCount = 12;
    private final float minValue = 50000;
    private final float maxValue = 100000;

    //yVals1:蓝色折线的数据 //yVals2:灰色折线的数据
    private ArrayList<BarEntry> yVals1;
    private ArrayList<BarEntry> yVals2;

    public static final String[] mDateTime = new String[]{
            "1月", "2月", "3月", "4月", "5月",
            "6月", "7月", "8月", "9月", "10月",
            "11月", "12月"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zui_haoo);

        mCombinedChart = (BarChart) findViewById(R.id.barChart);
        setGrayValue();
        initData();

    }

    /**
     * 初始化数据
     */
    private void initData() {
        mCombinedChartUtil = new CombinedBarChartUtil(this);
        mCombinedChartUtil.setRule(mCount, minValue, maxValue);
        mCombinedChartUtil.setBackgroundColor(R.color.chart_color_2D2D2D);
        mCombinedChartUtil.setMianCombinedChart(mCombinedChart, yVals1, yVals2);
    }

    /**
     * 【功能描述】  ：设置折线的数据
     * 【修改时间】  ：2016/3/17 14:37
     */
    private void setGrayValue() {
        yVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i < mCount; i++) {
            yVals1.add(new BarEntry(getRandom(maxValue / 2, minValue), i, mDateTime[i]));
        }
        yVals2 = new ArrayList<BarEntry>();
        for (int i = 0; i < mCount; i++) {
            yVals2.add(new BarEntry(getRandom(maxValue / 2, minValue), i, mDateTime[i]));
        }
    }


    /**
     * 【功能描述】  ：产生随机数
     * 【参数列表】  ：range 范围
     * 【返回值】    ：返回产生的随机数
     * 【修改时间】  ：2016/3/17 14:40
     */
    private float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
    }
}
