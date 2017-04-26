package ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.erp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import http.AccountManagementHttpPost;
import http.Constants;
import portface.LazyLoadFace;
import ui.adpter.ExpressNumberManagementAdapter;
import ui.xlistview.XListView;

public class ExpressPersonManagerActivity extends AppCompatActivity implements XListView.IXListViewListener, LazyLoadFace {
    private ImageView search,add;
    private Spinner typeSpinner, expressPersonSpinner;
    //private EditText searchTime;
    private static List<String> data_list;
    public static ArrayAdapter<String> arr_adapter;
    private String typeSpinnerString, classifySpinnerString;

    public static ExpressNumberManagementAdapter enmAdapter;
    //xList
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
    private Calendar calendar;
    private int currentYear,currentMon,currentDate;

    public static ProgressDialog progressDialog = null;//加载数据显示进度条
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express_person_manager);

        init();
        //空查询
        page = 1;//显示页数


        calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMon = calendar.get(Calendar.MONTH)+1;
        currentDate = calendar.get(Calendar.DAY_OF_MONTH);
        //searchTime.setText(String.format("%d-%d-%d",currentYear,currentMon,currentDate));
        //searchTime.setOnClickListener(o);

        httpPost = new AccountManagementHttpPost();
        String httpUrl = Constants.AccountManagementSearchUrl;
        //刚进入页面就要显示数据
        //accountManagementAdapter =new AccountManagementAdapter(getApplicationContext());
        //accountLv.setAdapter(accountManagementAdapter);
        //progressDialog = ProgressDialog.show(ExpressNumberManagerActivity.this, "请稍等...", "获取数据中...", true);//显示进度条
        String result = httpPost.searchHttp(httpUrl, "", "", "", ExpressPersonManagerActivity.this, page);
        //accountLv = (XListView) findViewById(R.id.xListView);
        accountLv.setPullLoadEnable(true);
        //accountManagementAdapter =new AccountManagementAdapter(getApplicationContext());
        enmAdapter = new ExpressNumberManagementAdapter(ExpressPersonManagerActivity.this);
        accountLv.setAdapter(enmAdapter);
        accountLv.setXListViewListener(this);
        mHandler = new Handler();
        accountLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {//点击时查看详细信息
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ExpressPersonManagerActivity.this);
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
                type.setText(Constants.accountManagementList.get(position - 1).getType());
                classify.setText(Constants.accountManagementList.get(position - 1).getClassify());
                reason.setText(Constants.accountManagementList.get(position - 1).getReason());
                price.setText(Constants.accountManagementList.get(position - 1).getSum());
                guest.setText(Constants.accountManagementList.get(position - 1).getCustomerId());
                remark.setText(Constants.accountManagementList.get(position - 1).getRemark());
                createuser.setText(Constants.accountManagementList.get(position - 1).getcreateBy());
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
        search.setOnClickListener(o);
        add.setOnClickListener(o);
    }


    View.OnClickListener o = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){

                /*case R.id.searchTime:
                    searchTime();
                    break;*/
                case R.id.search:
                    //条件查询
                    page = 1;
                    SearchBoolean = true;
                    httpPost = new AccountManagementHttpPost();
                    String httpUrl = Constants.AccountManagementSearchUrl;
                    //String result = httpPost.searchHttp(httpUrl, typeSpinnerString, classifySpinnerString, reasonSpinnerString, ExpressNumberManagerActivity.this, page);
                    break;
                case R.id.add:
                    Intent in = new Intent(ExpressPersonManagerActivity.this, AddExpressNumberActivity.class);
                    startActivity(in);
                    break;
            }
        }
    };

    private void spinnerType() {
        Log.d("test", "spinnerType");
        //数据
        //httpPost =new HttpPost();
        //httpPost.accountTypeSearchHttp(Constants.AccountTypeUrl, AccountManagementActivity.this);
        data_list = new ArrayList<>();
        for (int i = 0; i < Constants.accountTypeList.size(); i++) {
            data_list.add(Constants.accountTypeList.get(i).getName());
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
        //httpPost.accountClassifySearchHttp(Constants.AccountClassifyUrl, AccountManagementActivity.this);
        data_list = new ArrayList<>();
        for (int i = 0; i < Constants.accountClassifyList.size(); i++) {
            data_list.add(Constants.accountClassifyList.get(i).getName());
        }
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        expressPersonSpinner.setAdapter(arr_adapter);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeSpinnerString = Constants.accountTypeList.get(position).getId();
                data_list = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        expressPersonSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                classifySpinnerString = Constants.accountClassifyList.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void init() {

        search = (ImageView) findViewById(R.id.search);
        add = (ImageView) findViewById(R.id.add);
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        expressPersonSpinner = (Spinner) findViewById(R.id.expressPersonSpinner);
        accountLv = (XListView) findViewById(R.id.xListView);
        //searchTime = (EditText) findViewById(R.id.searchTime);
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //start = ++refreshCnt;
                if (!SearchBoolean) {
                    typeSpinnerString = "";
                    classifySpinnerString = "";
                }
                httpPost = new AccountManagementHttpPost();
                String httpUrl = Constants.AccountManagementSearchUrl;
                //String result = httpPost.searchHttp(httpUrl ,typeSpinnerString ,classifySpinnerString ,reasonSpinnerString, ExpressNumberManagerActivity.this,page);
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
                if (page >= Constants.page) {
                    page = Constants.page;
                    Toast.makeText(ExpressPersonManagerActivity.this,"已经是最后一页了",Toast.LENGTH_SHORT).show();
                }
                //大于总页数，不向下翻页
                httpPost = new AccountManagementHttpPost();
                String httpUrl = Constants.AccountManagementSearchUrl;
                if (!SearchBoolean) {
                    typeSpinnerString = "";
                    classifySpinnerString = "";
                }
                //String result = httpPost.searchHttp(httpUrl ,typeSpinnerString ,classifySpinnerString ,reasonSpinnerString,ExpressNumberManagerActivity.this,page);
                //accountManagementAdapter.notifyDataSetChanged();
                onLoad();

            }
        }, 2000);
    }

   /* private void searchTime() {

        //日期选择器
        new DatePickerDialog(ExpressPersonManagerActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                searchTime.setText(String.format("%d-%d-%d",i,i1+1,i2));
            }
        },currentYear,currentMon,currentDate).show();

    }*/

    @Override
    protected void onResume() {
        super.onResume();
        String httpUrl = Constants.AccountManagementSearchUrl;
        httpPost.searchHttp(httpUrl ,"" ,"" ,"",ExpressPersonManagerActivity.this,1);//刷新页面
    }

    private void onLoad() {
        accountLv.stopRefresh();
        accountLv.stopLoadMore();
        accountLv.setRefreshTime("刚刚");
    }

    @Override
    public void AdapterRefresh(String type) {
        switch (type) {
            case "accountManagementAdapter":
                if (enmAdapter != null){
                    enmAdapter.notifyDataSetChanged();
                    //progressDialog.dismiss();
                }

                break;
            case "reasonSpinner":
                if (arr_adapter != null) {
                    arr_adapter.notifyDataSetChanged();
                }
                break;
        }

    }
}
