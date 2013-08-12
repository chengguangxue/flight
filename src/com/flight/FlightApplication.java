package com.flight;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;

public class FlightApplication extends Application {
	
    private static FlightApplication mInstance = null;
    public boolean m_bKeyRight = true;
    BMapManager mBMapManager = null;

    public static final String strKey = "F75BCE66EC9303A953932FE78527B1F21E53FC67";
	
	@Override
    public void onCreate() {
	    super.onCreate();
		mInstance = this;
		initEngineManager(this);
	}
	
	public void initEngineManager(Context context) {
        if (mBMapManager == null) {
            mBMapManager = new BMapManager(context);
        }

        if (!mBMapManager.init(strKey,new MyGeneralListener())) {
            Toast.makeText(FlightApplication.getInstance().getApplicationContext(), 
                    "BMapManager  ��ʼ������!", Toast.LENGTH_LONG).show();
        }
	}
	
	public static FlightApplication getInstance() {
		return mInstance;
	}
	
	
	// �����¼���������������ͨ�������������Ȩ��֤�����
    static class MyGeneralListener implements MKGeneralListener {
        
        @Override
        public void onGetNetworkState(int iError) {
            if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
                Toast.makeText(FlightApplication.getInstance().getApplicationContext(), "���������������",
                    Toast.LENGTH_LONG).show();
            }
            else if (iError == MKEvent.ERROR_NETWORK_DATA) {
                Toast.makeText(FlightApplication.getInstance().getApplicationContext(), "������ȷ�ļ���������",
                        Toast.LENGTH_LONG).show();
            }
            // ...
        }

        @Override
        public void onGetPermissionState(int iError) {
            if (iError ==  MKEvent.ERROR_PERMISSION_DENIED) {
                //��ȨKey����
                Toast.makeText(FlightApplication.getInstance().getApplicationContext(), 
                        "���� DemoApplication.java�ļ�������ȷ����ȨKey��", Toast.LENGTH_LONG).show();
                FlightApplication.getInstance().m_bKeyRight = false;
            }
        }
    }
}