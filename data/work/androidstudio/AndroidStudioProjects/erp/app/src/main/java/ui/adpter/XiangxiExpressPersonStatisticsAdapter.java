package ui.adpter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.erp.R;

import java.util.List;

import model.ExpressPersonStatisticsXiangqing;

/**
 * Created by admin on 2017/3/8.
 */

public class XiangxiExpressPersonStatisticsAdapter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private List<ExpressPersonStatisticsXiangqing> list;
    private LayoutInflater inflater;

    public XiangxiExpressPersonStatisticsAdapter(Activity activityExpress, List<ExpressPersonStatisticsXiangqing> list) {
        Log.v("test", "CustomerBillingStatisticsAdapter" + list.size());
        inflater = LayoutInflater.from(activityExpress);
        this.activity = activityExpress;
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
        ExpressPersonStatisticsXiangqing epsx = (ExpressPersonStatisticsXiangqing) this.getItem(position);
        XiangxiExpressPersonStatisticsAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new XiangxiExpressPersonStatisticsAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.express_person_statics_xiangxilist_layout, null);
            viewHolder.id = (TextView) convertView.findViewById(R.id.text_id);//序号
            viewHolder.type = (TextView) convertView.findViewById(R.id.type);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.time = (TextView) convertView.findViewById(R.id.date);
            viewHolder.numeric = (TextView) convertView.findViewById(R.id.number);
            viewHolder.remark = (TextView) convertView.findViewById(R.id.remark);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (XiangxiExpressPersonStatisticsAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.id.setText(Integer.toString(++position));
        viewHolder.id.setTextSize(13);
        viewHolder.type.setText(epsx.getType1());
        viewHolder.type.setTextSize(13);
        viewHolder.name.setText(epsx.getName());
        viewHolder.name.setTextSize(13);
        viewHolder.time.setText(epsx.getTime());
        viewHolder.time.setTextSize(13);
        viewHolder.numeric.setText(epsx.getNumeric());
        viewHolder.numeric.setTextSize(13);
        viewHolder.remark.setText(epsx.getDescription());
        viewHolder.remark.setTextSize(13);
        return convertView;
    }

    public static class ViewHolder {
        public TextView id;
        public TextView type;
        public TextView time;
        public TextView name;
        public TextView remark;
        public TextView numeric;

    }
}
