package ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.admin.erp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import Tool.statistics.Statics;
import http.AttendanceStatisticsHttpPost;
import http.BillingStatisticsHttpPost;
import http.Constants;
import model.AttendanceStatistics;
import model.CustomerBillingStatistics;
import model.TimeBillingStatistics;
import model.XiangxiBillingStatistics;
import portface.LazyLoadFace;
import ui.adpter.AttendanceStatisticsAdapter;
import ui.adpter.CustomerBillingStatisticsAdapter;
import ui.adpter.TimeBillingStatisticsAdapter;
import ui.adpter.XiangxiBillingStatisticsAdapter;
import ui.fragement.AttendanceChartsFragmentActivity;
import ui.fragement.ChartsFragementActivity;

public class AttendanceStatisticsActivity extends AppCompatActivity implements LazyLoadFace{
    public static ListView attendListView;
    private ViewGroup tableTitle;
    private AttendanceStatisticsHttpPost attendanceStatisticsHttpPost;
    private Spinner nameSpinner, yearSpinner ,monthSpinner;
    private ImageView search,graph;
    private static List<String> data_list;
    public static ArrayAdapter<String> arr_adapter ;
    public static ArrayAdapter<String> arr_adapterName;
    private String nameSpinnerString = "024001", yearSpinnerString = null, monthSpinnerString = null;
    private List<AttendanceStatistics> attendanceStatisticsList;
    public static AttendanceStatisticsAdapter attendanceAdapter;
    private AlertDialog dlg;
    public static ProgressDialog progressDialog = null;//加载数据显示进度条
    //考勤统计
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("考勤统计");
        setContentView(R.layout.activity_attendance_statistics);


        init();
        spinnerType();
        yearSpinnerString = "2017";//默认赋值
        //首次访问
        //获取当前年月，默认查询当月
        Calendar now = Calendar.getInstance();
        int month =now.get(Calendar.MONTH)+1;
        month--;//默认显示上个月的数据
        System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");
        progressDialog = ProgressDialog.show(AttendanceStatisticsActivity.this, "请稍等...", "获取数据中...", true);//显示进度条
        attendanceStatisticsHttpPost = new AttendanceStatisticsHttpPost();
        attendanceStatisticsHttpPost.searchStatisticsHttp(Statics.AttendanceStatisticsSearchUrl, "", Integer.toString(now.get(Calendar.YEAR)), Integer.toString(month), AttendanceStatisticsActivity.this);
        attendanceStatisticsList =new ArrayList<>();
        attendanceStatisticsList = Statics.attendanceStatisticsList;
        attendanceAdapter = new AttendanceStatisticsAdapter(AttendanceStatisticsActivity.this, Statics.attendanceStatisticsList);
        attendListView.setAdapter(attendanceAdapter);
        search.setOnClickListener(o);
        graph.setOnClickListener(o);

    }
    View.OnClickListener o = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.search:
                    progressDialog = ProgressDialog.show(AttendanceStatisticsActivity.this, "请稍等...", "获取数据中...", true);//显示进度条
                    attendanceStatisticsHttpPost.searchStatisticsHttp(Statics.AttendanceStatisticsSearchUrl, nameSpinnerString, yearSpinnerString, monthSpinnerString, AttendanceStatisticsActivity.this);
                    attendanceStatisticsList = Statics.attendanceStatisticsList;
                    attendanceAdapter = new AttendanceStatisticsAdapter(AttendanceStatisticsActivity.this, attendanceStatisticsList);
                    attendListView.setAdapter(attendanceAdapter);
                    break;
                case R.id.zhuXing:
                    Log.d("sss","img");
                    Intent intent = new Intent(AttendanceStatisticsActivity.this,AttendanceChartsFragmentActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
    private void spinnerType() {

        //姓名查询
        AttendanceStatisticsHttpPost ash = new AttendanceStatisticsHttpPost();
        ash.searchStaffNameHttp(Statics.AttendanceStatisticsSearchUrl,AttendanceStatisticsActivity.this);
        //数据 员工姓名
        data_list = new ArrayList<>();
        data_list.add(0,"全部");
        for (int i=0;i<Statics.searchName.size();i++){
            data_list .add(Statics.searchName.get(i));
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
                if(position == 0){
                    nameSpinnerString = "全部";
                }else{
                    nameSpinnerString = Statics.searchNameId.get(--position);
                    for (int i=0;i<Statics.searchNameId.size();i++){
                        Log.d("jjjj",Statics.searchNameId.get(i));
                    }
                }
                data_list = null;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //数据 年
        //httpPost =new HttpPost();
        //httpPost.accountTypeSearchHttp(Constants.AccountTypeUrl, AccountManagementActivity.this);
        ArrayList<String> yearlist = new ArrayList<>();
        yearlist.add("全部");
        for (int i=0;i<Statics.searchYear.size();i++){
            yearlist.add(Statics.searchYear.get(i).getAttendanceYear());
        }
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, yearlist);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        yearSpinner.setAdapter(arr_adapter);
        yearlist = null;
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    yearSpinnerString = "全部";
                }else{
                    yearSpinnerString = Statics.searchYear.get(--position).getAttendanceYear();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //数据 月
        //httpPost =new HttpPost();
        //httpPost.accountTypeSearchHttp(Constants.AccountTypeUrl, AccountManagementActivity.this);
        ArrayList<String> monthList = new ArrayList<>();
        monthList.add("全部");
        for (int i=1;i <= 12;i++){
            monthList.add(i+"");
        }
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, monthList);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        monthSpinner.setAdapter(arr_adapter);
        final ArrayList<String> finalMonthList = monthList;
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    monthSpinnerString = "全部";
                }else{
                    monthSpinnerString = finalMonthList.get(position);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }
    public void init() {
        attendListView = (ListView) findViewById(R.id.lv);
        tableTitle = (ViewGroup) findViewById(R.id.table_title);
        tableTitle.setBackgroundColor(Color.rgb(177, 173, 172));
        nameSpinner = (Spinner) findViewById(R.id.nameSpinner);
        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        monthSpinner = (Spinner) findViewById(R.id.monthSpinner);
        search = (ImageView) findViewById(R.id.search);
        graph = (ImageView) findViewById(R.id.zhuXing);


    }
    @Override
    public void AdapterRefresh(String type) {//刷新adapter
        switch (type) {
            case "attendAdapter":
                Log.d("adpter","adpter");
                attendanceAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
                break;
            case "searchName":
                data_list = new ArrayList<>();
                data_list.add(0,"全部");
                for (int i=0;i<Statics.searchName.size();i++){
                    data_list .add(Statics.searchName.get(i));
                }
                arr_adapterName.notifyDataSetChanged();
                break;
        }
    }
}
