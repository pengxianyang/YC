package com.example.administrator.yc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by Administrator on 2018/3/6.
 */

public class NewMatchActivity extends AppCompatActivity {

    private Button button_toGPS;
    private RadioGroup matchType;
    private EditText matchTime_h,matchTime_m,matchDate_m,matchDate_d,matchNum;
    public static String type,time_h,time_m,date_m,date_d,num;

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
        matchType=(RadioGroup)findViewById(R.id.radioGroup_matchtype);
        matchTime_h=(EditText)findViewById(R.id.editText_matchtime_h);
        matchTime_m=(EditText)findViewById(R.id.editText_matchtime_m);
        matchDate_d=(EditText)findViewById(R.id.editText_matchdate_d);
        matchDate_m=(EditText)findViewById(R.id.editText_matchdate_m);
        matchNum=(EditText)findViewById(R.id.editText_match_max_player);
        button_toGPS = (Button)findViewById(R.id.button_toGPS);
    }

    public void set_Button()
    {
        button_toGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置跳转到GPS页面
                RadioButton radioButton=(RadioButton)findViewById( matchType.getCheckedRadioButtonId());
                type=radioButton.getText().toString();
                time_h=matchTime_h.getText().toString();
                time_m=matchTime_m.getText().toString();
                date_d=matchDate_d.getText().toString();
                date_m=matchDate_m.getText().toString();
                num=matchNum.getText().toString();
                Intent intent=new Intent(NewMatchActivity.this,MatchDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

}
