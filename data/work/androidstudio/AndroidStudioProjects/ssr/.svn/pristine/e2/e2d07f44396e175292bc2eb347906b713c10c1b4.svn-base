package ui.adpter;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.admin.erp.R;

import java.util.ArrayList;
import java.util.List;

import Tool.statistics.Statics;
import model.javabean.AttendanceStaffBelongProject;

/**
 * Created by admin on 2017/6/28.
 */

public class AttendanceBelongProjectStatisticsAdapter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private List<AttendanceStaffBelongProject> list;
    private LayoutInflater inflater;
    public AttendanceBelongProjectStatisticsAdapter(Activity activityBilling, ArrayList<AttendanceStaffBelongProject> list) {
        inflater = LayoutInflater.from(activityBilling);
        this.activity = activityBilling;
        this.list = Statics.staffBelongProjectArrayList;
    }
    @Override
    public int getCount() {
        int ret = 0;
        if (this.list != null) {
            ret = this.list.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AttendanceBelongProjectStatisticsAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new AttendanceBelongProjectStatisticsAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.attendance_belongproject_list_layout, null);
            viewHolder.id = (TextView) convertView.findViewById(R.id.text_id);
            viewHolder.staffName = (TextView) convertView.findViewById(R.id.staff_name);//员工姓名
            viewHolder.staffProject = (TextView) convertView.findViewById(R.id.staff_project);//项目名
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (AttendanceBelongProjectStatisticsAdapter.ViewHolder) convertView.getTag();
        }
        Log.d("AttendanceBelongProject", "得到"+Statics.staffBelongProjectArrayList.get(position).getProject_user());
        Log.d("AttendanceBelongProject", Statics.staffBelongProjectArrayList.get(position).getProject_name());
        viewHolder.staffName.setText(this.list.get(position).getProject_user());
        viewHolder.staffName.setTextSize(13);
        viewHolder.staffProject.setText(this.list.get(position).getProject_name());
        viewHolder.staffProject.setTextSize(13);
        viewHolder.id.setText(Integer.toString(++position));
        viewHolder.id.setTextSize(13);
        return convertView;
    }
    public static class ViewHolder {
        public TextView id;
        public TextView staffName;
        public TextView staffProject;
    }
}
