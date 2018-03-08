package com.example.administrator.yc;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yc.model.Match;
import com.example.administrator.yc.retro_interface.Retro;
import com.example.administrator.yc.retro_interface.Service;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    public BoomMenuButton boomMenuButton;
    private List<View> viewList;
    private ViewPager viewPager;
    private MyAdapter myAdapter;
    private CircleIndicator circleIndicator;
    private int numOfPage = 2;
    private List<Match>matches_ = new ArrayList<Match>();
    Retro retro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        retro=new Retro();
        set_Boom_Menu();
        set_View_Pager();
        TextView name=(TextView)findViewById(R.id.textView_name);
        name.setText(GlobalData.username);
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
                    Toast.makeText(MainActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
                    if(index == 0)
                    {
                        Intent intent = new Intent(MainActivity.this,PersonalActivity.class);
                        startActivity(intent);
                    }
                    if(index == 1)
                    {
                        Intent intent = new Intent(MainActivity.this,SearchMatchActivity.class);
                        startActivity(intent);
                    }
                    if (index == 2)
                    {
                        Intent intent = new Intent(MainActivity.this,NewMatchActivity.class);
                        startActivity(intent);
                    }
                    if(index == 3)
                    {
                        //Intent intent = new Intent(MainActivity.this,SearchMatchActivity.class);
                        //startActivity(intent);
                    }
                    if(index == 4)
                    {
                        Intent intent = new Intent(MainActivity.this,MatchListActivity.class);
                        startActivity(intent);
                    }
                    if(index == 5)
                    {
                        //好友列表
                        Intent intent = new Intent(MainActivity.this,ContactviewActivity.class);
                        startActivity(intent);
                    }
                }
            });

            builder.imageRect(new Rect(Util.dp2px(25), Util.dp2px(10), Util.dp2px(55), Util.dp2px(55)));
            if(i==0)//个人信息
            {
                builder.normalImageRes(R.mipmap.me_s);
                builder.normalText("个人信息");
            }
            if(i==1)//寻找比赛
            {
                builder.normalImageRes(R.mipmap.find_s);
                builder.normalText("寻找比赛");

            }
            if(i==2)//创建比赛
            {
                builder.normalImageRes(R.mipmap.add_s);
                builder.normalText("创建比赛");
            }
            if(i==3)//查看消息
            {
                builder.normalImageRes(R.mipmap.mes_s);
                builder.normalText("查看消息");
            }
            if(i==4)//查看已进入的比赛
            {
                builder.normalImageRes(R.mipmap.match_s);
                builder.normalText("我的比赛");
            }
            if(i==5)//好友信息
            {
                builder.normalImageRes(R.mipmap.fri_s);
                builder.normalText("好友信息");
            }


            boomMenuButton.addBuilder(builder);
        }

    }

    public void set_View_Pager()
    {
        viewList = new ArrayList<>();
        for (int i = 0; i < numOfPage; i++) {

            if(i==0)//first_page
            {
                LayoutInflater layoutInflater = getLayoutInflater();
                final View view1 = layoutInflater.inflate(R.layout.layout_viewpager1,null,false);
                ImageView refresh=(ImageView)view1.findViewById(R.id.imageView_refresh);
                refresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"暂无更新",Toast.LENGTH_LONG).show();
                    }
                });

                final ConstraintLayout constrain1=(ConstraintLayout)view1.findViewById(R.id.constrain1);
                final ConstraintLayout constrain2=(ConstraintLayout)view1.findViewById(R.id.constrain2);
                final ConstraintLayout constrain3=(ConstraintLayout)view1.findViewById(R.id.constrain3);
                final TextView textView_sub1_name = (TextView) view1.findViewById(R.id.textView_sub1_name);
                final TextView textView_sub1_date = (TextView) view1.findViewById(R.id.textView_sub1_date);
                final TextView textView_sub1_time = (TextView) view1.findViewById(R.id.textView_sub1_time);
                final TextView textView_sub1_loc = (TextView) view1.findViewById(R.id.textView_sub1_loc);

                final TextView textView_sub2_name = (TextView) view1.findViewById(R.id.textView_sub2_name);
                final TextView textView_sub2_date = (TextView) view1.findViewById(R.id.textView_sub2_date);
                final TextView textView_sub2_time = (TextView) view1.findViewById(R.id.textView_sub2_time);
                final TextView textView_sub2_loc = (TextView) view1.findViewById(R.id.textView_sub2_loc);

                final TextView textView_sub3_name = (TextView) view1.findViewById(R.id.textView_sub3_name);
                final TextView textView_sub3_date = (TextView) view1.findViewById(R.id.textView_sub3_date);
                final TextView textView_sub3_time = (TextView) view1.findViewById(R.id.textView_sub3_time);
                final TextView textView_sub3_loc = (TextView) view1.findViewById(R.id.textView_sub3_loc);

                ImageView imageView_sub1_icon = (ImageView) view1.findViewById(R.id.imageView_sub1);
                ImageView imageView_sub2_icon = (ImageView) view1.findViewById(R.id.imageView_sub2);
                ImageView imageView_sub3_icon = (ImageView) view1.findViewById(R.id.imageView_sub3);

                retro.GetMatchList(GlobalData.username,"0")
                        .subscribe(new Subscriber<List<Match>>() {
                            @Override
                            public void onCompleted() {
                                constrain1.setOnClickListener(onClickListener);
                                constrain2.setOnClickListener(onClickListener);
                                constrain3.setOnClickListener(onClickListener);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(List<Match> matches) {
                                matches_ = matches;
                                if(matches.size()==0){
                                    view1.setVisibility(View.GONE);
                                }
                                else if(matches.size()==1){

                                    textView_sub1_name.setText(matches.get(0).getCreator());
                                    textView_sub1_date.setText(matches.get(0).getTime());
                                    textView_sub1_time.setText(matches.get(0).getTime());
                                    textView_sub1_loc.setText(matches.get(0).getField_name());
                                    constrain2.setVisibility(View.GONE);
                                    constrain3.setVisibility(View.GONE);
                                }
                                else if(matches.size()==2){
                                    textView_sub1_name.setText(matches.get(0).getCreator());
                                    textView_sub1_date.setText(matches.get(0).getTime());
                                    textView_sub1_time.setText(matches.get(0).getTime());
                                    textView_sub1_loc.setText(matches.get(0).getField_name());
                                    textView_sub2_name.setText(matches.get(1).getCreator());
                                    textView_sub2_date.setText(matches.get(1).getTime());
                                    textView_sub2_time.setText(matches.get(1).getTime());
                                    textView_sub2_loc.setText(matches.get(1).getField_name());
                                    constrain3.setVisibility(View.GONE);
                                }
                                else if(matches.size()>=3){
                                    textView_sub1_name.setText(matches.get(0).getCreator());
                                    textView_sub1_date.setText(matches.get(0).getTime());
                                    textView_sub1_time.setText(matches.get(0).getTime());
                                    textView_sub1_loc.setText(matches.get(0).getField_name());
                                    textView_sub2_name.setText(matches.get(1).getCreator());
                                    textView_sub2_date.setText(matches.get(1).getTime());
                                    textView_sub2_time.setText(matches.get(1).getTime());
                                    textView_sub2_loc.setText(matches.get(1).getField_name());
                                    textView_sub3_name.setText(matches.get(2).getCreator());
                                    textView_sub3_date.setText(matches.get(2).getTime());
                                    textView_sub3_time.setText(matches.get(2).getTime());
                                    textView_sub3_loc.setText(matches.get(2).getField_name());
                                }
                            }
                        });
//                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/E1.ttf");
//                textView_name.setTypeface(typeface);
//                textView_signature.setTypeface(typeface);
//                textView_name.setText(GlobalData.getName());
//                textView_fans.setTypeface(typeface);
//                textView_focus.setTypeface(typeface);
//                textView_footprints.setTypeface(typeface);

                viewList.add(i, view1);
            }
            if(i==1)//second_page
            {
                LayoutInflater layoutInflater2 = getLayoutInflater();
                View view2 = layoutInflater2.inflate(R.layout.layout_viewpager2,null,false);
                ImageView refresh=(ImageView)view2.findViewById(R.id.imageView_refresh);
                refresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"暂无更新",Toast.LENGTH_LONG).show();
                    }
                });

                TextView textView_sub1_sender = (TextView) view2.findViewById(R.id.textView_sub1_name);
                TextView textView_sub1_receiver = (TextView) view2.findViewById(R.id.textView_sub1_receiver);
                TextView textView_sub1_sendtime = (TextView) view2.findViewById(R.id.textView_sub1_sendtime);
                TextView textView_sub1_content = (TextView) view2.findViewById(R.id.textView_sub1_content);

                TextView textView_sub2_sender = (TextView) view2.findViewById(R.id.textView_sub2_name);
                TextView textView_sub2_receiver = (TextView) view2.findViewById(R.id.textView_sub2_receiver);
                TextView textView_sub2_sendtime = (TextView) view2.findViewById(R.id.textView_sub2_sendtime);
                TextView textView_sub2_content = (TextView) view2.findViewById(R.id.textView_sub2_content);

                ImageView imageView_sub1_sender_icon = (ImageView) view2.findViewById(R.id.imageView_sub1_sender_icon);
                ImageView imageView_sub2_sender_icon = (ImageView) view2.findViewById(R.id.imageView_sub2_sender_icon);


                viewList.add(i, view2);

            }

        }

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        myAdapter = new MyAdapter();
        viewPager.setAdapter(myAdapter);
        //viewPager.setPageTransformer(true ,new SimplePageTransform());

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==0&&positionOffset==0)
                {
                    //circleImageView2.setVisibility(VISIBLE);
                }
                else
                {
                    //circleImageView2.setVisibility(GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {

                if(position==0)
                {
                    //circleImageView2.setVisibility(VISIBLE);
                }
                else if(position==1)
                {
                    //circleImageView2.setVisibility(GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        circleIndicator = (CircleIndicator)findViewById(R.id.indicator);
        circleIndicator.setViewPager(viewPager);
    }


    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return numOfPage;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //container.removeView(viewList.get(position));
            container.removeView((View)object);
        }

        @Override
        public int getItemPosition(Object object) {
            // 最简单解决 notifyDataSetChanged() 页面不刷新问题的方法
            return POSITION_NONE;
        }

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Match match = null;
            switch (view.getId()){
                case R.id.constrain1:
                    if(matches_.size()>0){
                        match = matches_.get(0);
                    }
                    break;
                case R.id.constrain2:
                    if(matches_.size()>1){
                        match = matches_.get(1);
                    }
                    break;
                case R.id.constrain3:
                    if(matches_.size()>2){
                        match = matches_.get(2);
                    }
                    break;
            }
            Intent intent = new Intent(MainActivity.this,MatchDetails2Activity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("match",match);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
