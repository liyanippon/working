package ui.activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.admin.erp.R;
import Tool.ToolUtils;
import Tool.crash.BaseActivity;

public class OfficeOnlineActivity extends BaseActivity{
    //private static final String TEST_PAGE_URL = "https://view.officeapps.live.com/op/view.aspx?src=" +"https://newgtlds.icann.org/sites/default/files/agreements/agreement-approved-31jul17-en.docx";
    private static final String TEST_PAGE_URL = "https://view.officeapps.live.com/op/view.aspx?src=" +"http://192.168.1.56:8083/downloadFile.ajax?id=139417381583da551c195f14e7fb848";
    private WebView mWebView;
    private long exitTime = 0;
    private ProgressBar pg1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("个人简历");
        setContentView(R.layout.activity_office_online);

        //添加返回按钮
        ToolUtils.backButton(this);
        mWebView = (WebView) findViewById(R.id.office);
        pg1=(ProgressBar) findViewById(R.id.progressBar1);
        WebSettings seting=mWebView.getSettings();
        seting.setJavaScriptEnabled(true);//设置webview支持javascript脚本
        mWebView.setWebViewClient(new WebViewClient() {
            //设置在webView点击打开的新网页在当前界面显示,而不跳转到新的浏览器中
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO 自动生成的方法存根

                if(newProgress==100){
                    pg1.setVisibility(View.GONE);//加载完网页进度条消失
                }
                else{
                    pg1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    pg1.setProgress(newProgress);//设置进度值
                }

            }
        });

        mWebView.getSettings().setJavaScriptEnabled(true);  //设置WebView属性,运行执行js脚本
        mWebView.loadUrl(TEST_PAGE_URL);          //调用loadUrl方法为WebView加入链接
        //setContentView(mWebView);                           //调用Activity提供的setContentView将webView显示出来
    }

    //返回按钮事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                exitTime = System.currentTimeMillis();
                this.finish(); // back button
            } else {
                super.onBackPressed();
            }

        }
    }

    //添加返回按钮
    public static void backButton(AppCompatActivity appCompatActivity) {
        android.support.v7.app.ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
