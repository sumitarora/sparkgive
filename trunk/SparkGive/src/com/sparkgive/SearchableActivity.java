package com.sparkgive;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.StackView;
import android.widget.TextView;

import com.sparkgive.components.Card;
import com.sparkgive.components.StackAdapter;
import com.sparkgive.components.StackItem;
import com.sparkgive.model.SparkGiveModel;


public class SearchableActivity extends Activity implements OnClickListener {

	private ImageView profileButton, mapsButton, publicCardsButton, settingsButton;
	
	ArrayList<Card> mCardList;
	ArrayList<StackItem> mStackItems;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        
        // enables the activity icon as a 'home' button. required if "android:targetSdkVersion" > 14
        final ActionBar actionBar = getActionBar(); 
        Bitmap actionBarBackground = BitmapFactory.decodeResource(getResources(), R.drawable.footer_bar);
        BitmapDrawable background = new BitmapDrawable(getResources(), actionBarBackground);
        
        actionBar.setBackgroundDrawable(background);
        actionBar.setHomeButtonEnabled(true);
        
        profileButton = (ImageView) findViewById(R.id.profile2);
        mapsButton = (ImageView) findViewById(R.id.Maps_button2);
        publicCardsButton = (ImageView) findViewById(R.id.public_cards2);
        settingsButton = (ImageView) findViewById(R.id.list2);
       

        
        profileButton.setOnClickListener(this);
        mapsButton.setOnClickListener(this);
        publicCardsButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);

        
       mStackItems = new ArrayList<StackItem>();
      mStackItems.add(new StackItem(this.getResources().getDrawable(R.drawable.sick_kids_card)));
      mStackItems.add(new StackItem(this.getResources().getDrawable(R.drawable.sick_kids_card)));
   	
   	///Stackview
       StackView stk = (StackView)this.findViewById(R.id.stackView2);
   	StackAdapter adapt = new StackAdapter(this, R.layout.item, mStackItems);
   	 
       stk.setAdapter(adapt);
        
    }

    

    @Override
    protected void onResume() {
    	super.onResume();
    	
    }
    
    @Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.profile2:
			startActivity(new Intent(getApplicationContext(), CampaignsActivity.class));
			break;
		case R.id.Maps_button2:
			startActivity(new Intent(getApplicationContext(), MapsActivity.class));
			break;	
		case R.id.public_cards2:
			startActivity(new Intent(getApplicationContext(), PublicCardsActivity.class));
			break;
		case R.id.list2:
//			startActivity(new Intent(getApplicationContext(), TestSearchActivity.class));
			break;
		
		}
    }

    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {    
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