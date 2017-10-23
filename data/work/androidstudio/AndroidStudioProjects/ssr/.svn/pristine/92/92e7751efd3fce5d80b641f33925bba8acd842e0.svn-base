package ui.fragement;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.erp.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import Tool.StatisticalGraph.CombinedBarChartUtil;
import Tool.ToolUtils;
import Tool.statistics.Statics;
import broadcast.Config;
import broadcast.FreshenBroadcastReceiver;
import http.Constants;
import portface.LazyLoadFace;

/**
 * Created by admin on 2017/3/31.
 */

public class ExpressPiecePersonDetailsZheFragment extends Fragment {
    private LineChart mCombinedChart;
    private CombinedBarChartUtil mCombinedChartUtil;
    //第一个值：节点 第二个值：起始值 第三个值：结束值
    private  int mCount ;
    private final float minValue = 0;
    private float maxValue = 100;

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

        View view = inflater.inflate(R.layout.fragment_zhe, null);
        Statics.dayCount=true;
        mCombinedChart = (LineChart) view.findViewById(R.id.lineChart);
        initBroadCast();
        setGrayValue();
        initData();
        return view;
    }




    /**
     * 初始化数据
     */
    private void initData() {

        //统计最大y值
        List<Double> yZhiStatistics = new ArrayList<>();
        /*for (int i = 0; i< Statics.epmsXList.size(); i++){
            yZhiStatistics.add(Double.parseDouble(Statics.epmsXList.get(i).getSum()));
        }*/
        if(Statics.epmsXList!=null){
        for (int i = 0; i< Statics.epmsXList.length; i++){
            yZhiStatistics.add(Double.parseDouble(Statics.epmsXList[i]));
        }
        }else{
            yZhiStatistics=new ArrayList<>();
        }

        int yZhi = ToolUtils.tongJiTuY(yZhiStatistics);
        maxValue=(float) yZhi;

        List<String> list =new ArrayList<>();//图例
        list.add("数量");
        list.add(null);

        //mCount = Statics.Xday.length;

        mCombinedChartUtil = new CombinedBarChartUtil(getActivity());
        mCombinedChartUtil.setRule(mCount, minValue, maxValue);
        mCombinedChartUtil.setBackgroundColor(R.color.chart_color_2D2D2D);
        mCombinedChartUtil.setMianCombinedChart1(mCombinedChart, yValues1, yValues1,list,"业务员揽件量（每日）");
    }

    /**
     * 【功能描述】  ：设置折线的数据
     * 【修改时间】  ：2016/3/17 14:37
     */
    private void setGrayValue() {

        xValues1 = new ArrayList<>();
        for (int i = 1; i < mCount+1; i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            xValues1.add(i+"");

        }

        // y轴的数据
        yValues1 = new ArrayList<>();
        //数据源构建
        double[] piece = new double[mCount];
        //int[] day = new int[Statics.epmsXList.length];
        /*for (int i =0;i< Statics.epmsXList.size();i++){
            day[i] = Integer.parseInt(Statics.epmsXList.get(i).getDay());
        }*/
        /*for (int i =1;i<=mCount;i++ ){
            if(i<=mCount){
                for (int j = 0;j<day.length;j++){
                    if(i == day[j]){
                        piece[i-1] = Double.parseDouble(Statics.epmsXList.get(j).getSum());
                    }
                }
            }
        }*/

        yValues1 = new ArrayList<>();
        if(Statics.epmsXList!=null){
            for (int i = 0; i < Statics.epmsXList.length; i++) {
                yValues1.add(new BarEntry(Float.parseFloat(Statics.epmsXList[i]), i, Statics.Xday));
            }
        }
        /*for (int i = 0; i < Statics.epmsXList.length; i++) {
            yValues1.add(new BarEntry(Float.parseFloat(Statics.epmsXList[i]), i, Statics.Xday));
        }*/


    }


    private void initBroadCast() {
        //广播初始化 必须动态注册才能实现回调
        FreshenBroadcastReceiver broadcast = new FreshenBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Config.BC_ONE);
        getContext().registerReceiver(broadcast, intentFilter);


        broadcast.setLazyLoadFace(new LazyLoadFace() {
            @Override
            public void AdapterRefresh(String type) {
                //具体更新
                if(type.equals("PersonXq")){
                    Statics.dayCount=true;
                    Log.d("broadcast","更新界面");
                    //Log.d("shu",Statics.expressPieceCountMonthsList.get(0).getSum());
                    if(Statics.Xday!=null){
                        mCount = Statics.Xday.length;
                    }else{
                        mCount=0;
                    }
                    setGrayValue();
                    initData();
                }
            }

        });
    }
}
