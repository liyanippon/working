package ui.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import http.Constants;

/**
 * Created by admin on 2017/3/31.
 */

public class ExpressPieceZheFragment extends Fragment {
    private LineChart mCombinedChart;
    private CombinedBarChartUtil mCombinedChartUtil;
    //第一个值：节点 第二个值：起始值 第三个值：结束值
    private final int mCount = 12;
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

        mCombinedChart = (LineChart) view.findViewById(R.id.lineChart);
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
        for (int i = 0; i< Constants.expressTimeList.size(); i++){
            yZhiStatistics.add(Double.parseDouble(Constants.expressTimeList.get(i).getSum()));
        }

        int yZhi = ToolUtils.tongJiTuY(yZhiStatistics);
        maxValue=(float) yZhi;

        List<String> list =new ArrayList<>();//图例
        list.add("数量");
        list.add(null);

        mCombinedChartUtil = new CombinedBarChartUtil(getActivity());
        mCombinedChartUtil.setRule(mCount, minValue, maxValue);
        mCombinedChartUtil.setBackgroundColor(R.color.chart_color_2D2D2D);
        mCombinedChartUtil.setMianCombinedChart1(mCombinedChart, yValues1, yValues1,list,"快递数量折线统计图");
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

        // y轴的数据
        yValues1 = new ArrayList<>();
        //数据源构建
        double[] piece = new double[12];
        int[] mon = new int[Constants.expressTimeList.size()];
        for (int i =0;i< Constants.expressTimeList.size();i++){
            mon[i] = Integer.parseInt(Constants.expressTimeList.get(i).getMonth());
        }
        for (int i =0;i<12;i++ ){
            if(i<12){
                for (int j = 0;j<mon.length;j++){
                    if(i == mon[j]){
                        piece[i-1] = Double.parseDouble(Constants.expressTimeList.get(j).getSum());
                    }
                }
            }
        }

        yValues1 = new ArrayList<>();
        for (int i = 0; i < mCount; i++) {
            yValues1.add(new BarEntry((float) piece[i], i, mDateTime[i]));
        }
    }
}
