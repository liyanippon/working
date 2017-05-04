package com.example.admin.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String ExpressPersonNameSearchUrl = "http://192.168.1.18:8083/getWXAllNumber.ajax";
        HttpBase.expressPersionSearchHttp(ExpressPersonNameSearchUrl);
        ExpressPersonNameSearchUrl = "http://erp.yifeng-dl.com/wxApi.ajax";
        HttpBase.searchHttp(ExpressPersonNameSearchUrl,"","","",MainActivity.this,1);
    }
}
