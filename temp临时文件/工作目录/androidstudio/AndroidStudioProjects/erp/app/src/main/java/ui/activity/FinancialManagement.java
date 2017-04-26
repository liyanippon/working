package ui.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.admin.erp.R;

/**
 * Created by admin on 2017/2/21.
 */

public class FinancialManagement extends Fragment {
    private View view;
    private ListView fm;
    private String[] data = {"物流帐单管理", "物流帐单统计分析"  ,"业务员揽件量" ,"业务员揽件量统计分析"};
    private Intent in;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.controllayout, container, false);
        fm = (ListView) view.findViewById(R.id.fm);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, data);
        fm.setAdapter(adapter);

        fm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        in = new Intent(getActivity(), AccountManagementActivity.class);
                        Log.v("ui","----------------");
                        startActivity(in);
                        break;
                    case 1:
                        in = new Intent(getActivity(), BillingStatisticsActivity.class);
                        startActivity(in);
                        break;
                    case 2:
                        in = new Intent(getActivity(),ExpressNumberManagerActivity.class);
                        Log.v("ui","++++++++++++++++");

                        startActivity(in);
                        break;
                    /*case 3:
                        in = new Intent(getActivity(),ExpressNumberManagerActivity.class);
                        startActivity(in);
                        break;*/
                    case 3:
                        in = new Intent(getActivity(),ExpressStatisticsActivity.class);
                        startActivity(in);
                        break;
                    /*case 4:
                        in = new Intent(getActivity(), ChartsFragementActivity.class);
                        startActivity(in);
                        break;*/
                }
            }
        });

        return view;
    }
}
