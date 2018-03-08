package com.example.administrator.yc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/3/8.
 */

public class SendMassageActivity extends AppCompatActivity {

    public EditText editText_message_content;//新消息内容
    public TextView textView_sender;//发送人名字 格式为: 发送者: XXXX
    public TextView textView_receiver;//接受人名字 格式为: 接收者: XXXX
    public Button button_send_message;//发送信息按钮


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        editText_message_content = (EditText)findViewById(R.id.editText_message_content);
        textView_sender = (TextView)findViewById(R.id.textView_sender);
        textView_receiver = (TextView)findViewById(R.id.textView_receiver);
        button_send_message = (Button)findViewById(R.id.button_send_message);

        set_Button();

    }

    public void set_Button()
    {
        button_send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置发送信息动作
            }
        });
    }
}
