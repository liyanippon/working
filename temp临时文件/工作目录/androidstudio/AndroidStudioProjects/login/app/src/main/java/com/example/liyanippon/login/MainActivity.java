package com.example.liyanippon.login;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView select;
    private PopupWindow pw;
    private int width, i;
    private LinearLayout parent, option;
    private EditText et_user, et_pwd;
    private ListView listView;
    private Button login;
    private CheckBox checkBox;
    public static SharedPreferences sPreferences;
    private Map<String, String> map;
    private TextView del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        et_user = (EditText) findViewById(R.id.et_user);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        parent = (LinearLayout) findViewById(R.id.llayout);

        select = (ImageView) findViewById(R.id.select);
        login = (Button) findViewById(R.id.login);
        checkBox = (CheckBox) findViewById(R.id.checkBox1);
        del = (TextView) findViewById(R.id.del);

        select.setOnClickListener(this);
        login.setOnClickListener(this);

        //读取已经记住的用户名与密码
        sPreferences = getSharedPreferences("session", MODE_PRIVATE);
        map = (Map<String, String>) sPreferences.getAll();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < (map.size()/2); i++) {
            String name = sPreferences.getString("name" + i, "");
            list.add(name);
        }




        // 用4个参数的指定，哪个listview中的textview
        /*        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.login_items, R.id.item, list);*/
        LoginAdapter timeAdapter = new LoginAdapter(MainActivity.this, list);

        option = (LinearLayout) getLayoutInflater().inflate(R.layout.login_option,
                null);
        // 要在这个linearLayout里面找listView......
        listView = (ListView) option.findViewById(R.id.op);
        listView.setAdapter(timeAdapter);



        //获取屏幕的宽度并设置popupwindow的宽度为width,我这里是根据布局控件所占的权重
        /*WindowManager wManager = (WindowManager) getSystemService(this.WINDOW_SERVICE);
        width = wManager.getDefaultDisplay().getWidth() * 4 / 5;*/
        Point size = new Point();
        MainActivity.this.getWindowManager().getDefaultDisplay().getSize(size);
        int width = size.x;
        int height = size.y;


        //实例化一个popupwindow对象
        pw = new PopupWindow(option, width/2+80, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        ColorDrawable dw = new ColorDrawable(00000);
        pw.setBackgroundDrawable(dw);
        pw.setOutsideTouchable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {

                // 获取选中项内容及从sharePreferences中获取对应的密码
                String username = adapterView.getItemAtPosition(position)
                        .toString();
                String pwd = sPreferences.getString("pwd" + position, "");

                et_user.setText(username);
                et_pwd.setText(pwd);

                // 选择后，popupwindow自动消失
                pw.dismiss();
            }

        });

    }

    private void login() {

        // 若是第二次打开软件时，将map在size赋给i,若是不是,则i只要实现i++
        if (i == 0) {
            i = map.size()/2;
        }

        // 若有勾选记住，则保存
        if (checkBox.isChecked()) {
            String name = et_user.getText().toString().trim();
            String pwd = et_pwd.getText().toString().trim();
            if (!"".equals(name) && !"".equals(pwd)) {

                sPreferences.edit().putString("name" + i, name)
                        .putString("pwd" + i, pwd).commit();
                i++;

                Toast.makeText(this,"用户名与密码已经记住，下次进入软件时将生效！",0).show();

            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select:
                //为什么x正方向偏移不了？？？当取负时就可以，有人可以解释下吗
                pw.showAsDropDown(parent, 15, -4);
                break;
            case R.id.login:
                login();
                break;
        }
    }
}
