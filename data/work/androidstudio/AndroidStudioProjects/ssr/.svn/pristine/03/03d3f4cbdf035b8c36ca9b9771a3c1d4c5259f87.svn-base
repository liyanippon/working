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
import http.ExpressBillingManagementHttpPost;
import http.HttpBase;
import http.HttpBasePost;
import http.HttpTypeConstants;
import ui.activity.FinancialBillingManagementActivity;
import ui.activity.FinancialSalaryStastisticsActivity;
import ui.activity.FinancialStastisticsActivity;
@SuppressLint("NewApi")
public class MainTabFinancial extends Fragment {
    private GridView gridView;
    private View newsLayout;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    private Intent in;
    //动态菜单
    private ArrayList<Object> icon;
    private ArrayList<Object> iconName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        newsLayout = inflater.inflate(R.layout.main_tab_financial, container, false);
        spinnerData();
        init();
        gridView.setOnItemClickListener(g);
        return newsLayout;
    }

    private void spinnerData() {
        //获取数据 下拉菜单
        new Runnable() {
            @Override
            public void run() {
                HttpBasePost.postHttp(Statics.FinancialBillingGetWXAccountsTypeUrl,null, HttpTypeConstants.FinancialBillingGetWXAccountsTypeUrlType);//账目下拉框
                HttpBasePost.postHttp(Statics.FinancialAccountCustomerUrl,null, HttpTypeConstants.FinancialAccountCustomerUrlType);
                HttpBasePost.postHttp(Statics.AccountClassifyUrl,null,HttpTypeConstants.ExpressClassifyUrlType);//进账出账下拉框
                HttpBasePost.postHttp(Statics.FinancialSalaryGetWXOrgListUrl,null,HttpTypeConstants.CompanyDepartmentListUrlType);//单位下拉框
            }
        }.run();
    }
    private void init() {
        gridView = (GridView) newsLayout.findViewById(R.id.tab02_grid);
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
        Map<String, ArrayList<Object>> mapUML = UmlStatic.menuFinancialController(icon, iconName);//调用财务管理菜单
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
                    in = new Intent(getActivity(), FinancialSalaryStastisticsActivity.class);//工资统计表
                    startActivity(in);
                    break;
                case 1:
                    in = new Intent(getActivity(), FinancialBillingManagementActivity.class);//账目管理
                    startActivity(in);
                    break;
                case 2:
                    in = new Intent(getActivity(), FinancialStastisticsActivity.class);//账目统计
                    startActivity(in);
                    break;

            }
        }
    };
}
