package ui.activity.menu;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.erp.MainActivity;
import com.example.admin.erp.R;
import java.util.ArrayList;
import java.util.Properties;
import Tool.ACache;
import Tool.crash.ActivityCollector;
import Tool.crash.BaseActivity;
import Tool.statistics.Statics;
import http.HttpTypeConstants;
import model.javabean.UserUmp;
import presenter.LoginPresenterImpl;
import service.ErpService;
import thread.ResumeStart;
import ui.activity.BillingStatisticsActivity;
import ui.adpter.MFragmentPagerAdapter;
import ui.fragement.menu.MainTabAttendance;
import ui.fragement.menu.MainTabExpress;
import ui.fragement.menu.MainTabFinancial;
import ui.fragement.menu.MainTabProject;
import ui.fragement.menu.MainTabResource;
import ui.fragement.menu.MainTabSetting;

public class MenuFragmentMainActivity extends BaseActivity{
    private LinearLayout attendanceTextView,expressTextView,financialTextView
            ,setting_layout,projectTextView,resourceTextView;
    //实现Tab滑动效果
    private ViewPager mViewPager;
    //动画图片
    private ImageView cursor;
    //动画图片偏移量
    private int offset = 0;
    private int position_one;
    private int position_two;
    private int position_three;
    private int position_four;
    private int position_five;
    //动画图片宽度
    private int bmpW;
    //当前页卡编号
    private int currIndex = 0;
    //存放Fragment
    private ArrayList<Fragment> fragmentArrayList;
    //管理Fragment
    private android.support.v4.app.FragmentManager fragmentManager;
    public static Activity context;
    private ImageButton express,attendance,setting,financial,project,resource;
    private TextView expresstext,attendancetext,settingtext,financialtext,projectText,resourceText;
    private long exitTime = 0;
    //消息推送
    private NotificationManager mNManager;
    private Notification notify1;
    Bitmap LargeBitmap = null;
    private static final int NOTIFYID_1 = 1;
    private Button btn_show_normal;
    private Button btn_close_normal;
    private Properties properties;
    LoginPresenterImpl presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("主菜单");
        setContentView(R.layout.activity_menu_fragment_main);

