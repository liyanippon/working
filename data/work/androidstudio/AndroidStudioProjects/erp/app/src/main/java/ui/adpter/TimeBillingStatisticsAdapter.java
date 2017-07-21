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
import android.widget.Toast;

import com.example.admin.erp.R;

import java.util.List;

import model.TimeBillingStatistics;

/**
 * Created by admin on 2017/3/1.
 */

public class TimeBillingStatisticsAdapter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private List<TimeBillingStatistics> list;
    private LayoutInflater inflater;

    public TimeBillingStatisticsAdapter(Activity activityBilling, List<TimeBillingStatistics> list) {
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
        TimeBillingStatistics timeBillingStatistics = (TimeBillingStatistics) this.getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();

            convertView = inflater.inflate(R.layout.billingstatics_yearlist_layout, null);
            viewHolder.id = (TextView) convertView.findViewById(R.id.text_id);//序号
            viewHolder.month = (TextView) convertView.findViewById(R.id.month);//月份
            viewHolder.income = (TextView) convertView.findViewById(R.id.income);//进账
            viewHolder.outcome = (TextView) convertView.findViewById(R.id.outcome);//出账
            viewHolder.imbalance = (TextView) convertView.findViewById(R.id.imbalance);//差额

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        /*viewHolder.id.setText(Integer.toString(++position));
        viewHolder.id.setTextSize(13);*/
        viewHolder.month.setText(timeBillingStatistics.getMonth());
        viewHolder.month.setTextSize(13);
        viewHolder.income.setText(timeBillingStatistics.getIncome());
        viewHolder.income.setTextSize(13);
        if(Integer.parseInt(timeBillingStatistics.getOutcom())==0){
            viewHolder.outcome.setText(timeBillingStatistics.getOutcom() + "");
        }else{
            viewHolder.outcome.setText("- "+timeBillingStatistics.getOutcom() + "");
        }
        viewHolder.outcome.setTextSize(13);
        viewHolder.outcome.setTextColor(Color.RED);
        viewHolder.imbalance.setText(timeBillingStatistics.getImbalance() + "");
        viewHolder.imbalance.setTextSize(13);
        if(Integer.parseInt(timeBillingStatistics.getImbalance())<0){
            viewHolder.imbalance.setTextColor(Color.RED);
        }else{
            viewHolder.imbalance.setTextColor(Color.BLACK);
        }
        Log.d("TimeBillingStatisticsAd", "timeBillingStatistics.getIncome()" + timeBillingStatistics.getIncome());
        return convertView;
    }

    public static class ViewHolder {
        public TextView id;
        public TextView month;
        public TextView income;
        public TextView outcome;
        public TextView imbalance;
    }

}
