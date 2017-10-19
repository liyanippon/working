package com.example.admin.erp;

/**
 * Created by admin on 2017/9/29.
 */

public interface LoginView {
    void showProgress();//显示进度条
    void hideProgress();//隐藏进度条
    void toastMessage(String Message);//信息提示
    void editorEnabled();
    void isClickable(boolean clickable);
}
