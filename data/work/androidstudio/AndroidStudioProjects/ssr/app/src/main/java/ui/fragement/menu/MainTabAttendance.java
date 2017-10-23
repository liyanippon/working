package ui.fragement.menu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.admin.erp.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Tool.ACache;
import Tool.ToolUtils;
import Tool.statistics.Statics;
import Tool.statistics.UmlStatic;
import http.HttpBasePost;
import http.HttpTypeConstants;
import model.javabean.UserUmp;
import ui.activity.AttendanceCardActivity;
import ui.activity.AttendanceStatisticsActivity;

@SuppressLint("NewApi")
public class MainTabAttendance extends Fragment {
	private GridView gridView;
	private View view;
	private List<Map<String, Object>> data_list;
	private SimpleAdapter sim_adapter;
	private Intent in;
	// 图片封装为一个数组
	//private int[] icon = { R.drawable.kaoqintongji};
	//private String[] iconName = { "考勤统计"};
	//动态菜单
	private ArrayList<Object> icon;
	private ArrayList<Object> iconName;
	private long exitTime = 0;
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.main_tab_works, null);
		//http://www.cnblogs.com/tinyphp/p/3855224.html 可下载
		spinnerData();
		init();
		gridView.setOnItemClickListener(g);
		return view;
	}

	private void spinnerData() {
		//获取数据 下拉菜单
		//年份查询
		//httpPost.searchTimeYearHttp(Statics.searchYearUrl,getActivity());
		new Runnable() {
			@Override
			public void run() {
				//httpPost.searchStaffNameHttp(Statics.AttendanceStatisticsSearchUrl,getActivity());
				HttpBasePost.postHttp(Statics.AttendanceGetWXAttendanceNameUrl,null, HttpTypeConstants.SearchStuffName);//员工姓名
				HttpBasePost.postHttp(Statics.searchYearUrl,null,HttpTypeConstants.SearchYearUrlType);//年份查询
			}
		}.run();
	}

	private void init() {
		gridView = (GridView) view.findViewById(R.id.tab_grid);
		//新建List
		data_list = new ArrayList<>();
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

		//设定菜单显示(菜单控制)
		icon = new ArrayList<>();
		iconName = new ArrayList<>();
		Map<String,ArrayList<Object>> mapUML= UmlStatic.menuAttendanceController(icon,iconName);//调用财务管理菜单
		//去除重复元素
		ArrayList<Object> reIcon=mapUML.get("icon");
		ArrayList<Object> reIconName=mapUML.get("iconName");
		//调用公共方法
		reIcon= ToolUtils.removeDuplicate(reIcon);
		reIconName=ToolUtils.removeDuplicate(reIconName);
		for(int i=0;reIcon!=null&&reIconName!=null&&i<reIcon.size();i++){
			Map<String, Object> map = new HashMap<>();
			map.put("image", reIcon.get(i));
			map.put("text", reIconName.get(i));
			data_list.add(map);
		}

		return data_list;
	}

	@Override
	public void onResume() {
		super.onResume();

		/*ACache mCache = ACache.get(getActivity());
		//String result = mCache.getAsString("uml");
		//mCache.clear();清空所有数据 mCache.remove(key);移除某个数据
		Statics.userUmpsStatisticsList = (ArrayList<UserUmp>) mCache.getAsObject("uml");
		Toast.makeText(getActivity(), Statics.userUmpsStatisticsList.size()+"", Toast.LENGTH_SHORT).show();*/
		/*//json数据使用Gson框架解析
		Statics.userUmpsStatisticsList.clear();
		UserUmp[] as = new Gson().fromJson(result, UserUmp[].class);
		Collections.addAll(Statics.userUmpsStatisticsList, as);//转化arrayList*/
		/*spinnerData();
		init();
		gridView.setOnItemClickListener(g);*/

	}


	AdapterView.OnItemClickListener g = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			switch (data_list.get(position).get("text").toString()){
				case "考勤打卡":
					exitTime = ToolUtils.muchClick(exitTime);
					if(exitTime!=0){
						exitTime = System.currentTimeMillis();
						in = new Intent(getActivity(), AttendanceCardActivity.class);//考勤打卡
						startActivity(in);
					}
					break;
				case "考勤统计":
					exitTime = ToolUtils.muchClick(exitTime);
					if(exitTime!=0){
						exitTime = System.currentTimeMillis();
						in = new Intent(getActivity(), AttendanceStatisticsActivity.class);//考勤统计
						startActivity(in);
					}else {
						Toast.makeText(getContext(), "小于3秒", Toast.LENGTH_SHORT).show();
					}
					break;
			}
		}
	};

}
