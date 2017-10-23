package ui.adpter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.erp.R;

import java.util.List;

import model.XiangxiBillingStatistics;

/**
 * Created by admin on 2017/3/8.
 */

public class XiangxiBillingStatisticsAdapter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private List<XiangxiBillingStatistics> list;
    private LayoutInflater inflater;

    public XiangxiBillingStatisticsAdapter(Activity activityBilling, List<XiangxiBillingStatistics> list) {
        Log.v("test", "CustomerBillingStatisticsAdapter" + list.size());
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
        XiangxiBillingStatistics xiangxiBillingStatistics = (XiangxiBillingStatistics) this.getItem(position);
        XiangxiBillingStatisticsAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new XiangxiBillingStatisticsAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.billingstatics_xiangxilist_layout, null);
            viewHolder.id = (TextView) convertView.findViewById(R.id.text_id);//序号
            viewHolder.classifyname = (TextView) convertView.findViewById(R.id.classifyname);
            viewHolder.resonname = (TextView) convertView.findViewById(R.id.resonname);
            viewHolder.type = (TextView) convertView.findViewById(R.id.type);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
            viewHolder.remark = (TextView) convertView.findViewById(R.id.remark);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (XiangxiBillingStatisticsAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.id.setText(Integer.toString(++position));
        viewHolder.id.setTextSize(13);
        viewHolder.classifyname.setText(xiangxiBillingStatistics.getClassifyname());
        Log.v("name","classifyname"+xiangxiBillingStatistics.getClassifyname());
        viewHolder.classifyname.setTextSize(13);
        viewHolder.resonname.setText(xiangxiBillingStatistics.getResonname());
        viewHolder.resonname.setTextSize(13);
        viewHolder.type.setText(xiangxiBillingStatistics.getType());
        viewHolder.type.setTextSize(13);
        viewHolder.date.setText(xiangxiBillingStatistics.getDate());
        viewHolder.date.setTextSize(13);
        viewHolder.price.setText(xiangxiBillingStatistics.getPrice());
        viewHolder.price.setTextSize(13);
        viewHolder.remark.setText(xiangxiBillingStatistics.getRemark());
        viewHolder.remark.setTextSize(13);

        if(xiangxiBillingStatistics.getClassifyname().equals("出账")){
            viewHolder.classifyname.setTextColor(Color.RED);
            viewHolder.resonname.setTextColor(Color.RED);
            viewHolder.type.setTextColor(Color.RED);
            viewHolder.date.setTextColor(Color.RED);
            viewHolder.price.setTextColor(Color.RED);
            viewHolder.remark.setTextColor(Color.RED);
        }else{
            viewHolder.classifyname.setTextColor(Color.BLACK);
            viewHolder.resonname.setTextColor(Color.BLACK);
            viewHolder.type.setTextColor(Color.BLACK);
            viewHolder.date.setTextColor(Color.BLACK);
            viewHolder.price.setTextColor(Color.BLACK);
            viewHolder.remark.setTextColor(Color.BLACK);
        }
        return convertView;
    }

    public static class ViewHolder {
        public TextView id;
        public TextView classifyname;
        public TextView resonname;
        public TextView type;
        public TextView date;
        public TextView price;
        public TextView remark;

    }
}
