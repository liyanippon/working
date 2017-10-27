package thread;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Properties;

import Tool.ACache;
import Tool.statistics.AchacheConstant;
import Tool.statistics.Statics;
import http.HttpTypeConstants;
import model.javabean.UserUmp;
import presenter.LoginPresenterImpl;
import ui.activity.menu.MenuFragmentMainActivity;

/**
 * Created by admin on 2017/10/23.
 */

public class ResumeStart implements Runnable{
    private Activity activity;
    LoginPresenterImpl presenter;
    Properties properties;
    public ResumeStart(Activity activity){
        this.activity = activity;
    }
    @Override
    public void run() {
        ACache aCache = ACache.get(activity);
        //使用getAsObject()，直接进行强转
        new AchacheConstant();
        Statics.userUmpsStatisticsList = (ArrayList<UserUmp>) aCache.getAsObject(AchacheConstant.USER_UMP);
        Statics.Name = aCache.getAsString(AchacheConstant.USER_NAME);
        Log.d("ResumeStart", Statics.Name);
        presenter=new LoginPresenterImpl();
        properties = new Properties();
        presenter.readProperties(properties,activity);//读取配置文件
        new HttpTypeConstants();
    }
}
