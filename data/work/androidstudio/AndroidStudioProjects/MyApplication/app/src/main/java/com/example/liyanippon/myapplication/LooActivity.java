package com.example.liyanippon.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class LooActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loo);

        Log.d("test","yuyu");
    }

    @Override
    protected void onStart() {
        super.onStart();

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); // 注意顺序
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, // 注意顺序
                R.layout.title);
    }
}
