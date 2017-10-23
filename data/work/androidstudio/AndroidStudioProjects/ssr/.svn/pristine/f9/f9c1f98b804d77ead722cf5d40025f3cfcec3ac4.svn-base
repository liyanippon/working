package http;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONArray;
import org.json.JSONException;

import Tool.JsonResolve;
import Tool.statistics.ExceptionUtil;
import Tool.statistics.Statics;
import broadcast.BroadCastTool;
import broadcast.Config;
import broadcast.TYPE;
import ui.activity.ExpressStatisticsActivity;

/**
 * Created by admin on 2017/3/2.
 */

public class ExpressStatisticsHttpPost {
    public static FinalHttp finalHttp;
    public static AjaxParams params = new AjaxParams();
    public static String resultString = "error";
    private Intent intent;
    private Context context;

    public String searchTimeHttp(String httpUrl, String year, String type, final Activity activity) {//月份统计查询
        Log.d("test","------------------------------------");
        Log.d("test9","s"+year+type);
        if("全部".equals(year)){
            year = "2017";
        }
        if("全部".equals(type)){
            type = "";
        }
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("httpUrl", httpUrl);
        params.put("year", year);//传一个值
        params.put("type", type);
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);

                String result = (String) o;//从从网络端返回数据
                Log.d("expressing", "express:"+result);
                resultString = "success";
                JsonResolve.jsonExpressSearchTime(result, activity);//json解析

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);
                ExpressStatisticsActivity.progressDialog.dismiss();
                resultString = "error";
                ExceptionUtil.httpPost("ExpressStatisticsHttpPost");
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
                Log.d("test1", result);
                resultString = "success";
                JsonResolve.jsonExpressSearchYear(result);//json解析
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);
                Log.d("test1", strMsg);
                resultString = "error";
                ExceptionUtil.httpPost("ExpressStatisticsHttpPost");
            }
        });

        return resultString;
    }

    public String searchExpressPersonStatisticsHttp(String httpUrl, String year, String type, String month, final Activity activity) {//快递员统计及信息查询
        Log.v("test6","---------");
        Log.v("test6",year+"ss"+type+"dd"+month+"ss");
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
                Log.d("expressTongji", result);
                resultString = "success";
                JsonResolve.jsonSearchExpressPerson(result, activity);//json解析
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);
                resultString = "error";
                ExceptionUtil.httpPost("ExpressStatisticsHttpPost");
            }
        });
        return resultString;
    }

    public String searchXqExpressPersonHttp(String httpUrl, String year, String type, String month, String expressPersonId, final Context context) {//客户统计查询

        Log.d("testmss",year+"&"+type+"&"+month+"&"+expressPersonId);
        if("全部".equals(year)){
            year = "2017";
        }
        if("全部".equals(type)){
            type = "";
        }
        this.context=context;
        Log.v("test1", "----test----");
        Log.v("month", month+year);
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("httpUrl", httpUrl);
        params.put("year", year);
        params.put("month", month);
        params.put("name_id", expressPersonId);
        Log.d("test1", "name_id:" + expressPersonId);
        Log.d("test1", "month:" + month);
        params.put("type", type);//快递类别，圆通或韵达
        Log.d("test1", "type:" + type);
        Log.d("test1", "httpUrl:" + httpUrl);

        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);

                String result = (String) o;//从从网络端返回数据

                Log.d("longfei", "Xq:" + result);
                resultString = "success";
                JsonResolve.jsonSearchExpressPersonStatisticXiangxi(result);//json解析
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);
                resultString = "error";
                ExceptionUtil.httpPost("ExpressStatisticsHttpPost");
            }
        });
        return resultString;
    }


    public String searchDayCurrentMonth(String httpUrl, String year, String month) {//查询当月有多少天
        Log.d("test00",year+"&"+month);
        if("全部".equals(year)){
            year = "2017";
        }

        finalHttp = new FinalHttp();
        finalHttp.configRequestExecutionRetryCount(10);// 请求错误重试次数
        finalHttp.configTimeout(20000);// 超时时间
        params = new AjaxParams();
        params.put("httpUrl", httpUrl);
        params.put("year", year);
        params.put("month", month);
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);
                String result = (String) o;//从从网络端返回数据

                Log.d("testt",result);
                try {
                    JSONArray jsonArray =new JSONArray(result);
                    Statics.Xday = new String[jsonArray.length()];
                    for(int i=0;i<jsonArray.length();i++){
                        Statics.Xday[i]=jsonArray.get(i).toString();
                        Log.v("test6","day:"+Statics.Xday[i]);
                    }

                    //sendMyBroadcast(TYPE.NORMAL,"monthDay");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                resultString = "success";
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);

                resultString = "error";
                ExceptionUtil.httpPost("ExpressStatisticsHttpPost");
            }
        });
        return resultString;
    }
    public String searchDayCurrentMonthPieceCount(String httpUrl, String year, String month, String type , final Context context) {//查询哪天有快递
        if("全部".equals(year)){
            year = "2017";
        }
        if("全部".equals(type)){
            type = "";
        }
        this.context = context;
        finalHttp = new FinalHttp();
        finalHttp.configRequestExecutionRetryCount(10);// 请求错误重试次数
        finalHttp.configTimeout(20000);// 超时时间
        params = new AjaxParams();
        params.put("httpUrl", httpUrl);
        params.put("year", year);
        params.put("month", month);
        params.put("type",type);

        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);
                String result = (String) o;//从从网络端返回数据
                Log.d("countpiece", "piece"+result);
                resultString = "success";
                if(JsonResolve.jsonSearchPieceCountMonth(result)){
                    //发送广播到fragment
                    BroadCastTool.sendMyBroadcast(TYPE.NORMAL,context,"express");
                }

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);

                resultString = "error";
                ExceptionUtil.httpPost("ExpressStatisticsHttpPost");
            }
        });
        return resultString;
    }

    public String searchPersonDayCurrentMonthPieceCount(String httpUrl, String year, String month, String expressPersonId , final Context context) {//查询业务员哪天有快递
        if("全部".equals(year)){
            year = "2017";
        }
        Log.d("wushuju",year+"*"+month+"*"+expressPersonId +"url:"+httpUrl);
        this.context = context;
        finalHttp = new FinalHttp();
        finalHttp.configRequestExecutionRetryCount(10);// 请求错误重试次数
        finalHttp.configTimeout(20000);// 超时时间
        params = new AjaxParams();
        params.put("httpUrl", httpUrl);
        params.put("year", year);
        params.put("month", month);
        params.put("name_id",expressPersonId);

        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);
                String result = (String) o;//从从网络端返回数据
                Log.d("wushuju", "apiece::"+result);
                resultString = "success";

                if(JsonResolve.jsonSearchPersonMonthPiece(result)){
                    //发送广播到fragment
                    BroadCastTool.sendMyBroadcast(TYPE.NORMAL,context,"PersonXq");
                }

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);

                resultString = "error";
                ExceptionUtil.httpPost("ExpressStatisticsHttpPost");
            }
        });
        return resultString;
    }
}
