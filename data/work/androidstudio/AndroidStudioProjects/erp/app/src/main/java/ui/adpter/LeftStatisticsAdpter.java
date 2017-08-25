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

import java.util.List;

import model.FinancialSalaryStatistics;

/**
 * Created by admin on 2017/8/14.
 */

public class LeftStatisticsAdpter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private List<FinancialSalaryStatistics> fssList;
    private LayoutInflater inflater;
    public LeftStatisticsAdpter(Activity activityBilling, List<FinancialSalaryStatistics> list) {
        inflater = LayoutInflater.from(activityBilling);
        this.activity = activityBilling;
        this.fssList = list;
    }
    public void updateView(List<FinancialSalaryStatistics> list)
    {
        this.fssList = list;
        this.notifyDataSetChanged();//强制动态刷新数据进而调用getView方法
    }
    @Override
    public int getCount() {
        if(this.fssList==null||this.fssList.size()==0){
            return 0;
        }else {
            Log.d("LeftStatisticsAdpter", "fssLsi" + fssList.size());
            return this.fssList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return fssList.get(position);
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
            convertView = LayoutInflater.from(this.activity).inflate(R.layout.item_left, null);
            holder.tvLeft = (TextView) convertView.findViewById(R.id.tv_left);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        position++;
        holder.tvLeft.setText(position+"");

        return convertView;
    }

    class ViewHolder {
        TextView tvLeft;
    }
}
