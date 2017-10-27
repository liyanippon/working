package ui.adpter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
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

import Tool.ToolUtils;
import Tool.statistics.Statics;
import http.ExpressBillingManagementHttpPost;
import model.javabean.ExpressManagement;
import ui.activity.ExpressBillingManagementActivity;

/**
 * Created by admin on 2017/2/23.
 */

public class ExpressManagementAdapter extends BaseAdapter {
    private Activity activity;
    private String id;
    private ExpressBillingManagementHttpPost httpPost;
    private int positions;
    private ViewHolder vh;
    private List<ViewHolder> holders = new ArrayList<ViewHolder>();
    public ExpressManagementAdapter(Activity accactivity) {
        this.activity = accactivity;
    }
    @Override
    public int getCount() {
        //Log.d("ExpressManagementAdapte", "查看" + Statics.expressManagementList.get(0).getData().get(0).getRows().size());
        if (Statics.expressManagementList.size()!=0) {
            return Statics.expressManagementList.get(0).getData().get(0).getRows().size();
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
            convertView = LayoutInflater.from(activity).inflate(R.layout.accountmanagerlist, null);
            //找控件
            vh.number = (TextView) convertView.findViewById(R.id.number);
            vh.type = (TextView) convertView.findViewById(R.id.type);
            vh.classify = (TextView) convertView.findViewById(R.id.classify);
            vh.billingTime = (TextView) convertView.findViewById(R.id.billingTime);
            vh.sum = (TextView) convertView.findViewById(R.id.sum);
            vh.operate = (TextView) convertView.findViewById(R.id.operate);
            convertView.setTag(vh);
            holders.add(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        //获取数据和显示数据
        String number = Integer.toString((ExpressBillingManagementActivity.page-1)*50 + position+1);
        ExpressManagement.DataBean.RowsBean edrb=Statics.expressManagementList.get(0).getData().get(0).getRows().get(position);
        id = edrb.getId();
        String type = edrb.getType().trim();
        String classify = edrb.getClassify().trim();
        int temp = edrb.getBillingTime().getMonth();
        String billingTime = ToolUtils.timeDateFormat(Integer.toString(edrb.getBillingTime().getYear()))+"-"+(++temp)
                +"-"+edrb.getBillingTime().getDate();
        String sum = edrb.getSum()+"";
        if(!"进账".equals(edrb.getClassify())){
                type = type.split("<b>")[1].split("</b>")[0];
            classify = classify.split("<b>")[1].split("</b>")[0];
            vh.type.setTextColor(Color.RED);
            vh.classify.setTextColor(Color.RED);
            vh.billingTime.setTextColor(Color.RED);
            vh.sum.setTextColor(Color.RED);
        }else{
            if(type.indexOf("<b>")!=-1){
                type = type.split("<b>")[1].split("</b>")[0];
            }
            if(classify.indexOf("<b>")!=-1){
                classify = classify.split("<b>")[1].split("</b>")[0];
            }
            vh.type.setTextColor(Color.BLACK);
            vh.classify.setTextColor(Color.BLACK);
            vh.billingTime.setTextColor(Color.BLACK);
            vh.sum.setTextColor(Color.BLACK);
        }
        //vh.number.setText(number);
        vh.number.setText(Integer.toString(++positions));
        vh.number.setGravity(Gravity.CENTER);
        if(type.length()>2){
            vh.type.setText(type.substring(0,2));
        }else{
            vh.type.setText("");
        }
        vh.type.setGravity(Gravity.CENTER);
        vh.classify.setText(classify);
        vh.classify.setGravity(Gravity.CENTER);
        vh.billingTime.setText(billingTime);
        vh.billingTime.setGravity(Gravity.CENTER);
        vh.sum.setText(sum);
        vh.sum.setGravity(Gravity.CENTER);
        vh.operate.setOnClickListener(new Click(positions));//解决光标获取不对的问题
        return convertView;
    }
    private void showNormalDialog(final int item) {
            /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        id = Statics.expressManagementList.get(0).getData().get(0).getRows().get(item).getId();
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
                        ExpressBillingManagementActivity.deleteSuccess = true;
                        ExpressManagement.DataBean.RowsBean edrb=Statics.expressManagementList.get(0).getData().get(0).getRows().get(item);
                        String sum = edrb.getSum().toString();
                        String classify = edrb.getClassify();
                        String paymentMethod = edrb.getPaymentMethod();
                        Log.d("ExpressManagementAdapte", "jin" + sum + "*" + classify + "*" + paymentMethod);
                        if (!"进账".equals(edrb.getClassify())){
                             sum = edrb.getSum().abs().toString();
                             classify = edrb.getClassify().split("<b>")[1].split("</b>")[0];
                             paymentMethod = edrb.getPaymentMethod().split("<b>")[1].split("</b>")[0];
                            Log.d("ExpressManagementAdapte", "chuzhag"+sum+";"+classify+";"+paymentMethod);
                        }
                        Statics.isDelete = true;
                        if ("success".equals(httpPost.delAccountManagerHttp(Statics.FinancialBillingManagementSearchUrl, id, sum
                                ,classify,paymentMethod,activity))) {
                            ExpressBillingManagementActivity.progressDialog = ProgressDialog.show(activity, "请稍等...", "获取数据中...", true);//显示进度条
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
            httpPost = new ExpressBillingManagementHttpPost();
            ExpressBillingManagementActivity.newBilling.setVisibility(View.INVISIBLE);
            ExpressBillingManagementActivity.transferAccounts.setVisibility(View.INVISIBLE);
            showNormalDialog(item);
        }
    }
    class ViewHolder {
        TextView number, type, classify,  billingTime, sum, operate;
    }
}
