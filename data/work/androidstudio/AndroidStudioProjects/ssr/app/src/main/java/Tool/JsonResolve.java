package Tool;

import android.app.Activity;
import android.util.Log;

import com.example.admin.erp.MainActivity;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import Tool.statistics.AchacheConstant;
import Tool.statistics.Statics;
import broadcast.BroadCastTool;
import broadcast.TYPE;
import model.javabean.AccountReason;
import model.javabean.AccountType;
import model.javabean.Customer;
import model.javabean.CustomerBillingStatistics;
import model.javabean.ExpressNumberManagement;
import model.javabean.ExpressPerson;
import model.javabean.ExpressPersonStatistic;
import model.javabean.ExpressPersonStatisticsXiangqing;
import model.javabean.ExpressPieceCountMonth;
import model.javabean.TimeBillingStatistics;
import model.javabean.TimeExpressStatistics;
import model.javabean.XiangxiBillingStatistics;
import ui.activity.AddExpressBillingManagerActivity;
import ui.activity.BillingStatisticsActivity;
import ui.activity.ExpressBillingManagementActivity;
import ui.activity.ExpressNumberManagerActivity;
import ui.activity.ExpressStatisticsActivity;
import ui.activity.LogisticsReportActivity;
import ui.fragement.ExpressPiecesDetailsChartsFragementActivity;


/**
 * Created by admin on 2017/2/21.
 */

