package ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.admin.erp.R;

import Tool.ToolUtils;
import Tool.statistics.Statics;
import http.FinancialManagementHttpPost;

public class AddFinancialBillingManagerActivity extends AppCompatActivity{
    private Button add, reset;
    private EditText customerName,phone,address, remark;
    private FinancialManagementHttpPost httpPost;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("添加客户");
        setContentView(R.layout.activity_add_financial_manager);

        //添加返回按钮
        ToolUtils.backButton(this);
        context = getApplicationContext();
        init();
        add.setOnClickListener(o);
        reset.setOnClickListener(o);
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
                    String customerNameString =customerName.getText().toString().trim();
                    String phoneString = phone.getText().toString().trim();
                    String addressString = address.getText().toString().trim();
                    String remarkString = remark.getText().toString().trim();
                    if ( "".equals(customerNameString)||"".equals(phoneString)||"".equals(addressString)
                            || "".equals(remarkString)) {
                        Toast.makeText(AddFinancialBillingManagerActivity.this, "所填数据不能为空", Toast.LENGTH_LONG).show();
                    } else {
                        httpPost = new FinancialManagementHttpPost();
                        if ("success".equals(httpPost.addCountManagerHttp(
                                Statics.FinancialBillingManagementSearchUrl, customerNameString, phoneString,
                                addressString, remarkString))) {
                            Toast.makeText(AddFinancialBillingManagerActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                    break;
                case R.id.reset:
                    customerName.setText("");
                    phone.setText("");
                    address.setText("");
                    remark.setText("");
                    break;
            }
        }
    };

    private void init() {
        reset = (Button) findViewById(R.id.reset);
        add = (Button) findViewById(R.id.add);
        customerName = (EditText) findViewById(R.id.customerName);
        phone = (EditText) findViewById(R.id.phone);
        address = (EditText) findViewById(R.id.address);
        remark = (EditText) findViewById(R.id.remarkId);
    }

}
