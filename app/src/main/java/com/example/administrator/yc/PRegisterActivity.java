package com.example.administrator.yc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.yc.model.ResultEntity;
import com.example.administrator.yc.retro_interface.Retro;

import rx.Subscriber;

/**
 * Created by Administrator on 2018/3/5.
 */

public class PRegisterActivity extends AppCompatActivity{

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
        setContentView(R.layout.activity_p_register);

        init();
        set_button();
    }

    public void init()
    {
        editText_account = (EditText)findViewById(R.id.editText_account);
        editText_password = (EditText)findViewById(R.id.editText_password);
        editText_confirm_password = (EditText)findViewById(R.id.editText_confirm_password);
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
                Register();
                //Intent intent = new Intent(PRegisterActivity.this,LoginActivity.class);
            }
        });
    }

    public void Register(){
        String username = editText_account.getText().toString();
        String password = editText_password.getText().toString();
        String mail = editText_email.getText().toString();
        String phone = editText_phone.getText().toString();
        String nickname = editText_name.getText().toString();
        String confirm_pass = editText_confirm_password.getText().toString();

        if(isInputValid(username,password,confirm_pass,nickname,mail,phone)){
            Retro retro = new Retro();
            retro.pRegister(username,password,nickname,mail,phone)
                    .subscribe(new Subscriber<ResultEntity>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(PRegisterActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNext(ResultEntity resultEntity) {
                            if(resultEntity.getCode()==1){
                                Toast.makeText(PRegisterActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(PRegisterActivity.this,LoginActivity.class);
                            }
                            else Toast.makeText(PRegisterActivity.this,"注册失败",Toast.LENGTH_LONG).show();
                        }
                    });
        }

    }

    public boolean isInputValid(String username,String password,String confirm_pass,String nickname,String mail,String phone){
        boolean flag = true;
        if("".equals(username)||"".equals(password)||"".equals(confirm_pass)||"".equals(nickname)||"".equals(mail)||"".equals(phone)){
            flag = false;
            Toast.makeText(PRegisterActivity.this,"输入存在空项目，请补全",Toast.LENGTH_LONG).show();
        }
        else if(!password.equals(confirm_pass)){
            flag = false;
            Toast.makeText(PRegisterActivity.this,"两次输入的密码不匹配",Toast.LENGTH_LONG).show();
        }
        return flag;
    }
}
