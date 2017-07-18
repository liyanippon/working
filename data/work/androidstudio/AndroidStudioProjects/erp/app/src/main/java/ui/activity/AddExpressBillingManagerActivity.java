package ui.activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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
import Tool.ToolUtils;
import Tool.crash.BaseActivity;
import Tool.crash.CrashHandler;
import Tool.statistics.Statics;
import broadcast.Config;
import broadcast.FreshenBroadcastReceiver;
import http.ExpressBillingManagementHttpPost;
import model.ExpressExpensePayMethod;
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
    public static String expressType = "";
    private static ArrayList data_list1;
    static FreshenBroadcastReceiver broadcast;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("新建账单");
        setContentView(R.layout.activity_add_account_manager);

        //添加返回按钮
        ToolUtils.backButton(this);
        context = getApplicationContext();
        initBroadCast();
        init();
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
                    /*if("023002".equals(classifySpinnerString)){//出账添加‘-’
                        priceString = "-"+priceString;
                    }*/
                remarkString = remark.getText().toString().trim();
                if ("".equals(typeSpinnerString) || "".equals(classifySpinnerString) || "".equals(reasonSpinnerString)
                        || "".equals(customerSpinnerString) || "".equals(priceString)
                        ||"".equals(payMethodSpinnerString)) {
                    Toast.makeText(AddExpressBillingManagerActivity.this, "所填数据不能为空", Toast.LENGTH_LONG).show();
                } else {
                    httpPost = new ExpressBillingManagementHttpPost();
                    Log.d("test", typeSpinnerString + "@" + classifySpinnerString + "@" + reasonSpinnerString + "@" + priceString + "@" + remarkString + "@" + customerSpinnerString);
                    if ("success".equals(httpPost.addCountManagerHttp(
                            Statics.FinancialBillingManagementSearchUrl, typeSpinnerString, classifySpinnerString,
                            reasonSpinnerString, priceString, remarkString, customerSpinnerString ,billingTimeString,payMethodSpinnerString))) {
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
        //数据 customer
        httpPost = new ExpressBillingManagementHttpPost();
        httpPost.customerSearchHttp(Statics.AllCustomerUrl);
        data_list = new ArrayList<>();
        for (int i = 0; i < Statics.customerList.size(); i++) {
            data_list.add(Statics.customerList.get(i).getName());
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
                customerSpinnerString = Statics.customerList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Log.d("test", "spinnerType");
        //数据 accountType
        httpPost = new ExpressBillingManagementHttpPost();
        httpPost.accountTypeSearchHttp(Statics.AccountTypeUrl);
        data_list = new ArrayList<>();
        for (int i = 0; i < Statics.accountTypeList.size(); i++) {
            data_list.add(Statics.accountTypeList.get(i).getName());
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
                typeSpinnerString = Statics.accountTypeList.get(position).getId();
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
                expressType = "addExpress";
                httpPost = new ExpressBillingManagementHttpPost();
                httpPost.accountReasonSearchHttp(Statics.AccountReasonUrl, classifySpinnerString, AddExpressBillingManagerActivity.this);
                data_list1 = new ArrayList<>();
                for (int i = 0; i < Statics.accountReasonList.size(); i++) {
                    data_list1.add(Statics.accountReasonList.get(i).getName());
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
                        reasonSpinnerString = Statics.accountReasonList.get(position).getId();
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
        setPoint(price);//设置金额输入位数
        remark = (EditText) findViewById(R.id.remarkId);
        customSpinner = (Spinner) findViewById(R.id.customerId);
        buttonPanel = (RelativeLayout) findViewById(R.id.buttonPanel);
        billingTime = (EditText) findViewById(R.id.billingTime);
    }

    public void setPoint(EditText point) {//限制输入小数位数
        point.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 2+1);
                        price.setText(s);
                        price.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    price.setText(s);
                    price.setSelection(2);
                }
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        price.setText(s.subSequence(0, 1));
                        price.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
                    //适配器
                    //arr_adapter1.notifyDataSetChanged();
                    data_list1 = new ArrayList<>();
                    for (int i = 0; i < Statics.accountReasonList.size(); i++) {
                        data_list1.add(Statics.accountReasonList.get(i).getName());
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
