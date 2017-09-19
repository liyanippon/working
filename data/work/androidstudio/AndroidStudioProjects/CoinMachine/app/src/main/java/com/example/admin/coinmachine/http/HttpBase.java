package com.example.admin.coinmachine.http;

import android.os.Message;
import android.util.Log;
import com.example.admin.coinmachine.model.ExpressManagement;
import com.example.admin.coinmachine.ui.activity.HttpConnectionActivity;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Created by admin on 2017/3/27.
 */
public class HttpBase {
        private static String rows = "50";//每页显示条数
        public static ArrayList<ExpressManagement> postHttp(FormBody.Builder builder){
                /* 添加两个参数 */
                FormBody body = builder.build();
                Request request = new Request.Builder()
                        .url("http://erp.yifeng-dl.com/wxApi.ajax")
                        .post(body)
                        .build();
                /* 下边和get一样了 */
                File sdcache = HttpConnectionActivity.activity.getExternalCacheDir();
                int cacheSize = 10 * 1024 * 1024;
                OkHttpClient.Builder ocBuilder = new OkHttpClient.Builder()
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
                OkHttpClient mOkHttpClient=ocBuilder.build();

                mOkHttpClient.newCall(request).enqueue(new Callback() {
                        public void onResponse(Call call, Response response) throws IOException {
                                final  String bodyStr = response.body().string();
                                final boolean ok = response.isSuccessful();
                                Log.d("HttpBase", "网络返回数据:"+bodyStr);
                                JSONArray jsonArray = null;
                                try {
                                        jsonArray = new JSONArray(bodyStr);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                                        JSONObject jsonObject1 = jsonArray1.getJSONObject(0);
                                        String total = jsonObject1.get("total").toString();
                                        //Statics.page = (Integer.parseInt(total) + Integer.parseInt(rows) - 1) / Integer.parseInt(rows);
                                } catch (JSONException e) {
                                        e.printStackTrace();
                                }
                                //Statics.expressManagementList.clear();
                                ExpressManagement[] fc = new Gson().fromJson(bodyStr, ExpressManagement[].class);
                                //Collections.addAll(Statics.expressManagementList,fc);//转化arrayList
                                Message message = new Message();//发送一个消息，该消息用于在handleMessage中区分是谁发过来的消息；
                                message.what = 1;
                                HttpConnectionActivity.handler.sendMessage(message);
                        }
                        public void onFailure(Call call, final IOException e) {
                                Log.d("response", e.toString());
                        }
                });
                //return Statics.expressManagementList;
                return null;
        }

        public static String AsynPostHttp(String urls,String params){
                try {
                        URL url = new URL(urls);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setConnectTimeout(5000);
                        connection.setRequestMethod("POST");

                        //数据准备

                        //String data = "option="+"1"+"&type="+""+"&classify="+""+"&reason="+""+"&page="+"1"+"&rows="+"50";
                        String data = params;
                        //至少要设置的两个请求头
                        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                        connection.setRequestProperty("Content-Length", data.length()+"");

                        //post的方式提交实际上是留的方式提交给服务器
                        connection.setDoOutput(true);
                        OutputStream outputStream = connection.getOutputStream();
                        outputStream.write(data.getBytes());

                        //获得结果码
                        int responseCode = connection.getResponseCode();
                        if(responseCode ==200){
                                //请求成功
                                InputStream is = connection.getInputStream();

                                StringBuffer out = new StringBuffer();
                                InputStreamReader inread = new InputStreamReader(is, "UTF-8");
                                char[] b = new char[4096];
                                for (int n; (n = inread.read(b)) != -1;) {
                                        out.append(new String(b, 0, n));
                                }
                                return out.toString();
                        }else {
                                //请求失败
                                return null;
                        }
                } catch (MalformedURLException e) {
                        e.printStackTrace();
                } catch (ProtocolException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return null;
        }
}
