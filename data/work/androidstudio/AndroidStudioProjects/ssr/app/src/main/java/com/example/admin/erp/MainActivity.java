package com.example.admin.erp;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.util.Properties;
import Tool.crash.BaseActivity;
import Tool.crash.LogcatHelper;
import http.ExpressBillingManagementHttpPost;
import presenter.LoginPresenter;
import presenter.LoginPresenterImpl;
import service.ErpService;

public class MainActivity extends BaseActivity implements View.OnClickListener,LoginView{
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
    private LoginPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activity = MainActivity.this;
        presenter=new LoginPresenterImpl(this);
        //LogcatHelper.getInstance(this).start();//保存日志
        //presenter.initCrashHandler(activity);//系统异常处理
        super.onCreate(savedInstanceState);
        setTitle("统一登录平台");
        setContentView(R.layout.activity_main);

        initView();//查找控件
        presenter.readProperties(properties,activity);//读取配置文件
        if (sp.getBoolean("checkboxBoolean", false)){
            userName.setText(sp.getString("uname", null));
            userPassword.setText(sp.getString("upswd", null));
            rememberMe.setChecked(true);
        }
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.login:
                imm.hideSoftInputFromWindow(userName.getWindowToken(), 0); //强制隐藏键盘
                imm.hideSoftInputFromWindow(userPassword.getWindowToken(), 0); //强制隐藏键盘
                presenter.initBroadCast(activity,userNameString);
                presenter.validateCredentials(activity,httpPost,rememberMe,sp);
                break;
            case R.id.reset:
                userName.setText("");
                userPassword.setText("");
                imm.showSoftInput(userName,InputMethodManager.SHOW_FORCED);
                imm.showSoftInput(userPassword,InputMethodManager.SHOW_FORCED);
                break;
        }
    }
    private void initView(){
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
        properties = new Properties();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void showProgress() {
        loginProgressBar.setVisibility(View.VISIBLE);
    }
    @Override
    public void hideProgress() {
        loginProgressBar.setVisibility(View.GONE);
    }
    @Override
    public void toastMessage(String Message) {
        Toast.makeText(activity,Message,Toast.LENGTH_LONG).show();
    }
    @Override
    public void editorEnabled() {
        userName.setEnabled(false);
        userPassword.setEnabled(false);
    }
    @Override
    public void isClickable(boolean clickable) {
        login.setClickable(clickable);
        reset.setClickable(clickable);
    }
}
