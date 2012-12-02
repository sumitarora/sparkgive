package com.sparkgive;

import java.util.List;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.sparkgive.components.OnSingleTapListener;
import com.sparkgive.components.SimpleItemizedOverlay;
import com.sparkgive.components.TapControlledMapView;


public class MapsActivity extends MapActivity {
	TapControlledMapView mapView; // use the custom TapControlledMapView
	List<Overlay> mapOverlays;
	Drawable drawable;
	Drawable drawable2;
	SimpleItemizedOverlay itemizedOverlay;
	SimpleItemizedOverlay itemizedOverlay2;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        
        mapView = (TapControlledMapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		
		// dismiss balloon upon single tap of MapView (iOS behavior) 
		mapView.setOnSingleTapListener(new OnSingleTapListener() {		
			@Override
			public boolean onSingleTap(MotionEvent e) {
				itemizedOverlay.hideAllBalloons();
				return true;
			}
		});
		
		mapOverlays = mapView.getOverlays();
		
		// first overlay
		drawable = getResources().getDrawable(R.drawable.marker);
		itemizedOverlay = new SimpleItemizedOverlay(drawable, mapView);
		// set iOS behavior attributes for overlay
		itemizedOverlay.setShowClose(false);
		itemizedOverlay.setShowDisclosure(true);
		itemizedOverlay.setSnapToCenter(false);
		
		GeoPoint point = new GeoPoint((int)(43.6454314*1E6),(int)(-79.3697728*1E6));
		OverlayItem overlayItem = new OverlayItem(point, "Loblaws Superstore: Live Healthy", 
				"In partnership with The Food Bank");
		itemizedOverlay.addOverlay(overlayItem);
		
		GeoPoint point2 = new GeoPoint((int)(43.6475768*1E6),(int)(-79.4020997*1E6));
		OverlayItem overlayItem2 = new OverlayItem(point2, "Shoppers Drug Mart Taking Care Of You", 
				"In partnership with Sick Kids Foundation");		
		itemizedOverlay.addOverlay(overlayItem2);
		
		GeoPoint point3 = new GeoPoint((int)(43.6472549*1E6),(int)(-79.4037073*1E6));
		OverlayItem overlayItem3 = new OverlayItem(point3, "Your Friendly Neighbourhood Starbucks", 
				"In partnership with Sick Kids Foundation");		
		itemizedOverlay.addOverlay(overlayItem3);
		
		mapOverlays.add(itemizedOverlay);
		
		
		
		if (savedInstanceState == null) {
			
			final MapController mc = mapView.getController();
			mc.animateTo(point2);
			mc.setZoom(16);
			
		} else {
			
			// example restoring focused state of overlays
			int focused;
			focused = savedInstanceState.getInt("focused_1", -1);
			if (focused >= 0) {
				itemizedOverlay.setFocus(itemizedOverlay.getItem(focused));
			}
			
			
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
		
		super.onSaveInstanceState(outState);
	
	}
	
}