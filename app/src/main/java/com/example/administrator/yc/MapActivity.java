package com.example.administrator.yc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        setContentView(R.layout.activity_map);

        //获取百度地图控件
        mapView = (TextureMapView) findViewById(R.id.mTexturemap);
        //获取百度地图对象
        baiduMap = mapView.getMap();
        baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
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
                LatLng xy = new LatLng(38,105);
                MapStatusUpdate status = MapStatusUpdateFactory.newLatLngZoom(xy,5);
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
        bitmap[0] = BitmapDescriptorFactory.fromResource(R.mipmap.basketball_s);
        point[0] = new LatLng(34.9399499582,113.0954316198);
        //地点2
        bitmap[1] = BitmapDescriptorFactory.fromResource(R.mipmap.basketball_s);
        point[1] = new LatLng(35.2443351954,110.5224155436);
        //地点3
        bitmap[2] = BitmapDescriptorFactory.fromResource(R.mipmap.basketball_s);
        point[2] = new LatLng(39.4910370289,115.9809870437);
        //地点4
        bitmap[3] = BitmapDescriptorFactory.fromResource(R.mipmap.basketball_s);
        point[3] = new LatLng(35.5316306208,118.4405949919);
        //地点5
        bitmap[4] = BitmapDescriptorFactory.fromResource(R.mipmap.basketball_s);
        point[4] = new LatLng(30.2799186759,120.1617445782);
        //创建OverlayOptions的集合
        List<OverlayOptions> options = new ArrayList<OverlayOptions>();

        //创建OverlayOptions属性,并将OverlayOptions添加到list
        for(int i = 0; i < num; i++){
            overlayOptions[i] =  new MarkerOptions()
                    .position(point[i])
                    .icon(bitmap[i]).perspective(true);
            options.add(overlayOptions[i]);
        }
        //在地图上批量添加
        baiduMap.addOverlays(options);
        //设置监听器
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return false;
            }
        });
    }
}