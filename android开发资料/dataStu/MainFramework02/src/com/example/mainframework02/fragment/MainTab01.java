package com.example.mainframework02.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public class MainTab01 extends Fragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(com.example.mainframework02.R.layout.main_tab_01, container, false);

	}

}