        context = MenuFragmentMainActivity.this;
        //初始化TextView
        InitTextView();
        //初始化ImageView
        InitImageView();
        //初始化Fragment
        InitFragment();
        //初始化ViewPager
        InitViewPager();

    }
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {

        /*Intent intent =new Intent(MenuFragmentMainActivity.this, ErpService.class);
        startService(intent);*/
        return super.onCreateView(name, context, attrs);
    }


    //再按一次返回键退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                //Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                /*ActivityCollector.finishAll();方法有时不好用，暂时去除
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);*/
                //返回桌面（模仿微信）
                Intent home=new Intent(Intent.ACTION_MAIN);
                home.addCategory(Intent.CATEGORY_HOME);
                startActivity(home);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Thread resumeStart = new Thread(new ResumeStart(MenuFragmentMainActivity.this));
        resumeStart.start();
    }
    /**
     * 初始化头标
     */
    private void InitTextView(){
        //考勤管理
        attendancetext = (TextView) findViewById(R.id.kaoqintext);
        attendanceTextView = (LinearLayout) findViewById(R.id.attendance_text);
        //物流管理
        expressTextView = (LinearLayout) findViewById(R.id.express_text);
        expresstext = (TextView) findViewById(R.id.expresstext);
        //财务管理
        financialtext = (TextView) findViewById(R.id.caiwutext);
        financialTextView = (LinearLayout) findViewById(R.id.caiwu_text);
        //设置
        setting_layout = (LinearLayout) findViewById(R.id.seting_layout);
        settingtext = (TextView) findViewById(R.id.settingtext);
        //项目
        projectText = (TextView) findViewById(R.id.projecttext);
        projectTextView = (LinearLayout) findViewById(R.id.project_text);
        //人员池
        resourceText = (TextView) findViewById(R.id.resourcetext);
        resourceTextView = (LinearLayout) findViewById(R.id.resource_text);
        //添加点击事件
        attendanceTextView.setOnClickListener(new MyOnClickListener(0));
        expressTextView.setOnClickListener(new MyOnClickListener(1));
        financialTextView.setOnClickListener(new MyOnClickListener(3));
        setting_layout.setOnClickListener(new MyOnClickListener(5));
        projectTextView.setOnClickListener(new MyOnClickListener(2));
        resourceTextView.setOnClickListener(new MyOnClickListener(4));
    }
    /**
     * 初始化页卡内容区
     */
    private void InitViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.vPager);
        mViewPager.setAdapter(new MFragmentPagerAdapter(fragmentManager, fragmentArrayList));
        //让ViewPager缓存2个页面
        mViewPager.setOffscreenPageLimit(2);
        //设置默认打开第一页
        mViewPager.setCurrentItem(0);
        //pictureTextView.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
        //设置viewpager页面滑动监听事件
        mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        express= (ImageButton) findViewById(R.id.express);
        attendance = (ImageButton) findViewById(R.id.kaoqin);
        financial = (ImageButton) findViewById(R.id.caiwu);
        setting = (ImageButton) findViewById(R.id.setting);
        project = (ImageButton) findViewById(R.id.project);
        resource = (ImageButton) findViewById(R.id.resource);
    }
   /* *//**
     * 初始化动画
     */
    private void InitImageView() {
        cursor = (ImageView) findViewById(R.id.cursor);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 获取分辨率宽度
        int screenW = dm.widthPixels;
        bmpW = (screenW/6);
        bmpW = 0;//将原来的缺口去除，但是动画代码仍然不要动，具体原理我也不懂
        //设置动画图片宽度
        setBmpW(cursor, bmpW);
        offset = 0;
        //动画图片偏移量赋值
        position_one = (int) (screenW / 6.0);
        position_two = position_one * 2;
        position_three = position_one * 3;
        position_four = position_one * 4;
        position_five = position_five * 5;
    }
    /**
     * 初始化Fragment，并添加到ArrayList中
     */
    private void InitFragment(){
        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new MainTabAttendance());
        fragmentArrayList.add(new MainTabExpress());
        fragmentArrayList.add(new MainTabProject());
        fragmentArrayList.add(new MainTabFinancial());
        fragmentArrayList.add(new MainTabResource());
        fragmentArrayList.add(new MainTabSetting());
        fragmentManager = getSupportFragmentManager();
    }
    /**
     * 头标点击监听
     * @author weizhi
     * @version 1.0
     */
    public class MyOnClickListener implements View.OnClickListener{
        private int index = 0 ;
        public MyOnClickListener(int i) {
            index = i;
        }
        @Override
        public void onClick(View v) {
            mViewPager.setCurrentItem(index);
        }
    }
    /**
     * 页卡切换监听
     * @author weizhi
     * @version 1.0
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageSelected(int position) {
            Animation animation = null ;
            switch (position){
                //当前为页卡1
                case 0:
                    //从页卡1跳转转到页卡2
                    express.setImageResource(R.drawable.wuliu_up);
                    expresstext.setTextColor(Color.GRAY);
                    attendance.setImageResource(R.drawable.kaoqin_down);
                    attendancetext.setTextColor(Color.RED);
                    financial.setImageResource(R.drawable.caiwu_up);
                    financialtext.setTextColor(Color.GRAY);
                    setting.setImageResource(R.drawable.setting_up);
                    settingtext.setTextColor(Color.GRAY);
                    project.setImageResource(R.drawable.project_up);
                    projectText.setTextColor(Color.GRAY);
                    resource.setImageResource(R.drawable.resource_up);
                    resourceText.setTextColor(Color.GRAY);
                    if(currIndex == 1){
                        animation = new TranslateAnimation(position_one, 0, 0, 0);
                    }else if(currIndex == 2){//从页卡1跳转转到页卡3
                        animation = new TranslateAnimation(position_two, 0, 0, 0);
                    }else if(currIndex == 3){//从页卡1跳转转到页卡3
                        animation = new TranslateAnimation(position_three, 0, 0, 0);
                    }else if(currIndex == 4){//从页卡1跳转转到页卡3
                        animation = new TranslateAnimation(position_four, 0, 0, 0);
                    }else if(currIndex == 5){
                        animation = new TranslateAnimation(position_five, 0, 0, 0);
                    }
                    break;
                //当前为页卡2
                case 1:
                    express.setImageResource(R.drawable.wuliu_down);
                    expresstext.setTextColor(Color.RED);
                    attendance.setImageResource(R.drawable.kaoqin_up);
                    attendancetext.setTextColor(Color.GRAY);
                    financial.setImageResource(R.drawable.caiwu_up);
                    financialtext.setTextColor(Color.GRAY);
                    setting.setImageResource(R.drawable.setting_up);
                    settingtext.setTextColor(Color.GRAY);
                    project.setImageResource(R.drawable.project_up);
                    projectText.setTextColor(Color.GRAY);
                    resource.setImageResource(R.drawable.resource_up);
                    resourceText.setTextColor(Color.GRAY);
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_one, 0, 0);
                    } else if (currIndex == 2) { //从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_two, position_one, 0, 0);

                    }else if (currIndex == 3) { //从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_two, position_one, 0, 0);
                    }else if (currIndex == 4) { //从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_two, position_one, 0, 0);
                    }else if(currIndex == 5){
                        animation = new TranslateAnimation(position_two, position_one, 0, 0);
                    }
                    break;
                //当前为页卡3
                case 2:
                    express.setImageResource(R.drawable.wuliu_up);
                    expresstext.setTextColor(Color.GRAY);
                    attendance.setImageResource(R.drawable.kaoqin_up);
                    attendancetext.setTextColor(Color.GRAY);
                    financial.setImageResource(R.drawable.caiwu_up);
                    financialtext.setTextColor(Color.GRAY);
                    setting.setImageResource(R.drawable.setting_up);
                    settingtext.setTextColor(Color.GRAY);
                    project.setImageResource(R.drawable.project_down);
                    projectText.setTextColor(Color.RED);
                    resource.setImageResource(R.drawable.resource_up);
                    resourceText.setTextColor(Color.GRAY);
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_two, 0, 0);
                    } else if (currIndex == 1) {//从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_one, position_two, 0, 0);
                    }else if (currIndex == 3) {//从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_three, position_two, 0, 0);
                    }else if (currIndex == 4) {//从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_three, position_two, 0, 0);
                    }else if(currIndex == 5){
                        animation = new TranslateAnimation(position_three, position_two, 0, 0);
                    }
                    break;
                //当前为页卡4
                case 3:
                    express.setImageResource(R.drawable.wuliu_up);
                    expresstext.setTextColor(Color.GRAY);
                    attendance.setImageResource(R.drawable.kaoqin_up);
                    attendancetext.setTextColor(Color.GRAY);
                    financial.setImageResource(R.drawable.caiwu_down);
                    financialtext.setTextColor(Color.RED);
                    setting.setImageResource(R.drawable.setting_up);
                    settingtext.setTextColor(Color.GRAY);
                    project.setImageResource(R.drawable.project_up);
                    projectText.setTextColor(Color.GRAY);
                    resource.setImageResource(R.drawable.resource_up);
                    resourceText.setTextColor(Color.GRAY);
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_three, 0, 0);
                    } else if (currIndex == 1) {//从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_two, position_three, 0, 0);
                    }else if (currIndex == 2) {//从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_two, position_three, 0, 0);
                    }else if (currIndex == 4) {//从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_two, position_three, 0, 0);
                    }else if(currIndex == 5){
                        animation = new TranslateAnimation(position_four, position_three, 0, 0);
                }
                    break;

                case 4:
                    express.setImageResource(R.drawable.wuliu_up);
                    expresstext.setTextColor(Color.GRAY);
                    attendance.setImageResource(R.drawable.kaoqin_up);
                    attendancetext.setTextColor(Color.GRAY);
                    financial.setImageResource(R.drawable.caiwu_up);
                    financialtext.setTextColor(Color.GRAY);
                    setting.setImageResource(R.drawable.setting_up);
                    settingtext.setTextColor(Color.GRAY);
                    project.setImageResource(R.drawable.project_up);
                    projectText.setTextColor(Color.GRAY);
                    resource.setImageResource(R.drawable.resource_down);
                    resourceText.setTextColor(Color.RED);
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_four, 0, 0);
                    } else if (currIndex == 1) {//从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_three, position_four, 0, 0);
                    }else if (currIndex == 2) {//从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_three, position_four, 0, 0);
                    }else if (currIndex == 3) {//从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_three, position_four, 0, 0);
                    }else if(currIndex == 5){
                        animation = new TranslateAnimation(position_five, position_four, 0, 0);
                    }
                    break;
                case 5:
                    express.setImageResource(R.drawable.wuliu_up);
                    expresstext.setTextColor(Color.GRAY);
                    attendance.setImageResource(R.drawable.kaoqin_up);
                    attendancetext.setTextColor(Color.GRAY);
                    financial.setImageResource(R.drawable.caiwu_up);
                    financialtext.setTextColor(Color.GRAY);
                    setting.setImageResource(R.drawable.setting_down);
                    settingtext.setTextColor(Color.RED);
                    project.setImageResource(R.drawable.project_up);
                    projectText.setTextColor(Color.GRAY);
                    resource.setImageResource(R.drawable.resource_up);
                    resourceText.setTextColor(Color.GRAY);
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_five, 0, 0);
                    } else if (currIndex == 1) {//从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_four, position_five, 0, 0);
                    }else if (currIndex == 2) {//从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_four, position_five, 0, 0);
                    }else if (currIndex == 3) {//从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_four, position_five, 0, 0);
                    }else if(currIndex == 4){
                        animation = new TranslateAnimation(position_four, position_five, 0, 0);
                    }
                    break;
            }
            currIndex = position;
            animation.setFillAfter(true);// true:图片停在动画结束位置
            animation.setDuration(300);
            cursor.startAnimation(animation);
        }
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
    /**
     * 设置动画图片宽度
     * @param mWidth
     */
    private void setBmpW(ImageView imageView,int mWidth){
        ViewGroup.LayoutParams para;
        para = imageView.getLayoutParams();
        para.width = mWidth;
        imageView.setLayoutParams(para);
    }
    @Override
    public void finish() {
        super.finish();
    }
}
