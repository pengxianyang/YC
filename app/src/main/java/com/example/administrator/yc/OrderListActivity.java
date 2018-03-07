package com.example.administrator.yc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/4.
 */

public class OrderListActivity extends AppCompatActivity {
    public ListView listView_order_list;
    public List<Map<String,String>> list_infos;
    public int total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init_listView();

    }

    public void init_listView()
    {
        listView_order_list = (ListView)findViewById(R.id.ListView_order_list);
        list_infos = new ArrayList<>();

        for(int i=0;i<=total-1;i++)
        {
            Map<String,String> map = new LinkedHashMap<>();
            map.put("orderOwner","orderOwner");
            map.put("orderStatus","orderStatus");
            map.put("orderNumber","orderNumber");
            list_infos.add(map);
        }



        SimpleAdapter simpleAdapter = new SimpleAdapter(
                OrderListActivity.this,
                list_infos,
                R.layout.layout_order,
                new String[]{"orderOwner","orderNumber","orderStatus"},
                new int[]{R.id.textView_orderOwner ,R.id.textView_orderNumber, R.id.textView_orderStatus});

        listView_order_list.setAdapter(simpleAdapter);
    }

}
