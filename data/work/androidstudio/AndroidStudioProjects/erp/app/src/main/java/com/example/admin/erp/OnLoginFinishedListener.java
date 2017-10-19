package com.example.admin.erp;

/**
 * Created by admin on 2017/9/29.
 */

public interface OnLoginFinishedListener {
    void editorEnabled();
    void toastMessage(String Message);
    void showProgress();
    void hideProgress();
    void isClickable(boolean clickable);
}
