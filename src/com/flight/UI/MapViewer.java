package com.flight.UI;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.flight.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MapViewer extends Activity {

	private MapView mMapView = null;
	private MapController mMapController = null;

	private LocationClient mLocClient = null;
	private LocationData mLocData = null;
	private MyLocationListenner myListener = new MyLocationListenner();
	
	private boolean isRequest = false;
	private boolean isFirstLoc = true;
	private boolean isLocationClientStop = false;
	
	private MyLocationOverlay myLocationOverlay = null;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_viewer);
        
        mMapView = (MapView)findViewById(R.id.mapView);
        mMapController = mMapView.getController();
        mMapView.getController().setZoom(14);
        mMapView.getController().enableClick(true);
        mMapView.setBuiltInZoomControls(true);
        
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setCoorType("bd09ll");
        option.setScanSpan(5000);

        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        mLocClient.setLocOption(option);
        mLocClient.start();
        
		myLocationOverlay = new MyLocationOverlay(mMapView);
        mLocData = new LocationData();
	    myLocationOverlay.setData(mLocData);
		mMapView.getOverlays().add(myLocationOverlay);
		myLocationOverlay.enableCompass();
		mMapView.refresh();
		
		Button locButton = (Button)findViewById(R.id.cusLocation);
		locButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				requestLocClick();
			}
		});
    }
    
    @Override
    protected void onPause() {
    	isLocationClientStop = true;
        mMapView.onPause();
        super.onPause();
    }
    
    @Override
    protected void onResume() {
    	isLocationClientStop = false;
        mMapView.onResume();
        super.onResume();
    }
    
    @Override
    protected void onDestroy() {
        if (mLocClient != null) {
            mLocClient.stop();
        }
        isLocationClientStop = true;
        mMapView.destroy();
        super.onDestroy();
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	mMapView.onSaveInstanceState(outState);
    	
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    	super.onRestoreInstanceState(savedInstanceState);
    	mMapView.onRestoreInstanceState(savedInstanceState);
    }
    
    // 
    private void requestLocClick() {
    	isRequest = true;
        mLocClient.requestLocation();
        Toast.makeText(MapViewer.this, "正在定位…", Toast.LENGTH_SHORT).show();
    }
	
	//
    public class MyLocationListenner implements BDLocationListener {
    	
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null || isLocationClientStop)
                return ;
            
            mLocData.latitude = location.getLatitude();
            mLocData.longitude = location.getLongitude();
            mLocData.accuracy = location.getRadius();
            mLocData.direction = location.getDerect();
            myLocationOverlay.setData(mLocData);
            mMapView.refresh();
            if (isRequest || isFirstLoc) {
                mMapController.animateTo(new GeoPoint((int)(mLocData.latitude*1e6), (int)(mLocData.longitude*1e6)));
                isRequest = false;
            }
            isFirstLoc = false;
        }
        
        public void onReceivePoi(BDLocation poiLocation) {
            if (poiLocation == null) {
                return ;
            }
        }
    }
	
}