package ui.fragement;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.erp.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import Tool.StatisticalGraph.CombinedBarChartUtil;
import Tool.ToolUtils;
import Tool.statistics.Statics;
import broadcast.Config;
import broadcast.FreshenBroadcastReceiver;
import http.ExpressStatisticsHttpPost;
import portface.LazyLoadFace;

/**
 * Created by admin on 2017/3/28.
 */

public class ExpressPieceDetailsZhuFragment extends Fragment {
    private BarChart mCombinedChart;
    private CombinedBarChartUtil mCombinedChartUtil;
    //第一个值：节点 第二个值：起始值 第三个值：结束值

    private int mCount = 0;
    private float minValue = 0;
    private float maxValue = 100;

    //yVals1:蓝色折线的数据 //yVals2:灰色折线的数据
    private ArrayList<BarEntry> yVals1;
    public static final String[] mDateTime = new String[]{
            "1月", "2月", "3月", "4月", "5月",
            "6月", "7月", "8月", "9月", "10月",
            "11月", "12月"};
    private  ExpressStatisticsHttpPost expressStatisticsHttpPost;

    private String year;
    private String month;
    private String type;
    public ExpressPieceDetailsZhuFragment(){
        year = Statics.XiangxiChan.get("year");
        month = Statics.XiangxiChan.get("month");
        type = Statics.XiangxiChan.get("type");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_zhu, null);
        mCombinedChart = (BarChart) view.findViewById(R.id.barChart);

        initBroadCast();
        //处理月份天数和具体到每日的件数
        String ExpressPieceMonthDaySearchUrl = Statics.ExpressPieceMonthDaySearchUrl;
        expressStatisticsHttpPost =new ExpressStatisticsHttpPost();
        expressStatisticsHttpPost.searchDayCurrentMonth(ExpressPieceMonthDaySearchUrl,year,month);
        String ExpressPieceDaySearchUrl = Statics.ExpressPieceDaySearchUrl;
        expressStatisticsHttpPost.searchDayCurrentMonthPieceCount(ExpressPieceDaySearchUrl,year,month,type,getContext());
        setGrayValue(mCount);
        initData(null,mCombinedChart,false,yVals1,-1);
        return view;
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
                if(type.equals("express")){
                    Statics.dayCount=true;
                    Log.d("lazy","sdad:"+type);
                    Log.d("broadcast","更新界面");
                    //Log.d("shu",Statics.expressPieceCountMonthsList.get(0).getSum());
                    if(Statics.Xday!=null){
                        mCount = Statics.Xday.length;
                    }else{
                        mCount=0;
                    }
                    setGrayValue(mCount);
                    initData(null,mCombinedChart,false,yVals1,-1);
                }

            }

        });


    }

    /**
     * 初始化数据
     */
    public void initData(Activity activity , BarChart mCombinedChart , boolean basefragment , ArrayList<BarEntry> yVals1 ,int zhuCount) {

        //统计最大y值
        List<Double> input = new ArrayList<>();
        for (int i = 0; i < Statics.expressPieceCountMonthsList.size(); i++) {
            input.add(Double.parseDouble(Statics.expressPieceCountMonthsList.get(i).getSum()));
            Log.d("broadcast", "统计最大y值：" + Statics.expressPieceCountMonthsList.get(i).getSum());

        }

        int yZhi = ToolUtils.tongJiTuY(input);
        maxValue = (float) yZhi;
        Log.v("float", Float.toString(maxValue));
        Log.d("broadcast", "maxValue：" + Float.toString(maxValue));
        List<String> list = new ArrayList<>();//图例
        list.add("数量");
        list.add(null);
        Activity activitys = getActivity();

        if(basefragment){
            activitys = activity;
            mCount = zhuCount;
        }else{
            activitys = getActivity();
            zhuCount = mCount;

        }
        mCombinedChartUtil = new CombinedBarChartUtil(activitys);
        mCombinedChartUtil.setRule(zhuCount, minValue, maxValue);
        mCombinedChartUtil.setBackgroundColor(R.color.chart_color_2D2D2D);
        mCombinedChartUtil.setMianCombinedChart(mCombinedChart, yVals1, yVals1, list, "业务员揽件量（月份）");
    }

    public ArrayList<BarEntry> setGrayValue(int mCount) {
        Log.d("cece","yVals1"+"调用数据");
        Log.d("broadcast","调用数据");
        //double[] income = new double[12];
        double[] expressPieces = new double[mCount] ;
        int[] day = new int[Statics.expressPieceCountMonthsList.size()];
        for (int i =0;i< Statics.expressPieceCountMonthsList.size();i++){
            day[i] = Integer.parseInt(Statics.expressPieceCountMonthsList.get(i).getDay());//
            Log.d("broadcast","day[i]:"+Integer.toString(day[i]));

        }
        Log.d("cece","yVals1"+"mCount"+mCount);
        Log.d("test","mocount:"+Integer.toString(mCount));
        for (int i =1;i<=mCount;i++ ){
            if(i<=mCount){
                for (int j = 0;j<day.length;j++){
                    Log.d("test","mon:"+Integer.toString(i)+"k"+Integer.toString(day[j]));
                    if(i == day[j]){
                        Log.d("test","expressPieces[i-1]");
                        expressPieces[i-1] = Double.parseDouble(Statics.expressPieceCountMonthsList.get(j).getSum());
                        Log.d("cece","yVals1"+"调用数据"+expressPieces[i-1]);
                        //outcome[i-1] = Double.parseDouble(Statics.timeBillingStatisticsList.get(j).getOutcom());//出账
                        Log.v("double","expressP"+Double.toString(expressPieces[i-1]));
                    }
                }
            }

        }




        yVals1 = new ArrayList<>();
        //yVals2 = new ArrayList<>();
        /*for (int i = 0; i < mCount; i++) {
            yVals1.add(new BarEntry(getRandom(maxValue / 2, minValue), i, mDateTime[i]));
            yVals1.add(new BarEntry((float) income[i], i, mDateTime[i]));
        }*/
        for (int i = 0; i < mCount; i++) {
            Log.d("cece","yVals1"+expressPieces[i]);
            yVals1.add(new BarEntry((float) expressPieces[i], i, Statics.Xday));
            //yVals2.add(new BarEntry((float) outcome[i], i, mDateTime[i]));//没问题
        }
        return yVals1;
    }

}
