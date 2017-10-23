package ui.activity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
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
import com.example.admin.erp.R;
import com.github.mikephil.charting.charts.BarChart;
import java.util.ArrayList;
import java.util.List;
import Tool.ToolUtils;
import Tool.crash.BaseActivity;
import Tool.statistics.Statics;
import broadcast.Config;
import broadcast.FreshenBroadcastReceiver;
import http.BillingStatisticsHttpPost;
import http.ExpressBillingManagementHttpPost;
import model.CustomerBillingStatistics;
import model.TimeBillingStatistics;
import model.XiangxiBillingStatistics;
import portface.LazyLoadFace;
import ui.adpter.TimeBillingStatisticsAdapter;
import ui.adpter.XiangxiBillingStatisticsAdapter;
import ui.fragement.ChartsFragementActivity;
import ui.fragement.InoutComeZhuFragment;

public class LogisticsReportActivity extends BaseActivity {
    public static ListView timeListView, customerListView;
    private ViewGroup tableTitle, tableTitle1;
    private BillingStatisticsHttpPost billingStatisticsHttpPost;
    private ExpressBillingManagementHttpPost httpPost;
    private Spinner typeSpinner,yearSpinner,classifySpinner;
    private static Spinner reasonSpinner;
    private ImageView search;
    private List<String> data_list;
    public static ArrayAdapter<String> arr_adapter;
    private String yearSpinnerString = null, reasonSpinnerString,typeSpinnerString;
    private static String classifySpinnerString;
    private static List<TimeBillingStatistics> timeBillingStatisticsList;
    private static List<CustomerBillingStatistics> customerBillingStatisticsList;
    private List<XiangxiBillingStatistics> xiangxiBillingStatisticsList;
    //public static TimeBillingStatisticsAdapter timeAdapter;
    //public static CustomerBillingStatisticsAdapter customerAdapter;
    //public static XiangxiBillingStatisticsAdapter xiangxiAdapter;
    private AlertDialog dlg;
    private ListView listView;
    private int count = 0;
    public static ProgressDialog progressDialog = null;//加载数据显示进度条
    //public static TextView currentMoneyStatistics;
    private String month;
    //统计图
    private static BarChart mCombinedChart;
    private static Activity activity;
    static FreshenBroadcastReceiver broadcast;
    private static ArrayList data_list1;
    public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("统计报表");
        setContentView(R.layout.activity_logistics_report);

        //添加返回按钮
        ToolUtils.backButton(this);
        context = getApplicationContext();
        initBroadCast();
        init();
        activity = LogisticsReportActivity.this;
        spinnerType();
        Log.d("BillingStatisticsActivi", "test");
        yearSpinnerString = "2017";//默认赋值
        //首次访问
        progressDialog = ProgressDialog.show(activity, "请稍等...", "获取数据中...", true);//显示进度条
        Statics.ActivityType = "LogisticsReportActivity";
        billingStatisticsHttpPost = new BillingStatisticsHttpPost();
        billingStatisticsHttpPost.searchTimeHttp(Statics.TimeSearchUrl, "2017", "" ,  ""  , "", activity,"LogisticsReportActivity");
        timeBillingStatisticsList = Statics.timeBillingStatisticsList;
        BillingStatisticsActivity.timeAdapter = new TimeBillingStatisticsAdapter(activity, timeBillingStatisticsList);
        timeListView.setAdapter(BillingStatisticsActivity.timeAdapter);

