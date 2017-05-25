package ui.activity.menu;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.admin.erp.R;
import java.util.ArrayList;

import Tool.crash.BaseActivity;
import Tool.crash.CrashHandler;
import Tool.statistics.Statics;
import ui.adpter.MFragmentPagerAdapter;
import ui.fragement.menu.MainTab04;
import ui.fragement.menu.MainTabAttendance;
import ui.fragement.menu.MainTabExpress;
import ui.fragement.menu.MainTabFinancial;

public class MenuFragmentMainActivity extends BaseActivity{
    private LinearLayout attendanceTextView;
    private LinearLayout expressTextView;
    private LinearLayout financialTextView;
    private LinearLayout setting_layout;
    //实现Tab滑动效果
    private ViewPager mViewPager;
    //动画图片
    private ImageView cursor;
    //动画图片偏移量
    private int offset = 0;
    private int position_one;
    private int position_two;
    private int position_three;
    //动画图片宽度
    private int bmpW;
    //当前页卡编号
    private int currIndex = 0;
    //存放Fragment
    private ArrayList<Fragment> fragmentArrayList;
    //管理Fragment
    private android.support.v4.app.FragmentManager fragmentManager;
    public Context context;
    private ImageButton express,attendance,setting,financial;
    private TextView expresstext,attendancetext,settingtext,financialtext;

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

        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onResume() {
        /**
         * 设置为竖屏
         */
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
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
        //添加点击事件
        attendanceTextView.setOnClickListener(new MyOnClickListener(0));
        expressTextView.setOnClickListener(new MyOnClickListener(1));
        financialTextView.setOnClickListener(new MyOnClickListener(2));
        setting_layout.setOnClickListener(new MyOnClickListener(3));
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
        //将顶部文字恢复默认值
        resetTextViewTextColor();
        //pictureTextView.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
        //设置viewpager页面滑动监听事件
        mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        express= (ImageButton) findViewById(R.id.express);
        attendance = (ImageButton) findViewById(R.id.kaoqin);
        financial = (ImageButton) findViewById(R.id.caiwu);
        setting = (ImageButton) findViewById(R.id.setting);
    }

    /**
     * 初始化动画
     */
    private void InitImageView() {
        cursor = (ImageView) findViewById(R.id.cursor);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 获取分辨率宽度
        int screenW = dm.widthPixels;
        bmpW = (screenW/4);
        //设置动画图片宽度
        setBmpW(cursor, bmpW);
        offset = 0;
        //动画图片偏移量赋值
        position_one = (int) (screenW / 4.0);
        position_two = position_one * 2;
        position_three = position_one * 3;
    }
    /**
     * 初始化Fragment，并添加到ArrayList中
     */
    private void InitFragment(){
        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new MainTabAttendance());
        fragmentArrayList.add(new MainTabExpress());
        fragmentArrayList.add(new MainTabFinancial());
        fragmentArrayList.add(new MainTab04());
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
                    if(currIndex == 1){
                        animation = new TranslateAnimation(position_one, 0, 0, 0);
                        resetTextViewTextColor();
                    }else if(currIndex == 2){//从页卡1跳转转到页卡3
                        animation = new TranslateAnimation(position_two, 0, 0, 0);
                        resetTextViewTextColor();
                    }else if(currIndex == 3){//从页卡1跳转转到页卡3
                        animation = new TranslateAnimation(position_three, 0, 0, 0);
                        resetTextViewTextColor();
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
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_one, 0, 0);
                        resetTextViewTextColor();
                    } else if (currIndex == 2) { //从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_two, position_one, 0, 0);
                        resetTextViewTextColor();

                    }else if (currIndex == 3) { //从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_two, position_one, 0, 0);
                        resetTextViewTextColor();
                    }
                    break;
                //当前为页卡3
                case 2:
                    express.setImageResource(R.drawable.wuliu_up);
                    expresstext.setTextColor(Color.GRAY);
                    attendance.setImageResource(R.drawable.kaoqin_up);
                    attendancetext.setTextColor(Color.GRAY);
                    financial.setImageResource(R.drawable.caiwu_down);
                    financialtext.setTextColor(Color.RED);
                    setting.setImageResource(R.drawable.setting_up);
                    settingtext.setTextColor(Color.GRAY);
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_two, 0, 0);
                        resetTextViewTextColor();
                    } else if (currIndex == 1) {//从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_one, position_two, 0, 0);
                        resetTextViewTextColor();
                    }else if (currIndex == 3) {//从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_three, position_two, 0, 0);
                        resetTextViewTextColor();
                    }
                    break;
                //当前为页卡4
                case 3:
                    express.setImageResource(R.drawable.wuliu_up);
                    expresstext.setTextColor(Color.GRAY);
                    attendance.setImageResource(R.drawable.kaoqin_up);
                    attendancetext.setTextColor(Color.GRAY);
                    financial.setImageResource(R.drawable.caiwu_up);
                    financialtext.setTextColor(Color.GRAY);
                    setting.setImageResource(R.drawable.setting_down);
                    settingtext.setTextColor(Color.RED);
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_three, 0, 0);
                        resetTextViewTextColor();
                    } else if (currIndex == 1) {//从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_two, position_three, 0, 0);
                        resetTextViewTextColor();
                    }else if (currIndex == 2) {//从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_two, position_three, 0, 0);
                        resetTextViewTextColor();
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
    };
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
    /**
     * 将顶部文字恢复默认值
     */
    private void resetTextViewTextColor(){
        //pictureTextView.setTextColor(getResources().getColor(R.color.main_top_tab_color));
        //movieTextView.setTextColor(getResources().getColor(R.color.main_top_tab_color));
        //musicTextView.setTextColor(getResources().getColor(R.color.main_top_tab_color));
    }
}
