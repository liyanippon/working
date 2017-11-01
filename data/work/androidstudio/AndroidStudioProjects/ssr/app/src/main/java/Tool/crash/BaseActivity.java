package Tool.crash;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.admin.erp.R;
import thread.ResumeStart;
import ui.activity.menu.MenuFragmentMainActivity;

/**
 * Created by admin on 2017/5/23.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);//对所有Activity进行统一处理
        setContentView(R.layout.activity_main);

        Log.d("BaseActivity", "log");
        Log.d("BaseActivity", "logss");
    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

        //內存不足
        if (level == TRIM_MEMORY_MODERATE) {

        }
    }

}
