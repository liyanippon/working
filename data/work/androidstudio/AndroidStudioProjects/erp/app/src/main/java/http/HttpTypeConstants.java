package http;


/**
 * Created by admin on 2017/2/21.
 */

public class HttpTypeConstants {
    /**登录**/
    public static final String LoginUrlType = "100001";//登录请求
    public static final String UmlUrlType = "100002";//用户权限
    /**物流管理**/
    public static final String ExpressAccountUrlType = "100103";//快递账目查询地址 添加地址 删除地址
    public static final String CustomerUrlType = "100104";//客户
    public static final String ExpressReasonUrlType = "100105";//账目类型明细
    public static final String ExpressClassifyUrlType = "100106";//账目类型进账出账
    public static final String ExpressTypeUrlType = "100107";//获取快递类型 圆通韵达
    public static final String TransferAccountType = "100108";//转账下拉菜单
    public static final String GetWXExpenseAccountReasonUrl = "100109";//业务类型（转出处理）
    public static final String TransferAccount = "100110";//转账提交表单
    public static final String LogisticsReportGetWXTjsjList = "100111";//物流报表查询（月份）
    /**物流统计**/
    public static final String ExpressTimeSearchUrlType = "100200";//统计列表
    public static final String ExpressCustomerSearchUrlType = "100201";//客户统计列表
    public static final String YearSearchUrlType = "100201";//获取年
    public static final String ExpressXqCustomerSearchUrlType = "100203";//客户详情
    public static final String ExpressGetWXPaymentMethod = "100204";//查看现在资金信息
    public static final String ExpressGetWXExpenseAccountPaymentMethod = "100205";
    /**业务揽件和业务统计**/
    public static final String ExpressYearSearchUrlType = "100300";//年份
    public static final String ExpressPersonNameSearchUrlType = "100301";//业务员
    public static final String ExpressCountUrlType = "100302";//增删改查 删除id
    public static final String TimeStatisticSearchUrlType = "100303";//月份统计
    public static final String ExpressStatisticSearchUrlType = "100304";//快递员统计
    public static final String ExpressXqTimeSearchUrlType = "100305";//快递员统计详情
    public static final String ExpressPieceMonthDaySearchUrlType = "100306";    //当月有多少天
    public static final String ExpressPieceDaySearchUrlType = "100307";   //具体件数
    public static final String ExpressPersonPieceDaySearchUrlType = "100308";//快递员统计图表
    /**考勤管理**/
    public static final String AttendanceStatisticsSearchUrlType = "100400";//考勤查询url
    public static final String SearchYearUrlType = "100401";//年查询url
    public static final String SearchStuffName = "100402";//查询员工姓名
    public static final String GetWXAttendanceDetaSearchUrl = "100403";//考勤详细信息
    public static final String GetWXProjectSearchUrl = "100404";//员工所在项目组
    /**财务管理**/
    public static final String FinancialAccountCustomerUrlType = "100500"; //账目客户名
    //账目管理
    public static final String FinancialBillingManagementUrlType = "100501"; //账目统计查
    public static final String FinancialBillingManagementDelUrlType = "100502";//删除账目统计
    public static final String AddFinancialBillingUrlType = "100503";//添加账单
    //银行账目下拉框
    public static final String FinancialBillingGetWXAccountsTypeUrlType = "100504";//银行账目下拉框
    //账目统计
    public static final String FinancialBillingGetWXstatisticalDataUrlType = "100505";//年月统计表（按时间统计）
    public static final String FinancialBillingGetWXsettlementMonthUrlType = "100506";//时间统计表
    public static final String FinancialBillingGetWXSelectMonthAccountUrlType ="100507";//月份账目明细(详细信息)
    public static final String FinancialBillingGetCurrentMoneyUrlType ="100508";//查看现在金额
    public static final String FinancialBillingGetWXSelectCustomerDetailsUrlType = "100509";//客户统计信息查询
    //员工工资统计
    public static final String FinancialSalaryGetWXStaffPayrollListUrlType = "100510";//员工工资查询
    public static final String CompanyDepartmentListUrlType = "100511";//单位部门
    public static final String FinancialSalaryGetWXInsertPayrollUrlType = "100512";//添加工资单

}
