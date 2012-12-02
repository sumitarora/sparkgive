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
	private ImageView mDoneButton, mShareButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_redeem);
		final ActionBar actionBar = getActionBar(); 
        Bitmap actionBarBackground = BitmapFactory.decodeResource(getResources(), R.drawable.footer_bar);
        BitmapDrawable background = new BitmapDrawable(getResources(), actionBarBackground);
        
        actionBar.setBackgroundDrawable(background);
        actionBar.setHomeButtonEnabled(true);
        
        profileButton = (ImageView) findViewById(R.id.profile);
        mapsButton = (ImageView) findViewById(R.id.Maps_button);
        publicCardsButton = (ImageView) findViewById(R.id.public_cards);
        settingsButton = (ImageView) findViewById(R.id.list);
        mDoneButton = (ImageView) findViewById(R.id.doneButton);
        mShareButton = (ImageView) findViewById(R.id.shareRedeem);
        
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
        cardBackImage.setBackgroundResource(selectedCard.getResourceIdRedeem());
        
        String details = selectedCard.getDetails();
        TextView detailsTextView = (TextView) findViewById(R.id.detailsText);
        detailsTextView.setText(details);
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
			break;
		case R.id.doneButton:
			startActivity(new Intent(getApplicationContext(), ThankYouActivity.class));
			break;
		case R.id.shareRedeem:
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
			sendIntent.setType("text/plain");
			startActivity(Intent.createChooser(sendIntent, "My Text"));
			break;
		}
		
	}
}
