package http;
import android.util.Log;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import ui.activity.AttendanceStatisticsActivity;
import ui.activity.FinancialBillingManagementActivity;
import ui.activity.FinancialStastisticsActivity;

/**
 * Created by admin on 2017/3/27.
 * 新的网络连接放式代替以前的连接
 */

public class HttpBasePost {

        public static FinalHttp finalHttp;
        public static AjaxParams params = new AjaxParams();
        public static String resultString = "error";

        public static String postHttp(String httpUrl, HashMap<String,String> param, final String httpType) {

                finalHttp = new FinalHttp();
                params = new AjaxParams();
                if(param!=null){
                        Set set=param.entrySet();
                        Iterator it=set.iterator();
                        while(it.hasNext()){
                                Map.Entry me=(Map.Entry)it.next();
                                Log.d("HttpBase", "hashMap" + me.getKey() + ":" + me.getValue() + ":" + me.hashCode());
                                params.put(me.getKey()+"", me.getValue()+"");
                        }
                }

                Log.d("HttpBasePost", "http:" + httpUrl);
                params.put("httpUrl", httpUrl);
                finalHttp.post(httpUrl, params, new AjaxCallBack<Object>() {

                        @Override
                        public void onSuccess(Object o) {//网络请求网络请求成功
                                super.onSuccess(o);

                                String result = (String) o;//从从网络端返回数据
                                resultString = "success";
                                Log.d("HttpBasePost", "结果" + result);
                                if(httpType.equals("100106")||httpType.equals("100108")//物流模块
                                        ||httpType.equals("100109")||httpType.equals("100110")
                                        ||httpType.equals("100204")||httpType.equals("100205")){
                                        HttpTypeUtil.expressType(result,httpType);
                                }else if(httpType.equals("100400")||httpType.equals("100401")
                                        ||httpType.equals("100402")||httpType.equals("100403")
                                        ||httpType.equals("100404")){//考勤模块
                                        HttpTypeUtil.attendanceType(result,httpType);
                                }else if(httpType.equals("100500")||httpType.equals("100501")//财务模块
                                        ||httpType.equals("100502")||httpType.equals("100503")||httpType.equals("100504")
                                        ||httpType.equals("100506")||httpType.equals("100507")||httpType.equals("100508")){
                                        resultString = HttpTypeUtil.financialType(result,httpType);
                                }

                        }

                        @Override
                        public void onFailure(Throwable t, int errorNo, String strMsg) {//网络请求失败
                                super.onFailure(t, errorNo, strMsg);

                                switch (httpType){
                                        case "100400":
                                                AttendanceStatisticsActivity.progressDialog.dismiss();
                                                break;
                                        case "100402":
                                                if(AttendanceStatisticsActivity.progressDialog!=null) {
                                                        AttendanceStatisticsActivity.progressDialog.dismiss();
                                                }
                                                break;
                                        case "100501":
                                                FinancialBillingManagementActivity.progressDialog.dismiss();
                                                break;
                                        case "100506":
                                                FinancialStastisticsActivity.progressDialog.dismiss();
                                                break;
                                }

                        }
                });

                return "success";
        }

        /*public static void postHttp(String httpUrl, HashMap<String,String> param, final String httpType){ //okhttp不支持直接刷新UI  client = new OkHttpClient();
                FormEncodingBuilder  builder  = new FormEncodingBuilder();

                Set set=param.entrySet();
                Iterator it=set.iterator();
                while(it.hasNext()){
                        Map.Entry me=(Map.Entry)it.next();
                        Log.d("HttpBase", "hashMap" + me.getKey() + ":" + me.getValue() + ":" + me.hashCode());
                        builder.add(me.getKey()+"", me.getValue()+"");
                }
                builder.build();
                Request requestPost = new Request.Builder()
                        .url(httpUrl)
                        .post(builder.build())
                        .build();
                client.newCall(requestPost).enqueue(new Callback() {

                        @Override
                        public void onResponse(Response response) throws IOException {
                                String result = response.body().string();
                                Log.d("HttpBasePost", "result:" + result);
                                HttpTypeUtil.attendanceType(result,httpType);
                        }
                        @Override
                        public void onFailure(Request request, IOException e) {

                                switch (httpType){
                                        case "10021":
                                                AttendanceStatisticsActivity.progressDialog.dismiss();
                                                ExceptionUtil.httpPost("AttendanceStatisticsHttpPost");
                                                break;
                                }

                        }

                });
        }*/


}
