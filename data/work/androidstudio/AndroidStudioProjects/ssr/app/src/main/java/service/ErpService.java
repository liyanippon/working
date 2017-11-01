package service;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import com.example.admin.erp.MainActivity;
import java.util.Properties;
import Tool.statistics.AchacheConstant;
import http.HttpTypeConstants;
import presenter.LoginPresenterImpl;
import ui.activity.menu.MenuFragmentMainActivity;
/**
 * Created by admin on 2017/10/31.
 */
public class ErpService extends Service {
    LoginPresenterImpl presenter;
    Properties properties;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        new AchacheConstant();
        presenter=new LoginPresenterImpl();
        properties = new Properties();
        presenter.readProperties(properties, MenuFragmentMainActivity.context);//读取配置文件
        new HttpTypeConstants();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
                    Log.i("tag", "-->>onStartCommand-->>"+startId);
                    flags = START_STICKY;
                    //启用前台服务，主要是startForeground()
                    //NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    Notification.Builder builder = new Notification.Builder(ErpService.this);
                    builder.setTicker("显示第二个通知");
                    builder.setContentTitle("通知"); //设置标题
                    builder.setContentText("点击查看详细内容"); //消息内容
                    builder.setDefaults(Notification.DEFAULT_ALL); //设置默认的提示音，振动方式，灯光
                    builder.setAutoCancel(true);//打开程序后图标消失
                    Notification notification = builder.build();
                    //notifyManager.notify(1, builder.build());
                    startForeground(1, notification);
                    AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
                     //读者可以修改此处的Minutes从而改变提醒间隔时间
                     //此处是设置每隔55分钟启动一次
                     //这是55分钟的毫秒数
                     int Minutes = 3 * 60 * 1000;
                     //SystemClock.elapsedRealtime()表示1970年1月1日0点至今所经历的时间
                     long triggerAtTime = SystemClock.elapsedRealtime() + Minutes;
                     //此处设置开启AlarmReceiver这个Service
                     Intent i = new Intent(this, Alarmreceiver.class);
                     PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
                     //ELAPSED_REALTIME_WAKEUP表示让定时任务的出发时间从系统开机算起，并且会唤醒CPU。
                     manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
                    return super.onStartCommand(intent, flags, startId);
    }
}
