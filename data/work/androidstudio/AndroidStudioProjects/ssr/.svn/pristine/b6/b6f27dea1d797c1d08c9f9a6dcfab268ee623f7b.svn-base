package ui.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;

import com.example.admin.erp.R;

import java.util.ArrayList;
import java.util.List;

import Tool.statistics.Statics;
import http.ExpressStatisticsHttpPost;

public class ExpressPiecesPersonDetailsChartsFragementActivity extends FragmentActivity {
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
    ExpressPiecePersonDetailsZhuFragment zhuFragment;
    ExpressPiecePersonDetailsZheFragment zheFragement;
    //屏幕宽度
    int screenWidth;
    //当前选中的项
    int currenttab=-1;
    private  ExpressStatisticsHttpPost expressStatisticsHttpPost;
    private  boolean flag=false;
    public static MyFrageStatePagerAdapter adapter;
    String year;
    String month;
    String type;
    String expressPersonId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.express_pieces_charts_fragment);

        mViewPager=(ViewPager) findViewById(R.id.viewpager);
        Intent intent=getIntent();
        year=intent.getStringExtra("year");
        month=intent.getStringExtra("month");
        type=intent.getStringExtra("type");
        expressPersonId=intent.getStringExtra("expressPersonId");

        Statics.XiangxiChan.clear();
        Statics.XiangxiChan.put("year",year);
        Statics.XiangxiChan.put("month",month);
        Statics.XiangxiChan.put("type",type);
        Statics.XiangxiChan.put("expressPersonId",expressPersonId);

        fragmentList=new ArrayList<>();
        zhuFragment=new ExpressPiecePersonDetailsZhuFragment();
        zheFragement=new ExpressPiecePersonDetailsZheFragment();
        fragmentList.add(zhuFragment);
        fragmentList.add(zheFragement);
        screenWidth=getResources().getDisplayMetrics().widthPixels;
        mViewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));

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
        screenWidth=getResources().getDisplayMetrics().widthPixels;
        startPosition=currenttab*(screenWidth/4);
        movetoPosition=moveToTab*(screenWidth/4);
        //平移动画
        TranslateAnimation translateAnimation=new TranslateAnimation(startPosition,movetoPosition, 0, 0);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(200);
    }

}


