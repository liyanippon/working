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

import Tool.ToolUtils;
import Tool.statistics.Statics;
import http.HttpBasePost;
import http.HttpTypeConstants;
import model.javabean.ResourceGetWXExteriorProjects;
import model.javabean.ResourceGetWXPageDataResourceProject;
import model.javabean.ResourceGetWXWXPageDataResource;
import ui.activity.ResourceManagementActivity;

/**
 * Created by admin on 2017/8/14.
 */

public class OutResouceProjecInformationtAdpter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private LayoutInflater inflater;
    public OutResouceProjecInformationtAdpter(Activity activityBilling) {
        inflater = LayoutInflater.from(activityBilling);
        this.activity = activityBilling;
    }
    @Override
    public int getCount() {
        if(Statics.resourceGetWXExteriorProjectsList==null||Statics.resourceGetWXExteriorProjectsList.size()==0||Statics.resourceGetWXExteriorProjectsList.get(0).getRows().size()==0){
            return 0;
        }else {
            return Statics.resourceGetWXExteriorProjectsList.get(0).getRows().size();
        }
    }
    @Override
    public Object getItem(int position) {
        return Statics.resourceGetWXExteriorProjectsList.get(0).getRows().size();
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
            convertView = LayoutInflater.from(this.activity).inflate(R.layout.time_resource_outproject_information_item_layout, null);
            holder.number = (TextView) convertView.findViewById(R.id.number);
            holder.customerName = (TextView) convertView.findViewById(R.id.customer_name);
            holder.projectName = (TextView) convertView.findViewById(R.id.project_name);
            holder.inPeriod = (TextView) convertView.findViewById(R.id.in_peroid);
            holder.outPeroid = (TextView) convertView.findViewById(R.id.out_peroid);
            holder.projectNum = (TextView) convertView.findViewById(R.id.project_num);
            holder.information = (TextView) convertView.findViewById(R.id.information);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ResourceGetWXExteriorProjects.RowsBean rgwdrp = Statics.resourceGetWXExteriorProjectsList.get(0).getRows().get(position);
        holder.customerName.setText(rgwdrp.getCustomerName());
        holder.projectName.setText(rgwdrp.getProjectName());
        holder.inPeriod.setText(ToolUtils.getYearMonthDate(rgwdrp.getStartTime()));
        holder.outPeroid.setText(ToolUtils.getYearMonthDate(rgwdrp.getEndTime()));
        holder.projectNum.setText(rgwdrp.getPeopleNumber()+"");
        holder.information.setText(rgwdrp.getDescription());
        position++;
        holder.number.setText(position+"");
        return convertView;
    }
    class ViewHolder {
        TextView number,customerName,projectName, inPeriod, outPeroid, projectNum, information;
    }
}
