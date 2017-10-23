package ui.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.IntentFilter;
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
import broadcast.Config;
import broadcast.FreshenBroadcastReceiver;
import http.HttpBasePost;
import http.HttpTypeConstants;
import model.javabean.TransferAccountClassify;
import portface.LazyLoadFace;

public class TransferAccountActivity extends BaseActivity {
    private Button add, reset;
    private static Spinner classifySpinner, reasonSpinner;
    private EditText price, remark;
    public static ArrayAdapter<String> arr_adapter,arr_adapter1;
    private String classifySpinnerString, reasonSpinnerString, priceString, remarkString,billingTimeString,poundageString;
    private List<String> data_list;
    private EditText billingTime,poundage;
    private int currentYear,currentMon,currentDate;
    private Calendar calendar;
    public static Boolean addBoolean = false;
    private static ArrayList data_list1;
    static FreshenBroadcastReceiver broadcast;
    public static Context context;
    private HashMap<String,String> param;
    public static Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("转账");
        setContentView(R.layout.activity_transfer_account);

        activity = TransferAccountActivity.this;
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
                poundageString = poundage.getText().toString().trim();
                Log.d("addddd",classifySpinnerString);//进账：023001，出账：023002
                    /*if("023002".equals(classifySpinnerString)){//出账添加‘-’
                        priceString = "-"+priceString;
                    }*/
                remarkString = remark.getText().toString().trim();
                if ("".equals(priceString) && "".equals(poundageString)
                        ) {
                    Toast.makeText(TransferAccountActivity.this, "所填数据不能为空", Toast.LENGTH_LONG).show();
                } else {
                    /*if ("success".equals(httpPost.addCountManagerHttp(
                            Statics.FinancialBillingManagementSearchUrl, typeSpinnerString, classifySpinnerString,
                            reasonSpinnerString, priceString, remarkString, customerSpinnerString ,billingTimeString))) {
                        Toast.makeText(TransferAccountActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }*/
                    Log.d("information",priceString+"?"+reasonSpinnerString+"?"+classifySpinnerString+"?"+billingTimeString
                            +"?"+poundageString);
                    param = new HashMap<>();
                    param.put("option","4");
                    param.put("createBy", Statics.Name);
                    param.put("updateBy", Statics.Name);
                    param.put("billingTime", billingTimeString);
                    param.put("updateTime", billingTimeString);
                    param.put("createTime", billingTimeString);
                    param.put("description", remarkString);
                    param.put("sum", priceString);
                    param.put("reason",reasonSpinnerString);
                    param.put("classify",classifySpinnerString);
                    param.put("userName", Statics.Name);
                    param.put("id", "");
                    param.put("poundage",poundageString);
                    Statics.isTransfer = true;
                    if ("success".equals(HttpBasePost.postHttp(Statics.FinancialBillingManagementSearchUrl,param,HttpTypeConstants.TransferAccount))){//进账出账下拉框
                        //Toast.makeText(TransferAccountActivity.this, "转账成功", Toast.LENGTH_SHORT).show();
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
        new DatePickerDialog(TransferAccountActivity.this,
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
        /*//数据 customer
        httpPost = new ExpressBillingManagementHttpPost();
        httpPost.customerSearchHttp(Statics.AllCustomerUrl);
        data_list = new ArrayList<>();
        for (int i = 0; i < Statics.customerList.size(); i++) {
            //if("进账".equals(Statics.customerList.get(i).getName())&&"出账".equals(Statics.customerList.get(i).getName())){
                data_list.add(Statics.customerList.get(i).getName());
            //}
        }
        for (int j = 0; j < data_list.size(); j++) {
            Log.v("进账出账", "--" + data_list.get(j));
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
        Log.d("test", "spinnerType");*/
        //数据 accountType
        /*httpPost = new ExpressBillingManagementHttpPost();
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
        arr_adapter.setDropDownViewResource(R.layout.spinner_dropdown_style);*/
        //加载适配器
        //typeSpinner.setAdapter(arr_adapter);
        data_list = null;
        //数据 AccountClassify
        //httpPost = new ExpressBillingManagementHttpPost();
        //httpPost.accountClassifySearchHttp(Statics.AccountClassifyUrl,"3");
        data_list = new ArrayList<>();
        for (int i = 0; i < Statics.expressClassifyList2.get(0).getData().size(); i++) {
            data_list.add(Statics.expressClassifyList2.get(0).getData().get(i).getName());
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
        /*typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeSpinnerString = Statics.accountTypeList.get(position).getId();
                data_list = null;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/
        classifySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //二级联动
                classifySpinnerString = Statics.expressClassifyList2.get(0).getData().get(position).getId();
                Log.d("select","ss:"+position+"");
                Log.d("select",classifySpinnerString);
                //数据
                addBoolean = true;
                if ("全部".equals(classifySpinnerString)) {
                    classifySpinnerString = Statics.expressClassifyList2.get(0).getData().get(1).getId();
                }
                param = new HashMap<>();
                param.put("id",classifySpinnerString);
                HttpBasePost.postHttp(Statics.GetWXExpenseAccountReasonUrl,param, HttpTypeConstants.GetWXExpenseAccountReasonUrl);//业务类型（转出账户）
                data_list1 = new ArrayList<>();
                /*if(Statics.transferAccountClassifiesList.get(0).getData()!=null&&Statics.transferAccountClassifiesList.get(0).getData().size()>0){
                    for (TransferAccountClassify.DataBean tac:Statics.transferAccountClassifiesList.get(0).getData()) {
                        data_list1.add(tac.getName());
                    }
                }*/
                //适配器
                arr_adapter1 = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, data_list1);
                //设置样式
                arr_adapter1.setDropDownViewResource(R.layout.spinner_dropdown_style);
                //加载适配器
                reasonSpinner.setAdapter(arr_adapter1);

                reasonSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        reasonSpinnerString = Statics.transferAccountClassifiesList.get(0).getData().get(position).getId();
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
        /*//数据源
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
        });*/

    }
    private void init() {

        reset = (Button) findViewById(R.id.reset);
        add = (Button) findViewById(R.id.add);
        //typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        classifySpinner = (Spinner) findViewById(R.id.classifySpinner);
        reasonSpinner = (Spinner) findViewById(R.id.reasonSpinner);
        //payMethodSpinner = (Spinner) findViewById(R.id.payMethodSpinner);
        price = (EditText) findViewById(R.id.priceId);
        price.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        ToolUtils.setPoint(price);//设置金额输入位数
        remark = (EditText) findViewById(R.id.remarkId);
        billingTime = (EditText) findViewById(R.id.billingTime);
        poundage = (EditText) findViewById(R.id.poundage);
        poundage.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        ToolUtils.setPoint(poundage);
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
                if(type.equals("TransferAccount")){
                    Log.d("aleand","收到广播");
                    //适配器
                    //arr_adapter1.notifyDataSetChanged();
                    data_list1 = new ArrayList<>();
                    for (TransferAccountClassify.DataBean tac:Statics.transferAccountClassifiesList.get(0).getData()) {
                        data_list1.add(tac.getName());
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
            case "transfer":
                initBroadCast();
                break;
        }
    }
}
