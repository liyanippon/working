package com.ferun.pullrefresh;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;

import com.ferun.pullrefresh.PullToRefreshList.OnRefreshListener;

public class PullrefreshActivity extends Activity
{
    private PullToRefreshList list;

    List<String> data = new ArrayList<String>();

    ArrayAdapter<String> a;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        list = (PullToRefreshList) findViewById(R.id.list);
        for (int i = 0; i < 10; ++i)
        {
            data.add(new String("item " + i));
        }
        a = new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, data);
        list.setOnRefreshListener(new OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                new GetDataTask().execute();
            }
        });
        list.setAdapter(a);

        list.prepareForRefresh();
        list.onRefresh();
    }

    private class GetDataTask extends AsyncTask<Void, Void, String[]>
    {
        @Override
        protected void onPostExecute(String[] result)
        {
            data.add("add item " + data.size());
            // Call onRefreshComplete when the list has been refreshed.
            list.onRefreshComplete();
            super.onPostExecute(result);
        }

        @Override
        protected String[] doInBackground(Void... params)
        {
            return null;
        }
    }
}