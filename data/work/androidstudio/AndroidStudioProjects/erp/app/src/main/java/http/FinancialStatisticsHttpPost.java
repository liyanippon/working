package http;

import android.app.Activity;
import android.util.Log;
import com.google.gson.Gson;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import java.util.Collections;
import Tool.JsonResolve;
import Tool.statistics.ExceptionUtil;
import Tool.statistics.Statics;
import model.FinancialBilingGetXWstatisticalData;
import model.FinancialBillingGetWXSelectMonthAccount;
import model.FinancialBillingGetWXsettlementMonth;
import model.FinancialManagement;
import ui.activity.BillingStatisticsActivity;
import ui.activity.FinancialBillingManagementActivity;
import ui.activity.FinancialStastisticsActivity;
import ui.adpter.MonthXiangxiBillingStatisticsAdapter;

/**
 * Created by admin on 2017/3/2.
 */

public class FinancialStatisticsHttpPost {
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
        //params.put("year", year);
        //params.put("type", type);
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);

                String result = (String) o;//从从网络端返回数据
                resultString = "success";
                Log.d("FinancialStatisticsHttp", "财务分析数据:"+result);
                //json数据使用Gson框架解析
                Statics.fbgwxSettlementMonthList.clear();
                FinancialBillingGetWXsettlementMonth[] fbgxwd = new Gson().fromJson(result, FinancialBillingGetWXsettlementMonth[].class);
                Collections.addAll(Statics.fbgwxSettlementMonthList,fbgxwd);//转化arrayList
                Log.d("FinancialStatisticsHttp", "测试" + Statics.fbgwxSettlementMonthList.size());
                FinancialStastisticsActivity fsa = new FinancialStastisticsActivity();
                fsa.AdapterRefresh("timeAdapter");
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);
                FinancialStastisticsActivity.progressDialog.dismiss();
            }
        });

        return resultString;
    }

    public String searchCurrentMoneyHttp(String httpUrl) {//获取当前金额情况
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("httpUrl", httpUrl);
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);
                String result = (String) o;//从从网络端返回数据
                resultString = "success";
                Statics.CurrentMoney = result;
                Log.d("FinancialStatisticsHttp", "查看当前资金情况：" + result);
                //json数据使用Gson框架解析
                FinancialStastisticsActivity fsa = new FinancialStastisticsActivity();
                fsa.AdapterRefresh("currentMoney");
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);
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
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);
            }
        });
        return resultString;
    }

    //月份账目明细信息查询
    public String searchXqMonthBillHttp(String httpUrl, String year, String type, String month) {

        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("httpUrl", httpUrl);
        params.put("year", year);
        params.put("month", month);
        params.put("type", type);

        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);

                String result = (String) o;//从从网络端返回数据
                resultString = "success";
                Log.d("FinancialStatisticsHttp", "月份账目明细：" + result);

                //json数据使用Gson框架解析
                Statics.fbgwxsmaList.clear();
                FinancialBillingGetWXSelectMonthAccount[] mxbsa = new Gson().fromJson(result, FinancialBillingGetWXSelectMonthAccount[].class);
                Collections.addAll(Statics.fbgwxsmaList,mxbsa);//转化arrayList
                Log.d("FinancialStatisticsHttp", "测试" + Statics.fbgwxsmaList.size());
                FinancialStastisticsActivity fsa = new FinancialStastisticsActivity();
                fsa.AdapterRefresh("monthXiangXiAdapter");
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);
            }
        });
        return resultString;
    }
}
