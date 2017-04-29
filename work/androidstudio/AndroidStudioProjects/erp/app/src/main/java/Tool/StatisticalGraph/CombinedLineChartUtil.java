package Tool.StatisticalGraph;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/3/23.
 */

public class CombinedLineChartUtil extends BaseChartUtil{
    private Context mContext;
    private float minValue;
    private float maxValue;
    private List<String> dateTime;
    private List<Integer> types;


    public CombinedLineChartUtil(Context context) {
        this.mContext = context;
    }

    /**
     * 折线图  工具类
     * 2016--01--29
     * Demo中主要在listview中显示折线图
     * 第一个参数：节点 第二个参数：起始值 第三个参数：结束值
     *
     * @param minValue
     * @param maxValue
     */
    public void setRule(float minValue, float maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }


    public void setDateTime(List<String> dateTime) {
        if (dateTime == null) {
            dateTime = new ArrayList<>();
        }
        this.dateTime = dateTime;
    }

    /**
     * 折线图  工具类
     * 2016--01--29
     * Demo中主要在主页中显示两条的折线图
     * <p/>
     * //设置边框宽度
     * mChart.setBorderWidth(15f);
     * <p/>
     * //是否画边框
     * mChart.setDrawBorders(true);
     * <p/>
     * //边框的颜色
     * mChart.setBorderColor(Color.WHITE);
     * <p/>
     * //是否画标识
     * mChart.setDrawMarkerViews(false);
     * MyMarkerView mv = new MyMarkerView(mContext, R.layout.custom_marker_view);
     * // set the marker to the chart
     * mChart.setMarkerView(mv);
     * <p/>
     * 设置字体
     * Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");
     * Legend l = mChart.getLegend();//头部描述设置
     * l.setTypeface(tf);* @param
     * <p/>
     * //是否画x轴
     * leftAxis.setDrawAxisLine(true);
     * leftAxis.setDrawGridLines(true);
     * <p/>
     * //相当于设置外边距
     * mChart.setExtraOffsets(20, 0, 0, 0);
     */

    public void setCombinedLineChart(final LineChart mChart, List<List<Entry>> yValsList) {
        if (yValsList == null || yValsList.size() <= 0) {
            mChart.setNoDataText("暂时没有数据进行图表展示！");
            mChart.getPaint(Chart.PAINT_INFO).setColor(0xFFADA9AA);
            mChart.getPaint(Chart.PAINT_INFO).setTextSize(28);
            mChart.invalidate();
            return;
        }
        // 图表为空时显示
        mChart.setDescription("");//描述  设置为空
        mChart.setGridBackgroundColor(Color.WHITE); //设置表格的背景颜色
        mChart.setScaleYEnabled(false);
        mChart.setScaleXEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.getAxisRight().setEnabled(mAxisRight);
        //设置为false以禁止通过在其上双击缩放图表。
        mChart.setDoubleTapToZoomEnabled(false);
        /** * 设置数据x和样式 */
        mChart.setData(setData(yValsList));
        setLegend(mChart.getLegend(), true);//头部描述设置
        setLeftYAxis(mChart.getAxisLeft(), true, maxValue, minValue);  //设置左边样式
        mChart.getAxisLeft().setValueFormatter(new YAxisValueFormatter() {//设置左边文字描述
            @Override
            public String getFormattedValue(float value, YAxis yAxis) {
                int n = (int) value;
                String str = n + "%";
                return str;
            }
        });
        setxAxis(mChart.getXAxis(), true);//设置x 表格
        /** * 是否执行动画   false 否  true 执行 */
        setAnimate(mChart, false);
        mChart.invalidate();
    }

    private LineData setData(List<List<Entry>> yValsList) {
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        for (int i = 0; i < yValsList.size(); i++) {
            //前期图表只展示两条数据 多的不显示
            //TODO   后期显示条数问题在此处理  还有在DetailActivity中处理显示问题
            if (i > 1) {
                continue;
            }
            int color=Color.BLUE;
            if (getTypes() != null && getTypes().size() > i) {
                int type = getTypes().get(i);
                Log.d("yu","setData---type-->" + i + "-->" + type);
                switch (type) {
                    case 0:
                        color = Color.BLUE;//设置字体颜色  蓝色 实际值
                        break;
                    case 1:
                        color = Color.GRAY;//设置字体颜色  灰色目标值
                        break;
                    default:
                        color = Color.BLACK;//设置字体颜色  其他颜色的实际值
                        break;
                }
            } else {
                color = Color.RED;//设置字体颜色  其他颜色的实际值
            }
            dataSets.add(setYVals1Data1(yValsList.get(i), color)); // add the datasets
        }
        // create a data object with the datasets
        return setLineData(new LineData(dateTime, dataSets), false);
    }


    /**
     * 设置 折线图 蓝色折线样式
     *
     * @return
     */
    private LineDataSet setYVals1Data1(List<Entry> yVals1, int color) {
        /**         * 设置 蓝色折线的数据         */
        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals1, "Actual");
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setColor(getColor(mContext, color));//设置字体颜色  其他颜色的实际值
        set1.setCircleColor(getColor(mContext, color));
        set1.setLineWidth(2f);
        set1.setCircleSize(3f);
        set1.setHighlightEnabled(true);
        set1.setHighLightColor(getColor(mContext, Color.BLUE));
        set1.setHighlightLineWidth(2f);
        set1.setDrawHorizontalHighlightIndicator(false);
        set1.setDrawVerticalHighlightIndicator(true);
        set1.setDrawCircleHole(false);
        return set1;
    }

    private void setAnimate(LineChart mChart, boolean animateEnabled) {
        if (animateEnabled) {
            mChart.animateY(1500); //执行动画
        }
    }

    public List<Integer> getTypes() {
        return types;
    }

    public void setTypes(List<Integer> types) {
        this.types = types;
    }

}
