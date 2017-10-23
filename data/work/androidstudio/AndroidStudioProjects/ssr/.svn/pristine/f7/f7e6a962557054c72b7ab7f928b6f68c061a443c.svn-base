package Tool;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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
import java.util.logging.Logger;
import Tool.statistics.Statics;
import ui.activity.FinancialSalaryStastisticsActivity;
import ui.activity.OfficeDirActivity;
import ui.adpter.FinancialSalaryStatisticsAdapter;
import ui.adpter.SourceManagementAdapter;

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
            case 9:
                select = Statics.fssArrayList.size();
                break;
            case 10:
                select = Statics.projectCycleDataList.size();
                break;
            case 11:
                select = Statics.projectPeoplePageDataList.get(0).getRows().size();
        }
        for (int i = 0; i < select; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        if(FinancialSalaryStastisticsActivity.salartBoolean){
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)) + 3;
            FinancialSalaryStastisticsActivity.salartBoolean = false;
        }else{
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)) + 70;
        }

        Log.d("BillingStatisticsActivi", "高度" + totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)) + 10 + "ss");
        listView.setLayoutParams(params);
    }

    public static void setPoint(final EditText point) {//限制输入小数位数 金额
        point.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 2+1);
                        point.setText(s);
                        point.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    point.setText(s);
                    point.setSelection(2);
                }
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        point.setText(s.subSequence(0, 1));
                        point.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public static ArrayList<String> getFileName(String path) {//获取路径下的所有文件名
        ArrayList<String> list = new ArrayList<>() ;
        File file = new File(path);
        File[] array = file.listFiles();
        for(int i=0;i<array.length;i++){
            if(array[i].isFile()){
                //Toast.makeText(SourceManagementAdapter.activity, ""+array[i].getName(), Toast.LENGTH_SHORT).show();
                list.add(array[i].getName());
            }else if(array[i].isDirectory()){
                //getFile(array[i].getPath());
            }
        }
        return list;
    }


    //获取连接wifi的名字
    public static String getWifiName(Activity activity){
        WifiManager wifiManager = (WifiManager) activity.getSystemService(activity.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        Log.d("wifiInfo", wifiInfo.toString());
        Log.d("SSID",wifiInfo.getSSID());
        return wifiInfo.getSSID();
    }

    //获取网络时间
    public static String getWifiTime(){
        URL url = null;//取得资源对象
        try {
            url = new URL("http://www.baidu.com");
            URLConnection uc = url.openConnection();//生成连接对象
            uc.connect(); //发出连接
            long ld = uc.getDate(); //取得网站日期时间
            Date date = new Date(ld);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:aa");
            Log.d("ToolUtils", "网络时间" + sdf.format(date));
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "网络时间异常";
        }
    }

    /**
     * 解析返回下载数据
     *
     * @param soapObject
     */
    public static void parseSoapObjectDownload(String inputText,Activity activity) {// http://bbs.csdn.net/topics/391110763
        //将要写入文件路径
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try {
            //out=activity.openFileOutput("data", context.MODE_PRIVATE);//http://www.cnblogs.com/elleniou/archive/2012/05/17/2505630.html
            out=activity.openFileOutput(Environment.getExternalStorageDirectory()+"/erp/person_history/a.gif", activity.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }


    //将文件转化为二进制
    public static byte[] fileToTwoFlow(String imagePath)  {
        FileInputStream fs = null;
        ByteArrayOutputStream outStream;
        try {
            fs = new FileInputStream(imagePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        outStream = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[2048];
            int len = 0;
            while (-1 != (len = fs.read(buffer))) {
                outStream.write(buffer,0,len);
            }
            outStream.close();
            fs.close();
        } catch (IOException e) {
                e.printStackTrace();
            }

        return outStream.toByteArray();
    }
    //将文件写入到磁盘
    public static void writeImageToDisk(String pathStr, byte[] img, String fileName) {

        try {
            File file = new File(pathStr + fileName);
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(img);
            fops.flush();
            fops.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(SourceManagementAdapter.activity, "写入异常", Toast.LENGTH_SHORT).show();
        }

    }

    //连续点击超过3秒执行
    public static long muchClick(long exitTime){
        if((System.currentTimeMillis()-exitTime) > 3000){
            exitTime = System.currentTimeMillis();
            return exitTime;
        }
        return 0;
    }

}
