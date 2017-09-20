package com.example.admin.coinmachine.database;
import android.content.Context;
import android.database.SQLException;
import com.example.admin.coinmachine.model.User;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by admin on 2017/9/19.
 */
public class UserDao {
    private Context context;
    public UserDao(Context context) {
        this.context = context;
    }
    public void add(User user){
        try{
            DatabaseHelper.getHelper(context).getUserDao().create(user);
        } catch (SQLException e){
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    public List<User> getAllUser(){
        List<User> userList = new ArrayList<>();
        try {
            userList = DatabaseHelper.getHelper(context).getUserDao().queryForAll();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    public void deleteUserId(int id){
        DatabaseHelper helper = DatabaseHelper.getHelper(context);
        try {
            helper.getUserDao().deleteById(id);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteUser(User user){
        DatabaseHelper helper = DatabaseHelper.getHelper(context);
        try{
            helper.getUserDao().delete(user);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateUser(User user){
        DatabaseHelper helper = DatabaseHelper.getHelper(context);
        try{
            user.setId(3);
            try {
                helper.getUserDao().update(user);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
