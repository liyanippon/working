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
import Tool.statistics.Statics;
import model.javabean.FinancialBillingGetWXsettlementMonth;

/**
 * Created by admin on 2017/3/28.
 */

public class InoutComeZheFragement extends Fragment {
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
    private String catlog;

    public InoutComeZheFragement(){
    }
    public InoutComeZheFragement(String catlog){
        this.catlog=catlog;
    }

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

        String description = "进账出账折线统计图";

        //统计最大y值
        List<Double> input = new ArrayList<>();

        if(this.catlog!=null&&this.catlog.equals("财务统计分析")){
            input = new ArrayList<>();
            for (FinancialBillingGetWXsettlementMonth fgbgm:Statics.fbgwxSettlementMonthList){
                input.add(fgbgm.getJz1().doubleValue());
                input.add(fgbgm.getCz1().doubleValue());
            }
            description = "财务账目折线统计图";
        }else{
            for (int i = 0; i< Statics.timeBillingStatisticsList.size(); i++){
                input.add(Double.parseDouble(Statics.timeBillingStatisticsList.get(i).getIncome()));
                input.add(Double.parseDouble(Statics.timeBillingStatisticsList.get(i).getOutcom()));
            }
            description = "进账出账折线统计图";
        }

        int yZhi = ToolUtils.tongJiTuY(input);
        maxValue=(float) yZhi;

        List<String> list =new ArrayList<>();//图例
        list.add("出账");
        list.add("进账");

        mCombinedChartUtil = new CombinedBarChartUtil(getActivity());
        mCombinedChartUtil.setRule(mCount, minValue, maxValue);
        mCombinedChartUtil.setBackgroundColor(R.color.chart_color_2D2D2D);
        mCombinedChartUtil.setMianCombinedChart1(mCombinedChart, yValues1, yValues2,list,description);
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
        yValues1 = new ArrayList<>();
        yValues2 = new ArrayList<>();

        //数据源构建
        double[] income = new double[12];
        double[] outcome = new double[12] ;

        if(this.catlog!=null&&this.catlog.equals("财务统计分析")){
            int[] mon = new int[Statics.fbgwxSettlementMonthList.size()];
            for (int i =0;i< Statics.fbgwxSettlementMonthList.size();i++){
                mon[i] = Statics.fbgwxSettlementMonthList.get(i).getMon();
            }
            for (int i =0;i<=12;i++ ){
                    for (int j = 0;j<mon.length;j++){
                        if(i == mon[j]){
                            outcome[i-1] = Statics.fbgwxSettlementMonthList.get(j).getJz1().doubleValue();//进账 为了图形显示正确，数组名字
                            income[i-1] = Statics.fbgwxSettlementMonthList.get(j).getCz1().doubleValue();//出账
                        }
                    }
            }
        }else{//默认是物流统计分析
            int[] mon = new int[Statics.timeBillingStatisticsList.size()];
            for (int i =0;i< Statics.timeBillingStatisticsList.size();i++){
                mon[i] = Integer.parseInt(Statics.timeBillingStatisticsList.get(i).getMonth());
            }
            for (int i =0;i<=12;i++ ){
                    for (int j = 0;j<mon.length;j++){
                        if(i == mon[j]){
                            outcome[i-1] = Double.parseDouble(Statics.timeBillingStatisticsList.get(j).getIncome());//出账
                            income[i-1] = Double.parseDouble(Statics.timeBillingStatisticsList.get(j).getOutcom());//进账
                        }
                    }
            }
        }

        yValues1 = new ArrayList<>();
        for (int i = 0; i < mCount; i++) {
            yValues1.add(new BarEntry((float) income[i], i, mDateTime[i]));
        }
        yValues2 = new ArrayList<>();
        for (int i = 0; i < mCount; i++) {
            yValues2.add(new BarEntry((float) outcome[i], i, mDateTime[i]));
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
