package ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.admin.erp.R;
import com.github.mikephil.charting.charts.BarChart;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import Tool.StatisticalGraph.CombinedBarChartUtil;
import Tool.ToolUtils;
import Tool.crash.BaseActivity;
import Tool.customerWidget.PullScrollView;
import Tool.statistics.Statics;
import http.BillingStatisticsHttpPost;
import http.HttpBasePost;
import http.HttpTypeConstants;
import model.AttendanceStaffBelongProject;
import model.AttendanceStatistics;
import model.AttendanceWxDetaSearch;
import portface.LazyLoadFace;
import ui.adpter.AttendanceBelongProjectStatisticsAdapter;
import ui.adpter.AttendanceStatisticsAdapter;
import ui.adpter.AttendanceXiangxiStatisticsAdapter;
import ui.adpter.CustomerBillingStatisticsAdapter;
import ui.adpter.MonthXiangxiBillingStatisticsAdapter;
import ui.fragement.AttendanceChartsFragmentActivity;
import ui.fragement.AttendanceZhuFragment;

public class AttendanceStatisticsActivity extends BaseActivity implements android.os.Handler.Callback{
    public static ListView attendListView;
    private ViewGroup tableTitle;
    private Spinner nameSpinner, yearSpinner ,monthSpinner;
    private ImageView search;
    private static List<String> data_list;
    public static ArrayAdapter<String> arr_adapter ,yearAdapter,monthAdapter,arr_adapterName;
    private String nameSpinnerString = "024001";
    private String yearSpinnerString = null;
    private static String monthSpinnerString = null;
    private List<AttendanceStatistics> attendanceStatisticsList;
    public static AttendanceStatisticsAdapter attendanceAdapter;
    private AlertDialog dlg;
    private AlertDialog projectDlg;
    public static ProgressDialog progressDialog = null;//加载数据显示进度条
    public static ArrayList<String> yearlist;
    private int monthPosition,yearPosition;
    private HashMap<String,String> param;
    private int month;
    private Calendar now;
    private ListView listView,XiangxilistView;
    private static AttendanceXiangxiStatisticsAdapter axsAdapter;
    private static AttendanceBelongProjectStatisticsAdapter abpsAdapter;
    private ArrayList<AttendanceWxDetaSearch> awds ;
    private ArrayList<AttendanceStaffBelongProject> asbp;
    public static Activity activity;
    //统计图
    private static BarChart mCombinedChart;
    private static TextView titleMonth;
    private PullScrollView pullScrollView;
    private Handler handler ;
    //考勤统计
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("考勤统计");
        setContentView(R.layout.activity_attendance_statistics);

        //添加返回按钮
        ToolUtils.backButton(this);
        init();
        spinnerType();
        yearSpinnerString = "2017";//默认赋值
        //首次访问
        //获取当前年月，默认查询当月
        now = Calendar.getInstance();
        month =now.get(Calendar.MONTH)+1;
        month--;//默认显示上个月的数据
        System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");
        progressDialog = ProgressDialog.show(AttendanceStatisticsActivity.this, "请稍等...", "获取数据中...", true);//显示进度条

        //okHttp //上传参数
        if("全部".equals(Integer.toString(now.get(Calendar.YEAR)))){
            yearSpinnerString = "2017";
        }else{
            yearSpinnerString = Integer.toString(now.get(Calendar.YEAR));
        }
        if("全部".equals(monthSpinnerString)){
            monthSpinnerString = "";
        }else{
            monthSpinnerString = Integer.toString(month);
        }
        param=new HashMap<>();
        param.put("userId","");
        param.put("year",yearSpinnerString);
        param.put("month",monthSpinnerString);

        HttpBasePost.postHttp(Statics.AttendanceStatisticsSearchUrl,param, HttpTypeConstants.AttendanceStatisticsSearchUrlType);
        //attendanceStatisticsHttpPost.searchStatisticsHttp(Statics.AttendanceStatisticsSearchUrl, "", Integer.toString(now.get(Calendar.YEAR)), Integer.toString(month), AttendanceStatisticsActivity.this);
        attendanceStatisticsList =new ArrayList<>();
        attendanceStatisticsList = Statics.attendanceStatisticsList;
        attendanceAdapter = new AttendanceStatisticsAdapter(AttendanceStatisticsActivity.this, Statics.attendanceStatisticsList);
        attendListView.setAdapter(attendanceAdapter);
        attendListView.setOnItemClickListener(ao);
        search.setOnClickListener(this);
        activity = AttendanceStatisticsActivity.this;
        mCombinedChart.setOnClickListener(this);

