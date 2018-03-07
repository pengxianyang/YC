package com.example.administrator.yc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/3/6.
 */

public class PersonalActivity extends AppCompatActivity {
    public CircleImageView circleImageView_personal_icon;
    public TextView textView_name;
    public TextView textView_account;
    public TextView textView_email;
    public TextView textView_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();
    }

    public void init()
    {
        textView_account = (TextView)findViewById(R.id.textView_account);
        textView_name = (TextView)findViewById(R.id.textView_name);
        textView_email = (TextView)findViewById(R.id.textView_email);
        textView_phone = (TextView)findViewById(R.id.textView_phone);
        circleImageView_personal_icon = findViewById(R.id.circleimageview_personal_icon);
    }
}
