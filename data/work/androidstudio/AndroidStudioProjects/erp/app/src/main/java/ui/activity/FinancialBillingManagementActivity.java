package ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.admin.erp.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Tool.ToolUtils;
import Tool.crash.BaseActivity;
import Tool.statistics.Statics;
import broadcast.BroadCastTool;
import broadcast.FreshenBroadcastReceiver;
import http.HttpBasePost;
import http.HttpTypeConstants;
import http.HttpTypeUtil;
import model.*;
import model.FinancialManagement;
import portface.LazyLoadFace;
import ui.adpter.FinancialManagementAdapter;
import ui.xlistview.XListView;

public class FinancialBillingManagementActivity extends BaseActivity implements XListView.IXListViewListener{
    private View search;
    private View add;
    private Spinner typeSpinner, classifySpinner;
    public static Spinner customerNameSpinner;
    private List<String> data_list,data_type,data_classify;
    public static ArrayAdapter<String> arr_adapter;
    private String typeSpinnerString, classifySpinnerString, customerNameSpinnerString;
    public static FinancialManagementAdapter financialManagementAdapter;
    public static XListView accountLv;
    private Handler mHandler;
    public static int page = 1;
    private AlertDialog dlg;
    private boolean SearchBoolean = false;
    public static Context context;
    public static ProgressDialog progressDialog = null;//加载数据显示进度条
    static FreshenBroadcastReceiver broadcast;
    private HashMap<String,String> param;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("账目管理");
        setContentView(R.layout.activity_financial_billing_management);

