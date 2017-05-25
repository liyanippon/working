package http;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import Tool.JsonResolve;
import Tool.statistics.ExceptionUtil;
import Tool.statistics.Statics;
import model.AttendanceStatistics;
import model.FinancialAccount;
import model.FinancialCustomer;
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
    public String searchHttp(String httpUrl, String typeSpinnerString, String classifySpinnerString, String customerNameSpinnerString, final Activity activity, int page) {//账目查询
        Log.d("search",typeSpinnerString+"@"+classifySpinnerString+"@"+customerNameSpinnerString);
        Log.d("FinancialManagementHttp", "httpurl:" + httpUrl);
        if("全部".equals(typeSpinnerString)){
            typeSpinnerString = "";
        }
        if("全部".equals(classifySpinnerString)){
            classifySpinnerString = "";
        }
        if("全部".equals(customerNameSpinnerString)){
            customerNameSpinnerString = "";
        }
        Log.d("test", typeSpinnerString + "@" + classifySpinnerString + "@" + customerNameSpinnerString);
        final String rows = "50";//每页显示条数
        Log.v("test3", "searchHttp:" + Integer.toString(page));
        finalHttp = new FinalHttp();
        finalHttp.configRequestExecutionRetryCount(7);// 请求错误重试次数
        finalHttp.configTimeout(20000);// 超时时间
        params = new AjaxParams();
        params.put("option", "1");//1查询，2添加，3删除
        params.put("billType", typeSpinnerString);
        params.put("billClassify", classifySpinnerString);
        params.put("billCustomerId", customerNameSpinnerString);
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
                FinancialBillingManagementActivity financialBillingManagementActivity = new FinancialBillingManagementActivity();
                financialBillingManagementActivity.AdapterRefresh("FinancialManagementHttpPost");
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
    public String addCountManagerHttp(String httpUrl,String accountSpinnerString    //添加财务账单
            ,String classifySpinnerString,String timeString,String contentString
            ,String priceString,String customerNameSpinnerString,String remarkString) {

        Log.d("FinancialManagementHttp", "addCountManagerHttp:"
                + httpUrl + "@" + accountSpinnerString + "@" + classifySpinnerString + "@" + timeString +
                "@" + contentString + "@" + priceString + "@" + customerNameSpinnerString + "@" + remarkString);
        Log.d("FinancialManagementHttp", "name:" + Statics.Name);
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("id","");

        params.put("createBy",Statics.Name);
        params.put("updateBy",Statics.Name);
        params.put("option", "2");//1查询，2添加，3删除
        params.put("userName", Statics.Name);
        params.put("billType", accountSpinnerString);
        params.put("billClassify", classifySpinnerString);
        params.put("billTime2", timeString);//创建时间
        params.put("billClassification", contentString);
        params.put("billSum", priceString);
        params.put("billCustomerId", customerNameSpinnerString);
        params.put("billDescription", remarkString);
        params.put("updateTime",timeString);

        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);

                Log.v("test","Constants.userName::"+Statics.userName);
                String result = (String) o;//从从网络端返回数据
                Log.d("FinancialManagementHttp", "添加数据"+result);
                resultString = "success";
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);

                resultString = "error";
                Log.d("test", strMsg);
                ExceptionUtil.httpPost("FinancialManagementHttp");
            }
        });
        return resultString;
    }
    public String delAccountManagerHttp(String httpUrl, String id , final Activity activity) {//账目删除
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        //params.put("option", "3");//1查询，2添加，3删除
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
                ExceptionUtil.httpPost("FinancialManagementHttp");
            }
        });

        return resultString;
    }


    //下拉菜单
    public String searchAccount(String httpUrl) {//账目
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);

                String result = (String) o;//从从网络端返回数据
                resultString = "success";
                Log.d("FinancialManagementHttp", "下拉框账目:" + result);
                //json数据使用Gson框架解析
                Statics.financialManagementList.clear();
                FinancialAccount[] fm = new Gson().fromJson(result, FinancialAccount[].class);
                Collections.addAll(Statics.financialAccountList,fm);//转化arrayList
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);

            }
        });

        return resultString;
    }

    public String searchCustomName(String httpUrl) {//客户名
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);

                String result = (String) o;//从从网络端返回数据
                resultString = "success";
                Log.d("FinancialManagementHttp", "客户名:" + result);
                //json数据使用Gson框架解析
                Statics.financialCustomersList.clear();
                FinancialCustomer[] fm = new Gson().fromJson(result, FinancialCustomer[].class);
                Collections.addAll(Statics.financialCustomersList,fm);//转化arrayList
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);

                resultString = "error";
                Log.d("test", strMsg);
                ExceptionUtil.httpPost("FinancialManagementHttp");
            }
        });

        return resultString;
    }


}
