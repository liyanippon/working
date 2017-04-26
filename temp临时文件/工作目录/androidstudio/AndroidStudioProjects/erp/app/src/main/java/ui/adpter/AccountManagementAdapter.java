package ui.adpter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.erp.R;

import java.util.ArrayList;
import java.util.List;

import http.AccountManagementHttpPost;
import http.Constants;

/**
 * Created by admin on 2017/2/23.
 */

public class AccountManagementAdapter extends BaseAdapter {
    private Activity activity;
    private String id;
    private AccountManagementHttpPost httpPost;
    private int positions;
    private ViewHolder vh;
    private List<ViewHolder> holders = new ArrayList<ViewHolder>();
    public AccountManagementAdapter(Activity accactivity) {
        this.activity = accactivity;
    }

    @Override
    public int getCount() {
        if (Constants.accountManagementList.size() != 0) {
            return Constants.accountManagementList.size();
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
            vh.sum = (TextView) convertView.findViewById(R.id.sum);
            vh.operate = (TextView) convertView.findViewById(R.id.operate);
            convertView.setTag(vh);
            holders.add(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        //获取数据和显示数据
        String number = Integer.toString(position + 1);
        id = Constants.accountManagementList.get(position).getId();
        String type = Constants.accountManagementList.get(position).getType().trim();
        String classify = Constants.accountManagementList.get(position).getClassify().trim();
        String sum = Constants.accountManagementList.get(position).getSum().trim();
        vh.number.setText(number);
        vh.type.setText(type);
        vh.classify.setText(classify);
        vh.sum.setText(sum);
        vh.operate.setOnClickListener(new Click(positions));//解决光标获取不对的问题
        return convertView;
    }

    private void showNormalDialog(int item) {
            /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        id = Constants.accountManagementList.get(item).getId();
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
                        if ("success".equals(httpPost.delAccountManagerHttp(Constants.AccountManagementSearchUrl, id,activity))) {
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
            httpPost = new AccountManagementHttpPost();
            showNormalDialog(item);
        }

    }

    class ViewHolder {
        TextView number, type, classify, sum,operate;
    }
}
