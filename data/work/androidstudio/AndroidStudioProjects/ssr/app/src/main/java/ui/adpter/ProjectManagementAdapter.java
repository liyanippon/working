package ui.adpter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.erp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Tool.ACache;
import Tool.ToolUtils;
import Tool.statistics.AchacheConstant;
import Tool.statistics.Statics;
import http.ExpressBillingManagementHttpPost;
import http.HttpBasePost;
import http.HttpTypeConstants;
import model.javabean.ExpressManagement;
import model.javabean.ProjectAllPageData;
import ui.activity.ExpressBillingManagementActivity;
import ui.activity.ProjectManagementActivity;

/**
 * Created by admin on 2017/2/23.
 */

public class ProjectManagementAdapter extends BaseAdapter {
    private Activity activity;
    private android.support.v7.app.AlertDialog dlg,dlgMenu;
    private String id;
    private ExpressBillingManagementHttpPost httpPost;
    private int positions;
    private ViewHolder vh;
    private List<ViewHolder> holders = new ArrayList<ViewHolder>();
    private HashMap<String,String> param ;
    private static TimeBackAdpter timeBackAdpter;
    private static ListView lvs;
    private static ListView lv;
    private static MemberDetailsAdpter memberDetailsAdpter;
    private long exitTime = 0;
    ACache aCache ;
    public ProjectManagementAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        if (Statics.projectAllPageDataArrayList.size()!=0) {
            return Statics.projectAllPageDataArrayList.get(0).getRows().size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        positions = position;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.projectmanagerlist, null);
            //找控件
            vh.number = (TextView) convertView.findViewById(R.id.number);
            vh.projectName = (TextView) convertView.findViewById(R.id.project_name);
            vh.projectManager = (TextView) convertView.findViewById(R.id.project_manager);
            vh.startTime = (TextView) convertView.findViewById(R.id.start_time);
            vh.states = (TextView) convertView.findViewById(R.id.states);
            //vh.del = (TextView) convertView.findViewById(R.id.del);
            vh.details = (TextView) convertView.findViewById(R.id.details);
            convertView.setTag(vh);
            holders.add(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        //获取数据和显示数据
        String number = Integer.toString((ProjectManagementActivity.page-1)*50 + position+1);
        ProjectAllPageData.RowsBean projectAllPageData = Statics.projectAllPageDataArrayList.get(0).getRows().get(position);
        vh.number.setText(number);
        vh.number.setGravity(Gravity.CENTER);
        vh.projectName.setText(projectAllPageData.getProjectName());
        vh.projectName.setGravity(Gravity.CENTER);
        vh.projectManager.setText(projectAllPageData.getProjectManager());
        vh.projectManager.setGravity(Gravity.CENTER);
        vh.startTime.setText(ToolUtils.getYearMonthDate(projectAllPageData.getStartTime()));
        vh.startTime.setGravity(Gravity.CENTER);
        String state = "";
        if(projectAllPageData.getStu().equals("017001")){
            state = "项目进行中";
        }else if(projectAllPageData.getStu().equals("017002")){
            state = "项目已结束";
        }
        vh.states.setText(state);
        vh.states.setGravity(Gravity.CENTER);
        //vh.del.setOnClickListener(new Click(positions));//解决光标获取不对的问题
        vh.details.setOnClickListener(new Click(positions));
        return convertView;
    }
    private void showNormalDelDialog(final int item) {
            /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        id = Statics.expressManagementList.get(0).getData().get(0).getRows().get(item).getId();
        AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(activity);
        normalDialog.setIcon(R.drawable.delete);
        normalDialog.setTitle("删除记录");
        normalDialog.setMessage("是否删除该记录?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        ExpressBillingManagementActivity.deleteSuccess = true;
                        ExpressManagement.DataBean.RowsBean edrb=Statics.expressManagementList.get(0).getData().get(0).getRows().get(item);
                        String sum = edrb.getSum().toString();
                        String classify = edrb.getClassify();
                        String paymentMethod = edrb.getPaymentMethod();
                        Log.d("ExpressManagementAdapte", "jin" + sum + "*" + classify + "*" + paymentMethod);
                        if (!"进账".equals(edrb.getClassify())){
                             sum = edrb.getSum().abs().toString();
                             classify = edrb.getClassify().split("<b>")[1].split("</b>")[0];
                             paymentMethod = edrb.getPaymentMethod().split("<b>")[1].split("</b>")[0];
                            Log.d("ExpressManagementAdapte", "chuzhag"+sum+";"+classify+";"+paymentMethod);
                        }
                        Statics.isDelete = true;
                        aCache = ACache.get(activity);
                        if ("success".equals(httpPost.delAccountManagerHttp(aCache.getAsString(AchacheConstant.FINANCIAL_BILLINGMANAGEMENT_SEARCH_URL), id, sum
                                ,classify,paymentMethod,activity))) {
                            ExpressBillingManagementActivity.progressDialog = ProgressDialog.show(activity, "请稍等...", "获取数据中...", true);//显示进度条
                        } else {
                            Toast.makeText(activity, "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do

                    }
                });
        // 显示
        normalDialog.show();

    }

