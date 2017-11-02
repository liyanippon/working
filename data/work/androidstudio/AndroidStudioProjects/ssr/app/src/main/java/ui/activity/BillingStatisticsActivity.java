package ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
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

import com.example.admin.erp.R;
import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;
import java.util.List;

import Tool.ACache;
import Tool.ToolUtils;
import Tool.crash.BaseActivity;
import Tool.customerWidget.PullScrollView;
import Tool.statistics.AchacheConstant;
import Tool.statistics.Statics;
import http.BillingStatisticsHttpPost;
import http.HttpBasePost;
import http.HttpTypeConstants;
import model.javabean.AccountType;
import model.javabean.CustomerBillingStatistics;
import model.javabean.TimeBillingStatistics;
import model.javabean.XiangxiBillingStatistics;
import ui.adpter.CustomerBillingStatisticsAdapter;
import ui.adpter.TimeBillingStatisticsAdapter;
import ui.adpter.XiangxiBillingStatisticsAdapter;
import ui.fragement.ChartsFragementActivity;
import ui.fragement.InoutComeZhuFragment;

public class BillingStatisticsActivity extends BaseActivity implements android.os.Handler.Callback {
    public static ListView timeListView, customerListView;
    private ViewGroup tableTitle, tableTitle1;
    private BillingStatisticsHttpPost billingStatisticsHttpPost;
    private Spinner typeSpinner, yearSpinner;
    private ImageView search;
    private List<String> data_list;
    public static ArrayAdapter<String> arr_adapter;
    private String typeSpinnerString = "024001", yearSpinnerString = null;
    private static List<TimeBillingStatistics> timeBillingStatisticsList;
    private static List<CustomerBillingStatistics> customerBillingStatisticsList;
    private List<XiangxiBillingStatistics> xiangxiBillingStatisticsList;
    public static TimeBillingStatisticsAdapter timeAdapter;
    public static CustomerBillingStatisticsAdapter customerAdapter;
    public static XiangxiBillingStatisticsAdapter xiangxiAdapter;
    private AlertDialog dlg;
    private ListView listView;
    private int count = 0;
    public static ProgressDialog progressDialog = null;//加载数据显示进度条
    public static TextView currentMoneyStatistics;
    private String month;
    //统计图
    private static BarChart mCombinedChart;
    private static Activity activity;
    private PullScrollView pullScrollView;
    private Handler handler;
    private long exitTime = 0, customerTime = 0;
    ACache aCache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("物流账单统计分析");
        setContentView(R.layout.activity_billing_statistics);

        //添加返回按钮
        ToolUtils.backButton(this);
        init();
        activity = BillingStatisticsActivity.this;
        spinnerType();
        yearSpinnerString = "2017";//默认赋值
        //首次访问
        progressDialog = ProgressDialog.show(BillingStatisticsActivity.this, "请稍等...", "获取数据中...", true);//显示进度条

        HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.EXPRESS_GETWXPAYMENT_METHOD), null, HttpTypeConstants.ExpressGetWXPaymentMethod);//获取当前资金情况
        billingStatisticsHttpPost = new BillingStatisticsHttpPost();
        billingStatisticsHttpPost.searchTimeHttp(aCache.getAsString(AchacheConstant.TIME_SEARCH_URL), "2017", "", "", "", BillingStatisticsActivity.this, "BillingStatisticsActivity");
        timeBillingStatisticsList = Statics.timeBillingStatisticsList;
        timeAdapter = new TimeBillingStatisticsAdapter(BillingStatisticsActivity.this, timeBillingStatisticsList);
        timeListView.setAdapter(timeAdapter);
        search.setOnClickListener(o);
        mCombinedChart.setOnClickListener(o);
        //listView点击事件
        timeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //选中变色
                //ToolUtils.selectColor(parent,position);
                //确定月份
                customerListView.setVisibility(View.VISIBLE);
                month = Statics.timeBillingStatisticsList.get(position).getMonth();
                Log.d("search", typeSpinnerString);
                billingStatisticsHttpPost.searchCustomerHttp(aCache.getAsString(AchacheConstant.CUSTOMER_SEARCH_URL), yearSpinnerString, typeSpinnerString, month, BillingStatisticsActivity.this);
                customerBillingStatisticsList = Statics.customerBillingStatisticsArrayList;
                customerAdapter = new CustomerBillingStatisticsAdapter(BillingStatisticsActivity.this, customerBillingStatisticsList);
                customerListView.setAdapter(customerAdapter);
                customerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                            @Override
                                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                                customerTime = ToolUtils.muchClick(customerTime);
                                                                if (customerTime != 0) {
                                                                    customerTime = System.currentTimeMillis();
                                                                    String customerId = Statics.customerBillingStatisticsArrayList.get(position).getCustomerId();//ID
                                                                    month = Statics.customerBillingStatisticsArrayList.get(position).getMonth();
                                                                    listView = null;
                                                                    //递类型，月份，客户名客户名以检索
                                                                    billingStatisticsHttpPost.searchXqCustomerHttp(aCache.getAsString(AchacheConstant.XQCUSTOMER_SEARCH_URL), yearSpinnerString, typeSpinnerString, month, customerId, "", "", BillingStatisticsActivity.this);
                                                                    //显示对话框，在对话框中使用ListView
                                                                    AlertDialog.Builder builder = new AlertDialog.Builder(BillingStatisticsActivity.this);
                                                                    LayoutInflater inflater = getLayoutInflater();
                                                                    View layout = inflater.inflate(R.layout.billingstatistics_dialog_detailed_item, null);//获取自定义布局
                                                                    listView = (ListView) layout.findViewById(R.id.lv);
                                                                    xiangxiBillingStatisticsList = Statics.xiangxiBillingStatisticsArrayList;
                                                                    xiangxiAdapter = new XiangxiBillingStatisticsAdapter(BillingStatisticsActivity.this, xiangxiBillingStatisticsList);
                                                                    listView.setAdapter(xiangxiAdapter);
                                                                    //创建人就是用户名
                                                                    builder.setView(layout);
                                                                    dlg = builder.create();
                                                                    dlg.show();
                                                                    //dlg.getWindow().setLayout(1500, 1500);
                                                                    WindowManager windowManager = getWindowManager();
                                                                    Display display = windowManager.getDefaultDisplay();
                                                                    WindowManager.LayoutParams lp = dlg.getWindow().getAttributes();
                                                                    lp.width = display.getWidth(); //设置宽度
                                                                    //lp.height = display.getHeight();
                                                                    dlg.getWindow().setAttributes(lp);
                                                                }
                                                            }
                                                        }
                );
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
                        billingStatisticsHttpPost = new BillingStatisticsHttpPost();
                        billingStatisticsHttpPost.searchTimeHttp(aCache.getAsString(AchacheConstant.TIME_SEARCH_URL), "2017", "", "", "", activity, "BillingStatisticsActivity");
                        //BillingStatisticsActivity.timeAdapter.notifyDataSetChanged();
                        HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.EXPRESS_GETWXPAYMENT_METHOD), null, HttpTypeConstants.ExpressGetWXPaymentMethod);//获取当前资金情况
                        if (customerBillingStatisticsList != null) {
                            customerBillingStatisticsList.clear();//搜索将下面的数据清空
                        }
                        customerAdapter = new CustomerBillingStatisticsAdapter(BillingStatisticsActivity.this, customerBillingStatisticsList);
                        customerListView.setAdapter(customerAdapter);
                        //测量高度
                        ToolUtils.setListViewHeightBasedOnChildren(timeListView, 1);
                        //测量高度
                        ToolUtils.setListViewHeightBasedOnChildren(customerListView, 2);
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
    View.OnClickListener o = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.search:
                    exitTime = ToolUtils.muchClick(exitTime);
                    if (exitTime != 0) {
                        exitTime = System.currentTimeMillis();
                        Log.v("test2", "R.id.search");
                        progressDialog = ProgressDialog.show(BillingStatisticsActivity.this, "请稍等...", "获取数据中...", true);//显示进度条
                        billingStatisticsHttpPost.searchTimeHttp(aCache.getAsString(AchacheConstant.TIME_SEARCH_URL), yearSpinnerString
                                , typeSpinnerString, "", "", activity, "BillingStatisticsActivity");
                        timeBillingStatisticsList = Statics.timeBillingStatisticsList;
                        timeAdapter = new TimeBillingStatisticsAdapter(BillingStatisticsActivity.this, timeBillingStatisticsList);
                        timeListView.setAdapter(timeAdapter);
                        customerBillingStatisticsList = null;//搜索将下面的数据清空
                        customerAdapter = new CustomerBillingStatisticsAdapter(BillingStatisticsActivity.this, customerBillingStatisticsList);
                        customerListView.setAdapter(customerAdapter);
                    }
                    break;
                case R.id.barChart:
                    exitTime = ToolUtils.muchClick(exitTime);
                    if (exitTime != 0) {
                        exitTime = System.currentTimeMillis();
                        //Intent in = new Intent(BillingStatisticsActivity.this,TongjiGraphActivity.class);
                        Intent in = new Intent(getApplicationContext(), ChartsFragementActivity.class);
                        in.putExtra("catlog", "物流统计分析");
                        startActivity(in);
                    }
                    break;
            }
        }
    };
    private void spinnerType() {
        Log.d("test", "spinnerType");
        //数据
        data_list = new ArrayList<>();
        data_list.add("全部");
        ArrayList<AccountType> typeArrayList = (ArrayList<AccountType>) aCache.getAsObject(AchacheConstant.ACCOUNT_TYPE_LIST);
        for (int i = 0; i < typeArrayList.size(); i++) {
            data_list.add(typeArrayList.get(i).getName());
        }
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        typeSpinner.setAdapter(arr_adapter);
        data_list = null;

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<AccountType> typeArrayList = (ArrayList<AccountType>) aCache.getAsObject(AchacheConstant.ACCOUNT_TYPE_LIST);
                if (position == 0) {
                    typeSpinnerString = "全部";
                } else {
                    typeArrayList.get(--position).getId();
                }
                data_list = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //数据
        ArrayList<String> yearlist = new ArrayList<>();
        for (int i = 0; i < Statics.billingYear.size(); i++) {
            yearlist.add(Statics.billingYear.get(i));
        }
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, yearlist);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        yearSpinner.setAdapter(arr_adapter);
        data_list = null;
        yearSpinner.setSelection(1, true);
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yearSpinnerString = Statics.billingYear.get(position);
                data_list = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void init() {
        timeListView = (ListView) findViewById(R.id.lv);
        tableTitle = (ViewGroup) findViewById(R.id.table_title);
        tableTitle.setBackgroundColor(Color.rgb(230, 240, 255));
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        search = (ImageView) findViewById(R.id.search);
        customerListView = (ListView) findViewById(R.id.customerListView);
        tableTitle = (ViewGroup) findViewById(R.id.table_title);
        tableTitle.setBackgroundColor(Color.rgb(230, 240, 255));
        tableTitle1 = (ViewGroup) findViewById(R.id.table_title1);
        tableTitle1.setBackgroundColor(Color.rgb(230, 240, 255));
        currentMoneyStatistics = (TextView) findViewById(R.id.currentMoneyStatistics);
        mCombinedChart = (BarChart) findViewById(R.id.barChart);
        pullScrollView = (PullScrollView) findViewById(R.id.test);
        aCache = ACache.get(BillingStatisticsActivity.this);
    }

    public static void AdapterRefresh(String type) {//刷新adapter
        switch (type) {
            case "timeAdapter":
                timeAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
                //测量高度
                ToolUtils.setListViewHeightBasedOnChildren(timeListView, 1);
                //统计图
                InoutComeZhuFragment inoutComeZhuFragment = InoutComeZhuFragment.newInstance("物流统计分析");
                inoutComeZhuFragment.catlog = inoutComeZhuFragment.getArguments().getString("catlog");
                inoutComeZhuFragment.setGrayValue();
                inoutComeZhuFragment.initData(activity, mCombinedChart, true);
                break;
            case "customerAdapter":
                customerAdapter.notifyDataSetChanged();
                //测量高度
                ToolUtils.setListViewHeightBasedOnChildren(customerListView, 2);
                break;
            case "xiangxiAdapter":
                xiangxiAdapter.notifyDataSetChanged();
                break;
            case "CurrentPayStatistic":
                String currentString = Statics.CurrentPayStatistic;
                currentString = currentString.replace("null", "0.00");
                Log.d("BillingStatisticsActivi", "当前金额：" + currentString);
                try {
                    currentString = "—" + currentString.split(",")[0].trim() + "—" + "\n"
                            + currentString.split(",")[1].trim() + "，" + currentString.split(",")[2].trim() + "\n"
                            + currentString.split(",")[3].trim() + "，" + currentString.split(",")[4].trim();
                } catch (ArrayIndexOutOfBoundsException e) {
                    currentString = Statics.CurrentPayStatistic;
                }
                currentMoneyStatistics.setText(currentString);
                break;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }
}