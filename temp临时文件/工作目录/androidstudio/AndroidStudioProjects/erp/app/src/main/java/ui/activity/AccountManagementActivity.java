package ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.erp.R;

import java.util.ArrayList;
import java.util.List;

import Tool.statistics.Statics;
import broadcast.Config;
import broadcast.FreshenBroadcastReceiver;
import http.AccountManagementHttpPost;
import portface.LazyLoadFace;
import ui.adpter.AccountManagementAdapter;
import ui.xlistview.XListView;

public class AccountManagementActivity extends AppCompatActivity implements XListView.IXListViewListener, LazyLoadFace {
    private View search;
    private View add;
    private Spinner typeSpinner, classifySpinner;//,reasonSpinner;
    public static Spinner reasonSpinner;
    //private EditText customerEt;
    private static List<String> data_list;
    public static ArrayAdapter<String> arr_adapter;
    private String typeSpinnerString, classifySpinnerString, reasonSpinnerString;
    public static AccountManagementAdapter accountManagementAdapter;
    public static XListView mListView, accountLv;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> items = new ArrayList<>();
    private Handler mHandler;
    private int start = 0;
    private static int refreshCnt = 0;
    private static int page = 1;
    private AccountManagementHttpPost httpPost;
    private AlertDialog dlg;
    private boolean SearchBoolean = false;
    public static Context context;
    public static ProgressDialog progressDialog = null;//加载数据显示进度条
    static FreshenBroadcastReceiver broadcast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("物流账单管理");
        setContentView(R.layout.activity_account_management);

        context = getApplicationContext();
        initBroadCast();
        init();
        //空查询
        page = 1;//显示页数

