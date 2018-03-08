package com.example.administrator.yc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.administrator.yc.model.Match;
import com.example.administrator.yc.retro_interface.Retro;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;

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
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init_listView();

    }

    public void init_listView()
    {
        listView_match_list = (ListView)findViewById(R.id.ListView_match_list);
        list_infos = new ArrayList<>();
        Intent intent=this.getIntent();
        String type=intent.getStringExtra("type");
        Retro retro=new Retro();
        retro.GetMatchList(type,"1")
                .subscribe(new Subscriber<List<Match>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MatchListActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(final List<Match> matches) {
                        for(int i=0;i<matches.size();i++)//表示比赛类型的图片还要加入!
                        {
                            Map<String,String> map = new LinkedHashMap<>();
                            map.put("matchOwner",matches.get(i).getCreator());
                            map.put("matchDate",matches.get(i).getTime());
                            map.put("matchTime",matches.get(i).getTime());
                            map.put("matchLocation",matches.get(i).getField_name());
                            map.put("matchStatus",matches.get(i).getStatus());
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
                        listView_match_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent=new Intent(MatchListActivity.this,MatchDetails2Activity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("match",matches.get(i));
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                    }
                });

    }

}
