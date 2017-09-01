package ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.admin.erp.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import Tool.ToolUtils;
import Tool.crash.BaseActivity;
import Tool.customerWidget.NoscrollListView;
import Tool.customerWidget.PullScrollView;
import Tool.customerWidget.SyncHorizontalScrollView;
import Tool.statistics.Statics;
import http.BillingStatisticsHttpPost;
import http.HttpBasePost;
import http.HttpTypeConstants;
import model.CompanyDepartment;
import model.FinancialSalaryStatistics;
import ui.adpter.CustomerBillingStatisticsAdapter;
import ui.adpter.FinancialSalaryStatisticsAdapter;
import ui.adpter.LeftStatisticsAdpter;
import ui.fragement.AttendanceZhuFragment;
/**
 * 工资统计
 * */
public class FinancialSalaryStastisticsActivity extends BaseActivity implements android.os.Handler.Callback{
    public static LeftStatisticsAdpter leftStatisticsAdpter;
    public static FinancialSalaryStatisticsAdapter fssAdapter;
    private ViewGroup tableTitle,tableTitle1;
    private static NoscrollListView mLeft;
    private static NoscrollListView mData;
    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;
    public static Activity activity;
    public static ProgressDialog progressDialog = null;//加载数据显示进度条
    public LayoutInflater inflater;
    private Spinner yearSpinner,monthSpinner,departmentSpinner,nameSpinner;
    public static ArrayAdapter<String> arr_adapter ,yearAdapter,monthAdapter,arr_adapterName,departmentAdapter;
    private static List<String> data_list;
    private String nameSpinnerString,yearSpinnerString,monthSpinnerString,departmentSpinnerString;
    public static ArrayList<String> yearlist;
    private int monthPosition,yearPosition;
    private ImageView add,search;
    public static int page = 1;
    private Intent in;
    private HashMap<String,String> param;
    private PullScrollView pullScrollView;
    private Handler handler ;
    public static boolean salartBoolean = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("工资统计");
        setContentView(R.layout.activity_financial_salary_stastistics);

