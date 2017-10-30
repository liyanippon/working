package ui.activity;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.erp.R;

import java.util.HashMap;

import Tool.ACache;
import Tool.ToolUtils;
import Tool.crash.BaseActivity;
import Tool.statistics.AchacheConstant;
import Tool.statistics.Statics;
import http.HttpBasePost;
import http.HttpTypeConstants;
import thread.TimeThread;

/**
 * 考勤打卡
 * */
public class AttendanceCardActivity extends BaseActivity {
    private static TextView inWorkTime,outWorkTime,duringAm, duringPm,attendDate, inAttendCard, outAttendCard;
    public static FrameLayout inPunchClock,outPunch_clock;
    private static Activity activity;
    private static boolean isStartTime = false, isEndTime = false;//将其存入缓存当中
    private TextView cardTimeAm,cardTimePm;
    private static String workingTimeString;
    private static LinearLayout inAreaTrue,inWork,outWork,outAreaTrue;
    private HashMap<String,String> param;
    private ACache aCache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("考勤打卡");
        setContentView(R.layout.activity_attendance_card);

    }
    @Override
    protected void onResume() {
        super.onResume();

        //添加返回按钮
        ToolUtils.backButton(this);
        activity = AttendanceCardActivity.this;
        aCache = ACache.get(activity);
        initView();
        Thread thread =new Thread(new TimeThread(true));
        thread.start();

        isStartTime = Boolean.parseBoolean(aCache.getAsString(AchacheConstant.IS_STARTTIME));
        isEndTime = Boolean.parseBoolean(aCache.getAsString(AchacheConstant.IS_ENDTIME));
        //信息最好从网络端获取

        //判断是否已经打过卡了，上午如果已经打过显示上午信息，下午如果已经打过显示上下午信息
        if(isStartTime){
            cardTimeAm.setText(aCache.getAsString(AchacheConstant.IN_WORKINGTIME).split(" ")[1]);
        }
        if(isEndTime){//下班打卡
            cardTimeAm.setText(aCache.getAsString(AchacheConstant.IN_WORKINGTIME).split(" ")[1]);
            cardTimePm.setText(aCache.getAsString(AchacheConstant.OUT_WORKINGTIME).split(" ")[1]);
        }

    }
    private void initView() {
        inWorkTime = (TextView) findViewById(R.id.inworking_time);//工作时间
        outWorkTime = (TextView) findViewById(R.id.outworking_time);
        duringAm = (TextView) findViewById(R.id.during_am);
        duringPm = (TextView) findViewById(R.id.during_pm);
        inPunchClock = (FrameLayout) findViewById(R.id.punch_clock);//打卡按钮
        outPunch_clock = (FrameLayout) findViewById(R.id.punch_clock1);
        inWork = (LinearLayout) findViewById(R.id.in_work);
        outWork = (LinearLayout) findViewById(R.id.out_work);
        cardTimeAm = (TextView) findViewById(R.id.card_time_am);
        cardTimePm = (TextView) findViewById(R.id.card_time_pm);
        inAreaTrue = (LinearLayout) findViewById(R.id.area_true);
        outAreaTrue = (LinearLayout) findViewById(R.id.area_true1);
        attendDate = (TextView) findViewById(R.id.attend_date);
        inAttendCard = (TextView) findViewById(R.id.in_attend_card);
        outAttendCard = (TextView) findViewById(R.id.out_attend_card);
        inPunchClock.setOnClickListener(o);
        outPunch_clock.setOnClickListener(o);
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
                case R.id.punch_clock://上班打卡
                    if(!isStartTime){
                        isStartTime = true;
                        cardTimeAm.setText(workingTimeString.split(" ")[1]);
                        Toast.makeText(AttendanceCardActivity.this, "上班打卡成功", Toast.LENGTH_SHORT).show();
                        //inAreaTrue.setVisibility(View.INVISIBLE);
                        //morningIv.setImageResource(R.drawable.after_inwork);
                        aCache.put(AchacheConstant.IS_STARTTIME,Boolean.toString(isStartTime));//转为String
                        aCache.put(AchacheConstant.IN_WORKINGTIME,workingTimeString);//上班时间
                        //上传数据到服务器
                        /*param = new HashMap<>();
                        param.put("",aCache.getAsString("UserName"));//员工姓名
                        param.put("","");//上班还是下班
                        param.put("","");//考勤时间
                        HttpBasePost.postHttp(Statics.ResourceGetDownLoadFileUrl,param, HttpTypeConstants.ResourceGetDownLoadFileUrlType);//请求网络*/
                        //morning1.setVisibility(View.VISIBLE);//上传到服务器后设定
                    }else if(isStartTime&&!isEndTime){//下班打卡
                        isEndTime = true;
                        //inAreaTrue.setVisibility(View.INVISIBLE);
                        //outAreaTrue.setVisibility(View.INVISIBLE);
                        aCache.put(AchacheConstant.IS_ENDTIME,Boolean.toString(isEndTime));//转为String
                        aCache.put(AchacheConstant.OUT_WORKINGTIME,workingTimeString);//下班时间
                        cardTimePm.setText(workingTimeString.split(" ")[1]);
                        Toast.makeText(AttendanceCardActivity.this, "下班打卡成功", Toast.LENGTH_SHORT).show();
                        //afternoonIv.setImageResource(R.drawable.after_outwork);
                        //上传数据到服务器

                    }else{
                        //可以更改下班考勤时间

                    }
                    //workTime.getText();
                    break;
                case R.id.punch_clock1://下班打卡
                    if(isStartTime&&!isEndTime) {
                        isEndTime = true;
                        aCache.put(AchacheConstant.IS_ENDTIME, Boolean.toString(isEndTime));//转为String
                        aCache.put(AchacheConstant.OUT_WORKINGTIME, workingTimeString);//下班时间
                        cardTimePm.setText(workingTimeString.split(" ")[1]);
                        Toast.makeText(AttendanceCardActivity.this, "下班打卡成功", Toast.LENGTH_SHORT).show();
                    }else {
                        //可以更改下班考勤时间
                    }
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
                    //ui
                    if(!isStartTime){
                        inPunchClock.setVisibility(View.VISIBLE);
                        duringAm.setText("上班打卡");

                    }else if(!isEndTime){
                        inPunchClock.setVisibility(View.INVISIBLE);
                        outPunch_clock.setVisibility(View.VISIBLE);
                        duringPm.setText("下班打卡");
                        inWork.setVisibility(View.VISIBLE);

                    }else{
                        inPunchClock.setVisibility(View.INVISIBLE);
                        outPunch_clock.setVisibility(View.INVISIBLE);
                        inWork.setVisibility(View.VISIBLE);
                        outWork.setVisibility(View.VISIBLE);
                    }
                    String wifiName=ToolUtils.getWifiName(activity).trim();
                    if(wifiName.contains("yunvend")){
                        inAttendCard.setVisibility(View.INVISIBLE);
                        outAttendCard.setVisibility(View.INVISIBLE);
                        if(!isStartTime){
                            inAreaTrue.setVisibility(View.VISIBLE);
                        }else if(!isEndTime){
                            inAreaTrue.setVisibility(View.INVISIBLE);
                            outAreaTrue.setVisibility(View.VISIBLE);
                        }else {
                            inAreaTrue.setVisibility(View.INVISIBLE);
                            outAreaTrue.setVisibility(View.INVISIBLE);
                        }
                    }else{
                        //Toast.makeText(activity, ToolUtils.getWifiName(activity), Toast.LENGTH_SHORT).show();
                        //以后给出对话框，让用户选择
                        inAttendCard.setVisibility(View.VISIBLE);
                        outAttendCard.setVisibility(View.VISIBLE);
                    }
                    workingTimeString = msg.obj.toString();
                    inWorkTime.setText(workingTimeString.split(" ")[1]);
                    outWorkTime.setText(workingTimeString.split(" ")[1]);
                    attendDate.setText(workingTimeString.split(" ")[0]);
                    break;
            }
        }
    };
}
