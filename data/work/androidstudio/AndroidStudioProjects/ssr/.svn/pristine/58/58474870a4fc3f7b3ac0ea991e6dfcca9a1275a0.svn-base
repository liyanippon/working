package http;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import Tool.statistics.Statics;
import broadcast.BroadCastTool;
import broadcast.TYPE;
import model.AttendanceStaffBelongProject;
import model.AttendanceStatistics;
import model.AttendanceWxDetaSearch;
import model.AttendanceYear;
import model.CompanyDepartment;
import model.ExpressClassify;
import model.ExpressExpensePayMethod;
import model.FinancialAccount;
import model.FinancialBillingGetWXSelectCustomer;
import model.FinancialBillingGetWXSelectMonthAccount;
import model.FinancialBillingGetWXsettlementMonth;
import model.FinancialCustomer;
import model.FinancialManagement;
import model.FinancialSalaryStatistics;
import model.LogisticsReportSearch;
import model.StaffName;
import model.TransferAccountClassify;
import portface.LazyLoadFace;
import ui.activity.AttendanceStatisticsActivity;
import ui.activity.BillingStatisticsActivity;
import ui.activity.ExpressBillingManagementActivity;
import ui.activity.FinancialBillingManagementActivity;
import ui.activity.FinancialSalaryStastisticsActivity;
import ui.activity.FinancialStastisticsActivity;
import ui.activity.LogisticsReportActivity;
import ui.activity.TransferAccountActivity;

/**
 * Created by admin on 2017/5/26.
 */

public class HttpTypeUtil {

    private  Activity aActivity;

    public HttpTypeUtil(Activity activity){
        this.aActivity = activity;
    }

    public static void expressType(String result,String httpType) {//物流模块
        ExpressExpensePayMethod[] as;
        ExpressClassify[] ec;
        TransferAccountClassify[] tac;
        LogisticsReportSearch[] lrs;
        switch (httpType){
            case "100106":
                Statics.expressClassifyList.clear();
                ec = new Gson().fromJson(result, ExpressClassify[].class);
                Collections.addAll(Statics.expressClassifyList,ec);//转化arrayList
                break;
            case "100108":
                Statics.expressClassifyList2.clear();
                ec = new Gson().fromJson(result, ExpressClassify[].class);
                Collections.addAll(Statics.expressClassifyList2,ec);//转化arrayList
                break;
            case "100109":
                Statics.transferAccountClassifiesList.clear();
                tac = new Gson().fromJson(result, TransferAccountClassify[].class);
                Collections.addAll(Statics.transferAccountClassifiesList,tac);//转化arrayList
                BroadCastTool.sendMyBroadcast(TYPE.NORMAL,TransferAccountActivity.activity,"TransferAccount");
                //TransferAccountActivity transferAccountActivity = new TransferAccountActivity();
                TransferAccountActivity.AdapterRefresh("transfer");
                break;
            case "100110":
                //刷新页面
                Log.d("HttpTypeUtil", "转账结果"+result);
                ExpressBillingManagementActivity.isAdd = true;
                ExpressBillingManagementHttpPost httpPost = new ExpressBillingManagementHttpPost();
                String httpUrl = Statics.FinancialBillingManagementSearchUrl;
                Activity activity;
                activity = ExpressBillingManagementActivity.activityExpress;
                httpPost.searchHttp(httpUrl, "", "", "", activity, 1);
                break;
            /*case "100111":
                //物流报表月份查询
                Log.d("HttpTypeUtil", "物流报表月份查询:"+result);
                Statics.logisticsReportSearcheList.clear();
                lrs = new Gson().fromJson(result, LogisticsReportSearch[].class);
                Collections.addAll(Statics.logisticsReportSearcheList,lrs);//转化arrayList
                break;*/
            case "100204":
                //json数据使用Gson框架解析
                Log.d("HttpTypeUtil", "result:结果100204" + result);
                Statics.CurrentPayStatistic = result;
                //刷新异步刷新
                //BillingStatisticsActivity bsActivity = new BillingStatisticsActivity();
                if("LogisticsReportActivity".equals(Statics.ActivityType)){
                    LogisticsReportActivity.AdapterRefresh("CurrentPayStatistic");
                    Statics.ActivityType = "";
                }else {
                    BillingStatisticsActivity.AdapterRefresh("CurrentPayStatistic");
                }
                break;
            case "100205":
                Log.d("HttpTypeUtil", "支付方式字段获取：：" + result);
                Statics.expressPaymentMethodArrayList.clear();
                as = new Gson().fromJson(result, ExpressExpensePayMethod[].class);
                Collections.addAll(Statics.expressPaymentMethodArrayList,as);//转化arrayList
                break;
        }

    }

