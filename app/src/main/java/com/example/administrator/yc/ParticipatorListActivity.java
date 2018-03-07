package com.example.administrator.yc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/8.
 */

public class ParticipatorListActivity extends AppCompatActivity{
    public ListView listView_participator_list;
    public List<Map<String,String>> list_infos;
    public int total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participator_list);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init_listView();

    }

    public void init_listView()
    {
        listView_participator_list = (ListView)findViewById(R.id.ListView_particpator_list);
        list_infos = new ArrayList<>();
        total=10;

        for(int i=0;i<=total-1;i++)
        {
            Map<String,String> map = new LinkedHashMap<>();
            map.put("name","欧欣祺");
            map.put("phone","13622222222");
            map.put("sexal","男");
            list_infos.add(map);
        }



        SimpleAdapter simpleAdapter = new SimpleAdapter(
                ParticipatorListActivity.this,
                list_infos,
                R.layout.layout_person,
                new String[]{"name","phone","sexal"},
                new int[]{R.id.textView_name ,R.id.textView_phone, R.id.textView_sexal});

        listView_participator_list.setAdapter(simpleAdapter);
    }
}
