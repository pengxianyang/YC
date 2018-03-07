package com.example.administrator.yc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by Administrator on 2018/3/6.
 */

public class NewMatchActivity extends AppCompatActivity {

    private Button button_toGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_newmatch);
        init();
        set_Button();
    }

    public void init()
    {
        button_toGPS = (Button)findViewById(R.id.button_toGPS);
    }

    public void set_Button()
    {
        button_toGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置跳转到GPS页面
                Intent intent=new Intent(NewMatchActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
    }

}
