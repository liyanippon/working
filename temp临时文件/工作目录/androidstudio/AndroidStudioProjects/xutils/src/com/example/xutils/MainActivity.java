package com.example.xutils;

import java.util.List;

import com.example.xutils.fragment.One_Fragment;
import com.example.xutils.fragment.Three_Fragment;
import com.example.xutils.fragment.Two_Fragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity implements
		OnCheckedChangeListener {

	// 通过viewutils绑定布局
	@ViewInject(R.id.radioGroup1)
	private RadioGroup group;
	@ViewInject(R.id.radio0)
	private RadioButton one;
	@ViewInject(R.id.radio1)
	private RadioButton two;
	@ViewInject(R.id.radio2)
	private RadioButton three;

	// 碎片管理
	private FragmentManager fragmentManager;

	// 碎片分
	private One_Fragment one_Fragment;
	private Two_Fragment two_Fragment;
	private Three_Fragment three_Fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 向viewutils中注入布局。
		ViewUtils.inject(this);
		// 获取碎片管理
		fragmentManager = getSupportFragmentManager();
		// 设置radiobutton的默认值
		one.setChecked(true);
		// 对radiogrop进行监听
		group.setOnCheckedChangeListener(this);
		// 初始化布局文件
		infoView(0);
	}

	// 初始化布局文件
	@SuppressLint("CommitTransaction")
	private void infoView(int i) {
		// 开启事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 设置所有碎片的初始状态
		infoFrament(transaction);
		// 根据radiobutton传入的数值改变碎片的显示状态，并且在这一步对碎片的启用方式进行优化，效率提高
		switch (i) {
		case 0:
			if (one_Fragment == null) {
				one_Fragment = new One_Fragment();
				transaction.add(R.id.main_frame, one_Fragment);
			} else {
				transaction.show(one_Fragment);
			}
			break;
		case 1:
			if (two_Fragment == null) {
				two_Fragment = new Two_Fragment();
				transaction.add(R.id.main_frame, two_Fragment);
			} else {
				transaction.show(two_Fragment);
			}
			break;
		case 2:
			if (three_Fragment == null) {
				three_Fragment = new Three_Fragment();
				transaction.add(R.id.main_frame, three_Fragment);
			} else {
				transaction.show(three_Fragment);
			}
			break;

		default:
			break;
		}

		transaction.commit();

	}

	// 设置所有碎片的初始状态
	private void infoFrament(FragmentTransaction transaction) {

		// 如果碎片的状态不是null，则让碎片隐藏
		if (one_Fragment != null) {
			transaction.hide(one_Fragment);
		}
		if (two_Fragment != null) {
			transaction.hide(two_Fragment);
		}
		if (three_Fragment != null) {
			transaction.hide(three_Fragment);
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {

		switch (checkedId) {
		case R.id.radio0:
			infoView(0);
			one.setChecked(true);
			break;
		case R.id.radio1:
			infoView(1);
			two.setChecked(true);
			break;
		case R.id.radio2:
			infoView(2);
			three.setChecked(true);
			break;

		default:
			break;
		}
	}
}
