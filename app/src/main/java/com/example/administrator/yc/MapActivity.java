package com.example.administrator.yc;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.model.LatLng;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity {

    private LocationClient locationClient;//定位SDK核心类
    public MyLocationListenner myListener = new MyLocationListenner();//定位监听
    private TextureMapView mapView;//百度地图控件
    private BaiduMap baiduMap;//百度地图对象
    boolean isFirstLoc = true; // 是否首次定位
    public static String id;

    //地图类型切换
    int flag = 1;
    FloatingActionButton floatingActionButton;

    //标记点
    int num = 5;
    BitmapDescriptor[] bitmap = new BitmapDescriptor[num];
    LatLng[] point = new LatLng[num];
    OverlayOptions[] overlayOptions = new OverlayOptions[num];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_map);

//        Match = this.getIntent().getExtras();
        //获取百度地图控件
        mapView = (TextureMapView) findViewById(R.id.mTexturemap);
        //获取百度地图对象
        baiduMap = mapView.getMap();
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(flag == 1){
                    baiduMap.setTrafficEnabled(false);
                    baiduMap.setBaiduHeatMapEnabled(false);
                    baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                    flag = 2;
                }else if(flag == 2){
                    baiduMap.setTrafficEnabled(false);
                    baiduMap.setBaiduHeatMapEnabled(false);
                    baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                    flag = 3;
                }else if(flag == 3){
                    baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                    baiduMap.setTrafficEnabled(true);
                    baiduMap.setBaiduHeatMapEnabled(false);
                    flag = 4;
                }else if(flag == 4){
                    baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                    baiduMap.setTrafficEnabled(false);
                    baiduMap.setBaiduHeatMapEnabled(true);
                    flag = 1;
                }
            }
        });

        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
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
        //放置标记点
        initial_pos();
    }

    /*
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            baiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
//                LatLng xy = new LatLng(location.getLatitude(),location.getLongitude());
                LatLng xy = new LatLng(23.0662500000,113.3895300000);
                MapStatusUpdate status = MapStatusUpdateFactory.newLatLngZoom(xy,16);
                baiduMap.animateMapStatus(status);

            }
        }
        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        locationClient.stop();
        // 关闭定位图层
        baiduMap.setMyLocationEnabled(false);
        mapView.onDestroy();
        mapView = null;
        super.onDestroy();
    }

    public void initial_pos(){
        //构建Marker图标，并设置坐标点
        //地点1
        bitmap[0] = BitmapDescriptorFactory.fromResource(R.mipmap.basketball_map);
        point[0] = new LatLng(23.0634648499,113.3992052078);
        //地点2
        bitmap[1] = BitmapDescriptorFactory.fromResource(R.mipmap.football_map);
        point[1] = new LatLng(23.0557255312,113.3808374405);
        //地点3
        bitmap[2] = BitmapDescriptorFactory.fromResource(R.mipmap.football_map);
        point[2] = new LatLng(23.0548568044,113.4082603455);
        //地点4
        bitmap[3] = BitmapDescriptorFactory.fromResource(R.mipmap.football_map);
        point[3] = new LatLng(23.0389028245,113.3922100067);
        //地点5
        bitmap[4] = BitmapDescriptorFactory.fromResource(R.mipmap.basketball_map);
        point[4] = new LatLng(23.0401270829,113.3686494827);
        //创建OverlayOptions的集合
        List<OverlayOptions> options = new ArrayList<OverlayOptions>();

        //创建OverlayOptions属性,并将OverlayOptions添加到list
        for(int i = 0; i < num; i++){
            Bundle id_bundle = new Bundle();
            id_bundle.putString("id",Integer.toString(i+1));
            overlayOptions[i] =  new MarkerOptions()
                    .position(point[i])
                    .icon(bitmap[i]).perspective(false)
                    .extraInfo(id_bundle)
                    .zIndex(20);
            options.add(overlayOptions[i]);
        }
        //在地图上批量添加
        baiduMap.addOverlays(options);

        //创建InfoWindow展示的view
        TextView textView = new TextView(getApplicationContext());
        textView.setText("中大运动场");
        textView.setTextSize(24);
        //textView.setTextColor(Color.rgb(88, 186, 206));
        textView.setTextColor(Color.rgb(230, 102, 102));
        //定义用于显示该InfoWindow的坐标点
        LatLng pt = new LatLng(23.0401270829,113.3686494827);
        //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
        InfoWindow mInfoWindow = new InfoWindow(textView, pt, -90);
        //显示InfoWindow
        baiduMap.showInfoWindow(mInfoWindow);

        //设置监听器
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                id = marker.getExtraInfo().getString("id");
                Intent intent=new Intent(MapActivity.this,MatchDetailsActivity.class);
                startActivity(intent);
                return false;
            }
        });


    }
}