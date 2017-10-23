package Tool.StatisticalGraph;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.example.admin.erp.R;
import com.github.mikephil.charting.charts.BarChart;
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

import Tool.statistics.Statics;

/**
 * Created by admin on 2017/3/22.
 */

public class CombinedBarChartUtil  extends BaseChartUtil {
    private Context mContext;
    private int backgroundColor;
    private int mCount;
    private float minValue;
    private float maxValue;

    public String[] mDateTime = new String[]{
            "1月", "2月", "3月", "4月", "5月",
            "6月", "7月", "8月", "9月", "10月",
            "11月", "12月"};

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

    public void setMianCombinedChart(BarChart mCombinedChart, ArrayList<BarEntry> jinEntries, ArrayList<BarEntry> chuEntries ,List<String> list,String description) {
        // 图表为空时显示
        mCombinedChart.setNoDataTextDescription("暂时没有数据进行图表展示");
        mCombinedChart.setDescription(description);
        mCombinedChart.setDescriptionColor(Color.WHITE);
        mCombinedChart.setDescriptionPosition(400f,80f);
        /*if(Statics.yPositon) {//以后如果描述改变需求在此处更改
            mCombinedChart.setDescriptionPosition(400f,80f);
            Statics.yPositon = false;
        }*/
        mCombinedChart.setDescriptionTextSize(16);
        mCombinedChart.setBackgroundColor(Color.WHITE);
        mCombinedChart.setDrawBorders(false);//是否画边框
        mCombinedChart.setDrawGridBackground(false);
        mCombinedChart.setDrawBarShadow(false);
        mCombinedChart.setScaleYEnabled(false);
        mCombinedChart.setDoubleTapToZoomEnabled(false);
        mCombinedChart.setBackgroundColor(getColor(mContext, backgroundColor));
        mCombinedChart.animateX(3000); // 立即执行的动画,x轴
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
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();

        if(list.get(1)!=null){
            dataSets.add(generateBarData(jinEntries,list));
            for (int i=0;i<list.size();i++){
                Log.d("list","图例:"+list.get(i));
            }
            Log.d("broadcast","dataset");
        }

        dataSets.add(generateBarData1(chuEntries,list));
        Log.d("broadcast","是否按天统计"+Boolean.toString(Statics.dayCount));
        if(Statics.dayCount&&null!=Statics.Xday){
            mDateTime = Statics.Xday;
            Statics.dayCount = false;

        }else{
            Statics.dayCount = false;

        }

        if(Statics.personCount){
            mDateTime = Statics.xPerson;
            Statics.personCount=false;
            for (int i=0;i<Statics.xPerson.length;i++){
            Log.d("person",Statics.xPerson[i]);
            }
        }

        BarData data = new BarData(mDateTime, dataSets);
        data.setValueTextSize(10f);
        data.setGroupSpace(200f);//调节柱状图粗细 值越大越细
        mCombinedChart.setData(data);
        mCombinedChart.setDrawHighlightArrow(true);
        mCombinedChart.invalidate();
    }

    //横向柱形图
    public void setMianCombinedChart2(BarChart mCombinedChart, ArrayList<BarEntry> lineEntries, ArrayList<BarEntry> barEntries ,List<String> list,String description) {
        // 图表为空时显示
        Log.d("CombinedBarChartUtil", "描述：" + description+"空：："+Boolean.toString(mCombinedChart==null));
        mCombinedChart.setNoDataTextDescription("暂时没有数据进行图表展示");
        mCombinedChart.setDescription(description);
        mCombinedChart.setDescriptionColor(Color.WHITE);
        mCombinedChart.setDescriptionPosition(400f,80f);
        /*if(Statics.yPositon) {//以后如果描述改变需求在此处更改
            mCombinedChart.setDescriptionPosition(400f,80f);
            Statics.yPositon = false;
        }*/
        if(Statics.yPositon){
            mCombinedChart.setScaleXEnabled(false);//启用/禁用缩放在x轴上。
            mCombinedChart.setScaleYEnabled(true);//启用/禁用缩放在y轴。
        }
        mCombinedChart.setDescriptionTextSize(16);
        mCombinedChart.setBackgroundColor(Color.WHITE);
        mCombinedChart.setDrawBorders(false);//是否画边框
        mCombinedChart.setDrawGridBackground(false);
        mCombinedChart.setDrawBarShadow(false);
        mCombinedChart.setDoubleTapToZoomEnabled(false);
        mCombinedChart.setBackgroundColor(getColor(mContext, backgroundColor));
        mCombinedChart.animateX(3000); // 立即执行的动画,x轴
        /*
        mCombinedChart.setTouchEnabled(true);//启用/禁用与图表的所有可能的触摸交互。
        mCombinedChart.setDragEnabled(true);//启用/禁用拖动（平移）图表。
        mCombinedChart.setScaleEnabled(true);//启用/禁用缩放图表上的两个轴。
        mCombinedChart.setScaleXEnabled(false);//启用/禁用缩放在x轴上。
        mCombinedChart.setScaleYEnabled(true);//启用/禁用缩放在y轴。
        mCombinedChart.setPinchZoom(true);//如果设置为true，捏缩放功能。 如果false，x轴和y轴可分别放大。
        mCombinedChart.setDoubleTapToZoomEnabled(true);//设置为false以禁止通过在其上双击缩放图表。
        mCombinedChart.setHighlightPerDragEnabled(true);//设置为true，允许每个图表表面拖过，当它完全缩小突出。 默认值：true
        mCombinedChart.setHighlightPerTapEnabled(true);//设置为false，以防止值由敲击姿态被突出显示。 值仍然可以通过拖动或编程方式突出显示。 默认值：true*/

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
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        if(list.get(1)!=null){
            dataSets.add(generateBarData(barEntries,list));
            for (int i=0;i<list.size();i++){
                Log.d("list","图例:"+list.get(i));
            }
            Log.d("broadcast","dataset");
        }
        dataSets.add(generateBarData1(lineEntries,list));
        Log.d("broadcast","是否按天统计"+Boolean.toString(Statics.dayCount));
        if(Statics.dayCount&&null!=Statics.Xday){
            mDateTime = Statics.Xday;
            Statics.dayCount = false;
        }else{
            Statics.dayCount = false;
        }

        if(Statics.personCount){
            mDateTime = Statics.xPerson;
            Statics.personCount=false;
            for (int i=0;i<Statics.xPerson.length;i++){
                Log.d("person",Statics.xPerson[i]);
            }
        }
        Log.v("mDateTime","mDateTime:"+mDateTime.length+"");

        BarData data = new BarData(mDateTime, dataSets);
        data.setValueTextSize(10f);
        data.setGroupSpace(200f);//调节柱状图粗细 值越大越细
        mCombinedChart.setData(data);
        mCombinedChart.setDrawHighlightArrow(true);
        mCombinedChart.invalidate();
    }

