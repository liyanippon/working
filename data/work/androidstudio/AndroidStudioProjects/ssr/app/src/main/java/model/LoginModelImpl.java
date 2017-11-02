package model;
import android.app.Activity;
import android.graphics.Color;
import com.example.admin.erp.MainActivity;
import com.example.admin.erp.OnLoginFinishedListener;
import Tool.statistics.Statics;
import http.ExpressBillingManagementHttpPost;
public class LoginModelImpl implements LoginModel {
    @Override
    public void login(ExpressBillingManagementHttpPost httpPost, String username, String password, OnLoginFinishedListener listener, Activity activity) {
        listener.editorEnabled();
        listener.showProgress();
        MainActivity.login.setBackgroundColor(Color.rgb(0x66,0x66,0x66));
        listener.isClickable(false);//登录与重置按钮不可用
        String urlString = Statics.LoginUrl;
        httpPost = new ExpressBillingManagementHttpPost(activity);
        httpPost.LoginHttp(urlString, username, password, activity);
    }
}