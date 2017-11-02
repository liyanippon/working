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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.admin.erp.R;
import com.github.mikephil.charting.charts.BarChart;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Tool.ACache;
import Tool.ToolUtils;
import Tool.crash.BaseActivity;
import Tool.customerWidget.PullScrollView;
import Tool.statistics.AchacheConstant;
import Tool.statistics.Statics;
import http.HttpBasePost;
import http.HttpTypeConstants;
import model.*;
import model.javabean.FinancialBillingGetWXSelectCustomer;
import model.javabean.FinancialBillingGetWXSelectMonthAccount;
import model.javabean.FinancialBillingGetWXsettlementMonth;
import ui.adpter.FinancialCustomerBillingStatisticsAdapter;
import ui.adpter.FinancialTimeBillingStatisticsAdapter;
import ui.adpter.MonthXiangxiBillingStatisticsAdapter;
import ui.adpter.XiangxiBillingStatisticsAdapter;
import ui.fragement.ChartsFragementActivity;
import ui.fragement.InoutComeZhuFragment;

public class FinancialStastisticsActivity extends BaseActivity implements android.os.Handler.Callback{
    public static ListView timeListView, customerListView;
    public LinearLayout linear;//设置隐藏
    public static TextView currentMoney;
    private ViewGroup tableTitle, tableTitle1;
    private Spinner typeSpinner, yearSpinner;
    private ImageView search;
    private List<String> data_list;
    public static ArrayAdapter<String> arr_adapter;
    private String typeSpinnerString = "024001", yearSpinnerString = null;
    private static List<FinancialBillingGetWXsettlementMonth> timeBillingStatisticsList;
    private List<FinancialBillingGetWXSelectCustomer> fbgwxscList;
    private List<FinancialBillingGetWXSelectMonthAccount> monthXiangXiBillingStatisticsList;
    public static FinancialTimeBillingStatisticsAdapter timeAdapter;
    public static FinancialCustomerBillingStatisticsAdapter customerAdapter;
    public static XiangxiBillingStatisticsAdapter xiangxiAdapter;
    public static MonthXiangxiBillingStatisticsAdapter monXiangXiAdapter;
    private HashMap<String,String> param;
    private AlertDialog dlg;
    private ListView listView;
    private int count=0;
    public static ProgressDialog progressDialog = null;//加载数据显示进度条
    //统计图
    private static BarChart mCombinedChart;
    private static Activity activity;
    private PullScrollView pullScrollView;
    private Handler handler ;
    private long exitTime = 0;
    ACache aCache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("账目统计");
        setContentView(R.layout.activity_financial_stastistics);

