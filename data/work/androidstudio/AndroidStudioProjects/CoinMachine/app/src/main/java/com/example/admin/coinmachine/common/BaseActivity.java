package com.example.admin.coinmachine.common;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.admin.coinmachine.ui.activity.R;
public class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    TextView iop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initView();
        iop.setText("测试是");
    }

    @Override
    public void onClick(View view) {

    }

    public void initView(){
        iop = (TextView) findViewById(R.id.iop);
    }
}
