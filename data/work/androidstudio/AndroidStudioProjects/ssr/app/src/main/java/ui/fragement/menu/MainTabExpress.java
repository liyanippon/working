package ui.fragement.menu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import Tool.ACache;
import Tool.ToolUtils;
import Tool.statistics.AchacheConstant;
import Tool.statistics.Statics;
import Tool.statistics.UmlStatic;
import http.ExpressBillingManagementHttpPost;
import http.BillingStatisticsHttpPost;
import http.ExpressNumberManagementHttpPost;
import http.ExpressStatisticsHttpPost;
import http.HttpBasePost;
import http.HttpTypeConstants;
import ui.activity.BillingStatisticsActivity;
import ui.activity.ExpressBillingManagementActivity;
import ui.activity.ExpressNumberManagerActivity;
import ui.activity.ExpressStatisticsActivity;
import ui.activity.LogisticsReportActivity;

@SuppressLint("NewApi")
public class MainTabExpress extends Fragment implements AdapterView.OnItemClickListener{
	private ExpressBillingManagementHttpPost httpPost;
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
	//private int[] icon = { R.drawable.wuliuguanli, R.drawable.wuliutongji,
	//		R.drawable.yewuyuanlanjian, R.drawable.yewuyuanfenxi};
	//private String[] iconName = { "物流管理", "物流统计", "业务揽件", "业务统计" };
	//动态菜单
	private ArrayList<Object> icon;
	private ArrayList<Object> iconName;
	private HashMap<String,String> param;
	private long exitTime = 0;
	ACache aCache;
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.main_tab_works, null);
		//http://www.cnblogs.com/tinyphp/p/3855224.html 可下载
		aCache = ACache.get(getActivity());
		spinnerData();
		init();

		gridView.setOnItemClickListener(this);
		return view;
	}


	private void spinnerData() {
		//获取数据 下拉菜单
		httpPost = new ExpressBillingManagementHttpPost();
		spinner();
		//账单统计
		Statics.billingYear.clear();
		billingStatisticsHttpPost = new BillingStatisticsHttpPost();
		billingStatisticsHttpPost.searchYearHttp(aCache.getAsString(AchacheConstant.YEAR_SEARCH_URL));
		//快递管理
		//String ExpressPersonNameSearchUrl = Statics.ExpressPersonNameSearchUrl;
		expressNumberManagementHttpPost =new ExpressNumberManagementHttpPost();
		expressNumberManagementHttpPost.expressPersionSearchHttp(aCache.getAsString(AchacheConstant.EXPRESS_PERSSON_NAME_SEARCH_URL));
		//年份
		Statics.expressYear.clear();
		expressStatisticsHttpPost = new ExpressStatisticsHttpPost();
		expressNumberManagementHttpPost.searchExpressYearHttp(aCache.getAsString(AchacheConstant.EXPRESS_YEAR_SEARCH_URL));
	}

	private void spinner() {
		//获取数据 下拉菜单
		//AllCustomer
				httpPost.customerSearchHttp(aCache.getAsString(AchacheConstant.All_CUSTOMER_URL),getActivity());
				HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.ACCOUNT_CLASSIFY_URL),null,HttpTypeConstants.ExpressClassifyUrlType);//进账出账下拉框
				param=new HashMap<>();
				param.put("option","3");
				HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.ACCOUNT_CLASSIFY_URL),param,HttpTypeConstants.TransferAccountType);//转账下拉菜单
				httpPost.accountTypeSearchHttp(aCache.getAsString(AchacheConstant.ACCOUNT_TYPE_URL),getActivity());
				httpPost.accountReasonSearchHttp(aCache.getAsString(AchacheConstant.ACCOUNT_REASON_URL), Statics.accountClassify, getActivity());
				Log.d("MainTabExpress", "支付方式");
				HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.EXPRESS_GETWXEXPENSE_ACCOUNT_PAYMENT_METHOD) //支付方式下拉菜单
				,null, HttpTypeConstants.ExpressGetWXExpenseAccountPaymentMethod);
	}

	private void init() {
		gridView = (GridView) view.findViewById(R.id.tab_grid);
		//新建List
		data_list = new ArrayList<Map<String, Object>>();
		//获取数据
		data_list = getData();
		//新建适配器
		String[] from ={"image","text"};
		int [] to = {R.id.image,R.id.text};
		sim_adapter = new SimpleAdapter(getActivity(), data_list, R.layout.menu_item, from, to);
		//配置适配器
		gridView.setAdapter(sim_adapter);
	}

	public List<Map<String, Object>> getData(){
		//cion和iconName的长度是相同的，这里任选其一都可以
		//设定菜单显示(菜单控制)
		icon = new ArrayList<>();
		iconName = new ArrayList<>();
		Map<String,ArrayList<Object>> mapUML=UmlStatic.menuExpressController(icon,iconName);//调用物流管理菜单
		//去除重复元素
		ArrayList<Object> reIcon=mapUML.get("icon");
		ArrayList<Object> reIconName=mapUML.get("iconName");
		//调用公共方法
		reIcon= ToolUtils.removeDuplicate(reIcon);
		reIconName=ToolUtils.removeDuplicate(reIconName);
		for(int i=0;reIcon!=null&&reIconName!=null&&i<reIcon.size();i++){
			Map<String, Object> map = new HashMap<>();
			map.put("image", reIcon.get(i));
			map.put("text",	  reIconName.get(i));
			Log.d("umm",reIconName.get(i)+"");
			data_list.add(map);
		}
		return data_list;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (data_list.get(position).get("text").toString()){
			case "物流管理":
				exitTime = ToolUtils.muchClick(exitTime);
				if(exitTime!=0) {
					exitTime = System.currentTimeMillis();
					in = new Intent(getActivity(), ExpressBillingManagementActivity.class);
					startActivity(in);
				}
				break;
			case "物流统计":
				exitTime = ToolUtils.muchClick(exitTime);
				if(exitTime!=0) {
					exitTime = System.currentTimeMillis();
					in = new Intent(getActivity(), BillingStatisticsActivity.class);
					startActivity(in);
				}
				break;
			case "统计报表":
				exitTime = ToolUtils.muchClick(exitTime);
				if(exitTime!=0) {
					exitTime = System.currentTimeMillis();
					in = new Intent(getActivity(), LogisticsReportActivity.class);
					startActivity(in);
				}
				break;
			case "业务揽件":
				exitTime = ToolUtils.muchClick(exitTime);
				if(exitTime!=0) {
					exitTime = System.currentTimeMillis();
					in = new Intent(getActivity(), ExpressNumberManagerActivity.class);
					startActivity(in);
				}
				break;
			case "业务统计":
				exitTime = ToolUtils.muchClick(exitTime);
				if(exitTime!=0) {
					exitTime = System.currentTimeMillis();
					in = new Intent(getActivity(), ExpressStatisticsActivity.class);
					startActivity(in);
				}
				break;
		}
	}
}
