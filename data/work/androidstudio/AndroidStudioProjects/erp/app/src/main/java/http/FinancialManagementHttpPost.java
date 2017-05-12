package http;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import Tool.JsonResolve;
import Tool.statistics.ExceptionUtil;
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

    public FinancialManagementHttpPost(){

    }
    public FinancialManagementHttpPost(Context context){
        this.context =context;
    }
    public String searchHttp(String httpUrl, String typeSpinnerString, String classifySpinnerString, String reasonSpinnerString, final Activity activity, int page) {//账目查询
        Log.d("search",typeSpinnerString+"@"+classifySpinnerString+"@"+reasonSpinnerString);
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
                //JsonResolve.jsonAccountManager(results, activity, rows);//json解析
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
                Log.d("testst","kkkkk");
                FinancialBillingManagementActivity.progressDialog.dismiss();
                ExceptionUtil.httpPost("FinancialManagementHttpPost");
            }

        });

        return resultString;
    }
}
