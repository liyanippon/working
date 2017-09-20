package com.example.admin.coinmachine;
import android.app.Activity;
import android.database.SQLException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.util.Log;
import com.example.admin.coinmachine.common.BaseActivity;
import com.example.admin.coinmachine.database.UserDao;
import com.example.admin.coinmachine.model.User;
import com.example.admin.coinmachine.ui.activity.MainActivity;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;
import static org.junit.Assert.assertEquals;
/**
 * Created by admin on 2017/9/20.
 * 测试自动贩卖机
 */
@RunWith(AndroidJUnit4.class)
public class CoinMachineTest extends AndroidTestCase {
    @Test
    public void dataBaseTest(){//测试数据库
        try {
            User ul = new User("天一", "中国人");
            UserDao userDao = new UserDao(getContext());
            userDao.updateUser(ul);
            List<User> list  = userDao.getAllUser();//查询所有姓名
            for (User userList:list){
                Log.d("MainActivity", "输出所有信息：" + userList.getName() + ";;" + userList.getDesc() + ";;" + userList.getId());
            }
            assertEquals("天一",list.get(3).getDesc());
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