        //添加返回按钮
        ToolUtils.backButton(this);
        context = getApplicationContext();
        init();
        //空查询
        page = 1;//显示页数
        String httpUrl = Statics.FinancialBillingManagementUrl;
        //刚进入页面就要显示数据
        progressDialog = ProgressDialog.show(FinancialBillingManagementActivity.this, "请稍等...", "获取数据中...", true);//显示进度条
        HttpBasePost.postHttp(Statics.FinancialBillingManagementUrl,null,HttpTypeConstants.FinancialBillingManagementUrlType);
        //String result = httpPost.searchHttp(httpUrl, "", "", "", FinancialBillingManagementActivity.this, page);
        accountLv.setPullLoadEnable(true);
        financialManagementAdapter = new FinancialManagementAdapter(FinancialBillingManagementActivity.this);
        accountLv.setAdapter(financialManagementAdapter);
        accountLv.setXListViewListener(this);
        accountLv.setDivider(new ColorDrawable(Color.BLUE));
        accountLv.setDividerHeight(1);
        mHandler = new Handler();
        //暂时先不处理
        accountLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {//点击时查看详细信息
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                xiangxiAlertDialog(position);
            }
        });
        spinnerType();
        search.setOnClickListener(new View.OnClickListener() {//查询
            @Override
            public void onClick(View v) {
                //条件查询
                page = 1;
                progressDialog = ProgressDialog.show(FinancialBillingManagementActivity.this, "请稍等...", "获取数据中...", true);//显示进度条
                SearchBoolean = true;
                String httpUrl = Statics.FinancialBillingManagementUrl;
                Log.d("FinancialBillingManagem", typeSpinnerString + classifySpinnerString + customerNameSpinner);
                if("全部".equals(typeSpinnerString)){
                    typeSpinnerString = "";
                }
                if("全部".equals(classifySpinnerString)){
                    classifySpinnerString = "";
                }
                if("全部".equals(customerNameSpinnerString)){
                    customerNameSpinnerString = "";
                }
                Log.d("FinancialBillingManagem", "参数验证"+typeSpinnerString+"&"+classifySpinnerString+"&"+customerNameSpinnerString);
                param=new HashMap<>();
                param.put("billType",typeSpinnerString);
                param.put("billClassify",classifySpinnerString);
                param.put("billCustomerId",customerNameSpinnerString);
                param.put("page",Integer.toString(page));
                param.put("option","1");
                param.put("rows","50");
                HttpBasePost.postHttp(Statics.FinancialBillingManagementUrl,param,HttpTypeConstants.FinancialBillingManagementUrlType);
                //String result = httpPost.searchHttp(httpUrl, typeSpinnerString, classifySpinnerString, customerNameSpinnerString, FinancialBillingManagementActivity.this, page);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(FinancialBillingManagementActivity.this, AddFinancialBillingManagerActivity.class);
                startActivity(in);
            }
        });
    }

    private void xiangxiAlertDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(FinancialBillingManagementActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.financialbilling_dialog_detailed_item, null);//获取自定义布局
        //Button back = (Button) layout.findViewById(R.id.back);
        TextView account = (TextView) layout.findViewById(R.id.account);//账目
        TextView classify = (TextView) layout.findViewById(R.id.classify);//分类
        //TextView content = (TextView) layout.findViewById(R.id.content);//内容
        TextView billingTime = (TextView) layout.findViewById(R.id.billingTime);//账单时间
        TextView price = (TextView) layout.findViewById(R.id.price);//金额
        TextView customerName = (TextView) layout.findViewById(R.id.customerName);//客户名
        TextView createTime = (TextView) layout.findViewById(R.id.createTime);//创建时间
        TextView remark = (TextView) layout.findViewById(R.id.remark);//备注
        TextView userName = (TextView) layout.findViewById(R.id.userName);//用户名
        List<FinancialManagement.DataBean> fm = Statics.financialManagementList.get(0).getData();
        account.setText(fm.get(position - 1).getBillType());
        classify.setText(fm.get(position - 1).getBillClassify());
        //content.setText(fm.get(position - 1).getBillClassification());
        //账单时间 fm.get(position - 1).getBillCreateTime()
        FinancialManagement.DataBean.BillTimeBean fdb = fm.get(position - 1).getBillTime();
        int years = fdb.getYear();//年
        int mon = fdb.getMonth();//月
        int date= fdb.getDate();//日
        String year = ToolUtils.timeDateFormat(Integer.toString(years));
        StringBuffer billingTimeSb=new StringBuffer();
        billingTimeSb.append(year).append("-").append(++mon).append("-").append(date);
        billingTime.setText(billingTimeSb.toString());
        remark.setText(fm.get(position - 1).getBillClassification());//备注
        customerName.setText(fm.get(position - 1).getBillCustomerId());
        //创建时间 fm.get(position - 1).getBillCreateTime()
        FinancialManagement.DataBean.BillCreateTimeBean fdb1 = fm.get(position - 1).getBillCreateTime();
        years = fdb1.getYear();//年
        mon = fdb1.getMonth();//月
        date= fdb1.getDate();//日
        int hours = fdb1.getHours();
        int min = fdb1.getMinutes();
        int second = fdb1.getMinutes();
        year = ToolUtils.timeDateFormat(Integer.toString(years));
        billingTimeSb=new StringBuffer();
        billingTimeSb.append(year).append("-").append(++mon).append("-")
                .append(date).append(" ").append(hours).append(":").append(min).append(":").append(second);
        createTime.setText(billingTimeSb.toString());
        userName.setText(fm.get(position - 1).getBillCreateBy().toString());//创建人就是用户名
        if("出账".equals(fm.get(position - 1).getBillClassify())){
            price.setText(" - "+fm.get(position - 1).getBillSum());
            account.setTextColor(Color.RED);
            classify.setTextColor(Color.RED);
            billingTime.setTextColor(Color.RED);
            remark.setTextColor(Color.RED);
            customerName.setTextColor(Color.RED);
            createTime.setTextColor(Color.RED);
            userName.setTextColor(Color.RED);
            price.setTextColor(Color.RED);
        }else{
            price.setText(Float.toString(fm.get(position - 1).getBillSum()));
        }
        //创建人就是用户名
        builder.setView(layout);
        dlg = builder.create();
        dlg.show();
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dlg.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        dlg.getWindow().setAttributes(lp);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String httpUrl = Statics.FinancialBillingManagementUrl;
        //httpPost.searchHttp(httpUrl ,"" ,"" ,"",FinancialBillingManagementActivity.this,1);//刷新页面
        Log.d("FinancialBillingManagem", "添加后刷新");
        HttpBasePost.postHttp(Statics.FinancialBillingManagementUrl,null,HttpTypeConstants.FinancialBillingManagementUrlType);
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

    private void spinnerType() {//下拉框 都使用动态的
        Log.d("test", "spinnerType");
        //数据
        data_list = new ArrayList<>();
        data_list.add("全部");
        for (FinancialAccount fa:Statics.financialAccountList) {
            Log.d("FinancialBillingManagem", "label:" + fa.getLabel().toString());
            data_list.add(fa.getLabel().toString());
        }
        data_type = new ArrayList<>();
        data_type = data_list;
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        typeSpinner.setAdapter(arr_adapter);
        data_list = null;
        //数据
        data_list = new ArrayList<>();
        data_list.add("全部");
        for (ExpressClassify.DataBean ac:Statics.expressClassifyList.get(0).getData()) {
            Log.d("FinancialBillingManagem", "label:" + ac.getName().toString());
            data_list.add(ac.getName().toString());
        }
        data_classify = new ArrayList<>();
        data_classify = data_list;
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        classifySpinner.setAdapter(arr_adapter);

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("test8", Integer.toString(position) + "ss");
                if (position == 0) {
                    typeSpinnerString = "全部";
                } else {
                    typeSpinnerString = Statics.financialAccountList.get(--position).getCode();//银行
                    Log.d("FinancialBillingManagem", "typeSpinnerString:" + typeSpinnerString);
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
                Log.v("test8", Integer.toString(position) + "ss");
                if (position == 0) {
                    classifySpinnerString = "全部";
                } else {
                    //typeSpinnerString = Statics.accountTypeList.get(--position).getId();
                    classifySpinnerString = Statics.expressClassifyList.get(0).getData().get(--position).getId();
                }
                data_list = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //数据
        //httpPost =new HttpPost();
        //httpPost.accountClassifySearchHttp(Static.AccountClassifyUrl, AccountManagementActivity.this);
        data_list = new ArrayList<>();
        data_list.add("全部");
        for (FinancialCustomer.DataBean fc : Statics.financialCustomersList.get(0).getData()) {
            Log.d("FinancialBillingManagem", "ac.getName:" + fc.getFy_name().toString());
            data_list.add(fc.getFy_name().toString());
        }
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        customerNameSpinner.setAdapter(arr_adapter);
        customerNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("test8", Integer.toString(position) + "ss");
                if (position == 0) {
                    customerNameSpinnerString = "全部";
                } else {
                    customerNameSpinnerString = Statics.financialCustomersList.get(0).getData().get(--position).getId();
                }
                data_list = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void init() {//初始化

        search = findViewById(R.id.search);
        add = findViewById(R.id.add);
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        classifySpinner = (Spinner) findViewById(R.id.classifySpinner);
        customerNameSpinner = (Spinner) findViewById(R.id.customerNameSpinner);
        accountLv = (XListView) findViewById(R.id.xListView);
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if("全部".equals(typeSpinnerString)){
                    typeSpinnerString = "";
                }
                if("全部".equals(classifySpinnerString)){
                    classifySpinnerString = "";
                }
                if("全部".equals(customerNameSpinnerString)){
                    customerNameSpinnerString = "";
                }
                param=new HashMap<>();
                param.put("billType",typeSpinnerString);
                param.put("billClassify",classifySpinnerString);
                param.put("billCustomerId",customerNameSpinnerString);
                param.put("page",Integer.toString(page));
                param.put("option","1");
                param.put("rows","50");
                Log.d("FinancialBillingManagem", "下拉刷新"+typeSpinnerString+":"+classifySpinnerString+":"+customerNameSpinnerString+page);
                HttpBasePost.postHttp(Statics.FinancialBillingManagementUrl,param,HttpTypeConstants.FinancialBillingManagementUrlType);
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
                    Toast.makeText(FinancialBillingManagementActivity.this, "已经是最后一页了", Toast.LENGTH_SHORT).show();
                }
                //大于总页数，不向下翻页
                if("全部".equals(typeSpinnerString)){
                    typeSpinnerString = "";
                }
                if("全部".equals(classifySpinnerString)){
                    classifySpinnerString = "";
                }
                if("全部".equals(customerNameSpinnerString)){
                    customerNameSpinnerString = "";
                }
                param=new HashMap<>();
                param.put("billType",typeSpinnerString);
                param.put("billClassify",classifySpinnerString);
                param.put("billCustomerId",customerNameSpinnerString);
                param.put("page",Integer.toString(page));
                param.put("option","1");
                param.put("rows","50");
                HttpBasePost.postHttp(Statics.FinancialBillingManagementUrl,param,HttpTypeConstants.FinancialBillingManagementUrlType);
                onLoad();

            }
        }, 2000);
    }

    private void onLoad() {
        accountLv.stopRefresh();
        accountLv.stopLoadMore();
        accountLv.setRefreshTime("刚刚");
    }

    public static void AdapterRefresh(String type) {
        switch (type) {
            case "FinancialManagementHttpPost":
                if (financialManagementAdapter != null) {
                    financialManagementAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                }

                break;
            case "reasonSpinner":
                break;
        }

    }

}
