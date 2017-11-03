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
import Tool.statistics.AchacheConstant;
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
            //缓存本地
            ACache mCache = ACache.get(activity);
            mCache.put(AchacheConstant.LOGIN_URL, IFengLoginUrl,1 * ACache.TIME_DAY);
            String IFengUrl = properties.getProperty("IFengUrl").trim();
            String UmpUrl = properties.getProperty("UmpUrl").trim();
            mCache.put(AchacheConstant.UML_URL, UmpUrl + "/setRoles/loadRoleUserId.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.FINANCIAL_BILLINGMANAGEMENT_SEARCH_URL, IFengUrl + "/wxApi.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.All_CUSTOMER_URL, IFengUrl + "/getWXAllCustomer.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.ACCOUNT_REASON_URL, IFengUrl + "/getWXExpenseAccountReason.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.ACCOUNT_CLASSIFY_URL, IFengUrl + "/getWXExpenseAccountClassify.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.ACCOUNT_TYPE_URL, IFengUrl + "/getWXExpenseAccountType.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.TIME_SEARCH_URL, IFengUrl + "/getWXTjsjList.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.CUSTOMER_SEARCH_URL, IFengUrl + "/getWXCustomerTjsjList.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.YEAR_SEARCH_URL, IFengUrl + "/getWXYearAccountStatistics.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.XQCUSTOMER_SEARCH_URL, IFengUrl + "/getWXCustomerTjxq.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.EXPRESS_YEAR_SEARCH_URL, IFengUrl + "/getWXYearNumberStatistics.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.EXPRESS_PERSSON_NAME_SEARCH_URL, IFengUrl + "/getWXAllNumber.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.EXPRESS_COUNT_SEARCH, IFengUrl + "/wxApiNumber.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.TIME_STATISTIC_SEARCH_URL, IFengUrl + "/getWXTjsjListNumber.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.EXPRESS_STATISTIC_SEARCH_URL, IFengUrl + "/getCustomerTjsjListNumber.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.EXPRESS_XQTIME_SEARCH_URL, IFengUrl + "/getCustomerTjxqNumber.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.EXPRESS_PIECE_MONTHDAY_SEARCH_URL, IFengUrl + "/getWxXAxisDay.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.EXPRESS_PIECE_DAY_SEARCH_URL, IFengUrl + "/getWxSeriesDataDay.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.EXPRESS_PERSON_PIECE_DAY_SEARCH_URL, IFengUrl + "/getWxSeriesDataAmount.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.ATTENDANCE_STATISTICS_SEARCH_URL, IFengUrl + "/getWxAllAttendanceMonthSum.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.SEARCH_YEAR_URL, IFengUrl + "/getWxAllYears.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.FINANCIAL_ACCOUNT_CUSTOMER_URL, IFengUrl + "/getWXBillCustomer.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.FINANCIAL_BILLING_MANAGEMENT_URL, IFengUrl + "/getWXBillRecords.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.FINANCIAL_BILLING_MANAGEMENT_DEL_URL, IFengUrl + "/WXdeleteBillRecords.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.ADD_FINANCIAL_BILLING_URL, IFengUrl + "/getWXInsertOrUpdateBillRecords.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.FINANCIAL_BILLING_GETWXACCOUNTS_TYPE_URL, IFengUrl + "/getWXAccountsType.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.FINANCIAL_BILLING_GETWXSETTLEMENT_MONTH_URL, IFengUrl + "/getWXsettlementMonth.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.FINANCIAL_BILLING_GETWXSELECT_MONTH_ACCOUNT_URL, IFengUrl + "/getWXSelectMonthAccount.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.FINANCIAL_BILLING_GETCURRENT_MONTH_URL, IFengUrl + "/getWXSelectAll.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.EXPRESS_GETWXPAYMENT_METHOD, IFengUrl + "/getWXPaymentMethod.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.EXPRESS_GETWXEXPENSE_ACCOUNT_PAYMENT_METHOD, IFengUrl + "/getWXExpenseAccountPaymentMethod.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.GET_WXATTENDANCE_DETA, IFengUrl + "/getWXAttendanceDeta.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.GET_WXPROJECT_SEARCH_URL, IFengUrl + "/getWXProject.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.GET_WXEXPENSE_ACCOUNT_REASON_URL, IFengUrl + "/getWXExpenseAccountReason.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.GET_WXNEWADD_URL, IFengUrl + "/getWXNewAdd.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.FINANCIAL_BILLING_GETWX_SELECE_CUSTOMER_DETAILS, IFengUrl + "/getWXNewAdd.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.ATTENDANCE_GETWX_ATTENDANCE_NAME_URL, IFengUrl + "/getWXAttendanceName.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.FINANCIAL_SALARY_GETWXSTAFF_PAYROLL_LIST_URL, IFengUrl + "/getWXStaffPayrollList.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.FINANCIAL_SALARY_GETWXORG_LIST_URL, IFengUrl + "/getWXOrg.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.FINANCIAL_SALARY_GETWXINSERT_PAYROLL_URL, IFengUrl + "/getWXInsertPayroll.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.PROJECT_GETWXLOAD_PROJECT_PAGEDATA_URL, IFengUrl + "/getWXLoadProjectPageData.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.PROJECT_GETWXPROJECT_CYCLE_URL, IFengUrl + "/getWXProjectCycle.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.PROJECT_GETWXLOAD_PROJECT_PEOPLE_PAGE_DATA_URL, IFengUrl + "/getWXLoadProjectPeoplePageData.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.RESOURCE_GETWXWXP_PAGEDATA_RESOURCE_URL, IFengUrl + "/getWXPageDataResource.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.RESOURCE_GETWXPAGEDATA_RESOURCE_PROJECT_URL, IFengUrl + "/getWXPageDataResourceProjects.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.RESOURCE_GETWXEXTERIOR_PROJECTS_URL, IFengUrl + "/getWXExteriorProjects.ajax",1 * ACache.TIME_DAY);
            mCache.put(AchacheConstant.RESOURCE_GETDOWNLOADFILE_URL, IFengUrl + "/downloadFile.ajax",1 * ACache.TIME_DAY);
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
