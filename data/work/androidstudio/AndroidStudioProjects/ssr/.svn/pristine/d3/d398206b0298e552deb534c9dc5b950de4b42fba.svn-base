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
import Tool.statistics.Statics;
import model.FinancialBillingGetWXsettlementMonth;

/**
 * Created by admin on 2017/3/28.
 */

public class InoutComeZhuFragment extends Fragment {
    private BarChart mCombinedChart;
    private CombinedBarChartUtil mCombinedChartUtil;
    //第一个值：节点 第二个值：起始值 第三个值：结束值
    private final int mCount = 12;
    private float minValue = 0;
    private float maxValue = 100;

    //yVals1:蓝色折线的数据 //yVals2:灰色折线的数据
    private ArrayList<BarEntry> yVals1;
    private ArrayList<BarEntry> yVals2;

    public static final String[] mDateTime = new String[]{
            "1月", "2月", "3月", "4月", "5月",
            "6月", "7月", "8月", "9月", "10月",
            "11月", "12月"};
    private String catlog;
    List<String> list =new ArrayList<>();//图例
    List<Double> input;//y轴最大值
    InoutComeZhuFragment( ){}
    InoutComeZhuFragment(String catlog){
        this.catlog=catlog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("test","outcome:");
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

        String description = "进账出账柱型统计图";
        if(this.catlog!=null&&this.catlog.equals("财务统计分析")){
            //统计最大y值
            input = new ArrayList<>();
            for(FinancialBillingGetWXsettlementMonth fbg:Statics.fbgwxSettlementMonthList){
                input.add(fbg.getJz1());//进账
                input.add(fbg.getCz1());//出账
                Log.d("InoutComeZhuFragment", "进账" + fbg.getJz1());
                Log.d("InoutComeZhuFragment", "出账" + fbg.getCz1());
            }
            list =new ArrayList<>();//图例
            list.add("出账");
            list.add("进账");
            description = "财务账目柱型统计图";

        }else{
            //统计最大y值
            input = new ArrayList<>();
            for (int i = 0; i< Statics.timeBillingStatisticsList.size(); i++){
                input.add(Double.parseDouble(Statics.timeBillingStatisticsList.get(i).getIncome()));
                input.add(Double.parseDouble(Statics.timeBillingStatisticsList.get(i).getOutcom()));
            }

            list =new ArrayList<>();//图例
            list.add("出账");
            list.add("进账");
            description = "进账出账柱型统计图";
        }
        int yZhi = ToolUtils.tongJiTuY(input);
        maxValue=(float) yZhi;
        mCombinedChartUtil = new CombinedBarChartUtil(getActivity());
        mCombinedChartUtil.setRule(mCount, minValue, maxValue);
        mCombinedChartUtil.setBackgroundColor(R.color.chart_color_2D2D2D);
        mCombinedChartUtil.setMianCombinedChart(mCombinedChart, yVals1, yVals2,list,description);

    }

    /**
     * 【功能描述】  ：设置折线的数据
     * 【修改时间】  ：2016/3/17 14:37
     */
    private void setGrayValue() {

        double[] income = new double[12];
        double[] outcome = new double[12];
        yVals1 = new ArrayList<>();
        yVals2 = new ArrayList<>();
        if(this.catlog!=null&&this.catlog.equals("财务统计分析")){
            int[] mon = new int[Statics.fbgwxSettlementMonthList.size()];
            for (int i =0;i< Statics.fbgwxSettlementMonthList.size();i++){
                mon[i] = Statics.fbgwxSettlementMonthList.get(i).getMon();
            }

            for (int i =0;i<=12;i++ ){
                    for (int j = 0;j<mon.length;j++){
                        Log.d("test","mon:"+Integer.toString(i)+"k"+Integer.toString(mon[j]));
                        if(i == mon[j]){
                            income[i-1] = Statics.fbgwxSettlementMonthList.get(j).getJz1();//进账
                            outcome[i-1] = Statics.fbgwxSettlementMonthList.get(j).getCz1();//出账
                        }
                    }


            }
            for (int i = 0; i < mCount; i++) {
                yVals1.add(new BarEntry((float) income[i], i, mDateTime[i]));
                yVals2.add(new BarEntry((float) outcome[i], i, mDateTime[i]));//
            }

        }else{//物流账单统计分析
            int[] mon = new int[Statics.timeBillingStatisticsList.size()];
            for (int i =0;i< Statics.timeBillingStatisticsList.size();i++){
                mon[i] = Integer.parseInt(Statics.timeBillingStatisticsList.get(i).getMonth());
            }
            for (int i =0;i<=12;i++ ){
                    for (int j = 0;j<mon.length;j++){
                        Log.d("test","mon:"+Integer.toString(i)+"k"+Integer.toString(mon[j]));
                        if(i == mon[j]){
                            income[i-1] = Double.parseDouble(Statics.timeBillingStatisticsList.get(j).getIncome());//进账
                            outcome[i-1] = Double.parseDouble(Statics.timeBillingStatisticsList.get(j).getOutcom());//出账
                            Log.d("test","income:"+Statics.timeBillingStatisticsList.get(j).getIncome());
                            Log.d("test","outcome:"+Statics.timeBillingStatisticsList.get(j).getOutcom());
                        }
                    }

            }

            for (int i = 0; i < mCount; i++) {
                yVals1.add(new BarEntry((float) income[i], i, mDateTime[i]));
                yVals2.add(new BarEntry((float) outcome[i], i, mDateTime[i]));//没问题
            }
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
