package ui.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.admin.erp.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import Tool.ToolUtils;
import Tool.crash.BaseActivity;
import Tool.statistics.Statics;
import http.HttpBasePost;
import http.HttpTypeConstants;
import model.AccountClassify;
import model.FinancialAccount;
import model.FinancialCustomer;

public class AddFinancialBillingManagerActivity extends BaseActivity {
    private Button add, reset;
    private EditText time, content,price,remark;
    private Spinner accountSpinner,classifySpinner,customerNameSpinner;
    private String accountSpinnerString,classifySpinnerString,customerNameSpinnerString;
    public static Context context;
    private List<String> data_list,data_type,data_classify;
    public static ArrayAdapter<String> arr_adapter;
    private int currentYear,currentMon,currentDate;
    private Calendar calendar;
    private HashMap<String,String> param;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("添加客户");
        setContentView(R.layout.activity_add_financial_manager);

        //添加返回按钮
        ToolUtils.backButton(this);
        context = getApplicationContext();
        init();
        spinnerType();
        calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMon = calendar.get(Calendar.MONTH)+1;
        currentDate = calendar.get(Calendar.DATE);
        Log.d("AddFinancialBillingMana", "当前日期（号）:" + currentDate);
        time.setText(String.format("%d-%d-%d",currentYear,currentMon,currentDate));
        add.setOnClickListener(o);
        reset.setOnClickListener(o);
        time.setOnClickListener(o);
    }

    //返回按钮事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener o = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add:
                    String contentString =content.getText().toString().trim();
                    String priceString = price.getText().toString().trim();
                    String remarkString = remark.getText().toString().trim();
                    String timeString = time.getText().toString();
                    if ( "".equals(contentString)||"".equals(priceString)
                            || "".equals(remarkString)) {
                        Toast.makeText(AddFinancialBillingManagerActivity.this, "所填数据不能为空", Toast.LENGTH_LONG).show();
                    } else {
                        param=new HashMap<>();
                        param.put("id","");
                        param.put("createBy",Statics.Name);
                        param.put("updateBy",Statics.Name);
                        param.put("userName", Statics.Name);
                        param.put("option", "2");//1查询，2添加，3删除
                        param.put("billType",accountSpinnerString);
                        param.put("billClassify",classifySpinnerString);
                        param.put("billTime2",timeString);
                        param.put("billClassification",contentString);
                        param.put("billSum",priceString);
                        param.put("billCustomerId",customerNameSpinnerString);
                        param.put("billDescription",remarkString);
                        param.put("updateTime",timeString);
                        Log.d("AddFinancialBillingMana", "customerId" + customerNameSpinnerString);
                        if ("success".equals(HttpBasePost.postHttp(Statics.AddFinancialBillingUrl,param, HttpTypeConstants.AddFinancialBillingUrlType))) {
                            Toast.makeText(AddFinancialBillingManagerActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        /*if ("success".equals(httpPost.addCountManagerHttp(Statics.AddFinancialBillingUrl,accountSpinnerString
                                ,classifySpinnerString,timeString,contentString
                                ,priceString,customerNameSpinnerString,remarkString))) {
                            Toast.makeText(AddFinancialBillingManagerActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }*/
                    }
                    break;
                case R.id.reset:
                    content.setText("");
                    price.setText("");
                    remark.setText("");
                    break;
                case R.id.time:
                    billingTime();
                    break;
            }
        }
    };

    private void billingTime() {
        //日期选择器

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(AddFinancialBillingManagerActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month++;
                        time.setText(String.format("%d-%d-%d",year,month,dayOfMonth));
                    }
                },
                mYear, mMonth, mDay).show();
    }

    private void spinnerType() {//下拉框
        Log.d("test", "spinnerType");
        //数据
        data_list = new ArrayList<>();
        for (FinancialAccount fa:Statics.financialAccountList) {
            Log.d("FinancialBillingManagem", "label:" + fa.getLabel().toString());
            data_list.add(fa.getLabel().toString());
        }
        data_type = new ArrayList<>();
        data_type = data_list;
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        accountSpinner.setAdapter(arr_adapter);
        data_list = null;
        //数据
        //httpPost =new HttpPost();
        //httpPost.accountClassifySearchHttp(Static.AccountClassifyUrl, AccountManagementActivity.this);
        data_list = new ArrayList<>();
        for (AccountClassify ac:Statics.accountClassifyList) {
            Log.d("FinancialBillingManagem", "label:" + ac.getName().toString());
            data_list.add(ac.getName().toString());
        }
        data_classify = new ArrayList<>();
        data_classify = data_list;
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        classifySpinner.setAdapter(arr_adapter);

        accountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("test8", Integer.toString(position) + "ss");

                accountSpinnerString = Statics.financialAccountList.get(position).getCode();//银行
                Log.d("FinancialBillingManagem", "typeSpinnerString:" + accountSpinnerString);

                data_list = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        classifySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("test8", Integer.toString(position) + "ss");

                classifySpinnerString = Statics.accountClassifyList.get(position).getId();
                data_list = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //数据
        //httpPost =new HttpPost();
        //httpPost.accountClassifySearchHttp(Static.AccountClassifyUrl, AccountManagementActivity.this);
        data_list = new ArrayList<>();
        for (FinancialCustomer.DataBean fc : Statics.financialCustomersList.get(0).getData()) {
            Log.d("FinancialBillingManagem", "ac.getName:" + fc.getFy_name());
            data_list.add(fc.getFy_name());
        }
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        customerNameSpinner.setAdapter(arr_adapter);
        customerNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("test8", Integer.toString(position) + "ss");
                customerNameSpinnerString = Statics.financialCustomersList.get(0).getData().get(position).getId();
                data_list = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void init() {
        reset = (Button) findViewById(R.id.reset);
        add = (Button) findViewById(R.id.add);
        customerNameSpinner = (Spinner) findViewById(R.id.customerName);
        accountSpinner = (Spinner) findViewById(R.id.account);
        classifySpinner = (Spinner) findViewById(R.id.classify);
        remark = (EditText) findViewById(R.id.remark);
        time = (EditText) findViewById(R.id.time);
        content = (EditText) findViewById(R.id.content);
        price = (EditText) findViewById(R.id.price);
        remark = (EditText) findViewById(R.id.remark);
    }

}
