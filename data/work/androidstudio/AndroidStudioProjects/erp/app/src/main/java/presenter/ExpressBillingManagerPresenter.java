package presenter;

import android.app.Activity;
import http.ExpressBillingManagementHttpPost;

/**
 * Created by admin on 2017/8/21.
 */

public interface ExpressBillingManagerPresenter {
    void search(Activity activity, ExpressBillingManagementHttpPost httpPost, String typeSpinnerString, String classifySpinnerString, String reasonSpinnerString);
    void add(final Activity activity);
}
