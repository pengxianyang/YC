package com.example.administrator.yc;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/3/6.
 */

public class CoverActivity extends AppCompatActivity{

    public TextView textView_intro1;
    public TextView textView_intro2;
    public TextView textView_intro3;
    public TextView textView_intro4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_cover);

        set_font();
        jump();

    }

    public void set_font()
    {
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "font/Ga.otf");
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), "font/yanti.ttf");
        textView_intro1 = (TextView)findViewById(R.id.textView_intro);
        textView_intro2 = (TextView)findViewById(R.id.textView_intro2);
        textView_intro1.setTypeface(typeface1);
        textView_intro2.setTypeface(typeface1);

        textView_intro3 = (TextView)findViewById(R.id.textView_intro3);
        textView_intro4 = (TextView)findViewById(R.id.textView_intro4);
        textView_intro3.setTypeface(typeface2);
        textView_intro4.setTypeface(typeface2);
    }

    public void jump(){
        Timer time = new Timer();
        TimerTask tk = new TimerTask() {
            Intent intent = new Intent(CoverActivity.this,LoginActivity.class);
            @Override
            public void run() {
                // TODO Auto-generated method stub

                startActivity(intent);
                finish();
            }
        };time.schedule(tk, 2000);

    }

}
