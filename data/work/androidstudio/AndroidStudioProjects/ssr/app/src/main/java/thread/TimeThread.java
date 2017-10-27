package thread;

import android.os.Message;
import android.util.Log;

import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import ui.activity.AttendanceCardActivity;
/**
 * Created by admin on 2017/10/19.
 */

public class TimeThread implements Runnable{
private boolean timeSend;
    public TimeThread(boolean timeSend){
        this.timeSend = timeSend;

    }
    @Override
    public void run() {
        while (this.timeSend){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String currentTime = getNetTime();
            Message msg = new Message();
            msg.what = 1;
            msg.obj = currentTime;
            Log.d("TimeThread", "当前时间" + currentTime);
            if(AttendanceCardActivity.uiHandler!=null){
                AttendanceCardActivity.uiHandler.sendMessage(msg);
            }else{
                Log.d("TimeThread", "handler有问题");
            }
            //AttendanceCardActivity.uiHandler.sendMessage(msg);
        }
    }
    private String getNetTime() {
        URL url = null;//取得资源对象
        String time = null;
        try {
            //url = new URL("http://www.baidu.com");
            url = new URL("http://www.ntsc.ac.cn");//中国科学院国家授时中心
            //url = new URL("http://www.bjtime.cn");
            URLConnection uc = url.openConnection();//生成连接对象
            uc.connect(); //发出连接
            long ld = uc.getDate(); //取得网站日期时间
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(ld);
            time = formatter.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

}
