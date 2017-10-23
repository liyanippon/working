package thread;

import android.os.Message;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import Tool.ToolUtils;
import Tool.statistics.Statics;
import http.HttpBasePost;
import http.HttpTypeConstants;
import ui.adpter.SourceManagementAdapter;

/**
 * Created by admin on 2017/10/17.
 */

public class SyncThread implements Runnable{
    private HashMap<String,String> param;
    private String pathStr;
    private String fileName;
    private byte[] img;

    public SyncThread(String pathStr,byte[] img,String fileName){
        this.pathStr = pathStr;
        this.img = img;
        this.fileName = fileName;
    }
    public void run() {//线程安全
        synchronized(this) {
            ReadWriteLock rwl = new ReentrantReadWriteLock();
            rwl.writeLock().lock();// 取到写锁
            Message msg = new Message();
            ToolUtils.writeImageToDisk(pathStr, img,fileName);
            msg.what = 1;
            SourceManagementAdapter.uiHandler.sendMessage(msg);
            rwl.writeLock().unlock();// 释放写锁
        }
    }
}