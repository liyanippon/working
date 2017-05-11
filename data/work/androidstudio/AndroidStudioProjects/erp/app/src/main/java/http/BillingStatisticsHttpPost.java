package http;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import Tool.JsonResolve;
import Tool.statistics.ExceptionUtil;
import ui.activity.BillingStatisticsActivity;

/**
 * Created by admin on 2017/3/2.
 */

public class BillingStatisticsHttpPost {
    public static FinalHttp finalHttp;
    public static AjaxParams params = new AjaxParams();
    public static String resultString = "error";

    public String searchTimeHttp(String httpUrl, String year, String type, final Activity activity) {//月份统计查询
        Log.d("test-i",year+"adad"+type);
        if("全部".equals(year)){
            year = "2017";
        }
        if("全部".equals(type)){
            type = "";
        }

        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("httpUrl", httpUrl);
        params.put("year", year);
        params.put("type", type);//快递类别，圆通或韵达 //类型默认为空
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);

                String result = (String) o;//从从网络端返回数据
                Log.d("test1", result);
                resultString = "success";
                JsonResolve.jsonSearchTime(result, activity);//json解析
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);

                BillingStatisticsActivity.progressDialog.dismiss();
                resultString = "error";
                ExceptionUtil.httpPost("BillingStatisticsHttpPost");
            }
        });

        return resultString;
    }

    public String searchYearHttp(String httpUrl) {//年份查询
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        Log.v("test", "httpUrl:" + httpUrl);
        params.put("httpUrl", httpUrl);
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);
                String result = (String) o;//从从网络端返回数据
                Log.d("billing", result);
                resultString = "success";
                JsonResolve.jsonBillingSearchYear(result);//json解析
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);
                Log.d("test1", strMsg);
                resultString = "error";
                ExceptionUtil.httpPost("BillingStatisticsHttpPost");
            }
        });

        return resultString;
    }

    public String searchCustomerHttp(String httpUrl, String year, String type, String month, final Activity activity) {//客户统计查询
        Log.d("test34",year+"**"+month);
        if("全部".equals(year)){
            year = "2017";
        }
        if("全部".equals(type)){
            type = "";
        }
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("httpUrl", httpUrl);
        params.put("year", year);
        params.put("month", month);
        params.put("type", type);//快递类别，圆通或韵达
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);

                String result = (String) o;//从从网络端返回数据
                Log.d("test1", result);
                resultString = "success";
                JsonResolve.jsonSearchCustomer(result, activity);//json解析
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);
                resultString = "error";
                ExceptionUtil.httpPost("BillingStatisticsHttpPost");
            }
        });
        return resultString;
    }

    public String searchXqCustomerHttp(String httpUrl, String year, String type, String month, String customerId, final Activity activity) {//客户统计查询
        Log.d("test34",year+"**"+month);
        if("全部".equals(year)){
            year = "2017";
        }
        if("全部".equals(type)){
            type = "";
        }
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("httpUrl", httpUrl);
        params.put("year", year);
        params.put("month", month);
        params.put("customer_id", customerId);
        Log.d("test1", "customerId:" + customerId);
        Log.d("test1", "month:" + month);
        params.put("type", type);//快递类别，圆通或韵达
        Log.d("test1", "type:" + type);
        Log.d("test1", "httpUrl:" + httpUrl);

        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);

                String result = (String) o;//从从网络端返回数据

                Log.d("test9", "Xq:" + result);
                resultString = "success";
                JsonResolve.jsonSearchXiangxi(result, activity);//json解析
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);
                resultString = "error";
                ExceptionUtil.httpPost("BillingStatisticsHttpPost");
            }
        });
        return resultString;
    }
}
