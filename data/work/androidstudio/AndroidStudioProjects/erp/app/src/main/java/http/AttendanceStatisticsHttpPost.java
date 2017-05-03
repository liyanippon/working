package http;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Tool.statistics.Statics;
import model.AttendanceStatistics;
import model.AttendanceYear;
import ui.activity.AttendanceStatisticsActivity;

/**
 * Created by admin on 2017/4/13.
 */

public class AttendanceStatisticsHttpPost {
    public static FinalHttp finalHttp;
    public static AjaxParams params = new AjaxParams();
    public static String resultString = "error";

    public String searchStatisticsHttp(String httpUrl, String userId, String year, String month, final Activity activity) {//考勤统计查询
        Log.d("test-i","httpUrl:"+httpUrl);
        Log.d("test-i",userId+"@"+year+"@"+month);
        if("全部".equals(userId)){
            userId = "";
        }
        if("全部".equals(month)){
            month = "";
        }
        if("全部".equals(year)){
            year = "";
        }
        if("全部".equals(month)){
            month = "";
        }
        Log.d("month","mon"+month);
        Log.d("month","month:"+month);
        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("httpUrl", httpUrl);
        params.put("userId", userId);
        params.put("year", year);
        params.put("month", month);
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);

                String result = (String) o;//从从网络端返回数据
                Log.d("attendance", result);
                resultString = "success";

                //json数据使用Gson框架解析
                Statics.attendanceStatisticsList.clear();
                AttendanceStatistics[] as = new Gson().fromJson(result, AttendanceStatistics[].class);
                Collections.addAll(Statics.attendanceStatisticsList,as);//转化arrayList
                //刷新异步刷新
                AttendanceStatisticsActivity attendanceStatisticsActivity = new AttendanceStatisticsActivity();
                attendanceStatisticsActivity.AdapterRefresh("attendAdapter");
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);

                AttendanceStatisticsActivity.progressDialog.dismiss();
                resultString = "error";
            }
        });

        return resultString;
    }

    public String searchStaffNameHttp(String httpUrl, final Activity activity) {//员工姓名查询

        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params = new AjaxParams();
        params.put("httpUrl", httpUrl);
        params.put("userId", "");
        params.put("year", "");
        params.put("month", "");
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);

                String result = (String) o;//从从网络端返回数据
                Log.d("test1", result);
                resultString = "success";
                //json数据使用Gson框架解析
                Statics.searchName.clear();
                AttendanceStatistics[] as = new Gson().fromJson(result, AttendanceStatistics[].class);
                //取出所有姓名

                ArrayList<String> searchName = new ArrayList<>();
                ArrayList<String> searchNameId = new ArrayList<>();
                for (int i=0;i<as.length;i++){
                    searchName.add(as[i].getUserName());
                    searchNameId.add(as[i].getUserId());
                }
                //去除重复数据，不要用set，set顺序会被打乱
                for  ( int  i  =   0 ; i  <  searchName.size()  -   1 ; i ++ )   {
                    for  ( int  j  =  searchName.size()  -   1 ; j  >  i; j -- )   {
                        if  (searchName.get(j).equals(searchName.get(i)))   {
                            searchName.remove(j);
                        }
                    }
                }
                for  ( int  i  =   0 ; i  <  searchNameId.size()  -   1 ; i ++ )   {
                    for  ( int  j  =  searchNameId.size()  -   1 ; j  >  i; j -- )   {
                        if  (searchNameId.get(j).equals(searchNameId.get(i)))   {
                            searchNameId.remove(j);
                        }
                    }
                }
                Statics.searchName=searchName;
                Statics.searchNameId=searchNameId;
                for (int i=0;i<Statics.searchName.size();i++){
                    Log.d("hihi",Statics.searchName.get(i));
                    Log.d("hihi",Statics.searchNameId.get(i));
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);
                if(AttendanceStatisticsActivity.progressDialog!=null) {
                    AttendanceStatisticsActivity.progressDialog.dismiss();
                }
                resultString = "error";
            }
        });

        return resultString;
    }

    public String searchTimeYearHttp(String httpUrl, final Activity activity) {//年份查询

        finalHttp = new FinalHttp();
        params = new AjaxParams();
        params.put("httpUrl", httpUrl);
        finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {//网络请求网络请求成功
                super.onSuccess(o);

                String result = (String) o;//从从网络端返回数据
                resultString = "success";
                Statics.searchYear.clear();
                AttendanceYear[] as = new Gson().fromJson(result, AttendanceYear[].class);
                Collections.addAll(Statics.searchYear,as);//转化arrayList

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                super.onFailure(t, errorNo, strMsg);



                resultString = "error";
            }
        });

        return resultString;
    }

}
