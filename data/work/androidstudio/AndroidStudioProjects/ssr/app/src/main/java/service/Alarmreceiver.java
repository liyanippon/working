package service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by admin on 2017/10/31.
 */

public class Alarmreceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("arui.alarm.action")) {
                         Intent i = new Intent();
                         i.setClass(context, ErpService.class);
                         // 启动service
                         // 多次调用startService并不会启动多个service 而是会多次调用onStart
                         context.startService(i);
                     }
    }
}
