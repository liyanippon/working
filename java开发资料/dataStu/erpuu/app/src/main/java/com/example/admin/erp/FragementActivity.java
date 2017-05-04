package com.example.admin.erp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class FragementActivity extends FragmentActivity {
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
    RecordPager1 oneFragment;
    RecordPager2 twoFragment;
    RecordPager3 threeFragment;
    //屏幕宽度
    int screenWidth;
    //当前选中的项
    int currenttab=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragement);

        mViewPager=(ViewPager) findViewById(R.id.viewpager);
        fragmentList=new ArrayList<>();
        oneFragment=new RecordPager1();
        twoFragment=new RecordPager2();
        threeFragment=new RecordPager3();
        fragmentList.add(oneFragment);
        fragmentList.add(twoFragment);
        fragmentList.add(threeFragment);
        screenWidth=getResources().getDisplayMetrics().widthPixels;
        mViewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));
    }


    class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter{
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


