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
import android.widget.Toast;

import com.example.administrator.yc.model.ResultEntity;
import com.example.administrator.yc.retro_interface.Retro;

import rx.Subscriber;

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
                Login("1");
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
    void Login(String func){//0-管理员登录，1-用户登录
        Retro retro=new Retro();
        final String username=editText_account.getText().toString();
        final String password=editText_password.getText().toString();
        if(username.equals("")||password.equals(""))
            Toast.makeText(LoginActivity.this,"用户和密码不能为空",Toast.LENGTH_LONG).show();
        else
            retro.Login(username,password,func)
                    .subscribe(new Subscriber<ResultEntity>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(LoginActivity.this,"连接失败",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNext(ResultEntity resultEntity) {
                            if(resultEntity.getCode()==0)
                                Toast.makeText(LoginActivity.this,"用户或密码错误",Toast.LENGTH_LONG).show();
                            else if(resultEntity.getCode()==-1)
                                Toast.makeText(LoginActivity.this,"连接失败",Toast.LENGTH_LONG).show();
                            else if(resultEntity.getCode()==1){  //管理员登录
                                GlobalData.username=username;
                                GlobalData.password=password;
                                Intent intent1 = new Intent(LoginActivity.this,MainActivity2.class);
                                startActivity(intent1);
                            }
                            else if(resultEntity.getCode()==2){ //运动员登录
                                Intent intent1 = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent1);
                            }
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
