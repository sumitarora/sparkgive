package com.sparkgive;

import java.util.List;

import com.sparkgive.model.SparkGiveModel;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ThankYouActivity extends Activity implements OnClickListener {
	
	private ImageView twitterButton;
	private LinearLayout boxTweet;
	private LinearLayout boxShare;
	private LinearLayout boxButtons;
	private Button btnTweet;
	private EditText txtTwitter;
	private ConfigurationBuilder cb;
	private TwitterFactory tf;
	private Twitter twitter;
	TextView txtThankYou;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey("UBHiQQLYdIXERVUQAcPUGg")
          .setOAuthConsumerSecret("7c5DO0HXvNdOmKsyeOzxGLVBJL3gu6kkHmcIa7YDuY")
          .setOAuthAccessToken("19441386-xPLoXONt6RxINl7Zh5OEgCUx8aXLrczXotdRkbAUk")
          .setOAuthAccessTokenSecret("BH4Vucb5LRwJSPzmhh4qibVbyWcRYZmy5vosryyBs");
        tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
        
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thank_you);
		final ActionBar actionBar = getActionBar(); 
        Bitmap actionBarBackground = BitmapFactory.decodeResource(getResources(), R.drawable.footer_bar);
        BitmapDrawable background = new BitmapDrawable(getResources(), actionBarBackground);
        
        actionBar.setBackgroundDrawable(background);
        actionBar.setHomeButtonEnabled(true);
        
        txtThankYou = (TextView) findViewById(R.id.txtThankYou);
		TextView txtUsername = (TextView) findViewById(R.id.txtUsername);
		TextView txtCampaign = (TextView) findViewById(R.id.txtCampaign);
		
        twitterButton = (ImageView) findViewById(R.id.imgTwitter);
        twitterButton.setOnClickListener(this);
        
        boxTweet = (LinearLayout) findViewById(R.id.boxTweet);
        boxShare = (LinearLayout) findViewById(R.id.boxShare);
        boxButtons = (LinearLayout) findViewById(R.id.boxButtons);
        btnTweet = (Button) findViewById(R.id.btnTweet);
        btnTweet.setOnClickListener(this);
        
		txtUsername.setText(SparkGiveModel.username);
		txtCampaign.setText(SparkGiveModel.campaign);
		txtThankYou.setText("Thank You");
		
		txtTwitter = (EditText) findViewById(R.id.txtTwitter);
	}

	private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
	    @Override
	    protected String doInBackground(String... tweet) {
	    	twitter4j.Status statusa;
			try {
				statusa = twitter.updateStatus(tweet[0]);
			} catch (TwitterException e) {
				e.printStackTrace();
			}
	    	return "aa";
	    }

	    @Override
	    protected void onPostExecute(String result) {
	      boxTweet.setVisibility(4);
			boxButtons.setVisibility(0);
			boxShare.setVisibility(0);
	      Toast.makeText(getApplicationContext(),"Tweet Posted!",Toast.LENGTH_SHORT).show();
	    }
	  }
	
	@Override
	public void onClick(View v) {
		System.out.println("clicked");
    	switch (v.getId()) {
		case R.id.imgTwitter:
			boxTweet.setVisibility(0);
			boxButtons.setVisibility(4);
			boxShare.setVisibility(4);
			break;
			
		case R.id.btnTweet:
			DownloadWebPageTask task = new DownloadWebPageTask();
		    task.execute(new String[] {txtTwitter.getText().toString()});
			break;
			
		case R.id.profile:
			startActivity(new Intent(getApplicationContext(), ThankYouActivity.class));
			break;
		case R.id.Maps_button:
			startActivity(new Intent(getApplicationContext(), MapsActivity.class));
			break;	
		case R.id.public_cards:
			startActivity(new Intent(getApplicationContext(), PublicCardsActivity.class));
			break;
		case R.id.list:
			//startActivity(new Intent(getApplicationContext(), TestSearchActivity.class));
			break;
    	}
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_thank_you, menu);
		return true;
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
