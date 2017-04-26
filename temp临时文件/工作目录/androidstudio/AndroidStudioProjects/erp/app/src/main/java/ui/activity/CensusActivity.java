package ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.admin.erp.R;

import java.util.List;

import http.AccountManagementHttpPost;
import http.BillingStatisticsHttpPost;
import http.Constants;
import http.ExpressNumberManagementHttpPost;
import http.ExpressStatisticsHttpPost;

public class CensusActivity extends AppCompatActivity {
    private AccountManagementHttpPost httpPost;
    private List<String> data_list;
    private BillingStatisticsHttpPost billingStatisticsHttpPost;
    private ExpressNumberManagementHttpPost expressNumberManagementHttpPost;
    private ExpressStatisticsHttpPost expressStatisticsHttpPost;
    private String typeSpinnerString = "024001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("财务管理");
        setContentView(R.layout.activity_census);



        //获取数据 下拉菜单
        httpPost = new AccountManagementHttpPost();
        spinner();
        //AllCustomer
        /*httpPost =new AccountManagementHttpPost();
        httpPost.customerSearchHttp(Constants.AllCustomerUrl);
        httpPost.accountClassifySearchHttp(Constants.AccountClassifyUrl);
        httpPost.accountTypeSearchHttp(Constants.AccountTypeUrl);
        httpPost.accountReasonSearchHttp(Constants.AccountReasonUrl, Constants.accountClassify, CensusActivity.this);
        Log.d("ss","saf");*/

        //查询
        //int page = 1;//显示页数
        //httpPost = new AccountManagementHttpPost();
        //String httpUrl = Constants.AccountManagementSearchUrl;
        //String result = httpPost.searchHttp(httpUrl ,"" ,"" ,"",AccountManagementActivity.this,page);
        Constants.year.clear();
        //账单统计
        billingStatisticsHttpPost = new BillingStatisticsHttpPost();
        //billingStatisticsHttpPost.searchTimeHttp(Constants.TimeSearchUrl,"2016",typeSpinnerString,CensusActivity.this);
        billingStatisticsHttpPost.searchYearHttp(Constants.YearSearchUrl);

        //快递管理
        String ExpressPersonNameSearchUrl = Constants.ExpressPersonNameSearchUrl;
        expressNumberManagementHttpPost =new ExpressNumberManagementHttpPost();
        expressNumberManagementHttpPost.expressPersionSearchHttp(ExpressPersonNameSearchUrl);
        //年份
        expressStatisticsHttpPost = new ExpressStatisticsHttpPost();
        expressNumberManagementHttpPost.searchExpressYearHttp(Constants.ExpressYearSearchUrl);

    }

    private void spinner() {
        //获取数据 下拉菜单
        //AllCustomer

        new Runnable() {
            @Override
            public void run() {
                httpPost.customerSearchHttp(Constants.AllCustomerUrl);
                httpPost.accountClassifySearchHttp(Constants.AccountClassifyUrl);
                httpPost.accountTypeSearchHttp(Constants.AccountTypeUrl);
                httpPost.accountReasonSearchHttp(Constants.AccountReasonUrl, Constants.accountClassify, CensusActivity.this);
            }
        }.run();

    }
}
