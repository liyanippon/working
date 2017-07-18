package ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.erp.R;

import java.util.ArrayList;
import java.util.List;

import Tool.ToolUtils;
import Tool.crash.BaseActivity;
import Tool.crash.CrashHandler;
import Tool.statistics.Statics;
import broadcast.Config;
import broadcast.FreshenBroadcastReceiver;
import http.ExpressBillingManagementHttpPost;
import portface.LazyLoadFace;
import ui.adpter.ExpressManagementAdapter;
import ui.xlistview.XListView;

public class ExpressBillingManagementActivity extends BaseActivity implements XListView.IXListViewListener {
    public static View search, add, newBilling, transferAccounts;
    private Spinner typeSpinner, classifySpinner;//,reasonSpinner;
    public static Spinner reasonSpinner;
    private static List<String> data_list;
    public static ArrayAdapter<String> arr_adapter;
    private String typeSpinnerString, classifySpinnerString, reasonSpinnerString;
    public static ExpressManagementAdapter expressManagementAdapter;
    public static XListView mListView, accountLv;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> items = new ArrayList<>();
    private Handler mHandler;
    private int start = 0;
    private static int refreshCnt = 0;
    public static int page = 1;
    private ExpressBillingManagementHttpPost httpPost;
    private AlertDialog dlg,dlgMenu;
    private boolean SearchBoolean = false;
    public static Context context;
    public static ProgressDialog progressDialog = null;//加载数据显示进度条
    static FreshenBroadcastReceiver broadcast;
    private GridView gridView;
    public static boolean deleteSuccess = false;
    public static Activity activityExpress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("物流账单管理");
        setContentView(R.layout.activity_expressbilling_management);

        //添加返回按钮
        ToolUtils.backButton(this);
        context = getApplicationContext();
        activityExpress = ExpressBillingManagementActivity.this;
        initBroadCast();
        init();
        //空查询
        page = 1;//显示页数

