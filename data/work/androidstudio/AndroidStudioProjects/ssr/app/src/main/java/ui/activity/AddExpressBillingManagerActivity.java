package ui.activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.admin.erp.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Tool.ACache;
import Tool.ToolUtils;
import Tool.crash.BaseActivity;
import Tool.statistics.AchacheConstant;
import Tool.statistics.Statics;
import broadcast.Config;
import broadcast.FreshenBroadcastReceiver;
import http.ExpressBillingManagementHttpPost;
import model.javabean.AccountReason;
import model.javabean.AccountType;
import model.javabean.Customer;
import model.javabean.ExpressExpensePayMethod;
import portface.LazyLoadFace;
public class AddExpressBillingManagerActivity extends BaseActivity{
    private Button add, reset;
    private static Spinner typeSpinner, classifySpinner, reasonSpinner, customSpinner, payMethodSpinner;
    private EditText price, remark;
    public static ArrayAdapter<String> arr_adapter,arr_adapter1;
    private String typeSpinnerString, classifySpinnerString, reasonSpinnerString, customerSpinnerString, priceString, remarkString,billingTimeString, payMethodSpinnerString;
    private List<String> data_list;
    private ExpressBillingManagementHttpPost httpPost;
    private RelativeLayout buttonPanel;
    private EditText billingTime;
    private int currentYear,currentMon,currentDate;
    private Calendar calendar;
    private static ArrayList data_list1;
    static FreshenBroadcastReceiver broadcast;
    public static Context context;
    private static ACache aCache;
    private ArrayList<Customer> customerArrayList;
    private static ArrayList<AccountReason> accountReasonList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("新建账单");
        setContentView(R.layout.activity_add_account_manager);