    public void setMianCombinedChart1(LineChart mCombinedChart, ArrayList<Entry> lineEntries, ArrayList<Entry> barEntries ,List<String> list,String description) {

        // 图表为空时显示
        mCombinedChart.setNoDataTextDescription("暂时没有数据进行图表展示");
        mCombinedChart.setDescription(description);
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

        LineData data = generateLineDatas(lineEntries,barEntries,list);
        data.setValueTextSize(10f);
        //data.setGroupSpace(200f);//调节柱状图粗细 值越大越细
        mCombinedChart.setData(data);
        //mCombinedChart.setDrawHighlightArrow(true);
        mCombinedChart.invalidate();


    }

    private LineData generateLineDatas(ArrayList<Entry> entries,ArrayList<Entry> entries1,List<String> list) {
        LineData d = new LineData();
        ArrayList<String> xValues = new ArrayList<String>();

        if(Statics.dayCount&&Statics.Xday!=null){
            mDateTime = Statics.Xday;
            for (int i=1;i<Statics.Xday.length+1;i++){
                d.addXValue(Statics.Xday[i-1]);
            }
            Statics.dayCount = false;

        }else{
            for (int i=1;i<13;i++){
                d.addXValue(i+"月");
            }
            Statics.dayCount = false;
        }

        if(Statics.yPositon){
            d=new LineData();
            d.clearValues();
            for (int i=0;i<Statics.xPerson.length;i++){
                d.addXValue(Statics.xPerson[i]);
            }
            Statics.yPositon=false;
        }

        //ILineDataSet d = new LineData();
        LineDataSet set=null;
        LineDataSet set1=null;
        if(list.get(1)!=null){
             set1 = new LineDataSet(entries1, list.get(1));
        }
        set = new LineDataSet(entries, list.get(0));
        set.setColor(getColor(mContext, R.color.chart_color_8E8E8E));//chart_color_8E8E8E
        set.setCircleColor(getColor(mContext, R.color.chart_color_8E8E8E));
        set.setLineWidth(1f);
        set.setCircleSize(1f);
        set.setDrawValues(false);
        set.setHighlightEnabled(true); //设置十字线 是否启用
        set.setHighLightColor(Color.TRANSPARENT);
        //set.setAxisDependency(YAxis.AxisDependency.RIGHT);
        if(list.get(1)!=null) {
            set1.setColor(getColor(mContext, R.color.chart_color_FFCE55));
            set1.setCircleColor(getColor(mContext, R.color.chart_color_FFCE55));
            set1.setLineWidth(1f);
            set1.setCircleSize(1f);
            set1.setDrawValues(false);
            set1.setHighlightEnabled(true); //设置十字线 是否启用
            set1.setHighLightColor(Color.TRANSPARENT);
            //set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setVisible(true);
            d.addDataSet(set1);
        }

        d.addDataSet(set);


        return d;
    }

    private BarDataSet generateBarData(ArrayList<BarEntry> entries,List<String> list) {
        if(entries==null) {
            return null;
        }
        String PictureTag ;
        if (list!=null){
            PictureTag = list.get(1);
        }else{
            PictureTag = "";
        }
        BarDataSet set = new BarDataSet(entries,PictureTag );
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

    private BarDataSet generateBarData1(ArrayList<BarEntry> entries,List<String> list) {
        String PictureTag ;
        if (list!=null){
            PictureTag = list.get(0);
        }else{
            PictureTag = "";
        }
        BarDataSet set = new BarDataSet(entries, PictureTag);
        set.setValueTextSize(18);
        set.setColor(getColor(mContext, R.color.chart_color_234C68));
        set.setValueTextColor(getColor(mContext, R.color.chart_color_234C68));
        set.setDrawValues(false);
        set.setHighlightEnabled(true); //设置十字线 是否启用
        set.setHighLightColor(getColor(mContext, R.color.chart_color_234C68));//chart_color_8E8E8E
        set.setHighLightAlpha(50);
        set.setVisible(true);
        //set.setAxisDependency(YAxis.AxisDependency.RIGHT); //最好不要使用
        //Log.d("test","set"+set.getLabel().toString());
        return set;
    }
    public void setBackgroundColor(int color) {
        this.backgroundColor = color;
    }
}
