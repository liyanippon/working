package ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.admin.erp.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

import Tool.ToolUtils;
import Tool.crash.BaseActivity;
import Tool.statistics.Statics;

public class TongjiGraphActivity extends BaseActivity{

    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;
    //柱形图处理
    private LinearLayout relativeLayout;
    private Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_zhangdan_zhuxing_graph);

        //得到资源
        relativeLayout = (LinearLayout) findViewById(R.id.activity_zhangdan_zhuxing_graph);
        //柱形图
        dataDispose();
        //折线图
        //zeData();
        //test测试
    }

    private void dataDispose() {//数据处理
        //存放柱状图两个序列的值
        ArrayList<double[]> value = new ArrayList<>();
        double[] income = new double[12];
        double[] outcome = new double[12] ;
        int[] mon = new int[Statics.timeBillingStatisticsList.size()];
        for (int i =0;i< Statics.timeBillingStatisticsList.size();i++){
            mon[i] = Integer.parseInt(Statics.timeBillingStatisticsList.get(i).getMonth());
        }
        for (int i =0;i<12;i++ ){
            if(i<12){
                for (int j = 0;j<mon.length;j++){
                    Log.d("test","mon:"+Integer.toString(i)+"k"+Integer.toString(mon[j]));
                    if(i == mon[j]){
                        income[i-1] = Double.parseDouble(Statics.timeBillingStatisticsList.get(j).getIncome());//进账
                        outcome[i-1] = Double.parseDouble(Statics.timeBillingStatisticsList.get(j).getOutcom());//出账
                        Log.d("test","income"+Double.toString(income[i]));
                        Log.d("test","outcome"+Double.toString(outcome[i]));
                    }
                }
            }

        }


        //统计最大y值
        List<Double> input = new ArrayList<>();
        for (int i =0;i<Statics.timeBillingStatisticsList.size();i++){
            input.add(Double.parseDouble(Statics.timeBillingStatisticsList.get(i).getIncome()));
            input.add(Double.parseDouble(Statics.timeBillingStatisticsList.get(i).getOutcom()));
        }

        int yZhi = ToolUtils.tongJiTuY(input);

        value.add(income);
        value.add(outcome);
        //柱状图的两个序列的名字
        String[] titles = new String[] { "进账", "出帐" };
        //两个柱状的颜色
        int[] colors = { Color.BLUE, Color.RED};
        //为li1添加柱状图
        relativeLayout.addView(//Integer.parseInt(sb.toString())设置y轴最大值
                xychar(titles, value, colors, 13, 5, new double[] { 0,
                        12, 0, yZhi}, new int[] {1, 2, 3, 4, 5, 6, 7, 8,
                        9, 10, 11, 12 }, "账单柱形图", false),
                new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
    }

    public GraphicalView xychar(String[] titles, ArrayList<double[]> value,
                                int[] colors, int x, int y, double[] range, int []xLable , String xtitle, boolean f) {
        //多个渲染
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        //多个序列的数据集
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        //构建数据集以及渲染
        for (int i = 0; i < titles.length; i++) {

            XYSeries series = new XYSeries(titles[i]);
            double [] yLable= value.get(i);
            for (int j=0;j<yLable.length;j++) {
                series.add(xLable[j],yLable[j]);
            }
            dataset.addSeries(series);
            XYSeriesRenderer xyRenderer = new XYSeriesRenderer();
            // 设置颜色
            xyRenderer.setColor(colors[i]);

            // 设置点的样式 //
            xyRenderer.setPointStyle(PointStyle.SQUARE);
            // 将要绘制的点添加到坐标绘制中

            renderer.addSeriesRenderer(xyRenderer);
        }
        //将x标签栏目显示如：1,2,3,4替换为显示1月，2月，3月，4月
        renderer.addXTextLabel(1,"1月");
        renderer.addXTextLabel(2,"2月");
        renderer.addXTextLabel(3,"3月");
        renderer.addXTextLabel(4,"4月");
        renderer.addXTextLabel(5,"5月");
        renderer.addXTextLabel(6,"6月");
        renderer.addXTextLabel(7,"7月");
        renderer.addXTextLabel(8,"8月");
        renderer.addXTextLabel(9,"9月");
        renderer.addXTextLabel(10,"10月");
        renderer.addXTextLabel(11,"11月");
        renderer.addXTextLabel(12,"12月");
        renderer.setXLabels(0);//设置只显示如1月，2月等替换后的东西，不显示1,2,3等
        //设置x轴标签数
        //renderer.setXLabels(x);
        //设置Y轴标签数
        renderer.setYLabels(y);
        //设置x轴的最大值
        renderer.setXAxisMax(x - 0.5);
        //设置轴的颜色
        renderer.setAxesColor(Color.BLACK);
        //设置x轴和y轴的标签对齐方式
        renderer.setXLabelsAlign(Paint.Align.CENTER);
        renderer.setYLabelsAlign(Paint.Align.RIGHT);
        // 设置现实网格
        renderer.setShowGrid(true);
        renderer.setShowAxes(true);
        // 设置条形图之间的距离
        renderer.setBarSpacing(1.5);
        renderer.setInScroll(false);
        renderer.setPanEnabled(false, false);
        renderer.setClickEnabled(true);
        //设置x轴和y轴标签的颜色
        renderer.setXLabelsColor(Color.RED);
        renderer.setYLabelsColor(0,Color.RED);

        int length = renderer.getSeriesRendererCount();
        //设置图标的标题
        renderer.setChartTitle(xtitle);
        renderer.setLabelsColor(Color.RED);
        renderer.setChartTitleTextSize(23);//标题大小
        renderer.setXTitle("月份");//设置为X轴的标题
        renderer.setYTitle("金额");//设置y轴的标题

        //设置图例的字体大小
        renderer.setLegendTextSize(18);
        //设置x轴和y轴的最大最小值
        renderer.setRange(range);
        renderer.setMarginsColor(0x00888888);
        for (int i = 0; i < length; i++) {
            SimpleSeriesRenderer ssr = renderer.getSeriesRendererAt(i);
            ssr.setChartValuesTextAlign(Paint.Align.RIGHT);
            ssr.setChartValuesTextSize(12);
            ssr.setDisplayChartValues(f);
        }
        GraphicalView mChartView = ChartFactory.getBarChartView(getApplicationContext(),
                dataset, renderer, BarChart.Type.DEFAULT);

        return mChartView;

    }



    //折线图
    public void zeData(){
        //同样是需要数据dataset和视图渲染器renderer
        XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
        XYSeries income = new XYSeries("进账");
        XYSeries outcome = new XYSeries("出账");
        ArrayList<double[]> value = new ArrayList<>();
        double[] incomes = new double[12];
        double[] outcomes = new double[12] ;
        int[] mon = new int[Statics.timeBillingStatisticsList.size()];
        for (int i =0;i< Statics.timeBillingStatisticsList.size();i++){
            mon[i] = Integer.parseInt(Statics.timeBillingStatisticsList.get(i).getMonth());
        }
        for (int i =0;i<12;i++ ){
            if(i<12){
                for (int j = 0;j<mon.length;j++){
                    Log.d("test","mon:"+Integer.toString(i)+"k"+Integer.toString(mon[j]));
                    if(i == mon[j]){
                        incomes[i-1] = Double.parseDouble(Statics.timeBillingStatisticsList.get(j).getIncome());//进账
                        outcomes[i-1] = Double.parseDouble(Statics.timeBillingStatisticsList.get(j).getOutcom());//出账
                        Log.d("test","income"+Double.toString(incomes[i]));
                        Log.d("test","outcome"+Double.toString(outcomes[i]));
                    }
                }
            }
        }
        for (int i=1;i<=12;i++){
            income.add(i,incomes[i-1]);
            outcome.add(i,outcomes[i-1]);
        }
        mDataset.addSeries(income);
        mDataset.addSeries(outcome);
        lineView(mDataset);

    }

    public void lineView(XYMultipleSeriesDataset mDataset) {

        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        //设置图表的X轴的当前方向
        mRenderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
        mRenderer.setXTitle("月份");//设置为X轴的标题
        mRenderer.setYTitle("金额");//设置y轴的标题
        //设置x轴和y轴标签的颜色
        mRenderer.setXLabelsColor(Color.RED);
        mRenderer.setYLabelsColor(0,Color.RED);
        //设置轴的颜色
        mRenderer.setAxesColor(Color.BLACK);
        mRenderer.setAxisTitleTextSize(20);//设置轴标题文本大小
        mRenderer.setChartTitle("进账出账折线图");//设置图表标题
        mRenderer.setLabelsColor(Color.RED);
        mRenderer.setChartTitleTextSize(23);//设置图表标题文字的大小
        mRenderer.setLabelsTextSize(18);//设置标签的文字大小
        mRenderer.setLegendTextSize(20);//设置图例文本大小
        mRenderer.setPointSize(10f);//设置点的大小
        mRenderer.setYAxisMin(0);//设置y轴最小值是0
        //mRenderer.setYAxisMax(40000);
        mRenderer.setYLabels(7);//设置Y轴刻度个数
        mRenderer.setShowGrid(true);//显示网格

        mRenderer.setInScroll(false);
        mRenderer.setPanEnabled(false, false);
        mRenderer.setClickEnabled(true);

        //将x标签栏目显示如：1,2,3,4替换为显示1月，2月，3月，4月
        mRenderer.addXTextLabel(1, "1月");
        mRenderer.addXTextLabel(2, "2月");
        mRenderer.addXTextLabel(3, "3月");
        mRenderer.addXTextLabel(4, "4月");
        mRenderer.addXTextLabel(5, "5月");
        mRenderer.addXTextLabel(6, "6月");
        mRenderer.addXTextLabel(7, "7月");
        mRenderer.addXTextLabel(8, "8月");
        mRenderer.addXTextLabel(9, "9月");
        mRenderer.addXTextLabel(10, "10月");
        mRenderer.addXTextLabel(11, "11月");
        mRenderer.addXTextLabel(12, "12月");
        mRenderer.setXLabels(0);//设置只显示如1月，2月等替换后的东西，不显示1,2,3等
        mRenderer.setMargins(new int[]{20, 30, 15, 20});//设置视图位置

        mRenderer.setMarginsColor(0x00888888);

        XYSeriesRenderer r = new XYSeriesRenderer();//(类似于一条线对象)
        r.setColor(Color.BLUE);//设置颜色
        r.setPointStyle(PointStyle.CIRCLE);//设置点的样式
        r.setFillPoints(true);//填充点（显示的点是空心还是实心）
        r.setDisplayChartValues(false);//将点的值显示出来
        r.setChartValuesSpacing(10);//显示的点的值与图的距离
        r.setChartValuesTextSize(25);//点的值的文字大小


        //  r.setFillBelowLine(true);//是否填充折线图的下方
        //  r.setFillBelowLineColor(Color.GREEN);//填充的颜色，如果不设置就默认与线的颜色一致
        r.setLineWidth(3);//设置线宽
        mRenderer.addSeriesRenderer(r);
        XYSeriesRenderer rTwo = new XYSeriesRenderer();//(类似于一条线对象)
        rTwo.setColor(Color.RED);//设置颜色
        rTwo.setPointStyle(PointStyle.CIRCLE);//设置点的样式
        rTwo.setFillPoints(true);//填充点（显示的点是空心还是实心）
        rTwo.setDisplayChartValues(false);//将点的值显示出来
        rTwo.setChartValuesSpacing(10);//显示的点的值与图的距离
        rTwo.setChartValuesTextSize(25);//点的值的文字大小
        //  rTwo.setFillBelowLine(true);//是否填充折线图的下方
        //  rTwo.setFillBelowLineColor(Color.GREEN);//填充的颜色，如果不设置就默认与线的颜色一致
        rTwo.setLineWidth(3);//设置线宽
        mRenderer.addSeriesRenderer(rTwo);
        GraphicalView view = ChartFactory.getLineChartView(this, mDataset, mRenderer);
        //view.setBackgroundColor(Color.WHITE);
        setContentView(view);
        }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //继承了Activity的onTouchEvent方法，直接监听点击事件
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x1 = event.getX();
            y1 = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            //当手指离开的时候
            x2 = event.getX();
            y2 = event.getY();
            if(y1 - y2 > 50) {
                //Toast.makeText(TongjiGraphActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
            } else if(y2 - y1 > 50) {
                //Toast.makeText(TongjiGraphActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
            } else if(x1 - x2 > 50) {
                in = new Intent(TongjiGraphActivity.this,ZheXianGraphActivity.class);
                startActivity(in);
                finish();
                //Toast.makeText(TongjiGraphActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
            } else if(x2 - x1 > 50) {
                in = new Intent(TongjiGraphActivity.this,ZheXianGraphActivity.class);
                startActivity(in);
                finish();
                //Toast.makeText(TongjiGraphActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

}