        search.setOnClickListener(o);
        mCombinedChart.setOnClickListener(o);
        timeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String customerId = Statics.customerBillingStatisticsArrayList.get(position).getCustomerId();//ID
                Statics.ActivityType = "LogisticsReportActivity";
                month = Statics.timeBillingStatisticsList.get(position).getMonth();
                listView = null;
                //递类型，月份，客户名以检索 (检索条件新增业务分类和业务类型)
                billingStatisticsHttpPost.searchXqCustomerHttp(Statics.XqCustomerSearchUrl, yearSpinnerString, typeSpinnerString, month, "", classifySpinnerString, reasonSpinnerString, activity);
                //显示对话框，在对话框中使用ListView
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                LayoutInflater inflater = getLayoutInflater();
                final View layout = inflater.inflate(R.layout.billingstatistics_dialog_detailed_item, null);//获取自定义布局
                listView = (ListView) layout.findViewById(R.id.lv);
                //tableTitle = (ViewGroup) layout.findViewById(R.id.table_title);
                //tableTitle.setBackgroundColor(Color.rgb(230, 240, 255));
                xiangxiBillingStatisticsList = Statics.xiangxiBillingStatisticsArrayList;
                BillingStatisticsActivity.xiangxiAdapter = new XiangxiBillingStatisticsAdapter(activity, xiangxiBillingStatisticsList);
                listView.setAdapter(BillingStatisticsActivity.xiangxiAdapter);
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
        });

    }

    View.OnClickListener o = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.search:
                    Statics.ActivityType = "LogisticsReportActivity";
                    progressDialog = ProgressDialog.show(activity, "请稍等...", "获取数据中...", true);//显示进度条
                    billingStatisticsHttpPost.searchTimeHttp(Statics.TimeSearchUrl, yearSpinnerString, typeSpinnerString
                            , classifySpinnerString ,reasonSpinnerString, activity,"LogisticsReportActivity");
                    timeBillingStatisticsList = Statics.timeBillingStatisticsList;
                    BillingStatisticsActivity.timeAdapter = new TimeBillingStatisticsAdapter(activity, timeBillingStatisticsList);
                    timeListView.setAdapter(BillingStatisticsActivity.timeAdapter);
                    /*customerBillingStatisticsList = null;//搜索将下面的数据清空
                    BillingStatisticsActivity.customerAdapter = new CustomerBillingStatisticsAdapter(activity, customerBillingStatisticsList);
                    customerListView.setAdapter(BillingStatisticsActivity.customerAdapter);*/
                    break;
                case R.id.barChart:
                    //Intent in = new Intent(BillingStatisticsActivity.this,TongjiGraphActivity.class);
                    Intent in = new Intent(getApplicationContext(), ChartsFragementActivity.class);
                    in.putExtra("catlog","物流统计分析");
                    startActivity(in);
                    break;
            }
        }
    };

    private static void initBroadCast() {//二级联动
        //广播初始化 必须动态注册才能实现回调
        Log.d("LogisticsReportActivity", "广播初始化");
        Log.d("express","广播初始化");
        broadcast = new FreshenBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Config.BC_ONE);
        context.registerReceiver(broadcast, intentFilter);

        broadcast.setLazyLoadFace(new LazyLoadFace() {
            @Override
            public void AdapterRefresh(String type) {
                //具体更新
                if(type.equals("SearchReasonSpinner1")){
                    Log.d("aleand","收到广播");
                    //适配器
                    //arr_adapter1.notifyDataSetChanged();
                    data_list1 = new ArrayList<>();
                    data_list1.add("全部");
                    for (int i = 0; i < Statics.accountReasonList.size() && !"全部".equals(classifySpinnerString); i++) {
                        data_list1.add(Statics.accountReasonList.get(i).getName());
                        Log.v("test2", "data_list:" + Statics.accountReasonList.get(i).getName());

                    }
                    //适配器
                    arr_adapter = new ArrayAdapter<>(activity, R.layout.spinner_display_style, R.id.txtvwSpinner, data_list1);
                    //设置样式
                    arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
                    //加载适配器
                    reasonSpinner.setAdapter(arr_adapter);
                }
            }
        });
    }

    private void spinnerType() {
        //数据（快递类型：圆通，韵达）
        //httpPost =new HttpPost();
        //httpPost.accountTypeSearchHttp(Static.AccountTypeUrl, AccountManagementActivity.this);
        data_list = new ArrayList<>();
        data_list.add("全部");
        for (int i = 0; i < Statics.accountTypeList.size(); i++) {
            data_list.add(Statics.accountTypeList.get(i).getName().replace("快递",""));
        }
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        typeSpinner.setAdapter(arr_adapter);
        data_list = null;

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    typeSpinnerString = "全部";
                } else {
                    typeSpinnerString = Statics.accountTypeList.get(--position).getId();
                }
                data_list = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //数据 (年份)
        //httpPost =new HttpPost();
        //httpPost.accountTypeSearchHttp(Static.AccountTypeUrl, AccountManagementActivity.this);
        ArrayList<String> yearlist = new ArrayList<>();
        //yearlist.add("全部");
        for (int i = 0; i < Statics.billingYear.size(); i++) {
            yearlist.add(Statics.billingYear.get(i).toString());
        }
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_display_style, R.id.txtvwSpinner, yearlist);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        yearSpinner.setAdapter(arr_adapter);
        yearlist = null;
        yearSpinner.setSelection(1, true);
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*if (position == 0) {
                    yearSpinnerString = "全部";
                } else {
                    yearSpinnerString = Statics.billingYear.get(position);
                }*/
                yearSpinnerString = Statics.billingYear.get(position);
                data_list = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //数据（业务分类：进账，出账 和 业务类型 二级联动）
        //httpPost =new HttpPost();
        //httpPost.accountClassifySearchHttp(Static.AccountClassifyUrl, AccountManagementActivity.this);
        data_list = new ArrayList<>();
        data_list.add("全部");
        for (int i = 0; Statics.expressClassifyList.get(0).getData().size() > 0 && i < Statics.expressClassifyList.get(0).getData().size(); i++) {
            data_list.add(Statics.expressClassifyList.get(0).getData().get(i).getName());
            Log.d("ytpe",Statics.expressClassifyList.get(0).getData().get(i).getName());
        }
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        classifySpinner.setAdapter(arr_adapter);
        classifySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Statics.ActivityType="LogisticsReportActivity";
                //二级联动
                if(position == 0){
                    classifySpinnerString = "全部";
                }else{
                    classifySpinnerString = Statics.expressClassifyList.get(0).getData().get(--position).getId();
                }
                Log.v("test2", "classifySpinnerString:" + classifySpinnerString);
                //数据
                httpPost = new ExpressBillingManagementHttpPost();
                Log.v("test2", "PrereasonSpinner:" + Boolean.toString(reasonSpinner == null));
                httpPost.accountReasonSearchHttp(Statics.AccountReasonUrl, classifySpinnerString, activity);

                data_list1 = new ArrayList<>();
                data_list1.add("全部");
                for (int i = 0; i < Statics.accountReasonList.size(); i++) {
                    data_list1.add(Statics.accountReasonList.get(i).getName());
                    Log.v("test2", "data_list:" + Statics.accountReasonList.get(i).getName());
                    Log.v("test2", "data_list:" + Statics.accountReasonList.get(i).getId());

                }
                //适配器
                arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_display_style, R.id.txtvwSpinner, data_list1);
                //设置样式
                arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
                //加载适配器
                reasonSpinner.setAdapter(arr_adapter);

                Log.v("test2", "After:" + Boolean.toString(reasonSpinner == null));
                reasonSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position <= Statics.accountReasonList.size()) {//需要仔细
                            if(position == 0){
                                reasonSpinnerString = "全部";
                                Log.d("ExpressBillingManagemen", "代号："+"全部");

                            }else{
                                reasonSpinnerString = Statics.accountReasonList.get(--position).getId();
                                Log.d("ExpressBillingManagemen", "代号："+reasonSpinnerString);
                            }
                        } else {
                            Log.v("test", "position:" + Integer.toString(position) + "@" +
                                    "Static.accountReasonList.size()" + Integer.toString(Statics.accountReasonList.size()));
                            Log.d("ExpressBillingManagemen","代号::"+position+"@@"+Integer.toString(Statics.accountReasonList.size()-1));
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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

    public void init() {
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        classifySpinner = (Spinner) findViewById(R.id.classifySpinner);
        reasonSpinner = (Spinner) findViewById(R.id.reasonSpinner);
        timeListView = (ListView) findViewById(R.id.lv);
        tableTitle = (ViewGroup) findViewById(R.id.table_title);
        tableTitle.setBackgroundColor(Color.rgb(230, 240, 255));
        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        search = (ImageView) findViewById(R.id.search);
        customerListView = (ListView) findViewById(R.id.customerListView);
        tableTitle = (ViewGroup) findViewById(R.id.table_title);
        tableTitle.setBackgroundColor(Color.rgb(230, 240, 255));
        //tableTitle1 = (ViewGroup) findViewById(R.id.table_title1);
        //tableTitle1.setBackgroundColor(Color.rgb(230, 240, 255));
        //currentMoneyStatistics = (TextView) findViewById(R.id.currentMoneyStatistics);
        mCombinedChart = (BarChart) findViewById(R.id.barChart);
    }

    public static void AdapterRefresh(String type) {//刷新adapter
        switch (type) {
            case "timeAdapter":
                BillingStatisticsActivity.timeAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
                //测量高度
                ToolUtils.setListViewHeightBasedOnChildren(timeListView,1);
                //统计图
                InoutComeZhuFragment inoutComeZhuFragment = new InoutComeZhuFragment("物流统计分析");
                inoutComeZhuFragment.setGrayValue();
                inoutComeZhuFragment.initData(activity ,mCombinedChart,true);
                break;
            case "customerAdapter":
                BillingStatisticsActivity.customerAdapter.notifyDataSetChanged();
                //测量高度
                Log.d("测量高度","customerListView,2");
                ToolUtils.setListViewHeightBasedOnChildren(customerListView,2);
                break;
            case "xiangxiAdapter":
                BillingStatisticsActivity.xiangxiAdapter.notifyDataSetChanged();
                break;
            case  "CurrentPayStatistic":
                String currentString = Statics.CurrentPayStatistic;
                currentString = currentString.replace("null", "0.00");
                Log.d("BillingStatisticsActivi", "当前金额：" + currentString);
                try {
                    currentString = "—"+currentString.split(",")[0].trim() + "—" + "\n"
                            + currentString.split(",")[1].trim() + "，" + currentString.split(",")[2].trim() + "\n"
                            + currentString.split(",")[3].trim() + "，"+currentString.split(",")[4].trim();
                }catch (ArrayIndexOutOfBoundsException e){
                    currentString = Statics.CurrentPayStatistic;
                }
                //currentMoneyStatistics.setText(currentString);
                break;
            case "reasonSpinner":
                /*if (arr_adapter != null) {
                    arr_adapter.notifyDataSetChanged();
                }*/
                initBroadCast();
        }
    }

}
