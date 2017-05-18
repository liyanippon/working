package http;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.Collections;

import Tool.JsonResolve;
import Tool.statistics.ExceptionUtil;
import Tool.statistics.Statics;
import model.AttendanceStatistics;
import model.FinancialManagement;
import portface.LazyLoadFace;
import ui.activity.ExpressBillingManagementActivity;
import ui.activity.FinancialBillingManagementActivity;

/**
 * Created by admin on 2017/5/11.
 */

public class FinancialManagementHttpPost {
    public static FinalHttp finalHttp;
    public static AjaxParams params = new AjaxParams();
    public static String resultString = "error";
    private boolean result = false;
    private Context context;
    private LazyLoadFace lazyLoad;
    public FinancialManagementHttpPost(){}
    public FinancialManagementHttpPost(Context context){
        this.context =context;
    }
    public String searchHttp(String httpUrl, String typeSpinnerString, String classifySpinnerString, String reasonSpinnerString, final Activity activity, int page) {//账目查询
        Log.d("search",typeSpinnerString+"@"+classifySpinnerString+"@"+reasonSpinnerString);
        Log.d("FinancialManagementHttp", "httpurl:" + httpUrl);
        if("全部".equals(typeSpinnerString)){
            typeSpinnerString = "";
        }
        if("全部".equals(classifySpinnerString)){
            classifySpinnerString = "";
        }
        if("全部".equals(reasonSpinnerString)){
            reasonSpinnerString = "";
        }
        Log.d("test", typeSpinnerString + "@" + classifySpinnerString + "@" + reasonSpinnerString);
        final String rows = "50";//每页显示条数
        Log.v("test3", "searchHttp:" + Integer.toString(page));
        finalHttp = new FinalHttp();
        finalHttp.configRequestExecutionRetryCount(7);// 请求错误重试次数
        finalHttp.configTimeout(20000);// 超时时间
        params = new AjaxParams();
        params.put("option", "1");//1查询，2添加，3删除
        //params.put("type", typeSpinnerString);
        //params.put("classify", classifySpinnerString);
        //params.put("reason", reasonSpinnerString);
        params.put("page", Integer.toString(page));
        params.put("rows", rows);

        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);


                String results = (String) o;//从从网络端返回数据
                resultString = "success";
                Log.d("FinancialManagementHttp", "financial" + results);
                //json数据使用Gson框架解析
                Statics.financialManagementList.clear();
                FinancialManagement[] fm = new Gson().fromJson(results, FinancialManagement[].class);
                Collections.addAll(Statics.financialManagementList,fm);//转化arrayList
                //FinancialBillingManagementActivity financialBillingManagementActivity = new FinancialBillingManagementActivity();
                //financialBillingManagementActivity.AdapterRefresh("FinancialManagementHttpPost");
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);

                if (strMsg != null) {
                    resultString = "error";
                    Log.d("test", strMsg);
                }
                FinancialBillingManagementActivity.progressDialog.dismiss();
                ExceptionUtil.httpPost("FinancialManagementHttpPost");
            }

        });

        return resultString;
    }
    public String addCountManagerHttp(String httpUrl, String customerName, String phone, String address,
                                      String description) {

        Log.d("addmm","添加账单:"+customerName+"/"
                +phone+
                "/"+address);
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("id","");
        params.put("createBy",Statics.Name);
        params.put("updateBy",Statics.Name);
        params.put("option", "2");//1查询，2添加，3删除
        params.put("userName", Statics.Name);
        params.put("customerName", customerName);
        params.put("phone", phone);
        params.put("address", address);
        params.put("description", description);
        params.put("billingTime", "");//billingTime 创建时间
        params.put("updateTime","");

        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);

                Log.v("test","Constants.userName::"+Statics.userName);
                String result = (String) o;//从从网络端返回数据
                //Log.d("test",result);
                resultString = "success";
                Log.v("test", "result：" + result);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);

                resultString = "error";
                Log.d("test", strMsg);
                ExceptionUtil.httpPost("AccountManagementHttpPost");
            }
        });

        return resultString;
    }
    public String delAccountManagerHttp(String httpUrl, String id , final Activity activity) {//账目删除
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("option", "3");//1查询，2添加，3删除
        params.put("id", id);

        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);

                String result = (String) o;//从从网络端返回数据
                Log.d("test", "delete"+result);
                resultString = "success";

                //刷新页面
                Log.v("test","notifyDataSetInvalidated");
                String httpUrl = Statics.FinancialBillingManagementUrl;
                searchHttp(httpUrl ,"" ,"" ,"",activity,1);//刷新页面
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);

                resultString = "error";
                Log.d("test", strMsg);
                ExceptionUtil.httpPost("AccountManagementHttpPost");
            }
        });

        return resultString;
    }
}
