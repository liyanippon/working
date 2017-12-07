package ui.activity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.Spinner;
import android.widget.Toast;
import com.example.admin.erp.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import Tool.ACache;
import Tool.ToolUtils;
import Tool.customerWidget.YearMonthPickerDialog;
import Tool.statistics.AchacheConstant;
import Tool.statistics.Statics;
import http.ExpressBillingManagementHttpPost;
import http.HttpBasePost;
import http.HttpTypeConstants;
public class AddFinancialSalaryActivity extends AppCompatActivity {
    private EditText basePay,travellingAllowance,bonus,trafficAllowance,overtimeAllowance,expense,leavePay,housingFund
    ,endowmentInsurance,medicalInsurance,unemploymentInsurance ,billingTime;
    private String basePayString,travellingAllowanceString,bonusString,trafficAllowanceString,overtimeAllowanceString
            ,expenseString,leavePayString,housingFundString
            ,endowmentInsuranceString,medicalInsuranceString,unemploymentInsuranceString
            ,nameSpinnerString,billingTimeString,relavenceSpinnerString,buildStyleSpinnerString;
    private Spinner nameSpinner,relavenceSpinner,buildStyleSpinner;
    private Button add,reset;
    private static List<String> data_list;
    public static ArrayAdapter<String> arr_adapterName,arr_adapterRelavence,arr_adapterBuildStyle;
    private HashMap<String,String> param;
    private Calendar calendar;
    private int currentYear,currentMon;
    private String[] time;
    ACache aCache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("添加工资");
        setContentView(R.layout.activity_add_financial_salary);