        httpPost = new AccountManagementHttpPost();
        String httpUrl = Statics.AccountManagementSearchUrl;
        //刚进入页面就要显示数据
        progressDialog = ProgressDialog.show(AccountManagementActivity.this, "请稍等...", "获取数据中...", true);//显示进度条
        String result = httpPost.searchHttp(httpUrl, "", "", "", AccountManagementActivity.this, page);
        accountLv.setPullLoadEnable(true);
        accountManagementAdapter = new AccountManagementAdapter(AccountManagementActivity.this);
        accountLv.setAdapter(accountManagementAdapter);
        accountLv.setXListViewListener(this);
        mHandler = new Handler();
        accountLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {//点击时查看详细信息
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AccountManagementActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                final View layout = inflater.inflate(R.layout.accountmanager_dialog_detailed_item, null);//获取自定义布局
                Button back = (Button) layout.findViewById(R.id.back);
                TextView type = (TextView) layout.findViewById(R.id.type);//快递类型
                TextView classify = (TextView) layout.findViewById(R.id.classify);//业务分类
                TextView reason = (TextView) layout.findViewById(R.id.reason);//业务类型
                TextView price = (TextView) layout.findViewById(R.id.price);//金额
                TextView guest = (TextView) layout.findViewById(R.id.guest);//客户
                TextView remark = (TextView) layout.findViewById(R.id.remark);//备注
                TextView createuser = (TextView) layout.findViewById(R.id.createuser);//创建人
                TextView billingTime = (TextView) layout.findViewById(R.id.billingTime);//账单时间
                TextView createTime = (TextView) layout.findViewById(R.id.createTime);//创建时间
                type.setText(Statics.accountManagementList.get(position - 1).getType());
                classify.setText(Statics.accountManagementList.get(position - 1).getClassify());
                reason.setText(Statics.accountManagementList.get(position - 1).getReason());
                price.setText(Statics.accountManagementList.get(position - 1).getSum());
                guest.setText(Statics.accountManagementList.get(position - 1).getCustomerId());
                remark.setText(Statics.accountManagementList.get(position - 1).getRemark());
                createuser.setText(Statics.accountManagementList.get(position - 1).getCreateBy());
                billingTime.setText(Statics.accountManagementList.get(position - 1).getBillingTime());
                createTime.setText(Statics.accountManagementList.get(position - 1).getCreateTime());

                //创建人就是用户名
                builder.setView(layout);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg.dismiss();
                    }
                });
                dlg = builder.create();
                dlg.show();
            }
        });

        spinnerType();

        search.setOnClickListener(new View.OnClickListener() {//查询
            @Override
            public void onClick(View v) {
                //条件查询
                page = 1;
                progressDialog = ProgressDialog.show(AccountManagementActivity.this, "请稍等...", "获取数据中...", true);//显示进度条
                SearchBoolean = true;
                httpPost = new AccountManagementHttpPost();
                String httpUrl = Statics.AccountManagementSearchUrl;
                String result = httpPost.searchHttp(httpUrl, typeSpinnerString, classifySpinnerString, reasonSpinnerString, AccountManagementActivity.this, page);

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AccountManagementActivity.this, AddAccountManagerActivity.class);
                startActivity(in);
            }
        });
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
        for (int i = 0; i < Statics.accountClassifyList.size(); i++) {
            data_list.add(Statics.accountClassifyList.get(i).getName());
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
                    classifySpinnerString = Statics.accountClassifyList.get(--position).getId();
                }
                Log.v("test2", "classifySpinnerString:" + classifySpinnerString);
                //数据
                httpPost = new AccountManagementHttpPost();
                Log.v("test2", "PrereasonSpinner:" + Boolean.toString(AccountManagementActivity.reasonSpinner == null));
                httpPost.accountReasonSearchHttp(Statics.AccountReasonUrl, classifySpinnerString, AccountManagementActivity.this);

                data_list = new ArrayList<>();
                data_list.add("全部");
                for (int i = 0; i < Statics.accountReasonList.size(); i++) {
                    data_list.add(Statics.accountReasonList.get(i).getName());
                    Log.v("test2", "data_list:" + Statics.accountReasonList.get(i).getName());

                }
                //适配器
                arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_display_style, R.id.txtvwSpinner, data_list);
                //设置样式
                arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
                //加载适配器
                reasonSpinner.setAdapter(arr_adapter);

                Log.v("test2", "After:" + Boolean.toString(AccountManagementActivity.reasonSpinner == null));
                reasonSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position < Statics.accountReasonList.size()-1) {//需要仔细
                            if(position == 0){
                                reasonSpinnerString = "全部";
                            }else{
                                reasonSpinnerString = Statics.accountReasonList.get(--position).getId();
                            }
                        } else {
                            Log.v("test", "position:" + Integer.toString(position) + "@" +
                                    "Static.accountReasonList.size()" + Integer.toString(Statics.accountReasonList.size()));
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
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                httpPost = new AccountManagementHttpPost();
                String httpUrl = Statics.AccountManagementSearchUrl;
                String result = httpPost.searchHttp(httpUrl ,typeSpinnerString ,classifySpinnerString ,reasonSpinnerString,AccountManagementActivity.this,page);
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
                    Toast.makeText(AccountManagementActivity.this,"已经是最后一页了",Toast.LENGTH_SHORT).show();
                }
                //大于总页数，不向下翻页
                httpPost = new AccountManagementHttpPost();
                String httpUrl = Statics.AccountManagementSearchUrl;
                String result = httpPost.searchHttp(httpUrl ,typeSpinnerString ,classifySpinnerString ,reasonSpinnerString,AccountManagementActivity.this,page);
                onLoad();

            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String httpUrl = Statics.AccountManagementSearchUrl;
        httpPost.searchHttp(httpUrl ,"" ,"" ,"",AccountManagementActivity.this,1);//刷新页面
    }

    private void onLoad() {
        accountLv.stopRefresh();
        accountLv.stopLoadMore();
        accountLv.setRefreshTime("刚刚");
    }

    private void initBroadCast() {
        //广播初始化 必须动态注册才能实现回调
        broadcast = new FreshenBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Config.BC_ONE);
        context.registerReceiver(broadcast, intentFilter);

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

    @Override
    public void AdapterRefresh(String type) {
        switch (type) {
            case "accountManagementAdapter":
                if (accountManagementAdapter != null){
                    accountManagementAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
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
