package com.example.admin.gesturepassworddemo;

import android.app.AppOpsManager;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.utils.AppManager;
import com.example.admin.weiget.GestureContentView;
import com.example.admin.weiget.GestureDrawline;
import com.example.admin.weiget.LockIndicator;

/**
 *
 * 手势密码设置界面
 *
 */
public class GestureLockActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mTextCancel;
    private LockIndicator mLockIndicator;
    private TextView mTextTip;
    private FrameLayout mGestureContainer;
    private GestureContentView mGestureContentView;
    private TextView mTextReset;
    private boolean mIsFirstInput = true;
    private String mFirstPassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_lock);
        Log.d("GestureLockActivity", "PreOncreate");
        AppManager.getAppManager().addActivity(this);

        Log.d("GestureLockActivity", "Oncreate");
        SharedPreferences sharedPreferences = getSharedPreferences("gesturePsw", Context.MODE_PRIVATE);
        String gesturePsw = sharedPreferences.getString("gesturePsw", "");
        if (!"".equals(gesturePsw) || !TextUtils.isEmpty(gesturePsw)){
            Intent intent = new Intent(GestureLockActivity.this, GestureVerifyActivity.class);
            intent.putExtra("gesturePsw", gesturePsw);
            intent.putExtra("phoneNumber", "15852188888");
            startActivity(intent);
        }
        setUpViews();
        setUpListeners();
    }

    private void setUpListeners() {
        mTextCancel.setOnClickListener(this);
        mTextReset.setOnClickListener(this);
    }

    private void setUpViews() {
        mTextCancel = (TextView) findViewById(R.id.text_cancel);
        mTextReset = (TextView) findViewById(R.id.text_show);
        mTextReset.setClickable(true);
        mLockIndicator = (LockIndicator) findViewById(R.id.lock_indicator);
        mTextTip = (TextView) findViewById(R.id.text_tip);
        mGestureContainer = (FrameLayout) findViewById(R.id.gesture_container);
        // 初始化一个显示各个点的viewGroup
        mGestureContentView = new GestureContentView(this, false, "", new GestureDrawline.GestureCallBack() {
            @Override
            public void onGestureCodeInput(String inputCode) {
                if(!isInputPassValidate(inputCode)){//连接点过少
                    mTextTip.setText("最少链接4个点, 请重新输入");
                    mTextTip.setTextColor(0xc70c1e00);
                    mGestureContentView.clearDrawlineState(0L);
                    return;
                }
                if (mIsFirstInput){//首次使用需要绘制
                    mFirstPassword = inputCode;
                    updateCodeList(inputCode);
                    mGestureContentView.clearDrawlineState(0L);
                    mTextReset.setClickable(true);
                    mTextReset.setText("重新设置手势密码");
                } else {
                   if (inputCode.equals(mFirstPassword)){////第二次与第一次相等
                       Toast.makeText(GestureLockActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                       SharedPreferences sp = getSharedPreferences("gesturePsw", Context.MODE_PRIVATE);
                       sp.edit().putString("gesturePsw",inputCode).commit();

                       mGestureContentView.clearDrawlineState(0L);
                       Intent intent = new Intent(GestureLockActivity.this, ResultShowActivity.class);
                       intent.putExtra("code", inputCode);
                       startActivity(intent);
                       GestureLockActivity.this.finish();
                   }else{//第二次与第一次不一致
                       mTextTip.setText("与上一次绘制不一致，请重新绘制");
                       mTextTip.setTextColor(0xc70c1e00);
                       // 左右移动动画
                       Animation shakeAnimation = AnimationUtils.loadAnimation(GestureLockActivity.this, R.anim.shake);
                       mTextTip.startAnimation(shakeAnimation);
                       // 保持绘制的线，1.5秒后清除
                       mGestureContentView.clearDrawlineState(1500L);
                   }
                }
                mIsFirstInput = false;
            }

            @Override
            public void checkedSuccess() {

            }

            @Override
            public void checkedFail() {

            }
        });
        // 设置手势解锁显示到哪个布局里面
        mGestureContentView.setParentView(mGestureContainer);
        updateCodeList("");
    }

    private void updateCodeList(String inputCode) {
        // 更新选择的图案
        mLockIndicator.setPath(inputCode);
    }

    private boolean isInputPassValidate(String inputPassword) {
        if (TextUtils.isEmpty(inputPassword) || inputPassword.length() < 4) {
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.text_cancel:
                break;
            case R.id.text_show:
                mIsFirstInput = true;
                updateCodeList("");
                mTextTip.setText("绘制解锁图案");
                break;
        }
    }
}

















