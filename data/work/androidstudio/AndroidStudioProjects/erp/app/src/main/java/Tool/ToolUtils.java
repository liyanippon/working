package Tool;


import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public static void selectColor(AdapterView<?> parent , int position) {//计算y轴最大值
        for (int i = 0; i < parent.getCount(); i++) {
            View v = parent.getChildAt(i);

            if(v!=null){
                if (position == i) {
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

}