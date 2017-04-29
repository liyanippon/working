package broadcast;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by admin on 2017/4/26.
 */

public class BroadCastTool {
     public static void sendMyBroadcast(TYPE type, Context context, String description) {
        Intent intent = new Intent();
        switch (type) {
            case NORMAL:   //普通广播
                Log.d("broadcast", "send发送广播");
                intent.putExtra("msg", description);
                intent.setAction(Config.BC_ONE);
                context.sendBroadcast(intent);
                break;
        }
    }
}
