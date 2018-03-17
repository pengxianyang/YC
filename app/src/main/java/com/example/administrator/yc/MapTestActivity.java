package com.example.administrator.yc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class MapTestActivity extends AppCompatActivity {
    private LocationClient locationClient;//定位SDK核心类
    public MyLocationListenner myListener = new MyLocationListenner();//定位监听
    private Double Latitude, Longitude;
    private TextView MapPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map_test);

        //声明定位SDK核心类
        locationClient = new LocationClient(this);
        //注册监听
        locationClient.registerLocationListener(myListener);
        //定位配置信息
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(0);//定位请求时间间隔
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        locationClient.setLocOption(option);
        //开启定位
        locationClient.start();
        MapPos = (TextView)findViewById(R.id.MapPos);
    }

    /*
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) return;
            Latitude = location.getLatitude();
            Longitude = location.getLongitude();
            MapPos.setText(Latitude + "\n" + Longitude);
            //locationClient.stop();
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }
}
