package ui.fragement.menu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.admin.erp.MainActivity;
import com.example.admin.erp.R;

import Tool.ACache;
import Tool.statistics.AchacheConstant;
import Tool.statistics.Statics;
@SuppressLint("NewApi")
public class MainTabSetting extends Fragment{
	private View settingLayout;
	private TextView userName;
	private Button exitAccount;
	private Intent in;
	ACache aCache;
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		//初始化
		init(inflater,container);
		userName.setText(aCache.getAsString(AchacheConstant.USER_NAME));

		return settingLayout;
	}
	private void init(LayoutInflater inflater,ViewGroup container) {
		settingLayout = inflater.inflate(R.layout.main_tab_setting, container, false);
		userName = (TextView) settingLayout.findViewById(R.id.userName);
		exitAccount = (Button) settingLayout.findViewById(R.id.exitAccount);
		exitAccount.setOnClickListener(o);
		aCache =ACache.get(getActivity());
	}
	View.OnClickListener o = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()){
				case R.id.exitAccount:
					in = new Intent(getActivity(), MainActivity.class);
					startActivity(in);
					getActivity().finish();
					break;
			}
		}
	};
}