        //添加返回按钮
        ToolUtils.backButton(this);
        context = getApplicationContext();
        init();
        customerArrayList = (ArrayList<Customer>) aCache.getAsObject(AchacheConstant.CUSTOMER_LIST);
        initBroadCast();
        spinnerType();
        calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMon = calendar.get(Calendar.MONTH)+1;
        currentDate = calendar.get(Calendar.DAY_OF_MONTH);
        billingTime.setText(String.format("%d-%d-%d",currentYear,currentMon,currentDate));
        add.setOnClickListener(this);
        reset.setOnClickListener(this);
        billingTime.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        add.setClickable(true);
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

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.add:
                billingTimeString = billingTime.getText().toString().trim();
                Log.d("test","billingTimeString:"+billingTimeString);
                priceString = price.getText().toString().trim();
                Log.d("addddd",classifySpinnerString);//进账：023001，出账：023002
                remarkString = remark.getText().toString().trim();
                if ("".equals(typeSpinnerString) || "".equals(classifySpinnerString) || "".equals(reasonSpinnerString)
                        || "".equals(customerSpinnerString) || "".equals(priceString)
                        ||"".equals(payMethodSpinnerString)) {
                    Toast.makeText(AddExpressBillingManagerActivity.this, "所填数据不能为空", Toast.LENGTH_LONG).show();
                } else {
                    add.setClickable(false);
                    add.setBackgroundColor(Color.rgb(0x66,0x66,0x66));
                    httpPost = new ExpressBillingManagementHttpPost();
                    Log.d("test", typeSpinnerString + "@" + classifySpinnerString + "@" + reasonSpinnerString + "@" + priceString + "@" + remarkString + "@" + customerSpinnerString);
                    if ("success".equals(httpPost.addCountManagerHttp(
                            aCache.getAsString(AchacheConstant.FINANCIAL_BILLINGMANAGEMENT_SEARCH_URL), typeSpinnerString, classifySpinnerString,
                            reasonSpinnerString, priceString, remarkString, customerSpinnerString ,billingTimeString,payMethodSpinnerString,AddExpressBillingManagerActivity.this))) {
                        Toast.makeText(AddExpressBillingManagerActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                break;
            case R.id.reset:
                price.setText("");
                remark.setText("");
                billingTime.setText(String.format("%d-%d-%d",currentYear,currentMon+1,currentDate));
                break;
            case R.id.billingTime:
                billingTime();
                break;
        }
    }

    private void billingTime() {
        //日期选择器

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(AddExpressBillingManagerActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month++;
                        billingTime.setText(String.format("%d-%d-%d",year,month,dayOfMonth));
                    }
                },
                mYear, mMonth, mDay).show();
    }
    private void spinnerType() {
        accountReasonList = (ArrayList<AccountReason>) aCache.getAsObject(AchacheConstant.ACCOUNT_REASON_LIST);
        //数据 customer
        httpPost = new ExpressBillingManagementHttpPost();
        httpPost.customerSearchHttp(aCache.getAsString(AchacheConstant.All_CUSTOMER_URL),AddExpressBillingManagerActivity.this);
        data_list = new ArrayList<>();
        for (int i = 0; i < customerArrayList.size(); i++) {
            data_list.add(customerArrayList.get(i).getName());
        }
        for (int j = 0; j < data_list.size(); j++) {
            Log.v("data-list", "--" + data_list.get(j));
        }
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        customSpinner.setAdapter(arr_adapter);
        customSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                customerSpinnerString = customerArrayList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Log.d("test", "spinnerType");
        //数据 accountType
        httpPost = new ExpressBillingManagementHttpPost();
        httpPost.accountTypeSearchHttp(aCache.getAsString(AchacheConstant.ACCOUNT_TYPE_URL),AddExpressBillingManagerActivity.this);
        data_list = new ArrayList<>();
        ArrayList<AccountType> accountTypeArrayList = (ArrayList<AccountType>) aCache.getAsObject(AchacheConstant.ACCOUNT_TYPE_LIST);
        for (int i = 0; i < accountTypeArrayList.size(); i++) {
            data_list.add(accountTypeArrayList.get(i).getName());
        }
        for (int j = 0; j < data_list.size(); j++) {
            Log.v("data-list", "--" + data_list.get(j));
        }
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        typeSpinner.setAdapter(arr_adapter);
        data_list = null;
        //数据 AccountClassify
        //httpPost = new ExpressBillingManagementHttpPost();
        //httpPost.accountClassifySearchHttp(Statics.AccountClassifyUrl);
        data_list = new ArrayList<>();
        for (int i = 0; i < Statics.expressClassifyList.get(0).getData().size(); i++) {
            data_list.add(Statics.expressClassifyList.get(0).getData().get(i).getName());
        }
        for (int j = 0; j < data_list.size(); j++) {
            Log.v("data-list", "--" + data_list.get(j));
        }
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        classifySpinner.setAdapter(arr_adapter);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<AccountType> arrayList = (ArrayList<AccountType>) aCache.getAsObject(AchacheConstant.ACCOUNT_TYPE_LIST);
                typeSpinnerString = arrayList.get(position).getId();
                data_list = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        classifySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //二级联动
                classifySpinnerString = Statics.expressClassifyList.get(0).getData().get(position).getId();

                Log.d("select","ss:"+position+"");
                Log.d("select",classifySpinnerString);

                //数据
                Statics.ActivityType = "addExpress";
                httpPost = new ExpressBillingManagementHttpPost();
                httpPost.accountReasonSearchHttp(aCache.getAsString(AchacheConstant.ACCOUNT_REASON_URL), classifySpinnerString, AddExpressBillingManagerActivity.this);
                data_list1 = new ArrayList<>();
                for (int i = 0; i < accountReasonList.size(); i++) {
                    data_list1.add(accountReasonList.get(i).getName());
                }

                //适配器
                arr_adapter1 = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, data_list1);
                //设置样式
                arr_adapter1.setDropDownViewResource(R.layout.spinner_dropdown_style);
                //加载适配器
                reasonSpinner.setAdapter(arr_adapter1);

                reasonSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        reasonSpinnerString = accountReasonList.get(position).getId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //数据源
        data_list = new ArrayList<>();
        for(ExpressExpensePayMethod eepm:Statics.expressPaymentMethodArrayList){
            data_list.add(eepm.getLabel());
        }
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        payMethodSpinner.setAdapter(arr_adapter);
        payMethodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                payMethodSpinnerString = Statics.expressPaymentMethodArrayList.get(position).getCode();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void init() {
        reset = (Button) findViewById(R.id.reset);
        add = (Button) findViewById(R.id.add);
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        classifySpinner = (Spinner) findViewById(R.id.classifySpinner);
        reasonSpinner = (Spinner) findViewById(R.id.reasonSpinner);
        payMethodSpinner = (Spinner) findViewById(R.id.payMethodSpinner);
        price = (EditText) findViewById(R.id.priceId);
        price.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        ToolUtils.setPoint(price);//设置金额输入位数
        remark = (EditText) findViewById(R.id.remarkId);
        customSpinner = (Spinner) findViewById(R.id.customerId);
        buttonPanel = (RelativeLayout) findViewById(R.id.buttonPanel);
        billingTime = (EditText) findViewById(R.id.billingTime);
        aCache = ACache.get(AddExpressBillingManagerActivity.this);
    }

    private static void initBroadCast() {
        //广播初始化 必须动态注册才能实现回调
        broadcast = new FreshenBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Config.BC_ONE);
        context.registerReceiver(broadcast, intentFilter);
        broadcast.setLazyLoadFace(new LazyLoadFace() {
            @Override
            public void AdapterRefresh(String type) {
                //具体更新
                if(type.equals("addReasonSpinner")){
                    Log.d("aleand","收到广播");
                    accountReasonList = (ArrayList<AccountReason>) aCache.getAsObject(AchacheConstant.ACCOUNT_REASON_LIST);
                    //适配器
                    //arr_adapter1.notifyDataSetChanged();
                    data_list1 = new ArrayList<>();
                    for (int i = 0; i <accountReasonList.size(); i++) {
                        data_list1.add(accountReasonList.get(i).getName());
                    }
                    //适配器
                    arr_adapter1 = new ArrayAdapter<>(context, R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, data_list1);
                    //设置样式
                    arr_adapter1.setDropDownViewResource(R.layout.spinner_dropdown_style);
                    //加载适配器
                    reasonSpinner.setAdapter(arr_adapter1);
                }
            }

        });
    }
    public static void AdapterRefresh(String type) {
        switch (type) {
            case "reasonSpinner":
                initBroadCast();
                break;
        }
    }
}
