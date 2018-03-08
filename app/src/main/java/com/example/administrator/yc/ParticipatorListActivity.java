package com.example.administrator.yc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.administrator.yc.model.Player;
import com.example.administrator.yc.retro_interface.Retro;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;

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
        Intent intent=this.getIntent();
        String matchId=intent.getStringExtra("matchId");
        Toast.makeText(ParticipatorListActivity.this,matchId,Toast.LENGTH_LONG).show();
        listView_participator_list = (ListView)findViewById(R.id.ListView_particpator_list);
        list_infos = new ArrayList<>();
        Retro retro=new Retro();
        retro.GetPlayerList(matchId)
                .subscribe(new Subscriber<List<Player>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Player> players) {
                        for(int i=0;i<players.size();i++)
                        {
                            Map<String,String> map = new LinkedHashMap<>();
                            map.put("name","用户名:"+players.get(i).getUsername());
                            map.put("phone","电话:"+players.get(i).getPhone());
                            map.put("sexal","邮箱:"+players.get(i).getMail());
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
                });
    }
}
