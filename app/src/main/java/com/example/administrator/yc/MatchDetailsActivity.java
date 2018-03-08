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

import com.example.administrator.yc.model.ResultEntity;
import com.example.administrator.yc.retro_interface.Retro;

import rx.Subscriber;

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
        button_check_player=(Button)findViewById(R.id.button_check_participator);
        button_check_player.setVisibility(View.GONE);
        retro=new Retro();
        getFieldName(MapActivity.id);
        button_confirm_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date=NewMatchActivity.date_m+"-"+NewMatchActivity.date_d;
                String time=NewMatchActivity.time_h+":"+NewMatchActivity.time_m;
                CreateMatch(GlobalData.username,date+" "+time,fieldName,0,
                               Integer.parseInt(NewMatchActivity.num),NewMatchActivity.type);
            }
        });
        button_check_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MatchDetailsActivity.this,ParticipatorListActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getFieldName(String fieldId){
        retro.GetFieldName(fieldId)
                .subscribe(new Subscriber<ResultEntity>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
Toast.makeText(MatchDetailsActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(ResultEntity s) {
                        fieldName=s.getMessage();
                        textView_match_place.setText("场地:"+s.getMessage());
                        String date=NewMatchActivity.date_m+"-"+NewMatchActivity.date_d;
                        String time=NewMatchActivity.time_h+":"+NewMatchActivity.time_m;
                        textView_match_time.setText("时间:"+time);
                        textView_match_date.setText("日期:"+date);
                        textView_player_counts.setText("当前人数:"+"1/"+NewMatchActivity.num);
                        textView_match_status.setText("状态:waiting");
                    }
                });
    }
    private void CreateMatch(String creator,String time,String field_name,int field_id,int total,String type){
        retro.CreateMatch(creator,time,field_name,field_id,total,type)
                .subscribe(new Subscriber<ResultEntity>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MatchDetailsActivity.this,"创建成功",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MatchDetailsActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(ResultEntity resultEntity) {
                        Toast.makeText(MatchDetailsActivity.this,String.valueOf(resultEntity.getCode()),Toast.LENGTH_LONG).show();
                    }
                });
    }
}
