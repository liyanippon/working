package com.example.admin.gesturepassworddemo;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.admin.utils.AppManager;

public class ResultShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_show);

        AppManager.getAppManager().addActivity(this);

        Intent intent = getIntent();
        TextView textView = (TextView) findViewById(R.id.result);
        textView.setText("手势密码是：" + intent.getStringExtra("code"));

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishAllActivity();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }
}
