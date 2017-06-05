package com.example.admin.html5;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by admin on 2017/6/5.
 */

public class JavaScriptObject {
    private Context mContxt;
    public JavaScriptObject(Context mContxt) {
        this.mContxt = mContxt;
    }
    @JavascriptInterface
    public void fun2(String name,String psd) {
        if(name.equals("liyan") && psd.equals("123456")){
            Toast.makeText(mContxt, "登录成功", Toast.LENGTH_SHORT).show();
            Intent in = new Intent(mContxt,MainActivity.class);
            this.mContxt.startActivity(in);
        }
    }
    @JavascriptInterface
    public  void register(String name,String psd,String sex,String age,String phone,String address){
        Log.d("JavaScriptObject", name + psd + sex + age + phone + address);
    }

    @JavascriptInterface
    public  void registerController(String integer){
        if(integer.equals("register")){
            Intent in = new Intent(mContxt,RegisterActivity.class);
            this.mContxt.startActivity(in);
        }
    }
}
