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
import model.javabean.ProjectCycleData;

/**
 * Created by admin on 2017/8/14.
 */

public class TimeBackAdpter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private List<ProjectCycleData> pcdList;
    private LayoutInflater inflater;
    public TimeBackAdpter(Activity activityBilling, List<ProjectCycleData> list) {
        inflater = LayoutInflater.from(activityBilling);
        this.activity = activityBilling;
        this.pcdList = list;
    }
    public void updateView(List<ProjectCycleData> list)
    {
        this.pcdList = list;
        this.notifyDataSetChanged();//强制动态刷新数据进而调用getView方法
    }
    @Override
    public int getCount() {
        if(this.pcdList==null||this.pcdList.size()==0){
            return 0;
        }else {
            return this.pcdList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return pcdList.get(position);
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
            convertView = LayoutInflater.from(this.activity).inflate(R.layout.time_back_item_layout, null);
            holder.number = (TextView) convertView.findViewById(R.id.number);
            holder.period = (TextView) convertView.findViewById(R.id.period);
            holder.backMoney = (TextView) convertView.findViewById(R.id.back_money);
            holder.details = (TextView) convertView.findViewById(R.id.details);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.period.setText(ToolUtils.getYearMonthDate(this.pcdList.get(position).getCycle_time()));
        holder.backMoney.setText(Double.toString(this.pcdList.get(position).getReturned_money()));
        holder.details.setText(this.pcdList.get(position).getCont());
        position++;
        holder.number.setText(position+"");

        return convertView;
    }

    class ViewHolder {
        TextView number, period, backMoney, details;
    }
}
