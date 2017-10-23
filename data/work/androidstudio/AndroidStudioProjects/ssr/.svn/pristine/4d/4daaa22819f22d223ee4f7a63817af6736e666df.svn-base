package thread;

import java.util.HashMap;

import Tool.ToolUtils;
import Tool.statistics.Statics;
import http.HttpBasePost;
import http.HttpTypeConstants;

/**
 * Created by admin on 2017/10/17.
 */

public class SyncThread1 implements Runnable{
    private HashMap<String,String> param;
    public SyncThread1(HashMap<String,String> param){
        this.param = param;

    }
    public void run() {//线程安全
        synchronized(this) {
            HttpBasePost.postHttp(Statics.ResourceGetDownLoadFileUrl,param, HttpTypeConstants.ResourceGetDownLoadFileUrlType);//请求网络
        }
    }
}