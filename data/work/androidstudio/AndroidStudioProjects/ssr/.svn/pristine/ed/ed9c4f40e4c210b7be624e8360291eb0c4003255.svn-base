package ui.activity;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.erp.R;

import java.util.ArrayList;
import java.util.List;

import Tool.ToolUtils;
import Tool.statistics.Statics;
import http.ExpressBillingManagementHttpPost;

public class AddFinancialSalaryActivity extends AppCompatActivity {
    private EditText jibenGongzi,chucaiBuzhu,jiangjinGongzi,jiaotongBuzhu,jiabanBuzhu,baoXiao,bshiJiaGongzi,zhuFangGongjijin
    ,yangLaoBaoxian,yiLiaoBaoxian,shiYeBaoxian ;
    private String jibenGongziString,chucaiBuzhuString,jiangjinGongziString,jiaotongBuzhuString,jiabanBuzhuString
            ,baoXiaoString,bshiJiaGongziString,zhuFangGongjijinString
            ,yangLaoBaoxianString,yiLiaoBaoxianString,shiYeBaoxianString
            ,nameSpinnerString;
    private Spinner nameSpinner;
    private Button add,reset;
    private static List<String> data_list;
    public static ArrayAdapter<String> arr_adapterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("添加工资");
        setContentView(R.layout.activity_add_financial_salary);

        //添加返回按钮
        ToolUtils.backButton(this);//返回图标
        findViewById();//绑定控件
        spinnerType();//下拉菜单
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


    }

    private void initView() {
        jibenGongziString = jiangjinGongzi.getText().toString().trim();
        chucaiBuzhuString = chucaiBuzhu.getText().toString().trim();
        jiangjinGongziString = jiangjinGongzi.getText().toString().trim();
        jiaotongBuzhuString = jiaotongBuzhu.getText().toString().trim();
        jiabanBuzhuString = jiabanBuzhu.getText().toString().trim();
        baoXiaoString = baoXiao.getText().toString().trim();
        bshiJiaGongziString = bshiJiaGongzi.getText().toString().trim();
        zhuFangGongjijinString = zhuFangGongjijin.getText().toString().trim();
        yangLaoBaoxianString = yangLaoBaoxian.getText().toString().trim();
        yiLiaoBaoxianString = yiLiaoBaoxian.getText().toString().trim();
        shiYeBaoxianString = shiYeBaoxian.getText().toString().trim();
    }

    View.OnClickListener o= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.add:
                    initView();

                    break;
                case R.id.reset:
                    jibenGongzi.setText("");
                    chucaiBuzhu.setText("");
                    jiangjinGongzi.setText("");
                    jiaotongBuzhu.setText("");
                    jiabanBuzhu.setText("");
                    baoXiao.setText("");
                    bshiJiaGongzi.setText("");
                    zhuFangGongjijin.setText("");
                    yangLaoBaoxian.setText("");
                    yiLiaoBaoxian.setText("");
                    shiYeBaoxian.setText("");
                    break;
            }
        }
    };

    private void findViewById() {
        jibenGongzi = (EditText) findViewById(R.id.jibengongzi);
        chucaiBuzhu = (EditText) findViewById(R.id.chucaibuzhu);
        jiangjinGongzi = (EditText) findViewById(R.id.jiangjingongzi);
        jiaotongBuzhu = (EditText) findViewById(R.id.jiaotongbuzhu);
        jiabanBuzhu = (EditText) findViewById(R.id.jiabanbuzhu);
        baoXiao = (EditText) findViewById(R.id.baoxiao);
        bshiJiaGongzi = (EditText) findViewById(R.id.bingshijiagongzi);
        zhuFangGongjijin = (EditText) findViewById(R.id.zhufanggongjijin);
        yangLaoBaoxian = (EditText) findViewById(R.id.yanglaobaoxian);
        yiLiaoBaoxian = (EditText) findViewById(R.id.yiliaobaoxian);
        shiYeBaoxian = (EditText) findViewById(R.id.shiyebaoxian);
        nameSpinner = (Spinner) findViewById(R.id.nameSpinner);
        add = (Button) findViewById(R.id.add);
        reset = (Button) findViewById(R.id.reset);
        jibenGongzi.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        chucaiBuzhu.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        jiangjinGongzi.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        jiaotongBuzhu.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        jiabanBuzhu.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        baoXiao.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        bshiJiaGongzi.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        zhuFangGongjijin.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        yangLaoBaoxian.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        yiLiaoBaoxian.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        shiYeBaoxian.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        ToolUtils.setPoint(jibenGongzi);//设置金额输入位数
        ToolUtils.setPoint(chucaiBuzhu);
        ToolUtils.setPoint(jiangjinGongzi);
        ToolUtils.setPoint(jiaotongBuzhu);
        ToolUtils.setPoint(jiabanBuzhu);
        ToolUtils.setPoint(baoXiao);
        ToolUtils.setPoint(bshiJiaGongzi);
        ToolUtils.setPoint(zhuFangGongjijin);
        ToolUtils.setPoint(yangLaoBaoxian);
        ToolUtils.setPoint(yiLiaoBaoxian);
        ToolUtils.setPoint(shiYeBaoxian);
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
