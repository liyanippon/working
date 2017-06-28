package com.example.admin.gesturepassworddemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.utils.AppManager;
import com.example.admin.weiget.GestureContentView;
import com.example.admin.weiget.GestureDrawline;

/**
 *
 * 手势绘制/校验界面
 *
 */
public class GestureVerifyActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mTextTip;
    private FrameLayout mGestureContainer;
    private GestureContentView mGestureContentView;
    private TextView mTextForget;
    private TextView mTextOther;
    private TextView phoneTextView;
    private int count = 3;//解锁操作三次
    private String gestureCode;
    private String phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_verify);
        AppManager.getAppManager().addActivity(this);

        ObtainExtraData();
        setUpViews();
        setUpListeners();

    }

    private void setUpListeners() {
        mTextForget.setOnClickListener(this);
        mTextOther.setOnClickListener(this);
    }

    private void setUpViews() {
        phoneTextView = (TextView) findViewById(R.id.text_phone_number);
        mTextTip = (TextView) findViewById(R.id.text_tip);
        mGestureContainer = (FrameLayout) findViewById(R.id.gesture_container);
        mTextForget = (TextView) findViewById(R.id.text_forget_gesture);
        mTextOther = (TextView) findViewById(R.id.text_other_account);
        phoneTextView.setText(getProtectedMobile(phoneNumber));
        // 初始化一个显示各个点的viewGroup
        mGestureContentView = new GestureContentView(this, true, gestureCode,
                new GestureDrawline.GestureCallBack() {
                    @Override
                    public void onGestureCodeInput(String inputCode) {

                    }

                    @Override
                    public void checkedSuccess() {
                        if (count > 0){
                            mGestureContentView.clearDrawlineState(0L);
                            Toast.makeText(GestureVerifyActivity.this, "密码正确", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(GestureVerifyActivity.this, ResultShowActivity.class);
                            intent.putExtra("code", gestureCode);
                            startActivity(intent);;
                            GestureVerifyActivity.this.finish();
                            AppManager.getAppManager().finishAllActivity();
                        }else{
                            showExceedTimes();
                        }
                    }

                    @Override
                    public void checkedFail() {
                        count --;
                        if(count > -1){
                            mGestureContentView.clearDrawlineState(1300L);
                            mTextTip.setVisibility(View.VISIBLE);
                            mTextTip.setText(Html.fromHtml("<font color='#c70c1e'>密码错误，还有</font>"
                                    + "<font color='#ffffff'>" + count + "</font>"
                                    + "<font color='#c70c1e'>次机会</font>"));
                            //左右移动动画
                            Animation shakeAnimation = AnimationUtils.loadAnimation(GestureVerifyActivity.this,R.anim.shake);
                            mTextTip.startAnimation(shakeAnimation);
                        }else{
                            showExceedTimes();
                        }
                    }
                });
        // 设置手势解锁显示到哪个布局里面
        mGestureContentView.setParentView(mGestureContainer);
    }

    private String getProtectedMobile(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber) || phoneNumber.length() < 11) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(phoneNumber.subSequence(0,3));
        builder.append("****");
        builder.append(phoneNumber.subSequence(7,11));
        return builder.toString();
    }


    private void showExceedTimes() {
            AlertDialog.Builder dialog = new AlertDialog.Builder(GestureVerifyActivity.this);
            dialog.setTitle("提示")
                    .setMessage("解锁超过三次!!!是否找回密码？")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AppManager.getAppManager().finishAllActivity();
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            openResultActivity();
                        }
                    })
                    .show();

    }

    private void ObtainExtraData() {
        Intent intent = getIntent();
        gestureCode = intent.getStringExtra("gesturePsw");
        phoneNumber = intent.getStringExtra("phoneNumber");
    }

    private void openResultActivity() {
        Intent intent = new Intent(GestureVerifyActivity.this, ResultShowActivity.class);
        intent.putExtra("code", gestureCode);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_forget_gesture:
                openResultActivity();
                AppManager.getAppManager().finishAllActivity();
                break;
            case R.id.text_other_account:
                Toast.makeText(getApplicationContext(), "其它方式登录", Toast.LENGTH_SHORT).show();
                AppManager.getAppManager().finishAllActivity();
                this.finish();
                break;
            default:
                break;
        }
    }
}



















