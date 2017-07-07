package http;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.*;
import java.util.Collections;
import Tool.JsonResolve;
import Tool.statistics.ExceptionUtil;
import Tool.statistics.Statics;
import broadcast.BroadCastTool;
import broadcast.TYPE;
import model.AttendanceStatistics;
import model.ExpressClassify;
import model.TransferAccountClassify;
import model.UserUmp;
import portface.LazyLoadFace;
import ui.activity.ExpressBillingManagementActivity;
import ui.activity.TransferAccountActivity;

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
        finalHttp.configTimeout(20000);// 超时时间
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
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                            break;
                        case "ok":
                            String success = jsonObject.getString("success");
                            String sessionid = jsonObject.getString("sessionid");//在添加账单时会用到
                            Statics.sessionId = sessionid;
                            Statics.results = success;
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

                resultString = strMsg;
                Log.d("test", "sssss:" + strMsg);
                Statics.results = "没有网络";
                ExceptionUtil.httpPost("AccountManagementHttpPost");
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
                JsonResolve.jsonAccountManager(results, activitys, rows);//json解析
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);

                if (strMsg != null) {
                    resultString = "error";
                    Log.d("test", strMsg);
                }
                ExpressBillingManagementActivity.progressDialog.dismiss();
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
                Log.v("test", "Constants.userName::" + Statics.userName);
                String result = (String) o;//从从网络端返回数据
                //Log.d("test",result);
                resultString = "success";
                Log.v("test", "result：" + result);
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

    public String transferAccountHttp(String httpUrl, String classifySpinnerString, String reasonSpinnerString, String sum,
                                      String description, String billingTime ) {

        Log.d("ExpressBillingManagemen", httpUrl + "00" + "00" + classifySpinnerString + "00" + reasonSpinnerString + "00" + sum + "00" + description + "00" + billingTime);
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("id", "");
        params.put("createBy", Statics.Name);
        params.put("updateBy", Statics.Name);
        params.put("option", "2");//1查询，2添加，3删除
        params.put("userName", Statics.Name);
        //params.put("type", typeSpinnerString);
        params.put("classify", classifySpinnerString);
        params.put("reason", reasonSpinnerString);
        params.put("sum", sum);
        params.put("description", description);
        //params.put("customerId", customerId);
        params.put("billingTime", billingTime);//billingTime 创建时间
        params.put("updateTime", billingTime);

        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);
                Log.v("test", "Constants.userName::" + Statics.userName);
                String result = (String) o;//从从网络端返回数据
                //Log.d("test",result);
                resultString = "success";
                Log.v("test", "result：" + result);
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


    public String delAccountManagerHttp(String httpUrl, String id, final Activity activity) {//账目删除
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("option", "3");//1查询，2添加，3删除
        params.put("id", id);
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);
                String result = (String) o;//从从网络端返回数据
                Log.d("test", "delete" + result);
                resultString = "success";
                //刷新页面
                Log.v("test", "notifyDataSetInvalidated");
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

    //转账业务类型
    public String accountTransferSearchHttp(String httpUrl, String classifyId, final Activity activity) {//customerSearchHttp 账目类型//明细
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
                Log.d("转账类型", result);
                resultString = "success";
                //JsonResolve.jsonAccountReasonSearch(result, activity);//json解析
                Statics.transferAccountClassifiesList.clear();
                TransferAccountClassify[] as = new Gson().fromJson(result, TransferAccountClassify[].class);
                Collections.addAll(Statics.transferAccountClassifiesList,as);//转化arrayList
                BroadCastTool.sendMyBroadcast(TYPE.NORMAL,activity,"TransferAccount");
                TransferAccountActivity transferAccountActivity = new TransferAccountActivity();
                transferAccountActivity.AdapterRefresh("transfer");
                Log.d("aleand","发送广播");
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
    /*public String accountClassifySearchHttp(String httpUrl) {//accountClassifySearchHttp 账目类型//进账出账
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("httpUrl", httpUrl);
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);
                String result = (String) o;//从从网络端返回数据
                Log.d("ahj", result);
                resultString = "success";
                Statics.expressClassifyList.clear();
                ExpressClassify[] as = new Gson().fromJson(result, ExpressClassify[].class);
                Collections.addAll(Statics.expressClassifyList,as);//转化arrayList
                //Statics.accountClassifyList.add(accountClassify);
                //JsonResolve.jsonAccountClassifySearch(result);//json解析
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);
                resultString = "error";
            }
        });
        return resultString;
    }*/
    /*public String accountClassifySearchHttp(String httpUrl,String option) {//accountClassifySearchHttp 账目类型//进账出账
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        Log.d("转账", "http" + httpUrl + "&" + option + "&" + Statics.sessionId);
        params.put("httpUrl", httpUrl);
        params.put("option", option);
        //params.put("sessionid",Statics.sessionId);
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);

                String result = (String) o;//从从网络端返回数据
                Log.d("转账", result);
                resultString = "success";
                Statics.expressClassifyList2.clear();
                ExpressClassify[] as = new Gson().fromJson(result, ExpressClassify[].class);
                Collections.addAll(Statics.expressClassifyList2,as);//转化arrayList
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);
                resultString = "error";
            }
        });
        return resultString;
    }*/

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
