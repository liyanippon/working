package biz;
import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.CheckBox;
import java.util.Properties;
import http.ExpressBillingManagementHttpPost;
/**
 * Created by admin on 2017/8/21.
 */
public interface IUser {
    void readProperties(Properties properties, Activity activity);
    void check(Activity activity, ExpressBillingManagementHttpPost httpPost, CheckBox rememberMe, SharedPreferences sp);
    void initBroadCast(final Activity activity, final String userNameString);
    void initCrashHandler(Activity activity);
}