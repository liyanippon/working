package ui.activity;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.erp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import http.Constants;
import http.ExpressNumberManagementHttpPost;
import portface.LazyLoadFace;
import ui.adpter.ExpressNumberManagementAdapter;
import ui.xlistview.XListView;

/**
 * Created by admin on 2017/3/22.
 */

public class ExpressNumberManagerActivity extends AppCompatActivity implements XListView.IXListViewListener, LazyLoadFace {
    private ImageView search,add;
    private Spinner typeSpinner, expressPersonSpinner;
    private EditText searchTime;
    private static List<String> data_list;
    public static ArrayAdapter<String> arr_adapter;
    private String typeSpinnerString, expressPersonSpinnerString,billingTimeString;

    public static ExpressNumberManagementAdapter enmAdapter;
    //xList
    public static XListView mListView, expressLv;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> items = new ArrayList<>();
    private Handler mHandler;
    private int start = 0;
    private static int refreshCnt = 0;
    private static int page = 1;
    private ExpressNumberManagementHttpPost httpPost;
    private AlertDialog dlg;
    private boolean SearchBoolean = false;
    private Calendar calendar;
    private int currentYear,currentMon,currentDate;

    public static ProgressDialog progressDialog = null;//加载数据显示进度条
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("业务员揽件量");
        setContentView(R.layout.activity_express_manager);

        init();
        //空查询
        page = 1;//显示页数

        calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMon = calendar.get(Calendar.MONTH)+1;
        currentDate = calendar.get(Calendar.DAY_OF_MONTH);
        searchTime.setText("全部");
        searchTime.setOnClickListener(o);
        searchTime.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                searchTime.setText("全部");
                billingTimeString = "全部";
                return true;
            }
        });

        httpPost = new ExpressNumberManagementHttpPost();
        String httpUrl = Constants.ExpressCountSearch;
        //刚进入页面就要显示数据
        //accountManagementAdapter =new AccountManagementAdapter(getApplicationContext());
        //accountLv.setAdapter(accountManagementAdapter);
        progressDialog = ProgressDialog.show(ExpressNumberManagerActivity.this, "请稍等...", "获取数据中...", true);//显示进度条
        String result = httpPost.searchHttp(httpUrl, "", "", "", ExpressNumberManagerActivity.this, page);
        //accountLv = (XListView) findViewById(R.id.xListView);
        expressLv.setPullLoadEnable(true);
        enmAdapter = new ExpressNumberManagementAdapter(ExpressNumberManagerActivity.this);
        expressLv.setAdapter(enmAdapter);
        expressLv.setXListViewListener(this);
        mHandler = new Handler();
        spinnerType();
        search.setOnClickListener(o);
        add.setOnClickListener(o);
    }


    View.OnClickListener o = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
               switch (v.getId()){
                   case R.id.searchTime:
                       searchTime();
                        break;
                   case R.id.search:
                       //条件查询
                       page = 1;
                       SearchBoolean = true;
                       billingTimeString = searchTime.getText().toString();
                       httpPost = new ExpressNumberManagementHttpPost();
                       String httpUrl = Constants.ExpressCountSearch;
                       String result = httpPost.searchHttp(httpUrl, expressPersonSpinnerString,typeSpinnerString, billingTimeString, ExpressNumberManagerActivity.this, page);
                        break;
                   case R.id.add:
                       Intent in = new Intent(ExpressNumberManagerActivity.this, AddExpressNumberActivity.class);
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
        data_list.add("全部");
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
        data_list.add("全部");
        String name;
        for (int i = 0; i < Constants.expressPersonsList.size(); i++) {
            //data_list.add(Constants.expressPersonsList.get(i).getName());
            Log.d("dd",Constants.expressPersonsList.get(i).getName());
            if(Constants.expressPersonsList.get(i).getName().contains("（")){
                name = Constants.expressPersonsList.get(i).getName().split("（", 2)[0];//字符串以‘（’截取
                data_list.add(name);
            }else if(Constants.expressPersonsList.get(i).getName().contains("(")){
                name = Constants.expressPersonsList.get(i).getName().split("\\(", 2)[0];//字符串以‘（’截取
                data_list.add(name);
            }else{
                name = Constants.expressPersonsList.get(i).getName();
                data_list.add(name);
            }
           /* name = Constants.expressPersonsList.get(i).getName().split("（", 2)[0];//字符串以‘（’截取
            data_list.add(name);*/
            name = null;
            Log.d("dd","ddL:"+Constants.expressPersonsList.get(i).getName().split("（", 2)[0]);
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
                if(position == 0){
                    typeSpinnerString = "全部";
                }else{
                    typeSpinnerString = Constants.accountTypeList.get(--position).getId();
                }
                data_list = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        expressPersonSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if(position == 0){
                        expressPersonSpinnerString = "全部";
                    }else{
                        expressPersonSpinnerString = Constants.expressPersonsList.get(--position).getId();
                    }
                    data_list = null;

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
        expressLv = (XListView) findViewById(R.id.xListView);
        searchTime = (EditText) findViewById(R.id.searchTime);
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                httpPost = new ExpressNumberManagementHttpPost();
                String httpUrl = Constants.AccountManagementSearchUrl;
                String result = httpPost.searchHttp(httpUrl ,expressPersonSpinnerString ,typeSpinnerString ,billingTimeString,ExpressNumberManagerActivity.this,page);
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
                    Toast.makeText(ExpressNumberManagerActivity.this,"已经是最后一页了",Toast.LENGTH_SHORT).show();
                }
                //大于总页数，不向下翻页
                httpPost = new ExpressNumberManagementHttpPost();
                String httpUrl = Constants.ExpressCountSearch;
                Log.d("typeyy",expressPersonSpinnerString+typeSpinnerString+billingTimeString);
                String result = httpPost.searchHttp(httpUrl ,expressPersonSpinnerString ,typeSpinnerString ,billingTimeString,ExpressNumberManagerActivity.this,page);
                enmAdapter.notifyDataSetChanged();
                onLoad();

            }
        }, 2000);
    }

    private void searchTime() {

        //日期选择器
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(ExpressNumberManagerActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month++;
                        searchTime.setText(String.format("%d-%02d-%02d",year,month,dayOfMonth));
                        billingTimeString = String.format("%d-%02d-%02d",year,month,dayOfMonth);
                    }
                },
                mYear, mMonth, mDay).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        String httpUrl = Constants.ExpressCountSearch;
        httpPost.searchHttp(httpUrl ,"" ,"" ,"",ExpressNumberManagerActivity.this,1);//刷新页面
    }

    private void onLoad() {
        expressLv.stopRefresh();
        expressLv.stopLoadMore();
        expressLv.setRefreshTime("刚刚");
    }

    @Override
    public void AdapterRefresh(String type) {
        switch (type) {
            case "accountManagementAdapter":
                if (enmAdapter != null){
                    enmAdapter.notifyDataSetChanged();
                    Log.d("test","页面刷新");
                    progressDialog.dismiss();
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
