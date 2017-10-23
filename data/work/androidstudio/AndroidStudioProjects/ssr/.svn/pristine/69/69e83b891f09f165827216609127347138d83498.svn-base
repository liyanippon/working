package ui.adpter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.admin.erp.R;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import Tool.ToolUtils;
import Tool.statistics.Statics;
import http.ExpressBillingManagementHttpPost;
import http.HttpBasePost;
import http.HttpTypeConstants;
import model.javabean.ExpressManagement;
import model.javabean.ProjectAllPageData;
import model.javabean.ResourceGetWXWXPageDataResource;
import thread.SyncThread;
import thread.SyncThread1;
import ui.activity.ExpressBillingManagementActivity;
import ui.activity.OfficeDirActivity;
import ui.activity.ProjectManagementActivity;
import ui.activity.ResourceManagementActivity;
/**
 * Created by admin on 2017/2/23.
 */
public class SourceManagementAdapter extends BaseAdapter {
    public static Activity activity;
    private String id;
    private ExpressBillingManagementHttpPost httpPost;
    public int positions;
    public static int items;
    private ViewHolder vh;
    private List<ViewHolder> holders = new ArrayList<ViewHolder>();
    private android.support.v7.app.AlertDialog dlg;
    private static String fileName;
    public static File dir;
    private static boolean isFileExists = false;
    //请在内存下放个文件
    public static String pathStr = Environment.getExternalStorageDirectory()+"/erp/person_history/";
    private HashMap<String,String> param ;
    //public static ProgressDialog progressDialog = null;//加载数据显示进度条
    private InputMethodManager imm;//键盘处理
    static Thread httpThread,writeFileThread;
    private static String fileNameStrings;
    public SourceManagementAdapter(Activity activity) {
        this.activity = activity;
    }
    @Override
    public int getCount() {
        if (Statics.rgwDataResourcesList!=null&&Statics.rgwDataResourcesList.size()!=0) {
            return Statics.rgwDataResourcesList.size();
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
            convertView = LayoutInflater.from(activity).inflate(R.layout.sourcemanagerlist, null);
            //找控件
            vh.number = (TextView) convertView.findViewById(R.id.number);
            vh.name = (TextView) convertView.findViewById(R.id.name);
            vh.sex = (TextView) convertView.findViewById(R.id.sex);
            vh.phone = (TextView) convertView.findViewById(R.id.phone);
            vh.educationalBackground = (TextView) convertView.findViewById(R.id.background);
            vh.details = (TextView) convertView.findViewById(R.id.details);
            vh.history = (TextView) convertView.findViewById(R.id.history);
            convertView.setTag(vh);
            holders.add(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        //获取数据和显示数据
        //String number = Integer.toString((ExpressBillingManagementActivity.page-1)*50 + position+1);
        ResourceGetWXWXPageDataResource rgwdResource=Statics.rgwDataResourcesList.get(position);
        id = rgwdResource.getId();
        String name = rgwdResource.getName();//名字
        String sex = rgwdResource.getSex();//姓别
        String phone = rgwdResource.getContact();//电话号码
        String backgroud = rgwdResource.getEducation();//学历

        vh.number.setText(Integer.toString(++positions));
        vh.number.setGravity(Gravity.CENTER);
        vh.name.setText(name);
        vh.sex.setText(sex);
        vh.phone.setText(phone);
        vh.educationalBackground.setText(backgroud);
        fileName = rgwdResource.getFileName();
        vh.history.setOnClickListener(new Click(position));
        vh.details.setOnClickListener(new Click(position));
        return convertView;
    }

    public class Click implements View.OnClickListener {
        int item= 0;

        public Click(int position) {
            item= position;
        }

        @Override
        public void onClick(View v) {
            //获取文件名
            //ResourceGetWXWXPageDataResource.RowsBean rgwdResource=Statics.rgwDataResourcesList.get(0).getRows().get(positions);
            //String fileName = rgwdResource.getFileName();
            switch (v.getId()){
                case R.id.history:
                    items=item;
                    ResourceGetWXWXPageDataResource rgwdResource=Statics.rgwDataResourcesList.get(item);
                    fileName = rgwdResource.getFileName();
                    id = rgwdResource.getId();
                    if(!fileName.contains(",")){
                        Toast.makeText(activity, "简历不存在", Toast.LENGTH_SHORT).show();
                        break;
                    }else{
                        dir = new File(pathStr);
                        id = rgwdResource.getId();
                        String[] parts = fileName.split(",");
                        String name;
                        if(parts.length==3){
                            name = parts[0].toString();
                            openFile(id,name);
                        }

                    }
                    break;
                case R.id.details:
                    detailsDialog(item);
                    break;
            }
        }
    }
    private void detailsDialog(int item) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);
        LayoutInflater inflater = ResourceManagementActivity.activity.getLayoutInflater();
        final View layout = inflater.inflate(R.layout.resourcemanager_dialog_detailed_item, null);//获取自定义布局
        TextView name = (TextView) layout.findViewById(R.id.name);//姓名
        TextView employeeId = (TextView) layout.findViewById(R.id.employeeId);//员工编号
        TextView sex = (TextView) layout.findViewById(R.id.sex);//性别
        TextView phone = (TextView) layout.findViewById(R.id.phone);//联系电话
        TextView educationalBackground = (TextView) layout.findViewById(R.id.background);//学历
        TextView idcard = (TextView) layout.findViewById(R.id.idcard);//身份证
        TextView email = (TextView) layout.findViewById(R.id.email);//邮箱
        TextView remark = (TextView) layout.findViewById(R.id.remark);//备注
        ResourceGetWXWXPageDataResource rgwdResource=Statics.rgwDataResourcesList.get(item);
        name.setText(rgwdResource.getName());
        employeeId.setText(rgwdResource.getEmployeeId());
        sex.setText(rgwdResource.getSex());
        phone.setText(rgwdResource.getContact());
        educationalBackground.setText(rgwdResource.getEducation());
        idcard.setText(rgwdResource.getIdCard());
        email.setText(rgwdResource.getMail());
        remark.setText(rgwdResource.getDescription());
        //创建人就是用户名
        builder.setView(layout);
        dlg = builder.create();
        dlg.show();
        //dlg.getWindow().setLayout(1500, 1500);
        WindowManager windowManager = ResourceManagementActivity.activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dlg.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        dlg.getWindow().setAttributes(lp);
    }
    class ViewHolder {
        TextView number, name, sex,  phone, educationalBackground, details,history;
    }

