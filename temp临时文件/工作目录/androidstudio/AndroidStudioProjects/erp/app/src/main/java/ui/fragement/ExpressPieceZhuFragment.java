package ui.fragement;

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
import http.Constants;

/**
 * Created by admin on 2017/3/28.
 */

public class ExpressPieceZhuFragment extends Fragment {
    private BarChart mCombinedChart;
    private CombinedBarChartUtil mCombinedChartUtil;
    //第一个值：节点 第二个值：起始值 第三个值：结束值
    private final int mCount = 12;
    private float minValue = 0;
    private float maxValue = 100;

    //yVals1:蓝色折线的数据 //yVals2:灰色折线的数据
    private ArrayList<BarEntry> yVals1;
    public static final String[] mDateTime = new String[]{
            "1月", "2月", "3月", "4月", "5月",
            "6月", "7月", "8月", "9月", "10月",
            "11月", "12月"};
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_zhu, null);
        mCombinedChart = (BarChart) view.findViewById(R.id.barChart);
        setGrayValue();
        initData();

        return view;
    }

    /**
     * 初始化数据
     */
    private void initData() {

        //统计最大y值
        List<Double> input = new ArrayList<>();
        for (int i =0;i<Constants.expressTimeList.size();i++){
            input.add(Double.parseDouble(Constants.expressTimeList.get(i).getSum()));
        }

        int yZhi = ToolUtils.tongJiTuY(input);
        maxValue=(float) yZhi;
        Log.v("float",Float.toString(maxValue));

        List<String> list =new ArrayList<>();//图例
        list.add("数量");
        list.add(null);

        mCombinedChartUtil = new CombinedBarChartUtil(getActivity());
        mCombinedChartUtil.setRule(mCount, minValue, maxValue);
        mCombinedChartUtil.setBackgroundColor(R.color.chart_color_2D2D2D);
        mCombinedChartUtil.setMianCombinedChart(mCombinedChart, yVals1, yVals1,list,"快递数量柱形统计图");
    }

    /**
     * 【功能描述】  ：设置折线的数据
     * 【修改时间】  ：2016/3/17 14:37
     */
    private void setGrayValue() {

        //double[] income = new double[12];
        double[] expressPieces = new double[12] ;
        int[] mon = new int[Constants.expressTimeList.size()];
        for (int i =0;i< Constants.expressTimeList.size();i++){
            mon[i] = Integer.parseInt(Constants.expressTimeList.get(i).getMonth());//
        }
        for (int i =0;i<12;i++ ){
            if(i<12){
                for (int j = 0;j<mon.length;j++){
                    Log.d("test","mon:"+Integer.toString(i)+"k"+Integer.toString(mon[j]));
                    if(i == mon[j]){
                        expressPieces[i-1] = Double.parseDouble(Constants.expressTimeList.get(j).getSum());
                        //outcome[i-1] = Double.parseDouble(Constants.timeBillingStatisticsList.get(j).getOutcom());//出账
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
            yVals1.add(new BarEntry((float) expressPieces[i], i, mDateTime[i]));
            //yVals2.add(new BarEntry((float) outcome[i], i, mDateTime[i]));//没问题
        }
    }

}
