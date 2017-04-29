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

	// ͨ��viewutils�󶨲���
	@ViewInject(R.id.radioGroup1)
	private RadioGroup group;
	@ViewInject(R.id.radio0)
	private RadioButton one;
	@ViewInject(R.id.radio1)
	private RadioButton two;
	@ViewInject(R.id.radio2)
	private RadioButton three;

	// ��Ƭ����
	private FragmentManager fragmentManager;

	// ��Ƭ��
	private One_Fragment one_Fragment;
	private Two_Fragment two_Fragment;
	private Three_Fragment three_Fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ��viewutils��ע�벼�֡�
		ViewUtils.inject(this);
		// ��ȡ��Ƭ����
		fragmentManager = getSupportFragmentManager();
		// ����radiobutton��Ĭ��ֵ
		one.setChecked(true);
		// ��radiogrop���м���
		group.setOnCheckedChangeListener(this);
		// ��ʼ�������ļ�
		infoView(0);
	}

	// ��ʼ�������ļ�
	@SuppressLint("CommitTransaction")
	private void infoView(int i) {
		// ��������
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// ����������Ƭ�ĳ�ʼ״̬
		infoFrament(transaction);
		// ����radiobutton�������ֵ�ı���Ƭ����ʾ״̬����������һ������Ƭ�����÷�ʽ�����Ż���Ч�����
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

	// ����������Ƭ�ĳ�ʼ״̬
	private void infoFrament(FragmentTransaction transaction) {

		// �����Ƭ��״̬����null��������Ƭ����
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
