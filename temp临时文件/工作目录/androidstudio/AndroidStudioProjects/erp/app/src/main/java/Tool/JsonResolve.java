package Tool;

import android.app.Activity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import http.Constants;
import model.AccountClassify;
import model.AccountManagement;
import model.AccountReason;
import model.AccountType;
import model.Customer;
import model.CustomerBillingStatistics;
import model.ExpressNumberManagement;
import model.ExpressPerson;
import model.ExpressPersonMonthStatisticsXiangqing;
import model.ExpressPersonStatistic;
import model.ExpressPersonStatisticsXiangqing;
import model.ExpressPieceCountMonth;
import model.TimeBillingStatistics;
import model.TimeExpressStatistics;
import model.XiangxiBillingStatistics;
import ui.activity.AccountManagementActivity;
import ui.activity.BillingStatisticsActivity;
import ui.activity.ExpressNumberManagerActivity;
import ui.activity.ExpressStatisticsActivity;
import ui.fragement.ExpressPiecesDetailsChartsFragementActivity;


/**
 * Created by admin on 2017/2/21.
 */

public class JsonResolve {
    final static int[] sizeTable = {9, 99, 999, 9999, 99999, 999999, 9999999,
            99999999, 999999999, Integer.MAX_VALUE};

    //json解析 项目管理
    public static void jsonAccountManager(String json, Activity activity, String rows) {
        try {
            //解析前先清空
            Log.v("test", "jsonAccountManager");
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            JSONObject jsonObject1 = jsonArray1.getJSONObject(0);
            String total = jsonObject1.get("total").toString();
            Log.d("test", "total:" + total);
            Constants.page = (Integer.parseInt(total) + Integer.parseInt(rows) - 1) / Integer.parseInt(rows);
            JSONArray jsonArray2 = jsonObject1.getJSONArray("rows");
            Constants.accountManagementList.clear();
            for (int k = 0; k < jsonArray2.length(); k++) {
                JSONObject jsonObject2 = jsonArray2.getJSONObject(k);
                String id = jsonObject2.getString("id");
                String type = jsonObject2.getString("type");
                String classify = jsonObject2.getString("classify");
                String reason = jsonObject2.getString("reason");
                String sum = jsonObject2.getString("sum");
                String createBy = jsonObject2.getString("createBy");
                String customerId = jsonObject2.getString("customerId");
                String remark = jsonObject2.getString("description");
                Log.d("test3", "----------------------------------");
                Log.d("test3", "sum:" + sum);
                Log.d("test3", "type:" + type);
                AccountManagement financialManagement = new AccountManagement(id, type, classify, reason, sum, createBy, customerId, remark);
                Constants.accountManagementList.add(financialManagement);
                financialManagement = null;
            }
            //刷新
            //AccountManagementAdapter accountManagementAdapter =new AccountManagementAdapter(activity);
            //AccountManagementActivity.accountLv.setAdapter(accountManagementAdapter);
            //AccountManagementActivity.accountManagementAdapter.notifyDataSetChanged();
            AccountManagementActivity accountManagementActivity = new AccountManagementActivity();
            accountManagementActivity.AdapterRefresh("accountManagementAdapter");

        } catch (JSONException e) {
            e.printStackTrace();

        }

    }

    public static void jsonAccountReasonSearch(String json, Activity activity) {//详细
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            Constants.accountReasonList.clear();
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("name");
                AccountReason accountReason = new AccountReason(id, name);
                Constants.accountReasonList.add(accountReason);
                accountReason = null;
                Log.v("test2", "name:" + name);
                Log.v("test2", "------------");
            }
            //刷新

