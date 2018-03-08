package com.example.administrator.yc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yc.model.Player;
import com.example.administrator.yc.retro_interface.Retro;

import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;

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
        Retro retro=new Retro();
        retro.GetPlayer(GlobalData.username)
                .subscribe(new Subscriber<Player>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(PersonalActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(Player player) {
                        textView_account.setText("用户名:"+player.getUsername());
                        textView_name.setText("昵称:"+player.getNickname());
                        textView_email.setText("邮箱:"+player.getMail());
                        textView_phone.setText("电话:"+player.getPhone());
                    }
                });
    }
}
