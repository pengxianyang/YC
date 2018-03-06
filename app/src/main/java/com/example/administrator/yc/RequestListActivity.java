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
 * Created by Administrator on 2018/3/4.
 */

public class RequestListActivity extends AppCompatActivity {

    public ListView listView_request_list;
    public List<Map<String,String>> list_infos;
    public int total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);
        getSupportActionBar().hide();

        init_listView();

    }

    public void init_listView()
    {
        listView_request_list = (ListView)findViewById(R.id.ListView_request_list);
        list_infos = new ArrayList<>();

        for(int i=0;i<=total-1;i++)
        {
            Map<String,String> map = new LinkedHashMap<>();
            map.put("matchType","matchType");
            map.put("requestOwner","requestOwner");
            map.put("requestDate","requestDate");
            map.put("requestTime","requestTime");
            list_infos.add(map);
        }



        SimpleAdapter simpleAdapter = new SimpleAdapter(
               RequestListActivity.this,
                list_infos,
                R.layout.layout_request,
                new String[]{"matchType","requestOwner","requestDate","requestTime"},
                new int[]{R.id.textView_matchType ,R.id.textView_requestOwner, R.id.textView_requestDate,R.id.textView_requestTime});

        listView_request_list.setAdapter(simpleAdapter);
    }
}
