package presenter;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.CheckBox;
import com.example.admin.erp.LoginView;
import com.example.admin.erp.MainActivity;
import com.example.admin.erp.OnLoginFinishedListener;

import java.util.ArrayList;
import java.util.Properties;

import Tool.ACache;
import Tool.crash.CrashHandler;
import Tool.statistics.Statics;
import broadcast.Config;
import broadcast.FreshenBroadcastReceiver;
import http.ExpressBillingManagementHttpPost;
import model.LoginModel;
import model.LoginModelImpl;
import portface.LazyLoadFace;
import ui.activity.menu.MenuFragmentMainActivity;
/**
 * Created by admin on 2017/8/21.
 */
public class LoginPresenterImpl implements LoginPresenter,OnLoginFinishedListener {
    private LoginView loginView;
    private LoginModel loginModel;
    public LoginPresenterImpl() {
    }
    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginModel = new LoginModelImpl();
    }
    /*
    * 读取配置文件 拼接所有的url
    */
    @Override
    public void readProperties(Properties properties, Activity activity) {
        try {
            properties.load(activity.getAssets().open("property1.properties"));
            String IFengLoginUrl = properties.getProperty("IFengLoginUrl").trim();
            Statics.LoginUrl = IFengLoginUrl + "/identify/login.jhtml"; //外网登录
            ArrayList<String> urlList = new ArrayList<>();
            //Statics.LoginUrl = IFengLoginUrl;//本地登录
            String IFengUrl = properties.getProperty("IFengUrl").trim();
            String UmpUrl = properties.getProperty("UmpUrl").trim();
            Statics.UmlUrl = UmpUrl + "/setRoles/loadRoleUserId.ajax";
            Statics.FinancialBillingManagementSearchUrl = IFengUrl + "/wxApi.ajax";
            Statics.AllCustomerUrl = IFengUrl + "/getWXAllCustomer.ajax";
            Statics.AccountReasonUrl = IFengUrl + "/getWXExpenseAccountReason.ajax";
            Statics.AccountClassifyUrl = IFengUrl + "/getWXExpenseAccountClassify.ajax";
            Statics.AccountTypeUrl = IFengUrl + "/getWXExpenseAccountType.ajax";
            Statics.TimeSearchUrl = IFengUrl + "/getWXTjsjList.ajax";
            Statics.CustomerSearchUrl = IFengUrl + "/getWXCustomerTjsjList.ajax";
            Statics.YearSearchUrl = IFengUrl + "/getWXYearAccountStatistics.ajax";
            Statics.XqCustomerSearchUrl = IFengUrl + "/getWXCustomerTjxq.ajax";
            Statics.ExpressYearSearchUrl = IFengUrl + "/getWXYearNumberStatistics.ajax";
            Statics.ExpressPersonNameSearchUrl = IFengUrl + "/getWXAllNumber.ajax";
            Statics.ExpressCountSearch = IFengUrl + "/wxApiNumber.ajax";
            Statics.TimeStatisticSearchUrl = IFengUrl + "/getWXTjsjListNumber.ajax";
            Statics.ExpressStatisticSearchUrl = IFengUrl + "/getCustomerTjsjListNumber.ajax";
            Statics.ExpressXqTimeSearchUrl = IFengUrl + "/getCustomerTjxqNumber.ajax";
            Statics.ExpressPieceMonthDaySearchUrl = IFengUrl + "/getWxXAxisDay.ajax";
            Statics.ExpressPieceDaySearchUrl = IFengUrl + "/getWxSeriesDataDay.ajax";
            Statics.ExpressPersonPieceDaySearchUrl = IFengUrl + "/getWxSeriesDataAmount.ajax";
            Statics.AttendanceStatisticsSearchUrl = IFengUrl + "/getWxAllAttendanceMonthSum.ajax";
            Statics.searchYearUrl = IFengUrl + "/getWxAllYears.ajax";
            Statics.FinancialAccountCustomerUrl = IFengUrl + "/getWXBillCustomer.ajax";
            Statics.FinancialBillingManagementUrl = IFengUrl + "/getWXBillRecords.ajax";
            Statics.FinancialBillingManagementDelUrl = IFengUrl + "/WXdeleteBillRecords.ajax";
            Statics.AddFinancialBillingUrl = IFengUrl + "/getWXInsertOrUpdateBillRecords.ajax";
            Statics.FinancialBillingGetWXAccountsTypeUrl = IFengUrl + "/getWXAccountsType.ajax";
            Statics.FinancialBillingGetWXsettlementMonthUrl = IFengUrl + "/getWXsettlementMonth.ajax";
            Statics.FinancialBillingGetWXSelectMonthAccountUrl = IFengUrl + "/getWXSelectMonthAccount.ajax";
            Statics.FinancialBillingGetCurrentMoneyUrl = IFengUrl + "/getWXSelectAll.ajax";
            Statics.ExpressGetWXPaymentMethod = IFengUrl + "/getWXPaymentMethod.ajax";
            Statics.ExpressGetWXExpenseAccountPaymentMethod = IFengUrl + "/getWXExpenseAccountPaymentMethod.ajax";
            Statics.GetWXAttendanceDetaSearchUrl = IFengUrl + "/getWXAttendanceDeta.ajax";
            Statics.GetWXProjectSearchUrl = IFengUrl + "/getWXProject.ajax";
            Statics.GetWXExpenseAccountReasonUrl = IFengUrl + "/getWXExpenseAccountReason.ajax";//转账业务类型
            Statics.GetWXNewAddUrl = IFengUrl + "/getWXNewAdd.ajax";//转账提交单
            Statics.FinancialBillingGetWXSelectCustomerDetails = IFengUrl + "/getWXSelectCustomerDetails.ajax";//账目统计详细查询（客户）
            Statics.AttendanceGetWXAttendanceNameUrl = IFengUrl + "/getWXAttendanceName.ajax";//员工姓名
            Statics.FinancialSalaryGetWXStaffPayrollListUrl = IFengUrl + "/getWXStaffPayrollList.ajax";//员工工资查询数据
            Statics.FinancialSalaryGetWXOrgListUrl = IFengUrl + "/getWXOrg.ajax";//企业部门信息
            Statics.FinancialSalaryGetWXInsertPayrollUrl = IFengUrl + "/getWXInsertPayroll.ajax";//添加考勤
            Statics.ProjectGetWXLoadProjectPageDataUrl = IFengUrl + "/getWXLoadProjectPageData.ajax";//查询所有项目
            Statics.ProjectGetWXProjectCycleUrl = IFengUrl + "/getWXProjectCycle.ajax";//项目周期查询
            Statics.ProjectGetWXLoadProjectPeoplePageDataUrl = IFengUrl + "/getWXLoadProjectPeoplePageData.ajax";//成员加入情况
            Statics.ResourceGetWXWXPageDataResourceUrl = IFengUrl + "/getWXPageDataResource.ajax";//人力资源池查询
            Statics.ResourceGetWXPageDataResourceProjectUrl = IFengUrl + "/getWXPageDataResourceProjects.ajax";//人力资源池外部项目查询
            Statics.ResourceGetWXExteriorProjectsUrl = IFengUrl + "/getWXExteriorProjects.ajax";//人力资源池外部项目所有查询
            Statics.ResourceGetDownLoadFileUrl = IFengUrl + "/downloadFile.ajax";//简历word请求地址

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    * 对登录进行检查
    */
    @Override
    public void validateCredentials(Activity activity, ExpressBillingManagementHttpPost httpPost, CheckBox rememberMe,SharedPreferences sp) {//登录校验
        MainActivity.flag = true;
        String userNameString = MainActivity.userName.getText().toString().trim();
        String password = MainActivity.userPassword.getText().toString().trim();
        if ("".equals(userNameString) || "".equals(password)) {
            //Toast.makeText(activity, "用户名或密码不能为空", Toast.LENGTH_LONG).show();
            toastMessage("用户名或密码不能为空");
            return;
        }
        boolean CheckBoxLogin = rememberMe.isChecked();
        if (CheckBoxLogin)
        {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("uname", userNameString);
            editor.putString("upswd", password);
            editor.putBoolean("checkboxBoolean", true);
            editor.commit();
        }
        else
        {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("uname", null);
            editor.putString("upswd", null);
            editor.putBoolean("checkboxBoolean", false);
            editor.commit();
        }
        loginModel.login(httpPost,userNameString,password,this,activity);
    }
    /*
        * 广播初始化
        */
    @Override
    public void initBroadCast(final Activity activity, final String userNameString) {
        //广播初始化 必须动态注册才能实现回调
        FreshenBroadcastReceiver broadcast = new FreshenBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Config.BC_ONE);
        activity.registerReceiver(broadcast, intentFilter);
        broadcast.setLazyLoadFace(new LazyLoadFace() {
            @Override
            public void AdapterRefresh(String type) {
                //具体更新
                if(type.equals("login")){
                    //login.setBackgroundColor(Color.GRAY);
                    toastMessage("登录成功");
                    Intent in = new Intent(activity, MenuFragmentMainActivity.class);
                    MainActivity.flag = false;
                    //Statics.Name = userNameString;
                    activity.startActivity(in);
                    activity.finish();
                }else{//登录失败移除activity
                    Log.d("MainActivity", "移除activity");
                }
            }
        });
    }
    @Override
    public void initCrashHandler(Activity activity) {
        CrashHandler crashHandler = CrashHandler.getInstance();
        // 注册crashHandler
        crashHandler.init(activity);
    }
    @Override
    public void editorEnabled() {
        loginView.editorEnabled();
    }
    @Override
    public void toastMessage(String Message) {
        loginView.toastMessage(Message);
    }
    @Override
    public void showProgress() {
        loginView.showProgress();
    }
    @Override
    public void hideProgress() {
        loginView.hideProgress();
    }
    @Override
    public void isClickable(boolean clickable) {
        loginView.isClickable(clickable);
    }
}
