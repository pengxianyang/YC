package com.example.administrator.yc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yc.model.Match;
import com.example.administrator.yc.model.ResultEntity;
import com.example.administrator.yc.retro_interface.Retro;

import rx.Subscriber;

/**
 * Created by a on 2018/3/8.
 */

public class MatchDetails2Activity extends AppCompatActivity{
    public ImageView imageView_match_type;//比赛类型
    public TextView textView_match_status;//比赛状态
    public TextView textView_player_counts;//比赛人数(当前人数/总人数)
    public TextView textView_match_place;//比赛场馆
    public TextView textView_match_date;//比赛日期
    public TextView textView_match_time;//比赛时间
    public Button button_confirm_match,button_check_player;//确认比赛按钮
    private String fieldName;
    Retro retro;
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
        button_confirm_match.setVisibility(View.GONE);
        button_check_player=(Button)findViewById(R.id.button_check_participator);

        final Match match = (Match) getIntent().getExtras().getSerializable("match");
        textView_match_status.setText("状态:"+match.getStatus());
        textView_player_counts.setText("当前比赛人数"+match.getCurrentNum()+"/"+match.getTotal());
        String[] time=match.getTime().split(" ");
        textView_match_date.setText("比赛日期:"+time[0]);
        textView_match_time.setText("比赛时间:"+time[1]);
        textView_match_place.setText("比赛地点:"+match.getField_name());

        button_check_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MatchDetails2Activity.this,ParticipatorListActivity.class);
                intent.putExtra("matchId",String.valueOf(match.getId()));
                startActivity(intent);
            }
        });
        Retro retro=new Retro();
        retro.ParticipateMatch(match.getId(),GlobalData.username)
                .subscribe(new Subscriber<ResultEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResultEntity resultEntity) {
                        if(resultEntity.getCode()==1) Toast.makeText(MatchDetails2Activity.this,"成功参与此比赛！",Toast.LENGTH_LONG).show();
                    }
                });
    }

}
