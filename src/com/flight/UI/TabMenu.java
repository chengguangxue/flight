package com.flight.UI;

import android.content.Intent;
import android.os.Bundle;

import com.flight.R;
import com.mobyfactory.uiwidgets.ScrollableTabActivity;

public class TabMenu extends ScrollableTabActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent;

		intent = new Intent(this, Route.class);
		addTab("route", R.drawable.ic_launcher, intent, true);
		
		intent = new Intent(this, MapViewer.class);
		addTab("map", R.drawable.icon_tabbar_map, intent, true);

		intent = new Intent(this, Input.class);
		addTab("input", R.drawable.ic_launcher, intent, true);

		intent = new Intent(this, Query.class);
		addTab("query", R.drawable.ic_launcher, intent, true);

		intent = new Intent(this, Setting.class);
		addTab("setting", R.drawable.ic_launcher, intent, true);

		commit();
		setCurrentTab(0);
	}	
}