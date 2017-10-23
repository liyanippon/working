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

import java.util.ArrayList;
import java.util.List;

import Tool.ToolUtils;
import Tool.crash.BaseActivity;
import Tool.statistics.Statics;
import http.ExpressStatisticsHttpPost;
import model.ExpressPersonStatistic;
import model.ExpressPersonStatisticsXiangqing;
import model.TimeExpressStatistics;
import portface.LazyLoadFace;
import ui.adpter.ExpressPersonStatisticsAdapter;
import ui.adpter.TimeExpressStatisticsAdapter;
import ui.adpter.XiangxiExpressPersonStatisticsAdapter;
import ui.fragement.ExpressPiecesChartsFragementActivity;
import ui.fragement.ExpressPiecesDetailsChartsFragementActivity;
import ui.fragement.ExpressPiecesPersonDetailsChartsFragementActivity;

public class ExpressStatisticsActivity extends BaseActivity implements LazyLoadFace {
    public static ListView timeListView, expressPersonListView;
    private ViewGroup tableTitle, tableTitle1;
    private ExpressStatisticsHttpPost expressStatisticsHttpPost;
    private Spinner typeSpinner, yearSpinner;
    private ImageView search;
    private List<String> data_list;
    public static ArrayAdapter<String> arr_adapter;
    private String typeSpinnerString = "024001", yearSpinnerString = null;
    private List<TimeExpressStatistics> timeExpressStatisticsesList;
    private List<ExpressPersonStatistic> expressPersonStatisticList;
    private List<ExpressPersonStatisticsXiangqing> epsXList;
    public static TimeExpressStatisticsAdapter timeAdapter;
    public static ExpressPersonStatisticsAdapter expressPersionAdapter;
    public static XiangxiExpressPersonStatisticsAdapter xiangxiAdapter;
    private AlertDialog dlg;
    private ListView mouth_statistic_lv, listView;
    private ImageView zhuXing;
    private String month;
    boolean onsearchclick = false;
    public static ProgressDialog progressDialog = null;//加载数据显示进度条
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("业务员揽件量统计分析");
        setContentView(R.layout.activity_express_statistics);

        //添加返回按钮
        ToolUtils.backButton(this);
        init();
        spinnerType();
        if (Statics.expressYear.size() > 0) {
            yearSpinnerString = Statics.expressYear.get(0);//默认赋值
        }
        yearSpinnerString = "2017";//默认赋值
        //首次访问 空查询
        progressDialog = ProgressDialog.show(ExpressStatisticsActivity.this, "请稍等...", "获取数据中...", true);//显示进度条
        expressStatisticsHttpPost = new ExpressStatisticsHttpPost();
        expressStatisticsHttpPost.searchTimeHttp(Statics.TimeStatisticSearchUrl, yearSpinnerString, "", ExpressStatisticsActivity.this);
        timeExpressStatisticsesList = new ArrayList<>();
        timeExpressStatisticsesList = Statics.expressTimeList;
        timeAdapter = new TimeExpressStatisticsAdapter(ExpressStatisticsActivity.this, timeExpressStatisticsesList);
        timeListView.setAdapter(timeAdapter);
        search.setOnClickListener(o);
        zhuXing.setOnClickListener(o);

        //listView点击事件
        timeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //选中变色
                //ToolUtils.selectColor(parent,position);
                //确定月份
                month = Statics.expressTimeList.get(position).getMonth();
                //判断是否点击过查询，若是则typeSpinnerString不变，否则为空
                if (!onsearchclick) {
                    typeSpinnerString = "";
                }
                expressStatisticsHttpPost.searchExpressPersonStatisticsHttp(Statics.ExpressStatisticSearchUrl, yearSpinnerString, typeSpinnerString, month, ExpressStatisticsActivity.this);
                expressPersonStatisticList = Statics.expressPersonStatisticList;
                expressPersionAdapter = new ExpressPersonStatisticsAdapter(ExpressStatisticsActivity.this, expressPersonStatisticList);
                expressPersonListView.setAdapter(expressPersionAdapter);
                expressPersonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                                 @Override
                                                                 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                                     //选中变色
                                                                     ToolUtils.selectColor(parent, position);
                                                                     String expressPersonId = Statics.expressPersonStatisticList.get(position).getName_id();//ID
                                                                     month = Statics.expressPersonStatisticList.get(position).getMonth();
                                                                     Log.d("xiangxixinx", "---------------------------------------------------------------");
                                                                     listView = null;
                                                                     //递类型，月份，客户名客户名以检索
                                                                     Log.d("test90", "express:" + typeSpinnerString);
                                                                     Log.d("xiangxixinx", yearSpinnerString + "@" + typeSpinnerString + "@" + month + expressPersonId);
                                                                     expressStatisticsHttpPost.searchXqExpressPersonHttp(Statics.ExpressXqTimeSearchUrl, "2017", typeSpinnerString, month, expressPersonId, ExpressStatisticsActivity.this);
                                                                     //显示对话框，在对话框中使用ListView
                                                                     AlertDialog.Builder builder = new AlertDialog.Builder(ExpressStatisticsActivity.this);
                                                                     LayoutInflater inflater = getLayoutInflater();
                                                                     final View layout = inflater.inflate(R.layout.express_person_statistics_dialog_detailed_item, null);//获取自定义布局
                                                                     //Button back = (Button) layout.findViewById(R.id.back);
                                                                     listView = (ListView) layout.findViewById(R.id.lv);
                                                                     //tableTitle = (ViewGroup) layout.findViewById(R.id.table_title);
                                                                     //tableTitle.setBackgroundColor(Color.rgb(230, 240, 255));
                                                                     epsXList = Statics.epsXList;
                                                                     for (int i = 0; i < epsXList.size(); i++) {
                                                                         Log.d("xiangxixinx", epsXList.get(i).getNumeric() + "@" + epsXList.get(i).getTime());
                                                                     }

