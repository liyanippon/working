package ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.admin.erp.R;
import java.util.ArrayList;
import java.util.List;
import Tool.ToolUtils;
import Tool.crash.BaseActivity;
import Tool.statistics.Statics;
import model.javabean.ExpressManagement;
import presenter.ExpressBillingManagerPresenter;
import presenter.ExpressBillingManagerPresenterImpl;
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
    public static List<String> data_list;
    public static ArrayAdapter<String> arr_adapter;
    private String typeSpinnerString, reasonSpinnerString;
    private static String classifySpinnerString;
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
    public static boolean SearchBoolean = false;
    public static Context context;
    public static ProgressDialog progressDialog = null;//加载数据显示进度条
    static FreshenBroadcastReceiver broadcast;
    private GridView gridView;
    public static boolean deleteSuccess = false;
    public static Activity activityExpress;
    public static boolean isAdd = false;
    ExpressBillingManagerPresenterImpl ebmi = new ExpressBillingManagerPresenterImpl();
    //ExpressBillingManagerPresenter ebmPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("物流账单管理");
        setContentView(R.layout.activity_expressbilling_management);

        //ebmPresenter = new ExpressBillingManagerPresenterImpl();
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
        //ebmPresenter.searchClick(httpUrl, "", "", "", ExpressBillingManagementActivity.this, page);
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
                ExpressManagement.DataBean.RowsBean edrb=Statics.expressManagementList.get(0).getData().get(0).getRows().get(position-1);
                type.setText(edrb.getType());
                classify.setText(edrb.getClassify());
                reason.setText(edrb.getReason());
                payMethod.setText(edrb.getPaymentMethod());
                price.setText(edrb.getSum().toString());
                guest.setText(edrb.getCustomerId());
                remark.setText(edrb.getDescription());
                createuser.setText(edrb.getCreateBy());
                int temp = edrb.getBillingTime().getMonth();
                int temps = edrb.getCreateTime().getMonth();
                String billingTimes = ToolUtils.timeDateFormat(Integer.toString(edrb.getBillingTime().getYear()))+"-"+(++temp)
                        +"-"+edrb.getBillingTime().getDate();
                billingTime.setText(billingTimes);
                String createTimes = ToolUtils.timeDateFormat(Integer.toString(edrb.getCreateTime().getYear()))+"-"+(++temps)
                        +"-"+edrb.getCreateTime().getDate()+" "
                        +edrb.getCreateTime().getHours()+":"
                        + edrb.getCreateTime().getMinutes()+":"
                        +edrb.getCreateTime().getSeconds();
                billingTime.setText(billingTimes);
                createTime.setText(createTimes);

                if(!"进账".equals(edrb.getClassify())){
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
                    type.setText(edrb.getType().split("<b>")[1].split("</b>")[0]);
                    classify.setText(edrb.getClassify().split("<b>")[1].split("</b>")[0]);
                    reason.setText(edrb.getReason().split("<b>")[1].split("</b>")[0]);
                    payMethod.setText(edrb.getPaymentMethod().split("<b>")[1].split("</b>")[0]);
                    guest.setText(edrb.getCustomerId().split("<b>")[1].split("</b>")[0]);
                    createuser.setText(edrb.getCreateBy().split("<b>")[1].split("</b>")[0]);

                }else {
                    if(edrb.getType().indexOf("<b>")!=-1){
                        type.setText(edrb.getType().split("<b>")[1].split("</b>")[0]);
                    }
                    if(edrb.getClassify().indexOf("<b>")!=-1){
                        classify.setText(edrb.getClassify().split("<b>")[1].split("</b>")[0]);
                    }
                    if (edrb.getReason().indexOf("<b>")!=-1){
                        reason.setText(edrb.getReason().split("<b>")[1].split("</b>")[0]);
                    }
                    if (edrb.getPaymentMethod().indexOf("<b>")!=-1) {
                        payMethod.setText(edrb.getPaymentMethod().split("<b>")[1].split("</b>")[0]);
                    }
                    if (edrb.getCustomerId().indexOf(">")!=-1) {
                        guest.setText(edrb.getCustomerId().split(">")[1].split("</")[0]);
                    }
                    if (edrb.getCreateBy().indexOf("<b>")!=-1) {
                        createuser.setText(edrb.getCreateBy().split("<b>")[1].split("</b>")[0]);
                    }
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
        //数据（快递类型：圆通，韵达）
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

        //数据（业务分类：进账，出账）
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

                Statics.ActivityType = "ExpressBillingManagementActivity";
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

                Log.d("ExpressBillingManagemen", "quanbu" + classifySpinnerString);
                for (int i = 0; i < Statics.accountReasonList.size() ; i++) {
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
        add.setOnClickListener(o);
        search.setOnClickListener(o);
    }

    View.OnClickListener o = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.search:
                    ebmi.search(activityExpress,httpPost,typeSpinnerString,classifySpinnerString,reasonSpinnerString);
                    break;
                case R.id.add:
                    ebmi.add(activityExpress);
                    break;
            }
        }
    };

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
                Statics.isPageUpload = true;
                Log.d("ExpressBillingManagemen", "翻页");
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
        if(isAdd){
            httpPost.searchHttp(httpUrl ,"" ,"" ,"",ExpressBillingManagementActivity.this,1);//刷新页面
            isAdd = false;
        }

    }

    private void onLoad() {
        accountLv.stopRefresh();
        accountLv.stopLoadMore();
        accountLv.setRefreshTime("刚刚");
    }

    private static void initBroadCast() {
        Log.d("express","广播初始化");
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
                    for (int i = 0; i < Statics.accountReasonList.size() && !"全部".equals(classifySpinnerString); i++) {
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
                Log.d("ExpressBillingManagemen", "刷新");
                if (expressManagementAdapter != null){
                    expressManagementAdapter.notifyDataSetChanged();
                    ExpressBillingManagementActivity.progressDialog.dismiss();
                }
                if(Statics.isDelete){
                    Toast.makeText(activityExpress,"删除成功",Toast.LENGTH_SHORT).show();
                    Statics.isDelete = false;
                }
                if(Statics.isTransfer){
                    Toast.makeText(activityExpress,"转账成功",Toast.LENGTH_SHORT).show();
                    Statics.isTransfer = false;
                }

                break;
            case "reasonSpinner":
                if (arr_adapter != null) {
                    arr_adapter.notifyDataSetChanged();
                }
                initBroadCast();
                break;
        }
    }

}