            //ArrayList<String> data_list = new ArrayList<>();
            Constants.data_list.clear();
            for (int i = 0; i < Constants.accountReasonList.size(); i++) {
                Constants.data_list.add(Constants.accountReasonList.get(i).getName());
                Log.v("test2", "data_list1:" + Constants.accountReasonList.get(i).getName());

            }
            AccountManagementActivity accountManagementActivity = new AccountManagementActivity();
            accountManagementActivity.AdapterRefresh("reasonSpinner");


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void jsonAccountClassifySearch(String json) {//分类
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            Constants.accountClassifyList.clear();
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("name");
                AccountClassify accountClassify = new AccountClassify(id, name);
                Constants.accountClassifyList.add(accountClassify);
                accountClassify = null;
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void jsonAccountTypeSearch(String json) {//快递类型 圆通韵达
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            Constants.accountTypeList.clear();
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("name");
                AccountType accountType = new AccountType(id, name);
                Constants.accountTypeList.add(accountType);
                accountType = null;
            }

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
            Constants.customerList.clear();

            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("name");
                Customer customer = new Customer(id, name);
                Constants.customerList.add(customer);
                customer = null;
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //json解析 账单统计
    public static void jsonSearchTime(String json, Activity activity) {
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            Constants.timeBillingStatisticsList.clear();
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String income = jsonObject1.getString("jz1");
                String imbanlance = jsonObject1.getString("ce");
                String outcome = jsonObject1.getString("cz1");
                //String type = jsonObject1.getString("type");
                String year = jsonObject1.getString("ye");
                String month = jsonObject1.getString("mon");
                TimeBillingStatistics timeBillingStatistics = new TimeBillingStatistics(month, income, outcome, imbanlance);
                Constants.timeBillingStatisticsList.add(timeBillingStatistics);
                timeBillingStatistics = null;
            }
            //刷新异步刷新
            BillingStatisticsActivity billingStatisticsActivity = new BillingStatisticsActivity();
            billingStatisticsActivity.AdapterRefresh("timeAdapter");
            //BillingStatisticsActivity.timeAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void jsonSearchYear(String json) {
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            Constants.year.clear();

            for (int i = 0; i < jsonArray1.length(); i++) {
                Constants.year.add(jsonArray1.get(i).toString());
                Log.d("tes99","oni:::"+jsonArray1.get(i).toString());
                Log.d("tes99","Constants:::"+Constants.year.get(i));
            }
            //AccountManagementActivity.accountManagementAdapter =new AccountManagementAdapter(context);
            //AccountManagementActivity.accountLv.setAdapter(AccountManagementActivity.accountManagementAdapter);
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
            Constants.expressPieceCountMonthsList.clear();
            for (int i=0;i<jsonArray1.length();i++){
                JSONObject jsonObject2 =jsonArray1.getJSONObject(i);
                String month = jsonObject2.getString("month");
                String sum =  jsonObject2.getString("sum");
                String day =  jsonObject2.getString("day");
                ExpressPieceCountMonth expressPieceCountMonth =new ExpressPieceCountMonth(month,sum,day);
                Constants.expressPieceCountMonthsList.add(expressPieceCountMonth);
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
            Constants.epmsXList.clear();
            for (int i=0;i<jsonArray1.length();i++){
                JSONObject jsonObject2 =jsonArray1.getJSONObject(i);
                String month = jsonObject2.getString("month");
                String sum =  jsonObject2.getString("sum");
                String name_id =  jsonObject2.getString("name_id");
                String day =  jsonObject2.getString("day");
                ExpressPersonMonthStatisticsXiangqing epmsx =new ExpressPersonMonthStatisticsXiangqing(month,sum,name_id,day);
                Constants.epmsXList.add(epmsx);
                Log.v("sum",sum);
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
                Constants.customerBillingStatisticsArrayList.clear();
                BillingStatisticsActivity billingStatisticsActivity = new BillingStatisticsActivity();
                billingStatisticsActivity.AdapterRefresh("customerAdapter");
                return;
            }
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            Constants.customerBillingStatisticsArrayList.clear();
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
                Constants.customerBillingStatisticsArrayList.add(customerBillingStatistics);
                customerBillingStatistics = null;
            }
            //刷新异步刷新
            BillingStatisticsActivity billingStatisticsActivity = new BillingStatisticsActivity();
            billingStatisticsActivity.AdapterRefresh("customerAdapter");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void jsonSearchXiangxi(String json, Activity activity) {
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");

            Constants.xiangxiBillingStatisticsArrayList.clear();

            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String price = jsonObject1.getString("sum");
                String classifyname = jsonObject1.getString("classifyname");//进账
                String resonname = jsonObject1.getString("resonname");//进账
                String remark = jsonObject1.getString("description");
                JSONObject jsonObject2 = jsonObject1.getJSONObject("date");
                String year = jsonObject2.getString("year");
                String month = jsonObject2.getString("month");
                String day = jsonObject2.getString("date");
                String time = jsonObject2.getString("time");
                year=ToolUtils.timeDateFormat(Long.parseLong(time));
                StringBuffer sb=new StringBuffer();
                int temp = Integer.parseInt(month);
                sb.append(year).append("-").append(++temp).append("-").append(day);
                XiangxiBillingStatistics xiangxiBillingStatistics = new XiangxiBillingStatistics(classifyname, resonname, sb.toString(), price, remark);
                Constants.xiangxiBillingStatisticsArrayList.add(xiangxiBillingStatistics);
                xiangxiBillingStatistics = null;
            }
            Log.v("tool", "word:" + Constants.xiangxiBillingStatisticsArrayList.size());
            //刷新异步刷新
            Log.v("ToolUtils","yuyu:"+Integer.toString(Constants.xiangxiBillingStatisticsArrayList.size()));
            BillingStatisticsActivity billingStatisticsActivity = new BillingStatisticsActivity();
            billingStatisticsActivity.AdapterRefresh("xiangxiAdapter");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //业务员揽件量
    public static void jsonExpressionSearchCount(String json, Activity activity, String rows) {
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            JSONObject jsonObject1 = jsonArray1.getJSONObject(0);
            String total = jsonObject1.get("total").toString();
            Constants.page = (Integer.parseInt(total) + Integer.parseInt(rows) - 1) / Integer.parseInt(rows);
            JSONArray jsonArray2 = jsonObject1.getJSONArray("rows");
            Constants.enmList.clear();
            for (int k = 0; k < jsonArray2.length(); k++) {
                JSONObject jsonObject2 = jsonArray2.getJSONObject(k);
                String id = jsonObject2.getString("id");
                String type = jsonObject2.getString("type");
                String expresscount = jsonObject2.getString("numeric");
                String expressName = jsonObject2.getString("nameId");//name
                JSONObject jsonObject3 = jsonArray2.getJSONObject(k);
                JSONObject jsonObject4 = jsonObject3.getJSONObject("billingTime");
                long billingtime = jsonObject4.getLong("time");
                String month = jsonObject4.getString("month");
                String day = jsonObject4.getString("date");
                String year = jsonObject4.getString("year");
                Log.d("test4","billingTime"+Long.toString(billingtime));
                Log.d("test45","ddd"+Long.toString(billingtime));
                //----------------------------------------------------------------------------------
                year = ToolUtils.timeDateFormat(billingtime);
                StringBuffer sb=new StringBuffer();
                int temp = Integer.parseInt(month);

                sb.append(year).append("-").append(++temp).append("-").append(day);

                ExpressNumberManagement expressNumberManagement = new ExpressNumberManagement(id, expressName, type, expresscount, sb.toString());
                Constants.enmList.add(expressNumberManagement);
                expressNumberManagement=null;
            }
            //刷新
            ExpressNumberManagerActivity expressNumberManagerActivity = new ExpressNumberManagerActivity();
            expressNumberManagerActivity.AdapterRefresh("accountManagementAdapter");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void jsonExpressPersonAllSearch(String json) {//获取快递员姓名
        try {
            //解析前先清空
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            Constants.expressPersonsList.clear();
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("name");
                ExpressPerson expressPerson = new ExpressPerson(id, name);
                Constants.expressPersonsList.add(expressPerson);
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
            Constants.expressTimeList.clear();
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String month = jsonObject1.getString("month");
                String year = jsonObject1.getString("year");
                String sum = jsonObject1.getString("sum");
                TimeExpressStatistics timeBillingStatistics = new TimeExpressStatistics(month, year, sum);
                Constants.expressTimeList.add(timeBillingStatistics);
                timeBillingStatistics = null;
            }
            //刷新异步刷新

            ExpressStatisticsActivity expressStatisticsActivity = new ExpressStatisticsActivity();
           expressStatisticsActivity.AdapterRefresh("timeAdapter");

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
                Constants.expressPersonStatisticList.clear();
                ExpressStatisticsActivity expressStatisticsActivity = new ExpressStatisticsActivity();
                expressStatisticsActivity.AdapterRefresh("expressPersonAdapter");
                return;
            }
            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            Constants.expressPersonStatisticList.clear();
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String month = jsonObject1.getString("month");
                String name = jsonObject1.getString("name");
                String sum = jsonObject1.getString("sum");
                String name_id = jsonObject1.getString("name_id");
                ExpressPersonStatistic expressPersonStatistic = new ExpressPersonStatistic(name_id ,month, name, sum);
                Constants.expressPersonStatisticList.add(expressPersonStatistic);
                expressPersonStatistic = null;
            }
            //刷新异步刷新
            ExpressStatisticsActivity expressStatisticsActivity = new ExpressStatisticsActivity();
            expressStatisticsActivity.AdapterRefresh("expressPersonAdapter");
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

            Constants.epsXList.clear();
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
                year = ToolUtils.timeDateFormat(Long.parseLong(time));
                StringBuffer sb=new StringBuffer();
                int temp = Integer.parseInt(month);

                sb.append(year).append("-").append(++temp).append("-").append(day);

                ExpressPersonStatisticsXiangqing epsXiangqing = new ExpressPersonStatisticsXiangqing(
                       sb.toString(),name, numeric, description, id, type,type1,name_id);
                Constants.epsXList.add(epsXiangqing);
                epsXiangqing = null;
            }
            //刷新异步刷新

                ExpressStatisticsActivity expressStatisticsActivity = new ExpressStatisticsActivity();
                expressStatisticsActivity.AdapterRefresh("xiangxiAdapter");


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
