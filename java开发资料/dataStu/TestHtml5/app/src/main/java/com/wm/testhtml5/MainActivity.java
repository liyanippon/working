package com.wm.testhtml5;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import android.app.Activity;
import android.graphics.Color;

public class MainActivity extends Activity {

	private WebView mWebView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		init();		
	}
	
	public void onTestClick(View v) {
		switch (v.getId()) {
		case R.id.test_btn:
			mWebView.loadUrl("file:///android_asset/pages/button.html");
			break;

		case R.id.test_listview:
			mWebView.loadUrl("file:///android_asset/pages/listview.html");
			break;
			
		case R.id.test_collspae:
			mWebView.loadUrl("file:///android_asset/pages/collapsible.html");
			break;
		}
	}
	
	private void init() {
		initWebView();
		// TODO
	}

	private void initWebView() {
		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.setHorizontalScrollBarEnabled(false);
		mWebView.setVerticalScrollBarEnabled(false);
		mWebView.addJavascriptInterface(new WebViewHandleFactory(), "$$");	
		mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		mWebView.setBackgroundColor(0);
		mWebView.setBackgroundColor(Color.RED); 
		
		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);		
		// 优先使用缓存
		// settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		// 不使用缓存
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		// 支持通过JS打开新窗口
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		// 支持插件
		//settings.setPluginsEnabled(true); //以前是可以用的
		
		// 将图片调整到适合WebView的大小
		// settings.setUseWideViewPort(false);
		// 支持缩放
		// settings.setSupportZoom(true);
		// 支持内容从新布局
		// settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		// 多窗口
		settings.supportMultipleWindows();
		// 设置可以访问文件
		settings.setAllowFileAccess(true);
		// 当WebView调用requestFocus时为WebView设置节点
		settings.setNeedInitialFocus(true);
		// 设置支持缩放
		// settings.setBuiltInZoomControls(true);
		// 支持通过JS打开新窗口
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		// 支持自动加载图片
		settings.setLoadsImagesAutomatically(true);
	}

	@Override
	protected void onDestroy() {
		mWebView.clearCache(true);
		mWebView.clearHistory();
		
		super.onDestroy();
	}

	/*******供JavaScript调用的方法*************************************************************************/
	public class WebViewHandleFactory {
				
		public void info(String msg) {
			Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
		}
		
		// TODO append callback methods
		
	}
	
}
