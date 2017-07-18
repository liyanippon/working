package com.example.admin.erp;

import android.graphics.Color;
import android.icu.util.RangeValueIterator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

public class SuangZheActivity extends AppCompatActivity {
    private final float minValue = 50000;
    private final float maxValue = 100000;


    /*public static final String[] mDateTime = new String[]{
            "1月", "2月", "3月", "4月", "5月",
            "6月", "7月", "8月", "9月", "10月",
            "11月", "12月"};*/
    private List<String> mDateTime;
    private List<Integer> type;
    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suang_zhe);

        mDateTime=new ArrayList<>();
        mDateTime.add("1月");
        mDateTime.add("2月");
        mDateTime.add("3月");
        mDateTime.add("4月");
        mDateTime.add("5月");
        mDateTime.add("6月");
        mDateTime.add("7月");
        mDateTime.add("8月");
        mDateTime.add("9月");
        mDateTime.add("10月");
        mDateTime.add("11月");
        mDateTime.add("12月");

        type =new ArrayList<>();
        type.add(1);
        type.add(2);

        List<List<Entry>> yValsList=new ArrayList<>() ;
        Entry entry =new Entry((float) 500.2,1);
        List<Entry> list =new ArrayList<>();
        list.add(entry);
        yValsList.add(list);

        CombinedLineChartUtil mLineChartUtil = new CombinedLineChartUtil(SuangZheActivity.this);
        mLineChartUtil.setRule(minValue, maxValue);//设置最小值和最大值
        mLineChartUtil.setDateTime(mDateTime);//设置日期
        mLineChartUtil.setTypes(type);  //显示多根线
        mLineChartUtil.setCombinedLineChart(lineChart, yValsList);//设置数据进行展示
        // /*     * Chart 中设置图表中最多显示的节点数     */
        lineChart.setVisibleXRangeMinimum(12);
    }
}
