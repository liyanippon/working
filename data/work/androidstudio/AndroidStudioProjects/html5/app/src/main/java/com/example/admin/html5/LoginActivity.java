package com.example.admin.html5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private WebView view;
    private JavaScriptObject js;
    private TextView titleTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        view=(WebView) findViewById(R.id.web);
        titleTv=(TextView) findViewById(R.id.text);
        js=new JavaScriptObject(this);
        view.getSettings().setDefaultTextEncodingName("utf-8");
        view.getSettings().setJavaScriptEnabled(true);
        view.addJavascriptInterface(js, "myObj");
        view.loadUrl("file:///android_asset/pages/login.html");
        view.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            // 出现错误是的回调
            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                // TODO Auto-generated method stub
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        view.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                // 设置标题
                super.onReceivedTitle(view, title);
                if (titleTv != null) {
                    titleTv.setText(title);
                }
            }

            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                // TODO Auto-generated method stub
                super.onShowCustomView(view, callback);
            }
        });
    }
}
