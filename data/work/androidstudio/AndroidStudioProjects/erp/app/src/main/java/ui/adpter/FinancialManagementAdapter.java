package ui.adpter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.admin.erp.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Tool.ToolUtils;
import Tool.statistics.Statics;
import http.HttpBasePost;
import http.HttpTypeConstants;
import model.javabean.FinancialManagement;
import ui.activity.FinancialBillingManagementActivity;

/**
 * Created by admin on 2017/2/23.
 */

public class FinancialManagementAdapter extends BaseAdapter {
    private Activity activity;
    private String id;
    private int positions;
    private ViewHolder vh;
    private List<ViewHolder> holders = new ArrayList<ViewHolder>();
    public FinancialManagementAdapter(Activity accactivity) {
        this.activity = accactivity;
    }
    private HashMap<String,String> param;

    @Override
    public int getCount() {
        if (Statics.financialManagementList.size() != 0
                && Statics.financialManagementList.get(0).getData().size() !=0 && Statics.financialManagementList.get(0).getData().get(0).getRows()!=null) {
            return Statics.financialManagementList.get(0).getData().get(0).getRows().size();
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
            //vh.content = (TextView) convertView.findViewById(R.id.content);
            vh.billingTime = (TextView) convertView.findViewById(R.id.billingTime);
            vh.price = (TextView) convertView.findViewById(R.id.price);
            vh.operate = (TextView) convertView.findViewById(R.id.operate);
            convertView.setTag(vh);
            holders.add(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        //获取数据和显示数据
        String number = Integer.toString((FinancialBillingManagementActivity.page-1)*50 + position+1);
        //id = Statics.expressManagementList.get(position).getId();
        String account = Statics.financialManagementList.get(0).getData().get(0).getRows().get(position).getBillType();//账目
        String classify = Statics.financialManagementList.get(0).getData().get(0).getRows().get(position).getBillClassify();//分类
        String content = Statics.financialManagementList.get(0).getData().get(0).getRows().get(position).getBillClassification();//内容
        //账单时间处理
        FinancialManagement.DataBean.RowsBean.BillTimeBean time = Statics.financialManagementList.get(0).getData().get(0).getRows().get(position).getBillTime();//账单时间
        int years = time.getYear();//年
        int mon = time.getMonth();//月
        int date= time.getDate();//日
        String year = ToolUtils.timeDateFormat(Integer.toString(years));
        StringBuffer billingTimeSb=new StringBuffer();
        billingTimeSb.append(year).append("-").append(++mon).append("-").append(date);
        String price = Statics.financialManagementList.get(0).getData().get(0).getRows().get(position).getBillSum()+"";//金额

        if(classify.equals("出账")){
            vh.account.setTextColor(Color.RED);
            vh.classify.setTextColor(Color.RED);
//            vh.content.setTextColor(Color.RED);
            vh.billingTime.setTextColor(Color.RED);
            vh.price.setTextColor(Color.RED);
            vh.price.setText("-"+price);
        }else{
            vh.account.setTextColor(Color.BLACK);
            vh.classify.setTextColor(Color.BLACK);
 //           vh.content.setTextColor(Color.BLACK);
            vh.billingTime.setTextColor(Color.BLACK);
            vh.price.setTextColor(Color.BLACK);
            vh.price.setText(price);
        }
        //vh.number.setText(number);
        vh.number.setText(Integer.toString(++positions));
        vh.account.setText(account);
        vh.classify.setText(classify);
//        vh.content.setText(content);
        vh.billingTime.setText(billingTimeSb.toString());

        vh.operate.setOnClickListener(new Click(positions));//解决光标获取不对的问题
        return convertView;
    }

    private void showNormalDialog(int item) {
            /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        //id = Statics.expressManagementList.get(item).getId();
        id = Statics.financialManagementList.get(0).getData().get(0).getRows().get(item).getId();
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
                        param=new HashMap<>();
                        param.put("id", id);
                        if ("success".equals(HttpBasePost.postHttp(Statics.FinancialBillingManagementDelUrl, param, HttpTypeConstants.FinancialBillingManagementDelUrlType))) {
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
            showNormalDialog(item);
        }

    }

    class ViewHolder {
        TextView number, account, classify,  content, billingTime, price, operate;
    }
}
