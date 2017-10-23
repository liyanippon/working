package ui.adpter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.erp.R;

import java.util.HashMap;
import java.util.List;

import Tool.ToolUtils;
import Tool.statistics.Statics;
import http.HttpBasePost;
import http.HttpTypeConstants;
import model.javabean.ProjectCycleData;
import model.javabean.ResourceGetWXPageDataResourceProject;
import model.javabean.ResourceGetWXWXPageDataResource;
import ui.activity.ResourceManagementActivity;

/**
 * Created by admin on 2017/8/14.
 */

public class OutResouceProjectAdpter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private AlertDialog dlg;
    private HashMap<String,String> param;
    private static OutResouceProjecInformationtAdpter outResouceProjecInformationtAdpter;
    private static ListView lvs;
    private String projectNameString;
    public static boolean isOutResouceProjectAdpter = false;
    public OutResouceProjectAdpter(Activity activityBilling) {
        inflater = LayoutInflater.from(activityBilling);
        this.activity = activityBilling;
    }
    @Override
    public int getCount() {
        if(Statics.rgwDataResourceProjectList==null||Statics.rgwDataResourceProjectList.size()==0||Statics.rgwDataResourceProjectList.get(0).getRows().size()==0){
            return 0;
        }else {
            return Statics.rgwDataResourceProjectList.size();
        }
    }
    @Override
    public Object getItem(int position) {
        return Statics.rgwDataResourceProjectList.get(0).getRows().size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(this.activity).inflate(R.layout.time_resource_item_layout, null);
            holder.number = (TextView) convertView.findViewById(R.id.number);
            holder.projectName = (TextView) convertView.findViewById(R.id.project_name);
            holder.inPeriod = (TextView) convertView.findViewById(R.id.in_peroid);
            holder.outPeroid = (TextView) convertView.findViewById(R.id.out_peroid);
            holder.details = (TextView) convertView.findViewById(R.id.details);
            holder.information = (TextView) convertView.findViewById(R.id.information);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ResourceGetWXPageDataResourceProject.RowsBean rgwdrp = Statics.rgwDataResourceProjectList.get(0).getRows().get(position);
        holder.projectName.setText(rgwdrp.getProjectId());
        holder.inPeriod.setText(ToolUtils.getYearMonthDate(rgwdrp.getJoinTime()));
        holder.outPeroid.setText(ToolUtils.getYearMonthDate(rgwdrp.getLeaveTime()));
        holder.details.setText(rgwdrp.getDescription());
        position++;
        holder.number.setText(position+"");
        holder.information.setOnClickListener(o);
        projectNameString = rgwdrp.getProjectId();
        return convertView;
    }
    class ViewHolder {
        TextView number,projectName, inPeriod, outPeroid, details, information;
    }
    View.OnClickListener o =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            LayoutInflater inflater = ResourceManagementActivity.activity.getLayoutInflater();
            final View layout = inflater.inflate(R.layout.outprojectmanager_information_item, null);//获取自定义布局
            lvs = (ListView) layout.findViewById(R.id.lvs);
            //查询项目周期情况
            int page = 1;
            param = new HashMap<>();
            param.put("projectName",projectNameString);//应该有一个上传人员id
            param.put("page", Integer.toString(page));
            param.put("rows", "1000");
            //isProject = true;
            isOutResouceProjectAdpter = true;
            HttpBasePost.postHttp(Statics.ResourceGetWXExteriorProjectsUrl, param, HttpTypeConstants.ResourceGetWXExteriorProjectsUrlType);
            outResouceProjecInformationtAdpter = new OutResouceProjecInformationtAdpter(activity);//项目信息
            lvs.setAdapter(outResouceProjecInformationtAdpter);
            //创建人就是用户名
            builder.setView(layout);
            dlg = builder.create();
            dlg.show();
            //dlg.getWindow().setLayout(1500, 1500);
            WindowManager windowManager = ResourceManagementActivity.activity.getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            WindowManager.LayoutParams lp = dlg.getWindow().getAttributes();
            lp.width = (int) (display.getWidth()); //设置宽度
            dlg.getWindow().setAttributes(lp);
        }
    };
    public static void AdapterRefresh(String type) {//刷新adapter
        switch (type) {
            case "projectAdapter":
                outResouceProjecInformationtAdpter.notifyDataSetChanged();
                break;
        }
    }
}
