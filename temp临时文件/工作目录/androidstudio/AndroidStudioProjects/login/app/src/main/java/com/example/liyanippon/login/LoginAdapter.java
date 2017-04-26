package com.example.liyanippon.login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by liyanippon on 17/04/10.
 */

public class LoginAdapter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private List<String> list;
    private LayoutInflater inflater;
    public LoginAdapter(Activity activityBilling, List<String> list) {
        //Log.v("test", "CustomerBillingStatisticsAdapter" + list.size());
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LoginAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.login_items, null);
            viewHolder.item = (TextView) convertView.findViewById(R.id.item);//姓名
            viewHolder.del = (TextView) convertView.findViewById(R.id.del);//删除
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (LoginAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.item.setText(list.get(position));
        viewHolder.del.setText("删除");
        viewHolder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"你点击的是："+Integer.toString(position),Toast.LENGTH_SHORT).show();
                list.remove(position);
                SharedPreferences.Editor editor = MainActivity.sPreferences.edit();
                editor.remove("name"+position);
                editor.remove("pwd"+position);
                //editor.clear();
                editor.commit();
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    public static class ViewHolder {
        public TextView item;
        public TextView del;
    }
}
