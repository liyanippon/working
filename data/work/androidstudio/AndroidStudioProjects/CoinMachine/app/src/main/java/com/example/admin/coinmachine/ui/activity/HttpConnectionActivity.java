package com.example.admin.coinmachine.ui.activity;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.admin.coinmachine.http.HttpBase;
import okhttp3.FormBody;
public class HttpConnectionActivity extends AppCompatActivity {
    FormBody.Builder builder = new FormBody.Builder();
    private static TextView createName;
    public static Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_connection);

        activity = HttpConnectionActivity.this;
        builder.add("option", "1");
        builder.add("type", "");
        builder.add("classify", "");
        builder.add("reason", "");
        builder.add("page", "1");
        builder.add("rows", "50");
        HttpBase.postHttp(builder);
    }
    public static Handler handler = new Handler() {//更新网络来的数据

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    /*createName.setText(Statics.expressManagementList.get(0)
                            .getData().get(0).getRows().get(0).getCreateBy());*/
                    break;
            }
        }
    };
}