    public class Click implements View.OnClickListener {
        int item= 0;

        public Click(int position) {
            item= position;
        }

        @Override
        public void onClick(View v) {
            httpPost = new ExpressBillingManagementHttpPost();
           /* if(v.getId() == R.id.del){
                showNormalDelDialog(item);//删除对话框
            }else*/ if(v.getId() == R.id.details){
                exitTime = ToolUtils.muchClick(exitTime);
                if(exitTime!=0) {
                    exitTime = System.currentTimeMillis();
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);
                    LayoutInflater inflater = ProjectManagementActivity.activity.getLayoutInflater();
                    final View layout = inflater.inflate(R.layout.projectmanager_dialog_detailed_item, null);//获取自定义布局
                    lvs = (ListView) layout.findViewById(R.id.lvs);
                    lv = (ListView) layout.findViewById(R.id.lv);
                    ProjectAllPageData.RowsBean projectAllPageDate = Statics.projectAllPageDataArrayList.get(0).getRows().get(item);
                    //查询项目周期情况
                    param = new HashMap<>();
                    param.put("id", projectAllPageDate.getId());//projectid
                    HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.PROJECT_GETWXPROJECT_CYCLE_URL), param, HttpTypeConstants.ProjectGetWXProjectCycleUrlType);
                    timeBackAdpter = new TimeBackAdpter(ProjectManagementActivity.activity, Statics.projectCycleDataList);//回款时间
                    lvs.setAdapter(timeBackAdpter);
                    //成员情况
                    param = new HashMap<>();
                    param.put("projectId", projectAllPageDate.getId());
                    HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.PROJECT_GETWXLOAD_PROJECT_PEOPLE_PAGE_DATA_URL), param, HttpTypeConstants.ProjectGetWXLoadProjectPeoplePageDataType);
                    memberDetailsAdpter = new MemberDetailsAdpter(ProjectManagementActivity.activity, Statics.projectPeoplePageDataList);
                    lv.setAdapter(memberDetailsAdpter);
                    TextView projectName = (TextView) layout.findViewById(R.id.project_name);//项目名称
                    TextView projectManager = (TextView) layout.findViewById(R.id.project_manager);//项目经理
                    TextView yjStartTime = (TextView) layout.findViewById(R.id.yjstart_time);//预计开始时间
                    TextView sjStarttime = (TextView) layout.findViewById(R.id.sjstart_time);//实际开始时间
                    TextView jEndTime = (TextView) layout.findViewById(R.id.jend_time);//结束时间
                    TextView period = (TextView) layout.findViewById(R.id.period);//收款周期
                    TextView money = (TextView) layout.findViewById(R.id.money);//金额
                    TextView states = (TextView) layout.findViewById(R.id.states);//合同状态
                    TextView description = (TextView) layout.findViewById(R.id.project_description);//项目描述
                    projectName.setText(projectAllPageDate.getProjectName());
                    projectManager.setText(projectAllPageDate.getProjectManager());
                    yjStartTime.setText(ToolUtils.getYearMonthDate(projectAllPageDate.getYjstartTime()));
                    sjStarttime.setText(ToolUtils.getYearMonthDate(projectAllPageDate.getStartTime()));
                    jEndTime.setText(ToolUtils.getYearMonthDate(projectAllPageDate.getEndTime()));
                    if (projectAllPageDate.getSum() > 0) {
                        money.setText(Double.toString(projectAllPageDate.getSum()));
                    } else {
                        money.setText("");
                    }
                    period.setText(projectAllPageDate.getProjectCycle());
                    description.setText(projectAllPageDate.getMome());
                    String state = "";
                    if (projectAllPageDate.getStu().equals("017001")) {
                        state = "项目进行中";
                    } else if (projectAllPageDate.getStu().equals("017002")) {
                        state = "项目已结束";
                    }
                    states.setText(state);
                    //创建人就是用户名
                    builder.setView(layout);
                    dlg = builder.create();
                    dlg.show();
                    //dlg.getWindow().setLayout(1500, 1500);
                    WindowManager windowManager = ProjectManagementActivity.activity.getWindowManager();
                    Display display = windowManager.getDefaultDisplay();
                    WindowManager.LayoutParams lp = dlg.getWindow().getAttributes();
                    lp.width = (int) (display.getWidth()); //设置宽度
                    dlg.getWindow().setAttributes(lp);
                }
            }
        }
    }
    class ViewHolder {
        TextView number, projectName, projectManager,  startTime, states, details, del;
    }

    public static void AdapterRefresh(String type) {//刷新adapter
        switch (type) {
            case "projectTimeBackAdapter":
                timeBackAdpter.notifyDataSetChanged();
                ToolUtils.setListViewHeightBasedOnChildren(lvs,10);
                break;
            case "projectPeopleAdapter":
                memberDetailsAdpter.notifyDataSetChanged();
                ToolUtils.setListViewHeightBasedOnChildren(lv,11);
                break;
        }
    }
}
