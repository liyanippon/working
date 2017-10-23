package biz;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.view.animation.ScaleAnimation;
import Tool.statistics.Statics;
import http.ExpressBillingManagementHttpPost;
import ui.activity.AddExpressBillingManagerActivity;
import ui.activity.ExpressBillingManagementActivity;
import ui.activity.TransferAccountActivity;
/**
 * Created by admin on 2017/8/21.
 */
public class ExpressBillingManagerImpl implements ExpressBillingManager{
    @Override
    public void search(Activity activity, ExpressBillingManagementHttpPost httpPost, String typeSpinnerString, String classifySpinnerString, String reasonSpinnerString) {
        //条件查询
        ExpressBillingManagementActivity.page = 1;
        ExpressBillingManagementActivity.progressDialog = ProgressDialog.show(activity, "请稍等...", "获取数据中...", true);//显示进度条
        ExpressBillingManagementActivity.SearchBoolean = true;
        httpPost = new ExpressBillingManagementHttpPost();
        String httpUrl = Statics.FinancialBillingManagementSearchUrl;
        String result = httpPost.searchHttp(httpUrl, typeSpinnerString, classifySpinnerString, reasonSpinnerString, activity, ExpressBillingManagementActivity.page);
    }

    @Override
    public void add(final Activity activity) {
        ExpressBillingManagementActivity.newBilling.setVisibility(View.VISIBLE);
        ExpressBillingManagementActivity.transferAccounts.setVisibility(View.VISIBLE);
        ScaleAnimation sa = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, 100.0f, 120.0f);
        sa.setDuration(1000);
        ExpressBillingManagementActivity.newBilling.startAnimation(sa);
        ExpressBillingManagementActivity.transferAccounts.startAnimation(sa);
        ExpressBillingManagementActivity.newBilling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(activity, AddExpressBillingManagerActivity.class);
                activity.startActivity(in);
            }
        });
        ExpressBillingManagementActivity.transferAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ins = new Intent(activity,TransferAccountActivity.class);
                activity.startActivity(ins);
            }
        });

    }
}