        //添加返回按钮
        ToolUtils.backButton(this);
        init();
        //spinnerType();
        activity = FinancialStastisticsActivity.this;
        yearSpinnerString = "2017";//默认赋值
        //首次访问
        progressDialog = ProgressDialog.show(FinancialStastisticsActivity.this, "请稍等...", "获取数据中...", true);//显示进度条
        //financialStatisticsHttpPost.searchCurrentMoneyHttp(Statics.FinancialBillingGetCurrentMoneyUrl);//获取当前资金情况
        HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.FINANCIAL_BILLING_GETCURRENT_MONTH_URL),null, HttpTypeConstants.FinancialBillingGetCurrentMoneyUrlType);//获取当前资金情况
        HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.FINANCIAL_BILLING_GETWXSETTLEMENT_MONTH_URL),null, HttpTypeConstants.FinancialBillingGetWXsettlementMonthUrlType);
        //financialStatisticsHttpPost.searchTimeHttp(Statics.FinancialBillingGetWXsettlementMonthUrl, "", "", FinancialStastisticsActivity.this);//
        timeBillingStatisticsList = Statics.fbgwxSettlementMonthList;
        timeAdapter = new FinancialTimeBillingStatisticsAdapter(FinancialStastisticsActivity.this, timeBillingStatisticsList);
        timeListView.setAdapter(timeAdapter);
        //search.setOnClickListener(this);
        mCombinedChart.setOnClickListener(this);
        timeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //xiangxiAlertDialog(position);//点击显示详细信息
                //点击显示月份账目明细
                //选中变色
                //ToolUtils.selectColor(parent,position);
                //确定月份
                //final String month = Statics.timeBillingStatisticsList.get(position).getMonth();
                param=new HashMap<>();
                String typeString = null;
                String monthString = null;
                yearSpinnerString = Integer.toString(Statics.fbgwxSettlementMonthList.get(position).getYe());//选中年
                monthString = Integer.toString(Statics.fbgwxSettlementMonthList.get(position).getMon());//选中月
                param.put("id",Statics.Name);
                param.put("year",yearSpinnerString);
                param.put("month",monthString);
                HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.FINANCIAL_BILLING_GETWXSELECT_MONTH_ACCOUNT_URL),param,HttpTypeConstants.FinancialBillingGetWXSelectMonthAccountUrlType);
                fbgwxscList = Statics.fbgwxscList;
                customerAdapter = new FinancialCustomerBillingStatisticsAdapter(FinancialStastisticsActivity.this, fbgwxscList);
                customerListView.setAdapter(customerAdapter);
                final String finalMonthString = monthString;
                customerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        exitTime = ToolUtils.muchClick(exitTime);
                        if(exitTime!=0) {
                            exitTime = System.currentTimeMillis();
                            xiangxiAlertDialog(position, yearSpinnerString, finalMonthString);//详细信息
                        }
                    }
                });
            }
        });

        //下拉刷新
        handler = new Handler(getMainLooper(), this);
        pullScrollView.setOnRefreshListener(new PullScrollView.onRefreshListener() {

            @Override
            public void refresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //表格数据刷新
                        HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.FINANCIAL_BILLING_GETWXSETTLEMENT_MONTH_URL),null, HttpTypeConstants.FinancialBillingGetWXsettlementMonthUrlType);
                        fbgwxscList = null;
                        customerAdapter = new FinancialCustomerBillingStatisticsAdapter(FinancialStastisticsActivity.this, fbgwxscList);
                        customerListView.setAdapter(customerAdapter);
                        pullScrollView.stopRefresh();
                    }
                }, 5000);
            }
        });
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

    private void xiangxiAlertDialog(int position,String yearSpinnerString,String monthString) {//详细信息对话框
        String typeString = null;
        //String monthString = null;
        //yearSpinnerString = Integer.toString(Statics.fbgwxSettlementMonthList.get(position).getYe());//选中年
        //monthString = Integer.toString(Statics.fbgwxSettlementMonthList.get(position).getMon());//选中月
        //递类型，月份，客户名客户名以检索
        param=new HashMap<>();
        param.put("type",typeString);
        param.put("year",yearSpinnerString);
        param.put("month",monthString);
        param.put("id",Statics.fbgwxscList.get(position).getId());
        Log.d("FinancialStastisticsAct", "年月：" + yearSpinnerString + monthString + Statics.fbgwxscList.get(position).getFy_name());
        HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.FINANCIAL_BILLING_GETWX_SELECE_CUSTOMER_DETAILS),param,HttpTypeConstants.FinancialBillingGetWXSelectCustomerDetailsUrlType);
        //financialStatisticsHttpPost.searchXqMonthBillHttp(Statics.FinancialBillingGetWXSelectMonthAccountUrl, yearSpinnerString, typeString, monthString);//
        //显示对话框，在对话框中使用ListView
        listView = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(FinancialStastisticsActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        final View customerLayout = inflater.inflate(R.layout.billingstatistics_month_dialog_detailed_item, null);//获取自定义布局
        listView = (ListView) customerLayout.findViewById(R.id.month_lv);
        //tableTitle = (ViewGroup) layout.findViewById(R.id.table_title);
        //tableTitle.setBackgroundColor(Color.rgb(230, 240, 255));
        monthXiangXiBillingStatisticsList = Statics.fbgwxsmaList;
        Log.d("FinancialStastisticsAct", monthXiangXiBillingStatisticsList.size() + "ss");
        monXiangXiAdapter = new MonthXiangxiBillingStatisticsAdapter(FinancialStastisticsActivity.this, monthXiangXiBillingStatisticsList);
        listView.setAdapter(monXiangXiAdapter);
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
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.search:
                Log.v("test2", "R.id.search");
                progressDialog = ProgressDialog.show(FinancialStastisticsActivity.this, "请稍等...", "获取数据中...", true);//显示进度条
                //financialStatisticsHttpPost.searchTimeHttp(Statics.TimeSearchUrl, yearSpinnerString, typeSpinnerString, FinancialStastisticsActivity.this);//
                HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.FINANCIAL_BILLING_GETWXSETTLEMENT_MONTH_URL),null, HttpTypeConstants.FinancialBillingGetWXsettlementMonthUrlType);
                timeBillingStatisticsList = Statics.fbgwxSettlementMonthList;
                timeAdapter = new FinancialTimeBillingStatisticsAdapter(FinancialStastisticsActivity.this, timeBillingStatisticsList);
                timeListView.setAdapter(timeAdapter);
                //customerBillingStatisticsList = null;//搜索将下面的数据清空
                //customerAdapter = new FinancialCustomerBillingStatisticsAdapter(FinancialStastisticsActivity.this, customerBillingStatisticsList);
                //customerListView.setAdapter(customerAdapter);
                break;
            case R.id.barChart:
                //Intent in = new Intent(BillingStatisticsActivity.this,TongjiGraphActivity.class);
                Intent in = new Intent(getApplicationContext(), ChartsFragementActivity.class);
                in.putExtra("catlog","财务统计分析");
                startActivity(in);
                break;
        }
    }
    public void init() {
        timeListView = (ListView) findViewById(R.id.lv);
        tableTitle = (ViewGroup) findViewById(R.id.table_title);
        tableTitle.setBackgroundColor(Color.rgb(230, 240, 255));//Color.rgb(177, 173, 172)e6f0ff
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        search = (ImageView) findViewById(R.id.search);
        customerListView = (ListView) findViewById(R.id.customerListView);

        tableTitle = (ViewGroup) findViewById(R.id.table_title);
        tableTitle.setBackgroundColor(Color.rgb(230, 240, 255));
        tableTitle1 = (ViewGroup) findViewById(R.id.table_title1);
//      tableTitle1.setBackgroundColor(Color.rgb(177, 173, 172));

        currentMoney = (TextView) findViewById(R.id.currentMoney);
        //linear = (LinearLayout) findViewById(R.id.linear);
        mCombinedChart = (BarChart) findViewById(R.id.barChart);
        pullScrollView = (PullScrollView) findViewById(R.id.test);
        aCache = ACache.get(FinancialStastisticsActivity.this);
    }
    public static void AdapterRefresh(String type) {//刷新adapter
        switch (type) {
            case "timeAdapter":
                timeAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
                //测量高度;
                ToolUtils.setListViewHeightBasedOnChildren(timeListView,5);
                InoutComeZhuFragment inoutComeZhuFragment =InoutComeZhuFragment.newInstance("财务统计分析");
                InoutComeZhuFragment.catlog = inoutComeZhuFragment.getArguments().getString("catlog");
                inoutComeZhuFragment.setGrayValue();
                inoutComeZhuFragment.initData(activity,mCombinedChart,true);
                break;
            case "customerAdapter":
                customerAdapter.notifyDataSetChanged();
                //测量高度
                ToolUtils.setListViewHeightBasedOnChildren(customerListView,6);
                break;
            case "xiangxiAdapter":
                xiangxiAdapter.notifyDataSetChanged();
                break;
            case "monthXiangXiAdapter":
                Log.d("FinancialStastisticsAct", "刷新");
                monXiangXiAdapter.notifyDataSetChanged();
                break;
            case "currentMoney":
                Log.d("FinancialStastisticsAct", "余额：" + Statics.CurrentMoney);
                currentMoney.setText(Statics.CurrentMoney);
                break;
        }
    }
    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }
}