        //添加返回按钮
        ToolUtils.backButton(this);
        initView();
        spinnerType();
        //从服务器获取数据
        //空查询
        //page = 1;//显示页数
        //刚进入页面就要显示数据
        progressDialog = ProgressDialog.show(activity, "请稍等...", "获取数据中...", true);//显示进度条
        param = new HashMap<>();
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        yearSpinnerString = Integer.toString(c.get(Calendar.YEAR));
        monthSpinnerString = Integer.toString(c.get(Calendar.MONTH));
        param.put("year",yearSpinnerString);
        param.put("month",monthSpinnerString);
        param.put("orgId","8d670a31e1cb42a9843d036d9aaefe98");//默认大连翼峰软件有限公司
        Log.d("FinancialSalaryStastist", "默认年月:" + yearSpinnerString + monthSpinnerString + departmentSpinnerString);
        HttpBasePost.postHttp(Statics.FinancialSalaryGetWXStaffPayrollListUrl,param, HttpTypeConstants.FinancialSalaryGetWXStaffPayrollListUrlType);
        leftStatisticsAdpter = new LeftStatisticsAdpter(activity, Statics.fssArrayList);
        mLeft.setAdapter(leftStatisticsAdpter);
        //测量高度
        //ToolUtils.setListViewHeightBasedOnChildren(mLeft,9);
        fssAdapter = new FinancialSalaryStatisticsAdapter(activity,Statics.fssArrayList);
        mData.setAdapter(fssAdapter);
        //下拉刷新
        handler = new Handler(getMainLooper(), this);
        pullScrollView.setOnRefreshListener(new PullScrollView.onRefreshListener() {

            @Override
            public void refresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //表格数据刷新
                        HttpBasePost.postHttp(Statics.FinancialSalaryGetWXStaffPayrollListUrl,param, HttpTypeConstants.FinancialSalaryGetWXStaffPayrollListUrlType);
                        pullScrollView.stopRefresh();
                    }
                }, 5000);
            }
        });
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
        arr_adapterName = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_display_style, R.id.txtvwSpinner, data_list);
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
        yearAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_display_style, R.id.txtvwSpinner, yearlist);
        //设置样式
        yearAdapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        yearSpinner.setAdapter(yearAdapter);
        yearAdapter.notifyDataSetChanged();
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
                monthPosition=i;
                break;
            }
        }
        //适配器
        monthAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_display_style, R.id.txtvwSpinner, monthList);
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

        //适配器 企业部门
        data_list = new ArrayList<>();

        for(CompanyDepartment companyDepartment:Statics.companyDepartmentsArrayList){
            /*if(companyDepartment.getOrg_name().length()>6){
                data_list.add(companyDepartment.getOrg_name().substring(0,6));
            }else{
                data_list.add(companyDepartment.getOrg_name());
            }*/
            data_list.add(companyDepartment.getOrg_name());
        }
        departmentAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        departmentAdapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        departmentSpinner.setAdapter(departmentAdapter);
        departmentAdapter.notifyDataSetChanged();
        departmentSpinner.setSelection(2);
        data_list = null;
        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*if(position == 0){
                    monthSpinnerString = "全部";
                }else{
                    monthSpinnerString = finalMonthList.get(position);
                }*/
                departmentSpinnerString = Statics.companyDepartmentsArrayList.get(position).getId();
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

    private void initView() {
        activity = FinancialSalaryStastisticsActivity.this;
        mLeft = (NoscrollListView) findViewById(R.id.lv_left);
        mData = (NoscrollListView) findViewById(R.id.lv_data);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);
        mDataHorizontal.setScrollView(mHeaderHorizontal);
        mHeaderHorizontal.setScrollView(mDataHorizontal);
        tableTitle = (ViewGroup) findViewById(R.id.table_title);
        tableTitle.setBackgroundColor(Color.rgb(230, 240, 255));
        tableTitle1 = (ViewGroup) findViewById(R.id.table_title1);
        tableTitle1.setBackgroundColor(Color.rgb(230, 240, 255));
        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        monthSpinner = (Spinner) findViewById(R.id.monthSpinner);
        departmentSpinner = (Spinner) findViewById(R.id.departmentSpinner);
        nameSpinner = (Spinner) findViewById(R.id.nameSpinner);
        //add = (ImageView) findViewById(R.id.add);
        //add.setOnClickListener(o);
        search = (ImageView) findViewById(R.id.search);
        search.setOnClickListener(o);
        pullScrollView = (PullScrollView) findViewById(R.id.test);

    }

    View.OnClickListener o = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.add://添加工资单
                    in = new Intent(activity,AddFinancialSalaryActivity.class);
                    startActivity(in);
                    break;
                case R.id.search://条件检索
                    if(nameSpinnerString.equals("全部")){
                        nameSpinnerString = "";
                    }
                    progressDialog = ProgressDialog.show(activity, "请稍等...", "获取数据中...", true);//显示进度条
                    param = new HashMap<>();
                    param.put("userId",nameSpinnerString);
                    param.put("year",yearSpinnerString);
                    param.put("month",monthSpinnerString);
                    param.put("orgId",departmentSpinnerString);
                    Log.d("FinancialSalaryStastist", "ces");
                    Log.d("FinancialSalaryStastist", "检索条件:"+nameSpinnerString+"@"+yearSpinnerString+"@"+monthSpinnerString+"??"+departmentSpinnerString+"!!"+nameSpinnerString);
                    HttpBasePost.postHttp(Statics.FinancialSalaryGetWXStaffPayrollListUrl,param, HttpTypeConstants.FinancialSalaryGetWXStaffPayrollListUrlType);
                    break;
            }
        }
    } ;

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

    public static void AdapterRefresh(String type) {//刷新adapter
        switch (type) {
            case "salaryAdapter":
                Log.d("FinancialSalaryStastist", "刷新" + Statics.fssArrayList.size() + "ss");
                salartBoolean = true;
                ToolUtils.setListViewHeightBasedOnChildren(mLeft,9);
                leftStatisticsAdpter.notifyDataSetChanged();//刷新
                fssAdapter.notifyDataSetChanged();//刷新
                progressDialog.dismiss();
                break;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }
}
