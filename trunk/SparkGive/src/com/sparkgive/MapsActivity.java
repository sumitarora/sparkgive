package com.sparkgive;


import java.util.List;



import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.sparkgive.components.SimpleItemizedOverlay;

public class MapsActivity extends MapActivity {


	MapView mapView;
	List<Overlay> mapOverlays;
	Drawable drawable;
	Drawable drawable2;
	SimpleItemizedOverlay itemizedOverlay;
	SimpleItemizedOverlay itemizedOverlay2;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        
        mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		
		mapOverlays = mapView.getOverlays();
		
		// first overlay
		drawable = getResources().getDrawable(R.drawable.marker);
		itemizedOverlay = new SimpleItemizedOverlay(drawable, mapView);
		
		GeoPoint point = new GeoPoint((int)(43.6475768*1E6),(int)(-79.4020997*1E6));
		OverlayItem overlayItem = new OverlayItem(point, "Shoppers Drug Mart", 
				"Partnered with Sick kids Foundation");
		itemizedOverlay.addOverlay(overlayItem);
		
//		GeoPoint point2 = new GeoPoint((int)(51.515259*1E6),(int)(-0.086623*1E6));
//		OverlayItem overlayItem2 = new OverlayItem(point2, "GoldenEye (1995)", 
//				"(Interiors Russian defence ministry council chambers in St Petersburg)");		
//		itemizedOverlay.addOverlay(overlayItem2);
		
		mapOverlays.add(itemizedOverlay);
		
//		// second overlay
//		drawable2 = getResources().getDrawable(R.drawable.marker2);
//		itemizedOverlay2 = new SimpleItemizedOverlay(drawable2, mapView);
//		
//		GeoPoint point3 = new GeoPoint((int)(51.513329*1E6),(int)(-0.08896*1E6));
//		OverlayItem overlayItem3 = new OverlayItem(point3, "Sliding Doors (1998)", null);
//		itemizedOverlay2.addOverlay(overlayItem3);
//		
//		GeoPoint point4 = new GeoPoint((int)(51.51738*1E6),(int)(-0.08186*1E6));
//		OverlayItem overlayItem4 = new OverlayItem(point4, "Mission: Impossible (1996)", 
//				"(Ethan & Jim cafe meeting)");
//		itemizedOverlay2.addOverlay(overlayItem4);
//		
//		mapOverlays.add(itemizedOverlay2);
		
		if (savedInstanceState == null) {
			
			final MapController mc = mapView.getController();
			mc.animateTo(point);
			mc.setZoom(16);
			
		} else {
			
			// example restoring focused state of overlays
			int focused;
			focused = savedInstanceState.getInt("focused_1", -1);
			if (focused >= 0) {
				itemizedOverlay.setFocus(itemizedOverlay.getItem(focused));
			}
//			focused = savedInstanceState.getInt("focused_2", -1);
//			if (focused >= 0) {
//				itemizedOverlay2.setFocus(itemizedOverlay2.getItem(focused));
//			}
			
		}
		
    }
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		
		// example saving focused state of overlays
		if (itemizedOverlay.getFocus() != null) outState.putInt("focused_1", itemizedOverlay.getLastFocusedIndex());
		if (itemizedOverlay2.getFocus() != null) outState.putInt("focused_2", itemizedOverlay2.getLastFocusedIndex());
		super.onSaveInstanceState(outState);
	
		// enables the activity icon as a 'home' button. required if "android:targetSdkVersion" > 14
        getActionBar().setHomeButtonEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 1, "Remove Overlay");
		return true;
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) 
	       {        
	          case android.R.id.home:            
	             Intent intent = new Intent(this, MainActivity.class);            
	             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
	             startActivity(intent);            
	             return true;        
	          default:            
	             return super.onOptionsItemSelected(item);    
	       }
		
	}
	
	
	
}
