package com.example.sample;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.readystatesoftware.maps.OnSingleTapListener;
import com.readystatesoftware.maps.TapControlledMapView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondScreenActivity extends MapActivity {
	TapControlledMapView mapView; // use the custom TapControlledMapView
	List<Overlay> mapOverlays;
	Drawable drawable;
	Drawable drawable2;
	SimpleItemizedOverlay itemizedOverlay;
	SimpleItemizedOverlay itemizedOverlay2;
	Button btnMapScreen;
	Button btnFavoriteScreen;
	Button btnPublicScreen;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

//		TextView txtName = (TextView) findViewById(R.id.txtName);
//		TextView txtEmail = (TextView) findViewById(R.id.txtEmail);
//
//		Intent i = getIntent();
//		// Receiving the Data
//		String name = i.getStringExtra("name");
//		String email = i.getStringExtra("email");
//		Log.e("Second Screen", name + "." + email);
//
//		// Displaying Received data
//		txtName.setText(name);
//		txtEmail.setText(email);

		btnMapScreen = (Button) findViewById(R.id.btnMapScreen);
		btnFavoriteScreen = (Button) findViewById(R.id.btnFavoriteScreen);
		btnPublicScreen = (Button) findViewById(R.id.btnPublicScreen);

		// Listening to button event
		btnFavoriteScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Starting a new Intent

				Intent nextScreen = new Intent(getApplicationContext(),
						FirstScreenActivity.class);

				startActivity(nextScreen);
			}

		});
		// Listening to button event
		btnPublicScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Starting a new Intent

				Intent nextScreen = new Intent(getApplicationContext(),
						ThirdScreenActivity.class);

				startActivity(nextScreen);
			}

		});
		
		////Map Code
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
		
		GeoPoint point = new GeoPoint((int)(51.5174723*1E6),(int)(-0.0899537*1E6));
		OverlayItem overlayItem = new OverlayItem(point, "Tomorrow Never Dies (1997)", 
				"(M gives Bond his mission in Daimler car)");
		itemizedOverlay.addOverlay(overlayItem);
		
		GeoPoint point2 = new GeoPoint((int)(51.515259*1E6),(int)(-0.086623*1E6));
		OverlayItem overlayItem2 = new OverlayItem(point2, "GoldenEye (1995)", 
				"(Interiors Russian defence ministry council chambers in St Petersburg)");		
		itemizedOverlay.addOverlay(overlayItem2);
		
		mapOverlays.add(itemizedOverlay);
		
		// second overlay
		drawable2 = getResources().getDrawable(R.drawable.marker2);
		itemizedOverlay2 = new SimpleItemizedOverlay(drawable2, mapView);
		// set iOS behavior attributes for overlay
		itemizedOverlay2.setShowClose(false);
		itemizedOverlay2.setShowDisclosure(true);
		itemizedOverlay2.setSnapToCenter(false);
		
		GeoPoint point3 = new GeoPoint((int)(51.513329*1E6),(int)(-0.08896*1E6));
		OverlayItem overlayItem3 = new OverlayItem(point3, "Sliding Doors (1998)", null);
		itemizedOverlay2.addOverlay(overlayItem3);
		
		GeoPoint point4 = new GeoPoint((int)(51.51738*1E6),(int)(-0.08186*1E6));
		OverlayItem overlayItem4 = new OverlayItem(point4, "Mission: Impossible (1996)", 
				"(Ethan & Jim cafe meeting)");
		itemizedOverlay2.addOverlay(overlayItem4);
		
		mapOverlays.add(itemizedOverlay2);
		
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
			focused = savedInstanceState.getInt("focused_2", -1);
			if (focused >= 0) {
				itemizedOverlay2.setFocus(itemizedOverlay2.getItem(focused));
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
		if (itemizedOverlay2.getFocus() != null) outState.putInt("focused_2", itemizedOverlay2.getLastFocusedIndex());
		super.onSaveInstanceState(outState);
	
	}
}