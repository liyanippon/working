package ui.activity;
import android.app.Activity;
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
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.admin.erp.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Tool.ACache;
import Tool.ToolUtils;
import Tool.crash.BaseActivity;
import Tool.statistics.AchacheConstant;
import Tool.statistics.Statics;
import http.ExpressBillingManagementHttpPost;
import http.HttpBasePost;
import http.HttpTypeConstants;
import model.javabean.ExpressManagement;
import model.javabean.ProjectAllPageData;
import model.javabean.ResourceGetWXWXPageDataResource;
import ui.adpter.OutResouceProjectAdpter;
import ui.adpter.SourceManagementAdapter;
import ui.adpter.TimeBackAdpter;
import ui.xlistview.XListView;
public class ResourceManagementActivity extends BaseActivity implements XListView.IXListViewListener {
    public static View search, add, addPerson, addPersonProject;
    private Spinner typeSpinner;
    public static List<String> data_list;
    public static ArrayAdapter<String> arr_adapter;
    private String typeSpinnerString;
    public static SourceManagementAdapter sourceManagementAdapter;
    public static XListView mListView, resourceLv;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> items = new ArrayList<>();
    private Handler mHandler;
    public static int page = 1;
    private ExpressBillingManagementHttpPost httpPost;
    public static Context context;
    public static ProgressDialog progressDialog = null;//加载数据显示进度条
    public static Activity activity;
    private HashMap<String,String> param;
    private static ListView lvs;
    private AlertDialog dlg;
    private static OutResouceProjectAdpter outResourceProjectAdpter;
    public EditText staffName;
    private String staffNameString = "";
    private long exitTime = 0, resourceLvTime = 0;
    ACache aCache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("信息维护");
        setContentView(R.layout.activity_resource_management);

        //清空缓存文件
        File dirFile = new File(SourceManagementAdapter.pathStr);
        SourceManagementAdapter.deleteDirWihtFile(dirFile);//删除所有文件
        //添加返回按钮
        ToolUtils.backButton(this);
        context = getApplicationContext();
        activity = ResourceManagementActivity.this;
        init();
        //空查询
        page = 1;//显示页数
        httpPost = new ExpressBillingManagementHttpPost();
        //String httpUrl = Statics.FinancialBillingManagementSearchUrl;
        //刚进入页面就要显示数据
        progressDialog = ProgressDialog.show(activity, "请稍等...", "获取数据中...", true);//显示进度条
        param = new HashMap<>();
        param.put("page", Integer.toString(page));
        param.put("rows", "50");
        //Log.d("ResourceManagementActiv", "地址"+Statics.ResourceGetWXWXPageDataResourceUrl);
        HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.RESOURCE_GETWXWXP_PAGEDATA_RESOURCE_URL),param,HttpTypeConstants.ResourceGetWXWXPageDataResourceUrlType);
        resourceLv.setPullLoadEnable(true);
        sourceManagementAdapter = new SourceManagementAdapter(activity);
        resourceLv.setAdapter(sourceManagementAdapter);
        resourceLv.setXListViewListener(this);
        resourceLv.setDivider(new ColorDrawable(Color.BLUE));
        resourceLv.setDividerHeight(1);
        mHandler = new Handler();
        resourceLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {//点击时查看详细信息
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                resourceLvTime = ToolUtils.muchClick(resourceLvTime);
                if(resourceLvTime!=0) {
                    resourceLvTime = System.currentTimeMillis();
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    LayoutInflater inflater = getLayoutInflater();
                    final View layout = inflater.inflate(R.layout.outprojectmanager_dialog_time_item, null);//获取自定义布局
                    lvs = (ListView) layout.findViewById(R.id.lvs);
                    ResourceGetWXWXPageDataResource rgwdResource = Statics.rgwDataResourcesList.get(position - 1);
                    //查询项目周期情况
                    param = new HashMap<>();
                    param.put("id", rgwdResource.getId());//projectid
                    param.put("page", Integer.toString(page));
                    param.put("rows", "50");
                    //isProject = true;
                    HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.RESOURCE_GETWXPAGEDATA_RESOURCE_PROJECT_URL), param, HttpTypeConstants.ResourceGetWXPageDataResourceProjectUrlType);
                    outResourceProjectAdpter = new OutResouceProjectAdpter(activity);//项目信息
                    lvs.setAdapter(outResourceProjectAdpter);
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
    private void init() {
        search = findViewById(R.id.search);
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        resourceLv = (XListView) findViewById(R.id.xListView);
        staffName = (EditText) findViewById(R.id.staff_name);
        //add.setOnClickListener(o);
        search.setOnClickListener(o);
        aCache = ACache.get(ResourceManagementActivity.this);
    }
    View.OnClickListener o = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.search:
                    exitTime = ToolUtils.muchClick(exitTime);
                    if(exitTime!=0) {
                        exitTime = System.currentTimeMillis();
                        staffNameString = staffName.getText().toString().trim();
                        param = new HashMap<>();
                        param.put("name", staffNameString);
                        param.put("page", Integer.toString(page));
                        param.put("rows", "50");
                        HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.RESOURCE_GETWXWXP_PAGEDATA_RESOURCE_URL), param, HttpTypeConstants.ResourceGetWXWXPageDataResourceUrlType);
                    }
                    break;
            }
        }
    };
    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                Log.d("ResourceManagementActiv", "刷新" + staffNameString + "%%" + page);
                //清除缓存
                //清空缓存文件
                File dirFile = new File(SourceManagementAdapter.pathStr);
                SourceManagementAdapter.deleteDirWihtFile(dirFile);//删除所有文件
                param = new HashMap<>();
                param.put("name",staffNameString);
                param.put("page", Integer.toString(1));
                param.put("rows", "50");
                HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.RESOURCE_GETWXWXP_PAGEDATA_RESOURCE_URL),param,HttpTypeConstants.ResourceGetWXWXPageDataResourceUrlType);
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
                if (page > Statics.page) {
                    page = Statics.page;
                    //大于总页数，不向下翻页
                    Toast.makeText(activity,"已经是最后一页了",Toast.LENGTH_SHORT).show();
                }else if(page < Statics.page){
                    Statics.isPageUpload = true;
                    param = new HashMap<>();
                    param.put("name",staffNameString);
                    param.put("page", Integer.toString(page));
                    param.put("rows", "50");
                    HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.RESOURCE_GETWXWXP_PAGEDATA_RESOURCE_URL),param,HttpTypeConstants.ResourceGetWXWXPageDataResourceUrlType);
                }

                onLoad();
            }
        }, 2000);
    }
    @Override
    protected void onResume() {
        super.onResume();

        param = new HashMap<>();
        param.put("name",staffNameString);
        param.put("page", Integer.toString(page));
        param.put("rows", "50");
        HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.RESOURCE_GETWXWXP_PAGEDATA_RESOURCE_URL),param,HttpTypeConstants.ResourceGetWXWXPageDataResourceUrlType);
    }
    private void onLoad() {
        resourceLv.stopRefresh();
        resourceLv.stopLoadMore();
        resourceLv.setRefreshTime("刚刚");
    }
    public static void AdapterRefresh(String type) {
        switch (type) {
            case "resourceManagementAdapter":
                if (sourceManagementAdapter != null){
                    sourceManagementAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                }
                break;
            case "outResourceProjectAdpter":
                if(outResourceProjectAdpter != null){
                    Log.d("ResourceManagementActiv", "刷新");
                    outResourceProjectAdpter.notifyDataSetChanged();
                }
                break;
        }
    }
}
