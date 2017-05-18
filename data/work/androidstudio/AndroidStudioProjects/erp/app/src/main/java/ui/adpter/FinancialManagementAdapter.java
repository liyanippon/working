package ui.adpter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.admin.erp.R;
import java.util.ArrayList;
import java.util.List;
import Tool.statistics.Statics;
import http.ExpressBillingManagementHttpPost;
import http.FinancialManagementHttpPost;

/**
 * Created by admin on 2017/2/23.
 */

public class FinancialManagementAdapter extends BaseAdapter {
    private Activity activity;
    private String id;
    private FinancialManagementHttpPost httpPost;
    private int positions;
    private ViewHolder vh;
    private List<ViewHolder> holders = new ArrayList<ViewHolder>();
    public FinancialManagementAdapter(Activity accactivity) {
        this.activity = accactivity;
    }

    @Override
    public int getCount() {
        if (Statics.financialManagementList.size() != 0) {
            return Statics.financialManagementList.size();
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
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.financialmanagerlist, null);
            //找控件
            vh.number = (TextView) convertView.findViewById(R.id.number);
            vh.account = (TextView) convertView.findViewById(R.id.account);
            vh.classify = (TextView) convertView.findViewById(R.id.classify);
            vh.content = (TextView) convertView.findViewById(R.id.content);
            vh.billingTime = (TextView) convertView.findViewById(R.id.billingTime);
            vh.price = (TextView) convertView.findViewById(R.id.price);
            vh.operate = (TextView) convertView.findViewById(R.id.operate);
            convertView.setTag(vh);
            holders.add(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        //获取数据和显示数据
        String number = Integer.toString(position + 1);
        //id = Statics.expressManagementList.get(position).getId();
        String account = Statics.financialManagementList.get(position).getData().get(position).getFy_name();
        String classify = Statics.financialManagementList.get(position).getData().get(position).getFy_contact();
        String content = Statics.financialManagementList.get(position).getData().get(position).getFy_contact();
        String billingTime = Statics.expressManagementList.get(position).getBillingTime();
        String price = Statics.expressManagementList.get(position).getSum().trim();
        /*if(!(sum.indexOf("-")<0)){
            vh.type.setTextColor(Color.RED);
            vh.classify.setTextColor(Color.RED);
            vh.billingTime.setTextColor(Color.RED);
            vh.sum.setTextColor(Color.RED);
        }else{
            vh.type.setTextColor(Color.BLACK);
            vh.classify.setTextColor(Color.BLACK);
            vh.billingTime.setTextColor(Color.BLACK);
            vh.sum.setTextColor(Color.BLACK);
        }*/
        vh.number.setText(number);
        //vh.customerName.setText(type);
        //vh.phone.setText(classify);
        //vh.address.setText(billingTime);
        //vh.remarks.setText(sum);
        vh.operate.setOnClickListener(new Click(positions));//解决光标获取不对的问题
        return convertView;
    }

    private void showNormalDialog(int item) {
            /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        id = Statics.expressManagementList.get(item).getId();
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
                        if ("success".equals(httpPost.delAccountManagerHttp(Statics.FinancialBillingManagementSearchUrl, id,activity))) {
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
            httpPost = new FinancialManagementHttpPost();
            showNormalDialog(item);
        }

    }

    class ViewHolder {
        TextView number, account, classify,  content, billingTime, price, operate;
    }
}
