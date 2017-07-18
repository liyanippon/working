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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import Tool.ToolUtils;
import model.FinancialBillingGetWXSelectMonthAccount;
import model.XiangxiBillingStatistics;

/**
 * Created by admin on 2017/3/8.
 */

public class MonthXiangxiBillingStatisticsAdapter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private List<FinancialBillingGetWXSelectMonthAccount> list;
    private LayoutInflater inflater;

    public MonthXiangxiBillingStatisticsAdapter(Activity activityBilling, List<FinancialBillingGetWXSelectMonthAccount> list) {
        inflater = LayoutInflater.from(activityBilling);
        this.activity = activityBilling;
        this.list = list;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list != null) {
            ret = list.size();
            Log.d("MonthXiangxiBillingStat", ";'" + ret);
        }
        Log.d("MonthXiangxiBillingStat", ";'" + ret);
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
        //FinancialBillingGetWXSelectMonthAccount xiangxiBillingStatistics = (FinancialBillingGetWXSelectMonthAccount) this.getItem(position);
        MonthXiangxiBillingStatisticsAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new MonthXiangxiBillingStatisticsAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.billing_mon_statics_xiangxilist_layout, null);
            viewHolder.id = (TextView) convertView.findViewById(R.id.text_id);//序号
            viewHolder.dateTime = (TextView) convertView.findViewById(R.id.date_time);
            viewHolder.createTime = (TextView) convertView.findViewById(R.id.create_time);
            viewHolder.type = (TextView) convertView.findViewById(R.id.type);
            viewHolder.account = (TextView) convertView.findViewById(R.id.account);
            viewHolder.sum = (TextView) convertView.findViewById(R.id.count);
            viewHolder.remark = (TextView) convertView.findViewById(R.id.remarkId);
            viewHolder.customerName = (TextView) convertView.findViewById(R.id.customerName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MonthXiangxiBillingStatisticsAdapter.ViewHolder) convertView.getTag();
        }

        //毫秒转换年月日
        String time = ToolUtils.getYearMonthDate(list.get(position).getBill_time());
        viewHolder.dateTime.setText(time);
        viewHolder.dateTime.setTextSize(13);
        time = ToolUtils.getYearMonthDate(list.get(position).getBill_create_time());
        viewHolder.createTime.setText(time);
        viewHolder.createTime.setTextSize(13);
        String bill_classify = list.get(position).getClassifyname();
        String sumString = list.get(position).getSum();
        String accountString = list.get(position).getId();
        String customerNameString = list.get(position).getNick_name();
        String remarkString = list.get(position).getBill_classification();
        if(!bill_classify.equals("进账")){
            Log.d("MonthXiangxiBillingStat", "类型："+bill_classify);
            bill_classify = bill_classify.split("<b>")[1].split("</b>")[0];
            accountString = accountString.split("<b>")[1].split("</b>")[0];
            remarkString = remarkString.split("<b>")[1].split("</b>")[0];
            viewHolder.dateTime.setTextColor(Color.RED);
            viewHolder.createTime.setTextColor(Color.RED);
            viewHolder.remark.setTextColor(Color.RED);
            viewHolder.type.setTextColor(Color.RED);
            viewHolder.account.setTextColor(Color.RED);
            viewHolder.sum.setTextColor(Color.RED);
            viewHolder.customerName.setTextColor(Color.RED);
        }else{
            viewHolder.dateTime.setTextColor(Color.BLACK);
            viewHolder.createTime.setTextColor(Color.BLACK);
            viewHolder.remark.setTextColor(Color.BLACK);
            viewHolder.type.setTextColor(Color.BLACK);
            viewHolder.account.setTextColor(Color.BLACK);
            viewHolder.sum.setTextColor(Color.BLACK);
            viewHolder.customerName.setTextColor(Color.BLACK);
        }
        viewHolder.type.setText(bill_classify);
        viewHolder.type.setTextSize(13);
        viewHolder.account.setText(accountString);
        viewHolder.account.setTextSize(13);
        viewHolder.sum.setText(sumString);
        viewHolder.sum.setTextSize(13);
        viewHolder.customerName.setText(customerNameString);
        viewHolder.customerName.setTextSize(13);
        viewHolder.remark.setText(remarkString);
        viewHolder.remark.setTextSize(13);
        viewHolder.id.setText(Integer.toString(++position));
        viewHolder.id.setTextSize(13);
        return convertView;
    }

    public static class ViewHolder {
        public TextView id, dateTime, createTime, type, account, sum ,remark ,customerName;
    }
}
