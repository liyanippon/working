package com.example.admin.example;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;



/**
 * Created by admin on 2017/3/27.
 */

public class HttpBase {
        public static FinalHttp finalHttp;
        public static AjaxParams params = new AjaxParams();
        public static String resultString = "error";
        private boolean result = false;
        private Context context;
        public static String expressPersionSearchHttp(String httpUrl) {//expressPersion 业务员姓名
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
                                Gson gson = new Gson();
                                JavaBean[] javaBeen = gson.fromJson(result, JavaBean[].class);
                                //for (int i=0;i<javaBeen.length;i++){
                                        for (int j=0;j<javaBeen[0].getData().size();j++){
                                                //Log.v("test","name:"+javaBeen[0].getData().get(j).getName());
                                        }

                                //}
                        }

                        @Override
                        public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                                super.onFailure(t, errorNo, strMsg);

                                resultString = "error";
                        }
                });

                return resultString;
        }

        public static String searchHttp(String httpUrl, String typeSpinnerString, String classifySpinnerString, String reasonSpinnerString, final Activity activity, int page) {//账目查询
                Log.d("test", typeSpinnerString + "@" + classifySpinnerString + "@" + reasonSpinnerString);
                final String rows = "50";//每页显示条数
                Log.v("test3", "searchHttp:" + Integer.toString(page));
                finalHttp = new FinalHttp();
                params = new AjaxParams();
                params.put("option", "1");//1查询，2添加，3删除
                params.put("type", typeSpinnerString);
                params.put("classify", classifySpinnerString);
                params.put("reason", reasonSpinnerString);
                params.put("page", Integer.toString(page));
                params.put("rows", rows);
                Log.d("test", "httpUrl" + httpUrl);
                finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

                        @Override
                        public void onSuccess(Object o) {//网络请求网络请求成功
                                super.onSuccess(o);

                                String results = (String) o;//从从网络端返回数据
                                Log.d("test", "results:" + results);
                                resultString = "success";

                                Gson gson = new Gson();
                                AccountBean[] javaBeen = gson.fromJson(results, AccountBean[].class);
                                for (int j=0;j<javaBeen[0].getData().get(0).getRows().size();j++){
                                        Log.v("test","getId:"+javaBeen[0].getData().get(0).getRows().get(j).getId());
                                        Log.v("test","getClassify:"+javaBeen[0].getData().get(0).getRows().get(j).getClassify());
                                        Log.v("test","getCreateBy:"+javaBeen[0].getData().get(0).getRows().get(j).getCreateBy());
                                        Log.v("test","getCustomerId:"+javaBeen[0].getData().get(0).getRows().get(j).getCustomerId());
                                        Log.v("test","getDescription:"+javaBeen[0].getData().get(0).getRows().get(j).getDescription());
                                        Log.v("test","getReason:"+javaBeen[0].getData().get(0).getRows().get(j).getReason());
                                        Log.v("test","getType:"+javaBeen[0].getData().get(0).getRows().get(j).getType());
                                        Log.v("test","getUpdateBy:"+javaBeen[0].getData().get(0).getRows().get(j).getUpdateBy());
                                }
                                Log.v("ads",Integer.toString(javaBeen[0].getData().get(0).getRows().size()));
                        }

                        @Override
                        public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                                super.onFailure(t, errorNo, strMsg);

                                if (strMsg != null) {
                                        resultString = "error";
                                        Log.d("test", strMsg);
                                }

                        }

                });

                return resultString;
        }



}