        httpPost = new ExpressBillingManagementHttpPost();
        String httpUrl = Statics.FinancialBillingManagementSearchUrl;
        //刚进入页面就要显示数据
        progressDialog = ProgressDialog.show(ExpressBillingManagementActivity.this, "请稍等...", "获取数据中...", true);//显示进度条
        String result = httpPost.searchHttp(httpUrl, "", "", "", ExpressBillingManagementActivity.this, page);
        accountLv.setPullLoadEnable(true);
        expressManagementAdapter = new ExpressManagementAdapter(ExpressBillingManagementActivity.this);
        accountLv.setAdapter(expressManagementAdapter);
        accountLv.setXListViewListener(this);
        accountLv.setDivider(new ColorDrawable(Color.BLUE));
        accountLv.setDividerHeight(1);
        mHandler = new Handler();
        accountLv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                newBilling.setVisibility(View.INVISIBLE);
                transferAccounts.setVisibility(View.INVISIBLE);
                return false;
            }
        });
        accountLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                newBilling.setVisibility(View.INVISIBLE);
                transferAccounts.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        accountLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {//点击时查看详细信息
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ExpressBillingManagementActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                final View layout = inflater.inflate(R.layout.accountmanager_dialog_detailed_item, null);//获取自定义布局
                TextView type = (TextView) layout.findViewById(R.id.type);//快递类型
                TextView classify = (TextView) layout.findViewById(R.id.classify);//业务分类
                TextView reason = (TextView) layout.findViewById(R.id.reason);//业务类型
                TextView payMethod = (TextView) layout.findViewById(R.id.pay_method);//支付方式
                TextView price = (TextView) layout.findViewById(R.id.price);//金额
                TextView guest = (TextView) layout.findViewById(R.id.guest);//客户
                TextView remark = (TextView) layout.findViewById(R.id.remark);//备注
                TextView createuser = (TextView) layout.findViewById(R.id.createuser);//创建人
                TextView billingTime = (TextView) layout.findViewById(R.id.billingTime);//账单时间
                TextView createTime = (TextView) layout.findViewById(R.id.createTime);//创建时间
                type.setText(Statics.expressManagementList.get(position - 1).getType());
                classify.setText(Statics.expressManagementList.get(position - 1).getClassify());
                reason.setText(Statics.expressManagementList.get(position - 1).getReason());
                payMethod.setText(Statics.expressManagementList.get(position - 1).getPaymentMethod());
                price.setText(Statics.expressManagementList.get(position - 1).getSum());
                guest.setText(Statics.expressManagementList.get(position - 1).getCustomerId());
                remark.setText(Statics.expressManagementList.get(position - 1).getRemark());
                createuser.setText(Statics.expressManagementList.get(position - 1).getCreateBy());
                billingTime.setText(Statics.expressManagementList.get(position - 1).getBillingTime());
                createTime.setText(Statics.expressManagementList.get(position - 1).getCreateTime());
                if("出账".equals(Statics.expressManagementList.get(position - 1).getClassify())){
                    type.setTextColor(Color.RED);
                    classify.setTextColor(Color.RED);
                    reason.setTextColor(Color.RED);
                    payMethod.setTextColor(Color.RED);
                    price.setTextColor(Color.RED);
                    guest.setTextColor(Color.RED);
                    remark.setTextColor(Color.RED);
                    createuser.setTextColor(Color.RED);
                    billingTime.setTextColor(Color.RED);
                    createTime.setTextColor(Color.RED);
                }else {

                }
                newBilling.setVisibility(View.INVISIBLE);
                transferAccounts.setVisibility(View.INVISIBLE);
                //创建人就是用户名
                builder.setView(layout);
                dlg = builder.create();
                dlg.show();
                //dlg.getWindow().setLayout(1500, 1500);
                WindowManager windowManager = getWindowManager();
                Display display = windowManager.getDefaultDisplay();
                WindowManager.LayoutParams lp = dlg.getWindow().getAttributes();
                lp.width = (int) (display.getWidth()); //设置宽度
                dlg.getWindow().setAttributes(lp);
            }
        });

        spinnerType();

        search.setOnClickListener(new View.OnClickListener() {//查询
            @Override
            public void onClick(View v) {
                //条件查询
                page = 1;
                progressDialog = ProgressDialog.show(ExpressBillingManagementActivity.this, "请稍等...", "获取数据中...", true);//显示进度条
                SearchBoolean = true;
                httpPost = new ExpressBillingManagementHttpPost();
                String httpUrl = Statics.FinancialBillingManagementSearchUrl;
                Log.d("ExpressBillingManagemen", "业务类型："+reasonSpinnerString);
                String result = httpPost.searchHttp(httpUrl, typeSpinnerString, classifySpinnerString, reasonSpinnerString, ExpressBillingManagementActivity.this, page);

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent in = new Intent(ExpressBillingManagementActivity.this, AddExpressBillingManagerActivity.class);
                startActivity(in);*/
                //弹出alertDialog
                /*AlertDialog.Builder builder = new AlertDialog.Builder(ExpressBillingManagementActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                final View layout = inflater.inflate(R.layout.express_menu_dialog, null);//获取自定义布局
                LinearLayout newBilling,transferAccounts;
                newBilling = (LinearLayout) layout.findViewById(R.id.newBilling);
                transferAccounts = (LinearLayout) layout.findViewById(R.id.transferAccounts);
                newBilling.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ExpressBillingManagementActivity.this, "新建账单", Toast.LENGTH_SHORT).show();
                    }
                });
                transferAccounts.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ExpressBillingManagementActivity.this, "转账", Toast.LENGTH_SHORT).show();
                    }
                });
                //创建人就是用户名
                builder.setView(layout);
                dlgMenu = builder.create();
                dlgMenu.show();
                //dlg.getWindow().setLayout(1500, 1500);
                WindowManager windowManager = getWindowManager();
                Display display = windowManager.getDefaultDisplay();
                WindowManager.LayoutParams lpMenu = dlgMenu.getWindow().getAttributes();
                lpMenu.width = 500; //设置宽度
                lpMenu.height = 500;//设置高度
                dlgMenu.getWindow().setAttributes(lpMenu);*/
                newBilling.setVisibility(View.VISIBLE);
                transferAccounts.setVisibility(View.VISIBLE);
                ScaleAnimation sa = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, 100.0f, 120.0f);
                sa.setDuration(1000);
                newBilling.startAnimation(sa);
                transferAccounts.startAnimation(sa);
                newBilling.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(ExpressBillingManagementActivity.this, AddExpressBillingManagerActivity.class);
                        startActivity(in);
                    }
                });
                transferAccounts.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent ins = new Intent(ExpressBillingManagementActivity.this,TransferAccountActivity.class);
                        startActivity(ins);
                    }
                });
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
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        typeSpinner.setAdapter(arr_adapter);
        data_list = null;
        //数据
        //httpPost =new HttpPost();
        //httpPost.accountClassifySearchHttp(Static.AccountClassifyUrl, AccountManagementActivity.this);
        data_list = new ArrayList<>();
        data_list.add("全部");
        for (int i = 0; Statics.expressClassifyList.get(0).getData().size() > 0 && i < Statics.expressClassifyList.get(0).getData().size(); i++) {
            data_list.add(Statics.expressClassifyList.get(0).getData().get(i).getName());
        }
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        classifySpinner.setAdapter(arr_adapter);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("test8",Integer.toString(position)+"ss");
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
        classifySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                //二级联动
                if(position == 0){
                    classifySpinnerString = "全部";
                }else{
                    classifySpinnerString = Statics.expressClassifyList.get(0).getData().get(--position).getId();
                }
                Log.v("test2", "classifySpinnerString:" + classifySpinnerString);
                //数据
                httpPost = new ExpressBillingManagementHttpPost();
                Log.v("test2", "PrereasonSpinner:" + Boolean.toString(ExpressBillingManagementActivity.reasonSpinner == null));
                httpPost.accountReasonSearchHttp(Statics.AccountReasonUrl, classifySpinnerString, ExpressBillingManagementActivity.this);

                data_list = new ArrayList<>();
                data_list.add("全部");
                for (int i = 0; i < Statics.accountReasonList.size(); i++) {
                    data_list.add(Statics.accountReasonList.get(i).getName());
                    Log.v("test2", "data_list:" + Statics.accountReasonList.get(i).getName());
                    Log.v("test2", "data_list:" + Statics.accountReasonList.get(i).getId());

                }
                //适配器
                arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_display_style, R.id.txtvwSpinner, data_list);
                //设置样式
                arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
                //加载适配器
                reasonSpinner.setAdapter(arr_adapter);

                Log.v("test2", "After:" + Boolean.toString(ExpressBillingManagementActivity.reasonSpinner == null));
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
                            Log.d("ExpressBillingManagemen", "代号："+"都不是");
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

    private void init() {

        search = findViewById(R.id.search);
        add = findViewById(R.id.add);
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        classifySpinner = (Spinner) findViewById(R.id.classifySpinner);
        reasonSpinner = (Spinner) findViewById(R.id.reasonSpinner);
        accountLv = (XListView) findViewById(R.id.xListView);
        newBilling = findViewById(R.id.newBilling);
        transferAccounts = findViewById(R.id.transferAccounts);
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                httpPost = new ExpressBillingManagementHttpPost();
                String httpUrl = Statics.FinancialBillingManagementSearchUrl;
                String result = httpPost.searchHttp(httpUrl ,typeSpinnerString ,classifySpinnerString ,reasonSpinnerString,ExpressBillingManagementActivity.this,page);
                onLoad();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                if (page >= Statics.page) {
                    page = Statics.page;
                    Toast.makeText(ExpressBillingManagementActivity.this,"已经是最后一页了",Toast.LENGTH_SHORT).show();
                }
                //大于总页数，不向下翻页
                httpPost = new ExpressBillingManagementHttpPost();
                String httpUrl = Statics.FinancialBillingManagementSearchUrl;
                String result = httpPost.searchHttp(httpUrl ,typeSpinnerString ,classifySpinnerString ,reasonSpinnerString,ExpressBillingManagementActivity.this,page);
                onLoad();

            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();

        newBilling.setVisibility(View.INVISIBLE);
        transferAccounts.setVisibility(View.INVISIBLE);
        String httpUrl = Statics.FinancialBillingManagementSearchUrl;
        httpPost.searchHttp(httpUrl ,"" ,"" ,"",ExpressBillingManagementActivity.this,1);//刷新页面
    }

    private void onLoad() {
        accountLv.stopRefresh();
        accountLv.stopLoadMore();
        accountLv.setRefreshTime("刚刚");
    }

    private static void initBroadCast() {
        //广播初始化 必须动态注册才能实现回调
        broadcast = new FreshenBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Config.BC_ONE);
        if(context!=null){
            context.registerReceiver(broadcast, intentFilter);

        }

        broadcast.setLazyLoadFace(new LazyLoadFace() {
            @Override
            public void AdapterRefresh(String type) {
                //具体更新
                if(type.equals("SearchReasonSpinner")){
                    Log.d("aleand","收到广播");
                    //适配器
                    //arr_adapter1.notifyDataSetChanged();
                    data_list = new ArrayList<>();
                    data_list.add("全部");
                    for (int i = 0; i < Statics.accountReasonList.size(); i++) {
                        data_list.add(Statics.accountReasonList.get(i).getName());
                        Log.v("test2", "data_list:" + Statics.accountReasonList.get(i).getName());

                    }
                    //适配器
                    arr_adapter = new ArrayAdapter<>(context, R.layout.spinner_display_style, R.id.txtvwSpinner, data_list);
                    //设置样式
                    arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
                    //加载适配器
                    reasonSpinner.setAdapter(arr_adapter);
                }
            }
        });
    }

    public static void AdapterRefresh(String type) {
        switch (type) {
            case "accountManagementAdapter":

                if (expressManagementAdapter != null){
                    expressManagementAdapter.notifyDataSetChanged();
                    ExpressBillingManagementActivity.progressDialog.dismiss();
                }

                break;
            case "reasonSpinner":
                /*if (arr_adapter != null) {
                    arr_adapter.notifyDataSetChanged();
                }*/
                initBroadCast();
                break;
        }

    }

}
