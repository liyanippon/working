package ui.fragement.menu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.admin.erp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Tool.statistics.Statics;
import http.AccountManagementHttpPost;
import http.BillingStatisticsHttpPost;
import http.Constants;
import http.ExpressNumberManagementHttpPost;
import http.ExpressStatisticsHttpPost;
import ui.activity.AccountManagementActivity;
import ui.activity.BillingStatisticsActivity;
import ui.activity.ExpressNumberManagerActivity;
import ui.activity.ExpressStatisticsActivity;

@SuppressLint("NewApi")
public class MainTabFinancial extends Fragment {

	private AccountManagementHttpPost httpPost;
	private BillingStatisticsHttpPost billingStatisticsHttpPost;
	private ExpressNumberManagementHttpPost expressNumberManagementHttpPost;
	private ExpressStatisticsHttpPost expressStatisticsHttpPost;
	private String typeSpinnerString = "024001";

	private GridView gridView;
	private View view;
	private List<Map<String, Object>> data_list;
	private SimpleAdapter sim_adapter;
	private Intent in;
	// 图片封装为一个数组
	private int[] icon = { R.drawable.wuliuguanli, R.drawable.wuliutongji,
			R.drawable.yewuyuanlanjian, R.drawable.yewuyuanfenxi};
	private String[] iconName = { "物流管理", "物流统计", "业务揽件", "业务统计" };
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.main_tab_financial, null);
		//http://www.cnblogs.com/tinyphp/p/3855224.html 可下载
		spinnerData();
		init();

		gridView.setOnItemClickListener(g);
		return view;
	}

	private void spinnerData() {
		//获取数据 下拉菜单
		httpPost = new AccountManagementHttpPost();
		spinner();


		//账单统计
		Statics.billingYear.clear();
		billingStatisticsHttpPost = new BillingStatisticsHttpPost();
		billingStatisticsHttpPost.searchYearHttp(Statics.YearSearchUrl);
		//快递管理
		String ExpressPersonNameSearchUrl = Statics.ExpressPersonNameSearchUrl;
		expressNumberManagementHttpPost =new ExpressNumberManagementHttpPost();
		expressNumberManagementHttpPost.expressPersionSearchHttp(ExpressPersonNameSearchUrl);
		//年份
		Statics.expressYear.clear();
		expressStatisticsHttpPost = new ExpressStatisticsHttpPost();
		expressNumberManagementHttpPost.searchExpressYearHttp(Statics.ExpressYearSearchUrl);
	}

	private void spinner() {
		//获取数据 下拉菜单
		//AllCustomer

		new Runnable() {
			@Override
			public void run() {
				httpPost.customerSearchHttp(Statics.AllCustomerUrl);
				httpPost.accountClassifySearchHttp(Statics.AccountClassifyUrl);
				httpPost.accountTypeSearchHttp(Statics.AccountTypeUrl);
				httpPost.accountReasonSearchHttp(Statics.AccountReasonUrl, Statics.accountClassify, getActivity());
			}
		}.run();
	}


	private void init() {
		gridView = (GridView) view.findViewById(R.id.tab02_grid);
		//新建List
		data_list = new ArrayList<Map<String, Object>>();
		//获取数据
		getData();
		//新建适配器
		String [] from ={"image","text"};
		int [] to = {R.id.image,R.id.text};
		sim_adapter = new SimpleAdapter(getActivity(), data_list, R.layout.menu_item, from, to);
		//配置适配器
		gridView.setAdapter(sim_adapter);
	}

	public List<Map<String, Object>> getData(){
		//cion和iconName的长度是相同的，这里任选其一都可以
		for(int i=0;i<icon.length;i++){
			Map<String, Object> map = new HashMap<>();
			map.put("image", icon[i]);
			map.put("text", iconName[i]);
			data_list.add(map);
		}

		return data_list;
	}

	AdapterView.OnItemClickListener g = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			switch (position){
				case 0:
					in = new Intent(getActivity(), AccountManagementActivity.class);
					startActivity(in);
					break;
				case 1:
					in = new Intent(getActivity(), BillingStatisticsActivity.class);
					startActivity(in);
					break;
				case 2:
					in = new Intent(getActivity(),ExpressNumberManagerActivity.class);
					startActivity(in);
					break;
				case 3:
					in = new Intent(getActivity(),ExpressStatisticsActivity.class);
					startActivity(in);
					break;
			}
		}
	};

}
