package com.example.administrator.yc;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/3/5.
 */

public class LoginActivity extends AppCompatActivity {

    public EditText editText_account;
    public EditText editText_password;
    public Button button_login;
    public Button button_register;
    public TextView textView_intro1;
    public TextView textView_intro2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        init();
        set_button();
        set_font();
    }
    public void init()
    {
        editText_account = (EditText)findViewById(R.id.editText_account);
        editText_password = (EditText)findViewById(R.id.editText_password);
        button_login = (Button)findViewById(R.id.button_login);
        button_register = (Button)findViewById(R.id.button_register);

    }

    public void set_button()
    {

        //Intent intent2 = new Intent(LoginActivity.this,MainActivity.class);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterTypeActivity.class);
                startActivity(intent);
            }
        });
    }

    public void set_font()
    {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/Ga.otf");
        textView_intro1 = (TextView)findViewById(R.id.textView_intro);
        textView_intro2 = (TextView)findViewById(R.id.textView_intro2);
        textView_intro1.setTypeface(typeface);
        textView_intro2.setTypeface(typeface);
    }


}
