package Tool;

import android.app.Activity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Tool.statistics.Statics;
import broadcast.BroadCastTool;
import broadcast.TYPE;
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
import ui.activity.AddAccountManagerActivity;
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
            Log.d("test7", "total:" + total);
            Statics.page = (Integer.parseInt(total) + Integer.parseInt(rows) - 1) / Integer.parseInt(rows);
            JSONArray jsonArray2 = jsonObject1.getJSONArray("rows");
            Statics.accountManagementList.clear();
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

                //对出账进行处理，去除样式，进账则不用
                if(!classify.equals("进账")){//以b为分割
                    classify = "出账";
                    String typeString[] =type.split("<b>");
                    Log.d("asda",customerId);
                    Log.d("asda",remark);

                    String customerIdString[] = customerId.split("<b>");
                    String remarkString[] = remark.split("<b>");
                    String reasonString[] = reason.split("<b>");
                    String createByString[] = createBy.split("<b>");

                    for(int i=0;i<typeString.length;i++){
                        Log.d("asda",typeString[i]);
                        if(i==1){
                            Log.d("asda",typeString[i]);
                            type=typeString[i].split("</b>")[0];
                            customerId=customerIdString[i].split("</b>")[0];
                            remark=remarkString[i].split("</b>")[0];
                            reason=reasonString[i].split("</b>")[0];
                            createBy=createByString[i].split("</b>")[0];
                            Log.d("asda","sss:"+customerId+remark);
                        }
                    }
                }else{

                    Log.d("asllll",remark);
                    String customerIdString[] = customerId.split(">");
                    String remarkString[] = remark.split(">");
                    String reasonString[] = reason.split(">");
                    String createByString[] = createBy.split(">");
                    for(int i=0;i<remarkString.length;i++){
                        Log.d("asda",remarkString[i]);
                        if(i==1){
                            Log.d("asda",customerIdString[i]);

                            customerId=customerIdString[i].split("</")[0];
                            remark=remarkString[i].split("</")[0];
                            //createBy=createByString[i].split("</span>")[0];
                            Log.d("asda","sss:"+customerId+remark);
                        }
                    }

                }

                //账单时间
                JSONObject billingObject3 = jsonObject2.getJSONObject("billingTime");
                String date = billingObject3.getString("date");
                String month = billingObject3.getString("month");
                String time = billingObject3.getString("time");
                String yearString = billingObject3.getString("year");
                //----------------------------------------------------------------------------------
                String year = ToolUtils.timeDateFormat(yearString);
                StringBuffer billingSb=new StringBuffer();
                int temp = Integer.parseInt(month);
                billingSb.append(year).append("-").append(++temp).append("-").append(date);
                Log.d("yeayr",billingSb.toString());

                //创建时间
                JSONObject createObject3 = jsonObject2.getJSONObject("createTime");
                String date1 = createObject3.getString("date");
                String month1 = createObject3.getString("month");
                String time1 = createObject3.getString("time");
                String hours = createObject3.getString("hours");
                String minutes = createObject3.getString("minutes");
                String seconds = createObject3.getString("seconds");
                String yearString1 = billingObject3.getString("year");
                //----------------------------------------------------------------------------------
                String year1 = ToolUtils.timeDateFormat(yearString1);
                StringBuffer createSb=new StringBuffer();
                int temp1 = Integer.parseInt(month);
                createSb.append(year1).append("/").append(++temp1).append("/").append(date1).append(" ")
                        .append(hours).append(":").append(minutes).append(":").append(seconds);
                Log.d("yeayr:",createSb.toString());
                Log.d("test3", "----------------------------------");
                Log.d("test3", "sum:" + sum);
                Log.d("test3", "type:" + type);
                AccountManagement financialManagement = new AccountManagement(id, type, classify,billingSb.toString()
                        ,createSb.toString(),reason, sum, createBy, customerId, remark);
                Statics.accountManagementList.add(financialManagement);
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
            if(AddAccountManagerActivity.addBoolean){//更新add添加页面
                BroadCastTool.sendMyBroadcast(TYPE.NORMAL,activity,"addReasonSpinner");
                AddAccountManagerActivity addAccountManagerActivity = new AddAccountManagerActivity();
                addAccountManagerActivity.AdapterRefresh("reasonSpinner");
                AddAccountManagerActivity.addBoolean=false;
                Log.d("aleand","发送广播");

            }else{//更新search显示检索页面
                BroadCastTool.sendMyBroadcast(TYPE.NORMAL,activity,"SearchReasonSpinner");
                AccountManagementActivity accountManagementActivity = new AccountManagementActivity();
                accountManagementActivity.AdapterRefresh("reasonSpinner");
                Log.d("aleand","发送广播");
            }

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
            Statics.accountClassifyList.clear();
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("name");
                AccountClassify accountClassify = new AccountClassify(id, name);
                Statics.accountClassifyList.add(accountClassify);
                accountClassify = null;
                Log.d("jizhang","name:"+name);
            }
            //刷新适配器

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
            Statics.accountTypeList.clear();
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("name");
                AccountType accountType = new AccountType(id, name);
                Statics.accountTypeList.add(accountType);
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
    public static void jsonSearchTime(String json, Activity activity) {
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
            BillingStatisticsActivity billingStatisticsActivity = new BillingStatisticsActivity();
            billingStatisticsActivity.AdapterRefresh("timeAdapter");
            //BillingStatisticsActivity.timeAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
            //AccountManagementActivity.accountManagementAdapter =new AccountManagementAdapter(context);
            //AccountManagementActivity.accountLv.setAdapter(AccountManagementActivity.accountManagementAdapter);
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
            /*for (int i=0;i<jsonArray1.length();i++){
                JSONObject jsonObject2 =jsonArray1.getJSONObject(i);
                String month = jsonObject2.getString("month");
                String sum =  jsonObject2.getString("sum");
                String name_id =  jsonObject2.getString("name_id");
                String day =  jsonObject2.getString("day");
                ExpressPersonMonthStatisticsXiangqing epmsx =new ExpressPersonMonthStatisticsXiangqing(month,sum,name_id,day);
                Statics.epmsXList.add(epmsx);
                Log.v("sum",sum);
            }*/
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
                BillingStatisticsActivity billingStatisticsActivity = new BillingStatisticsActivity();
                billingStatisticsActivity.AdapterRefresh("customerAdapter");
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

            Statics.xiangxiBillingStatisticsArrayList.clear();

            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                String price = jsonObject1.getString("sum");
                String classifyname = jsonObject1.getString("classifyname");//进账
                String resonname = jsonObject1.getString("resonname");//进账
                String remark = jsonObject1.getString("description");
                String type = jsonObject1.getString("type");
                JSONObject jsonObject2 = jsonObject1.getJSONObject("date");
                String year = jsonObject2.getString("year");
                String month = jsonObject2.getString("month");
                String day = jsonObject2.getString("date");
                String time = jsonObject2.getString("time");

                year=ToolUtils.timeDateFormat(year);

                StringBuffer sb=new StringBuffer();
                int temp = Integer.parseInt(month);
                sb.append(year).append("-").append(++temp).append("-").append(day);
                String typeString = null;
                switch (type){
                    case "024001":
                        typeString = "圆通快递";
                        break;
                    case "024002":
                        typeString = "韵达快递";
                        break;
                }
                Log.d("hui","year:"+year+",."+year);
                XiangxiBillingStatistics xiangxiBillingStatistics = new XiangxiBillingStatistics(classifyname, typeString, resonname, sb.toString(), price, remark);
                Statics.xiangxiBillingStatisticsArrayList.add(xiangxiBillingStatistics);
                xiangxiBillingStatistics = null;
            }
            Log.v("tool", "word:" + Statics.xiangxiBillingStatisticsArrayList.size());
            //刷新异步刷新
            Log.v("ToolUtils","yuyu:"+Integer.toString(Statics.xiangxiBillingStatisticsArrayList.size()));
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
            Statics.page = (Integer.parseInt(total) + Integer.parseInt(rows) - 1) / Integer.parseInt(rows);
            JSONArray jsonArray2 = jsonObject1.getJSONArray("rows");
            Statics.enmList.clear();
            for (int k = 0; k < jsonArray2.length(); k++) {
                JSONObject jsonObject2 = jsonArray2.getJSONObject(k);
                String id = jsonObject2.getString("id");
                String type = jsonObject2.getString("type");
                String expresscount = jsonObject2.getString("numeric");
                String expressName = jsonObject2.getString("nameId");//name
                JSONObject jsonObject3 = jsonArray2.getJSONObject(k);
                JSONObject jsonObject4 = jsonObject3.getJSONObject("billingTime");
                String billingtime = jsonObject4.getString("time");
                String month = jsonObject4.getString("month");
                String day = jsonObject4.getString("date");
                String year = jsonObject4.getString("year");
                Log.d("test4","billingTime"+billingtime);
                Log.d("test45","ddd"+billingtime);
                //----------------------------------------------------------------------------------
                year = ToolUtils.timeDateFormat(year);
                StringBuffer sb=new StringBuffer();
                int temp = Integer.parseInt(month);

                sb.append(year).append("-").append(++temp).append("-").append(day);

                ExpressNumberManagement expressNumberManagement = new ExpressNumberManagement(id, expressName, type, expresscount, sb.toString());
                Statics.enmList.add(expressNumberManagement);
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
                Statics.expressPersonStatisticList.clear();
                ExpressStatisticsActivity expressStatisticsActivity = new ExpressStatisticsActivity();
                expressStatisticsActivity.AdapterRefresh("expressPersonAdapter");
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

                ExpressStatisticsActivity expressStatisticsActivity = new ExpressStatisticsActivity();
                expressStatisticsActivity.AdapterRefresh("xiangxiAdapter");


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}