package Tool;


import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Tool.statistics.Statics;

/**
 * Created by admin on 2017/2/21.
 */

public class ToolUtils {
    final static int[] sizeTable = {9, 99, 999, 9999, 99999, 999999, 9999999,
            99999999, 999999999, Integer.MAX_VALUE};

    public static int sizeOfInt(int x) {//计算数字位数
        for (int i = 0; ; i++)
            if (x <= sizeTable[i])
                return i + 1;
    }

    //统计图y轴转换
    public static int tongJiTuY(List<Double> input) {//计算y轴最大值
        //取得y轴最大值
        Log.d("test","iop"+Integer.toString(input.size()));
        if (input.size() == 0){
            return 0;
        }
        int yZhi = Collections.max(input).intValue();//当前整数
        Double y = Collections.max(input);//当前浮点数
        //求整数最大值 3440-->4000,11-->20,890-->900,91-->100
        Log.v("math", "转换之前的值：" + Double.toString(y));
        int wei = sizeOfInt(yZhi);//5wei
        //int chu=1*wei;
        StringBuffer sb = new StringBuffer();
        sb.append(1);
        for (int i = 0; i < wei - 1; i++) {
            sb.append(0);
        }
        Double xiaoY = y / Integer.parseInt(sb.toString());
        Log.v("math", sb.toString());
        Log.v("math", "math:" + Double.toString(Math.ceil(xiaoY)));
        Log.v("math", "math:" + Double.toString(Math.ceil(y)));
        sb = new StringBuffer();
        sb.append((int) Math.ceil(xiaoY));
        for (int i = 0; i < wei - 1; i++) {
            sb.append(0);
        }
        Log.v("math", "转换之后的值：" + sb.toString());
        yZhi = Integer.parseInt(sb.toString());
        if (yZhi >= 0 && yZhi <= 10) {
            yZhi = 10;
        }
        return yZhi;
    }

    //listView选中变色
    public static void selectColor(AdapterView<?> parent , int position) {

        Log.d("listcolor","条目:"+position+"//"+parent.getCount());

        for (int i = 0; i < parent.getCount(); i++) {
            Log.d("listcolor","条目:"+position+"//"+i);
            View v = parent.getChildAt(i);

            if(v!=null){
                if (position == i) {
                    Log.d("listcolor",position+"//"+i);
                    v.setBackgroundColor(Color.YELLOW);
                } else {
                    v.setBackgroundColor(Color.TRANSPARENT);
                }
            }

        }
    }

    //日期处理方法
    public static String timeDateFormat(String year) {

        Log.d("klll",year);
        String yearString = year.subSequence(1, 3).toString();
        StringBuffer sb=new StringBuffer();
        sb.append("20");
        sb.append(yearString);
        return sb.toString();
    }


    public static Set compare(String[] strs){
        boolean result = false;
        //用于存放数组中出现相同的元素
        Set<String> set= new HashSet<>();
        //写一个方法把数组和set作为参数传过去
        //从第一个元素开始比较元素是不是有相同的出现
        for(int i=0;i<strs.length;i++){
            for(int j=i+1;j<strs.length;j++){
                //如果元素相同，保存到set中
                if(strs[i].equals(strs[j])){
                    set.add(strs[i]);
                    result = true;
                }
            }
        }
        return set;
    }

    //去掉ArrayList当中的重复元素，不改变位置
    public static ArrayList<Object> removeDuplicate(ArrayList<Object> arrayList) {
        if(arrayList==null||arrayList.size()==0){
            return null;
        }
        for ( int i = 0 ; i < arrayList.size() - 1 ; i ++ ) {
            for ( int j = arrayList.size() - 1 ; j > i; j -- ) {
                if (arrayList.get(j).equals(arrayList.get(i))) {
                    arrayList.remove(j);
                }
            }
        }
        return arrayList;
    }


    //添加返回按钮
    public static void backButton(AppCompatActivity appCompatActivity) {
        android.support.v7.app.ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /** * 获取指定日期是星期几
     * 参数为null时表示获取当前日期是星期几
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        if(date != null){
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekOfDays[w];
    }

    /**
     * 毫秒转换为年月日
     * */
    public static String getYearMonthDate(long million) {
        Date date = new Date(million);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 动态设置ListView的高度
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView, int select) {
        if(listView == null){
            return;
        }
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;

        switch (select){
            case 1:
                select = Statics.timeBillingStatisticsList.size();
                break;
            case 2:
                select = Statics.customerBillingStatisticsArrayList.size();
                Log.d("测量高度","select"+select);
                break;
            case 3:
                select = Statics.expressTimeList.size();
                break;
            case 4:
                select = Statics.expressPersonStatisticList.size();
                break;
            case 5:
                select = Statics.fbgwxSettlementMonthList.size();
                break;
            case 6:
                select = Statics.fbgwxscList.size();
                break;
            case 7:
                select = Statics.attendanceStatisticsList.size();
                break;
            case 8:
                select = Statics.epsXList.size();
                break;
        }
        Log.d("BillingStatisticsActivi", "大小：" + select);
        for (int i = 0; i < select; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)) + 10;
        Log.d("BillingStatisticsActivi", "高度" + totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)) + 10 + "ss");
        listView.setLayoutParams(params);
    }
}
