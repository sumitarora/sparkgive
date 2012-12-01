package com.sparkgive;

import com.sparkgive.model.SparkGiveModel;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private ImageView profileButton, mapsButton, publicCardsButton, settingsButton;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	SparkGiveModel.nearByPlaces.add("Place 1");
    	SparkGiveModel.nearByPlaces.add("Place 2");
    	SparkGiveModel.nearByPlaces.add("Place 3");
    	
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        profileButton = (ImageView) findViewById(R.id.profile);
        mapsButton = (ImageView) findViewById(R.id.Maps_button);
        publicCardsButton = (ImageView) findViewById(R.id.public_cards);
        settingsButton = (ImageView) findViewById(R.id.list);
        
        profileButton.setOnClickListener(this);
        mapsButton.setOnClickListener(this);
        publicCardsButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);
        
    }

    @Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.profile:
			startActivity(new Intent(getApplicationContext(), CampaignsActivity.class));
			break;
		case R.id.Maps_button:
			startActivity(new Intent(getApplicationContext(), MapsActivity.class));
			break;	
		case R.id.public_cards:
			startActivity(new Intent(getApplicationContext(), PublicCardsActivity.class));
			break;
		case R.id.list:
			//startActivity(new Intent(getApplicationContext(), CampaignsActivity.class));
			break;
		}
    }

    

}
