package com.example.admin.erp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

/**
 * Created by admin on 2017/3/28.
 */

public class RecordPager3 extends Fragment {
    private LineChart mCombinedChart;
    private CombinedBarChartUtil mCombinedChartUtil;
    //第一个值：节点 第二个值：起始值 第三个值：结束值
    private final int mCount = 12;
    private final float minValue = 10;
    private final float maxValue = 100;

    //yVals1:蓝色折线的数据 //yVals2:灰色折线的数据
    private ArrayList<BarEntry> yVals1;
    private ArrayList<BarEntry> yVals2;
    ArrayList<String> xValues1,xValues2;
    ArrayList<Entry> yValues1,yValues2;
    public static final String[] mDateTime = new String[]{
            "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view2, null);

        mCombinedChart = (LineChart) view.findViewById(R.id.lineChart);
        setGrayValue();
        initData();

        return view;
    }


    /**
     * 初始化数据
     */
    private void initData() {
        mCombinedChartUtil = new CombinedBarChartUtil(getActivity());
        mCombinedChartUtil.setRule(mCount, minValue, maxValue);
        mCombinedChartUtil.setBackgroundColor(R.color.chart_color_2D2D2D);
        mCombinedChartUtil.setMianCombinedChart1(mCombinedChart, yValues1, yValues2);
    }

    /**
     * 【功能描述】  ：设置折线的数据
     * 【修改时间】  ：2016/3/17 14:37
     */
    private void setGrayValue() {

        xValues1 = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            xValues1.add(i+"");

        }

        xValues2 = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            xValues2.add(i+"");

        }
        // y轴的数据
        yValues1 = new ArrayList<Entry>();
        for (int i = 1; i < 13; i++) {
            float value = (float) (Math.random()*10);
            yValues1.add(new Entry(value, i-1));
            Log.d("tt",Float.toString(value));
        }

        yValues2 = new ArrayList<Entry>();
        for (int i = 1; i < 13; i++) {
            float value = (float) (Math.random()*10+50);
            yValues2.add(new Entry(value, i-1));
            Log.d("tt",Float.toString(value));
        }

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
