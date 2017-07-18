package com.example.admin.erp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button zhu,zhe,suangzhu,suangzhe,fragement,aaaache;
    Intent in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aaaache= (Button) findViewById(R.id.fragementss);
        zhu= (Button) findViewById(R.id.zhu);
        zhe= (Button) findViewById(R.id.zhe);
        suangzhu= (Button) findViewById(R.id.suangzhu);
        suangzhe= (Button) findViewById(R.id.suangzhe);
        fragement = (Button) findViewById(R.id.fragement);

        zhu.setOnClickListener(o);
        zhe.setOnClickListener(o);
        zhu.setOnClickListener(o);
        aaaache.setOnClickListener(o);
        suangzhu.setOnClickListener(o);
        suangzhe.setOnClickListener(o);
        fragement.setOnClickListener(o);
    }
    View.OnClickListener o=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.zhu:
                    in =new Intent(MainActivity.this,ZheXianActivity.class);
                    startActivity(in);
                    break;
                case R.id.zhe:
                    in =new Intent(MainActivity.this,ZhuXingActivity.class);
                    startActivity(in);
                    break;
                case R.id.suangzhu:
                    in =new Intent(MainActivity.this,ZuiHaooActivity.class);
                    startActivity(in);
                    break;
                case R.id.suangzhe:
                    in =new Intent(MainActivity.this,ZheeActivity.class);
                    startActivity(in);
                    break;
                case R.id.fragement:
                    in =new Intent(MainActivity.this,FragementActivity.class);
                    startActivity(in);
                case R.id.fragementss:
                    in =new Intent(MainActivity.this,acacheActivity.class);
                    startActivity(in);
            }
        }
    };
}
