package ui.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.erp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import Tool.ToolUtils;
import Tool.crash.BaseActivity;
import Tool.statistics.Statics;
import model.javabean.ProjectAllPageData;
import presenter.ExpressBillingManagerPresenterImpl;
import broadcast.FreshenBroadcastReceiver;
import http.HttpBasePost;
import http.HttpTypeConstants;
import ui.adpter.ProjectManagementAdapter;
import ui.adpter.TimeBackAdpter;
import ui.xlistview.XListView;

public class ProjectManagementActivity extends BaseActivity implements XListView.IXListViewListener{
    public static View search;
    private Spinner typeSpinner, classifySpinner;//,reasonSpinner;
    public static Spinner reasonSpinner;
    public static List<String> data_list;
    public static ArrayAdapter<String> arr_adapter;
    private String projectNameString ,billingTimeString;
    private static String classifySpinnerString;
    public static ProjectManagementAdapter projectManagementAdapter;
    public static XListView mListView, projectLv;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> items = new ArrayList<>();
    private Handler mHandler;
    private int start = 0;
    private static int refreshCnt = 0;
    public static int page = 1;
    private AlertDialog dlg,dlgMenu;
    public static boolean SearchBoolean = false;
    public static Context context;
    public static ProgressDialog progressDialog = null;//加载数据显示进度条
    static FreshenBroadcastReceiver broadcast;
    private GridView gridView;
    public static boolean deleteSuccess = false;
    public static Activity activity;
    private EditText searchTime,projectName;
    private Calendar calendar;
    private int currentYear, currentMon, currentDate;
    private HashMap<String,String> param;
    private static ListView lvs;
    private static TimeBackAdpter timeBackAdpter;
    public static Boolean isProject = false;
    private long exitTime = 0 ,projectLvExitTime = 0;
    private InputMethodManager imm;//键盘处理

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("项目维护");
        /*Window window=this.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        setContentView(R.layout.activity_project_management);

        //添加返回按钮
        ToolUtils.backButton(this);
        context = getApplicationContext();
        activity = ProjectManagementActivity.this;
        initView();
        calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMon = calendar.get(Calendar.MONTH) + 1;
        currentDate = calendar.get(Calendar.DAY_OF_MONTH);
        //searchTime.setText("          ");
        searchTime.setOnClickListener(this);
        searchTime.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                searchTime.setCursorVisible(false);
                searchTime.setText("");
                billingTimeString = "全部";
                Drawable nav_up=getResources().getDrawable(R.drawable.date2);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                searchTime.setCompoundDrawables(null, null, nav_up, null);
                return true;
            }
        });
        //空查询
        page = 1;//显示页数
        //刚进入页面就要显示数据
        progressDialog = ProgressDialog.show(activity, "请稍等...", "获取数据中...", true);//显示进度条
        param = new HashMap<>();
        param.put("page", Integer.toString(page));
        param.put("rows", "50");
        HttpBasePost.postHttp(Statics.ProjectGetWXLoadProjectPageDataUrl, param, HttpTypeConstants.ProjectGetWXLoadProjectPageDataUrlType);
        projectLv.setPullLoadEnable(true);
        projectManagementAdapter = new ProjectManagementAdapter(activity);
        projectLv.setAdapter(projectManagementAdapter);
        projectLv.setXListViewListener(this);
        projectLv.setDivider(new ColorDrawable(Color.BLUE));
        projectLv.setDividerHeight(1);
        mHandler = new Handler();
        projectLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {//点击时查看详细信息
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                projectLvExitTime = ToolUtils.muchClick(projectLvExitTime);
                if(projectLvExitTime!=0) {
                    projectLvExitTime = System.currentTimeMillis();
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    LayoutInflater inflater = getLayoutInflater();
                    final View layout = inflater.inflate(R.layout.projectmanager_dialog_backtime_item, null);//获取自定义布局
                    lvs = (ListView) layout.findViewById(R.id.lvs);
                    ProjectAllPageData.RowsBean projectAllPageDate = Statics.projectAllPageDataArrayList.get(0).getRows().get(position - 1);
                    //查询项目周期情况
                    param = new HashMap<>();
                    param.put("id", projectAllPageDate.getId());//projectid
                    isProject = true;
                    HttpBasePost.postHttp(Statics.ProjectGetWXProjectCycleUrl, param, HttpTypeConstants.ProjectGetWXProjectCycleUrlType);
                    timeBackAdpter = new TimeBackAdpter(ProjectManagementActivity.activity, Statics.projectCycleDataList);//回款时间
                    lvs.setAdapter(timeBackAdpter);
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
    private void initView() {
        search = findViewById(R.id.search);
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        classifySpinner = (Spinner) findViewById(R.id.classifySpinner);
        reasonSpinner = (Spinner) findViewById(R.id.reasonSpinner);
        projectLv = (XListView) findViewById(R.id.xListView);
        searchTime = (EditText) findViewById(R.id.searchTime);
        projectName = (EditText) findViewById(R.id.project_name);
        search.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.searchTime:
                exitTime = ToolUtils.muchClick(exitTime);
                if(exitTime!=0) {
                    exitTime = System.currentTimeMillis();
                    //隐藏键盘
                    imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(searchTime.getWindowToken(), 0); //强制隐藏键盘
                    searchTime();
                }
                break;
            case R.id.search:
                exitTime = ToolUtils.muchClick(exitTime);
                if(exitTime!=0) {
                    exitTime = System.currentTimeMillis();
                    //条件查询
                    page = 1;
                    SearchBoolean = true;
                    billingTimeString = searchTime.getText().toString().trim();
                    projectNameString = projectName.getText().toString().trim();
                    Log.d("ProjectManagementActivi", "search:" + projectNameString + "?" + billingTimeString);
                    progressDialog = ProgressDialog.show(activity, "请稍等...", "获取数据中...", true);//显示进度条
                    param = new HashMap<>();
                    param.put("projectName", projectNameString);
                    param.put("startTime", billingTimeString);
                    param.put("page", Integer.toString(page));
                    param.put("rows", "50");
                    HttpBasePost.postHttp(Statics.ProjectGetWXLoadProjectPageDataUrl, param, HttpTypeConstants.ProjectGetWXLoadProjectPageDataUrlType);
                }
                break;
        }
    }
    private void searchTime() {
        //日期选择器
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(activity,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month++;
                        searchTime.setText(String.format("%d-%02d-%02d", year, month, dayOfMonth));
                        billingTimeString = String.format("%d-%02d-%02d", year, month, dayOfMonth);
                        searchTime.setCompoundDrawables(null, null, null, null);
                    }
                },
                mYear, mMonth, mDay).show();
    }
    @Override
    public void onRefresh() {//刷新
        page = 1;
        SearchBoolean = true;
        billingTimeString = searchTime.getText().toString().trim();
        projectNameString = projectName.getText().toString().trim();
        param = new HashMap<>();
        param.put("projectName",projectNameString);
        param.put("startTime",billingTimeString);
        param.put("page", Integer.toString(page));
        param.put("rows", "50");
        Log.d("timeeee","时间："+billingTimeString);
        HttpBasePost.postHttp(Statics.ProjectGetWXLoadProjectPageDataUrl, param, HttpTypeConstants.ProjectGetWXLoadProjectPageDataUrlType);
        onLoad();
    }
    @Override
    public void onLoadMore() {//加载更多
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                if (page >= Statics.page) {
                    page = Statics.page;
                    Toast.makeText(activity,"已经是最后一页了",Toast.LENGTH_SHORT).show();
                }
                //大于总页数，不向下翻页
                billingTimeString = searchTime.getText().toString().trim();
                projectNameString = projectName.getText().toString().trim();
                param = new HashMap<>();
                param.put("projectName",projectNameString);
                param.put("startTime",billingTimeString);
                param.put("page", Integer.toString(page));
                param.put("rows", "50");
                HttpBasePost.postHttp(Statics.ProjectGetWXLoadProjectPageDataUrl, param, HttpTypeConstants.ProjectGetWXLoadProjectPageDataUrlType);
                onLoad();

            }
        }, 2000);
    }
    private void onLoad() {
        projectLv.stopRefresh();
        projectLv.stopLoadMore();
        projectLv.setRefreshTime("刚刚");
    }
    public static void AdapterRefresh(String type) {//刷新adapter
        switch (type) {
            case "projectAdapter":
                projectManagementAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
                break;
            case "projectTimeBackAdapter":
                timeBackAdpter.notifyDataSetChanged();
                ToolUtils.setListViewHeightBasedOnChildren(lvs,10);
                break;
        }
    }
}
