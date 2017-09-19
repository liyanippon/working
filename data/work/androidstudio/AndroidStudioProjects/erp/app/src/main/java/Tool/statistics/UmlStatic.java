package Tool.statistics;

/**
 * Created by admin on 2017/5/8.
 */

import android.util.Log;
import com.example.admin.erp.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.UserUmp;

/**
 * *权限分配
 * 分为管理员、管理人员和员工
 * **/
public class UmlStatic {

    //系统管理员
    //BOSS
    //项目经理
    //财务人员
    //开发人员
    //物流业务员

    public static ArrayList<String> userType;

    public static HashMap<String,ArrayList<Object>> map;
    //多个角色匹配多的

    //菜单显示控制[物流管理]
    public static Map<String,ArrayList<Object>> menuExpressController(ArrayList<Object> icon, ArrayList<Object> iconName){
        map=new HashMap<>();
        for (UserUmp userUmp:Statics.userUmpsStatisticsList){
            map=addExpressMenu(userUmp.getRoleId(),icon,iconName);
        }
        return map;
    }

    //菜单显示控制[考勤管理]
    public static Map<String,ArrayList<Object>> menuAttendanceController(ArrayList<Object> icon,ArrayList<Object> iconName){
        map=new HashMap<>();
        for (UserUmp userUmp:Statics.userUmpsStatisticsList){
            map=addAttendanceMenu(userUmp.getRoleId(),icon,iconName);
        }
        return map;
    }

    //菜单显示控制[财务客户]
    public static Map<String,ArrayList<Object>> menuFinancialController(ArrayList<Object> icon, ArrayList<Object> iconName){
        map=new HashMap<>();
        for (UserUmp userUmp:Statics.userUmpsStatisticsList){
            map=addFinancialMenu(userUmp.getRoleId(),icon,iconName);
        }
        return map;
    }

    //菜单显示控制[项目管理]
    public static Map<String,ArrayList<Object>> menuProjectController(ArrayList<Object> icon, ArrayList<Object> iconName){
        map=new HashMap<>();
        for (UserUmp userUmp:Statics.userUmpsStatisticsList){
            map=addProjectMenu(userUmp.getRoleId(),icon,iconName);
        }
        return map;
    }

    public static HashMap<String,ArrayList<Object>> addExpressMenu(String menu,ArrayList<Object> icon, ArrayList<Object> iconName){

        switch (menu){//userType
            case "a6509c7f484b482ab979aff844acbd2f"://BOSS role_id值
                Log.d("add","非重复元素");
                icon.add(R.drawable.wuliuguanli);
                icon.add(R.drawable.wuliutongji);
                icon.add(R.drawable.wuliubaobiao);
                iconName.add("物流管理");
                iconName.add("物流统计");
                iconName.add("统计报表");
                map.put("icon",icon);
                map.put("iconName",iconName);
                break;
            case "8cb65d793df4457cae60484e6973e2d5"://财务人员 如果有财务人员，就定为财务人员
                icon.add(R.drawable.wuliuguanli);
                icon.add(R.drawable.wuliutongji);
                icon.add(R.drawable.wuliubaobiao);
                icon.add(R.drawable.yewuyuanlanjian);
                icon.add(R.drawable.yewuyuanfenxi);
                iconName.add("物流管理");
                iconName.add("物流统计");
                iconName.add("统计报表");
                iconName.add("业务揽件");
                iconName.add("业务统计");
                map.put("icon",icon);
                map.put("iconName",iconName);
                break;
            case "0e039933cb7249489c9c840e3aa4f6cd"://物流业务员
                icon.add(R.drawable.wuliutongji);
                icon.add(R.drawable.yewuyuanfenxi);
                iconName.add("物流统计");
                iconName.add("业务统计");
                map.put("icon",icon);
                map.put("iconName",iconName);
                break;
        }
        return map;
    }

    public static HashMap<String,ArrayList<Object>> addAttendanceMenu(String menu,ArrayList<Object> icon, ArrayList<Object> iconName){

        switch (menu){//userType
            case "8cb65d793df4457cae60484e6973e2d5"://财务人员
            case "f8dc2c2f6ece4f38a8df43ab4d4a4c5d"://项目经理
            case "a6509c7f484b482ab979aff844acbd2f"://BOSS
                icon.add(R.drawable.kaoqintongji);
                iconName.add("考勤统计");
                map.put("icon",icon);
                map.put("iconName",iconName);
                break;
        }

        return map;
    }

    public static HashMap<String,ArrayList<Object>> addFinancialMenu(String menu,ArrayList<Object> icon, ArrayList<Object> iconName) {

        switch (menu) {//userType
            case "8cb65d793df4457cae60484e6973e2d5"://财务人员
            case "a6509c7f484b482ab979aff844acbd2f"://BOSS
                icon.add(R.drawable.gongzitongji);
                icon.add(R.drawable.caiwuguanli);
                icon.add(R.drawable.zhang_mutongji);
                iconName.add("工资统计");
                iconName.add("账目管理");
                iconName.add("账目统计");
                map.put("icon", icon);
                map.put("iconName", iconName);
                break;
        }

        return map;
    }

    public static HashMap<String,ArrayList<Object>> addProjectMenu(String menu,ArrayList<Object> icon, ArrayList<Object> iconName) {

        switch (menu) {//userType
            case "8cb65d793df4457cae60484e6973e2d5"://财务人员
            case "a6509c7f484b482ab979aff844acbd2f"://BOSS
                icon.add(R.drawable.project_manager);
                //icon.add(R.drawable.zhang_mutongji);
                iconName.add("项目维护");
                //iconName.add("账目统计");
                map.put("icon", icon);
                map.put("iconName", iconName);
                break;
        }

        return map;
    }

}
