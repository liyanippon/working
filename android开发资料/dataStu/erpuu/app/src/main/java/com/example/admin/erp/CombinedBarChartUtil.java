package com.example.admin.erp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/3/22.
 */

public class CombinedBarChartUtil  extends BaseChartUtil {
    private Context mContext;
    private int backgroundColor;
    private int mCount;
    private float minValue;
    private float maxValue;

    public CombinedBarChartUtil(Context context) {
        this.mContext = context;
    }

    /**
     * Bar Chart  工具类
     * 2016--01--29
     * Demo中主要在listview中显示折线图
     * 第一个参数：节点 第二个参数：起始值 第三个参数：结束值
     *
     * @param mCount
     * @param minValue
     * @param maxValue
     */
    public void setRule(int mCount, float minValue, float maxValue) {
        this.mCount = mCount;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public void setMianCombinedChart(BarChart mCombinedChart, ArrayList<BarEntry> lineEntries, ArrayList<BarEntry> barEntries) {
        // 图表为空时显示
        mCombinedChart.setNoDataTextDescription("暂时没有数据进行图表展示");
        mCombinedChart.setDescription("进账出账统计图");
        mCombinedChart.setDescriptionColor(Color.WHITE);
        mCombinedChart.setDescriptionPosition(400f,50f);
        mCombinedChart.setDescriptionTextSize(16);
        mCombinedChart.setBackgroundColor(Color.WHITE);
        mCombinedChart.setDrawBorders(false);//是否画边框
        mCombinedChart.setDrawGridBackground(false);
        mCombinedChart.setDrawBarShadow(false);
        mCombinedChart.setScaleYEnabled(false);
        mCombinedChart.setDoubleTapToZoomEnabled(false);
        mCombinedChart.setBackgroundColor(getColor(mContext, backgroundColor));
        mCombinedChart.animateX(3000); // 立即执行的动画,x轴
        List<String> list =new ArrayList<>();
        list.add("甲");
        list.add("乙");
        Legend mLegend = mCombinedChart.getLegend(); // 设置比例图标示
        mLegend.setForm(Legend.LegendForm.SQUARE);// 样式
        mLegend.setFormSize(6f);// 字体
        mLegend.setTextColor(Color.WHITE);// 颜色
        mLegend.setComputedLabels(list);
        mLegend.setXOffset(10);
        mLegend.setYOffset(10);
        //         * 获取头部信息         *//*
        //setLegend(mCombinedChart.getLegend(), true); 千万不要使用
        //*          *Y轴 右侧数据设置     *//*
        setyAxis(mCombinedChart.getAxisRight(), false);
        //*          *Y轴 左侧数据设置     *//*
        setLeftYAxis(mCombinedChart.getAxisLeft(), true, maxValue, minValue);
        //*          *X轴    数据设置      *//*
        setxAxis(mCombinedChart.getXAxis(), true);
        //*           *设置数据            */
        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(generateBarData(barEntries));
        dataSets.add(generateBarData1(lineEntries));//设置折现图数据
        BarData data = new BarData(ZuiHaooActivity.mDateTime, dataSets);
        data.setValueTextSize(10f);
        data.setGroupSpace(200f);//调节柱状图粗细 值越大越细
        mCombinedChart.setData(data);
        mCombinedChart.setDrawHighlightArrow(true);
        mCombinedChart.invalidate();
    }


    public void setMianCombinedChart1(LineChart mCombinedChart, ArrayList<Entry> lineEntries, ArrayList<Entry> barEntries) {

        // 图表为空时显示
        mCombinedChart.setNoDataTextDescription("暂时没有数据进行图表展示");
        mCombinedChart.setDescription("进账出账统计图");
        mCombinedChart.setDescriptionColor(Color.WHITE);
        mCombinedChart.setDescriptionPosition(400f,50f);
        mCombinedChart.setDescriptionTextSize(16);
        mCombinedChart.setBackgroundColor(Color.WHITE);
        mCombinedChart.setDrawBorders(false);//是否画边框
        mCombinedChart.setDrawGridBackground(false);
        //mCombinedChart.setDrawBarShadow(false);
        mCombinedChart.setScaleYEnabled(false);
        mCombinedChart.setDoubleTapToZoomEnabled(false);
        mCombinedChart.setBackgroundColor(getColor(mContext, backgroundColor));
        //         * 获取头部信息
        //setLegend(mCombinedChart.getLegend(), true); 千万不要使用
        //          *Y轴 右侧数据设置
        setyAxis(mCombinedChart.getAxisRight(), false);
        //          *Y轴 左侧数据设置
        setLeftYAxis(mCombinedChart.getAxisLeft(), true, maxValue, minValue);
        //         *X轴    数据设置
        setxAxis(mCombinedChart.getXAxis(), true);
        /*           *设置数据            */
        mCombinedChart.animateX(3000); // 立即执行的动画,x轴

        List<String> list =new ArrayList<>();
        list.add("甲");//有用
        list.add("乙");
        Legend mLegend = mCombinedChart.getLegend(); // 设置比例图标示，就是那个一组y的value的
        mLegend.setForm(Legend.LegendForm.SQUARE);// 样式
        mLegend.setFormSize(6f);// 字体
        mLegend.setTextColor(Color.WHITE);// 颜色
        mLegend.setComputedLabels(list);
        mLegend.setXOffset(10);
        mLegend.setYOffset(10);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        //dataSets.add(generateLineDatas(lineEntries,barEntries));
        //dataSets.add(generateLineDatas(barEntries));//设置折现图数据
        //LineData data = new LineData(ZheeActivity.mDateTime, dataSets);
        LineData data = generateLineDatas(lineEntries,barEntries);
        data.setValueTextSize(10f);
        //data.setGroupSpace(200f);//调节柱状图粗细 值越大越细
        mCombinedChart.setData(data);
        //mCombinedChart.setDrawHighlightArrow(true);
        mCombinedChart.invalidate();


    }


    private LineData generateLineDatas(ArrayList<Entry> entries,ArrayList<Entry> entries1) {
        LineData d = new LineData();
        ArrayList<String> xValues = new ArrayList<String>();
        for (int i=1;i<13;i++){
            d.addXValue(i+"月");
        }
        //ILineDataSet d = new LineData();
        LineDataSet set = new LineDataSet(entries, "甲");
        LineDataSet set1 = new LineDataSet(entries1, "乙");

        set.setColor(getColor(mContext, R.color.chart_color_8E8E8E));
        set.setCircleColor(getColor(mContext, R.color.chart_color_8E8E8E));
        set.setLineWidth(1f);
        set.setCircleSize(1f);
        set.setDrawValues(false);
        set.setHighlightEnabled(true); //设置十字线 是否启用
        set.setHighLightColor(Color.TRANSPARENT);
        set.setAxisDependency(YAxis.AxisDependency.RIGHT);

        set1.setColor(getColor(mContext, R.color.chart_color_FFCE55));
        set1.setCircleColor(getColor(mContext, R.color.chart_color_FFCE55));
        set1.setLineWidth(1f);
        set1.setCircleSize(1f);
        set1.setDrawValues(false);
        set1.setHighlightEnabled(true); //设置十字线 是否启用
        set1.setHighLightColor(Color.TRANSPARENT);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setVisible(true);

        d.addDataSet(set);
        d.addDataSet(set1);

        return d;
    }

    private BarDataSet generateBarData(ArrayList<BarEntry> entries) {
        BarDataSet set = new BarDataSet(entries, "甲");
        set.setValueTextSize(18);
        set.setColor(getColor(mContext, R.color.color_login_079752));
        set.setValueTextColor(getColor(mContext, R.color.color_login_079752));
        set.setDrawValues(false);
        set.setHighlightEnabled(true); //设置十字线 是否启用
        set.setHighLightColor(getColor(mContext, R.color.color_login_079752));
        set.setHighLightAlpha(50);
        set.setVisible(true);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        return set;
    }

    private BarDataSet generateBarData1(ArrayList<BarEntry> entries) {
        BarDataSet set = new BarDataSet(entries, "乙");
        set.setColor(getColor(mContext, R.color.chart_color_234C68));
        set.setValueTextColor(getColor(mContext, R.color.chart_color_234C68));
        set.setDrawValues(false);
        set.setHighlightEnabled(true); //设置十字线 是否启用
        set.setHighLightColor(getColor(mContext, R.color.chart_color_234C68));
        set.setHighLightAlpha(50);
        set.setAxisDependency(YAxis.AxisDependency.RIGHT);
        set.setVisible(true);
        Log.d("test","set"+set.getLabel().toString());
        return set;
    }

    public void setBackgroundColor(int color) {
        this.backgroundColor = color;
    }
}
