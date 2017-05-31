package ui.adpter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.erp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Tool.statistics.Statics;
import http.ExpressNumberManagementHttpPost;
import ui.activity.ExpressBillingManagementActivity;
import ui.activity.ExpressNumberManagerActivity;

/**
 * Created by admin on 2017/2/23.
 */

public class ExpressNumberManagementAdapter extends BaseAdapter {
    private Activity activity;
    private String id;
    private ExpressNumberManagementHttpPost httpPost;
    private int positions;
    private ViewHolder vh;
    private List<ViewHolder> holders = new ArrayList<>();
    private Calendar calendar;

    public ExpressNumberManagementAdapter(Activity accactivity) {
        this.activity = accactivity;
    }

    @Override
    public int getCount() {
        if (Statics.enmList.size() != 0) {
            return Statics.enmList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        positions = position;
        Log.v("test", "getView");
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.express_number_managerlist, null);
            //找控件
            vh.number = (TextView) convertView.findViewById(R.id.number);
            vh.type = (TextView) convertView.findViewById(R.id.type);
            vh.expressName = (TextView) convertView.findViewById(R.id.expressName);
            vh.expressCount = (TextView) convertView.findViewById(R.id.expressCount);
            vh.billingTime=(TextView) convertView.findViewById(R.id.billingTime);
            vh.operate = (TextView) convertView.findViewById(R.id.operate);
            convertView.setTag(vh);
            holders.add(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        //获取数据和显示数据
        if (Statics.enmList.size() == 0){
            return convertView;
        }
        String number = Integer.toString((ExpressNumberManagerActivity.page-1)*50 + position+1);
        id = Statics.enmList.get(position).getId();
        String expressName = Statics.enmList.get(position).getExpressName().trim();
        Log.v("test4Adapter","Adapter:expressName"+expressName);
        String type = Statics.enmList.get(position).getType().trim();
        String expressCount = Statics.enmList.get(position).getExpressCount().trim();
        String billingTime = Statics.enmList.get(position).getBillingTime().trim();
        vh.number.setText(number);
        vh.type.setText(type);
        vh.expressName.setText(expressName);
        vh.expressCount.setText(expressCount);
        vh.billingTime.setText(billingTime);
        vh.operate.setOnClickListener(new Click(positions));//解决光标获取不对的问题
        return convertView;
    }

    private void showNormalDialog(int item) {
            /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        id = Statics.enmList.get(item).getId();
        AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(activity);
        normalDialog.setIcon(R.drawable.delete);
        normalDialog.setTitle("删除记录");
        normalDialog.setMessage("是否删除该记录?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        if ("success".equals(httpPost.delExpressManagerHttp(Statics.ExpressCountSearch, id ,activity))) {
                            Toast.makeText(activity, "删除成功", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(activity, "删除失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do

                    }
                });
        // 显示
        normalDialog.show();
    }


    public class Click implements View.OnClickListener {
        int item= 0;

        public Click(int position) {
            item= position;
        }

        @Override
        public void onClick(View v) {
            httpPost = new ExpressNumberManagementHttpPost();
            showNormalDialog(item);
        }

    }

    class ViewHolder {
        TextView number, expressName,type,expressCount,billingTime,operate;
    }
}
