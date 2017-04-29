package ui.adpter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.erp.R;

import java.util.List;

import model.AttendanceStatistics;
import model.CustomerBillingStatistics;

/**
 * Created by admin on 2017/3/3.
 */

public class AttendanceStatisticsAdapter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private List<AttendanceStatistics> list;
    private LayoutInflater inflater;
    public AttendanceStatisticsAdapter(Activity activity, List<AttendanceStatistics> list) {
        inflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.list = list;
    }


    @Override
    public int getCount() {
        int ret = 0;
        if (list != null) {
            ret = list.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AttendanceStatistics attendanceStatistics = (AttendanceStatistics) this.getItem(position);
        AttendanceStatisticsAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new AttendanceStatisticsAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.attendance_statics_stafflist_layout, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.attendance_person);//姓名
            viewHolder.year = (TextView) convertView.findViewById(R.id.attendance_year);//年份
            //viewHolder.month = (TextView) convertView.findViewById(R.id.attendance_month);//月份
            viewHolder.normalTime = (TextView) convertView.findViewById(R.id.normal_time);//正常工时
            viewHolder.overTime = (TextView) convertView.findViewById(R.id.general_over_time);//普通加班
            viewHolder.festivalTime = (TextView) convertView.findViewById(R.id.festival_over_time);//节日加班
            viewHolder.askLeaveTime = (TextView) convertView.findViewById(R.id.ask_leave_time);//请假合计
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (AttendanceStatisticsAdapter.ViewHolder) convertView.getTag();
        }
        /*viewHolder.id.setText(Integer.toString(++position));
        viewHolder.id.setTextSize(13);*/
        viewHolder.name.setText(attendanceStatistics.getUserName());
        viewHolder.name.setTextSize(13);
        viewHolder.year.setText(attendanceStatistics.getTAttendanceSumYear()+"-"+attendanceStatistics.getTAttendanceSumMonth());
        viewHolder.year.setTextSize(13);
        /*viewHolder.month.setText(attendanceStatistics.getTAttendanceSumMonth());
        viewHolder.month.setTextSize(13);*/
        viewHolder.normalTime.setText((int)attendanceStatistics.getNormalHourSum() + "");
        viewHolder.normalTime.setTextSize(13);
        viewHolder.overTime.setText((int)attendanceStatistics.getOvertimeWorkingHours() + "");
        viewHolder.overTime.setTextSize(13);
        viewHolder.festivalTime.setText((int)attendanceStatistics.getRegularOvertimeHoursSum() + "");
        viewHolder.festivalTime.setTextSize(13);
        viewHolder.askLeaveTime.setText(attendanceStatistics.getOffworkHoursSum() + "");
        viewHolder.askLeaveTime.setTextSize(13);
        return convertView;
    }

    public static class ViewHolder {
        public TextView name;
        public TextView year;
        //public TextView month;
        public TextView normalTime;
        public TextView overTime;
        public TextView festivalTime;
        public TextView askLeaveTime;
    }
}
