package ui.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import http.AccountManagementHttpPost;
import http.Constants;

//import static com.example.admin.erp.R.id.accountLv;

public class AddAccountManagerActivity extends AppCompatActivity {
    private Button add, reset;
    private Spinner typeSpinner, classifySpinner, reasonSpinner, customSpinner;
    private EditText price, remark;
    public static ArrayAdapter<String> arr_adapter;
    private String typeSpinnerString, classifySpinnerString, reasonSpinnerString, customerSpinnerString, priceString, remarkString,billingTimeString;
    private List<String> data_list;
    private AccountManagementHttpPost httpPost;
    private RelativeLayout buttonPanel;
    private EditText billingTime;
    private int currentYear,currentMon,currentDate;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("新建账单");
        setContentView(R.layout.activity_add_account_manager);

        init();
        spinnerType();
        calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMon = calendar.get(Calendar.MONTH)+1;
        currentDate = calendar.get(Calendar.DAY_OF_MONTH);
        billingTime.setText(String.format("%d-%d-%d",currentYear,currentMon,currentDate));
        add.setOnClickListener(o);
        reset.setOnClickListener(o);
        billingTime.setOnClickListener(o);
    }

    View.OnClickListener o = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add:
                    billingTimeString = billingTime.getText().toString().trim();
                    Log.d("test","billingTimeString:"+billingTimeString);
                    priceString = price.getText().toString().trim();
                    remarkString = remark.getText().toString().trim();
                    if ("".equals(typeSpinnerString) || "".equals(classifySpinnerString) || "".equals(reasonSpinnerString)
                            || "".equals(customerSpinnerString) || "".equals(priceString)
                            || "".equals(remarkString)) {
                        Toast.makeText(AddAccountManagerActivity.this, "所填数据不能为空", Toast.LENGTH_LONG).show();
                    } else {
                        httpPost = new AccountManagementHttpPost();
                        Log.d("test", typeSpinnerString + "@" + classifySpinnerString + "@" + reasonSpinnerString + "@" + priceString + "@" + remarkString + "@" + customerSpinnerString);
                        if ("success".equals(httpPost.addCountManagerHttp(
                                Constants.AccountManagementSearchUrl, typeSpinnerString, classifySpinnerString,
                                reasonSpinnerString, priceString, remarkString, customerSpinnerString ,billingTimeString))) {
                            Toast.makeText(AddAccountManagerActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
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
    };

    private void billingTime() {
        //日期选择器

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(AddAccountManagerActivity.this,
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
        httpPost = new AccountManagementHttpPost();
        httpPost.customerSearchHttp(Constants.AllCustomerUrl);
        data_list = new ArrayList<>();
        for (int i = 0; i < Constants.customerList.size(); i++) {
            data_list.add(Constants.customerList.get(i).getName());
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
                customerSpinnerString = Constants.customerList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Log.d("test", "spinnerType");
        //数据 accountType
        httpPost = new AccountManagementHttpPost();
        httpPost.accountTypeSearchHttp(Constants.AccountTypeUrl);
        data_list = new ArrayList<>();
        for (int i = 0; i < Constants.accountTypeList.size(); i++) {
            data_list.add(Constants.accountTypeList.get(i).getName());
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
        httpPost = new AccountManagementHttpPost();
        httpPost.accountClassifySearchHttp(Constants.AccountClassifyUrl);
        data_list = new ArrayList<>();
        for (int i = 0; i < Constants.accountClassifyList.size(); i++) {
            data_list.add(Constants.accountClassifyList.get(i).getName());
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
                typeSpinnerString = Constants.accountTypeList.get(position).getId();
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
                classifySpinnerString = Constants.accountClassifyList.get(position).getId();
                //数据
                httpPost = new AccountManagementHttpPost();
                httpPost.accountReasonSearchHttp(Constants.AccountReasonUrl, classifySpinnerString, AddAccountManagerActivity.this);
                data_list = new ArrayList<>();
                for (int i = 0; i < Constants.accountReasonList.size(); i++) {
                    data_list.add(Constants.accountReasonList.get(i).getName());
                }
                for (int j = 0; j < data_list.size(); j++) {
                    Log.v("data-list", "--" + data_list.get(j));
                }
                //适配器
                arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, data_list);
                //设置样式
                arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
                //加载适配器
                reasonSpinner.setAdapter(arr_adapter);

                reasonSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        reasonSpinnerString = Constants.accountReasonList.get(position).getId();
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
    }

    private void init() {

        reset = (Button) findViewById(R.id.reset);
        add = (Button) findViewById(R.id.add);
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        classifySpinner = (Spinner) findViewById(R.id.classifySpinner);
        reasonSpinner = (Spinner) findViewById(R.id.reasonSpinner);
        price = (EditText) findViewById(R.id.priceId);
        remark = (EditText) findViewById(R.id.remarkId);
        customSpinner = (Spinner) findViewById(R.id.customerId);
        buttonPanel = (RelativeLayout) findViewById(R.id.buttonPanel);
        billingTime = (EditText) findViewById(R.id.billingTime);
    }

}