        //添加返回按钮
        ToolUtils.backButton(this);//返回图标
        findViewById();//绑定控件
        spinnerType();//下拉菜单
        calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMon = calendar.get(Calendar.MONTH)+1;
        billingTime.setText(String.format("%d-%d",currentYear,currentMon));
        billingTime.setOnClickListener(o);
        add.setOnClickListener(o);
        reset.setOnClickListener(o);
    }

    private void spinnerType() {
        //姓名查询
        /*AttendanceStatisticsHttpPost ash = new AttendanceStatisticsHttpPost();
        ash.searchStaffNameHttp(Statics.AttendanceStatisticsSearchUrl,AttendanceStatisticsActivity.this);*/
        //HttpBasePost.postHttp(Statics.AttendanceStatisticsSearchUrl,null,HttpTypeConstants.SearchYearUrlType);
        //数据 员工姓名
        data_list = new ArrayList<>();
        for (int i=0;i<Statics.searchName.size();i++){
            data_list .add(Statics.searchName.get(i).getNick_name());
            Log.d("AttendanceStatisticsAct", "全部姓名：" + Statics.searchName.get(i).toString());
        }
        for (int i=0;i<Statics.searchNameId.size();i++){
            //data_list .add(Statics.searchNameId.get(i));
            Log.d("AttendanceStatisticsAct", "全部Id：" + Statics.searchNameId.get(i).toString());
        }
        //适配器
        arr_adapterName = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        arr_adapterName.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        nameSpinner.setAdapter(arr_adapterName);
        data_list = null;
        nameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.d("AttendanceStatisticsAct", "校对姓名：" + Statics.searchNameId.get(1).toString());
                    nameSpinnerString = Statics.searchName.get(position).getUser_id();
                    Log.d("AttendanceStatisticsAct", "选中姓名：" + nameSpinnerString);
                    for (int i=0;i<Statics.searchNameId.size();i++){
                        Log.d("AttendanceStatisticsAct",Statics.searchNameId.get(i));
                        Log.d("AttendanceStatisticsAct","下拉："+Statics.searchName.get(i));
                    }
                data_list = null;
                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //关联加班
        //数据 员工姓名
        data_list = new ArrayList<>();
        data_list.add("关联加班");
        data_list.add("不关联加班");
        //适配器
        arr_adapterRelavence = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        arr_adapterRelavence.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        relavenceSpinner.setAdapter(arr_adapterRelavence);
        relavenceSpinner.setSelection(1);
        data_list = null;
        relavenceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    relavenceSpinnerString = "yes";
                }else if(position==1){
                    relavenceSpinnerString = "";
                }
                data_list = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //生成方式
        //数据 员工姓名
        data_list = new ArrayList<>();
        data_list.add("关联考勤");
        data_list.add("不关联考勤");
        //适配器
        arr_adapterBuildStyle = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_addaccount_display_style, R.id.txtvwSpinner, data_list);
        //设置样式
        arr_adapterBuildStyle.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //加载适配器
        buildStyleSpinner.setAdapter(arr_adapterBuildStyle);
        buildStyleSpinner.setSelection(1);
        data_list = null;
        buildStyleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    buildStyleSpinnerString = "021001";//关联考勤
                }else if(position==1){
                    buildStyleSpinnerString = "021002";//不关联考勤
                }
                data_list = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void initView() {
        basePayString = basePay.getText().toString().trim();
        travellingAllowanceString = travellingAllowance.getText().toString().trim();
        bonusString = bonus.getText().toString().trim();
        trafficAllowanceString = trafficAllowance.getText().toString().trim();
        overtimeAllowanceString = overtimeAllowance.getText().toString().trim();
        expenseString = expense.getText().toString().trim();
        leavePayString = leavePay.getText().toString().trim();
        housingFundString = housingFund.getText().toString().trim();
        endowmentInsuranceString = endowmentInsurance.getText().toString().trim();
        medicalInsuranceString = medicalInsurance.getText().toString().trim();
        unemploymentInsuranceString = unemploymentInsurance.getText().toString().trim();
        billingTimeString = billingTime.getText().toString().trim();
    }

    View.OnClickListener o= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.add:
                    initView();
                    time =billingTimeString.split("-",2);
                    if ("".equals(basePayString) ) {
                        Toast.makeText(AddFinancialSalaryActivity.this, "所填数据不能为空", Toast.LENGTH_LONG).show();
                    } else {
                        param = new HashMap<>();
                        param.put("userName",nameSpinnerString);//姓名
                        param.put("basePay",basePayString);//基本工资
                        param.put("travellingAllowance",travellingAllowanceString);//出差补助
                        param.put("bonus", bonusString);//奖金
                        param.put("trafficAllowance",trafficAllowanceString);//交通补助
                        param.put("overtimeAllowance",overtimeAllowanceString);//加班补助
                        param.put("expense", expenseString);//报销
                        param.put("leavePay", leavePayString);//病事假工资
                        param.put("housingFund", housingFundString);//住房公积金
                        param.put("medicalInsurance", medicalInsuranceString);//医疗保险
                        param.put("endowmentInsurance", endowmentInsuranceString);//养老保险
                        param.put("unemploymentInsurance", unemploymentInsuranceString);//失业保险
                        param.put("year",time[0]);
                        param.put("month",time[1]);
                        param.put("createBy", aCache.getAsString(AchacheConstant.USER_NAME));
                        param.put("updateBy", aCache.getAsString(AchacheConstant.USER_NAME));
                        param.put("billingTime", billingTimeString);//billingTime 创建时间
                        param.put("updateTime", billingTimeString);
                        param.put("yes", relavenceSpinnerString);//是否关联加班
                        param.put("type",buildStyleSpinnerString);//生成方式
                        HttpBasePost.postHttp(aCache.getAsString(AchacheConstant.FINANCIAL_SALARY_GETWXINSERT_PAYROLL_URL),param, HttpTypeConstants.FinancialSalaryGetWXInsertPayrollUrlType);
                        finish();
                    }
                    break;
                case R.id.reset:
                    basePay.setText("");
                    travellingAllowance.setText("");
                    bonus.setText("");
                    trafficAllowance.setText("");
                    overtimeAllowance.setText("");
                    expense.setText("");
                    leavePay.setText("");
                    housingFund.setText("");
                    endowmentInsurance.setText("");
                    medicalInsurance.setText("");
                    unemploymentInsurance.setText("");
                    break;
                case R.id.createTime:
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
        new YearMonthPickerDialog(AddFinancialSalaryActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month++;
                        billingTime.setText(String.format("%d-%d",year,month));
                    }
                },
                mYear, mMonth, mDay).show();

    }

    private void findViewById() {
        basePay = (EditText) findViewById(R.id.jibengongzi);
        travellingAllowance = (EditText) findViewById(R.id.chucaibuzhu);
        bonus = (EditText) findViewById(R.id.jiangjingongzi);
        trafficAllowance = (EditText) findViewById(R.id.jiaotongbuzhu);
        overtimeAllowance = (EditText) findViewById(R.id.jiabanbuzhu);
        expense = (EditText) findViewById(R.id.baoxiao);
        leavePay = (EditText) findViewById(R.id.bingshijiagongzi);
        housingFund = (EditText) findViewById(R.id.zhufanggongjijin);
        endowmentInsurance = (EditText) findViewById(R.id.yanglaobaoxian);
        medicalInsurance = (EditText) findViewById(R.id.yiliaobaoxian);
        unemploymentInsurance = (EditText) findViewById(R.id.shiyebaoxian);
        nameSpinner = (Spinner) findViewById(R.id.nameSpinner);
        add = (Button) findViewById(R.id.add);
        reset = (Button) findViewById(R.id.reset);
        billingTime = (EditText) findViewById(R.id.createTime);
        relavenceSpinner = (Spinner) findViewById(R.id.guanlianjiaban);
        buildStyleSpinner = (Spinner) findViewById(R.id.shengchengfa);
        basePay.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        travellingAllowance.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        bonus.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        trafficAllowance.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        overtimeAllowance.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        expense.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        leavePay.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        housingFund.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        endowmentInsurance.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        medicalInsurance.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        unemploymentInsurance.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        ToolUtils.setPoint(basePay);//设置金额输入位数
        ToolUtils.setPoint(travellingAllowance);
        ToolUtils.setPoint(bonus);
        ToolUtils.setPoint(trafficAllowance);
        ToolUtils.setPoint(overtimeAllowance);
        ToolUtils.setPoint(expense);
        ToolUtils.setPoint(leavePay);
        ToolUtils.setPoint(housingFund);
        ToolUtils.setPoint(endowmentInsurance);
        ToolUtils.setPoint(medicalInsurance);
        ToolUtils.setPoint(unemploymentInsurance);
        aCache = ACache.get(AddFinancialSalaryActivity.this);
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
}
