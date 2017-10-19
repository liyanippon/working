package ui.adpter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.erp.R;

import java.util.List;

import Tool.ToolUtils;
import model.javabean.ProjectPeoplePageData;

/**
 * Created by admin on 2017/8/14.
 */

public class MemberDetailsAdpter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private List<ProjectPeoplePageData> pppdList;
    private LayoutInflater inflater;
    public MemberDetailsAdpter(Activity activityBilling, List<ProjectPeoplePageData> list) {
        inflater = LayoutInflater.from(activityBilling);
        this.activity = activityBilling;
        this.pppdList = list;
    }
    public void updateView(List<ProjectPeoplePageData> list)
    {
        this.pppdList = list;
        this.notifyDataSetChanged();//强制动态刷新数据进而调用getView方法
    }
    @Override
    public int getCount() {
        if(this.pppdList != null&&this.pppdList.size() != 0){
            return this.pppdList.get(0).getRows().size();
        }else {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(this.activity).inflate(R.layout.member_details_item_layout, null);
            holder.number = (TextView) convertView.findViewById(R.id.number);
            holder.memberName = (TextView) convertView.findViewById(R.id.member_name);
            holder.joinTime = (TextView) convertView.findViewById(R.id.join_time);
            holder.leaveTime = (TextView) convertView.findViewById(R.id.leave_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ProjectPeoplePageData.RowsBean pppdrb = this.pppdList.get(0).getRows().get(position);
        holder.memberName.setText(pppdrb.getProject_user());
        holder.joinTime.setText(ToolUtils.getYearMonthDate(pppdrb.getJoin_time()));
        if(pppdrb.getLeave_time()< pppdrb.getJoin_time()){
            holder.leaveTime.setText("");
        }else{
            holder.leaveTime.setText(ToolUtils.getYearMonthDate(pppdrb.getLeave_time()));
        }
        position++;
        holder.number.setText(position+"");

        return convertView;
    }

    class ViewHolder {
        TextView number, memberName, joinTime, leaveTime;
    }
}
