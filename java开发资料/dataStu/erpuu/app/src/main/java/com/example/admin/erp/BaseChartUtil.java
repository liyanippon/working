package com.example.admin.erp;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;

/**
 * Created by admin on 2017/3/22.
 */

public class BaseChartUtil {
    protected boolean mAxisLeft;
    protected boolean mAxisRight;
    protected boolean mXAxis;
    protected boolean mYAxis;

    protected String[] mMonths = new String[]{
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };


    protected String[] mParties = new String[]{
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };


    protected int[] color = {Color.TRANSPARENT, Color.TRANSPARENT};

    protected String[] lable = {"进账", "出账"};

    /**
     * 设置 左边轴 是否显示
     *
     * @param axisLeft
     * @return
     */
    public void setAxisLeft(boolean axisLeft) {
        this.mAxisLeft = axisLeft;
    }

    /**
     * 设置 右边轴 是否显示
     *
     * @param axisRight
     * @return
     */
    public void setAxisRight(boolean axisRight) {
        this.mAxisRight = axisRight;
    }

    /**
     * 设置 x表格是否显示
     *
     * @param xAxis
     * @return
     */
    public void setXAxis(boolean xAxis) {
        this.mXAxis = xAxis;
    }

    /**
     * 设置 y表格是否显示
     *
     * @param yAxis
     * @return
     */
    public void setYAxis(boolean yAxis) {
        this.mYAxis = yAxis;
    }


    /**
     * 设置表头样式    这个是折线表头样式
     *
     * @param l
     * @param LegendEnabled 是否显示  false：不显示   true：显示
     */


    public void setLegend(Legend l, boolean LegendEnabled) {
        if (LegendEnabled) {
            l.setTextColor(Color.TRANSPARENT);
            l.setCustom(color, lable);
            l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
            l.setForm(Legend.LegendForm.LINE);
        }
        l.setEnabled(LegendEnabled);
    }

    /**
     * /设置左边样式
     *
     * @param leftAxis
     * @param yAxisEnabled false：不显示   true：显示
     * @param maxValue     最大显示值
     * @param minValue     最小显示值
     */
    public void setLeftYAxis(YAxis leftAxis, boolean yAxisEnabled, float maxValue, float minValue) {
        if (yAxisEnabled) {
            leftAxis.setStartAtZero(false);

            //leftAxis.setAxisMaxValue(maxValue * CustomConstant.SET_MAXVALUE);
            //leftAxis.setAxisMinValue(minValue * CustomConstant.SET_MINVALUE);
            leftAxis.setAxisMaxValue(maxValue);
            leftAxis.setAxisMinValue(minValue);
            leftAxis.setTextColor(Color.WHITE);
            leftAxis.setDrawAxisLine(true);
            //颠倒显示
            //leftAxis.setInverted(true);
            //显示Y轴的坐标数 true:按平均值  false:按整数 y-axis max = 25, min = 2, default: 6,
            leftAxis.setLabelCount(6, true);
        }
        leftAxis.setEnabled(yAxisEnabled);
    }


    /**
     * /设置x 表格
     *
     * @param yAxis
     * @param yAxisEnabled false：不显示   true：显示
     */
    public void setyAxis(YAxis yAxis, boolean yAxisEnabled) {
        if (yAxisEnabled) {
            yAxis.setDrawAxisLine(true);
            yAxis.setDrawGridLines(true);
        }
        yAxis.setEnabled(yAxisEnabled);
    }

    /**
     * /设置X 日期样式
     *
     * @param xAxis
     * @param xAxisEnabled false：不显示   true：显示
     */
    public void setxAxis(XAxis xAxis, boolean xAxisEnabled) {
        if (xAxisEnabled) {
            xAxis.setTextColor(Color.WHITE);
            xAxis.setDrawGridLines(false);
            xAxis.setAvoidFirstLastClipping(true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        }
        xAxis.setEnabled(xAxisEnabled);
    }


    /**
     * 设置数据
     */
//    public abstract void setData();
    public LineData setLineData(LineData lineData, boolean lineDataEnabled) {
        if (lineDataEnabled) {
            lineData.setValueTextColor(Color.WHITE);
            lineData.setValueTextSize(9f);
            // 设置折线上显示数据的格式。如果不设置，将默认显示float数据格式。
//            lineData.setValueFormatter(new ValueFormatter() {
//                @Override
//                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                    int n = (int) value;
//                    String str = n + "℃";
//                    return str;
//                }
//            });
        }
        lineData.setDrawValues(lineDataEnabled);
        return lineData;
    }


    /**
     * 设置颜色
     *
     * @param context
     * @param color
     * @return
     */
    public int getColor(Context context, int color) {
        if (context != null && color != 0) {
            return context.getResources().getColor(color);
        }
        return 0x00ffffff;
}}
