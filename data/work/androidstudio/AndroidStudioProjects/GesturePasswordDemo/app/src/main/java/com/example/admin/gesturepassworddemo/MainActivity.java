package com.example.admin.gesturepassworddemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button gesture;
    private Intent in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化
        init();

    }

    private void init() {
        gesture = (Button) findViewById(R.id.gesture);
        gesture.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.gesture:
                Log.d("MainActivity", "gesture");
                in = new Intent(MainActivity.this,GestureLockActivity.class);
                startActivity(in);
                break;
        }
    }
}
