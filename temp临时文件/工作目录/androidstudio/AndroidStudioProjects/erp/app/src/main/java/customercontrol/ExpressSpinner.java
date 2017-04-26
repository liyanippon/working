package customercontrol;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.admin.erp.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import http.Constants;

/**
 * Created by admin on 2017/3/24.
 */

public class ExpressSpinner extends EditText{
    private List<Integer> list=new ArrayList<>();
    public ExpressSpinner(Context context) {
        super(context);

    }
    public ExpressSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);

    }
    @Override
    public void setText(CharSequence text, BufferType type) {
        //super.setText(text+"    ▼", type);
        super.setText(text, type);
    }
    public void initContent(final String[] m) {
        setText(m[0], null);
        OnClickListener listener=new OnClickListener() {
            @Override
            public void onClick(View v) {
                final SpinnerDialog dialog=new SpinnerDialog(getContext());
                dialog.setTitle("快递选择");
                TextView textView=(TextView) dialog.findViewById(R.id.over_text);
                textView.setText("确定");
                textView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(list.size()==0){
                            setText(m[0], null);
                        }else{
                            StringBuffer buffer=new StringBuffer();
                            int size=list.size();
                            //對list進行排序
                            Collections.sort(list);
                            for (int i = 0; i < size; i++) {
                                buffer.append(m[list.get(i)]+',');
                            }
                            setText(buffer.toString().substring(0, buffer.length()-1),null);
                            for (int i=0;i<buffer.toString().substring(0, buffer.length()-1).length();i++){
                                Constants.expressType = buffer.toString().substring(0, buffer.length()-1).split(",");

                            }
                            for (int j=0;j<Constants.expressType.length;j++){
                                Log.d("test", "::"+Constants.expressType[j]);
                            }

                            //點擊確定要看listView的選擇狀態


                        }
                        dialog.cancel();
                        list.clear();
                    }
                });

                dialog.show();
                final ListView listView=(ListView) dialog.findViewById(R.id.listview);

                ArrayAdapter<String> adapter = new ArrayAdapter<>
                        (getContext(),android.R.layout.simple_list_item_multiple_choice,m);

                listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

                int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
                int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
                textView.measure(w, h);
                int height =textView.getMeasuredHeight();
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0, 0, 0, height);
                listView.setLayoutParams(lp);
                listView.setAdapter(adapter);

                //复选回显
                if (Constants.expressType!=null){
                    for (int i=0;i<Constants.expressType.length;i++){

                        Log.d("uuui","::"+Constants.expressType[i]);
                        listView.setItemChecked(i,true);
                    }
                }


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int arg2, long arg3) {
                        Integer object=Integer.valueOf(arg2);
                        if(list.contains(object)){
                            list.remove(object);
                        }else{
                            list.add(object);
                        }
                    }
                });
                dialog.show();
            }
        };
        super.setOnClickListener(listener);
    }
    private class SpinnerDialog extends Dialog {
        public SpinnerDialog(Context context) {
            super(context);
            //requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.express_add_customer_spinner);
            Window window = getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.gravity = Gravity.CENTER;
            DisplayMetrics dm = new DisplayMetrics();
            getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
            int screenWidth = dm.widthPixels;
            int screenHeigh = dm.heightPixels;
            params.height=(int) (screenHeigh*0.5);
            params.width=(int) (screenWidth*0.8);
            window.setAttributes(params);
        }
    }
}
