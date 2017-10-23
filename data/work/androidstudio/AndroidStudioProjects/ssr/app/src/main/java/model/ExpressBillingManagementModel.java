package model;

import android.app.Activity;

/**
 * Created by admin on 2017/9/30.
 */

public interface ExpressBillingManagementModel {
    void login(String httpUrl, String typeSpinnerString, String classifySpinnerString, String reasonSpinnerString, Activity activity, int page);
}