    public static void attendanceType(String result,String httpType) {//考勤模块
        AttendanceStatistics[] as;
        AttendanceYear[] ay;
        AttendanceWxDetaSearch[] awds;
        AttendanceStaffBelongProject[] abp;
        StaffName[] sn;
        switch (httpType){
            case "100400":
                Log.d("HttpTypeUtil", "统计信息：：" + result);
                //json数据使用Gson框架解析
                Statics.attendanceStatisticsList.clear();
                as = new Gson().fromJson(result, AttendanceStatistics[].class);
                Collections.addAll(Statics.attendanceStatisticsList,as);//转化arrayList
                //刷新异步刷新
                //AttendanceStatisticsActivity attendanceStatisticsActivity = new AttendanceStatisticsActivity();
                //attendanceStatisticsActivity.AdapterRefresh("attendAdapter");
                AttendanceStatisticsActivity.AdapterRefresh("attendAdapter");
                break;
            case "100401":
                Log.d("HttpTypeUtil", "年份查询:"+result);
                Statics.searchYear.clear();
                ay = new Gson().fromJson(result, AttendanceYear[].class);
                Collections.addAll(Statics.searchYear,ay);//转化arrayList
                break;
            case "100402":
                Log.d("HttpTypeUtil", "员工姓名："+result);
                //json数据使用Gson框架解析
                Statics.searchName.clear();
                sn = new Gson().fromJson(result, StaffName[].class);
                Collections.addAll(Statics.searchName,sn);
                break;

            case "100403":
                Log.d("HttpTypeUtil", "考勤统计详细信息:"+result);
                if(result!=null&&!result.equals("")){
                    Statics.attendanceWxDetaSearchArrayList.clear();
                    Log.d("HttpTypeUtil", "考勤统计详细信ss息:"+result);
                    awds = new Gson().fromJson(result, AttendanceWxDetaSearch[].class);
                    Collections.addAll(Statics.attendanceWxDetaSearchArrayList,awds);//转化arrayList
                    //刷新异步刷新
                    //AttendanceStatisticsActivity attendanceStatisticsActivity1 = new AttendanceStatisticsActivity();
                    //attendanceStatisticsActivity1.AdapterRefresh("attendXiangxi");
                    AttendanceStatisticsActivity.AdapterRefresh("attendXiangxi");
                }else{
                    Statics.attendanceWxDetaSearchArrayList.clear();
                    //刷新异步刷新
                    //AttendanceStatisticsActivity attendanceStatisticsActivity1 = new AttendanceStatisticsActivity();
                    //attendanceStatisticsActivity1.AdapterRefresh("attendXiangxi");
                    AttendanceStatisticsActivity.AdapterRefresh("attendXiangxi");
                }
                break;
            case "100404":
                Log.d("HttpTypeUtil", "员工所在项目组:"+result);
                Statics.staffBelongProjectArrayList.clear();
                abp = new Gson().fromJson(result, AttendanceStaffBelongProject[].class);
                Collections.addAll(Statics.staffBelongProjectArrayList,abp);//转化arrayList
                for (int i=0 ; i<Statics.staffBelongProjectArrayList.size() ;i++){
                    Log.d("HttpTypeUtil", "shushu::" + Statics.staffBelongProjectArrayList.get(i).getProject_name());
                }
                //刷新异步刷新
                //AttendanceStatisticsActivity attendanceStatisticsActivity2 = new AttendanceStatisticsActivity();
                //attendanceStatisticsActivity2.AdapterRefresh("staffBelongProject");
                AttendanceStatisticsActivity.AdapterRefresh("staffBelongProject");
                break;
        }

    }
    public static String financialType(String result,String httpType) {//财务模块
        //FinancialStastisticsActivity fsa = new FinancialStastisticsActivity();
        String results;
        switch (httpType) {
            case "100500":
                //json数据使用Gson框架解析
                Statics.financialCustomersList.clear();
                FinancialCustomer[] fc = new Gson().fromJson(result, FinancialCustomer[].class);
                Collections.addAll(Statics.financialCustomersList,fc);//转化arrayList
                break;
            case "100501":
                //json数据使用Gson框架解析
                Log.d("HttpTypeUtil", "财务带条件查询");
                Statics.financialManagementList.clear();
                FinancialManagement[] fm = new Gson().fromJson(result, FinancialManagement[].class);
                Collections.addAll(Statics.financialManagementList,fm);//转化arrayList
                //FinancialBillingManagementActivity financialBillingManagementActivity =new FinancialBillingManagementActivity();
                //financialBillingManagementActivity.AdapterRefresh("FinancialManagementHttpPost");
                FinancialBillingManagementActivity.AdapterRefresh("FinancialManagementHttpPost");
                break;
            case "100502":
                //刷新页面
                HttpBasePost.postHttp(Statics.FinancialBillingManagementUrl,null,HttpTypeConstants.FinancialBillingManagementUrlType);//刷新页面
                break;
            case "100503":
                Log.d("HttpTypeUtil", "添加数据:");
                results = result;
                Log.d("HttpTypeUtil", "添加结果:" + results);
                results = "success";
                /*AddFinancialBillingManagerActivity abm = new AddFinancialBillingManagerActivity();
                abm.AdapterRefresh("addCountManager");*/
                break;
            case "100504":
                //json数据使用Gson框架解析
                Statics.financialAccountList.clear();
                FinancialAccount[] fa = new Gson().fromJson(result, FinancialAccount[].class);
                Collections.addAll(Statics.financialAccountList,fa);//转化arrayList
                break;
            case "100506":
                //json数据使用Gson框架解析
                Log.d("HttpTypeUtil", "管理详细：" + result);
                Statics.fbgwxSettlementMonthList.clear();
                FinancialBillingGetWXsettlementMonth[] fbgxwd = new Gson().fromJson(result, FinancialBillingGetWXsettlementMonth[].class);
                Collections.addAll(Statics.fbgwxSettlementMonthList,fbgxwd);//转化arrayList
                FinancialStastisticsActivity.AdapterRefresh("timeAdapter");
                break;
            case "100507":
                //json数据使用Gson框架解析
                Log.d("HttpTypeUtil", "账目统计（客户）："+result);
                Statics.fbgwxscList.clear();
                FinancialBillingGetWXSelectCustomer[] mxbsa = new Gson().fromJson(result, FinancialBillingGetWXSelectCustomer[].class);
                Collections.addAll(Statics.fbgwxscList,mxbsa);//转化arrayList
                FinancialStastisticsActivity.AdapterRefresh("customerAdapter");
                break;
            case "100508":
                Statics.CurrentMoney = result;
                Log.d("FinancialStatisticsHttp", "查看当前资金情况：" + result);
                //json数据使用Gson框架解析
                FinancialStastisticsActivity.AdapterRefresh("currentMoney");
                break;
            case "100509":
                Log.d("HttpTypeUtil", "账目统计详细查询："+result);
                Statics.fbgwxsmaList.clear();
                FinancialBillingGetWXSelectMonthAccount[] fbgmxbsa = new Gson().fromJson(result, FinancialBillingGetWXSelectMonthAccount[].class);
                Collections.addAll(Statics.fbgwxsmaList,fbgmxbsa);//转化arrayList
                FinancialStastisticsActivity.AdapterRefresh("monthXiangXiAdapter");
                break;
            case "100510":
                Log.d("HttpTypeUtil", "员工工资查询："+result);
                String replaceString = result.replace("\"\"","0.0");
                Statics.fssArrayList.clear();
                Log.d("HttpTypeUtil", "员工工资查询s："+replaceString);
                //FinancialSalaryStatistics[] fss = new Gson().fromJson(replaceString, FinancialSalaryStatistics[].class);
                //Collections.addAll(Statics.fssArrayList,fss);
                try {
                    FinancialSalaryStatistics financialSalaryStatistics = null;
                    JSONObject obj = new JSONObject(replaceString);
                    JSONArray jsonArray = obj.getJSONArray("rows");
                    for (int k = 0; k < jsonArray.length(); k++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(k);
                        String userName = jsonObject2.get("userName").toString();//员工姓名
                        String basePay = jsonObject2.get("basePay").toString();//基本工资
                        String leavePay = jsonObject2.get("leavePay").toString();//病事假工资
                        String month = jsonObject2.get("month").toString();//月份
                        String ndividualIncomeTax = jsonObject2.get("ndividualIncomeTax").toString();//个人所得税
                        String netPayroll = jsonObject2.get("netPayroll").toString();//实发工资
                        String taxableIncome = jsonObject2.get("taxableIncome").toString();//应税所得额
                        String bonus,expense,overtimeAllowance,trafficAllowance,travellingAllowance,endowmentInsurance,fullPay,housingFund,medicalInsurance,unemploymentInsurance,year;
                        if(jsonObject2.has("year")){
                            year = jsonObject2.get("year").toString();//年份
                        }else {
                            year = "0.0";
                        }
                        if(jsonObject2.has("unemploymentInsurance")){
                            unemploymentInsurance = jsonObject2.get("unemploymentInsurance").toString();//失业保险
                        }else {
                            unemploymentInsurance = "0.0";
                        }
                        if(jsonObject2.has("medicalInsurance")){
                            medicalInsurance = jsonObject2.get("medicalInsurance").toString();//医疗保险
                        }else {
                            medicalInsurance = "0.0";
                        }
                        if(jsonObject2.has("housingFund")){
                            housingFund = jsonObject2.get("housingFund").toString();//住房公积金
                        }else {
                            housingFund = "0.0";
                        }
                        if(jsonObject2.has("fullPay")){
                            fullPay = jsonObject2.get("fullPay").toString();//全额工资
                        }else {
                            fullPay = "0.0";
                        }
                        if(jsonObject2.has("endowmentInsurance")){
                            endowmentInsurance = jsonObject2.get("endowmentInsurance").toString();//养老保险
                        }else {
                            endowmentInsurance = "0.0";
                        }
                        if(jsonObject2.has("bonus")){
                             bonus = jsonObject2.get("bonus").toString();//奖金
                        }else {
                            bonus = "0.0";
                        }
                        if(jsonObject2.has("expense")){
                            expense = jsonObject2.get("expense").toString();//报销
                        }else {
                            expense = "0.0";
                        }
                        if(jsonObject2.has("overtimeAllowance")){
                            overtimeAllowance = jsonObject2.get("overtimeAllowance").toString();//加班补助
                        }else {
                            overtimeAllowance = "0.0";
                        }
                        if(jsonObject2.has("trafficAllowance")){
                            trafficAllowance = jsonObject2.get("trafficAllowance").toString();//交通补助
                        }else {
                            trafficAllowance = "0.0";
                        }
                        if(jsonObject2.has("travellingAllowance")){
                            travellingAllowance = jsonObject2.get("travellingAllowance").toString();//出差补助
                        }else {
                            travellingAllowance = "0.0";
                        }

                        //String bonus = jsonObject2.get("bonus").toString();//奖金
                        //String expense = jsonObject2.get("expense").toString();//报销
                        //String overtimeAllowance = jsonObject2.get("overtimeAllowance").toString();//加班补助
                        //String trafficAllowance = jsonObject2.get("trafficAllowance").toString();//交通补助
                        //String travellingAllowance = jsonObject2.get("travellingAllowance").toString();//出差补助
                        financialSalaryStatistics = new FinancialSalaryStatistics(userName,basePay
                        ,endowmentInsurance,fullPay,housingFund,leavePay,medicalInsurance,month,ndividualIncomeTax,netPayroll
                        ,taxableIncome,unemploymentInsurance,year,bonus,expense,overtimeAllowance,trafficAllowance,travellingAllowance);
                        Statics.fssArrayList.add(financialSalaryStatistics);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("HttpTypeUtil", "ki"+Statics.fssArrayList.size());
                FinancialSalaryStastisticsActivity.AdapterRefresh("salaryAdapter");
                break;
            case "100511":
                Log.d("HttpTypeUtil", "企业部门信息："+result);
                Statics.companyDepartmentsArrayList.clear();
                CompanyDepartment[] companyDepartments = new Gson().fromJson(result, CompanyDepartment[].class);
                Collections.addAll(Statics.companyDepartmentsArrayList,companyDepartments);//转化arrayList
                break;
            case "100512":
                Log.d("HttpTypeUtil", "adfasd");
                Log.d("HttpTypeUtil", "录入考勤："+result);
                break;
        }
        return result;
    }
}
