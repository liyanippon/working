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
import com.example.admin.erp.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Tool.statistics.Statics;
import http.AttendanceStatisticsHttpPost;
import http.Constants;
import ui.activity.AttendanceStatisticsActivity;

@SuppressLint("NewApi")
public class MainTabAttendance extends Fragment {
	private GridView gridView;
	private View view;
	private List<Map<String, Object>> data_list;
	private SimpleAdapter sim_adapter;
	private Intent in;
	// 图片封装为一个数组
	private int[] icon = { R.drawable.kaoqintongji};
	private String[] iconName = { "考勤统计"};
	private AttendanceStatisticsHttpPost httpPost;
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.main_tab_attendance, null);
		//http://www.cnblogs.com/tinyphp/p/3855224.html 可下载
		spinnerData();
		init();
		gridView.setOnItemClickListener(g);
		return view;
	}

	private void spinnerData() {
		//获取数据 下拉菜单
		httpPost = new AttendanceStatisticsHttpPost();
		//年份查询
		httpPost.searchTimeYearHttp(Statics.searchYearUrl,getActivity());
		new Runnable() {
			@Override
			public void run() {
				httpPost.searchStaffNameHttp(Statics.AttendanceStatisticsSearchUrl,getActivity());
			}
		}.run();
	}

	private void init() {
		gridView = (GridView) view.findViewById(R.id.tab01_grid);
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
					in = new Intent(getActivity(), AttendanceStatisticsActivity.class);//考勤统计
					startActivity(in);
					break;

			}
		}
	};


}