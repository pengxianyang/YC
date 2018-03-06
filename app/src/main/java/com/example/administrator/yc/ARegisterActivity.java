package com.example.administrator.yc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2018/3/5.
 */

public class ARegisterActivity extends AppCompatActivity{
    public EditText editText_account;
    public EditText editText_password;
    public EditText editText_confirm_password;
    public EditText editText_email;
    public EditText editText_phone;
    public EditText editText_name;
    public Button button_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_a_register);

        init();
    }

    public void init()
    {
        editText_account = (EditText)findViewById(R.id.editText_account);
        editText_password = (EditText)findViewById(R.id.editText_password);
        editText_confirm_password = (EditText)findViewById(R.id.editText_account);
        editText_email = (EditText)findViewById(R.id.editText_email);
        editText_phone = (EditText)findViewById(R.id.editText_phone);
        editText_name = (EditText)findViewById(R.id.editText_name);
        button_confirm = (Button)findViewById(R.id.button_confirm);
    }

    public void set_button()
    {
        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ARegisterActivity.this,LoginActivity.class);
            }
        });
    }

}
