package ui.adpter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.erp.R;

import java.util.List;

import model.CustomerBillingStatistics;

/**
 * Created by admin on 2017/3/3.
 */

public class CustomerBillingStatisticsAdapter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private List<CustomerBillingStatistics> list;
    private LayoutInflater inflater;
    private int selectID;
    private OnMyCheckChangedListener mCheckChange;
    public CustomerBillingStatisticsAdapter(Activity activityBilling, List<CustomerBillingStatistics> list) {
        //Log.v("test", "CustomerBillingStatisticsAdapter" + list.size());
        inflater = LayoutInflater.from(activityBilling);
        this.activity = activityBilling;
        this.list = list;
    }

    // 自定义的选中方法
    public void setSelectID(int position) {
        selectID = position;
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
        CustomerBillingStatistics customerBillingStatistics = (CustomerBillingStatistics) this.getItem(position);
        CustomerBillingStatisticsAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new CustomerBillingStatisticsAdapter.ViewHolder();
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
            viewHolder = (CustomerBillingStatisticsAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.id.setText(Integer.toString(++position));
        viewHolder.id.setTextSize(13);
        viewHolder.month.setText(customerBillingStatistics.getMonth());
        viewHolder.month.setTextSize(13);
        viewHolder.customer.setText(customerBillingStatistics.getCustomer());
        viewHolder.customer.setTextSize(13);
        viewHolder.income.setText(customerBillingStatistics.getIncome());
        viewHolder.income.setTextSize(13);
        viewHolder.outcome.setText("- "+customerBillingStatistics.getOutcom() + "");
        viewHolder.outcome.setTextSize(13);
        viewHolder.imbalance.setText(customerBillingStatistics.getImbalance() + "");
        viewHolder.imbalance.setTextSize(13);

        //核心方法，判断单选按钮被按下的位置与之前的位置是否相等，然后做相应的操作。
        Log.d("tets","selectID:"+Integer.toString(selectID));
        Log.d("tets","position:"+Integer.toString(position));

        if (selectID == position) {
            viewHolder.linearLayout.setBackgroundColor(Color.BLUE);
        } else {
            viewHolder.linearLayout.setBackgroundColor(0);
        }
        //final int finalPosition = position;
        /*viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                selectID = finalPosition;

                if (mCheckChange != null)
                    mCheckChange.setSelectID(selectID);
            }
        });*/
        return convertView;
    }

    // 回调函数，很类似OnClickListener吧，呵呵
    public void setOncheckChanged(OnMyCheckChangedListener l) {
        mCheckChange = l;
    }

    // 回調接口
    public interface OnMyCheckChangedListener {

        void setSelectID(int selectID);

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
