package ui.activity;

import android.content.Intent;
import android.os.Environment;
import android.os.Bundle;
import com.example.admin.erp.R;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import java.io.File;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import Tool.ToolUtils;
import Tool.crash.BaseActivity;
import Tool.office.WordReader;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import ui.adpter.SourceManagementAdapter;

/**
 * 打开本地文档
 * */
public class OfficeDirActivity extends BaseActivity {
    //请在内存下放个文件
    //public static String pathStr = Environment.getExternalStorageDirectory()+"/erp/person_history/";
    //Environment.getExternalStorageDirectory()getRootDirectory()//获取手机根目录
    public WebView wv_view;
    WordReader fr=null;
    private WebSettings webSettings;
    private String fileName;
    private String pathStr;
    private ProgressBar pg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("简历预览");
        setContentView(R.layout.activity_office_dir);

        //添加返回按钮
        ToolUtils.backButton(this);
        Intent intent = getIntent();
        if(intent!=null) {
            fileName = intent.getStringExtra("fileName");
            pathStr = intent.getStringExtra("pathStr");
        }
        pg1=(ProgressBar) findViewById(R.id.progressBar1);
        wv_view = (WebView)findViewById(R.id.wv_view);
        webSettings = wv_view.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        wv_view.setHapticFeedbackEnabled(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//关键点
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setDisplayZoomControls(false);

        rx.Observable.just(pathStr+fileName).map(new Func1<String, String>() {
                @Override
                public String call(String s) {
                    fr=new WordReader(s);
                    return fr.returnPath;
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String s) {
                            //拿到call方法对"test"的数据进行处理的结果
                            if (wv_view!=null){
                                wv_view.loadUrl(s);
                                webSettings.setLoadWithOverviewMode(true);
                                // parseFinishListenner.onParseFinshed();
                                // dialog.dismiss();
                            }
                        }
                    });
        //进度条
        wv_view.setWebChromeClient(new WebChromeClient(){
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


}
