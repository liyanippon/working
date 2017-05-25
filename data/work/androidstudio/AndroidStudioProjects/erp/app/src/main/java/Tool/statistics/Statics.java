package Tool.statistics;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import model.FinancialAccount;
import model.FinancialBilingGetXWstatisticalData;
import model.FinancialBillingGetWXSelectMonthAccount;
import model.FinancialBillingGetWXsettlementMonth;
import model.FinancialCustomer;
import model.FinancialManagement;
import model.TimeBillingStatistics;
import model.TimeExpressStatistics;
import model.UserUmp;
import model.XiangxiBillingStatistics;

/**
 * Created by admin on 2017/2/21.
 */

public class Statics {

    /**登录**/
    public static String sessionId ;

    /**物流管理**/
    public static String LoginUrl = "http://192.168.1.12:8081/login.jhtml";
    public static String UmlUrl = "http://192.168.1.16:8083/getWXMenuList.ajax";//用户权限url
    public static String Name;
    //public static  String LoginUrl = "http://i.yifeng-dl.com/login.jhtml";//用户登录地址
    public static String FinancialBillingManagementSearchUrl = "http://192.168.1.12:8081/wxApi.ajax";//账目查询地址 添加地址
    public static String AllCustomerUrl = "http://192.168.1.16:8083/getWXAllCustomer.ajax";//客户
    public static String AccountReasonUrl = "http://192.168.1.16:8083/getWXExpenseAccountReason.ajax";//账目类型明细
    public static String AccountClassifyUrl = "http://192.168.1.16:8083/getWXExpenseAccountClassify.ajax";//账目类型进账出账
    public static String AccountTypeUrl = "http://192.168.1.16:8083/getWXExpenseAccountType.ajax";//获取快递类型 圆通韵达
    public static ArrayList<ExpressManagement> expressManagementList = new ArrayList<>();
    public static ArrayList<Customer> customerList = new ArrayList<>();
    public static ArrayList<AccountReason> accountReasonList = new ArrayList<>();
    public static ArrayList<AccountClassify> accountClassifyList = new ArrayList<>();
    public static ArrayList<AccountType> accountTypeList = new ArrayList<>();
    public static String accountClassify = "023001";//默认为进账，出账：023002
    public static int page = 0;//分页页数

    /**物流统计**/
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

    /**业务揽件和业务统计**/      //18 52
    public static ArrayList<ExpressNumberManagement> enmList = new ArrayList<>();
    public static ArrayList<ExpressPerson> expressPersonsList = new ArrayList<>();
    public static ArrayList<ExpressPersonStatistic> expressPersonStatisticList = new ArrayList<>();////快递员统计解析
    public static ArrayList<ExpressPersonStatisticsXiangqing> epsXList = new ArrayList<>();//快递员统计详情
    //public static ArrayList<ExpressPersonMonthStatisticsXiangqing> epmsXList = new ArrayList<>();//快递员统计详情
    public static String[] epmsXList ;//快递员统计详情
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
    public static String[] Xday;//存放当月天数
    public static ArrayList<ExpressPieceCountMonth> expressPieceCountMonthsList = new ArrayList<>();
    public static Boolean dayCount = false;//是否按天统计
    public static HashMap<String,String>  XiangxiChan= new HashMap<>();//详细查询参数

    //是否需要发送广播
    public static Boolean isBroadCast = false;

    /**考勤管理**/
    public static String AttendanceStatisticsSearchUrl = "http://192.168.1.55:8083/getWxAllAttendanceMonthSum.ajax";//考勤查询url
    public static String searchYearUrl = "http://192.168.1.55:8083/getWxAllYears.ajax";//年查询url
    public static ArrayList<AttendanceStatistics> attendanceStatisticsList = new ArrayList<>();//考勤表格数据
    public static ArrayList<String> searchNameId = new ArrayList<>(); //员工id
    public static ArrayList<String> searchName = new ArrayList<>(); //员工姓名
    public static ArrayList<AttendanceYear> searchYear = new ArrayList<>();//年查询
    public static Boolean personCount = false;//是否按人员统计
    public static String[] xPerson;//统计表中纵轴数据
    public static Boolean yPositon = false;//设置y轴显示默认false

    /**财务管理**/
    public static String FinancialAccountCustomerUrl = "http://192.168.1.52:8083/getWXBillCustomer.ajax"; //账目客户名
    public static ArrayList<FinancialCustomer> financialCustomersList = new ArrayList<>();//账目客户
    //账目管理
    public static String FinancialBillingManagementUrl = "http://192.168.1.52:8083/getWXBillRecords.ajax"; //账目统计增删查
    public static String FinancialBillingManagementDelUrl = "http://192.168.1.52:8083/WXdeleteBillRecords.ajax";//删除账目统计
    public static ArrayList<FinancialManagement> financialManagementList = new ArrayList<>();//账目客户查询数据
    public static String AddFinancialBillingUrl = "http://192.168.1.52:8083/getWXInsertOrUpdateBillRecords.ajax";//添加账单
    //银行账目下拉框
    public static String FinancialBillingGetWXAccountsTypeUrl = "http://192.168.1.53:8083/getWXAccountsType.ajax";//银行账目下拉框
    public static ArrayList<FinancialAccount> financialAccountList = new ArrayList<>();//账目下拉框
    //账目统计
    public static String FinancialBillingGetWXstatisticalDataUrl = "http://192.168.1.53:8083/getWXstatisticalData.ajax";//年月统计表（按时间统计）
    public static List<FinancialBilingGetXWstatisticalData> fbgxwStatisticalDataList = new ArrayList<>();//时间查询数据
    public static String FinancialBillingGetWXsettlementMonthUrl = "http://192.168.1.53:8083/getWXsettlementMonth.ajax";//时间统计表
    public static List<FinancialBillingGetWXsettlementMonth> fbgwxSettlementMonthList = new ArrayList<>();//时间查询数据
    public static String FinancialBillingGetWXSelectMonthAccountUrl ="http://192.168.1.53:8083/getWXSelectMonthAccount.ajax";//月份账目明细
    public static List<FinancialBillingGetWXSelectMonthAccount> fbgwxsmaList = new ArrayList<>();
    public static String FinancialBillingGetCurrentMoneyUrl ="http://192.168.1.53:8083/getWXSelectAll.ajax";//查看现在金额
    public static String CurrentMoney = null;
    /**用户权限管理**/
    public static ArrayList<UserUmp> userUmpsStatisticsList = new ArrayList<>();//考勤表格数据

    public static Boolean SingleBoolean = true;//统计图是几路数据（默认两路）

}
