package broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import portface.LazyLoadFace;

/**
 * Created by liyanippon on 17/04/03.
 */

public class FreshenBroadcastReceiver extends BroadcastReceiver {
    private LazyLoadFace lazyLoadFace;


    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        String msg = intent.getStringExtra("msg");
        //lazyLoadFace = context;
        Log.d("broadcast","MyBroadcastReceiver");
        if (action.equals(Config.BC_ONE)) { //接收到普通广播
            lazyLoadFace.AdapterRefresh(msg);    //回调给HandleBroadcast
        }
        context.unregisterReceiver(this);//注销广播
    }

    public void setLazyLoadFace(LazyLoadFace lazyLoadFace) {
        this.lazyLoadFace = lazyLoadFace;
    }

}
