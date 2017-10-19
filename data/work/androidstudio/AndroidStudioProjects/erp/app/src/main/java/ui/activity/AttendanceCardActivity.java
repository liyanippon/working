package ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.admin.erp.R;

import Tool.ToolUtils;
import Tool.crash.BaseActivity;
/**
 * 考勤打卡
 * */
public class AttendanceCardActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("考勤打卡");
        setContentView(R.layout.activity_attendance_card);

        //添加返回按钮
        ToolUtils.backButton(this);
        new Thread(new Runnable() {//以后再做
            @Override
            public void run() {
                ToolUtils.getWifiTime();
            }
        }).start();
    }

    //返回按钮事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