    public void openFile(String fileNameId,String fileNameString){
        if (dir.exists()){
            //判断目录下是否有该文件名，如果有立即使用，没有从网络端下载再使用
            fileNameStrings = fileNameString;
            ArrayList<String> fileNames = ToolUtils.getFileName(pathStr);
            for (int i =0;i<fileNames.size();i++){
                if(fileNameString.equals(fileNames.get(i))){//存在该文件，立即打开
                    //打开文件 路径+文件名
                    isFileExists = true;
                    Intent in =new Intent(activity,OfficeDirActivity.class);
                    in.putExtra("fileName",fileNameString);
                    in.putExtra("pathStr",pathStr);
                    activity.startActivity(in);
                    break;
                }
            }
            //没有该文件从网络端下载
            if(!isFileExists){
                //从网络端下载简历
                param = new HashMap<>();
                param.put("id",fileNameId);
                //HttpBasePost.postHttp(Statics.ResourceGetDownLoadFileUrl,param, HttpTypeConstants.ResourceGetDownLoadFileUrlType);//请求网络
                //线程安全
                httpThread= new Thread(new SyncThread1(param));
                httpThread.start();
            }
            isFileExists = false;
        }else{
            if(!dir.exists()){
                dir.mkdir();//创建文件夹
                //访问网络
                //从网络端下载简历
                param = new HashMap<>();
                param.put("id",fileNameId);
                HttpBasePost.postHttp(Statics.ResourceGetDownLoadFileUrl,param, HttpTypeConstants.ResourceGetDownLoadFileUrlType);//请求网络
                //new SyncThread(param).run();//线程安全
            }
        }
    }
    public static void AdapterRefresh(String type) {//刷新adapter
        switch (type) {
            case "wordAadpter":
                byte[] img = new byte[0];
                //Toast.makeText(activity, "内容：" + Statics.downLoadFile, Toast.LENGTH_SHORT).show();
                try {
                    img = Statics.downLoadFile.getBytes("ISO-8859-1");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                //线程安全
                try {
                    writeFileThread = new Thread(new SyncThread(pathStr,img,fileNameStrings));
                    writeFileThread.start();
                    httpThread.join(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //ToolUtils.writeImageToDisk(pathStr, img,fileName);
                break;
        }
    }
    public static void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            //else if (file.isDirectory())
              //  deleteDirWihtFile(file); // 递规的方式删除文件夹
        }
        //dir.delete();// 删除目录本身
    }
    //uiHandler在主线程中创建，所以自动绑定主线程
    public static Handler uiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    //跳转到
                    Intent in =new Intent(activity,OfficeDirActivity.class);
                    in.putExtra("fileName",fileNameStrings);
                    in.putExtra("pathStr",pathStr);
                    activity.startActivity(in);
                    break;
            }
        }
    };

}
