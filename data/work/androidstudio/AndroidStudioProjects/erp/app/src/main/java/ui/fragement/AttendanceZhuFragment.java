package ui.fragement;

import android.app.Activity;
import android.graphics.Matrix;
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

/**
 * Created by admin on 2017/3/28.
 */

public class AttendanceZhuFragment extends Fragment {
    private BarChart mCombinedChart;
    private CombinedBarChartUtil mCombinedChartUtil;
    //第一个值：节点 第二个值：起始值 第三个值：结束值
    private int mCount = 12;
    private float minValue = 0;
    private float maxValue = 100;

    //yVals1:蓝色折线的数据 //yVals2:灰色折线的数据
    private ArrayList<BarEntry> yVals1;
    private ArrayList<BarEntry> yVals2;

    public static final String[] mDateTime = new String[]{
            "1月", "2月", "3月", "4月", "5月",
            "6月", "7月", "8月", "9月", "10月",
            "11月", "12月"};
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("test","outcome:");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.attendance_fragment_heng_zhu, null);
        mCombinedChart = (BarChart) view.findViewById(R.id.barChart);
        setGrayValue();
        initData(null,mCombinedChart,false);

        return view;
    }

    /**
     * 初始化数据
     */
    public void initData(Activity activity ,BarChart mCombinedChart ,boolean basefragment ) {

        Statics.personCount=true;//按人员统计

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
        Activity activitys = getActivity();
        if(basefragment){
            activitys = activity;
        }else{
            activitys = getActivity();
        }
        mCombinedChartUtil = new CombinedBarChartUtil(activitys);
        mCombinedChartUtil.setRule(mCount, minValue, maxValue);
        mCombinedChartUtil.setBackgroundColor(R.color.chart_color_2D2D2D);
        mCombinedChartUtil.setMianCombinedChart2(mCombinedChart, yVals1, yVals2,list,"考勤柱型统计图");
        Statics.yPositon = false;//用过之后置为否
    }

    /**
     * 【功能描述】  ：设置折线的数据
     * 【修改时间】  ：2016/3/17 14:37
     */
    public void setGrayValue() {

        double[] normal = new double[Statics.attendanceStatisticsList.size()];//正常出勤
        double[] actual = new double[Statics.attendanceStatisticsList.size()] ;//实际出勤
        String[] name = new String[Statics.attendanceStatisticsList.size()];//人员姓名
        Log.d("leng",Statics.attendanceStatisticsList.size()+"");

        /*for (int i =0;i< Statics.attendanceStatisticsList.size();i++){
            name[i] = Statics.attendanceStatisticsList.get(i).getUserName();//每个人哪个月有数据 竖轴存放的是名字
            Log.d("name","mame:"+name[i]);
        }*/
        //使其反向，以达到和表格一致
        for (int i =Statics.attendanceStatisticsList.size()-1,j=0;i>= 0;i--,j++){
            name[j] = Statics.attendanceStatisticsList.get(i).getUserName();//每个人哪个月有数据 竖轴存放的是名字
            Log.d("name","mame:"+name[i]);
        }
        Statics.xPerson=name;
        for (int i=0;i<name.length;i++){
            Log.d("xperson",name[i]);
        }
        mCount = name.length;
        /*for (int i=0;i<Statics.attendanceStatisticsList.size();i++){//正向数据
            normal[i] = Statics.attendanceStatisticsList.get(i).getNormalHourSum();//正常出勤
            actual[i] = Statics.attendanceStatisticsList.get(i).getNormalHourSum()
                    - Statics.attendanceStatisticsList.get(i).getOffworkHoursSum();//实际出勤=正常出勤-请假合计
        }*/
        //使其反向，以达到和表格一致
        for (int i=Statics.attendanceStatisticsList.size()-1,j=0;i>=0;i--,j++){
            normal[j] = Statics.attendanceStatisticsList.get(i).getNormalHourSum();//正常出勤
            actual[j] = Statics.attendanceStatisticsList.get(i).getNormalHourSum()//实际出勤=正常出勤-请假合计+普通加班+节假日加班
                    - Statics.attendanceStatisticsList.get(i).getOffworkHoursSum()
                    +Statics.attendanceStatisticsList.get(i).getOvertimeWorkingHours()
                    +Statics.attendanceStatisticsList.get(i).getRegularOvertimeHoursSum();
        }
       /* for (int i =0;i<12;i++ ){i
            if(i<12){
                for (int j = 0;j<name.length;j++){
                    Log.d("test","mon:"+Integer.toString(i)+"k"+Integer.toString(name[j]));
                    if(i == name[j]){
                        income[i-1] = Double.parseDouble(Statics.timeBillingStatisticsList.get(j).getIncome());//进账
                        outcome[i-1] = Double.parseDouble(Statics.timeBillingStatisticsList.get(j).getOutcom());//出账
                        Log.d("test","income:"+Statics.timeBillingStatisticsList.get(j).getIncome());
                        Log.d("test","outcome:"+Statics.timeBillingStatisticsList.get(j).getOutcom());
                    }
                }
            }

        }*/

        yVals1 = new ArrayList<>();
        yVals2 = new ArrayList<>();
        /*for (int i = 0; i < mCount; i++) {//测试数据 随机生成
            yVals1.add(new BarEntry(getRandom(maxValue / 2, minValue), i, name[i]));
            yVals2.add(new BarEntry(getRandom(maxValue / 2, minValue), i, name[i]));
            //yVals2.add(new BarEntry((float) income[i], i, mDateTime[i]));
        }*/
        for (int i = 0; i < mCount; i++) {
            yVals1.add(new BarEntry((float) normal[i], i, mDateTime[i]));
            yVals2.add(new BarEntry((float) actual[i], i, mDateTime[i]));//没问题
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
