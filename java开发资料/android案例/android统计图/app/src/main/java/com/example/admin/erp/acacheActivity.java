package com.example.admin.erp;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.admin.erp.acache.ACache;

public class acacheActivity extends AppCompatActivity {
    private Button save,pull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acache);

        save= (Button) findViewById(R.id.save);
        pull= (Button) findViewById(R.id.pull);
        save.setOnClickListener(o);
        pull.setOnClickListener(o);

    }
    View.OnClickListener o =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.save:
                    Log.d("llll", "保存数据");
                    ACache mCache = ACache.get(acacheActivity.this);
                    mCache.put("test_key1", "test value");
                    mCache.put("test_key2", "test 1010", 10);//保存10秒，如果超过10秒去获取这个key，将为null
                    mCache.put("test_key3", "test value", 2 * ACache.TIME_DAY);//保存两天，如果超过两天去获取这个key，将为null
                    break;
                case R.id.pull:
                    ACache mCacheq = ACache.get(acacheActivity.this);
                    String value = mCacheq.getAsString("test_key1");
                    String value1 = mCacheq.getAsString("test_key2");
                    Log.d("llll", "取出数据");
                    Log.d("llll", value);
                    if(value1!=null){
                        Log.d("llll", value1);
                    }

                    break;
            }
        }
    };
}
