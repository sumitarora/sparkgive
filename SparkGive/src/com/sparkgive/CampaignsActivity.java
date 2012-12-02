package com.sparkgive;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CampaignsActivity extends Activity implements OnClickListener {

	private Button btnTweet;
	private EditText txtTwitter;
	private ConfigurationBuilder cb;
	private TwitterFactory tf;
	private Twitter twitter;

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
		setContentView(R.layout.activity_campaigns);
		// enables the activity icon as a 'home' button. required if "android:targetSdkVersion" > 14
		final ActionBar actionBar = getActionBar(); 
        Bitmap actionBarBackground = BitmapFactory.decodeResource(getResources(), R.drawable.footer_bar);
        BitmapDrawable background = new BitmapDrawable(getResources(), actionBarBackground);
        
        actionBar.setBackgroundDrawable(background);
        actionBar.setHomeButtonEnabled(true);
        
        txtTwitter = (EditText) findViewById(R.id.txtTweet2);
        btnTweet = (Button) findViewById(R.id.btnTweet2);
        btnTweet.setOnClickListener(this);
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
	      txtTwitter.setText("");
	      Toast.makeText(getApplicationContext(),"Tweet Posted!",Toast.LENGTH_SHORT).show();
	    }
	  }

	
    @Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.profile:
			break;
		case R.id.Maps_button:
			startActivity(new Intent(getApplicationContext(), MapsActivity.class));
			break;	
		case R.id.public_cards:
			startActivity(new Intent(getApplicationContext(), PublicCardsActivity.class));
			break;
		case R.id.list:
			break;
		case R.id.btnTweet2:
			DownloadWebPageTask task = new DownloadWebPageTask();
		    task.execute(new String[] {txtTwitter.getText().toString()});
			break;
		
		}
    }

}
