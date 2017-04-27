package ui.fragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;

import com.example.admin.erp.R;

import java.util.ArrayList;
import java.util.List;

public class AttendanceChartsFragmentActivity extends FragmentActivity {
    /**
     * 作为页面容器的ViewPager
     */
    ViewPager mViewPager;
    /**
     * 页面集合
     */
    List<Fragment> fragmentList;

    /**
     * 四个Fragment（页面）
     */
    AttendanceZhuFragment zhuFragment;
    //AttendanceZheFragment zheFragement;
    //屏幕宽度
    int screenWidth;
    //当前选中的项
    int currenttab=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_charts_fragment);

        Log.d("test","sfadff:");
        mViewPager=(ViewPager) findViewById(R.id.viewpager);
        fragmentList=new ArrayList<>();
        zhuFragment=new AttendanceZhuFragment();
        //zheFragement=new AttendanceZheFragment();
        fragmentList.add(zhuFragment);
        //fragmentList.add(zheFragement);
        screenWidth=getResources().getDisplayMetrics().widthPixels;
        mViewPager.setAdapter(new AttendanceChartsFragmentActivity.MyFrageStatePagerAdapter(getSupportFragmentManager()));
    }

    class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter {
        public MyFrageStatePagerAdapter(FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        /**
         * 每次更新完成ViewPager的内容后，调用该接口，此处复写主要是为了让导航按钮上层的覆盖层能够动态的移动
         */
        @Override
        public void finishUpdate(ViewGroup container){
            super.finishUpdate(container);//这句话要放在最前面，否则会报错
            //获取当前的视图是位于ViewGroup的第几个位置，用来更新对应的覆盖层所在的位置
            int currentItem=mViewPager.getCurrentItem();
            if (currentItem==currenttab)
            {
                return ;
            }
            imageMove(mViewPager.getCurrentItem());
            currenttab=mViewPager.getCurrentItem();
        }

    }

    /**
     * 移动覆盖层
     * @param moveToTab 目标Tab，也就是要移动到的导航选项按钮的位置
     * 第一个导航按钮对应0，第二个对应1，以此类推
     */
    private void imageMove(int moveToTab){
        int startPosition=0;
        int movetoPosition=0;

        startPosition=currenttab*(screenWidth/4);
        movetoPosition=moveToTab*(screenWidth/4);
        //平移动画
        TranslateAnimation translateAnimation=new TranslateAnimation(startPosition,movetoPosition, 0, 0);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(200);
    }
}