public class JsonResolve {
    final static int[] sizeTable = {9, 99, 999, 9999, 99999, 999999, 9999999,
            99999999, 999999999, Integer.MAX_VALUE};
    static ACache aCache;
    public static void jsonAccountReasonSearch(String json, Activity activity) {//详细
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            Statics.accountReasonList.clear();
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("name");
                AccountReason accountReason = new AccountReason(id, name);
                Statics.accountReasonList.add(accountReason);
                accountReason = null;
                Log.v("test2", "name:" + name);
                Log.v("test2", "------------");
            }
            //刷新
            Statics.data_list.clear();
            for (int i = 0; i < Statics.accountReasonList.size(); i++) {
                Statics.data_list.add(Statics.accountReasonList.get(i).getName());
                Log.v("test2", "data_list1:" + Statics.accountReasonList.get(i).getName());

            }
            if(Statics.ActivityType!=null&&!Statics.ActivityType.equals("")){
                if(Statics.ActivityType.equals("addExpress")){//更新add添加页面
                    BroadCastTool.sendMyBroadcast(TYPE.NORMAL,activity,"addReasonSpinner");
                    //AddExpressBillingManagerActivity addExpressBillingManagerActivity = new AddExpressBillingManagerActivity();
                    AddExpressBillingManagerActivity.AdapterRefresh("reasonSpinner");
                    Statics.ActivityType = "";
                    Log.d("aleand","发送广播");

                }else if(Statics.ActivityType.equals("ExpressBillingManagementActivity")){//更新search显示检索页面
                    BroadCastTool.sendMyBroadcast(TYPE.NORMAL,activity,"SearchReasonSpinner");
                    //ExpressBillingManagementActivity expressBillingManagementActivity = new ExpressBillingManagementActivity();
                    ExpressBillingManagementActivity.AdapterRefresh("reasonSpinner");

                    Log.d("aleand","发送广播");
                    Statics.ActivityType = "";
                }else if(Statics.ActivityType.equals("LogisticsReportActivity")){
                    Log.d("kl3kl","二级联动");
                    for (int i = 0; i < Statics.accountReasonList.size(); i++) {
                        Statics.data_list.add(Statics.accountReasonList.get(i).getName());
                        Log.v("ki9", "sssss:" + Statics.accountReasonList.get(i).getName());

                    }
                    BroadCastTool.sendMyBroadcast(TYPE.NORMAL,activity,"SearchReasonSpinner1");
                    //ExpressBillingManagementActivity expressBillingManagementActivity = new ExpressBillingManagementActivity();
                    LogisticsReportActivity.AdapterRefresh("reasonSpinner");
                    Log.d("aleand","发送广播");
                    Statics.ActivityType = "";
                }
            }


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public static void jsonAccountTypeSearch(String json,Activity Activity) {//快递类型 圆通韵达
        ArrayList<AccountType> accountTypeList;
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            //Statics.accountTypeList.clear();
            accountTypeList = new ArrayList<>();
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("name");
                AccountType accountType = new AccountType(id, name);
                accountTypeList.add(accountType);
                accountType = null;
            }
            //保存到缓存当中
            aCache = ACache.get(Activity);
            aCache.put(AchacheConstant.ACCOUNT_TYPE_LIST,accountTypeList,1 * ACache.TIME_DAY);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void jsonCustomerAllSearch(String json) {//快递类型 圆通韵达
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            Statics.customerList.clear();

            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("name");
                Customer customer = new Customer(id, name);
                Statics.customerList.add(customer);
                customer = null;
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //json解析 账单统计
    public static void jsonSearchTime(String json, Activity activity,String activityType) {
        Log.d("JsonResolve", json + "sss");
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            Statics.timeBillingStatisticsList.clear();
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String income = jsonObject1.getString("jz1");
                String imbanlance = jsonObject1.getString("ce");
                String outcome = jsonObject1.getString("cz1");
                //String type = jsonObject1.getString("type");
                String year = jsonObject1.getString("ye");
                String month = jsonObject1.getString("mon");
                TimeBillingStatistics timeBillingStatistics = new TimeBillingStatistics(month, income, outcome, imbanlance);
                Statics.timeBillingStatisticsList.add(timeBillingStatistics);
                timeBillingStatistics = null;
            }
            //刷新异步刷新
            //BillingStatisticsActivity billingStatisticsActivity = new BillingStatisticsActivity();
            if(activityType.equals("LogisticsReportActivity")){
                Log.d("JsonResolve", "LogisticsReportActivity");
                LogisticsReportActivity.AdapterRefresh("timeAdapter");
            }else{
                BillingStatisticsActivity.AdapterRefresh("timeAdapter");
                Log.d("JsonResolve", "BillingStatisticsActivity");
            }
            //BillingStatisticsActivity.timeAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //没有数据时处理
            Statics.timeBillingStatisticsList.clear();
            if(activityType.equals("LogisticsReportActivity")){
                Log.d("JsonResolve", "LogisticsReportActivity");
                LogisticsReportActivity.AdapterRefresh("timeAdapter");
            }else{
                BillingStatisticsActivity.AdapterRefresh("timeAdapter");
                Log.d("JsonResolve", "BillingStatisticsActivity");
            }
        }
    }

    public static void jsonExpressSearchYear(String json) {
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            Statics.expressYear.clear();

            for (int i = 0; i < jsonArray1.length(); i++) {
                Statics.expressYear.add(jsonArray1.get(i).toString());
                Log.d("tes99","oni:::"+jsonArray1.get(i).toString());
                Log.d("tes99","Constants:::"+Statics.expressYear.get(i));
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void jsonBillingSearchYear(String json) {
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            Statics.billingYear.clear();

            for (int i = 0; i < jsonArray1.length(); i++) {
                Statics.billingYear.add(jsonArray1.get(i).toString());
                Log.d("tes99","oni:::"+jsonArray1.get(i).toString());
                Log.d("tes99","Constants:::"+Statics.billingYear.get(i));
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static boolean jsonSearchPieceCountMonth(String json) {
        ExpressPiecesDetailsChartsFragementActivity epdcfAction =new ExpressPiecesDetailsChartsFragementActivity();
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            Statics.expressPieceCountMonthsList.clear();
            for (int i=0;i<jsonArray1.length();i++){
                JSONObject jsonObject2 =jsonArray1.getJSONObject(i);
                String month = jsonObject2.getString("month");
                String sum =  jsonObject2.getString("sum");
                String day =  jsonObject2.getString("day");
                ExpressPieceCountMonth expressPieceCountMonth =new ExpressPieceCountMonth(month,sum,day);
                Statics.expressPieceCountMonthsList.add(expressPieceCountMonth);
                Log.v("sum",sum);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return true;
    }

    public static boolean jsonSearchPersonMonthPiece(String json) {
        ExpressPiecesDetailsChartsFragementActivity epdcfAction =new ExpressPiecesDetailsChartsFragementActivity();
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            Statics.epmsXList=new String[jsonArray1.length()];
            Log.d("adad",jsonArray1.get(0).toString());
            for (int i=0;i<jsonArray1.length();i++){
                Statics.epmsXList[i]=jsonArray1.get(i).toString();
                Log.d("yui",Statics.epmsXList[i]);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return true;
    }


    public static void jsonSearchCustomer(String json, Activity activity) {
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String result = jsonObject.getString("message");
            if ("失败！".equals(result)) {//没有数据时的处理
                Statics.customerBillingStatisticsArrayList.clear();
                //BillingStatisticsActivity billingStatisticsActivity = new BillingStatisticsActivity();
                BillingStatisticsActivity.AdapterRefresh("customerAdapter");
                return;
            }
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            Statics.customerBillingStatisticsArrayList.clear();
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String income = jsonObject1.getString("jz1");
                String imbanlance = jsonObject1.getString("ce");
                String outcome = jsonObject1.getString("cz1");
                //String type = jsonObject1.getString("type");
                String month = jsonObject1.getString("mon");
                String customer = jsonObject1.getString("name");
                String id = jsonObject1.getString("id");
                CustomerBillingStatistics customerBillingStatistics = new CustomerBillingStatistics(id, month, customer, income, outcome, imbanlance);
                Statics.customerBillingStatisticsArrayList.add(customerBillingStatistics);
                customerBillingStatistics = null;
            }
            //刷新异步刷新
            //BillingStatisticsActivity billingStatisticsActivity = new BillingStatisticsActivity();
            if("LogisticsReportActivity".equals(Statics.ActivityType)){
                Log.d("klkl","进入"+json);
                LogisticsReportActivity.AdapterRefresh("customerAdapter");
                Statics.ActivityType = "";
            }else {
                Log.d("klkl","进入x"+json);
                BillingStatisticsActivity.AdapterRefresh("customerAdapter");
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void jsonSearchXiangxi(String json, Activity activity) {
        Log.d("JsonResolve", "详细" + json);
            Statics.xiangxiBillingStatisticsArrayList.clear();
            XiangxiBillingStatistics[] as = new Gson().fromJson(json, XiangxiBillingStatistics[].class);
            Collections.addAll(Statics.xiangxiBillingStatisticsArrayList,as);//转化arrayList

            if("LogisticsReportActivity".equals(Statics.ActivityType)){
                LogisticsReportActivity.AdapterRefresh("xiangxiAdapter");
                Statics.ActivityType = "";
            }else{
                BillingStatisticsActivity.AdapterRefresh("xiangxiAdapter");
            }

        /*} catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }

    //业务员揽件量
    public static void jsonExpressionSearchCount(String json, Activity activity, String rows) {
        ExpressNumberManagerActivity expressNumberManagerActivity = new ExpressNumberManagerActivity();
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            JSONObject jsonObject1 = jsonArray1.getJSONObject(0);
            String total = jsonObject1.get("total").toString();
            Statics.page = (Integer.parseInt(total) + Integer.parseInt(rows) - 1) / Integer.parseInt(rows);
            JSONArray jsonArray2 = jsonObject1.getJSONArray("rows");
            Log.d("JsonResolve", "详细信息：" + json);
            //Statics.enmList.clear();
            ArrayList<ExpressNumberManagement> temps =new ArrayList<>();
            for (int k = 0; k < jsonArray2.length(); k++) {
                JSONObject jsonObject2 = jsonArray2.getJSONObject(k);
                String id = jsonObject2.getString("id");
                String type = jsonObject2.getString("type");
                Log.d("JsonResolve", "json:::" + k + "oop" + type);
                String expresscount = jsonObject2.getString("numeric");//在刷新时返回的json数据没有这个字段
                String expressName = jsonObject2.getString("nameId");//name
                String remark = jsonObject2.getString("description");
                JSONObject jsonObject3 = jsonArray2.getJSONObject(k);
                JSONObject jsonObject4 = jsonObject3.getJSONObject("billingTime");
                String billingtime = jsonObject4.getString("time");
                String month = jsonObject4.getString("month");
                String day = jsonObject4.getString("date");
                String year = jsonObject4.getString("year");
                Log.d("test4", "billingTime" + billingtime);
                Log.d("test45", "ddd" + billingtime);
                //----------------------------------------------------------------------------------
                year = ToolUtils.timeDateFormat(year);
                StringBuffer sb = new StringBuffer();
                int temp = Integer.parseInt(month);

                sb.append(year).append("-").append(++temp).append("-").append(day);

                ExpressNumberManagement expressNumberManagement = new ExpressNumberManagement(id, expressName, type, expresscount, sb.toString(), remark);
                //Statics.enmList.add(expressNumberManagement);
                temps.add(expressNumberManagement);
                expressNumberManagement = null;
            }
            //temps = Statics.enmList;
            if(Statics.isPageUpload){//如果是翻页动作，则不清除以前的数据
                /*Log.d("ExpressBillingManagemen", "翻页，数"+Statics.expressManagementList.get(0).getData().get(0).getRows().size());
                Log.d("ExpressBillingManagemen", "翻页，数1:"+temp.get(0).getData().get(0).getRows().size());
                Statics.expressManagementList.get(0).getData().get(0).getRows().addAll(fc[0].getData().get(0).getRows());
                Log.d("ExpressBillingManagemen", "翻页，数"+Statics.expressManagementList.get(0).getData().get(0).getRows().size());*/
                Statics.enmList.addAll(temps);
                temps.clear();

            }else {
                Statics.enmList.clear();
                Statics.enmList = temps;

            }
            Statics.isPageUpload = false;
            //刷新
            expressNumberManagerActivity.AdapterRefresh("accountManagementAdapter");

        }
        catch (JSONException e) {
            e.printStackTrace();
            Log.d("JsonResolve", "没有返回numeric这个字段");
        }

    }

    public static void jsonExpressPersonAllSearch(String json) {//获取快递员姓名
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            Statics.expressPersonsList.clear();
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("name");
                ExpressPerson expressPerson = new ExpressPerson(id, name);
                Statics.expressPersonsList.add(expressPerson);
                expressPerson = null;
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    //json解析 快递月份统计
    public static void jsonExpressSearchTime(String json, Activity activity) {
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            Statics.expressTimeList.clear();
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String month = jsonObject1.getString("month");
                String year = jsonObject1.getString("year");
                String sum = jsonObject1.getString("sum");
                TimeExpressStatistics timeBillingStatistics = new TimeExpressStatistics(month, year, sum);
                Statics.expressTimeList.add(timeBillingStatistics);
                timeBillingStatistics = null;
            }
            //刷新异步刷新

            //ExpressStatisticsActivity expressStatisticsActivity = new ExpressStatisticsActivity();
            ExpressStatisticsActivity.AdapterRefresh("timeAdapter");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void jsonSearchExpressPerson(String json, Activity activity) {//快递员统计解析
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String result = jsonObject.getString("message");
            if ("失败！".equals(result)) {//没有数据时的处理
                Statics.expressPersonStatisticList.clear();
                //ExpressStatisticsActivity expressStatisticsActivity = new ExpressStatisticsActivity();
                ExpressStatisticsActivity.AdapterRefresh("expressPersonAdapter");
                return;
            }
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            Statics.expressPersonStatisticList.clear();
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String month = jsonObject1.getString("month");
                String name = jsonObject1.getString("name");
                String sum = jsonObject1.getString("sum");
                String name_id = jsonObject1.getString("name_id");
                ExpressPersonStatistic expressPersonStatistic = new ExpressPersonStatistic(name_id ,month, name, sum);
                Statics.expressPersonStatisticList.add(expressPersonStatistic);
                expressPersonStatistic = null;
            }
            //刷新异步刷新
            //ExpressStatisticsActivity expressStatisticsActivity = new ExpressStatisticsActivity();
            ExpressStatisticsActivity.AdapterRefresh("expressPersonAdapter");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void jsonSearchExpressPersonStatisticXiangxi(String json) {//快递员统计详情
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");

            Statics.epsXList.clear();
            Log.d("tasts","------------------------------");
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String name = jsonObject1.getString("name");
                String numeric = jsonObject1.getString("numeric");
                String description = jsonObject1.getString("description");
                Log.d("tasts","POO+"+description);
                String id = jsonObject1.getString("id");
                String type = jsonObject1.getString("type");
                String type1 = jsonObject1.getString("type1");
                String name_id = jsonObject1.getString("name_id");
                Log.d("tasts","POO+"+name_id+"ssd"+type);
                JSONObject jsonObject2 = jsonObject1.getJSONObject("date");
                String year = jsonObject2.getString("year");
                String month = jsonObject2.getString("month");
                String day = jsonObject2.getString("date");
                String time = jsonObject2.getString("time");
                Log.d("longfei",year+"year"+month+"month"+day+"day");

                //----------------------------------------------------------------------------------
                year = ToolUtils.timeDateFormat(year);
                StringBuffer sb=new StringBuffer();
                int temp = Integer.parseInt(month);

                sb.append(year).append("-").append(++temp).append("-").append(day);

                ExpressPersonStatisticsXiangqing epsXiangqing = new ExpressPersonStatisticsXiangqing(
                       sb.toString(),name, numeric, description, id, type,type1,name_id);
                Statics.epsXList.add(epsXiangqing);
                epsXiangqing = null;
            }
            //刷新异步刷新
                //ExpressStatisticsActivity expressStatisticsActivity = new ExpressStatisticsActivity();
                ExpressStatisticsActivity.AdapterRefresh("xiangxiAdapter");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
