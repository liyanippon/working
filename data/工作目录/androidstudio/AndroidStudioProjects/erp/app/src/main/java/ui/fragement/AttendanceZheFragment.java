package ui.fragement;

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

/**
 * Created by admin on 2017/3/28.
 */

public class AttendanceZheFragment extends Fragment {
    private LineChart mCombinedChart;
    private CombinedBarChartUtil mCombinedChartUtil;
    //第一个值：节点 第二个值：起始值 第三个值：结束值
    private int mCount = 12;
    private final float minValue = 0;
    private float maxValue = 100;

    //yVals1:蓝色折线的数据 //yVals2:灰色折线的数据
    private ArrayList<BarEntry> yVals1;
    private ArrayList<BarEntry> yVals2;
    /*ArrayList<String> xValues1,xValues2;
    ArrayList<Entry> yValues1,yValues2;*/
    ArrayList<String> yValues1,yValues2;
    ArrayList<Entry> xValues1,xValues2;
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

        View view = inflater.inflate(R.layout.attendance_fragment_heng_zhe, null);

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

        List<Double> input = new ArrayList<>();
        for (int i = 0; i< Statics.attendanceStatisticsList.size(); i++){//添加应该出勤的小时数，然后推出最大值
            input.add(Statics.attendanceStatisticsList.get(i).getNormalHourSum());//正常的
        }

        int yZhi = ToolUtils.tongJiTuY(input);
        maxValue=(float) yZhi;

        List<String> list =new ArrayList<>();//图例
        list.add("实际出勤");
        list.add("预期出勤");

        //设置下面显示Y轴
        Statics.yPositon = true;

        mCombinedChartUtil = new CombinedBarChartUtil(getActivity());
        mCombinedChartUtil.setRule(mCount, minValue, maxValue);
        mCombinedChartUtil.setBackgroundColor(R.color.chart_color_2D2D2D);
        mCombinedChartUtil.setMianCombinedChart1(mCombinedChart, xValues1, xValues2,list,"进账出账折线统计图");
    }

    /**
     * 【功能描述】  ：设置折线的数据
     * 【修改时间】  ：2016/3/17 14:37
     */
    private void setGrayValue() {




        /*xValues1 = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            xValues1.add(i+"");

        }

        xValues2 = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            xValues2.add(i+"");

        }*/
        // y轴的数据
        //yValues1 = new ArrayList<>();
        /*for (int i = 1; i < 13; i++) {
            float value = (float) (Math.random()*10);
            yValues1.add(new Entry(value, i-1));
            Log.d("tt",Float.toString(value));
        }*/

        //yValues2 = new ArrayList<>();
        /*for (int i = 1; i < 13; i++) {
            float value = (float) (Math.random()*10+50);
            yValues2.add(new Entry(value, i-1));
            Log.d("tt",Float.toString(value));
        }
*/
        yValues1 = new ArrayList<>();//显示时间
        for (int i = 1; i < 13; i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            yValues1.add(i+"");

        }

        yValues2 = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            yValues2.add(i+"");

        }

        //数据源构建
        double[] normal = new double[Statics.attendanceStatisticsList.size()];
        double[] actual = new double[Statics.attendanceStatisticsList.size()] ;
        int[] mon = new int[Statics.timeBillingStatisticsList.size()];
        for (int i =0;i< Statics.timeBillingStatisticsList.size();i++){
            mon[i] = Integer.parseInt(Statics.timeBillingStatisticsList.get(i).getMonth());
        }
        //使其反向，以达到和表格一致
        String[] name = new String[Statics.attendanceStatisticsList.size()];//人员姓名
        for (int i =Statics.attendanceStatisticsList.size()-1,j=0;i>= 0;i--,j++){
            name[j] = Statics.attendanceStatisticsList.get(i).getUserName();//每个人哪个月有数据 竖轴存放的是名字
            Log.d("name","mame:"+name[i]);
        }

        Statics.xPerson=name;
        for (int i=0;i<name.length;i++){
            Log.d("xperson",name[i]);
        }
        mCount = name.length;

        //使其反向，以达到和表格一致
        for (int i=Statics.attendanceStatisticsList.size()-1,j=0;i>=0;i--,j++){
            normal[j] = Statics.attendanceStatisticsList.get(i).getNormalHourSum();//正常出勤
            actual[j] = Statics.attendanceStatisticsList.get(i).getNormalHourSum()
                    - Statics.attendanceStatisticsList.get(i).getOffworkHoursSum();//实际出勤=正常出勤-请假合计
        }

        /*for (int i =0;i<12;i++ ){
            if(i<12){
                for (int j = 0;j<mon.length;j++){
                    Log.d("test","mon:"+Integer.toString(i)+"k"+Integer.toString(mon[j]));
                    if(i == mon[j]){
                        income[i-1] = Double.parseDouble(Statics.timeBillingStatisticsList.get(j).getIncome());//进账
                        outcome[i-1] = Double.parseDouble(Statics.timeBillingStatisticsList.get(j).getOutcom());//出账
                    }
                }
            }
        }*/

        xValues1 = new ArrayList<>();//显示姓名
        for (int i = 0; i < mCount; i++) {
            //yVals1.add(new BarEntry(getRandom(maxValue / 2, minValue), i, mDateTime[i]));
            xValues1.add(new BarEntry((float) normal[i], i, mDateTime[i]));
        }
        xValues2 = new ArrayList<>();
        for (int i = 0; i < mCount; i++) {
            //yVals2.add(new BarEntry(getRandom(maxValue / 2, minValue), i, mDateTime[i]));
            xValues2.add(new BarEntry((float) actual[i], i, mDateTime[i]));
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
