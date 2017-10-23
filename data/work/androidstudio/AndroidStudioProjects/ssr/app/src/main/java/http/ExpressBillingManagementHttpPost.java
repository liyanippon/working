package http;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.admin.erp.MainActivity;
import com.google.gson.Gson;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.*;
import java.util.ArrayList;
import java.util.Collections;

import Tool.ACache;
import Tool.JsonResolve;
import Tool.statistics.ExceptionUtil;
import Tool.statistics.Statics;
import broadcast.BroadCastTool;
import broadcast.TYPE;
import model.javabean.ExpressManagement;
import model.javabean.UserUmp;
import portface.LazyLoadFace;
import ui.activity.ExpressBillingManagementActivity;

/**
 * Created by admin on 2017/2/21.
 */

public class ExpressBillingManagementHttpPost {
    public static FinalHttp finalHttp;
    public static AjaxParams params = new AjaxParams();
    public static String resultString = "error";
    private boolean result = false;
    private Context context;
    private Activity activitys ;
    private LazyLoadFace lazyLoad;
    public ExpressBillingManagementHttpPost() {
    }
    public ExpressBillingManagementHttpPost(Context context) {
        this.context = context;
    }
    public String LoginHttp(String httpUrl, final String username, String password, final Activity activity) {//登录验证
        Log.d("uml", httpUrl);
        finalHttp = new FinalHttp();
        finalHttp.configRequestExecutionRetryCount(7);// 请求错误重试次数
        finalHttp.configTimeout(5000);// 超时时间
        params = new AjaxParams();
        params.put("httpUrl", httpUrl);
        params.put("userName", username);
        params.put("password", password);
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);
                String result = (String) o;//从从网络端返回数据
                Log.v("tests", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String level = jsonObject.getString("level");
                    String message = null;
                    switch (level) {
                        case "error":
                            message = jsonObject.getString("message");
                            Statics.results = message;
                            Log.v("test", "message");
                            MainActivity.reset.setClickable(true);
                            MainActivity.login.setClickable(true);
                            MainActivity.userName.setEnabled(true);
                            MainActivity.userPassword.setEnabled(true);
                            MainActivity.loginProgressBar.setVisibility(View.GONE);
                            MainActivity.login.setBackgroundColor(Color.rgb(0x05,0x6d,0xaa));
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                            BroadCastTool.sendMyBroadcast(TYPE.NORMAL, context, "error");//发送广播
                            break;
                        case "ok":
                            String success = jsonObject.getString("success");
                            String sessionid = jsonObject.getString("sessionid");//在添加账单时会用到
                            Statics.sessionId = sessionid;
                            Statics.results = success;
                            Statics.Name = username;
                            UmlHttp(Statics.UmlUrl, sessionid, username);
                            Log.d("uml", sessionid);
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);
                MainActivity.reset.setClickable(true);
                MainActivity.userName.setEnabled(true);
                MainActivity.userPassword.setEnabled(true);
                MainActivity.login.setClickable(true);
                MainActivity.login.setBackgroundColor(Color.rgb(0x05,0x6d,0xaa));
                Toast.makeText(context, "网络有问题，请检查连接", Toast.LENGTH_LONG).show();
                MainActivity.loginProgressBar.setVisibility(View.GONE);
            }
            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
                MainActivity.loginProgressBar.setProgress((int)current);
                Log.d("ExpressBillingManagemen", "总进度::" + count +"当前：："+ current);
            }
        });
        return "success";
    }

    public String UmlHttp(String httpUrl, final String sessionid, String username) {//登录验证
        Log.d("uml", httpUrl+"sss");
        finalHttp = new FinalHttp();
        finalHttp.configRequestExecutionRetryCount(7);// 请求错误重试次数
        finalHttp.configTimeout(20000);// 超时时间
        params = new AjaxParams();
        params.put("httpUrl", httpUrl);
        params.put("sessionid", sessionid);
        Log.d("uml", "username:" + username);
        params.put("user_id", username);
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);

                String result = (String) o;//从从网络端返回数据
                Log.v("uml", "asdf::" + result);
                //json数据使用Gson框架解析
                Statics.userUmpsStatisticsList.clear();
                UserUmp[] as = new Gson().fromJson(result, UserUmp[].class);
                Collections.addAll(Statics.userUmpsStatisticsList, as);//转化arrayList
                /*//缓存本地
                ACache mCache = ACache.get(context);
                //只能使用List的子类
                mCache.put("uml", Statics.userUmpsStatisticsList,7 * ACache.TIME_DAY);*/

                BroadCastTool.sendMyBroadcast(TYPE.NORMAL, context, "login");//发送广播
            }
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);
                resultString = strMsg;
                Log.d("test", "sssss:" + strMsg);
                Statics.results = "没有网络";
                ExceptionUtil.httpPost("AccountManagementHttpPost");
            }
        });
        return "success";
    }

    public void AdapterRefresh(LazyLoadFace lazyLoadFace) {
        lazyLoad = lazyLoadFace;
        Log.d("test", Boolean.toString(lazyLoad == null));
    }
    public String searchHttp(String httpUrl, String typeSpinnerString, String classifySpinnerString, String reasonSpinnerString,Activity activity, int page) {//账目查询
        Log.d("search", typeSpinnerString + "@" + classifySpinnerString + "@" + reasonSpinnerString);
        Log.d("lll","请求次数");
        activitys = activity;
        if ("全部".equals(typeSpinnerString)) {
            typeSpinnerString = "";
        }
        if ("全部".equals(classifySpinnerString)) {
            classifySpinnerString = "";
        }
        if ("全部".equals(reasonSpinnerString)) {
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
                Log.d("jizhang", "results:" + results);
                resultString = "success";
                //JsonResolve.jsonAccountManager(results, activitys, rows);//json解析
                //使用框架
                //Statics.financialCustomersList.clear();
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(results);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                    JSONObject jsonObject1 = jsonArray1.getJSONObject(0);
                    String total = jsonObject1.get("total").toString();
                    Statics.page = (Integer.parseInt(total) + Integer.parseInt(rows) - 1) / Integer.parseInt(rows);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Statics.expressManagementList.clear();
                ArrayList<ExpressManagement> temp =new ArrayList<>();
                ExpressManagement[] fc = new Gson().fromJson(results, ExpressManagement[].class);
                //Collections.addAll(Statics.expressManagementList,fc);//转化arrayList
                Collections.addAll(temp,fc);//转化arrayList
                if(Statics.isPageUpload){//如果是翻页动作，则不清除以前的数据
                    Log.d("ExpressBillingManagemen", "翻页，数"+Statics.expressManagementList.get(0).getData().get(0).getRows().size());
                    Log.d("ExpressBillingManagemen", "翻页，数1:"+temp.get(0).getData().get(0).getRows().size());
                    Statics.expressManagementList.get(0).getData().get(0).getRows().addAll(fc[0].getData().get(0).getRows());
                    Log.d("ExpressBillingManagemen", "翻页，数"+Statics.expressManagementList.get(0).getData().get(0).getRows().size());

                }else {
                    Statics.expressManagementList.clear();
                    Statics.expressManagementList = temp;
                }
                Statics.isPageUpload = false;
                ExpressBillingManagementActivity.AdapterRefresh("accountManagementAdapter");
                //回调接口

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);

                if (strMsg != null) {
                    resultString = "error";
                    Log.d("test", strMsg);
                }
                if(ExpressBillingManagementActivity.progressDialog!=null){
                    ExpressBillingManagementActivity.progressDialog.dismiss();
                }
            }
        });
        return resultString;
    }
    public String addCountManagerHttp(String httpUrl, String typeSpinnerString, String classifySpinnerString, String reasonSpinnerString, String sum,
                                      String description, String customerId, String billingTime ,String payMethodString) {
        Log.d("addmm", "添加账单:" + typeSpinnerString + "/"
                + classifySpinnerString +
                "/" + reasonSpinnerString +
                "/" + sum + "/" + description +
                "/" + customerId +
                "/" + billingTime+
                "/" + payMethodString
        );
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        Log.d("ffff", "xxx" + Statics.Name);
        params.put("id", "");
        params.put("createBy", Statics.Name);
        params.put("updateBy", Statics.Name);
        params.put("option", "2");//1查询，2添加，3删除
        //params.put("id","1212"); //Id不用传
        params.put("userName", Statics.Name);
        params.put("type", typeSpinnerString);
        params.put("classify", classifySpinnerString);
        params.put("reason", reasonSpinnerString);
        params.put("sum", sum);
        params.put("description", description);
        params.put("customerId", customerId);
        params.put("billingTime", billingTime);//billingTime 创建时间
        params.put("updateTime", billingTime);
        params.put("paymentMethod",payMethodString);//支付方式
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);
                String result = (String) o;//从从网络端返回数据
                //Log.d("test",result);
                resultString = "success";
                Log.v("test", "result：" + result);
                ExpressBillingManagementActivity.isAdd = true;
            }
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);
                resultString = "error";
                Log.d("ExpressBillingManagemen", "添加失败" + strMsg);
            }
        });
        return resultString;
    }

    public String delAccountManagerHttp(String httpUrl, String id ,String sum, String classify, String paymentMethod, final Activity activity) {
        //账目删除 分类，支付方式金额
        Log.d("deletesss", "9id" + id);
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("option", "3");//1查询，2添加，3删除
        params.put("id", id);
        params.put("delete","yes");
        params.put("sum",sum);
        params.put("classify",classify);
        params.put("paymentMethod",paymentMethod);
        Log.d("ExpressBillingManagemen", "参数输入：" + id + "," + sum + "," + classify + "," + paymentMethod);
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);
                String result = (String) o;//从从网络端返回数据
                Log.d("deletesss", "delete" + result);
                resultString = "success";
                //刷新页面
                String httpUrl = Statics.FinancialBillingManagementSearchUrl;
                searchHttp(httpUrl, "", "", "", activity, 1);//刷新页面

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
    //下拉菜单
    public String customerSearchHttp(String httpUrl) {//customerSearchHttp 顾客
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("httpUrl", httpUrl);
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);
                String result = (String) o;//从从网络端返回数据
                Log.d("jijijij", result);
                resultString = "success";
                JsonResolve.jsonCustomerAllSearch(result);//json解析
            }
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);

                resultString = "error";
                ExceptionUtil.httpPost("AccountManagementHttpPost");
            }
        });
        return resultString;
    }
    public String accountReasonSearchHttp(String httpUrl, String classifyId, final Activity activity) {//customerSearchHttp 账目类型//明细
        Log.d("tesq9", "classifyId:" + classifyId);
        /*if (Statics.accountClassifyList.size() == 0) {
            return null;
        }
        if ("全部".equals(classifyId)) {//以进账为默认
            classifyId = Statics.accountClassifyList.get(1).getId();
        }*/
        if ("全部".equals(classifyId)) {//以进账为默认
            classifyId = Statics.expressClassifyList.get(0).getData().get(1).getId();
        }
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("httpUrl", httpUrl);
        params.put("id", classifyId);
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);
                String result = (String) o;//从从网络端返回数据
                Log.d("menu", result);
                resultString = "success";
                JsonResolve.jsonAccountReasonSearch(result, activity);//json解析
            }
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);
                resultString = "error";
                ExceptionUtil.httpPost("AccountManagementHttpPost");
            }
        });
        return resultString;
    }

    public String accountTypeSearchHttp(String httpUrl) {//accountTypeSearchHttp 快递类型 圆通韵达
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("httpUrl", httpUrl);
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);
                String result = (String) o;//从从网络端返回数据
                Log.d("menu", result);
                resultString = "success";
                JsonResolve.jsonAccountTypeSearch(result);//json解析
            }
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);
                resultString = "error";
                ExceptionUtil.httpPost("AccountManagementHttpPost");
            }
        });
        return resultString;
    }
}
