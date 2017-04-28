package ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;

import com.example.admin.erp.R;

import java.util.ArrayList;
import java.util.List;

import Tool.ToolUtils;
import Tool.statistics.Statics;
import http.BillingStatisticsHttpPost;
import model.CustomerBillingStatistics;
import model.TimeBillingStatistics;
import model.XiangxiBillingStatistics;
import portface.LazyLoadFace;
import ui.adpter.CustomerBillingStatisticsAdapter;
import ui.adpter.TimeBillingStatisticsAdapter;
import ui.adpter.XiangxiBillingStatisticsAdapter;
import ui.fragement.ChartsFragementActivity;

public class BillingStatisticsActivity extends AppCompatActivity implements LazyLoadFace {
    public static ListView timeListView, customerListView;
    private ViewGroup tableTitle, tableTitle1;
    private BillingStatisticsHttpPost billingStatisticsHttpPost;
    private Spinner typeSpinner, yearSpinner;
    private ImageView search;
    private List<String> data_list;
    public static ArrayAdapter<String> arr_adapter;
    private String typeSpinnerString = "024001", yearSpinnerString = null;
    private List<TimeBillingStatistics> timeBillingStatisticsList;
    private List<CustomerBillingStatistics> customerBillingStatisticsList;
    private List<XiangxiBillingStatistics> xiangxiBillingStatisticsList;
    public static TimeBillingStatisticsAdapter timeAdapter;
    public static CustomerBillingStatisticsAdapter customerAdapter;
    public static XiangxiBillingStatisticsAdapter xiangxiAdapter;
    private AlertDialog dlg;
    private ListView listView;
    private int count=0;
    private ImageView zhuXing;
    public static ProgressDialog progressDialog = null;//加载数据显示进度条
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("物流账单统计分析");
        setContentView(R.layout.activity_billing_statistics);

        init();
        spinnerType();

        yearSpinnerString = "2017";//默认赋值
        //首次访问
        progressDialog = ProgressDialog.show(BillingStatisticsActivity.this, "请稍等...", "获取数据中...", true);//显示进度条
        billingStatisticsHttpPost = new BillingStatisticsHttpPost();
        billingStatisticsHttpPost.searchTimeHttp(Statics.TimeSearchUrl, "2017", "", BillingStatisticsActivity.this);
        timeBillingStatisticsList = Statics.timeBillingStatisticsList;
        timeAdapter = new TimeBillingStatisticsAdapter(BillingStatisticsActivity.this, timeBillingStatisticsList);
        timeListView.setAdapter(timeAdapter);
        search.setOnClickListener(o);
        zhuXing.setOnClickListener(o);
        //listView点击事件
        timeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //选中变色
                ToolUtils.selectColor(parent,position);
                //确定月份
                final String month = Statics.timeBillingStatisticsList.get(position).getMonth();
                billingStatisticsHttpPost.searchCustomerHttp(Statics.CustomerSearchUrl, yearSpinnerString, typeSpinnerString, month, BillingStatisticsActivity.this);
                customerBillingStatisticsList = Statics.customerBillingStatisticsArrayList;
                customerAdapter = new CustomerBillingStatisticsAdapter(BillingStatisticsActivity.this, customerBillingStatisticsList);
                customerListView.setAdapter(customerAdapter);

                customerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String customerId = Statics.customerBillingStatisticsArrayList.get(position).getCustomerId();//ID
                        listView = null;
                        //递类型，月份，客户名客户名以检索
                        billingStatisticsHttpPost.searchXqCustomerHttp(Statics.XqCustomerSearchUrl, yearSpinnerString, typeSpinnerString, month, customerId, BillingStatisticsActivity.this);
                        //显示对话框，在对话框中使用ListView
                        AlertDialog.Builder builder = new AlertDialog.Builder(BillingStatisticsActivity.this);
                        LayoutInflater inflater = getLayoutInflater();
                        final View layout = inflater.inflate(R.layout.billingstatistics_dialog_detailed_item, null);//获取自定义布局
                        listView = (ListView) layout.findViewById(R.id.lv);
                        tableTitle = (ViewGroup) layout.findViewById(R.id.table_title);
                        tableTitle.setBackgroundColor(Color.rgb(177, 173, 172));
                        xiangxiBillingStatisticsList = Statics.xiangxiBillingStatisticsArrayList;
                        xiangxiAdapter = new XiangxiBillingStatisticsAdapter(BillingStatisticsActivity.this, xiangxiBillingStatisticsList);
                        listView.setAdapter(xiangxiAdapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            }
                        });

                        //创建人就是用户名
                        builder.setView(layout);
                        dlg = builder.create();
                        dlg.show();
                        //dlg.getWindow().setLayout(1500, 1500);
                        WindowManager windowManager = getWindowManager();
                        Display display = windowManager.getDefaultDisplay();
                        WindowManager.LayoutParams lp = dlg.getWindow().getAttributes();
                        lp.width = (int)(display.getWidth()); //设置宽度
                        dlg.getWindow().setAttributes(lp);
                    }
                }
                );
            }
        });
    }



    View.OnClickListener o = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.search:
                    Log.v("test2", "R.id.search");
                    progressDialog = ProgressDialog.show(BillingStatisticsActivity.this, "请稍等...", "获取数据中...", true);//显示进度条
                    billingStatisticsHttpPost.searchTimeHttp(Statics.TimeSearchUrl, yearSpinnerString, typeSpinnerString, BillingStatisticsActivity.this);
                    timeBillingStatisticsList = Statics.timeBillingStatisticsList;
                    timeAdapter = new TimeBillingStatisticsAdapter(BillingStatisticsActivity.this, timeBillingStatisticsList);
                    timeListView.setAdapter(timeAdapter);
                    customerBillingStatisticsList = null;//搜索将下面的数据清空
                    customerAdapter = new CustomerBillingStatisticsAdapter(BillingStatisticsActivity.this, customerBillingStatisticsList);
                    customerListView.setAdapter(customerAdapter);
                    break;
                case R.id.zhuXing:
                    //Intent in = new Intent(BillingStatisticsActivity.this,TongjiGraphActivity.class);
                    Intent in = new Intent(getApplicationContext(), ChartsFragementActivity.class);
                    startActivity(in);
                    break;
            }
        }
    };

    private void spinnerType() {
        Log.d("test", "spinnerType");
        //数据
        //httpPost =new HttpPost();
        //httpPost.accountTypeSearchHttp(Static.AccountTypeUrl, AccountManagementActivity.this);
        data_list = new ArrayList<>();
        data_list.add("全部");
        for (int i = 0; i < Statics.accountTypeList.size(); i++) {
            data_list.add(Statics.accountTypeList.get(i).getName());
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
                if(position == 0){
                    typeSpinnerString = "全部";
                }else{
                    typeSpinnerString = Statics.accountTypeList.get(--position).getId();
                }
                data_list = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //数据
        //httpPost =new HttpPost();
        //httpPost.accountTypeSearchHttp(Static.AccountTypeUrl, AccountManagementActivity.this);
        ArrayList<String> yearlist = new ArrayList<>();
        yearlist.add("全部");
        for (int i=0;i<Statics.billingYear.size();i++){
            yearlist.add(Statics.billingYear.get(i));
        }
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, yearlist);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        yearSpinner.setAdapter(arr_adapter);
        data_list = null;
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    yearSpinnerString = "全部";
                }else{
                    yearSpinnerString = Statics.billingYear.get(--position);
                }
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
        tableTitle.setBackgroundColor(Color.rgb(177, 173, 172));
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        search = (ImageView) findViewById(R.id.search);
        customerListView = (ListView) findViewById(R.id.customerListView);

        tableTitle = (ViewGroup) findViewById(R.id.table_title);
        tableTitle.setBackgroundColor(Color.rgb(177, 173, 172));
        tableTitle1 = (ViewGroup) findViewById(R.id.table_title1);
        tableTitle1.setBackgroundColor(Color.rgb(177, 173, 172));

        zhuXing = (ImageView) findViewById(R.id.zhuXing);
    }
    @Override
    public void AdapterRefresh(String type) {//刷新adapter
        switch (type) {
            case "timeAdapter":
                timeAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
                break;
            case "customerAdapter":
                customerAdapter.notifyDataSetChanged();
                break;
            case "xiangxiAdapter":
                Log.v("mme","jj");
                xiangxiAdapter.notifyDataSetChanged();
                break;
        }
    }
}
