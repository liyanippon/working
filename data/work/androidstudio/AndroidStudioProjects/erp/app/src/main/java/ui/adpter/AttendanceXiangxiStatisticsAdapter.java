package ui.adpter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.admin.erp.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Tool.ToolUtils;
import model.AttendanceWxDetaSearch;
import model.FinancialBillingGetWXsettlementMonth;

/**
 * Created by admin on 2017/6/5.
 */

public class AttendanceXiangxiStatisticsAdapter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private List<AttendanceWxDetaSearch> list;
    private LayoutInflater inflater;
    public AttendanceXiangxiStatisticsAdapter(Activity activityBilling, ArrayList<AttendanceWxDetaSearch> list) {
        Log.v("test2", "TimeBillingStatisticsAdapter");
        inflater = LayoutInflater.from(activityBilling);
        this.activity = activityBilling;
        this.list = list;
        Log.d("FinancialTimeBillingSta", "adpater" + list.size());
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
        Log.v("test2", "getView");
        AttendanceXiangxiStatisticsAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new AttendanceXiangxiStatisticsAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.attendance_staffxiangxi_list_layout, null);
            viewHolder.id = (TextView) convertView.findViewById(R.id.text_id);
            viewHolder.dateTime = (TextView) convertView.findViewById(R.id.date_time);//考勤日期
            viewHolder.normalTime = (TextView) convertView.findViewById(R.id.normal_time);//正常工时
            viewHolder.overTime = (TextView) convertView.findViewById(R.id.over_time);//普通加班工时
            viewHolder.festivalTime = (TextView) convertView.findViewById(R.id.festival_time);//节假日加班工时
            viewHolder.askLeaveTime = (TextView) convertView.findViewById(R.id.ask_leave_time);//请假工时
            viewHolder.pendingStatus = (TextView) convertView.findViewById(R.id.pendings_tatus);//审核状态
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (AttendanceXiangxiStatisticsAdapter.ViewHolder) convertView.getTag();
        }
        //几号
        long dateCurrent = list.get(position).getAttendanceDate();
        Date date = new Date(dateCurrent);
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String dateStr = sdf.format(date);
        viewHolder.dateTime.setText(list.get(position).getAttendanceYear()+"-"+list.get(position).getAttendanceMonth()+"-"+dateStr);//日期后期做
        viewHolder.dateTime.setTextSize(13);
        viewHolder.normalTime.setText(Double.toString(list.get(position).getNormalHour()));
        viewHolder.normalTime.setTextSize(13);
        viewHolder.overTime.setText(Double.toString(list.get(position).getRegularOvertimeHours()));
        viewHolder.overTime.setTextSize(13);
        viewHolder.festivalTime.setText(Double.toString(list.get(position).getOvertimeWorkingHours()));
        viewHolder.festivalTime.setTextSize(13);
        viewHolder.askLeaveTime.setText(Double.toString(list.get(position).getOffworkhours()));
        viewHolder.askLeaveTime.setTextSize(13);
        viewHolder.pendingStatus.setText(list.get(position).getStatusName());
        viewHolder.pendingStatus.setTextSize(13);
        viewHolder.id.setText(Integer.toString(++position));
        viewHolder.id.setTextSize(13);
        //如果周六周日染红
        if("星期六".equals(ToolUtils.getWeekOfDate(date))||"星期日".equals(ToolUtils.getWeekOfDate(date))){
            viewHolder.dateTime.setTextColor(Color.RED);
            viewHolder.normalTime.setTextColor(Color.RED);
            viewHolder.overTime.setTextColor(Color.RED);
            viewHolder.festivalTime.setTextColor(Color.RED);
            viewHolder.askLeaveTime.setTextColor(Color.RED);
            viewHolder.pendingStatus.setTextColor(Color.RED);
            viewHolder.id.setTextColor(Color.RED);
        }else{
            viewHolder.dateTime.setTextColor(Color.BLACK);
            viewHolder.normalTime.setTextColor(Color.BLACK);
            viewHolder.overTime.setTextColor(Color.BLACK);
            viewHolder.festivalTime.setTextColor(Color.BLACK);
            viewHolder.askLeaveTime.setTextColor(Color.BLACK);
            viewHolder.pendingStatus.setTextColor(Color.BLACK);
            viewHolder.id.setTextColor(Color.BLACK);
        }

        return convertView;
    }
    public static class ViewHolder {
        public TextView id;
        public TextView dateTime;
        public TextView normalTime;
        public TextView overTime;
        public TextView festivalTime;
        public TextView askLeaveTime;
        public TextView pendingStatus;
    }
}
