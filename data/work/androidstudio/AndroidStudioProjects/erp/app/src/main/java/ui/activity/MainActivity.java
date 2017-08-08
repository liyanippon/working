package ui.activity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.admin.erp.R;

import java.util.Properties;

import Tool.crash.BaseActivity;
import Tool.crash.CrashHandler;
import Tool.crash.LogcatHelper;
import Tool.statistics.Statics;
import broadcast.Config;
import broadcast.FreshenBroadcastReceiver;
import http.ExpressBillingManagementHttpPost;
import portface.LazyLoadFace;
import ui.activity.menu.MenuFragmentMainActivity;
public class MainActivity extends BaseActivity {
    public static EditText userName;
    public static EditText userPassword;
    private ExpressBillingManagementHttpPost httpPost;
    public static Button login;
    public static Button reset;
    private Properties properties;
    public static Intent in;
    public static Activity activity;
    public static boolean flag = true;
    private String userNameString;
    private CheckBox rememberMe;
    SharedPreferences sp = null;
    private RelativeLayout background;
    public static ProgressBar loginProgressBar;
    private InputMethodManager imm;//键盘处理
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogcatHelper.getInstance(this).start();//保存日志
        initCrashHandler();//系统异常处理
        super.onCreate(savedInstanceState);
        setTitle("统一登录平台");
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "10080");
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        background = (RelativeLayout) findViewById(R.id.activity_main2);
        userName = (EditText) findViewById(R.id.userName);
        userPassword = (EditText) findViewById(R.id.userPassword);
        login = (Button) findViewById(R.id.login);
        reset = (Button) findViewById(R.id.reset);
        rememberMe = (CheckBox) findViewById(R.id.rememberMe);
        loginProgressBar = (ProgressBar) findViewById(R.id.login_progressBar);
        login.setOnClickListener(this);
        reset.setOnClickListener(this);
        init();
        if (sp.getBoolean("checkboxBoolean", false)){
            userName.setText(sp.getString("uname", null));
            userPassword.setText(sp.getString("upswd", null));
            rememberMe.setChecked(true);
        }
    }
    private void initCrashHandler() {
        CrashHandler crashHandler = CrashHandler.getInstance();
        // 注册crashHandler
        crashHandler.init(getApplicationContext());
    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
    private void initBroadCast() {
        //广播初始化 必须动态注册才能实现回调
        FreshenBroadcastReceiver broadcast = new FreshenBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Config.BC_ONE);
        getApplicationContext().registerReceiver(broadcast, intentFilter);
        broadcast.setLazyLoadFace(new LazyLoadFace() {
            @Override
            public void AdapterRefresh(String type) {
                //具体更新
                if(type.equals("login")){
                    //login.setBackgroundColor(Color.GRAY);
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    in = new Intent(MainActivity.this, MenuFragmentMainActivity.class);
                    flag = false;
                    Statics.Name = userNameString ;
                    startActivity(in);
                    finish();
                }else{//登录失败移除activity
                    Log.d("MainActivity", "移除activity");
                }
            }
        });
    }
    /*
    * 读取配置文件 拼接所有的url
    */
    private void init() {
        properties = new Properties();
        try {
            properties.load(MainActivity.this.getAssets().open("property1.properties"));
            String IFengLoginUrl = properties.getProperty("IFengLoginUrl").trim();
            Statics.LoginUrl = IFengLoginUrl + "/identify/login.jhtml"; //外网登录
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case R.id.login:
                userName.setEnabled(false);
                userPassword.setEnabled(false);
                imm.hideSoftInputFromWindow(userName.getWindowToken(), 0); //强制隐藏键盘
                imm.hideSoftInputFromWindow(userPassword.getWindowToken(), 0); //强制隐藏键盘
                check();
                break;
            case R.id.reset:
                userName.setText("");
                userPassword.setText("");
                imm.showSoftInput(userName,InputMethodManager.SHOW_FORCED);
                imm.showSoftInput(userPassword,InputMethodManager.SHOW_FORCED);
                break;
        }
    }
    private void check() { //对输入进行判断性检查
        initBroadCast();
        flag = true;
        userNameString = userName.getText().toString().trim();
        String password = userPassword.getText().toString().trim();
        if ("".equals(userNameString) || "".equals(password)) {
            Toast.makeText(MainActivity.this, "用户名或密码不能为空", Toast.LENGTH_LONG).show();
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
        loginProgressBar.setVisibility(View.VISIBLE);
        login.setBackgroundColor(Color.rgb(0x66,0x66,0x66));
        login.setClickable(false);
        reset.setClickable(false);
        String urlString = Statics.LoginUrl;
        httpPost = new ExpressBillingManagementHttpPost(getApplicationContext());
        httpPost.LoginHttp(urlString, userNameString, password, MainActivity.this);
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
