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

import model.TimeExpressStatistics;

/**
 * Created by admin on 2017/3/1.
 */

public class TimeExpressStatisticsAdapter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private List<TimeExpressStatistics> list;
    private LayoutInflater inflater;

    public TimeExpressStatisticsAdapter(Activity activityBilling, List<TimeExpressStatistics> list) {
        Log.v("test2", "TimeBillingStatisticsAdapter");
        inflater = LayoutInflater.from(activityBilling);
        this.activity = activityBilling;
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
        TimeExpressStatistics timeExpressStatistics = (TimeExpressStatistics) this.getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();

            convertView = inflater.inflate(R.layout.express_statics_yearlist_layout, null);
            viewHolder.id = (TextView) convertView.findViewById(R.id.text_id);//序号
            viewHolder.month = (TextView) convertView.findViewById(R.id.month);//月份
            viewHolder.count = (TextView) convertView.findViewById(R.id.count);//数量
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        /*viewHolder.id.setText(Integer.toString(++position));
        viewHolder.id.setTextSize(13);*/
        viewHolder.month.setText(timeExpressStatistics.getMonth());
        viewHolder.month.setTextSize(13);
        viewHolder.count.setText(timeExpressStatistics.getSum());
        viewHolder.count.setTextSize(13);
        return convertView;
    }

    public static class ViewHolder {
        public TextView id;
        public TextView month;
        public TextView count;

    }

}
