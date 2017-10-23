package http;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Tool.JsonResolve;
import Tool.statistics.ExceptionUtil;
import Tool.statistics.Statics;
import model.ExpressNumberManagement;
import ui.activity.ExpressNumberManagerActivity;

/**
 * Created by admin on 2017/2/21.
 */

public class ExpressNumberManagementHttpPost{
    public static FinalHttp finalHttp;
    public static AjaxParams params = new AjaxParams();
    public static String resultString = "error";
    private boolean result = false;
    private Context context;

    public ExpressNumberManagementHttpPost(){

    }
    public ExpressNumberManagementHttpPost(Context context){
        this.context =context;
    }

    //快递数量查询 无条件和条件均可以
    public String searchHttp(String httpUrl, String nameIdSpinnerString, String typeSpinnerString, String billingTimeString, final Activity activity, int page) {
        Log.d("testi",nameIdSpinnerString+typeSpinnerString+billingTimeString);
        if("全部".equals(nameIdSpinnerString)){
            nameIdSpinnerString = "";
        }
        if("全部".equals(typeSpinnerString)){
            typeSpinnerString = "";
        }
        if("全部".equals(billingTimeString)){
            billingTimeString = "";
        }
        final String rows = "50";//每页显示条数
        finalHttp = new FinalHttp();
        finalHttp.configRequestExecutionRetryCount(7);// 请求错误重试次数
        finalHttp.configTimeout(20000);// 超时时间
        params = new AjaxParams();
        params.put("option", "1");//1查询，2添加，3删除
        params.put("nameId", nameIdSpinnerString);
        params.put("type", typeSpinnerString);
        params.put("billingTime", billingTimeString);
        params.put("page", Integer.toString(page));
        params.put("rows", rows);
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);

                String results = (String) o;//从从网络端返回数据
                resultString = "success";
                Log.d("yusdi","ss:"+results);
                JsonResolve.jsonExpressionSearchCount(results, activity, rows);//json解析

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);

                if (strMsg != null) {
                    resultString = "error";
                    Log.d("test", strMsg);
                }
                ExpressNumberManagerActivity.progressDialog.dismiss();
                ExceptionUtil.httpPost("ExpressNumberManagementHttpPost");
            }

        });

        return resultString;
    }

    //快递数量订单添加
    public String addExpressBillingHttp(String httpUrl, String typeSpinnerString, String expressNameSpinnerString, String numeric, String description,
                                      String billingTime) {//账目查询

        //typeSpinnerString,classifySpinnerString,reasonSpinnerString;
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("option", "2");//1查询，2添加，3删除
        params.put("userName", Statics.Name);
        params.put("type", typeSpinnerString);
        params.put("nameId", expressNameSpinnerString);
        params.put("numeric", numeric);
        params.put("description", description);
        params.put("billingTime", billingTime);//billingTime 创建时间
        Log.d("test5","type:"+typeSpinnerString+"@"+"nameId:"+expressNameSpinnerString+"@"+"numeric:"+numeric+"@"+"description:"
                +description+"@"+"billingTime:"+billingTime);
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);

                Log.v("test","Constants.userName::"+Statics.userName);
                String result = (String) o;//从从网络端返回数据
                resultString = "success";
                Log.v("test", "result：" + result);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);

                resultString = "error";
                Log.d("test", strMsg);
                ExceptionUtil.httpPost("ExpressNumberManagementHttpPost");
            }
        });

        return resultString;
    }

    //订单删除删除
    public String delExpressManagerHttp(String httpUrl, String id, final Activity activity) {
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
                String httpUrl = Statics.ExpressCountSearch;
                searchHttp(httpUrl ,"" ,"" ,"",activity,1);//刷新页面
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);

                resultString = "error";
                Log.d("test", strMsg);
                ExceptionUtil.httpPost("ExpressNumberManagementHttpPost");
            }
        });

        return resultString;
    }

    public String expressPersionSearchHttp(String httpUrl) {//expressPersion 业务员姓名
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("httpUrl", httpUrl);
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);

                String result = (String) o;//从从网络端返回数据
                Log.d("express", result);
                resultString = "success";
                Log.v("test","test");
                JsonResolve.jsonExpressPersonAllSearch(result);//json解析
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);

                resultString = "error";
                ExceptionUtil.httpPost("ExpressNumberManagementHttpPost");
            }
        });

        return resultString;
    }

    public String searchExpressYearHttp(String httpUrl) {//年份查询
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        Log.v("test", "httpUrl:" + httpUrl);
        params.put("httpUrl", httpUrl);
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);
                String result = (String) o;//从从网络端返回数据
                Log.d("yearing", result);
                resultString = "success";
                JsonResolve.jsonExpressSearchYear(result);//json解析
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);
            }
        });

        return resultString;
    }

}
