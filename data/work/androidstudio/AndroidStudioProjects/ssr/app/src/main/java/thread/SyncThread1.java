package thread;

import android.app.Activity;

import java.util.HashMap;

import Tool.ACache;
import Tool.ToolUtils;
import Tool.statistics.AchacheConstant;
import Tool.statistics.Statics;
import http.HttpBasePost;
import http.HttpTypeConstants;

/**
 * Created by admin on 2017/10/17.
 */

public class SyncThread1 implements Runnable{
    private HashMap<String,String> param;
    private Activity activity;
    ACache aCache;
    public SyncThread1(HashMap<String,String> param,Activity activity){
        this.param = param;
        this.activity = activity;
        aCache = ACache.get(this.activity);
    }
    public void run() {//线程安全
        synchronized(this) {
            HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.RESOURCE_GETDOWNLOADFILE_URL),param, HttpTypeConstants.ResourceGetDownLoadFileUrlType);//请求网络
        }
    }
}