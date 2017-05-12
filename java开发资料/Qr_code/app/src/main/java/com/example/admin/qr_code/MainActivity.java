package com.example.admin.qr_code;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static int SCANNIN_GREQUEST_CODE = 1;
    private LinearLayout linear;
    /**
     * 显示扫描结果
     */
    private TextView mTextView ,sure ,cancle ,close;
    /**
     * 显示扫描拍的图片
     */
    //private ImageView mImageView;
    //private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MipcaActivityCapture.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, SCANNIN_GREQUEST_CODE);

    }

    private void init() {//初始化控件
        linear= (LinearLayout) findViewById(R.id.linear);
        sure = (TextView) findViewById(R.id.sure);
        cancle = (TextView) findViewById(R.id.cancle);
        close = (TextView) findViewById(R.id.close);
        linear.setVisibility(View.INVISIBLE);
        sure.setOnClickListener(o);
        cancle.setOnClickListener(o);
        close.setOnClickListener(o);
    }

    View.OnClickListener o =new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){

                case R.id.sure:
                    Toast.makeText(MainActivity.this,"确认登录",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.cancle:
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, MipcaActivityCapture.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
                    break;
                case R.id.close:
                    finish();
                    break;

            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if(resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    String result  = bundle.getString("result");
                    Toast.makeText(MainActivity.this,"扫码内容为:"+result,Toast.LENGTH_LONG).show();
                    linear.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}
