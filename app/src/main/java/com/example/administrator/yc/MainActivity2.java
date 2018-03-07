package com.example.administrator.yc;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity2 extends AppCompatActivity {

    public BoomMenuButton boomMenuButton;
    private List<View> viewList;
    private ViewPager viewPager;
    private MyAdapter myAdapter;
    private CircleIndicator circleIndicator;
    private int numOfPage = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_2);

        set_Boom_Menu();
        set_View_Pager();
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
                    if(index == 0)
                    {
                        Intent intent = new Intent(MainActivity2.this,PersonalActivity.class);
                        startActivity(intent);
                    }
                    if(index == 1)
                    {
                        Intent intent = new Intent(MainActivity2.this,MapActivity.class);
                        startActivity(intent);
                    }
                    if (index == 2)
                    {
                        Intent intent = new Intent(MainActivity2.this,NewMatchActivity.class);
                        startActivity(intent);
                    }
                    if(index == 3)
                    {
                        Intent intent = new Intent(MainActivity2.this,OrderListActivity.class);
                        startActivity(intent);
                    }
                    if(index == 4)
                    {
                        Intent intent = new Intent(MainActivity2.this,MatchDetailsActivity.class);
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
                builder.normalText("比赛请求");

            }
            if(i==2)//创建比赛
            {
                builder.normalImageRes(R.mipmap.add_s);
                builder.normalText("查看订单");
            }
            if(i==3)//查看消息
            {
                builder.normalImageRes(R.mipmap.mes_s);
                builder.normalText("查看消息");
            }
            if(i==4)//好友信息
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
                View view1 = layoutInflater.inflate(R.layout.layout_viewpager1,null,false);
                TextView textView_sub1_name = (TextView) view1.findViewById(R.id.textView_sub1_name);
                TextView textView_sub1_date = (TextView) view1.findViewById(R.id.textView_sub1_date);
                TextView textView_sub1_time = (TextView) view1.findViewById(R.id.textView_sub1_time);
                TextView textView_sub1_loc = (TextView) view1.findViewById(R.id.textView_sub1_loc);

                TextView textView_sub2_name = (TextView) view1.findViewById(R.id.textView_sub2_name);
                TextView textView_sub2_date = (TextView) view1.findViewById(R.id.textView_sub2_date);
                TextView textView_sub2_time = (TextView) view1.findViewById(R.id.textView_sub2_time);
                TextView textView_sub2_loc = (TextView) view1.findViewById(R.id.textView_sub2_loc);

                TextView textView_sub3_name = (TextView) view1.findViewById(R.id.textView_sub3_name);
                TextView textView_sub3_date = (TextView) view1.findViewById(R.id.textView_sub3_date);
                TextView textView_sub3_time = (TextView) view1.findViewById(R.id.textView_sub3_time);
                TextView textView_sub3_loc = (TextView) view1.findViewById(R.id.textView_sub3_loc);

                ImageView imageView_sub1_icon = (ImageView) view1.findViewById(R.id.imageView_sub1);
                ImageView imageView_sub2_icon = (ImageView) view1.findViewById(R.id.imageView_sub2);
                ImageView imageView_sub3_icon = (ImageView) view1.findViewById(R.id.imageView_sub3);

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

}