        //下拉刷新
        handler = new Handler(getMainLooper(), this);
        pullScrollView.setOnRefreshListener(new PullScrollView.onRefreshListener() {

            @Override
            public void refresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //表格数据刷新
                        if("全部".equals(nameSpinnerString)){
                            nameSpinnerString = "";
                        }else{

                        }
                        param=new HashMap<>();
                        param.put("userId",nameSpinnerString);
                        param.put("year",yearSpinnerString);
                        param.put("month",monthSpinnerString);
                        HttpBasePost.postHttp(Statics.AttendanceStatisticsSearchUrl,param, HttpTypeConstants.AttendanceStatisticsSearchUrlType);
                        pullScrollView.stopRefresh();
                    }
                }, 5000);
            }
        });
    }

    //显示详情
    AdapterView.OnItemClickListener ao = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //xiangxiAlertDialog(position);
            //显示所在项目组对话框
            belongProjectAlertDialog(position);
        }
    };

    private void belongProjectAlertDialog(int position){//获取员工所在项目组
        final String yearString = Statics.attendanceStatisticsList.get(position).getTAttendanceSumYear();//选中年
        final String monthString = Statics.attendanceStatisticsList.get(position).getTAttendanceSumMonth();//选中月
        final String userId = Statics.attendanceStatisticsList.get(position).getUserId();
        //年，月份，员工姓名
        param=new HashMap<>();
        param.put("year",yearString);
        param.put("month",monthString);
        param.put("userId",userId);
        HttpBasePost.postHttp(Statics.GetWXProjectSearchUrl,param,HttpTypeConstants.GetWXProjectSearchUrl);
        //显示对话框，在对话框中使用ListView
        listView = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(AttendanceStatisticsActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        final View staffLayout = inflater.inflate(R.layout.attendancestatistics_staffproject_dialog_item, null);//获取自定义布局
        listView = (ListView) staffLayout.findViewById(R.id.project_lv);
        asbp = new ArrayList<>();
        asbp = Statics.staffBelongProjectArrayList;
        abpsAdapter = new AttendanceBelongProjectStatisticsAdapter(AttendanceStatisticsActivity.this, asbp);
        Log.d("AttendanceStatisticsAct", "第几次");
        listView.setAdapter(abpsAdapter);
        //创建人就是用户名
        builder.setView(staffLayout);
        projectDlg = builder.create();
        projectDlg.show();
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lps = projectDlg.getWindow().getAttributes();
        lps.width = display.getWidth(); //设置宽度
        projectDlg.getWindow().setAttributes(lps);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ida = Statics.staffBelongProjectArrayList.get(position).getId();
                xiangxiAlertDialog(yearString,monthString,userId,position,ida);
            }
        });
    }

    private void xiangxiAlertDialog(String yearString, String monthString, String userId, int positon, String ida) {//详细信息对话框
        //String yearString = Statics.attendanceStatisticsList.get(position).getTAttendanceSumYear();//选中年
        //String monthString = Statics.attendanceStatisticsList.get(position).getTAttendanceSumMonth();//选中月
        //String userId = Statics.attendanceStatisticsList.get(position).getUserId();
        Log.d("AttendanceStatisticsAct", "--"+ida);
        Log.d("AttendanceStatisticsAct", yearString);
        Log.d("AttendanceStatisticsAct", monthString);
        Log.d("AttendanceStatisticsAct", userId);
        //年，月份，员工姓名
        param=new HashMap<>();
        param.put("year",yearString);
        param.put("month",monthString);
        param.put("userId",userId);
        param.put("projectId",ida);
        HttpBasePost.postHttp(Statics.GetWXAttendanceDetaSearchUrl,param,HttpTypeConstants.GetWXAttendanceDetaSearchUrl);
        //显示对话框，在对话框中使用ListView
        XiangxilistView = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(AttendanceStatisticsActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        final View customerLayout = inflater.inflate(R.layout.attendancestatistics_month_dialog_detailed_item, null);//获取自定义布局
        XiangxilistView = (ListView) customerLayout.findViewById(R.id.month_lv);
        awds = Statics.attendanceWxDetaSearchArrayList;
        axsAdapter = new AttendanceXiangxiStatisticsAdapter(AttendanceStatisticsActivity.this, awds);
        XiangxilistView.setAdapter(axsAdapter);
        //创建人就是用户名
        builder.setView(customerLayout);
        dlg = builder.create();
        dlg.show();
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dlg.getWindow().getAttributes();
        lp.width = display.getWidth(); //设置宽度
        dlg.getWindow().setAttributes(lp);
    }

    //返回按钮事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.search:
                progressDialog = ProgressDialog.show(AttendanceStatisticsActivity.this, "请稍等...", "获取数据中...", true);//显示进度条
                //okHttp //上传参数
                if("全部".equals(nameSpinnerString)){
                    nameSpinnerString = "";
                }else{

                }
                /*if("全部".equals(Integer.toString(now.get(Calendar.YEAR)))){
                    yearSpinnerString = "2017";
                }else{
                    yearSpinnerString = Integer.toString(now.get(Calendar.YEAR));
                }
                if("全部".equals(monthSpinnerString)){
                    monthSpinnerString = "";
                }else{
                    monthSpinnerString = Integer.toString(month);
                }*/
                param=new HashMap<>();
                param.put("userId",nameSpinnerString);
                param.put("year",yearSpinnerString);
                param.put("month",monthSpinnerString);
                HttpBasePost.postHttp(Statics.AttendanceStatisticsSearchUrl,param, HttpTypeConstants.AttendanceStatisticsSearchUrlType);
                //attendanceStatisticsHttpPost.searchStatisticsHttp(Statics.AttendanceStatisticsSearchUrl, nameSpinnerString, yearSpinnerString, monthSpinnerString, AttendanceStatisticsActivity.this);
                attendanceStatisticsList = Statics.attendanceStatisticsList;
                attendanceAdapter = new AttendanceStatisticsAdapter(AttendanceStatisticsActivity.this, attendanceStatisticsList);
                attendListView.setAdapter(attendanceAdapter);

                break;
            case R.id.barChart:
                Intent intent = new Intent(AttendanceStatisticsActivity.this,AttendanceChartsFragmentActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void spinnerType() {
        //姓名查询
        /*AttendanceStatisticsHttpPost ash = new AttendanceStatisticsHttpPost();
        ash.searchStaffNameHttp(Statics.AttendanceStatisticsSearchUrl,AttendanceStatisticsActivity.this);*/
        //HttpBasePost.postHttp(Statics.AttendanceStatisticsSearchUrl,null,HttpTypeConstants.SearchYearUrlType);
        //数据 员工姓名
        data_list = new ArrayList<>();
        data_list.add(0,"全部");
        for (int i=0;i<Statics.searchName.size();i++){
            data_list .add(Statics.searchName.get(i).getNick_name());
            Log.d("AttendanceStatisticsAct", "全部姓名：" + Statics.searchName.get(i).toString());
        }
        for (int i=0;i<Statics.searchNameId.size();i++){
            //data_list .add(Statics.searchNameId.get(i));
            Log.d("AttendanceStatisticsAct", "全部Id：" + Statics.searchNameId.get(i).toString());
        }
        //适配器
        arr_adapterName = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        arr_adapterName.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        nameSpinner.setAdapter(arr_adapterName);
        data_list = null;
        nameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.d("AttendanceStatisticsAct", "校对姓名：" + Statics.searchNameId.get(1).toString());
                if(position == 0){
                    nameSpinnerString = "全部";
                }else{
                    nameSpinnerString = Statics.searchName.get(--position).getUser_id();
                    Log.d("AttendanceStatisticsAct", "选中姓名：" + nameSpinnerString);
                    for (int i=0;i<Statics.searchNameId.size();i++){
                        Log.d("AttendanceStatisticsAct",Statics.searchNameId.get(i));
                        Log.d("AttendanceStatisticsAct","下拉："+Statics.searchName.get(i));
                    }
                }
                data_list = null;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //数据 年 默认显示上个月的
        //httpPost =new HttpPost();
        //httpPost.accountTypeSearchHttp(Constants.AccountTypeUrl, AccountManagementActivity.this)
        yearlist = new ArrayList<>();
        //yearlist.add("全部");
        for (int i=0;i<Statics.searchYear.size();i++){
            yearlist.add(Statics.searchYear.get(i).getAttendanceYear());
        }
        //年份回显
        String year=currentTime("year");
        for (int i=0;i<yearlist.size();i++){
            String yearString = yearlist.get(i);
            Log.d("yearlist","yearString:"+yearString);
            if(yearSpinner!=null&&year.equals(yearString)){
                Log.d("yearlist","----------");
                Log.d("yearlist","i:"+i+"{}"+year+".."+yearString);
                yearPosition=i;
                break;
            }
        }
        //适配器
        yearAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, yearlist);
        //设置样式
        yearAdapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        yearSpinner.setAdapter(yearAdapter);
        yearAdapter.notifyDataSetChanged();
        Log.d("yearlist","yearPosition:"+yearPosition);
        yearSpinner.setSelection(yearPosition);
        yearlist = null;
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*if(position == 0){
                    yearSpinnerString = "全部";
                }else{
                    yearSpinnerString = Statics.searchYear.get(--position).getAttendanceYear();
                }*/
                yearSpinnerString = Statics.searchYear.get(position).getAttendanceYear();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //数据 月
        ArrayList<String> monthList = new ArrayList<>();
        //monthList.add("全部");
        for (int i=1;i <= 12;i++){
            monthList.add(i+"");
        }
        //月份回显 上一个月的
        String month=currentTime("month");
        for (int i=0;i<monthList.size();i++){
            String monthString = monthList.get(i);
            Log.d("monthString","monthString:"+monthString);
            if(monthSpinner!=null&&month.equals(monthString)){
                Log.d("monthString","----------");
                Log.d("monthString","i:"+i+"{}"+year+".."+monthString);
                monthPosition=i;
                break;
            }
        }
        //适配器
        monthAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, monthList);
        //设置样式
        monthAdapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        monthSpinner.setAdapter(monthAdapter);
        monthAdapter.notifyDataSetChanged();
        Log.d("yearlist","yearlist:"+monthPosition);
        monthSpinner.setSelection(monthPosition);
        final ArrayList<String> finalMonthList = monthList;
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*if(position == 0){
                    monthSpinnerString = "全部";
                }else{
                    monthSpinnerString = finalMonthList.get(position);
                }*/
                monthSpinnerString = finalMonthList.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private String currentTime(String type) {
        //获取当前时间
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        String year = Integer.toString(c.get(Calendar.YEAR));//取当前年
        String month = Integer.toString(c.get(Calendar.MONTH));//取上一个月的数据
        if(type.equals("year")){
            return year;
        }else if(type.equals("month")){
            return month;
        }
        else{
            Log.d("error","出错了");
        }
        return null;
    }

    public void init() {
        attendListView = (ListView) findViewById(R.id.lv);
        tableTitle = (ViewGroup) findViewById(R.id.table_title);
        tableTitle.setBackgroundColor(Color.rgb(230, 240, 255));
        nameSpinner = (Spinner) findViewById(R.id.nameSpinner);
        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        monthSpinner = (Spinner) findViewById(R.id.monthSpinner);
        search = (ImageView) findViewById(R.id.search);
        mCombinedChart = (BarChart)findViewById(R.id.barChart);
        titleMonth = (TextView) findViewById(R.id.titleMonth);
        pullScrollView = (PullScrollView) findViewById(R.id.test);

    }
    public static void AdapterRefresh(String type) {//刷新adapter
        switch (type) {
            case "attendAdapter":
                attendanceAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
                ToolUtils.setListViewHeightBasedOnChildren(attendListView,7);
                AttendanceZhuFragment attendanceZhuFragment =new AttendanceZhuFragment();
                attendanceZhuFragment.setGrayValue();
                attendanceZhuFragment.initData(activity ,mCombinedChart,true);
                titleMonth.setText(monthSpinnerString+"月考勤统计图");
                break;
            case "searchName":
                data_list = new ArrayList<>();
                data_list.add(0,"全部");
                for (int i=0;i<Statics.searchName.size();i++){
                    data_list .add(Statics.searchName.get(i).getNick_name());
                }
                arr_adapterName.notifyDataSetChanged();
                break;
            case "attendXiangxi":
                axsAdapter.notifyDataSetChanged();
                break;
            case "staffBelongProject":
                abpsAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }
}
