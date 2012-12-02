package com.sparkgive;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sparkgive.components.Card;
import com.sparkgive.model.SparkGiveModel;

public class RedeemActivity extends Activity implements OnClickListener {
	private ImageView profileButton, mapsButton, publicCardsButton, settingsButton;
	private int mCardIndex;
	private Button mDoneButton, mShareButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_redeem);
		// enables the activity icon as a 'home' button. required if "android:targetSdkVersion" > 14
		final ActionBar actionBar = getActionBar(); 
        Bitmap actionBarBackground = BitmapFactory.decodeResource(getResources(), R.drawable.footer_bar);
        BitmapDrawable background = new BitmapDrawable(getResources(), actionBarBackground);
        
        actionBar.setBackgroundDrawable(background);
        actionBar.setHomeButtonEnabled(true);
        
        profileButton = (ImageView) findViewById(R.id.profile);
        mapsButton = (ImageView) findViewById(R.id.Maps_button);
        publicCardsButton = (ImageView) findViewById(R.id.public_cards);
        settingsButton = (ImageView) findViewById(R.id.list);
        mDoneButton = (Button) findViewById(R.id.doneButton);
        mShareButton = (Button) findViewById(R.id.shareButton);
        
        profileButton.setOnClickListener(this);
        mapsButton.setOnClickListener(this);
        publicCardsButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);
        mDoneButton.setOnClickListener(this);
        mShareButton.setOnClickListener(this);
        
        
        Intent intent = getIntent();
        mCardIndex = Integer.parseInt(intent.getStringExtra("tag"));
        Card selectedCard = SparkGiveModel.cardList.get(mCardIndex);
        
        
        ImageView cardBackImage = (ImageView) findViewById(R.id.cardBack);
        cardBackImage.setBackgroundResource(selectedCard.getResourceId());
        
        String details = selectedCard.getDetails();
//        RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.card_details);
//        relLayout.ALIGN_PARENT_BOTTOM;
        TextView detailsTextView = (TextView) findViewById(R.id.detailsText);
        detailsTextView.setText(details);
//        switch(mCardIndex) {
//        	case 0:
//        	cardBackImage.setBackgroundResource(R.drawable.pc_card);
//        	break;
//        	
//        	case 1:
//            cardBackImage.setBackgroundResource(R.drawable.footer_bar);
//            break;
//            
//        	case 2:
//                cardBackImage.setBackgroundResource(R.drawable.breast_cancer_card);
//                break;
//        }
        
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_redeem, menu);
//		return true;
//	}
	
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
//			startActivity(new Intent(getApplicationContext(), TestSearchActivity.class));
			break;
	
		case R.id.doneButton:
			startActivity(new Intent(getApplicationContext(), ThankYouActivity.class));
			break;
		case R.id.shareButton:
//			startActivity(new Intent(getApplicationContext(), ShareActivity.class));
			break;
		}
		
	}
}
