package Tool.statistics;

/**
 * Created by admin on 2017/5/8.
 */

import android.util.Log;

import com.example.admin.erp.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
    public static ArrayList<Object> icons;
    public static ArrayList<Object> iconNames;
    //多个角色匹配多的

    //菜单显示控制[财务管理]
    public static Map<String,ArrayList<Object>> menuFinancialController(ArrayList<Object> icon, ArrayList<Object> iconName){
        map=new HashMap<>();

        userType=new ArrayList<>();
        userType.add("a6509c7f484b482ab979aff844acbd2f");
        userType.add("8cb65d793df4457cae60484e6973e2d5");
        userType.add("0e039933cb7249489c9c840e3aa4f6cd");

        for (int i=0;i<userType.size();i++){
            map=addMenu(userType.get(i),icon,iconName);
        }


        /*switch ("a6509c7f484b482ab979aff844acbd2f"){//userType
            case "a6509c7f484b482ab979aff844acbd2f"://BOSS role_id值
                icon.add(R.drawable.wuliuguanli);
                icon.add(R.drawable.wuliutongji);
                iconName.add("物流管理");
                iconName.add("物流统计");
                map.put("icon",icon);
                map.put("iconName",iconName);

                break;

            case "8cb65d793df4457cae60484e6973e2d5"://财务人员 如果有财务人员，就定为财务人员
                icon.add(R.drawable.wuliuguanli);
                icon.add(R.drawable.wuliutongji);
                icon.add(R.drawable.yewuyuanlanjian);
                icon.add(R.drawable.yewuyuanfenxi);
                iconName.add("物流管理");
                iconName.add("物流统计");
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
        }*/

        return map;
    }

    //菜单显示控制[考勤管理]
    public static Map<String,ArrayList<Object>> menuAttendanceController(ArrayList<Object> icon,ArrayList<Object> iconName){
        map=new HashMap<>();
        switch ("8cb65d793df4457cae60484e6973e2d5"){//userType
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


    public static HashMap<String,ArrayList<Object>> addMenu(String menu,ArrayList<Object> icon, ArrayList<Object> iconName){

        switch (menu){//userType
            case "a6509c7f484b482ab979aff844acbd2f"://BOSS role_id值
                Log.d("add","非重复元素");
                icon.add(R.drawable.wuliuguanli);
                icon.add(R.drawable.wuliutongji);
                iconName.add("物流管理");
                iconName.add("物流统计");
                map.put("icon",icon);
                map.put("iconName",iconName);
                break;
            case "8cb65d793df4457cae60484e6973e2d5"://财务人员 如果有财务人员，就定为财务人员
                icon.add(R.drawable.wuliuguanli);
                icon.add(R.drawable.wuliutongji);
                icon.add(R.drawable.yewuyuanlanjian);
                icon.add(R.drawable.yewuyuanfenxi);
                iconName.add("物流管理");
                iconName.add("物流统计");
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


}
