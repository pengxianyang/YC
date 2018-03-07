package com.example.administrator.yc;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Util;

public class MainActivity2 extends AppCompatActivity {

    public BoomMenuButton boomMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        set_Boom_Menu();
    }

    public void set_Boom_Menu()
    {
        boomMenuButton = (BoomMenuButton)findViewById(R.id.bmb);
        for (int i = 0; i < boomMenuButton.getPiecePlaceEnum().pieceNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder();
            //.normalImageRes(R.drawable.icon1);
            builder.listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {
                    Toast.makeText(MainActivity2.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
                    if(index == 1)
                    {
                        //Intent intent = new Intent(MainActivity.this,NewblogActivity.class);
                        //startActivity(intent);
                    }
                    if (index == 2)
                    {
                        //Intent intent = new Intent(MainActivity.this,GaodeActivity.class);
                        //startActivity(intent);
                    }
                    if(index == 3)
                    {
                        //Intent intent = new Intent(MainActivity.this,FriendActivity.class);
                        //startActivity(intent);
                    }
                    if(index == 4)
                    {
                        //Intent intent = new Intent(MainActivity.this,ModifyActivity.class);
                        //startActivity(intent);
                    }
                    if(index == 5)
                    {
                        //Intent intent = new Intent(MainActivity.this,LookActivity.class);
                        //startActivity(intent);
                    }
                }
            });

            builder.imageRect(new Rect(Util.dp2px(25), Util.dp2px(10), Util.dp2px(55), Util.dp2px(55)));
            if(i==0)//个人信息
            {
                //builder.normalImageRes(R.drawable.homepage_icon);
                builder.normalText("个人信息");
            }
            if(i==1)//待处理的比赛请求
            {
                //builder.normalImageRes(R.drawable.writenew_icon);
                builder.normalText("比赛请求");

            }
            if(i==2)//处理中的订单
            {
                //builder.normalImageRes(R.drawable.gps_icon);
                builder.normalText("处理订单");
            }
            if(i==3)//查看消息
            {
                //builder.normalImageRes(R.drawable.friend_icon);
                builder.normalText("查看消息");
            }
            if(i==4)//查看场地
            {
                //builder.normalImageRes(R.drawable.personal_icon);
                builder.normalText("我的场地");
            }
            if(i==5)//好友信息
            {
                //builder.normalImageRes(R.drawable.lookaround_icon);
                builder.normalText("好友信息");
            }


            boomMenuButton.addBuilder(builder);
        }

    }
}
