package http;


import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import model.AccountClassify;
import model.ExpressManagement;
import model.AccountReason;
import model.AccountType;
import model.AttendanceStatistics;
import model.AttendanceYear;
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

/**
 * Created by admin on 2017/2/21.
 */

public class Constants {

    /*//账目管理
    public static String LoginUrl = "http://192.168.1.12:8081/login.jhtml";
    public static String LoginId;
    //public static  String LoginUrl = "http://i.yifeng-dl.com/login.jhtml";//用户登录地址
    public static String AccountManagementSearchUrl = "http://192.168.1.12:8081/wxApi.ajax";//账目查询地址 添加地址
    public static String AllCustomerUrl = "http://192.168.1.16:8083/getWXAllCustomer.ajax";//客户
    public static String AccountReasonUrl = "http://192.168.1.16:8083/getWXExpenseAccountReason.ajax";//账目类型明细
    public static String AccountClassifyUrl = "http://192.168.1.16:8083/getWXExpenseAccountClassify.ajax";//账目类型进账出账
    public static String AccountTypeUrl = "http://192.168.1.16:8083/getWXExpenseAccountType.ajax";//获取快递类型 圆通韵达
    public static ArrayList<AccountManagement> accountManagementList = new ArrayList<>();
    public static ArrayList<Customer> customerList = new ArrayList<>();
    public static ArrayList<AccountReason> accountReasonList = new ArrayList<>();
    public static ArrayList<AccountClassify> accountClassifyList = new ArrayList<>();
    public static ArrayList<AccountType> accountTypeList = new ArrayList<>();
    public static String accountClassify = "023001";//默认为进账，出账：023002
    public static int page = 0;//分页页数

    //账单统计
    public static String TimeSearchUrl = "http://192.168.1.16:8083/getWXTjsjList.ajax";//统计列表
    public static String CustomerSearchUrl = "http://192.168.1.16:8083/getWXCustomerTjsjList.ajax";//客户统计列表
    public static String YearSearchUrl = "http://192.168.1.16:8083/getWXYearAccountStatistics.ajax";//获取年
    public static String XqCustomerSearchUrl = "http://192.168.1.16:8083/getWXCustomerTjxq.ajax";//客户详情
    public static ArrayList<TimeBillingStatistics> timeBillingStatisticsList = new ArrayList<>();
    public static ArrayList<String> billingYear = new ArrayList<>();
    public static ArrayList<String> expressYear = new ArrayList<>();

    public static ArrayList<CustomerBillingStatistics> customerBillingStatisticsArrayList = new ArrayList<>();
    public static ArrayList<XiangxiBillingStatistics> xiangxiBillingStatisticsArrayList = new ArrayList<>();

    public static ArrayList<String> data_list = new ArrayList<>();
    public static String results = null;
    public static String userName = null;

    //快递数量管理      //18 52
    public static ArrayList<ExpressNumberManagement> enmList = new ArrayList<>();
    public static ArrayList<ExpressPerson> expressPersonsList = new ArrayList<>();
    public static ArrayList<ExpressPersonStatistic> expressPersonStatisticList = new ArrayList<>();////快递员统计解析
    public static ArrayList<ExpressPersonStatisticsXiangqing> epsXList = new ArrayList<>();//快递员统计详情
    public static ArrayList<ExpressPersonMonthStatisticsXiangqing> epmsXList = new ArrayList<>();//快递员统计详情
    public static String[] expressType;
    public static String ExpressYearSearchUrl = "http://192.168.1.18:8083/getWXYearNumberStatistics.ajax";//年份
    public static String ExpressPersonNameSearchUrl = "http://192.168.1.18:8083/getWXAllNumber.ajax";//业务员
    public static String ExpressCountSearch = "http://192.168.1.18:8083/wxApiNumber.ajax";//增删改查 删除id
    public static String TimeStatisticSearchUrl = "http://192.168.1.18:8083/getWXTjsjListNumber.ajax";//月份统计
    public static ArrayList<TimeExpressStatistics> expressTimeList = new ArrayList<>();
    public static String ExpressStatisticSearchUrl = "http://192.168.1.18:8083/getCustomerTjsjListNumber.ajax";//快递员统计
    public static String ExpressXqTimeSearchUrl = "http://192.168.1.18:8083/getCustomerTjxqNumber.ajax";//快递员统计详情
    public static String ExpressPieceMonthDaySearchUrl = "http://192.168.1.18:8083/getWxXAxisDay.ajax";    //当月有多少天
    public static String ExpressPieceDaySearchUrl = "http://192.168.1.18:8083/getWxSeriesDataDay.ajax";   //具体件数
    public static String ExpressPersonPieceDaySearchUrl = "http://192.168.1.51:8083/getWxSeriesDataAmount.ajax";//快递员统计图表
    public static String[] Xday;
    public static ArrayList<ExpressPieceCountMonth> expressPieceCountMonthsList = new ArrayList<>();
    public static Boolean dayCount = false;//是否按天统计
    public static HashMap<String,String>  XiangxiChan= new HashMap<>();//详细查询参数
    //是否需要发送广播
    public static Boolean isBroadCast = false;

    //考勤管理
    public static String AttendanceStatisticsSearchUrl = "http://192.168.1.55:8083/getWxAllAttendanceMonthSum.ajax";//考勤查询url
    public static String searchYearUrl = "http://192.168.1.55:8083/getWxAllYears.ajax";//年查询url
    public static ArrayList<AttendanceStatistics> attendanceStatisticsList = new ArrayList<>();//考勤表格数据
    public static ArrayList<String> searchNameId = new ArrayList<>(); //员工id
    public static ArrayList<String> searchName = new ArrayList<>(); //员工姓名
    public static ArrayList<AttendanceYear> searchYear = new ArrayList<>();//年查询*/

}
