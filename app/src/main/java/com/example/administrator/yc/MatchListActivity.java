package com.example.administrator.yc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/6.
 */

public class MatchListActivity extends AppCompatActivity {
    public ListView listView_match_list;
    public List<Map<String,String>> list_infos;
    public int total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);
        getSupportActionBar().hide();

        init_listView();

    }

    public void init_listView()
    {
        listView_match_list = (ListView)findViewById(R.id.ListView_match_list);
        list_infos = new ArrayList<>();
        total = 3;

        for(int i=0;i<=total-1;i++)//表示比赛类型的图片还要加入!
        {
            Map<String,String> map = new LinkedHashMap<>();
            map.put("matchOwner","orderOwner");
            map.put("matchDate","matchDate");
            map.put("matchTime","matchTime");
            map.put("matchLocation","matchLocation");
            map.put("matchStatus","Status");
            list_infos.add(map);
        }



        SimpleAdapter simpleAdapter = new SimpleAdapter(
                MatchListActivity.this,
                list_infos,
                R.layout.layout_match,
                new String[]{"matchOwner","matchDate","matchTime","matchLocation","matchStatus"},
                new int[]{R.id.textView_sub1_name ,R.id.textView_sub1_date, R.id.textView_sub1_time,R.id.textView_sub1_loc,R.id.textView_sub1_sit});

        listView_match_list.setAdapter(simpleAdapter);
        listView_match_list.setDivider(null);
    }

}
