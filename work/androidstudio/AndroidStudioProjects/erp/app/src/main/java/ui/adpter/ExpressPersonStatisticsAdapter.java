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

import model.ExpressPersonStatistic;

/**
 * Created by admin on 2017/3/3.
 */

public class ExpressPersonStatisticsAdapter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private List<ExpressPersonStatistic> list;
    private LayoutInflater inflater;

    public ExpressPersonStatisticsAdapter(Activity activityBilling, List<ExpressPersonStatistic> list) {
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
        Log.v("test", "getView");
        ExpressPersonStatistic expressPersonStatistic = (ExpressPersonStatistic) this.getItem(position);
        ExpressPersonStatisticsAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ExpressPersonStatisticsAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.express_statics_expresspersonlist_layout, null);
            viewHolder.id = (TextView) convertView.findViewById(R.id.text_id);//序号
            viewHolder.month = (TextView) convertView.findViewById(R.id.month);//月份
            viewHolder.expressPerson = (TextView) convertView.findViewById(R.id.expressPerson);//快递员
            viewHolder.count = (TextView) convertView.findViewById(R.id.count);//数量

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ExpressPersonStatisticsAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.id.setText(Integer.toString(++position));
        viewHolder.id.setTextSize(13);
        viewHolder.month.setText(expressPersonStatistic.getMonth());
        viewHolder.month.setTextSize(13);
        viewHolder.expressPerson.setText(expressPersonStatistic.getName());
        viewHolder.expressPerson.setTextSize(13);
        viewHolder.count.setText(expressPersonStatistic.getSum());
        viewHolder.count.setTextSize(13);
        Log.v("test", "post");
        notifyDataSetChanged();
        return convertView;
    }

    public static class ViewHolder {
        public TextView id;
        public TextView month;
        public TextView expressPerson;
        public TextView count;
    }
}
