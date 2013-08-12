package com.flight;

import com.flight.UI.MapViewer;
import com.flight.UI.TabMenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class WelcomeScreen extends Activity {
	
	private long mSplashTime = 1000;
	
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(WelcomeScreen.this, TabMenu.class);
				startActivity(intent);
			}
		}, mSplashTime);		
		
	}
	
}
