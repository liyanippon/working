package com.example.listviewtable;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ListView;

public class ListTableActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//���ñ�����ı�����ɫ
		ViewGroup tableTitle = (ViewGroup) findViewById(R.id.table_title);
		tableTitle.setBackgroundColor(Color.rgb(177, 173, 172));
		
		List<Goods> list = new ArrayList<Goods>();
		list.add(new Goods("01", "����Ӥ���Ӹ��̷�110ml", "982323423232",34,23,23));
		list.add(new Goods("02", "���", "31312323223",34,23,23));
		list.add(new Goods("03", "ũ��ɽȪ", "12",34,23,23));
		list.add(new Goods("04", "����ę́0", "12333435445",34,23,23));
		list.add(new Goods("05", "ũ��С��", "34523",34,23,23));
		list.add(new Goods("06", "�������Ѳ�", "345456",34,23,23));
		list.add(new Goods("07", "����Сţ��", "2344",34,23,23));
		list.add(new Goods("08", "����", "23445",34,23,23));
		list.add(new Goods("09", "����԰��ʱţ��", "3234345",34,23,23));
		
		ListView tableListView = (ListView) findViewById(R.id.list);
		
		TableAdapter adapter = new TableAdapter(this, list);
		
		tableListView.setAdapter(adapter);
	}

}
