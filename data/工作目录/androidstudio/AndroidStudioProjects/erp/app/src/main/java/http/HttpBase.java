package http;

import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by admin on 2017/3/27.
 */

public class HttpBase {
        private static OkHttpClient client;

        public static void postHttp(HashMap<String,String> param){

                client = new OkHttpClient();
                FormEncodingBuilder  builder  = new FormEncodingBuilder();
                builder.add("option", "1");
                builder.add("type", "news");
                builder.add("classify", "20");
                builder.add("reason", "0");
                builder.add("page", "1");
                builder.add("rows", "10");
                builder.build();
                Request requestPost = new Request.Builder()
                        .url("http://erp.yifeng-dl.com/wxApi.ajax")
                        .post(builder.build())
                        .build();
                client.newCall(requestPost).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                                final String string = response.body().string();
                                Log.d("tets","uio:"+string);
                        }

                });
        }


}
