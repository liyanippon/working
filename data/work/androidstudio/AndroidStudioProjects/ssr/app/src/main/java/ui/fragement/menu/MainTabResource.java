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

import Tool.ToolUtils;
import Tool.statistics.Statics;
import Tool.statistics.UmlStatic;
import http.HttpBasePost;
import http.HttpTypeConstants;
import ui.activity.FinancialBillingManagementActivity;
import ui.activity.FinancialSalaryStastisticsActivity;
import ui.activity.FinancialStastisticsActivity;
import ui.activity.OutProjectManagementActivity;
import ui.activity.ResourceManagementActivity;

@SuppressLint("NewApi")
public class MainTabResource extends Fragment {
    private GridView gridView;
    private View newsLayout;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    private Intent in;
    //动态菜单
    private ArrayList<Object> icon;
    private ArrayList<Object> iconName;
    private long exitTime = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        newsLayout = inflater.inflate(R.layout.main_tab_works, container, false);
        //spinnerData();
        init();
        gridView.setOnItemClickListener(g);
        return newsLayout;
    }

    private void init() {
        gridView = (GridView) newsLayout.findViewById(R.id.tab_grid);
        //新建List
        data_list = new ArrayList<>();
        //获取数据
        getData();
        //新建适配器
        String[] from = {"image", "text"};
        int[] to = {R.id.image, R.id.text};
        sim_adapter = new SimpleAdapter(getActivity(), data_list, R.layout.menu_item, from, to);
        //配置适配器
        gridView.setAdapter(sim_adapter);
    }
    public List<Map<String, Object>> getData() {
        //cion和iconName的长度是相同的，这里任选其一都可以
        //设定菜单显示(菜单控制)
        icon = new ArrayList<>();
        iconName = new ArrayList<>();
        Map<String, ArrayList<Object>> mapUML = UmlStatic.menuResourceController(icon, iconName);//调用人力资源菜单
        //去除重复元素
        ArrayList<Object> reIcon = mapUML.get("icon");
        ArrayList<Object> reIconName = mapUML.get("iconName");
        //调用公共方法
        reIcon = ToolUtils.removeDuplicate(reIcon);
        reIconName = ToolUtils.removeDuplicate(reIconName);
        for (int i = 0; icon != null && iconName != null && i < icon.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", reIcon.get(i));
            map.put("text", reIconName.get(i));
            data_list.add(map);
        }
        return data_list;
    }
    AdapterView.OnItemClickListener g = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    exitTime = ToolUtils.muchClick(exitTime);
                    if(exitTime!=0) {
                        exitTime = System.currentTimeMillis();
                        in = new Intent(getActivity(), ResourceManagementActivity.class);//信息维护
                        startActivity(in);
                    }
                    break;
                case 1:
                    exitTime = ToolUtils.muchClick(exitTime);
                    if(exitTime!=0) {
                        exitTime = System.currentTimeMillis();
                        in = new Intent(getActivity(), OutProjectManagementActivity.class);//外部项目
                        startActivity(in);
                    }
                    break;
            }
        }
    };
}
