package com.example.admin.coinmachine.ui.activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.admin.coinmachine.common.BaseActivity;
import com.example.admin.coinmachine.database.UserDao;
import com.example.admin.coinmachine.http.StatisticUrl;
import com.example.admin.coinmachine.model.User;
import java.util.List;
public class MainActivity extends BaseActivity {
    private TextView iop;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        database();//数据库测试
        imageView();//图片缓存加载测试
    }

    private void imageView() {
        Glide.with(getApplicationContext())
                .load(StatisticUrl.viewUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//既缓存全尺寸又缓存其他尺寸,加载显示非常快
                .placeholder(R.drawable.ic_launcher_round)//加载占位图
                .error(R.drawable.ic_launcher_round)//错误加载
                .into(imageView);
    }

    private void database() {
        //testAddUser();//添加后查询
        testUpdateUser();//更新
    }

    @Override
    public void initView() {
        super.initView();
        imageView = (ImageView) findViewById(R.id.image);
    }
    public void testAddUser(){
        User ul = new User("zhy", "2B青年");
        UserDao userDao = new UserDao(getApplicationContext());
        //userDao.add(ul);//添加
        /*UserDao userDao = new UserDao(getApplicationContext());
        userDao.deleteUserId(1);*/
        userDao.deleteUser(ul);
        List<User> list  = userDao.getAllUser();//查询所有姓名
        for (User userList:list){
            Log.d("MainActivity", "输出所有信息：" + userList.getName() + ";;" + userList.getDesc() + ";;" + userList.getId());
        }
    }

    public void testUpdateUser(){
        User ul = new User("天一", "中国人");
        UserDao userDao = new UserDao(getApplicationContext());
        userDao.updateUser(ul);
        List<User> list  = userDao.getAllUser();//查询所有姓名
        for (User userList:list){
            Log.d("MainActivity", "输出所有信息：" + userList.getName() + ";;" + userList.getDesc() + ";;" + userList.getId());
        }
    }
}
