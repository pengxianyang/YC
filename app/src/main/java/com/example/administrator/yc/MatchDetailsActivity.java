package com.example.administrator.yc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/3/7.
 */

public class MatchDetailsActivity extends AppCompatActivity {

    public ImageView imageView_match_type;//比赛类型
    public TextView textView_match_status;//比赛状态
    public TextView textView_player_counts;//比赛人数(当前人数/总人数)
    public TextView textView_match_place;//比赛场馆
    public TextView textView_match_date;//比赛日期
    public TextView textView_match_time;//比赛时间
    public Button button_confirm_match;//确认比赛按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageView_match_type = (ImageView)findViewById(R.id.imageView_match_type);
        textView_match_status = (TextView)findViewById(R.id.TextView_match_status);
        textView_match_date = (TextView)findViewById(R.id.TextView_match_date);
        textView_match_time = (TextView)findViewById(R.id.TextView_match_time);
        textView_player_counts = (TextView)findViewById(R.id.TextView_player_counts);
        textView_match_place = (TextView)findViewById(R.id.TextView_match_place);
        button_confirm_match = (Button)findViewById(R.id.button_confirm_match);

    }
}
