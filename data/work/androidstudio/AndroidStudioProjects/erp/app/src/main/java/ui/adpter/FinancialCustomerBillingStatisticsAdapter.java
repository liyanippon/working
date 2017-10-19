package ui.adpter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.erp.R;

import java.math.BigDecimal;
import java.util.List;

import model.javabean.FinancialBillingGetWXSelectCustomer;

/**
 * Created by admin on 2017/3/3.
 */

public class FinancialCustomerBillingStatisticsAdapter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private List<FinancialBillingGetWXSelectCustomer> list;
    private LayoutInflater inflater;
    public FinancialCustomerBillingStatisticsAdapter(Activity activityBilling, List<FinancialBillingGetWXSelectCustomer> list) {
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
        FinancialBillingGetWXSelectCustomer customerBillingStatistics = (FinancialBillingGetWXSelectCustomer) this.getItem(position);
        FinancialCustomerBillingStatisticsAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new FinancialCustomerBillingStatisticsAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.billingstatics_customerlist_layout, null);
            viewHolder.id = (TextView) convertView.findViewById(R.id.text_id);//序号
            viewHolder.month = (TextView) convertView.findViewById(R.id.month);//月份
            viewHolder.customer = (TextView) convertView.findViewById(R.id.custom);//客户
            viewHolder.income = (TextView) convertView.findViewById(R.id.income);//进账
            viewHolder.outcome = (TextView) convertView.findViewById(R.id.outcome);//出账
            viewHolder.imbalance = (TextView) convertView.findViewById(R.id.imbalance);//差额

            viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.item_layout);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FinancialCustomerBillingStatisticsAdapter.ViewHolder) convertView.getTag();
        }
        /*viewHolder.id.setText(Integer.toString(++position));
        viewHolder.id.setTextSize(13);*/
        viewHolder.month.setText(customerBillingStatistics.getMon());
        viewHolder.month.setTextSize(13);
        viewHolder.customer.setText(customerBillingStatistics.getFy_name());
        viewHolder.customer.setTextSize(13);
        viewHolder.income.setText(customerBillingStatistics.getJz1().toString());
        viewHolder.income.setTextSize(13);
        viewHolder.outcome.setText("- "+customerBillingStatistics.getCz1().toString());
        viewHolder.outcome.setTextColor(Color.RED);
        viewHolder.outcome.setTextSize(13);
        viewHolder.imbalance.setText(customerBillingStatistics.getCe().toString());
        viewHolder.imbalance.setTextSize(13);
        if(customerBillingStatistics.getCe().compareTo(BigDecimal.ZERO)<0){
            viewHolder.imbalance.setTextColor(Color.RED);
        }else{
            viewHolder.imbalance.setTextColor(Color.BLACK);
        }
        return convertView;
    }
    public static class ViewHolder {
        public TextView id;
        public TextView month;
        public TextView customer;
        public TextView income;
        public TextView outcome;
        public TextView imbalance;
        public LinearLayout linearLayout;
    }
}
