package com.example.administrator.yc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2018/3/7.
 */

public class RegisterTypeActivity extends AppCompatActivity{

    public Button button_player_register;
    public Button button_administrator_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        getSupportActionBar().hide();

        set_button();
    }

    public void set_button()
    {
        button_administrator_register = (Button)findViewById(R.id.button_administrator_register);
        button_player_register = (Button)findViewById(R.id.button_player_register);

        button_player_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(RegisterTypeActivity.this,PRegisterActivity.class);
                startActivity(intent1);
            }
        });

        button_administrator_register.setOnClickListener(new View.OnClickListener() {//暂时两个都是一样的
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(RegisterTypeActivity.this,PRegisterActivity.class);
                startActivity(intent1);
            }
        });
    }
}
