package ui.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import java.util.List;

import Tool.ToolUtils;
import Tool.crash.BaseActivity;
import Tool.statistics.Statics;
import http.ExpressBillingManagementHttpPost;
import http.Constants;
import http.ExpressNumberManagementHttpPost;

public class AddExpressNumberActivity extends BaseActivity {
    private Button add, reset;
    private Spinner typeSpinner,personNameSpinner,expressTypeSpinner;
    private EditText description,billNumber;
    public static ArrayAdapter<String> arr_adapter;
    private String typeSpinnerString,expressNameString, descriptionString,billingTimeString,billNumberString;
    private List<String> data_list1,data_list2;
    private ExpressNumberManagementHttpPost httpPost;
    private EditText billingTime;
    private int currentYear,currentMon,currentDate;
    private Calendar calendar;

    //测试数据
    private static final String[] m={"A","B","C","D","E","F","G","其他","其他","其他"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("新建快递单");
        setContentView(R.layout.activity_add_express_number);

        //添加返回按钮
        ToolUtils.backButton(this);

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
                    billingTimeString = billingTime.getText().toString().trim();
                    descriptionString = description.getText().toString().trim();
                    billNumberString = billNumber.getText().toString().trim();
                    if ("".equals(typeSpinnerString)
                            || "".equals(descriptionString)) {
                        Toast.makeText(AddExpressNumberActivity.this, "所填数据不能为空", Toast.LENGTH_LONG).show();
                    } else {
                        httpPost = new ExpressNumberManagementHttpPost();
                        Log.d("test", typeSpinnerString    + "@" + description );
                        if ("success".equals(httpPost.addExpressBillingHttp(
                                Statics.ExpressCountSearch, typeSpinnerString,
                                expressNameString, billNumberString,descriptionString ,billingTimeString))) {
                            Toast.makeText(AddExpressNumberActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                    break;
                case R.id.reset:
                    description.setText("");
                    billingTime.setText(String.format("%d-%d-%d",currentYear,currentMon+1,currentDate));
                    billNumber.setText("");
                    break;
                case R.id.billingTime:
                    billingTime();
                    break;
            }
        }
    };

    private void billingTime() {

        //日期选择器
        new DatePickerDialog(AddExpressNumberActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                billingTime.setText(String.format("%d-%d-%d",i,i1+1,i2));
            }
        },currentYear,currentMon,currentDate).show();

    }



    private void spinnerType() {

        //数据  快递员的名字
        data_list1 = new ArrayList<>();
        for (int i = 0; i < Statics.expressPersonsList.size(); i++) {
            data_list1.add(Statics.expressPersonsList.get(i).getName());
        }
        for (int j = 0; j < data_list1.size(); j++) {
            Log.v("data-list", "--" + data_list1.get(j));
        }
        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, data_list1);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        personNameSpinner.setAdapter(arr_adapter);

        personNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                expressNameString = Statics.expressPersonsList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //expressNameButton.initContent(m);//快递选择
        ExpressBillingManagementHttpPost httpPost = new ExpressBillingManagementHttpPost();
        httpPost.accountTypeSearchHttp(Statics.AccountTypeUrl);
        data_list2 = new ArrayList<>();//快递类型
        for (int i = 0; i < Statics.accountTypeList.size(); i++) {
            data_list2.add(Statics.accountTypeList.get(i).getName());
        }

        //适配器
        arr_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, data_list2);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        typeSpinner.setAdapter(arr_adapter);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeSpinnerString = Statics.accountTypeList.get(position).getId();
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
        personNameSpinner = (Spinner) findViewById(R.id.personName);
        //reasonSpinner = (Spinner) findViewById(R.id.reasonSpinner);
        billNumber = (EditText) findViewById(R.id.billNumber);
        //expressNameButton = (ExpressSpinner) findViewById(R.id.expressNameButton);
        description = (EditText) findViewById(R.id.remarkId);
        billingTime = (EditText) findViewById(R.id.billingTime);
    }
}
