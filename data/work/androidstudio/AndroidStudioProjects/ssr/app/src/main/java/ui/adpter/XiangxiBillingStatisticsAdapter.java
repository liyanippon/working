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

import model.javabean.XiangxiBillingStatistics;

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
        if (this.list.size()!=0&&this.list.get(0).getData() != null&&this.list.get(0).getData().size()!=0) {
            ret = this.list.get(0).getData().size();
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
        XiangxiBillingStatisticsAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new XiangxiBillingStatisticsAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.billingstatics_xiangxilist_layout, null);
            viewHolder.id = (TextView) convertView.findViewById(R.id.text_id);//序号
            viewHolder.classifyname = (TextView) convertView.findViewById(R.id.classifyname);
            viewHolder.resonname = (TextView) convertView.findViewById(R.id.resonname);
            viewHolder.paymethod = (TextView) convertView.findViewById(R.id.pay_method);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
            viewHolder.remark = (TextView) convertView.findViewById(R.id.remark);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (XiangxiBillingStatisticsAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.classifyname.setText(this.list.get(0).getData().get(position).getClassifyname());
        viewHolder.classifyname.setTextSize(13);
        viewHolder.resonname.setText(this.list.get(0).getData().get(position).getResonname());
        viewHolder.resonname.setTextSize(13);
        viewHolder.paymethod.setText(this.list.get(0).getData().get(position).getPayment());
        viewHolder.paymethod.setTextSize(13);
        String dateTime = this.list.get(0).getData().get(position).getDate().getYear()+"-"
                +(this.list.get(0).getData().get(position).getDate().getMonth()+1)+"-"+this.list.get(0).getData().get(position).getDate().getDate();
        dateTime = dateTime.substring(1, dateTime.length());
        dateTime = "20"+dateTime;
        viewHolder.date.setText(dateTime);
        viewHolder.date.setTextSize(13);
        viewHolder.price.setText(this.list.get(0).getData().get(position).getSum().toString());
        viewHolder.price.setTextSize(13);
        viewHolder.remark.setText(this.list.get(0).getData().get(position).getDescription());
        viewHolder.remark.setTextSize(13);

        if(this.list.get(0).getData().get(position).getClassifyname().equals("出账")){
            viewHolder.price.setText("- " + this.list.get(0).getData().get(position).getSum().toString());
            viewHolder.classifyname.setTextColor(Color.RED);
            viewHolder.resonname.setTextColor(Color.RED);
            viewHolder.paymethod.setTextColor(Color.RED);
            viewHolder.date.setTextColor(Color.RED);
            viewHolder.price.setTextColor(Color.RED);
            viewHolder.remark.setTextColor(Color.RED);
        }else{
            viewHolder.classifyname.setTextColor(Color.BLACK);
            viewHolder.resonname.setTextColor(Color.BLACK);
            viewHolder.paymethod.setTextColor(Color.BLACK);
            viewHolder.date.setTextColor(Color.BLACK);
            viewHolder.price.setTextColor(Color.BLACK);
            viewHolder.remark.setTextColor(Color.BLACK);
        }

        viewHolder.id.setText(Integer.toString(++position));
        viewHolder.id.setTextSize(13);
        return convertView;
    }

    public static class ViewHolder {
        public TextView id;
        public TextView classifyname;
        public TextView resonname;
        public TextView paymethod;
        public TextView date;
        public TextView price;
        public TextView remark;

    }
}
