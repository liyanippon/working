package ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.erp.R;

import org.w3c.dom.Text;

import Tool.ToolUtils;
import Tool.crash.BaseActivity;
import thread.TimeThread;

/**
 * 考勤打卡
 * */
public class AttendanceCardActivity extends BaseActivity {
    private static TextView startTime,endTime;
    private FrameLayout punchClock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("考勤打卡");
        setContentView(R.layout.activity_attendance_card);

        //添加返回按钮
        ToolUtils.backButton(this);
        initView();

        Thread thread =new Thread(new TimeThread(true));
        thread.start();
    }

    private void initView() {
        startTime = (TextView) findViewById(R.id.start_time);//上班时间
        punchClock = (FrameLayout) findViewById(R.id.punch_clock);//打卡按钮
        punchClock.setOnClickListener(o);
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

    View.OnClickListener o = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.punch_clock:
                    Toast.makeText(AttendanceCardActivity.this, "打卡成功", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    //uiHandler在主线程中创建，所以自动绑定主线程
    public static Handler uiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    //更新时间ui
                    startTime.setText(msg.obj.toString().split(" ")[1]);
                    break;
            }
        }
    };

}