                                                                     xiangxiAdapter = new XiangxiExpressPersonStatisticsAdapter(ExpressStatisticsActivity.this, epsXList);
                                                                     listView.setAdapter(xiangxiAdapter);
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
                                                             }
                );
                expressPersonListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        //ToolUtils.selectColor(parent,position);
                        String expressPersonId = Statics.expressPersonStatisticList.get(position).getName_id();//ID
                        listView = null;
                        //递类型，月份，客户名客户名以检索
                        //expressStatisticsHttpPost.searchXqExpressPersonHttp(Statics.ExpressXqTimeSearchUrl, yearSpinnerString, typeSpinnerString, month, expressPersonId, ExpressStatisticsActivity.this);
                        Log.d("test90", "sdfa::" + typeSpinnerString);
                        Log.d("test90", "sdfa::" + yearSpinnerString);
                        Log.d("test90", "position::" + Integer.toString(position));
                        for (int i = 0; i < Statics.expressTimeList.size(); i++) {
                            Log.d("test90", "getSum::" + Statics.expressTimeList.get(i).getSum());
                        }
                        month = Statics.expressPersonStatisticList.get(position).getMonth();
                        Log.d("test00", "month:" + month);
                        Intent in = new Intent(ExpressStatisticsActivity.this, ExpressPiecesPersonDetailsChartsFragementActivity.class);
                        in.putExtra("year", yearSpinnerString);
                        in.putExtra("month", month);
                        in.putExtra("type", typeSpinnerString);
                        in.putExtra("expressPersonId", expressPersonId);
                        startActivity(in);
                        return true;
                    }
                });
            }
        });
        timeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //选中变色
                //ToolUtils.selectColor(parent,position);
                month = Statics.expressTimeList.get(position).getMonth();
                Intent in = new Intent(ExpressStatisticsActivity.this, ExpressPiecesDetailsChartsFragementActivity.class);
                in.putExtra("year", yearSpinnerString);
                in.putExtra("month", month);
                in.putExtra("type", typeSpinnerString);
                startActivity(in);
                return false;
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
                    Log.v("test2", "R.id.search");
                    progressDialog = ProgressDialog.show(ExpressStatisticsActivity.this, "请稍等...", "获取数据中...", true);//显示进度条
                    onsearchclick = true;
                    expressStatisticsHttpPost.searchTimeHttp(Statics.TimeStatisticSearchUrl, yearSpinnerString, typeSpinnerString, ExpressStatisticsActivity.this);
                    timeExpressStatisticsesList = Statics.expressTimeList;
                    timeAdapter = new TimeExpressStatisticsAdapter(ExpressStatisticsActivity.this, timeExpressStatisticsesList);
                    timeListView.setAdapter(timeAdapter);
                    expressPersonStatisticList = null;
                    expressPersionAdapter = new ExpressPersonStatisticsAdapter(ExpressStatisticsActivity.this, expressPersonStatisticList);
                    expressPersonListView.setAdapter(expressPersionAdapter);
                    break;
                case R.id.zhuXing:
                    Intent in = new Intent(ExpressStatisticsActivity.this, ExpressPiecesChartsFragementActivity.class);
                    Statics.dayCount = false;
                    startActivity(in);
                    break;
            }
        }
    };
    private void spinnerType() {
        //数据
        //httpPost =new HttpPost();
        //httpPost.accountTypeSearchHttp(Statics.AccountTypeUrl, AccountManagementActivity.this);
        data_list = new ArrayList<>();
        data_list.clear();
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
                if (position == 0) {
                    typeSpinnerString = "全部";
                } else {
                    typeSpinnerString = Statics.accountTypeList.get(--position).getId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //数据
        //httpPost =new HttpPost();
        //httpPost.accountTypeSearchHttp(Statics.AccountTypeUrl, AccountManagementActivity.this);

        ArrayList<String> yearlist = new ArrayList<>();
        yearlist.add("全部");
        for (int i = 0; i < Statics.expressYear.size(); i++) {
            yearlist.add(Statics.expressYear.get(i));
        }


        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, yearlist);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        yearSpinner.setAdapter(arr_adapter);
        data_list = null;
        yearlist = null;
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    yearSpinnerString = "全部";
                } else {
                    yearSpinnerString = Statics.expressYear.get(--position);
                }
                data_list = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
    public void init() {
        timeListView = (ListView) findViewById(R.id.timeListView);
        tableTitle = (ViewGroup) findViewById(R.id.table_title);
        tableTitle.setBackgroundColor(Color.rgb(230, 240, 255));
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        search = (ImageView) findViewById(R.id.search);
        expressPersonListView = (ListView) findViewById(R.id.expressPersonListView);
        tableTitle = (ViewGroup) findViewById(R.id.table_title);
        tableTitle.setBackgroundColor(Color.rgb(230, 240, 255));
        tableTitle1 = (ViewGroup) findViewById(R.id.table_title1);
        tableTitle1.setBackgroundColor(Color.rgb(230, 240, 255));

        zhuXing = (ImageView) findViewById(R.id.zhuXing);
        if (!onsearchclick) {
            typeSpinnerString = "";
        }

    }
    @Override
    public void AdapterRefresh(String type) {//刷新adapter
        switch (type) {
            case "timeAdapter":
                progressDialog.dismiss();
                timeAdapter.notifyDataSetChanged();
                break;
            case "expressPersonAdapter":
                expressPersionAdapter.notifyDataSetChanged();
                break;
            case "xiangxiAdapter":
                if (xiangxiAdapter != null) {
                    xiangxiAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
