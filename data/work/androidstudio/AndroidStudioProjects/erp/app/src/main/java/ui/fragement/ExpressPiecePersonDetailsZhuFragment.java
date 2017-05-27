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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import Tool.StatisticalGraph.CombinedBarChartUtil;
import Tool.ToolUtils;
import Tool.statistics.Statics;
import broadcast.Config;
import broadcast.FreshenBroadcastReceiver;
import http.ExpressStatisticsHttpPost;
import portface.LazyLoadFace;
import ui.activity.ExpressStatisticsActivity;

/**
 * Created by admin on 2017/3/28.
 */

public class ExpressPiecePersonDetailsZhuFragment extends Fragment {
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
    private String expressPersonId;
    private String temmp = null;
    private String shijianTime = null;
    private double temp =0.0;
    public ExpressPiecePersonDetailsZhuFragment(){
        year = Statics.XiangxiChan.get("year");
        month = Statics.XiangxiChan.get("month");
        type = Statics.XiangxiChan.get("type");
        expressPersonId = Statics.XiangxiChan.get("expressPersonId");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        Statics.epmsXList=null;

        View view = inflater.inflate(R.layout.fragment_zhu, null);
        mCombinedChart = (BarChart) view.findViewById(R.id.barChart);

        initBroadCast();
        //处理月份天数和具体到每日的件数
        Statics.isBroadCast =true;
        String ExpressPieceMonthDaySearchUrl = Statics.ExpressPieceMonthDaySearchUrl;
        expressStatisticsHttpPost =new ExpressStatisticsHttpPost();
        Log.d("test00",month+"月");
        expressStatisticsHttpPost.searchDayCurrentMonth(ExpressPieceMonthDaySearchUrl,year,month);
        //长按：
        expressStatisticsHttpPost.searchPersonDayCurrentMonthPieceCount(Statics.ExpressPersonPieceDaySearchUrl, year,  month, expressPersonId, getContext());
        //以快递员数量统计
        setGrayValue();
        initData();

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
                Log.d("aaaa","pppppppppppppppppppppppppp");
                if(type.equals("PersonXq")){
                    Log.d("broadcast","接收到广播sss");
                    Statics.dayCount=true;
                    Log.d("broadcast","更新界面");

                    if(Statics.Xday!=null){
                        mCount = Statics.Xday.length;
                    }else{
                        mCount=0;
                        Log.d("broadcast","ss"+"sdaf");
                    }

                    setGrayValue();
                    initData();
                }

            }

        });


    }

    /**
     * 初始化数据
     */
    private void initData() {

        //统计最大y值
        List<Double> input = new ArrayList<>();

        if(Statics.epmsXList!=null) {

            for (int i = 0; i < Statics.epmsXList.length; i++) {
                input.add(Double.parseDouble(Statics.epmsXList[i]));
                //Log.d("broadcast","统计最大y值："+Statics.epmsXList.get(i).getSum());
            }
        }else{
            input=new ArrayList<>();
        }
        input.add(temp);
        Log.d("testa","yyy::"+Double.toString(temp));
        int yZhi = ToolUtils.tongJiTuY(input);
        temp = 0;
        maxValue=(float) yZhi;
        Log.v("float",Float.toString(maxValue));
        Log.d("broadcast","maxValue："+Float.toString(maxValue));
        List<String> list =new ArrayList<>();//图例
        list.add("数量");
        list.add(null);

        mCombinedChartUtil = new CombinedBarChartUtil(getActivity());
        mCombinedChartUtil.setRule(mCount, minValue, maxValue);
        mCombinedChartUtil.setBackgroundColor(R.color.chart_color_2D2D2D);
        mCombinedChartUtil.setMianCombinedChart(mCombinedChart, yVals1, yVals1,list,"业务员揽件量（每日）");

    }

    private void setGrayValue() {

        Log.d("broadcast","调用数据");
        //Log.d("broadcast","ss:"+Integer.toString(Statics.epmsXList.size()));
        double[] expressPersonPieces = new double[mCount] ;
        //int[] day = new int[Statics.epmsXList.length];
        //Log.d("broadcast",day.length+"ad");
        /*for (int i =0;i< Statics.epmsXList.length;i++){
            Log.d("broadcast","day[idfd]:"+Statics.epmsXList.length);
            day[i] = Integer.parseInt(Statics.epmsXList.get(i).getDay());

        }*/
        Log.d("test20","---------------------");
        Log.d("test20","mCount::"+Integer.toString(mCount));
        //Log.d("test20","day.length::"+Integer.toString(day.length));//有数据的天数

        /*for (int i =1;i<=mCount;i++ ){//mCount月份天数
                for (int j = 0;j<day.length;j++){
                    Log.d("testss","day::"+Integer.toString(day[j]));
                    if(i == day[j]){
                        Log.d("test20","day[j]::"+Integer.toString(day[j]));
                        Log.d("test20","getNumeric()::"+Statics.epmsXList.get(j).getSum());
                        expressPersonPieces[i-1] = Double.parseDouble(Statics.epmsXList.get(j).getSum());
                        Log.d("testss","testss::"+Double.toString(expressPersonPieces[i-1]));
                    }
                }

        }*/
        if(Statics.epmsXList!=null&&Statics.epmsXList.length>0){
            for (int i=0;i<Statics.epmsXList.length;i++){
                Log.d("tetes","--------------");
                Log.d("tetes","asdadf"+Statics.epmsXList.length+"");
                expressPersonPieces[i]=Double.parseDouble(Statics.epmsXList[i]);
            }
        }



        yVals1 = new ArrayList<>();
        for (int i = 0; i < mCount; i++) {
            yVals1.add(new BarEntry((float) expressPersonPieces[i], i, Statics.Xday));
        }
    }

}
